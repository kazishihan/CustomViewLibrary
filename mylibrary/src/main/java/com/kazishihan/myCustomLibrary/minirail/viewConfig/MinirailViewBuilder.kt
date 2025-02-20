package com.kazishihan.myCustomLibrary.minirail.viewConfig

import androidx.fragment.app.FragmentManager

class MinirailViewBuilder {
    
    private var title: String? = null
    private var items: List<String> = emptyList()
    private var fragmentManager: FragmentManager? = null
    
    fun setTitle(title: String): MinirailViewBuilder {
        this.title = title
        return this
    }
    
    fun setItems(items: List<String>): MinirailViewBuilder {
        this.items = items
        return this
    }
    
    fun setFragmentManager(fragmentManager: FragmentManager): MinirailViewBuilder {
        this.fragmentManager = fragmentManager
        return this
    }
    
    fun build(): MinirailViewConfig {
        requireNotNull(title) { "Title must be set before calling build()" }
        requireNotNull(fragmentManager) { "FragmentManager must be set before calling build()" }
        
        return MinirailViewConfig(
            title = title,
            items = items,
            fragmentManager = fragmentManager
        )
    }
}