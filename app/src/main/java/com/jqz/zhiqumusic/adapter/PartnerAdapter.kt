package com.jqz.zhiqumusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.bean.PartnerBean
import org.jetbrains.anko.find

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.adapter
文件名：PartnerAdapter
创建者：梦想(JQZ)
创建时间：2020/9/15  15:15  
描述：TODO
格言：人不努力枉为人，加油！
*/class PartnerAdapter(var context: Context,var partners:MutableList<PartnerBean>) : RecyclerView.Adapter<PartnerAdapter.ViewHolderItem>() {

    inner class ViewHolderItem(var view : View) : RecyclerView.ViewHolder(view){
        //找控件
        val partnerItemIv : ImageView = view.find(R.id.partnerItemIv)
        val partnerItemTv : TextView = view.find(R.id.partnerItemTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        //绑定布局
        val view: View =LayoutInflater.from(context).inflate(R.layout.partner_item_layot,parent,false)

        return ViewHolderItem(view)
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        //绑定数据
        Glide.with(context).load(partners[position].image_url).into(holder.partnerItemIv)
        holder.partnerItemTv.text=partners[position].title

    }

    override fun getItemCount()=partners.size

}