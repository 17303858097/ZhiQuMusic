package com.jqz.zhiqumusic.bean

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.bean
文件名：ChoicesThreeBean
创建者：梦想(JQZ)
创建时间：2020/9/15  0:22  
描述：TODO
格言：人不努力枉为人，加油！
*/data class ChoicesThreeBean(
    val detail: String,
    val id: Int,
    val image: String,
    val image_gif: String,
    val image_ver: String,
    val item: Item,
    val item_id: Int,
    val item_type: String,
    val name: String,
    val rank: Int,
    val target_type: Int,
    val video: Video
)

data class Item(
    val ad_data: AdData,
    val age_type: Int,
    val album_id: Int,
    val albums: Albums,
    val copyright_contract_end_date: String,
    val copyright_contract_id: Int,
    val copyright_contract_start_date: String,
    val copyright_sensitive: Int,
    val created_at: String,
    val detail: String,
    val download_type: Int,
    val duration: Double,
    val id: Int,
    val image: String,
    val image_gif: String,
    val image_ver: String,
    val is_ad: Int,
    val is_vip: Int,
    val max_age: Int,
    val min_age: Int,
    val name: String,
    val play_count: Int,
    val publisher_id: Int,
    val publisher_name: String,
    val rank: Int,
    val resource: String,
    val search_keyword: String,
    val sensitive: Int,
    val singer: String,
    val srt_file_url: String,
    val status: Int,
    val type: Int,
    val updated_at: String,
    val vsample: Vsample,
    val watch_areas: List<String>
)

data class Video(
    val ad_data: AdDataX,
    val age_type: Int,
    val album_id: Int,
    val albums: AlbumsX,
    val copyright_contract_end_date: String,
    val copyright_contract_id: Int,
    val copyright_contract_start_date: String,
    val copyright_sensitive: Int,
    val created_at: String,
    val detail: String,
    val download_type: Int,
    val duration: Double,
    val id: Int,
    val image: String,
    val image_gif: String,
    val image_ver: String,
    val is_ad: Int,
    val is_vip: Int,
    val max_age: Int,
    val min_age: Int,
    val name: String,
    val play_count: Int,
    val publisher_id: Int,
    val publisher_name: String,
    val rank: Int,
    val resource: String,
    val search_keyword: String,
    val sensitive: Int,
    val singer: String,
    val srt_file_url: String,
    val status: Int,
    val type: Int,
    val updated_at: String,
    val vsample: VsampleX,
    val watch_areas: List<String>
)



data class ItemX(
    val postion: Int,
    val url: String
)

data class AdDataX(
    val image: String,
    val name: String,
    val url: String
)

data class AlbumsX(
    val cost: Int,
    val free: Int,
    val id: Int,
    val name: String,
    val play_count: Int,
    val price: Int,
    val video_count: Int,
    val vids: List<Any>,
    val vip_price: Int
)

data class VsampleX(
    val item: List<ItemXX>,
    val status: Int
)

data class ItemXX(
    val postion: Int,
    val url: String
)