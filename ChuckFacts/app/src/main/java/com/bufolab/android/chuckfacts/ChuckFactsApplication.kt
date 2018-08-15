package com.bufolab.android.chuckfacts

import android.app.Application
import com.bufolab.android.chuckfacts.dagger.AppComponent
import com.bufolab.android.chuckfacts.dagger.AppModule
import com.bufolab.android.chuckfacts.dagger.DaggerAppComponent

/**
 * Created by Bufolab on 13/08/2018.
 */
class ChuckFactsApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        component =   DaggerAppComponent.builder()
                .appModule( AppModule(this))
                .build()

        super.onCreate()
    }
}