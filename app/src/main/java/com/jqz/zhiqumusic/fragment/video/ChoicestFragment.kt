package com.jqz.zhiqumusic.fragment.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jqz.mvplibrary.base.BaseFragment

import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.adapter.ChoiceAdapter
import com.jqz.zhiqumusic.bean.ChoicesThreeBean
import com.jqz.zhiqumusic.bean.ChoicesTowBean
import com.jqz.zhiqumusic.constans.ErGeddKtConstans
import com.jqz.zhiqumusic.constans.UrlConstans
import com.jqz.zhiqumusic.presenter.ChoicesPresenterImpl
import kotlinx.android.synthetic.main.fragment_choicest.*
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 * TODO,宝宝看中的精选fragment
 */
class ChoicestFragment : BaseFragment<ChoicesPresenterImpl>(),ErGeddKtConstans.ChoiceView {
    var offest:Int=0
    var towLists : MutableList<ChoicesTowBean>? = mutableListOf()
    var threeLists : MutableList<ChoicesThreeBean> = mutableListOf()
    override fun initView() {

        //处理布局
        initRlv()
    }

    override fun init(savedInstanceState: Bundle?) {
        presenter?.initTowData(offest,UrlConstans.limit)
        presenter?.initThreeData(offest,UrlConstans.limit)

    }

    private fun initRlv() {
        //设置布局管理器
        choiceRlv.layoutManager=LinearLayoutManager(context)
        //创建适配器
        val choiceAdapter = ChoiceAdapter(towLists as ArrayList<ChoicesTowBean>?,
            threeLists as ArrayList<ChoicesThreeBean>?,context)

        choiceRlv.adapter=choiceAdapter

        //刷新适配器
        choiceAdapter.notifyDataSetChanged()

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_choicest
    }

    override fun createPresenter(): ChoicesPresenterImpl? {
       return ChoicesPresenterImpl(this)
    }

    override fun setTowList(towList: MutableList<ChoicesTowBean>) {
        //得到第二个布局数据
        towList.let {
            //添加到集合
            towLists?.addAll(it)
        }
    }

    override fun setThreeList(threeList: MutableList<ChoicesThreeBean>) {
        //得到第三个布局数据
        threeList.let {
            threeLists.addAll(it)
        }
    }

    override fun onError(error: String?) {
        UrlConstans.loge(UrlConstans.TAG,error)
    }

    override fun showToast(msg: String) {
    }

    //处理生命周期
    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.onDestroy()
    }

}
