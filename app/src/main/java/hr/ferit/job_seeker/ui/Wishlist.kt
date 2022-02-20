package hr.ferit.job_seeker.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.job_seeker.R
import hr.ferit.job_seeker.adapters.SavedBusinessesAdapter
import hr.ferit.job_seeker.mediator.DataMediator
import hr.ferit.job_seeker.storage.BusinessDao
import hr.ferit.job_seeker.storage.BusinessDatabase
import kotlinx.coroutines.runBlocking

class Wishlist : AppCompatActivity() {

    private lateinit var businessesView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#0611EF")))
        supportActionBar?.title = HtmlCompat.fromHtml("<font color=\"white\">" + getString(R.string.app_name) + "</font>",
            HtmlCompat.FROM_HTML_MODE_LEGACY)

        businessesView = findViewById(R.id.savedBusinessList)
        runBlocking {
                businessesView.apply{
                    layoutManager =
                        LinearLayoutManager(this@Wishlist)
                    adapter =
                        SavedBusinessesAdapter(DataMediator.getDao().getAll().toMutableList(), context)
                }
        }
    }
}
