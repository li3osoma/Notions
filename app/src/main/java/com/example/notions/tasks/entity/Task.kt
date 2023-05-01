package com.example.notions.tasks.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks1")
class Task (
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "deadline") val deadline: String,
    @ColumnInfo(name = "is_complete") val is_complete: Boolean,
    @ColumnInfo(name = "is_overdue") val is_overdue: Boolean,
    )
{
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}