package com.bufolab.android.chuckfacts.data

import android.content.Context
import android.content.SharedPreferences
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import com.google.gson.Gson
import io.reactivex.Observable




/**
 * For this example we use onlt SharedPresferences to save our items
 *
 * Created by Bufolab on 13/08/2018.
 */
class LocalRepository(private val context: Context) : Repository {

    private val SAVED_FACTS = "SAVED_FACTS"
    private val KEY_NAME = "PREF_NAME"


    override fun saveChuckJoke(fact: ChuckFact) : Observable<Unit> {
        val settings: SharedPreferences = context.getSharedPreferences(KEY_NAME, Context.MODE_PRIVATE)

        val toJson = Gson().toJson(fact)
        val stringSet = settings.getStringSet(SAVED_FACTS, mutableSetOf())
        stringSet.add(toJson)
        settings.edit().putStringSet(SAVED_FACTS, stringSet).apply()

        return Observable.just(Unit)
    }

    override fun loadSavedChuckJoke(): Observable<List<ChuckFact>> {
        val settings: SharedPreferences = context.getSharedPreferences(KEY_NAME, Context.MODE_PRIVATE)

        val stringSet = settings.getStringSet(SAVED_FACTS, emptySet())

        val listJokes = mutableListOf<ChuckFact>()
        stringSet.forEach { listJokes.add(Gson().fromJson(it, ChuckFact::class.java)) }

        return Observable.just(listJokes)
    }
}