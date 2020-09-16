package com.jqz.zhiqumusic.app

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.tencent.bugly.crashreport.CrashReport
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure


/*
项目名：IntelligentMusicKt
包名：com.jqz.intelligentmusickt.app
文件名：MyApp
创建者：梦想(JQZ)
创建时间：2020/9/7  15:44  
描述：TODO
格言：你是怎样，你的世界就是怎样！
*/class MyApp: Application() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate() {
        super.onCreate()
        //初始化请求头
//        GlobalSetting.instance.setkeyValue(Constants.baseUrl,"http://api.ergedd.com/").setShowLog(true)
        CrashReport.initCrashReport(applicationContext, "46bbea5bbd", false)

        /*  //进程上报控制
          val context: Context = applicationContext
          // 获取当前包名
          // 获取当前包名
          val packageName: String = context.packageName
          // 获取当前进程名
          // 获取当前进程名
          val processName = getProcessName()
          // 设置是否为上报进程
          // 设置是否为上报进程
          val strategy = UserStrategy(context)
          strategy.isUploadProcess = processName == null || processName == packageName
          // 初始化Bugly
          // 初始化Bugly
          CrashReport.initCrashReport(context, "注册时申请的APPID", true, strategy)*/
        //测试是否集成成功
//        CrashReport.testJavaCrash()

        //友盟
        /**
         * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调
         * 用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         * UMConfigure.init调用中appkey和channel参数请置为null）。
         */
        UMConfigure.init(this@MyApp, "5f5dc6f8b4739632429e2eee", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null)
        UMConfigure.setLogEnabled(true);//打开统计开关
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
        //开启log网络请求日志
//        RxHttp.setDebug(true)
        //添加公共请求头


    }


}