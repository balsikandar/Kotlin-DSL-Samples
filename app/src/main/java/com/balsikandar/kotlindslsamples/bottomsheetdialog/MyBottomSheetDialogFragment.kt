package com.balsikandar.kotlindslsamples.bottomsheetdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.balsikandar.kotlindslsamples.utils.TAG
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.Exception

class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {

    var layoutId: Int? = null

    lateinit var callback: (View) -> Unit

    companion object {

        val TAG = MyBottomSheetDialogFragment::TAG.toString()

        fun newInstance(layoutId: Int?): MyBottomSheetDialogFragment {

            val frag = MyBottomSheetDialogFragment()
            frag.setArguments(Bundle().apply {
                putInt("layoutId", layoutId!!)
            })
            return frag
        }
    }

    fun setCustomView(customView: (View) -> Unit) {
        callback = customView
    }

    override fun onStart() {
        super.onStart()
        val deviceWidth = context!!.resources.displayMetrics.widthPixels
        dialog?.window?.setLayout(deviceWidth, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutId = arguments?.getInt("layoutId")
        if (layoutId == null) throw Exception("layoutId can't be null")
        return inflater.inflate(layoutId!!, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callback.invoke(view)
    }

}