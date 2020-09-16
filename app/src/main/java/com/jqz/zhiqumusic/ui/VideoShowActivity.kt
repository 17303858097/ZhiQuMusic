package com.jqz.zhiqumusic.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jqz.mvplibrary.base.BaseActivity
import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.adapter.VideoShowItemAdapter
import com.jqz.zhiqumusic.bean.PlayBean
import com.jqz.zhiqumusic.bean.VideoShowBean
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.constans.UrlConstans
import com.jqz.zhiqumusic.presenter.VideoShowPresenterImpl
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_video_show.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.systemHealthManager

/**
 * TODO,视频详情界面
 */

class VideoShowActivity : BaseActivity<VideoShowPresenterImpl>(), ErGeddKtConstans.VideoShowView {

    var offest = 0
    var id = 0
    var showList:MutableList<VideoShowBean> = mutableListOf()
    var videoShowAdapter : VideoShowItemAdapter?=null
    override fun createPresenter(): VideoShowPresenterImpl? {
        return VideoShowPresenterImpl(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_video_show
    }

    override fun init(savedInstanceState: Bundle?) {
        //沉浸式背景色
        setStatusColor()
        //沉浸式状态
        setSystemInvadeBlack()
        //返回键
        videoShowTool.setOnClickListener{
            finish()
        }
        //获取意图
        id = intent.getIntExtra("id", 0)
        //下载数据
        presenter?.initData(offest, UrlConstans.limit, id)
        initRlv()
    }
    //由于框架问题，翻页会出现卡顿情况
    override fun setList(showList: MutableList<VideoShowBean>) {
        //得到数据集合

        showList.let {
            this.showList.addAll(showList)
            videoShowShuaxin.finishRefresh();
            videoShowShuaxin.finishLoadMore();
            //展示列表
            videoShowAdapter?.notifyDataSetChanged()
        }
        videoShowTvTitle.text=showList[0].singer

    }

    private fun initRlv() {
        //设置布局管理器
        videoShowRlv.layoutManager=LinearLayoutManager(this)
        videoShowRlv.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        //创建适配器
        videoShowAdapter= VideoShowItemAdapter(showList,this,R.layout.show_layout_item)

        //绑定适配器
        videoShowRlv.adapter=videoShowAdapter


        //加载更多
        videoShowShuaxin.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                //加载更多
                if(offest<=showList[0].albums.video_count){
                    offest+=UrlConstans.limit
                    presenter?.initData(offest,UrlConstans.limit,id)

                }
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                //刷新
                offest=0
                showList.clear()
                presenter?.initData(offest,UrlConstans.limit,id)

            }

        })

        //刷新适配器

        //点击跳转到视频播放
        videoShowAdapter?.setItemClickListener {
            val bean=showList[it]
            val playBean=PlayBean()
            playBean.name=bean.name
            playBean.resource=bean.resource
            playBean.image=bean.image

            //跳转activity    并且携带数据      指定传递数据的key  传入的value
            startActivity<PlayVideoActivity>("play" to playBean)

        }


    }

    override fun getContext(): Context? {
        return this
    }

    override fun onError(error: String?) {
        UrlConstans.loge(UrlConstans.TAG, error)
    }

    override fun showToast(msg: String) {
    }
}
