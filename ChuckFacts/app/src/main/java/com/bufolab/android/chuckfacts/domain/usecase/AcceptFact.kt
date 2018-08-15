package com.bufolab.android.chuckfacts.domain.usecase

import com.bufolab.android.chuckfacts.common.SchedulersProvider
import com.bufolab.android.chuckfacts.data.Repository
import com.bufolab.android.chuckfacts.domain.UseCase
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Bufolab on 12/08/2018.
 */
class AcceptFact @Inject constructor(val repository: Repository, val schedulers: SchedulersProvider): UseCase<Unit> {

    var fact: ChuckFact? = null

    fun setChuckFact(fact:ChuckFact){ this.fact = fact }

    override fun execute(): Observable<Unit> {
        val arg =  checkNotNull(fact)

        return repository.saveChuckJoke(arg)
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.ui)
    }
}