////package com.example.akash.subscriber_a2
////
////class SubscriberData {
////}
//package com.example.akash.subscriber_a2
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity(tableName = "subscriber_data")
//data class SubscriberData(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val studentId: String,
//    val latitude: Double,
//    val longitude: Double,
//    val minSpeed: Double,
//    val maxSpeed: Double
//)

package com.example.akash.subscriber_a2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber_data")
data class SubscriberData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto-increment ID
    val studentId: String,
    val latitude: Double,
    val longitude: Double,
    val minSpeed: Double,
    val maxSpeed: Double
)





