package com.bufolab.android.chuckfacts

import com.bufolab.android.chuckfacts.common.SchedulersProvider
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import com.bufolab.android.chuckfacts.domain.usecase.AcceptFact
import com.bufolab.android.chuckfacts.domain.usecase.GetFacts
import com.bufolab.android.chuckfacts.presenter.MainPresenter
import com.bufolab.android.chuckfacts.presenter.MainPresenterImpl
import com.bufolab.android.chuckfacts.view.MainView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner



/**
 * Created by Bufolab on 14/08/2018.
 */
@RunWith(MockitoJUnitRunner::class)
class PresenterTest {

    @Mock
    lateinit var acceptUseCase: AcceptFact

    @Mock
    lateinit var getJokesUseCase: GetFacts

    @Mock
    lateinit var scheduler: SchedulersProvider

    @Mock
    lateinit var view: MainView

    private lateinit var presenter: MainPresenter
    private lateinit var testScheduler: Scheduler

    @Before
    fun prepare() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(acceptUseCase.execute()).thenReturn(Observable.just(1))

        testScheduler = Schedulers.trampoline()

        //when execute is called perform a fake use case
        Mockito.`when`(acceptUseCase.execute()).thenReturn(Observable.just(10))
        Mockito.`when`(getJokesUseCase.execute()).thenReturn(
                Observable.just(
                        ChuckFact("11", arrayListOf(),"","","")))

        presenter = MainPresenterImpl(getJokesUseCase,acceptUseCase)
        presenter.setView(view)
    }


    @Test
    fun mainPresenterItemAcceptedTest() {
        val chuckJoke = ChuckFact("", arrayListOf(), "", "", "")
        presenter.onItemAccepted(chuckJoke)

        //testScheduler.
        verify(view).setAmountSavedJokes(10) //verify we set in the view the correct value
    }


}