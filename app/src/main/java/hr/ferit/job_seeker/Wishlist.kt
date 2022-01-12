package hr.ferit.job_seeker

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking

class Wishlist : AppCompatActivity() {

    private lateinit var database: BusinessDatabase
    private lateinit var businessDao: BusinessDao
    private lateinit var businessesView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#0611EF")))
        supportActionBar?.title = HtmlCompat.fromHtml("<font color=\"white\">" + getString(R.string.app_name) + "</font>",
            HtmlCompat.FROM_HTML_MODE_LEGACY)

        businessesView = findViewById(R.id.savedBusinessList)
        runBlocking {

                database = BusinessDatabase.getInstance(applicationContext)!!
                businessDao = database.getBusinessDao()
                DataMediator.setDao(businessDao)
                for (i in DataMediator.getAllBusinesses())
                    if(!(businessDao.getAllIds().contains(i.id)))
                        businessDao.insert(i)
                businessesView.apply{
                    layoutManager =
                        LinearLayoutManager(this@Wishlist)
                    adapter =
                        SavedBusinessesAdapter(businessDao.getAll().toMutableList(), context)

                }
                DataMediator.removeAll()
        }


    }
}