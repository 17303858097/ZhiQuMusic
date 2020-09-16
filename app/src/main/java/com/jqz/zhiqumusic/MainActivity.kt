package com.jqz.zhiqumusic

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.jqz.zhiqumusic.fragment.AudioFragment
import com.jqz.zhiqumusic.fragment.CachFragment
import com.jqz.zhiqumusic.fragment.VideoFragment
import com.jqz.zhiqumusic.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val videoFragment by lazy { VideoFragment() }
    val audioFragment by lazy { AudioFragment() }
    val cachFragment by lazy { CachFragment() }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //设置tab
        initTab()
        //状态栏
        initstart()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initstart() {
        //支持状态栏透明
        StatusBarUtil.transparencyBar(this);
        //使用谷歌原生方式修改状态栏颜色
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
    }

    private fun initTab() {

        //创建集合
        val fragments= mutableListOf<Fragment>()
        //创建fragment


        //开启事务
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.mainFrame,videoFragment)
            .add(R.id.mainFrame,audioFragment)
            .add(R.id.mainFrame,cachFragment)
            .show(videoFragment)
            .hide(audioFragment)
            .hide(cachFragment)
            .commit()//提交事务

        //设置tablayout
        mainTab.addTab(mainTab.newTab().setText("宝宝看").setIcon(R.drawable.main_tab_selector1))
        mainTab.addTab(mainTab.newTab().setText("宝宝听").setIcon(R.drawable.main_tab_selector2))
        mainTab.addTab(mainTab.newTab().setText("缓存").setIcon(R.drawable.main_tab_selector3))


        //对tab进行监听
        mainTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            //重新选中时
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            //取消选中时
            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            //选中时
            override fun onTabSelected(p0: TabLayout.Tab?) {

                //控制各个页面的显示
                when(p0?.position){
                    0 -> { showVideo() }
                    1 -> { showAudio() }
                    2 -> { showCach() }
                    else -> {
                        //没有的时候
                    }


                }

            }


        })

    }


    /**
     * 缓存
     */
    private fun showCach() {
        supportFragmentManager.beginTransaction()
            .show(cachFragment)
            .hide(videoFragment)
            .hide(audioFragment)
            .commit()
    }

    /**
     * 宝宝听
     */
    private fun showAudio() {
        supportFragmentManager.beginTransaction()
            .show(audioFragment)
            .hide(videoFragment)
            .hide(cachFragment)
            .commit()
    }

    /**
     * 宝宝看
     */
    private fun showVideo() {
        //显示video，隐藏其他
        supportFragmentManager.beginTransaction().show(videoFragment)
            .hide(audioFragment)
            .hide(cachFragment)
            .commit()
    }


}
