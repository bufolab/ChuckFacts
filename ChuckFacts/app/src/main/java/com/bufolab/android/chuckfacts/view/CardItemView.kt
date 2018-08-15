package com.bufolab.android.chuckfacts.view

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import com.bufolab.android.chuckfacts.R
import com.bufolab.android.chuckfacts.view.helper.ColorHelper
import com.bufolab.android.chuckfacts.view.helper.ViewSwipeable

/**
 * Created by Bufolab on 02/07/2018.
 */
class CardItemView : CardView, ViewSwipeable, View.OnAttachStateChangeListener {

    private val TAG = CardItemView.javaClass.canonicalName

    companion object {
        private val ROTATION_FACTOR = 20f //if the view needs to be rotated by a factor

    }

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attr: AttributeSet) : super(ctx, attr)


    constructor(ctx: Context, attr: AttributeSet, i: Int) : super(ctx, attr, i)

    init{
        this.addOnAttachStateChangeListener(this)
    }

    override fun onViewDetachedFromWindow(v: View?) {
        //nothing to do
    }


    //Everytime the view is attached it will look different
    override fun onViewAttachedToWindow(v: View?) {
        center() //reset if there was any rotation

        //set new colors
        val randomColor = ColorHelper.getRandomColor()
        this.setCardBackgroundColor(randomColor)
        val title: TextView = this.findViewById(R.id.value)
        title.setTextColor(ColorHelper.getAntiColor(randomColor))
    }

    /*************just for testing TinderLike swipes*********************/

    override fun thresholdAccepted() {
        Log.d(TAG,"thresholdAccepted")
    }

    override fun accepted() {
        Log.d(TAG,"accepted")
    }

    override fun thresholdRejected() {
        Log.d(TAG,"thresholdRejected")
    }

    override fun rejected() {
        Log.d(TAG,"rejected")
    }

    override fun center() {
        Log.d(TAG,"center")
        rotation = 0f
    }

    override fun moving(dx: Float, dy: Float) {
        Log.d(TAG,"onMoving $dx");
       rotation = dx/ ROTATION_FACTOR
    }


}
