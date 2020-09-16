package com.jqz.zhiqumusic.test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ThreadPoolExecutor
import kotlin.coroutines.coroutineContext
import kotlin.math.log10

class TestXieChengActivity : AppCompatActivity() {
    val url: String = "https://d47jbcq60tnr6.cloudfront.net/2020112/10841-c19x3h.dkzt6.jpg"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //支持状态栏透明
        StatusBarUtil.transparencyBar(this)
        //使用谷歌原生方式修改状态栏颜色
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true)
        //协程练习
        //通过协程网络请求数据

        initXie()

    }

    private fun initXie() {
        //对按钮进行监听
        mainTab.setOnClickListener {
            //点击开启协程获取网络数据

            //指定协程在主线程中运行      启动协程可以使用launch或者async函数来启动，协程其实就是这两个函数中包起来的代码块
            CoroutineScope(Dispatchers.Main).launch {
                //在协程中开启子线程做耗时操作
                Log.e("111jqz::", "当前线程${Thread.currentThread().name}")//打印当前线程名称给

                //开启io子线程网络获取数据,当协程内部定义了函数得时候，协程得返回值就是定义函数得返回值
                val netData = withContext(Dispatchers.IO) {
                    Log.e("111jqz::", "当前线程:${Thread.currentThread().name}")
                    //获取网络数据
                    getNetData(url)

                }
                //设置给ImageView,可以看到，我们在上面开启了一个io线程来进行网络数据得获取
                //在下边我们对UI控件进行了更新，并没有主动切换到主线程中，这就是协程得好处，它会自动帮我们把线程
                //切换到主线程中
//                mainIv.setImageBitmap(netData)

            }
        }
    }

    /**
     *协程中挂起的对象其实是协程，它是从当前线程挂起，换句话说，就是这个协程从正在执行它的线程上脱离
     * 注意它是脱离，而不是停止了，也就是我当前的这个线程不去管它了，它去干它该干的事情就可以了，
     * 在 suspend 函数执行完成之后，协程为我们做的最爽的事就来了：会自动帮我们把线程再切回来。
     * 协程在执行到有 suspend 标记的函数的时候，会被 suspend 也就是被挂起，而所谓的被挂起，就是切个线程；
       不过区别在于，挂起函数在执行完成之后，协程会重新切回它原先的线程。
       再简单来讲，在 Kotlin 中所谓的挂起，就是一个稍后会被自动切回来的线程调度操作。
       这个「切回来」的动作，在 Kotlin 里叫做 resume，恢复。
       所以，要求 suspend 函数只能在协程里或者另一个 suspend 函数里被调用，
      还是为了要让协程能够在 suspend 函数切换线程之后再切回来。

     */

    /**
     * 给函数加上 suspend 关键字，然后在 withContext 把函数的内容包住就可以了。
       提到用 withContext是因为它在挂起函数里功能最简单直接：把线程自动切走和切回。
       当然并不是只有 withContext 这一个函数来辅助我们实现自定义的 suspend 函数，比如还有一个挂起函数叫 delay，
       它的作用是等待一段时间后再继续往下执行代码。
     */

    //还有一个挂起函数叫 delay，它的作用是等待一段时间后再继续往下执行代码。可以看到左边有一个箭头
    suspend fun suspendUntilDone() {
        while (!false) {
            delay(5)
        }
    }

    //使用协程挂起加载一张图片
    suspend fun getNetData(url: String): Bitmap? {
        //suspend关键字只是一个提醒，要真正得挂起需要
        //进行网络请求
        val netUrl = URL(url)
        val connection = netUrl.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()
        val responseCode = connection.responseCode
        if (responseCode == 200) {
            val inputStream = connection.inputStream
            return BitmapFactory.decodeStream(inputStream)
        }
        return null
    }

    //如果你的某个函数比较耗时，也就是要等的操作，那就把它写成 suspend 函数。这就是原则。
    suspend fun demo() {

    }
}
