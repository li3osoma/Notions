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

    @Query("UPDATE notions SET title = :title, body = :text WHERE ID = :id")
    fun updateById(id:Int,title:String,text:String)

    @Query("DELETE FROM notions WHERE ID = :id")
    fun deleteById(id: Int)

}