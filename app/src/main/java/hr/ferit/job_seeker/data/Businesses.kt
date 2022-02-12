package hr.ferit.job_seeker.data

data class Businesses(
    val id: String,
    val name: String,
    val image_url: String,
    val rating: Float,
    val phone: String,
    val coordinates: Coordinates,
    val review_count: Int,
    val is_closed: Boolean,
    val categories: ArrayList<Categories>,
    val location: Location
)