package com.bufolab.android.chuckfacts.presenter

import com.bufolab.android.chuckfacts.domain.model.ChuckFact

interface MainPresenter : BasicPresenter {

    fun onItemAccepted(itemId: ChuckFact)

    fun onItemRejected(itemId: ChuckFact)
}
