package com.jqz.zhiqumusic.presenter

import android.annotation.SuppressLint
import com.jqz.mvplibrary.base.BasePresenter
import com.jqz.zhiqumusic.bean.ChoicesThreeBean
import com.jqz.zhiqumusic.bean.ChoicesTowBean
import com.jqz.zhiqumusic.bean.ErgeResponse
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.utils.JsonUtils
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rxhttp.wrapper.param.RxHttp

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.presenter
文件名：ChoicesPresenterImpl
创建者：梦想(JQZ)
创建时间：2020/9/15  0:25  
描述：TODO
格言：人不努力枉为人，加油！
*/open class ChoicesPresenterImpl(view: ErGeddKtConstans.ChoiceView) :
    BasePresenter<ErGeddKtConstans.ChoiceView>(view), ErGeddKtConstans.ChoicePresenter {
    /**
     * 下载第二个布局数据
     */
    @SuppressLint("CheckResult")
    override fun initTowData(offest: Int, limit: Int) {
        var list: MutableList<ChoicesTowBean> = mutableListOf()
        RxHttp.get("api/v1/albums/home_recommended")
            .add("channel", "new")
            .add("offset", offest)
            .add("limit", limit)
            .asClass(ErgeResponse::class.java)
            .subscribe({
                //成功回调
                val data = it.data
                list = JsonUtils.jsonToClassList(data, ChoicesTowBean::class.java)
                view?.setTowList(list)
            }, {

            })


//        使用协程下载
/*        CoroutineScope(Dispatchers.Main).launch {
            val towData = withContext(Dispatchers.IO){
                getTowData()
            }
            val threeData = withContext(Dispatchers.IO){
                getThreeData(offest,limit)
            }
                      //回调给V层
            view?.setTowList(towData)
            view?.setThreeList(threeData)*/
    }

    @SuppressLint("CheckResult")
    override fun initThreeData(offest: Int, limit: Int) {
        var list: MutableList<ChoicesThreeBean> = mutableListOf()
        RxHttp.get("api/v1/home_items")
            .add("type", 1)
            .add("channel", "new")
            .add("offset", offest)
            .add("limit", limit)
            .add("sensitive", 8)
            .asClass(ErgeResponse::class.java)
            .subscribe({
                //成功回调
                val data = it.data
                list = JsonUtils.jsonToClassList(data, ChoicesThreeBean::class.java)
                view?.setThreeList(list)
            }, {
                view?.onError(it.message)
            })
    }
}


/*  //协程挂起下载第三个数据
  @SuppressLint("CheckResult")
    fun getThreeData(offest: Int, limit: Int):MutableList<ChoicesThreeBean> {
      var list:MutableList<ChoicesThreeBean> = mutableListOf()
      RxHttp.get("api/v1/home_items")
          .add("type", 1)
          .add("channel", "new")
          .add("offset", offest)
          .add("limit", limit)
          .add("sensitive", 8)
          .asClass(ErgeResponse::class.java)
          .subscribe({
              //成功回调
              val data = it.data
              list = JsonUtils.jsonToClassList(data, ChoicesThreeBean::class.java)

          }, {

          })


      return list
  }

  //协程挂起下载第二个布局数据
  @SuppressLint("CheckResult")
    fun getTowData(offest: Int=0, limit: Int=8) :MutableList<ChoicesTowBean> {
      var list:MutableList<ChoicesTowBean> = mutableListOf()
      RxHttp.get("api/v1/albums/home_recommended")
          .add("channel","new")
          .add("offset",offest)
          .add("limit",limit)
          .asClass(ErgeResponse::class.java)
          .subscribe({
              //成功回调
              val data = it.data
              list=JsonUtils.jsonToClassList(data,ChoicesTowBean::class.java)

          },{

          })

      return list
  }
*/
/**
 * 下载第三个布局数据
 */
