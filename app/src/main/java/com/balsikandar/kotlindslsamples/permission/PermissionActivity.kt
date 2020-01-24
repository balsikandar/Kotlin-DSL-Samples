package com.balsikandar.kotlindslsamples.permission

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.balsikandar.kotlindslsamples.OnRequestCallBack

class PermissionActivity : AppCompatActivity() {

    companion object {

        lateinit var listener: OnRequestCallBack
        private var resultCode: Int? = null

        fun intialize(listener: OnRequestCallBack) {
            Companion.listener = listener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissions = intent.extras?.getStringArray("permissions")
        resultCode = intent.extras?.getInt("result_code")

        if (permissions != null && ContextCompat.checkSelfPermission(this, permissions.get(0)) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                permissions,
                resultCode!!
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (resultCode == requestCode) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                listener.onPermissionGranted()
                finish()
            } else {
                listener.onPermissionDenied()
                finish()
            }
        }
    }
}
