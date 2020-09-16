package com.jqz.mvplibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/*
项目名：ZhiQuMusic
包名：com.jqz.mvplibrary.base
文件名：BaseFragment
创建者：梦想(JQZ)
创建时间：2020/9/14  15:07  
描述：TODO
格言：人不努力枉为人，加油！
*/
abstract class BaseFragment<P : IBasePresenter<*>> : Fragment() {
    //公共的TAG，可用于日志
    protected var TAG = javaClass.name
    protected var presenter: P? = null
    private var contentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取P层对象
        presenter = createPresenter()
        //判断presenter不为空
        presenter?.let {
            //设置生命周期
            lifecycle.addObserver(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contentView = inflater.inflate(getLayoutId(), null)
        return contentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //下载网络数据
        initView()
        initData()
        //监听
    }

    abstract fun initView()

    protected open fun initData(){}


    //初始化
    protected abstract fun init(savedInstanceState: Bundle?)

    //获取布局id
    protected abstract fun getLayoutId(): Int

    //获取P层对象
    protected abstract fun createPresenter(): P?


}