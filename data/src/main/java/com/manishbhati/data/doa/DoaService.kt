package com.manishbhati.data.doa

import androidx.lifecycle.LiveData
import androidx.room.*
import com.manishbhati.data.util.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TableData(
    @PrimaryKey @JvmField val id: Long,
    val gender: String,
    val dob: Int,
    val name: String,
    val location: String,
    val mob: String,
    val email: String,
    val img: String,
    val isAccepted: Boolean = false
)

@Dao
interface DoaService {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<TableData>)

    @Query("SELECT * FROM $TABLE_NAME order by id desc LIMIT 1")
    fun getData(): LiveData<TableData>

    @Query("update $TABLE_NAME set isAccepted=:status where id =:pid")
    suspend fun update(pid: Long, status: Boolean)


}