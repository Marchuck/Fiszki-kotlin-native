package com.marchuck.fiszki.data.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Language
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Lesson

@Entity(tableName = "lesson")
data class RoomLesson(@PrimaryKey(autoGenerate = false)
                      @ColumnInfo(name = "id") var id: Long?,
                      @ColumnInfo(name = "translationFrom") var translationFrom: String,
                      @ColumnInfo(name = "translationTo") var translationTo: String,
                      @ColumnInfo(name = "name") var name: String)

fun Lesson.toRoom() = RoomLesson(id,
        translationFrom.code,
        translationTo.code,
        name)

fun RoomLesson.fromRoom() = Lesson(id!!,
        Language.recognize(translationFrom),
        Language.recognize(translationTo),
        name)