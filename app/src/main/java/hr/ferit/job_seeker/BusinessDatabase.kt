package hr.ferit.job_seeker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration


@Database(entities = [Business::class], version = 1)
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