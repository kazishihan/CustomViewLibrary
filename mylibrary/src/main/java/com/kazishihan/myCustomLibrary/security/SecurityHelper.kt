package com.kazishihan.myCustomLibrary.security

import android.content.Context


internal object SecurityHelper {
    private val ALLOWED_PACKAGE = "com.kazishihan.customviewlibrary" // Replace with your app's package name
    
    fun isValidCaller(context: Context): Boolean {
        return context.packageName == ALLOWED_PACKAGE
    }
}