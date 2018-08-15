package com.bufolab.android.chuckfacts.view

import com.bufolab.android.chuckfacts.domain.model.ChuckFact

interface MainView {

    fun showItems(list: List<ChuckFact>)

    fun showLoading()

    fun hideLoading()

    fun setAmountSavedJokes(amount:Int)

    fun setEmptySavedJokes()

}
