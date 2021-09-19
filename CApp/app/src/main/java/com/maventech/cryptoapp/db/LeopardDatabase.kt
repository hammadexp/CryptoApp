package com.matecho.wms.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.matecho.wms.model.educationLevel.EducationLevel
import java.util.concurrent.Executors

@Database(entities = [EducationLevel::class], version = 1, exportSchema = false)
abstract class LeopardDatabase : RoomDatabase() {
    abstract fun clientsDao(): ClientsDao

    companion object {
        @Volatile
        private var INSTANCE: LeopardDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        @JvmField
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        @JvmStatic
        fun getDatabase(context: Context): LeopardDatabase? {
            if (INSTANCE == null) {
                synchronized(LeopardDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                LeopardDatabase::class.java, "word_database")
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}