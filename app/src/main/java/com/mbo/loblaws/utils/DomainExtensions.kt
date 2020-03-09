package com.mbo.loblaws.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.mbo.loblaws.R
import com.mbo.loblaws.domain.Error
import com.mbo.loblaws.domain.NetworkError
import com.mbo.loblaws.domain.UnknownError

fun Error.display(view: View): Snackbar {
    val message = when (this) {
        is NetworkError -> R.string.error_network
        is UnknownError -> R.string.error_unkown
    }
    return Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply { show() }
}
