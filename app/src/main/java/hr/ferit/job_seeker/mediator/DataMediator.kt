package hr.ferit.job_seeker.mediator

import hr.ferit.job_seeker.storage.BusinessDao
import hr.ferit.job_seeker.storage.entities.Business

object DataMediator {

    private lateinit var businessDao: BusinessDao

    fun setDao(businessDao: BusinessDao){
        DataMediator.businessDao = businessDao
    }
    fun getDao(): BusinessDao{
        return businessDao
    }
}
