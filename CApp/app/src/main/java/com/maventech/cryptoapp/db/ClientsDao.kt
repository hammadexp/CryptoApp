package com.matecho.wms.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matecho.wms.model.educationLevel.EducationLevel

@Dao
interface ClientsDao {
    // allowing the insert of the same word multiple times by passing a 
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<EducationLevel>)

    @Query("SELECT * FROM EducationLevel ")
    fun fetchAllData(): LiveData<List<EducationLevel>>

    @Query("SELECT count(*) from EducationLevel")
    fun getSize():Int

}