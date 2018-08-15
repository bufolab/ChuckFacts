package com.bufolab.android.chuckfacts.domain.model

/**
 * Created by Bufolab on 12/08/2018.
 */
data class ChuckFact (val id: String,
                      val category: List<String>?,
                      val icon_url: String?,
                      val url: String?,
                      val value: String)