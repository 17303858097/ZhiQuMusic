package com.jqz.mvplibrary.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jqz.mvplibrary.utils.ColorUtils
import com.jqz.mvplibrary.utils.StatusUtils

/*
项目名：ZhiQuMusic
包名：com.jqz.mvplibrary.base
文件名：BaseActivity
创建者：梦想(JQZ)
创建时间：2020/9/13  23:56  
描述：TODO
格言：人不努力枉为人，加油！
*/

abstract class BaseActivity<P : IBasePresenter<*>> : AppCompatActivity() {

    //打印日志时可以直接使用定义的公共的TAG
    protected val TAG = javaClass.name
    protected var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutId = getLayoutId()
        if (layoutId != 0) {
            setContentView(layoutId)
        }

        presenter = createPresenter()

        presenter?.let {
            lifecycle.addObserver(it)
        }

        //用来适配沉浸式状态栏
        setStatusColor()
        setSystemInvadeBlack()
        init(savedInstanceState)
    }

    /**
     * 设置状态栏背景颜色
     */
    protected open fun setStatusColor(){
        StatusUtils.setUseStatusBarColor(this,ColorUtils.parseColor("#00ffffff"))
    }


    /**
     * 设置沉浸式状态
     */
    protected open fun setSystemInvadeBlack(){
        //第二个参数是是否沉浸式，第三个参数是状态栏字体是否为黑色
        StatusUtils.setSystemStatus(this,true,true)
    }

    /**
     * 界面跳转,可用于有跳转需求的界面
     */
    protected fun intent(clazz: Class<*>){
        startActivity(Intent(this,clazz))
    }

    /**
     * 携带bundle跳转
     */
    protected fun intent(bundle: Bundle,clazz: Class<*>){
        startActivity(Intent(this,clazz).apply {
            putExtras(bundle)
            /*val extras = intent.extras//可以获取存储的bundle
            //通过bundle获取值
            extras.getString()*/
        })
    }


    abstract fun createPresenter(): P?

    abstract fun getLayoutId(): Int

    abstract fun init(savedInstanceState: Bundle?)

}