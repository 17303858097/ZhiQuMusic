package com.jqz.zhiqumusic.fragment.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jqz.mvplibrary.base.LazyFragment

import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.adapter.PartnerAdapter
import com.jqz.zhiqumusic.bean.PartnerBean
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.constans.UrlConstans
import com.jqz.zhiqumusic.presenter.PartnerPresenterImpl
import kotlinx.android.synthetic.main.fragment_partner.*

/**
 * A simple [Fragment] subclass.
 * TODO，宝宝看中的伙伴界面
 */
class PartnerFragment : LazyFragment<PartnerPresenterImpl>(),ErGeddKtConstans.PartnerView {
    //加个判断值解决重复加载数据即可
    var isData : Boolean=false
    var partners : MutableList<PartnerBean> = mutableListOf()
    var adapter :PartnerAdapter? =null
    override fun lazyInit() {
        if(!isData)
        presenter?.initData()
    }

    override fun initView() {
        initRlv()//在GitHub中进行了修改
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_partner
    }

    override fun createPresenter(): PartnerPresenterImpl? {
        return PartnerPresenterImpl(this)
    }

    override fun setList(partnerList: MutableList<PartnerBean>) {
        UrlConstans.loge(UrlConstans.TAG,partnerList.toString())

        //得到数据,let标识当此对象不为空时，会执行let中的代码
        partnerList.let {
            //倒序集合
            val lista =partnerList.reversed()
            isData=true
            partners.addAll(lista)
            adapter?.notifyDataSetChanged()

        }

    }

    private fun initRlv() {
        //创建布局管理器           //网格布局 三列
        partnerRlv.layoutManager=GridLayoutManager(context,3)
        //创建适配器
        adapter=PartnerAdapter(context!!,partners)
        //绑定适配器
        partnerRlv.adapter=adapter

        adapter?.notifyDataSetChanged()
    }

    override fun onError(error: String?) {
        UrlConstans.loge(UrlConstans.TAG,error)
    }

    override fun showToast(msg: String) {

    }

}
