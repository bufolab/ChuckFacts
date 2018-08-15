package com.bufolab.android.chuckfacts.view

import android.support.v7.widget.RecyclerView
import android.view.animation.LinearInterpolator

/**
 * Created by Bufolab on 02/07/2018.
 */
class DeckLayoutManager(private val scaleOnDeck: Boolean) : RecyclerView.LayoutManager() {

    companion object {

        private val TAG = DeckLayoutManager::class.java.simpleName

        /**
         * Initial positions for a deck expanded
         * All the offsets are DPs
         */
        private val offsets = intArrayOf(10,40,100,210,400)

    }

    // Consistent size applied to all child views
    private var mDecoratedChildWidth = 0
    private var mDecoratedChildHeight = 0

    //how much smaller the card behind the deck will be
    private val SCALE_FACTOR = 30f

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT)
    }


    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        // We have nothing to show for an empty data set but clear any existing views
        if (itemCount == 0) {
            detachAndScrapAttachedViews(recycler)
            return
        }

        // First or empty layout
        if (childCount == 0) {

            //Scrap measure one child
            val scrap = recycler!!.getViewForPosition(0)
            addView(scrap)

            measureChildWithMargins(scrap, 0, 0)

            /*
             * We make some assumptions in this code based on every child
             * view being the same size (i.e. a uniform grid). This allows
             * us to compute the following values up front because they
             * won't change.
             */
            mDecoratedChildWidth = getDecoratedMeasuredWidth(scrap)
            mDecoratedChildHeight = getDecoratedMeasuredHeight(scrap)

            detachAndScrapView(scrap, recycler)
        }

        layoutChildren(recycler)
    }



    private fun layoutChildren(recycler: RecyclerView.Recycler?) {

        // Before we layout child views, we first scrap all current attached views
        detachAndScrapAttachedViews(recycler)

        for (i in 0 until childCount)
            measureChildWithMargins(getChildAt(i), 0, 0)

        for (i in itemCount-1 downTo  0 ) {

            //display length of offset max view
            if (i <= offsets.size-1) {

                val view = recycler!!.getViewForPosition(i)
                addView(view)
                measureChildWithMargins(view, 0, 0)

                var index = offsets.size-1 - i
                var itemOffset = offsets[index]

                //scale by some factor the card behind
                val scale = if(scaleOnDeck) 1f-(i/ SCALE_FACTOR) else 1f

                //center the view
                layoutDecorated(view, ( this.width / 2 - (mDecoratedChildWidth / 2)).toInt(), itemOffset,
                        ( this.width / 2  + (mDecoratedChildWidth/2)).toInt(), itemOffset + mDecoratedChildHeight)

                //run the scale the view when recyclerview considers.
                // Check {@link #supportsPredictiveItemAnimations()} if there is a possibility to run animation other way.
                this.postOnAnimation { view.animate().scaleX(scale).scaleY(scale).setDuration(100).interpolator = LinearInterpolator() }
            }

        }
    }


    override fun canScrollHorizontally(): Boolean {
        return false
    }

    override fun canScrollVertically(): Boolean {
        return false
    }

}
