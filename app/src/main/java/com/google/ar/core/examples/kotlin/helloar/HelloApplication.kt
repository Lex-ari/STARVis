package com.google.ar.core.examples.kotlin.helloar

import android.app.Application
import com.broncospace.android.starvis.SatelliteRepository

class HelloApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        PhoneTelemetry.initialize(this)
        MathSatellite.initialize()
        SatelliteRepository.initialize(this)
    }
}