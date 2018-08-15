package com.bufolab.android.chuckfacts.view.helper


/**
 * Created by Bufolab on 02/07/2018.
 */
interface ViewSwipeable {

    /**
     * Called when the View is within the range of being onAccepted.
     * But it has not been confirmed to be onAccepted.
     * (Example: user is still moving the item and can change her mind)
     */
    fun thresholdAccepted()

    /**
     * Item has been confirmed onAccepted
     */
    fun accepted()

    /**
     * Called when the View is within the range of being onRejected.
     * But it has not been confirmed to be onRejected.
     *
     * (Example: user is still moving the item and can change her mind)
     */
    fun thresholdRejected()

    /**
     * Item has been confirmed onRejected
     */
    fun rejected()

    /**
     * this is called always that the view is within the range between the thresholds
     */
    fun center()

    fun moving(dx:Float,dy:Float)

}