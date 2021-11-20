package com.example.aroma.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.aroma.R
import com.example.aroma.User
import com.example.aroma.presenter.IMainView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_bottomsheet_language_changer.*
import kotlinx.android.synthetic.main.fragment_bottomsheet_language_changer.view.*

class MainActivity : AppCompatActivity(),IMainView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun createUser(user: User) {

    }

    override fun onUserCreated() {
        TODO("Not yet implemented")
    }

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onError() {
        TODO("Not yet implemented")
    }

    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onLoginSuccesful() {
        TODO("Not yet implemented")
    }


    fun openLanguageChangeSheet()
    {
        val dialog = BottomSheetDialog(this)

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.fragment_bottomsheet_language_changer, null)


        // on below line we are adding on click listener
        // for our dismissing the dialog button.
   view.close_bottom_sheet.setOnClickListener {
            // on below line we are calling a dismiss
            // method to close our dialog.
            dialog.dismiss()
        }
        // below line is use to set cancelable to avoid
        // closing of dialog box when clicking on the screen.
        dialog.setCancelable(false)

        // on below line we are setting
        // content view to our view.
        dialog.setContentView(view)

        // on below line we are calling
        // a show method to display a dialog.
        dialog.show()
    }



}