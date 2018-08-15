package com.bufolab.android.chuckfacts.data

import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import io.reactivex.Observable

/**
 * Created by Bufolab on 13/08/2018.
 */
interface Repository {

    fun saveChuckJoke(fact: ChuckFact)

    fun loadSavedChuckJoke() : Observable<List<ChuckFact>>
}