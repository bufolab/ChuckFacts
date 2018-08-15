package com.bufolab.android.chuckfacts.domain.usecase

import com.bufolab.android.chuckfacts.common.SchedulersProvider
import com.bufolab.android.chuckfacts.data.ChuckFactFactory
import com.bufolab.android.chuckfacts.data.ChuckFactService
import com.bufolab.android.chuckfacts.domain.UseCase
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Bufolab on 12/08/2018.
 */
class GetFacts @Inject constructor(val service:ChuckFactService,
                                   val schedulers: SchedulersProvider): UseCase<ChuckFact> {

    val DEFAULT_NUMBER_JOKES = 1

    var numberJokes:Int = DEFAULT_NUMBER_JOKES //default number to request

    override fun execute(): Observable<ChuckFact> {
        return service.getRandomJoke()
                .repeat(numberJokes.toLong())
                .map { it -> ChuckFactFactory.create(it) }
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.ui)
                .doAfterTerminate { numberJokes = DEFAULT_NUMBER_JOKES }
        //reset number of jokes. We could have Concurrency problem

    }
}