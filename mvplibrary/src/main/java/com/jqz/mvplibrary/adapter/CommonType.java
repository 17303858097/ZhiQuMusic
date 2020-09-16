package com.jqz.mvplibrary.adapter;

/**
 * 项目名：AndroidAptDemo
 * 包名：  com.liangxq.mymvpone.adapter
 * 文件名：CommonType
 * 创建者：liangxq
 * 创建时间：2020/3/18  15:16
 * 描述：TODO
 */
public interface CommonType<DATA> {
    //此方法得到getType的布局判断值，根据不同的布局判断值返回对应的布局文件
    int getTypeLayoutId(int viewType);
    //根据下标，或者数据类型的不同，返回不同的布局判断值，比如，第一个条目是banner，第二个是TextView，是根据下标，
    // 或者根据集合中数据的数量，加载对应的布局，如：集合中存放图片网址，如果有一个网址，加载一张图片的布局，有两个则加载两张图片的布局
    int getType(int positon, DATA data);
}
