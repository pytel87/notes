package com.pytel.notes.ui.base

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Created by Vladimir Skouy on 2019-09-22.
 */

open class BaseFragment:Fragment(){
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(context!!)
    }

    fun logEvent(event:String){
        firebaseAnalytics.logEvent(event, null)
    }

    fun logScreen(screen:String){
        activity?.let {
            firebaseAnalytics.setCurrentScreen(it, screen, null)
        }
    }

    fun logEvent(event:String, bundle:Bundle){
        firebaseAnalytics.logEvent(event, bundle)
    }

    fun logError(screen:String ,error:String?){
        val bundle = bundleOf(
            Pair("screen", screen),
            Pair("message", error)
        )
        firebaseAnalytics.logEvent("Error", bundle)
    }
}