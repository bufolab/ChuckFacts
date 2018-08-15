package com.bufolab.android.chuckfacts.domain.usecase

import com.bufolab.android.chuckfacts.data.ChuckFactFactory
import com.bufolab.android.chuckfacts.data.ChuckFactService
import com.bufolab.android.chuckfacts.domain.UseCase
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Bufolab on 12/08/2018.
 */
class GetFacts(val quantity: Long = 1) : UseCase<ChuckFact> {
    override fun execute(): Observable<ChuckFact> {
        val api = ChuckFactService.create()
        return api.getRandomJoke()
                .repeat(quantity)
                .map { it -> ChuckFactFactory.create(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}