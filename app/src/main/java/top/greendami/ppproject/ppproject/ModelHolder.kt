package top.greendami.ppproject.ppproject

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel



/**
 * Created by GreendaMi on 2017/8/21.
 */
class ModelHolder : ViewModel() {
    var data :MutableLiveData<*>? = null
    fun <T> get(): MutableLiveData<T> {
        if(data == null){
            data = MutableLiveData<T>()
        }
        return (data as MutableLiveData<T>?)!!
    }
    fun clear(){
        data = null
    }

    fun callUpdate(){
        data?.value = data?.value
    }
}

/**
 * LiveData 自动绑定的kotlin拓展 再也不同手动指定重载了
 */
fun <T> LiveData<T>.bind(lifecycleOwner: LifecycleOwner, block : (T?) -> Unit) {
    this.observe(lifecycleOwner,android.arch.lifecycle.Observer<T>{
        block(it)
    })
}
