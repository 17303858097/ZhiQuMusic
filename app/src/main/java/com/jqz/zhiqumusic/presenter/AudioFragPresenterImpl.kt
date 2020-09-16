package com.jqz.zhiqumusic.presenter

import android.annotation.SuppressLint
import com.jqz.mvplibrary.base.BasePresenter
import com.jqz.mvplibrary.base.IBaseView
import com.jqz.zhiqumusic.bean.AudioFragTabBean
import com.jqz.zhiqumusic.bean.ErgeResponse
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.utils.JsonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rxhttp.wrapper.param.RxHttp

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.presenter
文件名：AudioFragPresenterImpl
创建者：梦想(JQZ)
创建时间：2020/9/15  20:59  
描述：TODO
格言：人不努力枉为人，加油！
*/class AudioFragPresenterImpl(view: ErGeddKtConstans.AudioFragView) : BasePresenter<ErGeddKtConstans.AudioFragView>(view) , ErGeddKtConstans.AudioFragPresenter {
    //获取tab数据
    @SuppressLint("CheckResult")
    override fun initData() {

        RxHttp.get("api/v1/audio_categories")
            .asClass(ErgeResponse::class.java)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.let {
                    val data = it.data
                    val list= JsonUtils.jsonToClassList(data,AudioFragTabBean::class.java)
                    view?.setTabs(list)
                }
            },{
                view?.onError(it.message)
            })

    }

}