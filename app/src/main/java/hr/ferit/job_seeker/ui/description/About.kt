package hr.ferit.job_seeker.ui.description

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.HtmlCompat
import hr.ferit.job_seeker.R

class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#0611EF")))
        supportActionBar?.title = HtmlCompat.fromHtml("<font color=\"white\">" + getString(R.string.app_name) + "</font>",
            HtmlCompat.FROM_HTML_MODE_LEGACY);
    }
}