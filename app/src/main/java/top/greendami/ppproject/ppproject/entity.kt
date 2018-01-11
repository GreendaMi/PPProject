package com.greendami.entity

/**
 * Created by GreendaMi on 2017/8/15.
 */


data class entity_base_obj<T>(var code: String, var msg: String, var obj: T)
data class entity_base_objlist<T>(var code: String, var msg: String, var objList: ArrayList<T>)
data class entity_base_obj_objlist<T, V>(var code: String, var msg: String, var obj: T, var objList: ArrayList<V>)

