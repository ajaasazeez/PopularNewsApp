package com.example.popularnewsapp.util

import android.app.Dialog
import android.content.Context
import com.example.popularnewsapp.R

class LoadingDialog(context: Context) : Dialog(context) {
    init {
        setContentView(R.layout.layout_loading_dialog)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCanceledOnTouchOutside(false)
        show()
    }

}