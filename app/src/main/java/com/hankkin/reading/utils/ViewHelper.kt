package com.hankkin.reading.utils

import android.content.Context
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.hankkin.reading.R
import com.hankkin.reading.common.Constant
import com.hankkin.reading.ui.home.articledetail.CommonWebActivity

/**
 * Created by huanghaijie on 2018/7/10.
 */
object ViewHelper {

    fun setRefreshLayout(context: Context?, isAutoRefresh: Boolean,
                         layout: SwipeRefreshLayout,
                         onRefreshListener: SwipeRefreshLayout.OnRefreshListener) {
        layout.setColorSchemeResources(ThemeHelper.getCurrentColor(context))
        layout.setOnRefreshListener(onRefreshListener)
        if (isAutoRefresh) layout.isRefreshing = true
    }

    fun changeRefreshColor(layout: SwipeRefreshLayout, context: Context?) {
        layout.setColorSchemeResources(ThemeHelper.getCurrentColor(context))
    }

    fun showConfirmDialog(context: Context, content: String, callback: MaterialDialog.SingleButtonCallback) {
        MaterialDialog.Builder(context)
                .content(content)
                .positiveText(context.resources.getString(R.string.ok))
                .negativeText(context.resources.getString(R.string.cancel))
                .onPositive(callback)
                .show()

    }

    fun showListNoTitleDialog(context: Context,list: MutableList<String>,calback: MaterialDialog.ListCallback){
        MaterialDialog.Builder(context)
                .items(list)
                .itemsCallback(calback)
                .show()
    }

    fun showAboutDialog(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_about_dialog,null)
        view.findViewById<ImageView>(R.id.iv_about_github).setOnClickListener { CommonWebActivity.loadUrl(context,Constant.AboutUrl.GITHUB,Constant.AboutUrl.GITHUB_TITLE) }
        view.findViewById<ImageView>(R.id.iv_about_juejin).setOnClickListener { CommonWebActivity.loadUrl(context,Constant.AboutUrl.JUEJIN,Constant.AboutUrl.JUEJIN_TITLE) }
        view.findViewById<ImageView>(R.id.iv_about_jianshu).setOnClickListener { CommonWebActivity.loadUrl(context,Constant.AboutUrl.JIANSHU,Constant.AboutUrl.JIANSHU_TITLE) }
        view.findViewById<ImageView>(R.id.iv_about_csdn).setOnClickListener { CommonWebActivity.loadUrl(context,Constant.AboutUrl.CSDN,Constant.AboutUrl.CSDN_TITLE) }
        val bottomSheet = BottomSheetDialog(context,R.style.BottomSheetDialog)
        view.findViewById<TextView>(R.id.tv_about_close).setOnClickListener { bottomSheet.dismiss() }
        view.findViewById<TextView>(R.id.tv_about_rate).setOnClickListener { ToastUtils.showToast(context,"敬请期待") }
        bottomSheet.setContentView(view)
        bottomSheet.show()
    }

}