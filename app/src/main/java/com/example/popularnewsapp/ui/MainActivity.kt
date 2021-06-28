package com.example.popularnewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.popularnewsapp.R
import com.example.popularnewsapp.util.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var loadingDialog: LoadingDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun showLoadingWidget() {
        loadingDialog =  LoadingDialog(this)
    }

    fun hideLoadingWidget() {
        if (loadingDialog != null )
            loadingDialog!!.dismiss()
    }

}