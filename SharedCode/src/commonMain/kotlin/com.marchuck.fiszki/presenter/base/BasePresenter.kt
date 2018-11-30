package org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.base

abstract class BasePresenter<T> {

    var view: T? = null

    fun attachView(view: T){
        this.view=  view
    }



}