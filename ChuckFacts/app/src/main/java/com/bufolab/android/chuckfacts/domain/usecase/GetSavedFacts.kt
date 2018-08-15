package com.bufolab.android.chuckfacts.domain.usecase

import com.bufolab.android.chuckfacts.data.Repository
import com.bufolab.android.chuckfacts.domain.UseCase
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Bufolab on 13/08/2018.
 */
class GetSavedFacts @Inject constructor(val repository: Repository): UseCase<List<ChuckFact>> {

    override fun execute(): Observable<List<ChuckFact>> {
        return repository.loadSavedChuckJoke()
    }
}