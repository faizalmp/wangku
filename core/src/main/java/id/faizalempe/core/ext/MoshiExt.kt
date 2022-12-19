package id.faizalempe.data.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version MoshiUtil, v 0.1 09/12/22 14.46 by Faizal Muhammad Priyowivowo
 */

inline fun <reified T> T.toJsonString() : String {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter = moshi.adapter(T::class.java)
    return jsonAdapter.toJson(this).toString()
}

inline fun <reified T> String?.toObject() : T? {
    if (this.isNullOrEmpty()) return null
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter = moshi.adapter(T::class.java)
    return jsonAdapter.fromJson(this)
}