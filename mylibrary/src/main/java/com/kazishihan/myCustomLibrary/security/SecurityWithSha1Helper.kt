package com.kazishihan.myCustomLibrary.security

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import java.security.MessageDigest

internal object SecurityWithSha1Helper {
    private val ALLOWED_PACKAGE = "com.example.package"
    private val ALLOWED_SIGNATURE = "YOUR_SHA256_SIGNATURE"
    
    fun isValidCaller(context: Context): Boolean {
        val packageManager = context.packageManager
        val packageName = context.packageName
        
        // Check package name
        if (packageName != ALLOWED_PACKAGE) {
            return false
        }
        
        try {
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                // API 28+ (Android 9+)
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            } else {
                // API 24-27 (Android 7-8)
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            }
            
            val signatures = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.signingInfo?.apkContentsSigners?.map { it.toByteArray() }
            } else {
                packageInfo.signatures?.map { it.toByteArray() }
            }
            
            if (signatures != null) {
                for (signature in signatures) {
                    val sha256 = getSHA256(signature)
                    if (sha256 == ALLOWED_SIGNATURE) {
                        return true
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        return false
    }
    
    private fun getSHA256(byteArray: ByteArray): String {
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(byteArray)
        return digest.joinToString("") { "%02x".format(it) }
    }
}