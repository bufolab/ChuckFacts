package com.bufolab.android.chuckfacts

import com.bufolab.android.chuckfacts.common.SchedulersProvider
import com.bufolab.android.chuckfacts.data.ChuckFactService
import com.bufolab.android.chuckfacts.data.Repository
import com.bufolab.android.chuckfacts.data.response.ChuckFactResponse
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import com.bufolab.android.chuckfacts.domain.usecase.AcceptFact
import com.bufolab.android.chuckfacts.domain.usecase.GetFacts
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner




/**
 *
 * For this example we dont have too many usecases. So we keep them in all in this file
 *
 * The interesting fact is that we can mock easily the service and the repository
 *
 * Created by Bufolab on 14/08/2018.
 */
@RunWith(MockitoJUnitRunner::class)
class UseCaseTest {

    @Mock
    lateinit var service: ChuckFactService

    //no need for mock of mockito
    private lateinit var schedulerProvider: SchedulersProvider

    private lateinit var scheduler: Scheduler

    private lateinit var getJokesuseCase: GetFacts

    @Mock
    lateinit var repository: Repository
    private lateinit var acceptUseCase: AcceptFact

    val mockedResponseData = arrayListOf(
            ChuckFactResponse("1", arrayListOf(), "", "", "junk1"),
            ChuckFactResponse("2", arrayListOf(), "", "", "junk2"))

    @Before
    fun prepare() {

        MockitoAnnotations.initMocks(this)

        //prepare the schedulers for testing
        scheduler = Schedulers.trampoline()
        schedulerProvider = SchedulersProvider(scheduler,scheduler)


        `when`(service.getRandomJoke()).thenReturn(Observable.fromIterable(mockedResponseData))
        getJokesuseCase = GetFacts(service,schedulerProvider)

        `when`(repository.loadSavedChuckJoke()).thenReturn(
                Observable.just(arrayListOf(ChuckFact("1",
                        arrayListOf(),"","",""))))


        acceptUseCase = AcceptFact(repository,schedulerProvider)
    }



    @Test
    fun getJokesUseCase() {

        //No need for TestObservable, just call .test() transforms the Observer into a testable one
        val test = getJokesuseCase.execute().test()

        test.awaitTerminalEvent() //actually no need, Scheduler is trampoline, so it uses current thread

        test.assertValueCount(2)
        test.assertComplete()
        test.assertNoErrors()

        //check indeed usecase interacted with getRandomJoke only once
        verify(service).getRandomJoke()
        verifyNoMoreInteractions(service)

        //check if after the execution the number of jokes to request is set to default
        assertEquals(getJokesuseCase.numberJokes,getJokesuseCase.DEFAULT_NUMBER_JOKES)
    }

    @Test
    fun getJokesUseCaseMoreThanOne() {

        //No need for TestObservable, just call .test() transforms the Observer into a testable one
        getJokesuseCase.numberJokes = 2
        val test = getJokesuseCase.execute().test()

        test.awaitTerminalEvent() //actually no need, Scheduler is trampoline, so it uses current thread

        test.assertValueCount(4)
        test.assertComplete()
        test.assertNoErrors()

        //check indeed usecase interacted with getRandomJoke only once
        verify(service).getRandomJoke()
        verifyNoMoreInteractions(service)

        //check if after the execution the number of jokes to request is set to default
        assertEquals(getJokesuseCase.numberJokes,getJokesuseCase.DEFAULT_NUMBER_JOKES)
    }


    @Test
    fun getJokesUseCaseError() {
        //service will produce an error
        val e = Exception()
        `when`(service.getRandomJoke()).thenReturn(Observable.error(e))

        //No need for TestObservable, just call .test() transforms the Observer into a testable one
        getJokesuseCase.numberJokes = 2
        val test = getJokesuseCase.execute().test()

        test.awaitTerminalEvent() //actually no need, Scheduler is trampoline, so it uses current thread

        test.assertError(e)

        //check indeed usecase interacted with getRandomJoke only once
        verify(service).getRandomJoke()
        verifyNoMoreInteractions(service)

        //check if after the execution the number of jokes to request is set to default
        assertEquals(getJokesuseCase.numberJokes,getJokesuseCase.DEFAULT_NUMBER_JOKES)
    }

    @Test
    fun acceptItemUseCase() {

        // onComplete check sized of stream
        // be sure there is no Error
        val chuckJoke = ChuckFact("", arrayListOf(), "", "", "")
        acceptUseCase.fact = chuckJoke


        `when`(repository.saveChuckJoke(chuckJoke)).thenReturn(
                Observable.just(Unit))


        acceptUseCase.execute()
                .test()
                .assertNoErrors()

        //check indeed usecase interacted with loadSavedChuckJoke and saveChuckJoke only once

        verify(repository).saveChuckJoke(chuckJoke)
    }
}
