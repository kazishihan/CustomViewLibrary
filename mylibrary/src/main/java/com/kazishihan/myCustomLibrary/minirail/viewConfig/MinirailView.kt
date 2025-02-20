package com.kazishihan.myCustomLibrary.minirail.viewConfig

import android.content.Context
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import com.kazishihan.myCustomLibrary.databinding.ViewMiniRailBinding
import com.kazishihan.myCustomLibrary.minirail.fragmentConfig.MiniRailManager

 class MinirailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    
    private val binding: ViewMiniRailBinding =
        ViewMiniRailBinding.inflate(LayoutInflater.from(context), this, true)
    
    private var fragmentManager: FragmentManager? = null
    
    // Initialize the view with a configuration object
    fun initialize(config: MinirailViewConfig) {
        requireNotNull(config.title) { "Title must be set in the configuration" }
        requireNotNull(config.fragmentManager) { "FragmentManager must be set in the configuration" }
        
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw IllegalStateException("initialize() must be called on the main thread")
        }
        
        fragmentManager = config.fragmentManager
        
        fragmentManager?.let { fm ->
            val existingFragment = fm.findFragmentByTag(config.title)
            if (existingFragment == null) {
                val fragment = MiniRailManager.getInstance(
                    title = config.title!!, // Safe because we checked it's not null
                    items = config.items
                )
                
                val uniqueContainerId = View.generateViewId()
                binding.miniRainContainer.id = uniqueContainerId
                
                fm.beginTransaction()
                    .replace(uniqueContainerId, fragment, config.title)
                    .commitAllowingStateLoss()
            }
        }
    }
    
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        fragmentManager = null  // Prevent memory leaks
    }
}

