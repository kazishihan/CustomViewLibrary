package com.kazishihan.myCustomLibrary

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import coil3.load
import com.kazishihan.myCustomLibrary.databinding.ItemMovieBinding

class MovieItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    devStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, devStyleAttr) {
    
    private lateinit var binding: ItemMovieBinding
    var onItemClickListener: (() -> Unit)? = null
    
    init {
        View.inflate(context, R.layout.item_movie, this)
    }
    
    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ItemMovieBinding.bind(this)
        binding.root.setOnClickListener {
            onItemClickListener?.invoke()
        }
    }
    
    fun setMovieName(name: String){
        binding.tvName.text = name
    }
    
    fun setMovieReleaseDate(release: String){
        binding.tvRelease.text = release
    }
    
    fun setMovieVote(vote: Float){
        binding.tvVote.text = vote.toString()
    }
    fun setPoster(url: String){
        binding.imgPoster.load(url)
    }
}
