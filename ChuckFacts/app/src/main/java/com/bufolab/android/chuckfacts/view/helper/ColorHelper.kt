package com.bufolab.android.chuckfacts.view.helper

import android.graphics.Color
import java.util.*

/**
 * Created by Bufolab on 13/08/2018.
 */
class ColorHelper {

    companion object {

        val rand = Math.random()
        /**
         * 328.2353 0.45333335 0.29411766  :  31.76471 0.5466666 0.7058823
         * Anticolor is a color that has enough contrast against other color
         */
        fun getAntiColor(color: Int): Int {
            return Color.rgb(Color.red(color) * 299, Color.green(color) * 587, Color.blue(color) * 114)
        }

        fun ClosedRange<Int>.random() =
                Random().nextInt((endInclusive + 1) - start) + start

        val intRange = 0..360
        val intRangeForFloat = 10..100
        val intRangeForFloatNotDark = 20..100

        /**
         * Get a random color
         */
        fun getRandomColor(): Int {

            //avoid bright saturated colors
            var s = Integer.MAX_VALUE //saturation
            var v =  Integer.MAX_VALUE //value

            while(s+v < 160F){
                 s = intRangeForFloat.random()
                 v = intRangeForFloatNotDark.random()
            }


            val floatArray = FloatArray(3)
            floatArray[0] = intRange.random().toFloat()
            floatArray[1] = s / 100F
            floatArray[2] = v / 100F

            return Color.HSVToColor(floatArray)
        }
    }
}