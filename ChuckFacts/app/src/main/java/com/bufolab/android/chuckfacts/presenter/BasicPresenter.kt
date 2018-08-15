package com.bufolab.android.chuckfacts.presenter

interface BasicPresenter<T> {

    /**
     * Note: onInitialize called when the presenter has to perform any operation at the beginning
     * usually called on onStart, onResume or onCreate from Activity.
     */
    fun onInitialize()

    /**
     * Note: onInitialize called when the presenter has to perform any operation at the end
     * usually called on onStop, onPause, on Destroy from Activity.
     */
    fun onFinish()

    /**
     *
     * Using pattern DI force us to inject the Presenter thus the view need to be set
     * The Generic T view makes the setter of the view more clear
     */
    fun setView(view: T)

}
