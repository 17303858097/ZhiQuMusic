package com.jqz.mvplibrary.base

import android.content.Context

/*
项目名：ZhiQuMusic
包名：com.jqz.mvplibrary.base
文件名：IBaseView
创建者：梦想(JQZ)
创建时间：2020/9/13  23:57  
描述：TODO
格言：人不努力枉为人，加油！
*/interface IBaseView {
    fun getContext():Context?//提供上下文,如果在P层需要使用则可以直接获取
    fun onError(error:String?)//提供公共的错误回调函数
    fun showToast(msg:String)//提供公共的吐司功能
}