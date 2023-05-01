package com.example.notions.trackers.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notions.notions.entity.Notion
import com.example.notions.trackers.entity.Tracker

@Dao
interface TrackerDao {
    @Query("SELECT * FROM trackers1")
    fun getTrackersList():List<Tracker>

    @Query("SELECT * FROM trackers1 WHERE ID = :id")
    fun getTrackerById(id:Int): Tracker

    @Insert
    fun insert(tracker: Tracker)

    @Query("UPDATE trackers1 SET title = :title, " +
            "days_number = :days_number, " +
            "complete_number = :complete_number, " +
            "start=:start, " +
            "finish = :finish, " +
            "color = :color, " +
            "is_passed = :is_passed WHERE ID = :id")
    fun updateById(id:Int,
                   title:String,
                   days_number:Int,
                   complete_number:Int,
                   start:String,
                   finish:String,
                   color:Int,
                   is_passed:Boolean)

    @Query("DELETE FROM trackers1 WHERE ID = :id")
    fun deleteById(id: Int)

}