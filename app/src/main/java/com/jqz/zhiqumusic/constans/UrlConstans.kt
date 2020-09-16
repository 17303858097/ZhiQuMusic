package com.jqz.zhiqumusic.constans

import android.util.Log

/*
项目名：ZhiQuMusic
包名：com.jqz.zhiqumusic.constans
文件名：UrlConstans
创建者：梦想(JQZ)
创建时间：2020/9/14  17:11  
描述：TODO
格言：人不努力枉为人，加油！
*/object UrlConstans {
    val TAG:String="jqz111::"
    val tabUrl:String="api/v1/album_categories"
    val limit:Int=10
    //宝宝看中精选的第一个布局的三张图片
    var ONE_IMAGE_URL = "http://img5g22.ergedd.com/video/9966_20170413122505_p455.jpg"
    var TOW_IMAGE_URL = "http://img5g22.ergedd.com/video/19567_1570873372542.jpg"
    var THREE_IMAGE_URL = "http://img5g22.ergedd.com/video/16553_1508849849585.jpg"

    fun loge(tag:String,msg:String?){
        Log.e(tag,msg)
    }
}