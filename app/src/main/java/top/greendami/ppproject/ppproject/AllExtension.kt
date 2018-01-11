package com.greendami.extension

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by GreendaMi on 2017/11/30.
 */

//在布局中，RecyclerView外嵌套一个swipeRefreshLayout，即可实现下拉刷新上拉加载更多
fun RecyclerView.setListener(loadMore:()->Unit,refresh:()->Unit ){
    setOnScrollListener(object : RecyclerView.OnScrollListener() {
        var lastVisibleItem: Int = 0
        val swipeRefreshLayout = this@setListener.parent
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            lastVisibleItem = (recyclerView?.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 === recyclerView.adapter?.itemCount) {
                //下拉刷新的时候不可以加载更多
                if(swipeRefreshLayout is SwipeRefreshLayout){
                    if(!swipeRefreshLayout.isRefreshing){
                        loadMore()
                    }
                }else{
                    loadMore()
                }
            }
        }

    })

    val swipeRefreshLayout = this.parent
    if(swipeRefreshLayout is SwipeRefreshLayout){
        swipeRefreshLayout.setOnRefreshListener {
            refresh()
        }
    }

}