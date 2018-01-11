package com.greendami.actvity

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import top.greendami.ppproject.ppproject.ModelHolder

/**
 * Created by GreendaMi on 2017/9/12.
 */
abstract class BaseFragmentActivity : FragmentActivity(), LifecycleRegistryOwner {
    var lifecycleRegistry = LifecycleRegistry(this)
    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    lateinit var modelHolder: ModelHolder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        modelHolder = ViewModelProviders.of(this).get(ModelHolder::class.java)
        setContentView()
        loadData()
        initView()
        bindData()
    }

    abstract fun setContentView()

    //请求数据
    abstract fun loadData()

    abstract fun initView()

    /**
     * 把数据和控件绑定
     */
    abstract fun bindData()
}
