////package com.example.akash.subscriber_a2
////
////class SubscriberDatabase {
////}
////
////
////
////package com.example.akash.subscriber_a2
////
////import android.content.Context
////import androidx.room.Database
////import androidx.room.Room
////import androidx.room.RoomDatabase
////
////@Database(entities = [SubscriberData::class], version = 1)
////abstract class SubscriberDatabase : RoomDatabase() {
////    abstract fun subscriberDao(): SubscriberDao
////
////    companion object {
////        @Volatile
////        private var INSTANCE: SubscriberDatabase? = null
////
////        fun getDatabase(context: Context): SubscriberDatabase {
////            return INSTANCE ?: synchronized(this) {
////                val instance = Room.databaseBuilder(
////                    context.applicationContext,
////                    SubscriberDatabase::class.java,
////                    "subscriber_database"
////                ).build()
////                INSTANCE = instance
////                instance
////            }
////        }
////    }
////}
////
//
//
//
//
//
//
//
//
//
///*
//package com.example.akash.subscriber_a2
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [SubscriberData::class], version = 1)
//abstract class SubscriberDatabase : RoomDatabase() {
//    abstract fun subscriberDao(): SubscriberDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: SubscriberDatabase? = null
//
//        fun getDatabase(context: Context): SubscriberDatabase {
//            return INSTANCE ?: synchronized(this) {
////                val instance = Room.databaseBuilder(
////                    context.applicationContext, // Pass application context here
////                    SubscriberDatabase::class.java, // The database class
////                    "subscriber_database" // The database name (string)
////                ).build()
//
////                val db = Room.databaseBuilder(
////                    context.applicationContext,
////                    SubscriberDatabase::class.java,
////                    "subscriber_database").build()
//
//                val databaseName = context.getString(R.string.database_name)
////                val db = Room.databaseBuilder(context.applicationContext,
////                    SubscriberDatabase::class.java,
////                    databaseName).build()
//
//                val db = Room.databaseBuilder(
//                    context,
//                    SubscriberDatabase::class.java,
//                    "subscriber_database",
//                    // ... extra arguments ...
//                ).build()
//
//                INSTANCE = db
//                db
//            }
//        }
//    }
//}
//*/
//
//
//
//
//
///*
//package com.example.akash.subscriber_a2
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [SubscriberData::class], version = 1)
//abstract class SubscriberDatabase : RoomDatabase() {
//    abstract fun subscriberDao(): SubscriberDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: SubscriberDatabase? = null
//
//        fun getDatabase(context: Context): SubscriberDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext, // Correct usage of context
//                    SubscriberDatabase::class.java, // Correct database class
//                    "subscriber_database" // Correct database name (string)
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//
//    }
//}
//*/
//package com.example.akash.subscriber_a2
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [SubscriberData::class], version = 1)
//abstract class SubscriberDatabase : RoomDatabase() {
//    abstract fun subscriberDao(): SubscriberDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: SubscriberDatabase? = null
//
//        fun getDatabase(context: Context): SubscriberDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext, // Correct usage of application context
//                    SubscriberDatabase::class.java, // Database class type
//                    "subscriber_database" // Database name (string)
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}



package com.example.akash.subscriber_a2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SubscriberData::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase() {
    abstract fun subscriberDao(): SubscriberDao

    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null

        fun getDatabase(context: Context): SubscriberDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, // Use applicationContext to prevent memory leaks
                    SubscriberDatabase::class.java, // The database class
                    "subscriber_database" // The name of the database
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

