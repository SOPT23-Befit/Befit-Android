package com.sopt.befit.animation

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar

class ProgressAnimation(progress : ProgressBar, mDuration : Long) : Animation() {
    private var mTo: Int = 0
    private var mFrom: Int = 0

    private var mStepDuration = mDuration
    private val mProgressBar = progress
    fun setProgress(progress: Int) {
        var progress = progress
        if (progress < 0) {
            progress = 0
        }

        if (progress > mProgressBar.max) {
            progress = mProgressBar.max
        }

        mTo = progress

        mFrom = 0
        //duration = Math.abs(mTo - mFrom) * mStepDuration
        duration = mStepDuration
        mProgressBar.startAnimation(this)
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        var value = mFrom + (mTo - mFrom) * interpolatedTime
        mProgressBar.progress = value.toInt()
    }
}
