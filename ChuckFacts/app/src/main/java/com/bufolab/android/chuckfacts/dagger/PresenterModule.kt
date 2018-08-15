package com.bufolab.android.chuckfacts.dagger

import com.bufolab.android.chuckfacts.domain.usecase.AcceptFact
import com.bufolab.android.chuckfacts.domain.usecase.GetFacts
import com.bufolab.android.chuckfacts.presenter.MainPresenter
import com.bufolab.android.chuckfacts.presenter.MainPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Bufolab on 14/08/2018.
 */
@Module(includes = [DataModule::class,SchedulersModule::class])
class PresenterModule {

    @Provides
    fun provideMainPresenter(useCase: GetFacts, acceptUsecase:AcceptFact):
            MainPresenter = MainPresenterImpl(useCase,acceptUsecase)

}