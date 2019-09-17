package com.pytel.notes.framework.utils

import timber.log.Timber


fun <T : Any> T.logDebug(message: Any?, error: Throwable? = null) {
    Timber.tag(this::class.java.simpleName)
    Timber.d(error, message?.toString() ?: "null")
}

inline fun <T : Any> T.logDebug(message: () -> Any?) {
    Timber.tag(this::class.java.simpleName)
    Timber.d(message()?.toString() ?: "null")
}


fun <T : Any> T.logWarn(message: Any?, error: Throwable? = null) {
    Timber.tag(this::class.java.simpleName)
    Timber.w(error, message?.toString() ?: "null")
}


inline fun <T : Any> T.logWarn(message: () -> Any?) {
    Timber.tag(this::class.java.simpleName)
    Timber.w(message()?.toString() ?: "null")
}


fun <T : Any> T.logError(message: Any?, error: Throwable? = null) {
    Timber.tag(this::class.java.simpleName)
    Timber.e(error, message?.toString() ?: "null")
}


inline fun <T : Any> T.logError(message: () -> Any?) {
    Timber.tag(this::class.java.simpleName)
    Timber.e(message()?.toString() ?: "null")
}


fun <T : Any> T.logInfo(message: Any?, error: Throwable? = null) {
    Timber.tag(this::class.java.simpleName)
    Timber.i(error, message?.toString() ?: "null")
}


inline fun <T : Any> T.logInfo(message: () -> Any?) {
    Timber.tag(this::class.java.simpleName)
    Timber.i(message()?.toString() ?: "null")
}


fun <T : Any> T.logVerbose(message: Any?, error: Throwable? = null) {
    Timber.tag(this::class.java.simpleName)
    Timber.v(error, message?.toString() ?: "null")
}


inline fun <T : Any> T.logVerbose(message: () -> Any?) {
    Timber.tag(this::class.java.simpleName)
    Timber.v(message()?.toString() ?: "null")
}