package com.bufolab.android.chuckfacts.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bufolab.android.chuckfacts.domain.usecase.AcceptFact
import com.bufolab.android.chuckfacts.domain.usecase.GetFacts
import com.bufolab.android.chuckfacts.domain.usecase.GetSavedFacts
import javax.inject.Inject


/**
 * Created by Bufolab on 15/08/2018.
 */
class MainViewModelFactory @Inject constructor(val getFacts: GetFacts,
                                               val acceptFact: AcceptFact,
                                               val getSavedFacts: GetSavedFacts) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                    getFactsUseCase = getFacts,
                    acceptItem = acceptFact,
                    getSavedFacts = getSavedFacts) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}