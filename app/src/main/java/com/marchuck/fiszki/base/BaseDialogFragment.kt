package com.marchuck.fiszki.base

import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

abstract class BaseDialogFragment<T : FragmentActivity> : DialogFragment() {

    @Suppress("UNCHECKED_CAST")
    fun getDirectParent(): T {
        return activity as T
    }
}