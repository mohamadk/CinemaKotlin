package com.mkhaleghy.cinemakt.tools.views

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TimeInterpolator
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator

/**
 * Created by mk on 3/9/2018.
 */

class DraggableConstraintLayout(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {
    val TAG = "DraggableConstraintL"

    private lateinit var mDragHelper: ViewDragHelper
    internal lateinit var mDragView: View
    private var mDragViewCover: View? = null
    internal var mDragController: DragController? = null

    internal var sAccelerator: TimeInterpolator = AccelerateInterpolator()
    internal var startX: Float = 0F
    internal var startY: Float = 0F
    internal var startTX: Float = 0F
    internal var startTY: Float = 0F
    internal var releasedChild: View? = null
    private var maxDrag: Int = 0
    private var canceledAnim: Boolean = false
    private var finishAfterRelease = false

    override fun onFinishInflate() {
        super.onFinishInflate()
        init()
    }

    private fun init() {
        mDragHelper = ViewDragHelper.create(this, 1.0f, object : ViewDragHelper.Callback() {

            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return mDragView === child
            }

            override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
                super.onViewPositionChanged(changedView, left, top, dx, dy)

                if (isReachToOffset()) {
                    finishAfterRelease = true
                }
                mDragController?.onDrag((top - startY).toInt())

            }

            private fun isReachToOffset(): Boolean {
                return top - startY >= maxDrag
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                return top
            }

            override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
                super.onViewCaptured(capturedChild, activePointerId)
                if (startX == 0f) {
                    startX = capturedChild.x
                    startY = capturedChild.y
                    startTX = capturedChild.translationX
                    startTY = capturedChild.translationY
                }
                mDragController?.onDragDrop(capturedChild, true)
            }

            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                super.onViewReleased(releasedChild, xvel, yvel)
                mDragController?.onDragDrop(releasedChild, false)
                releaseAnim(releasedChild, startX, startY)
            }
        })

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDragHelper.processTouchEvent(event)
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return when (ev.actionMasked) {
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                mDragHelper.cancel()
                false
            }
            else ->{
                mDragHelper.shouldInterceptTouchEvent(ev)
            }
        }
    }

    private fun releaseAnim(releasedChild: View, startX: Float, startY: Float) {
        this.releasedChild = releasedChild
        val pvhX = PropertyValuesHolder.ofFloat(View.X, releasedChild.x, startX)
        val pvhY = PropertyValuesHolder.ofFloat(View.Y, releasedChild.y, startY)
        val pvhTX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, releasedChild.translationX, startTX)
        val pvhTY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, releasedChild.translationY, startTY)
        val downAnim = ObjectAnimator.ofPropertyValuesHolder(
                releasedChild, pvhTX, pvhTY, pvhX, pvhY
        )

        downAnim.interpolator = sAccelerator
        downAnim.duration = 200
        downAnim.start()

        downAnim.addUpdateListener { animation ->
            val drag = (animation.getAnimatedValue(View.Y.name) as Float - startY).toInt()

            if (maxDrag < drag) {
                mDragController?.onDrag(drag)
            } else {
                if (finishAfterRelease) {
                    canceledAnim = true
                    mDragController?.finish()
                    downAnim.cancel()
                } else {
                    mDragController?.onDrag(drag)
                }
            }

        }

        downAnim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (!canceledAnim) {
                    releasedChild.visibility = View.INVISIBLE
                    mDragViewCover?.visibility = View.VISIBLE
                    requestLayout()
                }
            }
        })


    }


    override fun requestLayout() {
        super.requestLayout()
        if (releasedChild != null && startX != 0f) {
            Log.d(TAG, "requestLayout: ")
            releasedChild?.post {
                releasedChild?.also {
                    it.visibility = View.VISIBLE
                    it.x = startX
                    it.y = startY
                }
                mDragViewCover?.visibility = View.INVISIBLE
            }

        }
    }

    interface DragController {
        fun onDragDrop(view: View, captured: Boolean)

        fun onDrag(dy: Int)

        fun finish()
    }

}
