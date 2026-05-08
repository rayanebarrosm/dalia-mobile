package com.example.dalia2.data.local


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CicloDao {
    @Insert
    suspend fun insert( ciclo: CicloEntity)
    @Update
    suspend fun update( ciclo: CicloEntity)
    @Delete
    suspend fun delete( ciclo: CicloEntity)
    @Query("SELECT * FROM ciclo")
    suspend fun getCiclo(): CicloEntity?
}