package com.mkhaleghy.cinemakt.main.daylist

import android.app.Activity
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.PopupMenu
import android.util.AttributeSet
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mkhaleghy.cinemakt.R
import com.mkhaleghy.cinemakt.base.Binder
import com.mkhaleghy.cinemakt.base.OnAdapterInteractionListener
import com.mkhaleghy.cinemakt.main.Movie
import com.mkhaleghy.cinemakt.main.detail.DetailActivity
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by mk on 3/2/2018.
 */

class MovieView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr), Binder<Movie>, PopupMenu.OnMenuItemClickListener {
    val TAG = "MovieView"
    private var movie: Movie? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        val popupMenu = PopupMenu(context, iv_overflow!!)
        popupMenu.inflate(R.menu.item_list_menu)
        popupMenu.setOnMenuItemClickListener(this)

        iv_overflow.setOnClickListener { v -> popupMenu.show() }
    }

    override fun bind(item: Movie, mListener: OnAdapterInteractionListener) {
        this.movie = item

        Glide.with(context as Activity).load(R.drawable.walle).into(iv_icon)

        tv_title.text = item.title
        tv_subtitle.text = item.subtitle
        tv_genre.text = item.genre
        tv_time.text = item.time
        rb_rate.rating = item.rate

        setOnClickListener { v ->
            val ticketLoc = IntArray(2)
            iv_ticket.getLocationInWindow(ticketLoc)
            mListener.detailSelected()
            DetailActivity.start(
                    context as Activity
                    , item.detail
                    , ticketLoc[1]
                    , iv_icon
                    , iv_ticket
                    , cv
                    , tv_title
                    , tv_subtitle
                    , rb_rate)
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        if (movie != null) {
            if (item.itemId == R.id.book) {
                //TODO book the movie
                Toast.makeText(context, "book the movie", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return false
    }
}
