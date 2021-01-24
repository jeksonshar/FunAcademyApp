package com.jeksonshar.funacademyapp.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jeksonshar.funacademyapp.R
import java.io.Serializable
import java.lang.IllegalStateException

class NoInternetConnectionListDialog : DialogFragment(), Serializable {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(context?.resources?.getString(R.string.attention_no_internet))
                .setMessage(context?.resources?.getString(R.string.check_internet_connection))
                .setPositiveButton("Ok") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}