package com.jqz.zhiqumusic.presenter

import android.annotation.SuppressLint
import com.jqz.mvplibrary.base.BasePresenter
import com.jqz.zhiqumusic.bean.ErgeResponse
import com.jqz.zhiqumusic.bean.VideoTabBean
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.constans.UrlConstans
import com.jqz.zhiqumusic.utils.JsonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.param.RxHttp

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.presenter
文件名：AudioPresenterImpl
创建者：梦想(JQZ)
创建时间：2020/9/14  16:41  
描述：TODO
格言：人不努力枉为人，加油！
*/class AudioPresenterImpl(view: ErGeddKtConstans.AudioView) :
    BasePresenter<ErGeddKtConstans.AudioView>(view), ErGeddKtConstans.AudioPresenter {
    //得到下载数据方法
    @SuppressLint("CheckResult")
    override fun initData() {
        //获取网络数据

        //他妈的不会自己切换线程，坑死老子了
        RxHttp.get(UrlConstans.tabUrl)
            .asClass(ErgeResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //成功回调
                val data = it.data
                val list = JsonUtils.jsonToClassList(data, VideoTabBean::class.java)
                //返回数据
                view?.setList(list)

            }, {
                //错误回调
                it.message?.let { it1 -> view?.onError(it1) }
            })
    }

}