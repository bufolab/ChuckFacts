package com.bufolab.android.chuckfacts.presenter

import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import com.bufolab.android.chuckfacts.domain.usecase.AcceptFact
import com.bufolab.android.chuckfacts.domain.usecase.GetFacts
import com.bufolab.android.chuckfacts.domain.usecase.GetSavedFacts
import com.bufolab.android.chuckfacts.view.MainView
import io.reactivex.disposables.Disposable

/**
 * Created by Bufolab on 12/08/2018.
 */

open class MainPresenterImpl(val view: MainView) : MainPresenter {

    private var composite: MutableList<Disposable> = mutableListOf()

    override fun onInitialize() {
        requestItems(5)
    }

    override fun onFinish() {
        composite.forEach { it.dispose() }
    }


    override fun onItemAccepted(fact: ChuckFact) {
        val subscribe = AcceptFact(fact).execute().
                flatMap { GetSavedFacts().execute() }
                .subscribe {
                    view.setAmountSavedJokes(it.size)
                    requestItems(1)
                }

        composite.add(subscribe)

    }

    override fun onItemRejected(itemId: ChuckFact) {
        //do nothing
    }


    private fun requestItems(quantity: Long = 1) {
        val subscribe = GetFacts(quantity).execute()
                .subscribe(
                        { loadItemsSuccess(it) },
                        { loadItemsFailue(it) }
                )

        composite.add(subscribe)
    }

    private fun loadItemsSuccess(jokes: ChuckFact) {
        view.hideLoading()
        view.showItems(arrayListOf(jokes))
    }

    private fun loadItemsFailue(it: Throwable) {
        view.hideLoading()
        it.printStackTrace()
    }
}