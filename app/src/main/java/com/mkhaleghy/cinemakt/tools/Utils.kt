package com.mkhaleghy.cinemakt.tools

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.mkhaleghy.cinemakt.R
import com.mkhaleghy.cinemakt.app.Ci
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

/**
 * Created by mk on 3/8/2018.
 */

object Utils {
    val statusBarHeight: Int
        get() {
            val resourceId = Ci.context.resources
                    .getIdentifier("status_bar_height", "dimen", "android")
            return if (resourceId > 0) {
                Ci.context.resources.getDimensionPixelSize(resourceId)
            } else 0
        }

    val actionBarSize: Int
        get() = Ci.context.resources.getDimensionPixelSize(R.dimen.abc_action_bar_default_height_material)

    fun loadFileFromAssets(path: String): String? {
        var content: String? 
        try {
            val `is` = loadInputStreamFromAssetFile(path)
            val size = `is`!!.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            content = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return content
    }

    fun loadInputStreamFromAssetFile(fileName: String): InputStream? {
        try {
            return Ci.context.assets.open(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun getColoredText(txt: String, colorId: Int): Spannable {
        val spannable = SpannableString(txt)
        spannable.setSpan(ForegroundColorSpan(colorId), 0, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }
}
