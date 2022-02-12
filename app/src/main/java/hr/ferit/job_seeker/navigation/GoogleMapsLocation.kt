package hr.ferit.job_seeker.navigation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.HtmlCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import hr.ferit.job_seeker.adapters.EXTRA_LATITUDE
import hr.ferit.job_seeker.adapters.EXTRA_LONGITUDE
import hr.ferit.job_seeker.R

class GoogleMapsLocation : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var bundle: Bundle
    private lateinit var mapFragment: SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps_location)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#0611EF")))
        supportActionBar?.title = HtmlCompat.fromHtml("<font color=\"white\">" + getString(R.string.app_name) + "</font>",
            HtmlCompat.FROM_HTML_MODE_LEGACY)
        bundle = intent.extras!!
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val latitude = bundle.getDouble(EXTRA_LATITUDE)
        val longitude = bundle.getDouble(EXTRA_LONGITUDE)
        map.addMarker(MarkerOptions().position(LatLng(latitude, longitude)))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 15F))
    }
}