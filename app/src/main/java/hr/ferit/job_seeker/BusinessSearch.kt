package hr.ferit.job_seeker

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.*
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NumberFormatException
import kotlin.properties.Delegates

private const val API_KEY = "vpGjCvwSFYBbTmbzOOpczM4g7xwSL_AUyJ3LUnrqQ4xNoC1Ztt15qhLuqZDxN5VNi7NFYo0d09vhy2Qwihl9fL9sQMWedAD-gF7tbNg1Xv68hms90cupMvCq-VTTYXYx"
private const val LIMIT = 50

class BusinessSearch : AppCompatActivity() {

    private lateinit var location: EditText
    private lateinit var term: EditText
    private lateinit var hide: MaterialButton
    private lateinit var search: MaterialButton
    private lateinit var businessView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_search)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#0611EF")))
        supportActionBar?.title = HtmlCompat.fromHtml("<font color=\"white\">" + getString(R.string.app_name) + "</font>",
            HtmlCompat.FROM_HTML_MODE_LEGACY)
        location = findViewById(R.id.inputLocation)
        term = findViewById(R.id.inputTerm)
        hide = findViewById(R.id.lessOptns)
        search = findViewById(R.id.searchJobs)
        businessView = findViewById(R.id.businessList)
        hide.setOnClickListener {
                term.visibility = GONE
                hide.visibility = GONE
                term.setText("")
        }
        search.setOnClickListener {

            businessView.adapter = BusinessesAdapter(ArrayList())

            val request = ServiceBuilder.buildService(YelpBusinessService::class.java)
            val call = request.getPersons("Bearer $API_KEY",
                    location.text.toString(), term.text.toString(), LIMIT)
            if(location.text.toString() == ""){
                Toast.makeText(this@BusinessSearch, getString(R.string.EmptyRequest),
                    Toast.LENGTH_SHORT).show()
            }
            call.enqueue(object : Callback<BusinessesTotal> {
                override fun onResponse(call: Call<BusinessesTotal>, response:
                Response<BusinessesTotal>
                ) {
                    if(response.isSuccessful) {
                        businessView.apply {
                            layoutManager =
                                LinearLayoutManager(this@BusinessSearch)
                            adapter =
                                BusinessesAdapter(response.body()!!.businesses)
                        }
                    }
                }
                override fun onFailure(call: Call<BusinessesTotal>, t: Throwable)
                {
                    Toast.makeText(this@BusinessSearch, getString(R.string.InvalidRequest),
                        Toast.LENGTH_LONG).show()
                    Log.d("FAIL", t.message.toString())

                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.extras, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.additional){
            term.visibility = VISIBLE
            hide.visibility = VISIBLE
        }
        return super.onOptionsItemSelected(item)
    }
}