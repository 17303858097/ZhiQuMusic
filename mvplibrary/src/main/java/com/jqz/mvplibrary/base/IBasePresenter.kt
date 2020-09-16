package com.jqz.mvplibrary.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.trello.rxlifecycle2.LifecycleProvider

/*
项目名：ZhiQuMusic
包名：com.jqz.mvplibrary.base
文件名：IBasePresenter
创建者：梦想(JQZ)
创建时间：2020/9/13  23:57  
描述：TODO
格言：人不努力枉为人，加油！
*/interface IBasePresenter<V : IBaseView> : LifecycleObserver {
//    管理生命周期
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume()
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause()
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy()


}