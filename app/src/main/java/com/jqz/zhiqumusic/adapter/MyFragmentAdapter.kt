package com.jqz.zhiqumusic.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.adapter
文件名：MyFragmentAdapter
创建者：梦想(JQZ)
创建时间：2020/9/14  18:37  
描述：TODO
格言：人不努力枉为人，加油！
*/open class MyFragmentAdapter(
    fm: FragmentManager,
    behavior: Int,
    var titles: MutableList<String>,
    var fragments: MutableList<Fragment>
) : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): String? {
        return titles[position]
    }
}