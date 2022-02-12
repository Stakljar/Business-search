package hr.ferit.job_seeker.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Business(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "Name") val name: String?,
    @ColumnInfo(name = "Phone") val phone: String?,
    @ColumnInfo(name = "City") val city: String?,
    @ColumnInfo(name = "Address") val address: String?,
    @ColumnInfo(name = "Latitude") val latitude: Double?,
    @ColumnInfo(name = "Longitude") val longitude: Double?
)
