package com.jqz.zhiqumusic.fragment.audio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jqz.mvplibrary.base.BaseFragment
import com.jqz.mvplibrary.base.LazyFragment

import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.adapter.AudioAllAdapter
import com.jqz.zhiqumusic.bean.AudioAllItemBean
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.constans.UrlConstans
import com.jqz.zhiqumusic.presenter.AudioAllPresenterImpl
import kotlinx.android.synthetic.main.fragment_audio_all.*

/**
 * A simple [Fragment] subclass.
 * TODO,宝宝听中的儿歌到纯音乐列表界面
 */
class AudioAllFragment(var audioId : Int) : LazyFragment<AudioAllPresenterImpl>(),ErGeddKtConstans.AudioAllView {
    var adapter : AudioAllAdapter? = null
    var isData : Boolean =false
    var audioItemsA:MutableList<AudioAllItemBean> = mutableListOf()
    var offest : Int =0
    override fun lazyInit() {

    }

    override fun initView() {
        initRlv()
    }

    override fun initData() {
        if(!isData)
        presenter?.initData(audioId,offest,UrlConstans.limit)
    }
    override fun init(savedInstanceState: Bundle?) {

    }

    private fun initRlv() {
        //设置布局管理器
        audioAllRlv.layoutManager=LinearLayoutManager(context)
        //设置分隔线
        audioAllRlv.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))

        //创建适配器
        adapter= context?.let { AudioAllAdapter(audioItemsA, it) }
        //绑定
        audioAllRlv.adapter=adapter

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_audio_all
    }

    override fun createPresenter(): AudioAllPresenterImpl? {
        return AudioAllPresenterImpl(this)
    }

    override fun setList(audioItems: MutableList<AudioAllItemBean>) {
        //得到集合
        audioItems.let {
            audioItemsA.addAll(audioItems)
            adapter?.notifyDataSetChanged()
            isData=true
        }
    }

    override fun onError(error: String?) {
        UrlConstans.loge(UrlConstans.TAG,error)
    }

    override fun showToast(msg: String) {

    }

}
