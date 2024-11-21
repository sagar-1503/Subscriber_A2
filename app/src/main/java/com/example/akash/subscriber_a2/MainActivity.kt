
package com.example.akash.subscriber_a2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import java.nio.charset.StandardCharsets
import java.util.UUID

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private var mqttClient: Mqtt5AsyncClient? = null
    private val devicePath = mutableListOf<LatLng>()

    companion object {
        private const val MQTT_BROKER_HOST = "broker-816036149.sundaebytestt.com"
        private const val MQTT_BROKER_PORT = 1883
        private const val MQTT_TOPIC = "assignment/location"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Initialize the map
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Initialize the MQTT client
        mqttClient = Mqtt5Client.builder()
            .identifier(UUID.randomUUID().toString())
            .serverHost(MQTT_BROKER_HOST)
            .serverPort(MQTT_BROKER_PORT)
            .buildAsync()

        connectToBroker()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomControlsEnabled = true // Enable zoom controls
        googleMap.uiSettings.isScrollGesturesEnabled = true // Allow panning
        googleMap.uiSettings.isZoomGesturesEnabled = true // Allow zooming
    }

    private fun connectToBroker() {
        mqttClient?.connect()?.whenComplete { _, exception ->
            if (exception != null) {
                runOnUiThread {
                    Toast.makeText(this, "Failed to connect to MQTT broker", Toast.LENGTH_SHORT).show()
                }
                Log.e("MQTT", "Connection error: $exception")
            } else {
                runOnUiThread {
                    Toast.makeText(this, "Connected to MQTT broker", Toast.LENGTH_SHORT).show()
                }
                subscribeToTopic()
            }
        }
    }

    private fun subscribeToTopic() {
        mqttClient?.subscribeWith()
            ?.topicFilter(MQTT_TOPIC)
            ?.callback { publish ->
                val message = String(publish.payloadAsBytes, StandardCharsets.UTF_8)
                Log.d("MQTT", "Received message: $message")

                processPayload(message)
            }
            ?.send()
            ?.whenComplete { _, exception ->
                if (exception != null) {
                    runOnUiThread {
                        Toast.makeText(this, "Failed to subscribe to topic", Toast.LENGTH_SHORT).show()
                    }
                    Log.e("MQTT", "Subscription error: $exception")
                } else {
                    Log.d("MQTT", "Subscribed to topic: $MQTT_TOPIC")
                }
            }
    }

    private fun processPayload(message: String) {
        try {
            val lines = message.lines()
            val studentId = lines[0].substringAfter("Student ID: ").trim()
            val latitude = lines[1].substringAfter("Lat: ").toDoubleOrNull()
                ?: throw IllegalArgumentException("Invalid latitude value")
            val longitude = lines[2].substringAfter("Lon: ").toDoubleOrNull()
                ?: throw IllegalArgumentException("Invalid longitude value")
            val minSpeed = lines[3].substringAfter("Min Speed: ").toDoubleOrNull() ?: 0.0
            val maxSpeed = lines[4].substringAfter("Max Speed: ").toDoubleOrNull() ?: 0.0

            val latLng = LatLng(latitude, longitude)
            devicePath.add(latLng)
            drawPath(latLng)
        } catch (e: Exception) {
            Log.e("MQTT", "Error processing payload: $e")
            runOnUiThread {
                Toast.makeText(this, "Error processing the data.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun drawPath(latLng: LatLng) {
        runOnUiThread {
            googleMap.addMarker(
                MarkerOptions().position(latLng).title("Location Marker")
            )
            googleMap.addPolyline(
                PolylineOptions().addAll(devicePath).width(5f).color(0xFF0000FF.toInt())
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mqttClient?.disconnect()
    }
}



