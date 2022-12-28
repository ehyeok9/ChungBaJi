package com.example.temp

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "47b4bc782ad04b1567ee3c48cbf01016")

    }
}