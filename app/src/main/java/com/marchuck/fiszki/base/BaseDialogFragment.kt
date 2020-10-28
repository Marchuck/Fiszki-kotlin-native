package com.marchuck.fiszki.base

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity


abstract class BaseDialogFragment<T : FragmentActivity> : DialogFragment() {

    @Suppress("UNCHECKED_CAST")
    fun getDirectParent(): T {
        return activity as T
    }
}