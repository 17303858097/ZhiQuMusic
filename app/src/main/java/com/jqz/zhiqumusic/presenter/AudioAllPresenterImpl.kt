package com.jqz.zhiqumusic.presenter

import android.annotation.SuppressLint
import com.jqz.mvplibrary.base.BasePresenter
import com.jqz.zhiqumusic.bean.AudioAllItemBean
import com.jqz.zhiqumusic.bean.ErgeResponse
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.constans.UrlConstans
import com.jqz.zhiqumusic.utils.JsonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rxhttp.wrapper.param.RxHttp

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.presenter
文件名：AudioAllPresenterImpl
创建者：梦想(JQZ)
创建时间：2020/9/15  21:32  
描述：TODO
格言：人不努力枉为人，加油！
*/class AudioAllPresenterImpl(view: ErGeddKtConstans.AudioAllView) : BasePresenter<ErGeddKtConstans.AudioAllView>(view),ErGeddKtConstans.AudioAllPresenter {
    @SuppressLint("CheckResult")
    override fun initData(itemId: Int, offest: Int, limit: Int) {

        RxHttp.get("api/v1/audio_categories/${itemId}/playlists")
            .add("channel","new")
            .add("offest",offest)
            .add("limit",UrlConstans.limit)
            .asClass(ErgeResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //使用let就是当调用对象不为空的时候，才会执行let中的代码，否则不执行
                it.let {
                    val data = it.data
                    val list=JsonUtils.jsonToClassList(data,AudioAllItemBean::class.java)
                    view?.setList(list)
                }
            },{
                view?.onError(it.message)
            })

    }

}