package com.bufolab.android.chuckfacts.data

import com.bufolab.android.chuckfacts.data.response.ChuckFactResponse
import com.bufolab.android.chuckfacts.domain.model.ChuckFact

class ChuckFactFactory {

    companion object {
        fun create(response: ChuckFactResponse):ChuckFact {
            return ChuckFact(response.id, response.category, response.icon_url, response.url,
                    response.value)
        }
    }
}
