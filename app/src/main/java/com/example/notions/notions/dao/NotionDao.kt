package com.example.notions.notions.dao

import androidx.room.*
import com.example.notions.notions.entity.Notion

@Dao
interface NotionDao {
    @Query("SELECT * FROM notions1")
    fun getNotionsList():List<Notion>

    @Query("SELECT * FROM notions1 WHERE ID = :id")
    fun getNotionById(id:Int): Notion

    @Insert
    fun insert(notion: Notion)

    @Query("UPDATE notions1 SET title = :title, body = :text, date = :dateText WHERE ID = :id")
    fun updateById(id:Int,title:String,text:String, dateText:String)

    @Query("DELETE FROM notions1 WHERE ID = :id")
    fun deleteById(id: Int)

}