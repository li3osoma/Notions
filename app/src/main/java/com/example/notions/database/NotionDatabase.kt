package com.example.notions.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notions.dao.NotionDao
import com.example.notions.entity.Notion

@Database(entities = [Notion::class], version = 1)
abstract class NotionDatabase : RoomDatabase() {
    abstract fun notionDao(): NotionDao
}