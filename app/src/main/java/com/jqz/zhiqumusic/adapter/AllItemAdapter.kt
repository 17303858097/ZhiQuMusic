package com.jqz.zhiqumusic.adapter

import android.content.Context
import com.jqz.mvplibrary.adapter.BaseAdapter
import com.jqz.mvplibrary.adapter.BaseViewHolder
import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.bean.AllBean

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.adapter
文件名：AllItemAdapter
创建者：梦想(JQZ)
创建时间：2020/9/14  20:32  
描述：TODO
格言：人不努力枉为人，加油！
*/class AllItemAdapter(datas: MutableList<AllBean>?, context: Context?, itemlayoutId: Int) :
    BaseAdapter<AllBean>(datas, context, itemlayoutId) {

    //绑定数据
    override fun bindData(holder: BaseViewHolder?, data: AllBean?) {
        holder!!.setImageViewContent(R.id.videodata_iv, data?.image_url)
        holder.setTextViewContent(R.id.videodata_tv_name, data?.name)
        holder.setTextViewContent(R.id.videodata_tv_description, data?.description)
        holder.setTextViewContent(
            R.id.videodata_tv_video_count,
            "共${data?.video_count}集"
        )

    }
}