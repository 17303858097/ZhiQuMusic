package com.jqz.zhiqumusic.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import com.google.android.material.tabs.TabLayout
import com.jqz.mvplibrary.base.BaseFragment
import com.jqz.mvplibrary.base.LazyFragment

import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.adapter.MyFragmentAdapter
import com.jqz.zhiqumusic.bean.VideoTabBean
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.constans.UrlConstans
import com.jqz.zhiqumusic.fragment.video.AllFragment
import com.jqz.zhiqumusic.fragment.video.ChoicestFragment
import com.jqz.zhiqumusic.fragment.video.PartnerFragment
import com.jqz.zhiqumusic.presenter.AudioPresenterImpl
import kotlinx.android.synthetic.main.fragment_video.*
import org.jetbrains.anko.collections.forEachByIndex
import org.jetbrains.anko.support.v4.runOnUiThread
import rxhttp.wrapper.param.RxHttp

/**
 * A simple [Fragment] subclass.
 * TODO,宝宝看
 */
class VideoFragment : LazyFragment<AudioPresenterImpl>(), ErGeddKtConstans.AudioView {
    //使用预加载的变量，需要在使用之前初始化一下子
    private var titles: MutableList<String> = mutableListOf()
    private var list: MutableList<VideoTabBean> = mutableListOf()
    override fun initView() {

    }


    override fun initData() {
        RxHttp.setDebug(true)
        //下载数据

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_video
    }

    override fun createPresenter(): AudioPresenterImpl? {
        return AudioPresenterImpl(this)
    }

    override fun setList(mutableList: MutableList<VideoTabBean>) {
        //比如这样初始化
        //接口中没有精选，手动添加
        titles.add("精选")
        //得到数据集合
        mutableList.let {
            list.clear()
            list.addAll(mutableList)
            //集合不为空
            for (bean in it) {
                //添加标题到集合中
                titles.add(bean.name)
            }
        }
        //接口中没有英文和伙伴手动添加
        titles.add("英文")
        titles.add("伙伴")

        initTab()
    }

    private fun initTab() {
        //初始化组件

        //根据集合的长度创建fragment，第一个和最后一个需要单独创建
        //初始化集合用来存储fragment
        val fragments = mutableListOf<Fragment>()
        //添加精选
        var index = 0
        fragments.add(ChoicestFragment())
        //遍历title集合
        titles.forEach {
            if (index < titles.size-3) {
                fragments.add(AllFragment(list[index].id))
                UrlConstans.loge("jqz111::::","${list[index].id}")
                index++;
            }
        }
        //伙伴
        fragments.add(AllFragment(list[list.size-1].id))
        //添加伙伴
        fragments.add(PartnerFragment())

        //创建适配器
        val adapter =
            MyFragmentAdapter(childFragmentManager, 0, titles = titles, fragments = fragments)
        //绑定适配器
        videoVp.adapter = adapter
        //绑定tab
        //tablayout不出文字
        videoTab.setupWithViewPager(videoVp, true)


    }

    override fun onError(error: String?) {
        //这里的TAG使用的是封装到baseframgnet
        UrlConstans.loge(TAG, error)
    }

    override fun showToast(msg: String) {
        //如果需要吐司提示，那就写toast即可

    }

    override fun lazyInit() {
        presenter?.initData()
    }

    override fun init(savedInstanceState: Bundle?) {

    }

}
