package com.jqz.zhiqumusic.presenter

import android.annotation.SuppressLint
import com.jqz.mvplibrary.base.BasePresenter
import com.jqz.zhiqumusic.bean.AllBean
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
文件名：AllPresenterImpl
创建者：梦想(JQZ)
创建时间：2020/9/14  20:09  
描述：TODO
格言：人不努力枉为人，加油！
*/class AllPresenterImpl(view:ErGeddKtConstans.AllView) : BasePresenter<ErGeddKtConstans.AllView>(view),ErGeddKtConstans.AllPresenter {
    //下载数据
    @SuppressLint("CheckResult")
    override fun initData(offset: Int, limit: Int, id:Int) {
        Thread.sleep(1000)
        RxHttp.get("api/v1/album_categories/${id}/albums")
            .add("channel","new")
            .add("offset",offset)
            .add("limit",limit)
            .add("sensitive",8)
            .asClass(ErgeResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val list:List<AllBean>
                //成功回调
                it.let {
                    val data = it.data
                    list=JsonUtils.jsonToClassList(data,AllBean::class.java)

                }
                view?.setList(list)
            },{
                //失败回调
                view?.onError(it.message)
            })


    }

}