package com.mkhaleghy.cinemakt.app

import com.google.gson.TypeAdapterFactory
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory

/**
 * Created by mk on 3/8/2018.
 */
@GsonTypeAdapterFactory
abstract class CinemaTypeAdapterFactory : TypeAdapterFactory {
    companion object {
        fun create(): TypeAdapterFactory? {
            return null//AutoValueGson_CinemaTypeAdapterFactory()
        }
    }
}
