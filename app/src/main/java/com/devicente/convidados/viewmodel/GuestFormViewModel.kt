package com.devicente.convidados.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devicente.convidados.service.model.GuestModel
import com.devicente.convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository(mContext)
    private var mSavedGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSavedGuest;

    private var mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest;


    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel().apply {
            this.id = id
            this.name = name
            this.presence = presence
        }


        if (id == 0) {
            mSavedGuest.value = mGuestRepository.save(guest)

        } else {
            mSavedGuest.value = mGuestRepository.update(guest)
        }


    }

    fun load(id: Int) {
        mGuest.value = mGuestRepository.get(id)
    }
}