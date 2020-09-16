package com.jqz.mvplibrary.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/*
项目名：ZhiQuMusic
包名：com.jqz.mvplibrary.base
文件名：BasePresenter
创建者：梦想(JQZ)
创建时间：2020/9/14  16:44  
描述：TODO
格言：人不努力枉为人，加油！
*/open class BasePresenter<V : IBaseView>(view : V): IBasePresenter<V>{

    protected var view: V?=view
    private var compositeDisposable : CompositeDisposable?=null
    init {
        compositeDisposable= CompositeDisposable()
    }
    override fun onCreate() {

    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    //生命周期清空
    override fun onDestroy() {
        compositeDisposable?.clear()
    }

    protected fun addSubscribe(disposable: Disposable){
        disposable.let {
            compositeDisposable?.add(disposable)
        }
    }

}