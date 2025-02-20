package com.kazishihan.customviewlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kazishihan.customviewlibrary.databinding.ActivityMainBinding
import com.kazishihan.myCustomLibrary.minirail.MiniRailManager


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.movieItem.setMovieName("The Ghost")
        binding.movieItem.setMovieReleaseDate("2023")
        binding.movieItem.setMovieVote(8.5f)
        binding.movieItem.setPoster("https://samplelib.com/lib/preview/png/sample-hut-400x300.png")
        
        loadMiniRailFragment()
    }
    
    private fun loadMiniRailFragment() {
        val fragment = MiniRailManager.getInstance(
            title = "MiniRail Secure",
            items = listOf("Item A", "Item B", "Item C")
        )
        
        supportFragmentManager.beginTransaction()
            .replace(binding.miniRainContainer.id, fragment)
            .commit()
    }
}