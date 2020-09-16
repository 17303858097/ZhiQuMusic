package com.jqz.zhiqumusic.bean

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.bean
文件名：AudioAllItemBean
创建者：梦想(JQZ)
创建时间：2020/9/15  21:30  
描述：TODO
格言：人不努力枉为人，加油！
*/data class AudioAllItemBean(
    val cost: Int,
    val count: Int,
    val description: String,
    val free: Int,
    val id: Int,
    val image: String,
    val name: String,
    val price: Int,
    val sensitive: Int,
    val square_image_url: String,
    val vip_price: Int,
    val watch_areas: List<String>
)