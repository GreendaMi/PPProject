package com.greendami.repository

import android.app.Activity
import android.content.Context
import com.greendami.entity.entity_base_obj
import com.greendami.entity.entity_base_obj_objlist
import com.greendami.entity.entity_base_objlist
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import top.greendami.ppproject.ppproject.LoadingDialog
import top.greendami.ppproject.ppproject.ModelHolder


/**
 * val call = ServerUtil.retrofitService.getDays(AppConfig.API_KEY, location, start, days)
 * Created by GreendaMi on 2017/8/15.
 */
class RepositoryUtil {
    fun <T> request(context: Context, call: Call<T>, modelHolder: ModelHolder?, isFinishActivityOnfail: Boolean = false, isFinishActivityOnSuccee: Boolean = false, SucceeToast: String = "成功") {
        LoadingDialog.showNoCancelDialog(context)
        //如需缓存此处添加
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>?, t: Throwable?) {
                LoadingDialog.dismissCancelDialog()
                context.toast("网络异常，请重试")
                if (isFinishActivityOnfail) {
                    (context as? Activity)?.finish()
                }
            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                LoadingDialog.dismissCancelDialog()

                when ("1") {
                    (response?.body() as? entity_base_obj<String>)?.code -> {
                        context.toast((response?.body() as entity_base_obj<String>)?.msg)
                        if (isFinishActivityOnfail) {
                            (context as? Activity)?.finish()
                        }
                    }
                    (response?.body() as? entity_base_objlist<String>)?.code -> {
                        context.toast((response?.body() as entity_base_objlist<String>)?.msg)
                        if (isFinishActivityOnfail) {
                            (context as? Activity)?.finish()
                        }
                    }
                    (response?.body() as? entity_base_obj_objlist<String, String>)?.code -> {
                        context.toast((response?.body() as entity_base_obj_objlist<String, String>)?.msg)
                        if (isFinishActivityOnfail) {
                            (context as? Activity)?.finish()
                        }
                    }
                    else -> {
                        modelHolder?.get<T>()?.value = (response?.body() as T)
                        if (isFinishActivityOnSuccee) {
                            if (SucceeToast != "") {
                                context.toast(SucceeToast)
                            }
                            (context as? Activity)?.finish()
                        }
                    }
                }

            }

        })
    }

    //RepositoryUtil().request(this, call, modelHolder,{it},{})
    fun <T> request(context: Context, call: Call<T>, modelHolder: ModelHolder?, success : (T?) -> Unit , failure: () -> Unit) {
        LoadingDialog.showNoCancelDialog(context)
        //如需缓存此处添加
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>?, t: Throwable?) {
                LoadingDialog.dismissCancelDialog()
                context.toast("网络异常，请重试")
                failure()
            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                LoadingDialog.dismissCancelDialog()

                when ("1") {
                    (response?.body() as? entity_base_obj<String>)?.code -> {
                        context.toast((response?.body() as entity_base_obj<String>)?.msg)
                        failure()
                    }
                    (response?.body() as? entity_base_objlist<String>)?.code -> {
                        context.toast((response?.body() as entity_base_objlist<String>)?.msg)
                        failure()
                    }
                    (response?.body() as? entity_base_obj_objlist<String, String>)?.code -> {
                        context.toast((response?.body() as entity_base_obj_objlist<String, String>)?.msg)
                        failure()
                    }
                    else -> {
                        modelHolder?.get<T>()?.value = (response?.body() as T)
                        success(modelHolder?.get<T>()?.value)
                    }
                }

            }

        })
    }

    fun <T> requestNoDialog(context: Context, call: Call<T>, modelHolder: ModelHolder?, success : (T?) -> Unit , failure: () -> Unit) {
        //如需缓存此处添加
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>?, t: Throwable?) {
                context.toast("网络异常，请重试")
                failure()
            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {

                when ("1") {
                    (response?.body() as? entity_base_obj<String>)?.code -> {
                        context.toast((response?.body() as entity_base_obj<String>)?.msg)
                        failure()
                    }
                    (response?.body() as? entity_base_objlist<String>)?.code -> {
                        context.toast((response?.body() as entity_base_objlist<String>)?.msg)
                        failure()
                    }
                    (response?.body() as? entity_base_obj_objlist<String, String>)?.code -> {
                        context.toast((response?.body() as entity_base_obj_objlist<String, String>)?.msg)
                        failure()
                    }
                    else -> {
                        modelHolder?.get<T>()?.value = (response?.body() as T)
                        success(modelHolder?.get<T>()?.value)
                    }
                }

            }

        })
    }

    fun <T> requestNoDialog(context: Context, call: Call<T>, modelHolder: ModelHolder?) {
        //如需缓存此处添加
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>?, t: Throwable?) {
                context.toast("网络异常，请重试")
            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                when ("1") {
                    (response?.body() as? entity_base_obj<String>)?.code -> {
                        context.toast((response?.body() as entity_base_obj<String>)?.msg)
                    }
                    (response?.body() as? entity_base_objlist<String>)?.code -> {
                        context.toast((response?.body() as entity_base_objlist<String>)?.msg)
                    }
                    (response?.body() as? entity_base_obj_objlist<String, String>)?.code -> {
                        context.toast((response?.body() as entity_base_obj_objlist<String, String>)?.msg)
                    }
                    else -> {
                        modelHolder?.get<T>()?.value = (response?.body() as T)
                    }
                }
            }

        })
    }

}
