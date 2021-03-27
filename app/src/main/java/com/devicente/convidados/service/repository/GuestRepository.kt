package com.devicente.convidados.service.repository

import android.content.Context
import com.devicente.convidados.service.model.GuestModel

class GuestRepository (context: Context) {


    private val mDatabase = GuestDatabase.getDatabase(context).guestDAO()



    fun get(id: Int): GuestModel {
       return mDatabase.get(id);
    }

    fun save(guest: GuestModel): Boolean {
       return mDatabase.save(guest) > 1;
    }


    fun getAll(): List<GuestModel> {
        return mDatabase.getInvited()
    }

    fun getPresent(): List<GuestModel> {
        return mDatabase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return mDatabase.getAbsent()
    }

    fun update(guest: GuestModel): Boolean {
        return mDatabase.update(guest) > 1
    }


    fun remove(guest: GuestModel) {
        return mDatabase.delete(guest);
    }
}