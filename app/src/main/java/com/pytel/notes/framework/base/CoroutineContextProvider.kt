package com.pytel.notes.framework.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vladimir Skouy on 2019-09-19.
 */

open class  CoroutineContextProvider {
    open val Main:  CoroutineContext by lazy { Dispatchers.Main }
    open val IO:  CoroutineContext by lazy { Dispatchers.IO }
}

@ExperimentalCoroutinesApi
class TestContextProvider:CoroutineContextProvider(){
    override val Main: CoroutineContext = Dispatchers.Unconfined
    override val IO: CoroutineContext = Dispatchers.Unconfined
}
