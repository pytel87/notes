package com.pytel.notes.framework.utils

import android.content.Context
import android.content.res.Configuration

fun Context.isLandscape() = Configuration.ORIENTATION_LANDSCAPE == this.resources.configuration.orientation