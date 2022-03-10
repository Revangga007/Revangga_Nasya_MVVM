package com.revangga.revangga_nasya_mvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.revangga.revangga_nasya_mvvm.data.model.Dog

@Database(entities = [Dog::class], version = 1)
abstract class DogsDatabase : RoomDatabase() {
    abstract  fun dog() : DogsDao

    companion object {
        private var database: DogsDatabase? = null

        fun instance(context: Context): DogsDatabase {
            if (database == null) {
                synchronized(DogsDatabase::class) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        DogsDatabase::class.java,
                        "dogs_database.db"
                    ).build()
                }
            }
            return database!!
        }

        fun destroyInstance() {
            database = null
        }
    }
}