package com.jqz.zhiqumusic.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.jqz.zhiqumusic.MainActivity
import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.utils.StatusBarUtil
import java.util.*

class WelcomActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom)

        //支持状态栏透明
        StatusBarUtil.transparencyBar(this);
        //使用谷歌原生方式修改状态栏颜色
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
        //全屏
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startMainActivity()

    }
    private fun startMainActivity() {
        val delayTask: TimerTask = object : TimerTask() {
            override fun run() {
                val mainIntent: Intent = Intent(this@WelcomActivity, MainActivity::class.java)
                startActivity(mainIntent)
                this@WelcomActivity.finish()
            }
        }
        val timer = Timer()
        timer.schedule(delayTask, 2000) //延时两秒执行 run 里面的操作
    }
}
