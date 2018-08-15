package com.bufolab.android.chuckfacts.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

/**
 * Created by Bufolab on 14/08/2018.
 */
@Module
class SchedulersModule {

    @Provides
    @Named("io")
    fun scheduler(): Scheduler = Schedulers.io()

    @Provides
    @Named("ui")
    fun scheduler2(): Scheduler = AndroidSchedulers.mainThread()
}