package hr.ferit.job_seeker.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hr.ferit.job_seeker.storage.entities.Business

@Dao
interface BusinessDao {
    @Query("SELECT * FROM business")
    suspend fun getAll(): List<Business>

    @Query("SELECT id FROM business")
    suspend fun getAllIds(): List<String>

    @Query("DELETE FROM business")
    suspend fun removeAll()

    @Insert
    suspend fun insert(business: Business)

    @Delete
    suspend fun delete(business: Business)
}