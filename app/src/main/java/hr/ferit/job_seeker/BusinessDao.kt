package hr.ferit.job_seeker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

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