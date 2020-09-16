package com.jqz.zhiqumusic.presenter

import android.annotation.SuppressLint
import com.jqz.mvplibrary.base.BasePresenter
import com.jqz.zhiqumusic.bean.ErgeResponse
import com.jqz.zhiqumusic.bean.VideoShowBean
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.constans.UrlConstans
import com.jqz.zhiqumusic.utils.JsonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rxhttp.wrapper.param.RxHttp

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.presenter
文件名：VideoShowPresenterImpl
创建者：梦想(JQZ)
创建时间：2020/9/14  21:07  
描述：TODO
格言：人不努力枉为人，加油！
*/class VideoShowPresenterImpl(view:ErGeddKtConstans.VideoShowView) : BasePresenter<ErGeddKtConstans.VideoShowView>(view) , ErGeddKtConstans.VideoPresenter {
    @SuppressLint("CheckResult")
    override fun initData(offest: Int, limimt: Int, id: Int) {

        RxHttp.get("api/v1/albums/${id}/videos")
            .add("channel","new")
            .add("offset",offest)
            .add("limit",UrlConstans.limit)
            .add("sensitive",8)
            .asClass(ErgeResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                //成功回调
                it.let {
                    val data = it.data
                    val list=JsonUtils.jsonToClassList(data,VideoShowBean::class.java)
                    view?.setList(list)
                }

            },{
                view?.onError(it.message)
            })
    }

}