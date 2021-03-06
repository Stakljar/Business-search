package hr.ferit.job_seeker.api

import hr.ferit.job_seeker.data.BusinessesTotal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpBusinessService {
    @GET("/v3/businesses/search")
    fun getBusinesses(
        @Header("Authorization") authorization: String,
        @Query("location") location: String,
        @Query("term") term: String,
        @Query("limit") limit: Int):
            Call<BusinessesTotal>
}
