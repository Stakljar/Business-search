package hr.ferit.job_seeker

object DataMediator {

    private val businesses = ArrayList<Business>()
    private lateinit var businessDao: BusinessDao

    fun setDao(businessDao: BusinessDao){
        this.businessDao = businessDao
    }
    suspend fun removeFromDB(business: Business){
        businessDao.delete(business)
    }
    fun addBusiness(business: Business){
        businesses.add(business)
    }
    fun getAllBusinesses(): ArrayList<Business>{
        return businesses
    }
    fun removeAll(){
        businesses.clear()
    }
}