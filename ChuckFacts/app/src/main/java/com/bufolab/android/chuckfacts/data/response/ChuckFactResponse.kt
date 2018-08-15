package com.bufolab.android.chuckfacts.data.response

data class ChuckFactResponse(
        val id: String,
        val category: List<String>?,
        val icon_url: String,
        val url: String,
        val value: String
)