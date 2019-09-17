package com.pytel.notes.domain.common

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()

    data class Error(val error: ErrorResult) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=${error.throwable}"
        }
    }
}
open class ErrorResult(open val message: String? = null, open val throwable: Throwable? = null)

data class ApiErrorResult(
    val code: Int,
    val errorMessage: String? = null,
    val apiThrowable: Throwable? = null
) : ErrorResult(errorMessage, apiThrowable)

data class ErrorCodeResult(val code: ErrorCode,
                           override val message: String? = null,
                           override val throwable: Throwable? = null) : ErrorResult()


enum class ErrorCode(val value: Int) {

    NO_ERROR(0)

}

suspend fun <T : Any> callSafe(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
    return try {
        call()
    } catch (e: Throwable) {
        Result.Error(ErrorResult(errorMessage, e))
    }
}