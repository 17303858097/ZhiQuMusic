package com.jqz.zhiqumusic.presenter

import android.annotation.SuppressLint
import com.jqz.mvplibrary.base.BasePresenter
import com.jqz.mvplibrary.base.IBaseView
import com.jqz.zhiqumusic.bean.ErgeResponse
import com.jqz.zhiqumusic.bean.PartnerBean
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.utils.JsonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rxhttp.wrapper.param.RxHttp

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.presenter
文件名：PartnerPresenterImpl
创建者：梦想(JQZ)
创建时间：2020/9/15  14:54  
描述：TODO
格言：人不努力枉为人，加油！
*/class PartnerPresenterImpl(view: ErGeddKtConstans.PartnerView) :
    BasePresenter<ErGeddKtConstans.PartnerView>(view), ErGeddKtConstans.PartnerPresenter {

    @SuppressLint("CheckResult")
    override fun initData() {
        //下载数据
        RxHttp.get("api/v1/app_configs?types=brand_area")
            .addHeader("Authorization", "ergedd_android:Android")
            .asClass(ErgeResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.let {
                    val data = it.data
                    val list = JsonUtils.jsonToClassList(data, PartnerBean::class.java)
                    view?.setList(list)
                }
            }, {
                view?.onError(it.message)
            })


        //使用协程
        /*  CoroutineScope(Dispatchers.Main).launch {
             var partnerList=withContext(Dispatchers.IO){
                  //加载数据
                  getData()
              }
              view?.setList(partnerList)
          }*/

    }

    @SuppressLint("CheckResult")
    private fun getData(): MutableList<PartnerBean> {
        var list: MutableList<PartnerBean> = mutableListOf()
        RxHttp.get("api/v1/app_configs?types=brand_area")
            .addHeader("Authorization", "ergedd_android:Android")
            .asClass(ErgeResponse::class.java)
            .subscribe({
                it.let {
                    val data = it.data
                    list = JsonUtils.jsonToClassList(data, PartnerBean::class.java)
                }
            }, {

            })

        return list
    }
}