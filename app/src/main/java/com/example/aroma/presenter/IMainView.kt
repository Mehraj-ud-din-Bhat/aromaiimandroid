package com.example.aroma.presenter

import com.example.aroma.User

interface IMainView {
    fun createUser(user: User)
    fun onUserCreated()
    fun onError(msg:String)
    fun onError()
    fun onSuccess()
    fun onSuccess(msg:String)
    fun onLoginSuccesful()


}