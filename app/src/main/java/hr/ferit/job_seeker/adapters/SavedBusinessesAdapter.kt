package hr.ferit.job_seeker.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.job_seeker.R
import hr.ferit.job_seeker.mediator.DataMediator
import hr.ferit.job_seeker.navigation.GoogleMapsLocation
import hr.ferit.job_seeker.storage.entities.Business
import kotlinx.coroutines.runBlocking

const val EXTRA_LONGITUDE = "hr.ferit.job_seeker.LONGITUDE"
const val EXTRA_LATITUDE = "hr.ferit.job_seeker.LATITUDE"

class SavedBusinessesAdapter(private val items: MutableList<Business>, private val context: Context):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return BusinessViewHolder(

            LayoutInflater.from(parent.context).inflate(
                R.layout.saved_business_adapter, parent,
                false)
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        when(holder) {
            is BusinessViewHolder -> {
                holder.bind(items[position], context)
                holder.itemView.findViewById<Button>(R.id.removeBusiness).setOnClickListener{
                    runBlocking{
                        DataMediator.removeFromDB(items[position])
                    }
                    items.removeAt(position)
                    notifyDataSetChanged()
                }
            }

        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    class BusinessViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        private val businessName: TextView = itemView.findViewById(R.id.savedBusinessName)
        private val businessPhone: TextView = itemView.findViewById(R.id.savedBusinessPhone)
        private val businessCity: TextView = itemView.findViewById(R.id.savedBusinessCity)
        private val businessAddress: TextView = itemView.findViewById(R.id.savedBusinessAddress)
        fun bind(businesses: Business, context: Context){
            businessName.text = businesses.name
            businessPhone.text = businesses.phone
            businessCity.text = businesses.city
            businessAddress.text = HtmlCompat.fromHtml("<u>${businesses.address}</u>",
                HtmlCompat.FROM_HTML_MODE_LEGACY)
            businessAddress.setOnClickListener{
                val intent = Intent(context, GoogleMapsLocation::class.java)
                intent.putExtra(EXTRA_LATITUDE, businesses.latitude)
                intent.putExtra(EXTRA_LONGITUDE, businesses.longitude)
                context.startActivity(intent)
            }
        }
    }
}