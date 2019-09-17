package com.pytel.notes.framework.ui.splash

sealed class SplashStates {
    object Begin : SplashStates()
    object Done : SplashStates()
}