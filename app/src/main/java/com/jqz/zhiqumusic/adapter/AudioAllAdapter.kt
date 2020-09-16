package com.jqz.zhiqumusic.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.bean.AudioAllItemBean
import kotlinx.android.synthetic.main.audio_all_item_layout.view.*
import org.jetbrains.anko.find

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.adapter
文件名：AudioAllAdapter
创建者：梦想(JQZ)
创建时间：2020/9/15  21:43  
描述：TODO
格言：人不努力枉为人，加油！
*/class AudioAllAdapter(var allItems: MutableList<AudioAllItemBean>, var context: Context) :
    RecyclerView.Adapter<AudioAllAdapter.ViewHolderAllItem>() {


    class ViewHolderAllItem(view: View) : RecyclerView.ViewHolder(view) {

        val allItemIv: ImageView = view.find(R.id.allItemIv)
        val allItemTvTitle: TextView = view.find(R.id.allItemTvTitle)
        val allItemTvDescription: TextView = view.find(R.id.allItemTvDescription)
        val allItemTvCount: TextView = view.find(R.id.allItemTvCount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAllItem {
        //绑定布局
        val view =
            LayoutInflater.from(context).inflate(R.layout.audio_all_item_layout, parent, false)
        val holder = ViewHolderAllItem(view)
        return holder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderAllItem, position: Int) {
        //绑定数据
        holder.allItemTvTitle.text=allItems[position].name
        holder.allItemTvDescription.text=allItems[position].description
        holder.allItemTvCount.text="${allItems[position].count}首"
        Glide.with(context).load(allItems[position].image).into(holder.allItemIv)
    }

    override fun getItemCount(): Int {
        return allItems.size
    }


}

