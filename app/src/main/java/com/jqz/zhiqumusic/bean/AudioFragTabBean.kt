package com.jqz.zhiqumusic.bean

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.bean
文件名：AudioFragTabBean
创建者：梦想(JQZ)
创建时间：2020/9/15  20:57  
描述：TODO
格言：人不努力枉为人，加油！
*/data class AudioFragTabBean(
    val description: String,
    val id: Int,
    val image: String,
    val name: String,
    val playlists: List<Playlists>,
    val sensitive: Int,
    val type: Int
)

data class Playlists(
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