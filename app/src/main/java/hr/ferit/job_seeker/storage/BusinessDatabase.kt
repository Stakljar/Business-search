package hr.ferit.job_seeker.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.ferit.job_seeker.storage.entities.Business

@Database(entities = [Business::class], version = 1, exportSchema = false)
abstract class BusinessDatabase : RoomDatabase() {

    companion object{
        private var instance: BusinessDatabase? = null
        fun getInstance(context: Context): BusinessDatabase?{
                if(instance == null)
                    synchronized(this) {
                        instance = buildDatabase(context)
                    }
            return instance
        }
        fun destroyInstance(){
            instance = null
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            BusinessDatabase::class.java, "Business Database"
        ).build()
    }
    abstract fun getBusinessDao(): BusinessDao
}
