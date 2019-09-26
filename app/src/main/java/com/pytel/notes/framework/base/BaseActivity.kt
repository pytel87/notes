package com.pytel.notes.framework.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.analytics.FirebaseAnalytics


/**
 * Created by Vladimir Skouy on 2019-09-22.
 */
open class BaseActivity: AppCompatActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    fun logEvent(event:String){
        firebaseAnalytics.logEvent(event, null)
    }
}