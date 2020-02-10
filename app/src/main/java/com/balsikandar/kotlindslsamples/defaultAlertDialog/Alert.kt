package com.balsikandar.kotlindslsamples.defaultAlertDialog

import android.content.Context
import androidx.appcompat.app.AlertDialog

class Alert(
    val title: String?,
    val description: String?,
    val alertContext: Context,
    val positiveButton: () -> Unit,
    val negativeButton: () -> Unit
) {

    constructor(builder: Builder) : this(
        builder.title,
        builder.description,
        builder.alertContext,
        builder.positiveButton,
        builder.negativeButton
    )

    init {

        AlertDialog.Builder(alertContext)
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton("Yes") { dialog, which -> positiveButton.invoke() }
            .setNegativeButton("No") { dialog, which -> negativeButton.invoke() }.show()
    }

    companion object {
        inline fun alert(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var title: String? = null
        var description: String? = null
        lateinit var alertContext: Context
        lateinit var positiveButton: () -> Unit
        lateinit var negativeButton: () -> Unit
        fun build() = Alert(this)
    }
}