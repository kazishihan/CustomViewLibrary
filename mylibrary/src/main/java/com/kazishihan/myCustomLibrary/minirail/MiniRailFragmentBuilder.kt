package com.kazishihan.myCustomLibrary.minirail

import android.os.Bundle

internal class MiniRailFragmentBuilder {
    private var title: String = "Default Title"
    private var items: List<String> = emptyList()
    
    fun setTitle(title: String) = apply { this.title = title }
    fun setItems(items: List<String>) = apply { this.items = items }
    
    fun build(): MiniRailFragment {
        return MiniRailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putStringArrayList(ARG_ITEMS, ArrayList(items))
            }
        }
    }
    
    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_ITEMS = "items"
    }
}