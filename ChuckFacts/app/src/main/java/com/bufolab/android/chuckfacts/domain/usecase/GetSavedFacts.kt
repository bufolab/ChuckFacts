package com.bufolab.android.chuckfacts.domain.usecase

import com.bufolab.android.chuckfacts.data.LocalRepository
import com.bufolab.android.chuckfacts.data.Repository
import com.bufolab.android.chuckfacts.domain.UseCase
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Bufolab on 13/08/2018.
 */
class GetSavedFacts : UseCase<List<ChuckFact>> {

    var repository: Repository = LocalRepository.getInstance()

    override fun execute(): Observable<List<ChuckFact>> {
       return repository.loadSavedChuckJoke().
                subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
    }
}