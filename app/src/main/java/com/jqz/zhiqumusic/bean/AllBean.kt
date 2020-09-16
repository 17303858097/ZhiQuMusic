package com.jqz.zhiqumusic.bean

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.bean
文件名：AllBean
创建者：梦想(JQZ)
创建时间：2020/9/14  20:05  
描述：TODO
格言：人不努力枉为人，加油！
*/data class AllBean(
    val copyright_sensitive: Int,
    val cost: Int,
    val description: String,
    val erge_img_url: String,
    val ext: Ext,
    val free: Int,
    val icon_gif: String,
    val icon_url: String,
    val id: Int,
    val image_gif: String,
    val image_url: String,
    val image_ver: String,
    val index_recommend: Int,
    val is_vip: Int,
    val name: String,
    val play_count: Int,
    val price: Int,
    val publisher_name: String,
    val sensitive: Int,
    val status: Int,
    val type: Int,
    val video_count: Int,
    val vip_price: Int,
    val watch_areas: List<String>
)

