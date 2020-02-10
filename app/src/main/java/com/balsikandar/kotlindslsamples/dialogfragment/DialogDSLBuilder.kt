package com.balsikandar.kotlindslsamples.dialogfragment

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

data class DialogDSLBuilder(
    val alertContext: Context,
    val layoutId: Int?,
    val setCustomView: (View) -> Unit
) {

    constructor(builder: Builder) : this(
        builder.alertContext,
        builder.layoutId,
        builder.setCustomView
    )


    init {
        val frag = MyDialogFragment.newInstance(layoutId)
        frag.setCustomView(setCustomView)
        frag.show(
            (alertContext as FragmentActivity).supportFragmentManager,
            MyDialogFragment.TAG
        )
    }

    companion object {
        inline fun Activity.dialog(block: Builder.() -> Unit) {
            Builder(this).apply(block).build()
        }

        inline fun Fragment.dialog(block: Builder.() -> Unit) {
            Builder(this.requireActivity())
                .apply(block).build()
        }
    }

    class Builder(context: Context) {
        var alertContext: Context = context
        var layoutId: Int? = null
        lateinit var setCustomView: (View) -> Unit
        fun build() = DialogDSLBuilder(this)

    }
}