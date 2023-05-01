package com.example.notions.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notions.notions.dao.NotionDao
import com.example.notions.notions.entity.Notion
import com.example.notions.trackers.dao.TrackerDao

@Database(entities = [Notion::class], version = 1)
abstract class NotionsDatabase : RoomDatabase() {
    abstract fun notionDao(): NotionDao
    abstract fun trackerDao(): TrackerDao
}