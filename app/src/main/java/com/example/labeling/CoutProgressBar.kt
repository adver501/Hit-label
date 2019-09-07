package com.example.labeling

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout

/**
 * @author Hamid Keyhani | hamidkeyhani8761@gmail.com
 * @version 1.0
 * @since 2018/11/01
 */
class CoutProgressBar(viewGroup: ViewGroup , context: Context){
    private val image6: ImageView
    private val image12: ImageView
    private val image18: ImageView
    private val image24: ImageView
    private var rotateAnimation: RotateAnimation? = null
    private val container: View
    init {
        val inflater = LayoutInflater.from(context)
        container = inflater.inflate(R.layout.loading, null, false)
        container.id = View.generateViewId()
        val params = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        params.addRule(RelativeLayout.CENTER_IN_PARENT, container.id)

        image6 = container.findViewById(R.id.lens6)
        image12 = container.findViewById(R.id.lens12)
        image18 = container.findViewById(R.id.lens18)
        image24 = container.findViewById(R.id.lens24)


        container.visibility = View.GONE
        viewGroup.addView(container, params)
    }

    fun dismiss() {
        rotateAnimation?.cancel()
        container.visibility = View.GONE
    }

    fun show() {
        image24.startAnimation(setAnimation(0, 1700))
        image18.startAnimation(setAnimation(400, 1300))
        image12.startAnimation(setAnimation(700, 1000))
        image6.startAnimation(setAnimation(900, 800))
        container.visibility = View.VISIBLE
    }

    fun setColor(color:Int){
        image24.setColorFilter(container.context.resources.getColor(color))
        image18.setColorFilter(container.context.resources.getColor(color))
        image12.setColorFilter(container.context.resources.getColor(color))
        image6.setColorFilter(container.context.resources.getColor(color))
    }

    fun enCreaseSpeed(speed: Long) {
        image24.startAnimation(setAnimation(0, 1700 / speed))
        image18.startAnimation(setAnimation(400 / speed, 1300 / speed))
        image12.startAnimation(setAnimation(700 / speed, 1000 / speed))
        image6.startAnimation(setAnimation(900 / speed, 800 / speed))
    }

    private fun setAnimation(offSet: Long, duration: Long): RotateAnimation {
        val rotateAnimation = RotateAnimation(
                0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.5f
        )
        rotateAnimation.duration = duration
        rotateAnimation.startOffset = offSet
        rotateAnimation.repeatCount = Animation.INFINITE
        return rotateAnimation
    }
}