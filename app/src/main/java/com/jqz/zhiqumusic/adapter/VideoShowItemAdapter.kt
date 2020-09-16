package com.jqz.zhiqumusic.adapter

import android.content.Context
import android.view.View
import com.jqz.mvplibrary.adapter.BaseAdapter
import com.jqz.mvplibrary.adapter.BaseViewHolder
import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.bean.VideoShowBean

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.adapter
文件名：VideoShowItemAdapter
创建者：梦想(JQZ)
创建时间：2020/9/14  21:18  
描述：TODO
格言：人不努力枉为人，加油！
*/class VideoShowItemAdapter(
    datas: MutableList<VideoShowBean>?,
    context: Context?,
    itemlayoutId: Int
) : BaseAdapter<VideoShowBean>(datas, context, itemlayoutId) {


    override fun bindData(holder: BaseViewHolder?, data: VideoShowBean?) {
        //绑定数据
        holder!!.setImageViewContent(R.id.show_iv, data?.image)
        holder.setTextViewContent(R.id.show_tvName, data?.name)
        val view: View = holder.itemView.findViewById(R.id.show_downLoad)

        //下载监听

        //下载监听
        view.setOnClickListener { }
    }
}