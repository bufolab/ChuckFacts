package com.bufolab.android.chuckfacts.presenter

interface BasicPresenter {

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

}
