package com.example.notions.notions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notions1")
class Notion(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val text: String,
    @ColumnInfo(name = "date") var date:String="")
{
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}