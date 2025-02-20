package com.kazishihan.myCustomLibrary.minirail.viewConfig

import androidx.fragment.app.FragmentManager

data class MinirailViewConfig(
    val title: String?,
    val items: List<String>,
    val fragmentManager: FragmentManager?
)