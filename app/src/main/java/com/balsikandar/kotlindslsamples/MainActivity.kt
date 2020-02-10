package com.balsikandar.kotlindslsamples

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.balsikandar.kotlindslsamples.bottomsheetdialog.BottomSheetDSLBuilder.Companion.bottomSheetDialog
import com.balsikandar.kotlindslsamples.customAlertDialog.AlertData
import com.balsikandar.kotlindslsamples.customAlertDialog.CustomAlert.Companion.customAlert
import com.balsikandar.kotlindslsamples.defaultAlertDialog.Alert.Companion.alert
import com.balsikandar.kotlindslsamples.dialogfragment.DialogDSLBuilder.Companion.dialog
import com.balsikandar.kotlindslsamples.permission.PermissionRequest.Companion.getPermissions
import kotlinx.android.synthetic.main.layout_dialog.view.*

class MainActivity : AppCompatActivity() {

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
    }

    fun showDialogFragment(view: View) {
        dialog {
            layoutId = R.layout.layout_dialog
            setCustomView = {it: View, dialog: DialogFragment ->

                it.title.text = getString(R.string.fragment_dialog_title)
                it.description.text = getString(R.string.fragment_dialog_description)

                it.accept.setOnClickListener {
                    Toast.makeText(context, "accept button click", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }

                it.reject.setOnClickListener {
                    Toast.makeText(context, "reject button click", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }

            }
        }
    }

    fun showCustomAlert(view: View) {
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

    fun showDefaultAlert(view: View) {
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

    fun requestStoragePermission(view: View) {
        val permissionsList = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        getPermissions {
            permissions = permissionsList
            onPermissionGranted = {
                Toast.makeText(context, "Permission Given", Toast.LENGTH_LONG).show()
            }

            onPermissionDenied = {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun showBottomSheetDialog(view: View) {
        bottomSheetDialog {
            layoutId = R.layout.layout_dialog
            setCustomView = {it: View, dialog: DialogFragment ->

                it.title.text = getString(R.string.fragment_dialog_title)
                it.description.text = getString(R.string.fragment_dialog_description)

                it.accept.setOnClickListener {
                    Toast.makeText(context, "accept button click", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }

                it.reject.setOnClickListener {
                    Toast.makeText(context, "reject button click", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }

            }
        }
    }
}
