package com.bufolab.android.chuckfacts.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bufolab.android.chuckfacts.domain.livemodel.ChuckFactModel
import com.bufolab.android.chuckfacts.domain.livemodel.SavedFactModel
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import com.bufolab.android.chuckfacts.domain.usecase.AcceptFact
import com.bufolab.android.chuckfacts.domain.usecase.GetFacts
import com.bufolab.android.chuckfacts.domain.usecase.GetSavedFacts
import io.reactivex.disposables.CompositeDisposable


/**
 *
 * This is the ViewModel component on the MVVM
 *
 * This class is created by the MainViewModelFactory
 * using ViewModelProviders.of(Lifecycle, mainViewModelFactory)
 *
 * So dependencies are not injected
 *
 * Created by Bufolab on 15/08/2018.
 */
class MainViewModel(private var getFactsUseCase: GetFacts,
                    private var acceptItem: AcceptFact,
                    private var getSavedFacts: GetSavedFacts) : ViewModel() {

    private var disposables: CompositeDisposable = CompositeDisposable()

    var factData: MutableLiveData<ChuckFactModel> = MutableLiveData()
    var savedFactsData: MutableLiveData<SavedFactModel> = MutableLiveData()

    fun onInitialize() {
        requestItems(5)
    }

    override fun onCleared() {
        disposables.clear()
    }


    fun onItemAccepted(fact: ChuckFact) {
        acceptItem.setChuckFact(fact)

        disposables.add(acceptItem.execute().flatMap { getSavedFacts.execute() }.subscribe(
                {
                    //subscriptions we assume always on mainThread so we can use setValue
                    savedFactsData.value = SavedFactModel.success(it.size)
                },
                {
                    savedFactsData.value = SavedFactModel.error(it)
                }))

        getFactsUseCase.numberJokes = 1
        disposables.add(getFactsUseCase.execute().subscribe(
                {
                    factData.value = ChuckFactModel.success(it)
                },
                {
                    factData.value = ChuckFactModel.error(it)
                }
        ))

    }

    fun onItemRejected(itemId: ChuckFact) {
        //do nothing
    }


    private fun requestItems(numberJokes: Int = 1) {
        getFactsUseCase.numberJokes = numberJokes
        disposables.add(getFactsUseCase.execute().subscribe(
                {
                    factData.value = ChuckFactModel.success(it)
                },
                {
                    factData.value = ChuckFactModel.error(it)
                }
        ))

    }

}