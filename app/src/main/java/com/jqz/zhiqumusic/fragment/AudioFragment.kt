package com.jqz.zhiqumusic.fragment

import android.graphics.ColorSpace
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jqz.mvplibrary.base.BaseFragment
import com.jqz.mvplibrary.base.LazyFragment

import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.adapter.MyFragmentAdapter
import com.jqz.zhiqumusic.bean.AudioFragTabBean
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.constans.UrlConstans
import com.jqz.zhiqumusic.fragment.audio.AudioAllFragment
import com.jqz.zhiqumusic.fragment.audio.AudioChoiceFragment
import com.jqz.zhiqumusic.presenter.AudioFragPresenterImpl
import kotlinx.android.synthetic.main.fragment_audio.*

/**
 * A simple [Fragment] subclass.
 * TODO,宝宝听
 */
class AudioFragment : BaseFragment<AudioFragPresenterImpl>(),ErGeddKtConstans.AudioFragView {
    var audioTabs : MutableList<AudioFragTabBean> = mutableListOf()
    override fun initView() {
        presenter?.initData()
    }


    private fun initTab() {
        //设置tab栏
        //因为有一个精选界面需要单独设置
        //存放fragment
        val fragments: MutableList<Fragment> = mutableListOf()
        //存放tab栏的标题
        val tabTitles: MutableList<String> = mutableListOf()
        tabTitles.add("精选")

        //遍历集合拿出title
        audioTabs.forEach {
            tabTitles.add(it.name)
        }

        //创建fragment
        fragments.add(AudioChoiceFragment())
        //遍历集合创建fragment
        audioTabs.forEach {
            fragments.add(AudioAllFragment(it.id))
        }

        //创建适配器
        val audioAdapter = MyFragmentAdapter(childFragmentManager, 0, tabTitles, fragments)
        //绑定viewPager
        audioVp.adapter = audioAdapter
        //绑定tab
        audioTab.setupWithViewPager(audioVp)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_audio
    }

    override fun createPresenter(): AudioFragPresenterImpl? {
        return AudioFragPresenterImpl(this)
    }

    override fun setTabs(mutableList: MutableList<AudioFragTabBean>) {
       //得到数据集合
        mutableList.let {
            audioTabs.clear()
                //until代表某个区间的前一个值，与0~(list.size-1)一样
            for (i in (0 until it.size)){
                UrlConstans.loge(UrlConstans.TAG,it[i].name)
            }
            audioTabs.addAll(mutableList)
        }
        initTab()
    }

    override fun onError(error: String?) {
        UrlConstans.loge(UrlConstans.TAG,error)
    }

    override fun showToast(msg: String) {

    }

    override fun init(savedInstanceState: Bundle?) {

    }

}
