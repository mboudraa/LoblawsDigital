package com.mbo.loblaws.domain

import java.io.IOException
import java.net.ConnectException

sealed class Error

object NetworkError : Error()
object UnknownError : Error()

fun Exception.toError(): Error {
    return when (this) {
        is IOException, is ConnectException -> NetworkError
        else -> UnknownError
    }
}