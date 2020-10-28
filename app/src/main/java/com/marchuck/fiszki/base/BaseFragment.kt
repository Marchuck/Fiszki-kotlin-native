package com.marchuck.fiszki.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

abstract class BaseFragment<T : FragmentActivity> : Fragment() {


    @Suppress("UNCHECKED_CAST")
    fun getDirectParent(): T {
        return activity as T
    }
}