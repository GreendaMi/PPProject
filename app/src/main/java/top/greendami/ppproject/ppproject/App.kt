package com.greendami.actvity

import android.app.Application
import com.orhanobut.logger.LogLevel
import com.orhanobut.logger.Logger
import org.jetbrains.anko.doAsync


/**
 * Created by GreendaMi on 2017/8/30.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        doAsync {
            initLog()
        }
    }

    private fun initLog() {
//        Logger.init().logLevel(LogLevel.FULL).methodCount(3)
        Logger
                .init("TAg")    //LOG TAG默认是PRETTYLOGGER
                .methodCount(1)                 // 决定打印多少行（每一行代表一个方法）默认：2
                .hideThreadInfo()               // 隐藏线程信息 默认：显示
                .logLevel(LogLevel.FULL)        // 是否显示Log 默认：LogLevel.FULL（全部显示）
                .methodOffset(2)                // 默认：0

    }



}
