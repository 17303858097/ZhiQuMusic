package com.jqz.mvplibrary.base

import android.os.Bundle

/*
项目名：ZhiQuMusic
包名：com.jqz.mvplibrary.base
文件名：LazyFragment
创建者：梦想(JQZ)
创建时间：2020/9/14  15:15  
描述：TODO
格言：人不努力枉为人，加油！
*/
abstract class LazyFragment<P : IBasePresenter<*>> :BaseFragment<P>() {

    private var isLoaded =false
    override fun onResume() {
        super.onResume()
        //判断，fragment是否可见
        if(!isLoaded && !isHidden){
            lazyInit()
            isLoaded=true
        }

    }

    override fun init(savedInstanceState: Bundle?) {

    }
    abstract fun lazyInit()

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded=false
    }


}