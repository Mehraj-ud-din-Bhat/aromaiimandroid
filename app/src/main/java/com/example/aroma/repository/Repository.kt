package com.example.aroma.repository

import com.example.aroma.User

class Repository {

    companion object{
        private   var mainRepository: Repository?=null

        fun  getMainRepository(): Repository? {

            synchronized(Repository::class.java) {
                if(mainRepository==null)
                {
                    Repository.mainRepository= Repository()
                }
            }
            return Repository.mainRepository
        }
    }

    fun createUser( user:User){

    }


    fun login(email:String,password:String)
    {

    }

    fun forgotPassword(email:String)
    {

    }




}