package com.matecho.wms.model.educationLevel

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "EducationLevel")
data class EducationLevel(
        @PrimaryKey  var educationId :Int, var educationlevel:String
)
data class ResponseEducationLevel(
        val data: MutableList<EducationLevel>
)