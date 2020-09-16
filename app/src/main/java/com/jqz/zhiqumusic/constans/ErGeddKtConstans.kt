package com.jqz.zhiqumusic.constans

import android.view.View
import com.jqz.mvplibrary.base.IBasePresenter
import com.jqz.mvplibrary.base.IBaseView
import com.jqz.zhiqumusic.bean.*
import com.umeng.analytics.pro.ba

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.constans
文件名：ErGeddKtConstans
创建者：梦想(JQZ)
创建时间：2020/9/14  16:25  
描述：TODO
格言：人不努力枉为人，加油！
*/interface ErGeddKtConstans {

    //我当前的mvp框架
    //宝宝看Tab栏
    interface AudioView : IBaseView {
        //提供获取数据方法
        //返回集合
        fun setList(mutableList: MutableList<VideoTabBean>)

    }

    interface AudioPresenter : IBasePresenter<AudioView>{
        //提供下载数据方法
        fun initData()
    }

    //宝宝看中的动画到英文
    interface AllView : IBaseView{
        //提供得到集合数据
        fun setList(allList:List<AllBean>)

    }
    interface AllPresenter : IBasePresenter<AllView>{
        //提供下载数据方法
        fun initData(offset:Int,limit:Int,id:Int)
    }

    interface VideoShowView : IBaseView{
        //得到列表集合
        fun setList(showList:MutableList<VideoShowBean>)

    }

    interface VideoPresenter : IBasePresenter<VideoShowView>{
        //提供下载数据方法
        fun initData(offest: Int,limimt:Int,id:Int)
    }


    //宝宝看中的精选页面
    interface ChoiceView : IBaseView{
        //提供给集合赋值方法
        //第二个布局的数据
        fun setTowList(towList:MutableList<ChoicesTowBean>)
        fun setThreeList(threeList:MutableList<ChoicesThreeBean>)
    }

    interface ChoicePresenter : IBasePresenter<ChoiceView>{
        //提供下载数据函数
        fun initTowData(offest: Int,limit: Int)
        fun initThreeData(offest: Int,limit: Int)
    }

    //宝宝看中的伙伴界面
    interface PartnerView : IBaseView{
        //得到数据集合
        fun setList(partnerList: MutableList<PartnerBean>)
    }

    interface PartnerPresenter : IBasePresenter<PartnerView>{
        //下载数据
        fun initData()
    }


    //宝宝听主界面
    interface AudioFragView : IBaseView{
        //提供给集合赋值方法
        fun setTabs(mutableList: MutableList<AudioFragTabBean>)
    }
    interface AudioFragPresenter : IBasePresenter<AudioFragView>{
        //下载数据
        fun initData()
    }


    //宝宝听中的条目
    interface AudioAllView : IBaseView{
        //获取集合数据
        fun setList(audioItems:MutableList<AudioAllItemBean>)
    }

    interface AudioAllPresenter : IBasePresenter<AudioAllView>{
        //下载数据
        fun initData(itemId: Int,offest: Int,limit: Int)
    }


}