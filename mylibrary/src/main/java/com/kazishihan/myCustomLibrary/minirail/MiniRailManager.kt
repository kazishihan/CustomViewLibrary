package com.kazishihan.myCustomLibrary.minirail

import androidx.fragment.app.Fragment

object MiniRailManager {
    fun getInstance(title: String, items: List<String>): Fragment {
        return MiniRailFragment.builder()
            .setTitle(title)
            .setItems(items)
            .build()
    }
}