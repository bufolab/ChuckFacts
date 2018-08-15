package com.bufolab.android.chuckfacts.presenter

import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import com.bufolab.android.chuckfacts.view.MainView

interface MainPresenter : BasicPresenter<MainView>  {

    fun onItemAccepted(itemId: ChuckFact)

    fun onItemRejected(itemId: ChuckFact)
}
