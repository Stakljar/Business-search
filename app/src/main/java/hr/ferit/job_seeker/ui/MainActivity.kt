package hr.ferit.job_seeker.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.text.HtmlCompat
import com.google.android.material.button.MaterialButton
import hr.ferit.job_seeker.R
import hr.ferit.job_seeker.ui.description.About

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#0611EF")))
        supportActionBar?.title = HtmlCompat.fromHtml("<font color=\"white\">" + getString(R.string.app_name) + "</font>",
            HtmlCompat.FROM_HTML_MODE_LEGACY)
        findViewById<MaterialButton>(R.id.searchButton).setOnClickListener(){
            val intent = Intent(this, BusinessSearch::class.java)
            startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.dataButton).setOnClickListener(){
            val intent = Intent(this, Wishlist::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.about){
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}