package hr.ferit.job_seeker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import android.widget.*
import hr.ferit.job_seeker.mediator.DataMediator
import hr.ferit.job_seeker.R
import hr.ferit.job_seeker.data.Businesses
import hr.ferit.job_seeker.storage.entities.Business

class BusinessesAdapter(private val items: List<Businesses>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return BusinessViewHolder(

            LayoutInflater.from(parent.context).inflate(
                R.layout.business_adapter, parent,
                false)
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        when(holder) {
            is BusinessViewHolder -> {
                holder.bind(items[position])
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    class BusinessViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        private val businessImage: ImageView = itemView.findViewById(R.id.businessPhoto)
        private val businessName: TextView = itemView.findViewById(R.id.businessName)
        private val businessRating: RatingBar = itemView.findViewById(R.id.businessRating)
        private val reviews: TextView = itemView.findViewById(R.id.reviewCount)
        private val businessCategory: TextView = itemView.findViewById(R.id.businessCategory)
        private val status: TextView = itemView.findViewById(R.id.status)
        private val saveToList: Button = itemView.findViewById(R.id.addItem)
        fun bind(businesses: Businesses) {
            Glide
                .with(itemView.context)
                .load(businesses.image_url)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(10)))
                .into(businessImage)
            businessName.text = businesses.name
            businessRating.rating = businesses.rating
            saveToList.setOnClickListener{
                runBlocking {
                    try {
                        DataMediator.getDao().insert(
                            Business(
                                "${businesses.id}",
                                "${businesses.name}",
                                "${businesses.phone}",
                                "${businesses.location.city}",
                                "${businesses.location.address1}",
                                businesses.coordinates.latitude,
                                businesses.coordinates.longitude
                            )
                        )
                    }
                    catch(e: SQLiteConstraintException){ }
                }
            }
            reviews.text = "${businesses.review_count} recenzija"
            try{
                businessCategory.text = businesses.categories[0].title
            }
            catch(e: IndexOutOfBoundsException){
                businessCategory.text=""
            }
            if(!businesses.is_closed)
                status.text = "Otvoreno"
            else
                status.text = "Zatvoreno"

        }
    }
}
