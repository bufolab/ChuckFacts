package com.bufolab.android.chuckfacts.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 *
 * Module to provide the App context. For our case AppContext is enough.
 * No need for activity or other contexts.
 *
 * Created by Bufolab on 14/08/2018.
 */
@Module
class AppModule(private var application: Application) {

    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideAplication(): Application = application

}