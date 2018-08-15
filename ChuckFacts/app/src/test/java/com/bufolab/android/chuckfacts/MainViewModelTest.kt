package com.bufolab.android.chuckfacts

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import com.bufolab.android.chuckfacts.domain.usecase.AcceptFact
import com.bufolab.android.chuckfacts.domain.usecase.GetFacts
import com.bufolab.android.chuckfacts.domain.usecase.GetSavedFacts
import com.bufolab.android.chuckfacts.viewmodel.MainViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by Bufolab on 14/08/2018.
 */
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    lateinit var acceptUseCase: AcceptFact

    @Mock
    lateinit var getJokesUseCase: GetFacts

    @Mock
    lateinit var getSavedFacts: GetSavedFacts


    lateinit var viewModel: MainViewModel

    private lateinit var testScheduler: Scheduler

    //some junk data
    val chuckFact = ChuckFact("11", arrayListOf(), "", "", "")
    val savedFacts = arrayListOf(chuckFact,chuckFact,chuckFact)


        /*
        LiveData.setValue() run on the main thread, and throw exception if not.
        So either we use postValue. or we use the Rule to mock the mainThread.

        Exception thrown:
        	java.lang.RuntimeException: Method getMainLooper in android.os.Looper not mocked. See http://g.co/androidstudio/not-mocked for details.
		at android.os.Looper.getMainLooper(Looper.java)
		at android.arch.core.executor.DefaultTaskExecutor.isMainThread(DefaultTaskExecutor.java:58)
		at android.arch.core.executor.ArchTaskExecutor.isMainThread(ArchTaskExecutor.java:116)

        */
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun prepare() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(acceptUseCase.execute()).thenReturn(Observable.just(Unit))

        //when execute is called perform a fake use case
        Mockito.`when`(acceptUseCase.execute()).thenReturn(Observable.just(Unit))
        Mockito.`when`(getSavedFacts.execute()).thenReturn(Observable.just(savedFacts))
        Mockito.`when`(getJokesUseCase.execute()).thenReturn( Observable.just(chuckFact))

        viewModel = MainViewModel(getJokesUseCase,acceptUseCase,getSavedFacts)
    }


    @Test
    fun mainViewModelItemAcceptedTest() {

        viewModel.onItemAccepted(chuckFact)

        assert(viewModel.savedFactsData.value?.savedFacts==savedFacts.size)
    }


}