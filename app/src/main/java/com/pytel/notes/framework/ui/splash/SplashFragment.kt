package com.pytel.notes.framework.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pytel.notes.R
import org.koin.androidx.viewmodel.ext.viewModel


class SplashFragment : Fragment() {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        registerEvents()
        return view
    }

    private fun registerEvents() {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                is SplashStates.Begin -> viewModel.initialize()
                is SplashStates.Done -> moveToNextScreen()
            }
        })


    }

    private fun moveToNextScreen() {
        findNavController().navigate(R.id.action_splashFragment_to_notesFragment)
    }

}
