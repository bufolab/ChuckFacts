package com.bufolab.android.chuckfacts.dagger

import com.bufolab.android.chuckfacts.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Bufolab on 14/08/2018.
 */
@Singleton
@Component(modules = [DataModule::class, AppModule::class, SchedulersModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}