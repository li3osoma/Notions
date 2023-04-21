package com.example.notions.dao

import androidx.room.*
import com.example.notions.entity.Notion

@Dao
interface NotionDao {
    @Query("SELECT * FROM notions")
    fun getNotionsList():List<Notion>

    @Query("SELECT * FROM notions WHERE ID = :id")
    fun getNotionById(id:Int):Notion

    @Insert
    fun insert(notion: Notion)

    @Update
    fun update(notion: Notion)

    @Delete
    fun delete(notion: Notion)
}