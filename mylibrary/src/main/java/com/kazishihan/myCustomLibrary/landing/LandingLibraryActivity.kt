package com.kazishihan.myCustomLibrary.landing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kazishihan.myCustomLibrary.R

open class LandingLibraryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_library)
    }
}