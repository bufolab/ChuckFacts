package com.bufolab.android.chuckfacts.livemodel

import com.bufolab.android.chuckfacts.domain.model.ChuckFact

/**
 * Created by Bufolab on 15/08/2018.
 */
class ChuckFactModel(val fact: ChuckFact?,
                     val success:Boolean,
                     val error: Throwable? //if there was any error we also store it
) {

    companion object {
        fun error(error: Throwable) =
                ChuckFactModel(null,
                        success = false,
                        error = error)

        fun success(fact: ChuckFact) =
                ChuckFactModel(fact,
                        success = true,
                        error = null)
    }

}