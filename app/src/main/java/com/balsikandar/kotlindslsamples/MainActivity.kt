package com.balsikandar.kotlindslsamples

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.balsikandar.kotlindslsamples.customAlert.AlertData
import com.balsikandar.kotlindslsamples.customAlert.CustomAlert.Companion.customAlert
import com.balsikandar.kotlindslsamples.defaultAlert.Alert.Companion.alert
import com.balsikandar.kotlindslsamples.permission.PermissionRequest.Companion.getPermissions

class MainActivity : AppCompatActivity() {

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        //request permission
        requestStoragepermission()

        //show default alertDialog
        //showDefaultAlert()

        //show custom alert
        showCustomAlert()
    }

    private fun showCustomAlert() {
        val alertData = AlertData(
                "This is a Custom Dialog Title",
                "This is a Custom Dialog Description"
        )
        customAlert {

            layoutId = R.layout.layout_dialog
            setCustomView = { it: View, dialog: AlertDialog ->

                it.findViewById<TextView>(R.id.title).text = alertData.title
                it.findViewById<TextView>(R.id.description).text = alertData.description

                it.findViewById<TextView>(R.id.accept).setOnClickListener {
                    Toast.makeText(context, "accept button click", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }

                it.findViewById<TextView>(R.id.reject).setOnClickListener {
                    Toast.makeText(context, "reject button click", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }

            }
        }
    }

    private fun showDefaultAlert() {
        alert {
            title = "Hey Title"
            description = "Hey Description"
            alertContext = context
            positiveButton = {
                Toast.makeText(context, "Yes", Toast.LENGTH_LONG).show()
            }

            negativeButton = {
                Toast.makeText(context, "And No", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun requestStoragepermission() {
        val permissionsList = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        getPermissions {
            permissions = permissionsList
            resultCode = 4
            onPermissionGranted = {
                Toast.makeText(context, "onPermissionGranted", Toast.LENGTH_LONG).show()
            }

            onPermissionDenied = {
                Toast.makeText(context, "onPermissionDenied", Toast.LENGTH_LONG).show()
            }
        }
    }
}
