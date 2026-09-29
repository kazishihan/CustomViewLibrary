package com.kazishihan.customviewlibrary

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kazishihan.customviewlibrary.databinding.ActivityMainBinding
import com.kazishihan.myCustomLibrary.bridge.LibraryBridgeManager
import com.kazishihan.myCustomLibrary.bridge.LibraryEvent
import com.kazishihan.myCustomLibrary.landing.LandingLibraryActivity
import com.kazishihan.myCustomLibrary.minirail.viewConfig.MinirailViewBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBridgeHandler: AppBridgeHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appBridgeHandler = AppBridgeHandler(this)
        appBridgeHandler.register()


        binding.openUpsButton.setOnClickListener {
            startActivity(Intent(this, LandingLibraryActivity::class.java))
        }

        binding.sendToLibraryButton.setOnClickListener {
            val message = "Hello from App"
            LibraryBridgeManager.sendToLibrary(LibraryEvent(event = "APP_MESSAGE", data = message))
            Toast.makeText(this, "Sent to library: $message", Toast.LENGTH_SHORT).show()
        }

        setMoveItem()
        setCustomRail()
    }

    override fun onDestroy() {
        appBridgeHandler.unregister()
        super.onDestroy()
    }

    private fun setMoveItem() {
        binding.movieItem.setMovieName("The Ghost")
        binding.movieItem.setMovieReleaseDate("2023")
        binding.movieItem.setMovieVote(8.5f)
        binding.movieItem.setPoster("https://samplelib.com/lib/preview/png/sample-hut-400x300.png")
    }

    private fun setCustomRail() {
        val config = MinirailViewBuilder()
            .setTitle("Dashboard")
            .setItems(listOf("DPS", "FDR", "FDR2", "FDR3", "FDR4"))
            .setFragmentManager(supportFragmentManager)
            .build()

        binding.customRail.initialize(config)
    }

}