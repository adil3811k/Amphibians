package com.example.amphibians.ui.theme

import android.app.Application
import com.example.amphibians.data.Appcotainer
import com.example.amphibians.data.DefaultAppContainer

public class AmphibianApplication :Application(){
    lateinit var container: Appcotainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}