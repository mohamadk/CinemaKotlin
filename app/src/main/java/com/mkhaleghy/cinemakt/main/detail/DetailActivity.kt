package com.mkhaleghy.cinemakt.main.detail

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.*
import com.mkhaleghy.cinemakt.R
import com.mkhaleghy.cinemakt.base.BaseActivity
import com.mkhaleghy.cinemakt.main.Detail
import com.mkhaleghy.cinemakt.tools.Utils
import com.mkhaleghy.cinemakt.tools.views.DraggableConstraintLayout
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity(override val layout: Int=R.layout.activity_detail) : BaseActivity() {
    private var MAX_DRAG: Int = 0
    private lateinit var detail: Detail


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MAX_DRAG = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, resources.displayMetrics).toInt()

        detail = intent.getParcelableExtra(PAR_DETAIL)
        initViews()
        toolbarDetail.title = ""
        setSupportActionBar(toolbarDetail)

        pager.adapter = InfoPageAdapter(supportFragmentManager, detail.detailInfoPages)
        nts_center.setViewPager(pager)

        mainLayDetail.mDragView = iv_icon
        mainLayDetail.mDragViewCover = iv_icon_cover

        mainLayDetail.maxDrag = MAX_DRAG
        mainLayDetail.mDragController = (object : DraggableConstraintLayout.DragController {
            override fun onDragDrop(view: View, captured: Boolean) {

            }

            override fun onDrag(dy: Int) {
                if (dy in 0..MAX_DRAG) {
                    iv_cover.rampDy = dy.toFloat()
                    card.translationY = dy.toFloat()
                    timeSpinner.translationY = dy.toFloat()
                    tv_title.translationY = dy.toFloat()
                    tv_subtitle.translationY = dy.toFloat()
                    rb_rate.translationY = dy.toFloat()
                    tv_rate.translationY = dy.toFloat()
                    ll_infoLay.translationY = dy.toFloat()
                    iv_play.translationY = dy * .4f
                }
            }

            override fun finish() {
                supportFinishAfterTransition()
            }
        })
        bindViews()

        ViewCompat.setOnApplyWindowInsetsListener(iv_cover, { _, insets ->
            insets.consumeSystemWindowInsets()
        })

//        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW)
    }

    private fun bindViews() {
        tv_title.text = detail.title
        tv_subtitle.text = detail.subtile
        rb_rate.rating = detail.rate
        tv_rate.text = detail.rateText

        nts_center.setTitles(*detail.detailInfoPagesTitles.toTypedArray())

        timeSpinner.attachDataSource(detail.bookTimes)

//        GlideApp.with(this).load(R.drawable.walle).into(iv_icon)
//        GlideApp.with(this).load(R.drawable.walle).into(iv_icon_cover)
//        GlideApp.with(this).load(R.drawable.walle).into(iv_cover)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        supportFinishAfterTransition()
    }

    private fun initViews() {
        iv_ticket.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                iv_ticket.viewTreeObserver.removeOnPreDrawListener(this)
                iv_ticket.translationY = intent.getIntExtra(PAR_TICKET_Y, 0) - iv_ticket.y
                return true
            }
        })

        val params = toolbarDetail.layoutParams as AppBarLayout.LayoutParams
        params.setMargins(params.leftMargin, Utils.statusBarHeight, params.rightMargin, params.bottomMargin)
        toolbarDetail.layoutParams = params
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        val TAG = "DetailActivity"
        private val PAR_DETAIL = "d"
        private val PAR_TICKET_Y = "ty"

        fun start(context: Activity, detail: Detail, ticketY: Int, vararg sharedViews: View) {
            val starter = Intent(context, DetailActivity::class.java)
            starter.also {
                it.putExtra(PAR_DETAIL, detail)
                it.putExtra(PAR_TICKET_Y, ticketY)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val elements = sharedViews.map {
                    Pair(it, it.transitionName)
                }

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, *elements.toTypedArray())
                context.startActivity(starter, options.toBundle())
            } else {
                context.startActivity(starter)
            }

        }
    }
}
