package com.kazishihan.myCustomLibrary.minirail.fragmentConfig

import androidx.fragment.app.Fragment

internal object MiniRailManager {
    fun getInstance(title: String, items: List<String>): Fragment {
        return MiniRailFragment.builder()
            .setTitle(title)
            .setItems(items)
            .build()
    }
}