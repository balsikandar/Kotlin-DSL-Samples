package com.balsikandar.kotlindslsamples.customAlert

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog

class CustomAlert(alertContext: Context, layoutId: Int?, setCustomView: (View, AlertDialog) -> Unit) {

    private constructor(builder: Builder) : this(
        builder.alertContext,
        builder.layoutId,
        builder.setCustomView
    )

    init {
        val customView = LayoutInflater.from(alertContext).inflate(layoutId!!, null)
        val dialog = AlertDialog.Builder(alertContext).setView(customView).setCancelable(true).show()
        setCustomView.invoke(customView, dialog)
    }

    companion object {
        inline fun Activity.customAlert(block: Builder.() -> Unit) {
            Builder(this).apply(block).build()
        }
    }

    class Builder(context: Context) {
        var alertContext: Context = context
        var layoutId: Int? = null
        lateinit var setCustomView: (View, AlertDialog) -> Unit
        fun build() = CustomAlert(this)
    }
}

data class AlertData(
    val title: String,
    val description: String
)