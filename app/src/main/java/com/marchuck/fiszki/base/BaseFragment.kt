package com.marchuck.fiszki.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

abstract class BaseFragment<T : FragmentActivity> : Fragment() {


    @Suppress("UNCHECKED_CAST")
    fun getDirectParent(): T {
        return activity as T
    }
}