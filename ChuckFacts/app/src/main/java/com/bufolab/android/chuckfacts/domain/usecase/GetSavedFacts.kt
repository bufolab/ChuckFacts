package com.bufolab.android.chuckfacts.domain.usecase

import com.bufolab.android.chuckfacts.domain.UseCase
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import io.reactivex.Observable

/**
 * Created by Bufolab on 13/08/2018.
 */
class GetSavedFacts : UseCase<List<ChuckFact>> {


    override fun execute(): Observable<List<ChuckFact>> {
       return Observable.just(arrayListOf(ChuckFact("",null,"","","")))
    }
}