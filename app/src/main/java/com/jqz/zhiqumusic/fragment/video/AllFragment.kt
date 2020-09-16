package com.jqz.zhiqumusic.fragment.video

import android.content.Intent
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
import com.jqz.zhiqumusic.adapter.AllItemAdapter
import com.jqz.zhiqumusic.bean.AllBean
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.constans.UrlConstans
import com.jqz.zhiqumusic.presenter.AllPresenterImpl
import com.jqz.zhiqumusic.ui.VideoShowActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_all.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 * TODO,宝宝看中的动画到英文fragment
 */
class AllFragment(var int: Int) : LazyFragment<AllPresenterImpl>(),ErGeddKtConstans.AllView {
    var offest:Int=0
    var listAll : MutableList<AllBean> = mutableListOf()
    var adapter : AllItemAdapter? =null
    var isData : Boolean=false

    override fun getLayoutId(): Int {
        return R.layout.fragment_all
    }

    override fun createPresenter(): AllPresenterImpl? {
        return AllPresenterImpl(this)

    }

    override fun setList(allList: List<AllBean>) {

        //得到数据回调
        allList.let {
            //交给展示列表方法
            listAll.addAll(allList)
            isData=true
            allShuaXin.finishLoadMore()
            allShuaXin.finishRefresh()
            adapter?.notifyDataSetChanged()
        }




    }

    private fun initRlv() {
        //设置布局管理器
        allRlv.layoutManager=LinearLayoutManager(context)
        //设置分隔线
        allRlv.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
        //创建适配器
        adapter =AllItemAdapter(listAll,context,R.layout.videoall_layout_item)
        //绑定适配器
        allRlv.adapter=adapter

        allShuaXin.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                //加载更多
                if(offest<=72){
                    offest+=UrlConstans.limit
                    presenter?.initData(offest,UrlConstans.limit,int)

                }
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
//                toast("刷新了")
                //刷新
                //清空集合
                listAll.clear()
                offest=0
                presenter?.initData(offest,UrlConstans.limit,int)

            }
        })



        //设置点击监听
        adapter?.setItemClickListener {
            //点击跳转到详情界面
            val intent=Intent(context,VideoShowActivity::class.java)
            val bean=listAll[it]
            intent.putExtra("id",bean.id)
            startActivity(intent)
        }

    }

    override fun onError(error: String?) {
        UrlConstans.loge(UrlConstans.TAG,error)
    }

    override fun showToast(msg: String) {

    }

    override fun lazyInit() {
        if(!isData)
        presenter?.initData(offest,UrlConstans.limit,int)
    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        initRlv()

    }

    override fun initView() {
    }

}
