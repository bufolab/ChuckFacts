package com.bufolab.android.chuckfacts.presenter

import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import com.bufolab.android.chuckfacts.domain.usecase.AcceptFact
import com.bufolab.android.chuckfacts.domain.usecase.GetFacts
import com.bufolab.android.chuckfacts.view.MainView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Bufolab on 12/08/2018.
 */

open class  MainPresenterImpl  @Inject constructor(var getJokesUseCase: GetFacts,
                                                   var acceptItem: AcceptFact): MainPresenter {

    lateinit var mainview: MainView


    private var composite: MutableList<Disposable> = mutableListOf()

    override fun onInitialize() {
        requestItems(5)
    }

    override fun onFinish() {
        composite.forEach { it.dispose() }
    }

    override fun setView(view: MainView) {
        mainview = view
    }

    override fun onItemAccepted(fact: ChuckFact) {
        acceptItem.setChuckFact(fact)

        val subscribe = acceptItem.execute()
                .subscribe {
                    mainview.setAmountSavedJokes(it.toInt())
                    requestItems(1)
                }

        composite.add(subscribe)

    }

    override fun onItemRejected(itemId: ChuckFact) {
        //do nothing
    }

    private fun requestItems(numberJokes: Int = 1) {
        getJokesUseCase.numberJokes = numberJokes
        val subscribe = getJokesUseCase.execute()
                .subscribe(
                        { loadItemsSuccess(it) },
                        { loadItemsFailue(it) }
                )

        composite.add(subscribe)
    }

    private fun loadItemsSuccess(jokes: ChuckFact) {
        mainview.hideLoading()
        mainview.showItems(arrayListOf(jokes))
    }

    private fun loadItemsFailue(it: Throwable) {
        mainview.hideLoading()
        it.printStackTrace()
    }
}
