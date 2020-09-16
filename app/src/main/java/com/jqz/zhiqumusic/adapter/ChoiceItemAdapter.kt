//package com.jqz.zhiqumusic.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.recyclerview.widget.RecyclerView
//import androidx.recyclerview.widget.RecyclerView.Adapter
//import com.jqz.zhiqumusic.R
//import com.jqz.zhiqumusic.bean.ChoicesThreeBean
//import com.jqz.zhiqumusic.bean.ChoicesTowBean
//
//
///*
//项目名：ZhiQuMusic
//包名：com.jqz.zhiqumusic.adapter
//文件名：ChoiceItemAdapter
//创建者：梦想(JQZ)
//创建时间：2020/9/15  7:39
//描述：TODO
//格言：人不努力枉为人，加油！
//*/class ChoiceItemAdapter(var towLists:MutableList<ChoicesTowBean>,var threeLists: MutableList<ChoicesThreeBean>,var context: Context) : Adapter<RecyclerView.ViewHolder>() {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        //绑定布局
//        when (viewType) {
//            1 -> {
//                //加载第一个布局
//                val view = LayoutInflater.from(context).inflate(R.layout.choice_layout1,parent,false)
//                //创建ViewHOlder
//
//            }
//            2 -> {
//                //加载第二个布局
//
//            }
//            else -> {
//                //加载第三个布局
//
//            }
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return 3
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        if(position==0){
//            //返回第一个布局
//            return 1
//        }else if(position==1){
//            //返回第二个布局
//            return 2
//        }else if(position==2){
//            //返回第三个布局
//            return 3
//        }
//        return -1
//    }
//
//
//}