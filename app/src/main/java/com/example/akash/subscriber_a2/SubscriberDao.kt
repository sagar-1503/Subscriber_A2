////package com.example.akash.subscriber_a2
////
////class SubscriberDao {
////}
//
////package com.example.akash.subscriber_a2
////
////import androidx.room.Dao
////import androidx.room.Entity
////import androidx.room.Insert
////import androidx.room.PrimaryKey
////import androidx.room.Query
////
////@Dao
////interface SubscriberDao {
////    @Insert
////    suspend fun insert(subscriberData: MainActivity.SubscriberData)
////
////    @Query("SELECT * FROM subscriber_data")
////    suspend fun getAllData(): List<SubscriberData>
////
////    @Entity(tableName = "subscriber_data")
////    data class SubscriberData(
////        @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto-increment ID
////        val studentId: String,
////        val latitude: Double,
////        val longitude: Double,
////        val minSpeed: Double,
////        val maxSpeed: Double
////    )
////}
//
//
//
///*
//package com.example.akash.subscriber_a2
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//
//@Dao
//interface SubscriberDao {
//
//    // Insert a new subscriber data entry
//    @Insert
//    suspend fun insert(subscriberData: SubscriberData)
//
//    // Get all data entries from the database
//    @Query("SELECT * FROM subscriber_data")
//    suspend fun getAllData(): List<SubscriberData>
//
//    // Get all data for a specific Student ID
//    @Query("SELECT * FROM subscriber_data WHERE studentId = :studentId")
//    suspend fun getDataByStudentId(studentId: String): List<SubscriberData>
//
//    // Delete all data from the database
//    @Query("DELETE FROM subscriber_data")
//    suspend fun deleteAll()
//
//    // Delete data by Student ID
//    @Query("DELETE FROM subscriber_data WHERE studentId = :studentId")
//    suspend fun deleteByStudentId(studentId: String)
//}
//
//
//
//*/
//
//
//
//
//
//
//
//
//package com.example.akash.subscriber_a2
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//
//@Dao
//interface SubscriberDao {
//    @Insert
//    suspend fun insert(subscriberData: SubscriberData)
//
//    @Query("SELECT * FROM subscriber_data")
//    suspend fun getAllData(): List<SubscriberData>
//}
//
//
//





package com.example.akash.subscriber_a2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubscriberDao {

    // Insert new subscriber data into the database
    @Insert
    suspend fun insert(subscriberData: SubscriberData)

    // Get all coordinates from the database
    @Query("SELECT * FROM subscriber_data")
    suspend fun getAllData(): List<SubscriberData>

    // Get the data by Student ID
    @Query("SELECT * FROM subscriber_data WHERE studentId = :studentId")
    suspend fun getDataByStudentId(studentId: String): List<SubscriberData>

    // Clear the database
    @Query("DELETE FROM subscriber_data")
    suspend fun clearAll()
}

