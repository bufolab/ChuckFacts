package com.bufolab.android.chuckfacts.domain

import io.reactivex.Observable


/**
 * Created by Bufolab on 12/08/2018.
 */
interface UseCase<T> {

    fun execute(): Observable<T>
}