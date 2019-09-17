package com.pytel.notes.framework.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pytel.notes.framework.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {

    val viewState = MutableLiveData<SplashStates>()

    init {
        viewState.value = SplashStates.Begin
    }

    fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2500)
            viewState.postValue(SplashStates.Done)
        }
    }
}