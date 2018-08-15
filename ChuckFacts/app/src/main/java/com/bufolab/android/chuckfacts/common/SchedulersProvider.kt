package com.bufolab.android.chuckfacts.common

import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

/**
 *
 * Using DI and start performing tests you realize schedulers are used
 * by Rx, they are coupled in every usecase or presenter (wherever we decide where to observe)
 * So in order to make it testable
 *
 * Note: Using PowerMockito we could Mock AndroidSchedulers.mainThread() for instance.
 * That could be a solution. But better to inject a Scheduler provider.
 * Also makes the code more consistent and more clear, since the dependency of the presenter against
 * scheduler is more obvious.
 *
 *
 * Created by Bufolab on 14/08/2018.
 */
class SchedulersProvider @Inject constructor(@Named("io") val io:Scheduler,
                                             @Named("ui") val ui: Scheduler)