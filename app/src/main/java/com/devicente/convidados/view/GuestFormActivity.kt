package com.devicente.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devicente.convidados.viewmodel.GuestFormViewModel
import com.devicente.convidados.R
import com.devicente.convidados.service.constants.GuestConstants
import kotlinx.android.synthetic.main.activity_guest_from_activity.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_from_activity)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        observe()
        setListeners()
        loadData()
        radio_presence.isChecked = true;

    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.button_save) {
            val name = edit_name.text.toString()
            val presence = radio_presence.isChecked
                mViewModel.save(mGuestId,name, presence)

        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            //CARREGAR
            mViewModel.load(mGuestId)
        }
    }

    private fun setListeners() {
        button_save.setOnClickListener(this)
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Failure", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
        mViewModel.guest.observe(this, Observer {
            edit_name.setText(it.name)
            if (it.presence) {
                radio_presence.isChecked = true
            } else {
                radio_absent.isChecked = true
            }
        })
    }


}