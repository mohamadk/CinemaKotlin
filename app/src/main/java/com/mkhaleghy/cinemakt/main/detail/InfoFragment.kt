package com.mkhaleghy.cinemakt.main.detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mkhaleghy.cinemakt.R
import com.mkhaleghy.cinemakt.main.InfoPages
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    private lateinit var mContent: InfoPages

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.also {
            mContent = it.getParcelable(ARG_CONTENT)
        }
        if (arguments != null) {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindContent()
    }

    private fun bindContent() {
        tv_title.setText(mContent.title)
        tv_content.setText(mContent.content)
    }

    companion object {
        private val ARG_CONTENT = "c"

        fun newInstance(content: InfoPages): InfoFragment {
            val fragment = InfoFragment()
            val args = Bundle()
            args.putParcelable(ARG_CONTENT, content)
            fragment.arguments = args
            return fragment
        }
    }
}
