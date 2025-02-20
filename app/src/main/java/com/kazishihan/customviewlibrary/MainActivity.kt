package com.kazishihan.customviewlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kazishihan.customviewlibrary.databinding.ActivityMainBinding
import com.kazishihan.myCustomLibrary.minirail.viewConfig.MinirailViewBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setMoveItem()
        setCustomRail()
        setCustomRail2()
    }
    
    private fun setMoveItem() {
        binding.movieItem.setMovieName("The Ghost")
        binding.movieItem.setMovieReleaseDate("2023")
        binding.movieItem.setMovieVote(8.5f)
        binding.movieItem.setPoster("https://samplelib.com/lib/preview/png/sample-hut-400x300.png")
    }
    
    private fun setCustomRail() {
        val config = MinirailViewBuilder()
            .setTitle("My Title")
            .setItems(listOf("Item 1", "Item 2"))
            .setFragmentManager(supportFragmentManager)
            .build()
        
        binding.customRail.initialize(config)
    }
    
    private fun setCustomRail2() {
        val config = MinirailViewBuilder()
            .setTitle("My Title")
            .setItems(listOf("Item 1", "Item 2"))
            .setFragmentManager(supportFragmentManager)
            .build()
        
        binding.customRail2.initialize(config)
    }
    
}