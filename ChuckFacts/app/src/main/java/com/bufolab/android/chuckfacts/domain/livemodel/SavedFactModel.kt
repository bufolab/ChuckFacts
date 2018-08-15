package com.bufolab.android.chuckfacts.domain.livemodel

/**
 * Created by Bufolab on 15/08/2018.
 */
class SavedFactModel(val savedFacts: Int,
                     val success:Boolean,
                     val error: Throwable? //if there was any error we also store it
) {

    companion object {
        fun error(error: Throwable) =
                SavedFactModel(
                        savedFacts = 0,
                        success = false,
                        error = error)

        fun success(savedFacts: Int) =
                SavedFactModel(
                        savedFacts = savedFacts,
                        success = true,
                        error = null)
    }

}