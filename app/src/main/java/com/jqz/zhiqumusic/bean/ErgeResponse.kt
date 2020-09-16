package com.jqz.zhiqumusic.bean

import com.google.gson.JsonElement

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.bean
文件名：ErgeResponse
创建者：梦想(JQZ)
创建时间：2020/9/14  16:30  
描述：TODO
格言：人不努力枉为人，加油！
*/data class ErgeResponse(
    val data: JsonElement,
    val message: String,
    val success: Boolean
)

