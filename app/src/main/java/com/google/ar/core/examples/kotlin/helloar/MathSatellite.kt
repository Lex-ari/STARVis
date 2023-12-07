package com.google.ar.core.examples.kotlin.helloar

import android.content.Context
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class MathSatellite private constructor(){
    companion object {
        private var INSTANCE: MathSatellite? = null
        fun initialize() {
            if (INSTANCE == null) {
                INSTANCE = MathSatellite()
            }
        }
        fun get(): MathSatellite {
            return INSTANCE ?: throw IllegalStateException("SatelliteRepository must be initialized")
        }
    }
    private val distance = 1F

    /*
    Assumes...
    azimuth = 0 = North
    elevation = 0 = Horizon
     */
    fun aziEleToEuler(azimuth: Double, elevation: Double) : FloatArray {
        val radAzimuth = Math.toRadians(azimuth)
        val radElevation = Math.toRadians(elevation)

        val north = cos(radAzimuth) * cos(radElevation)
        val east = sin(radAzimuth) * cos(radElevation)
        val groundDistance = sqrt(north.pow(2.0) + east.pow(2.0))
        val altitude = tan(radElevation) * groundDistance
        val ratio = 1.0 / sqrt(groundDistance.pow(2.0) + altitude.pow(2.0))

        val adjNorth = distance * ratio * north
        val adjEast = distance * ratio * east
        val adjAltitude = distance * ratio * altitude
        return floatArrayOf(adjNorth.toFloat(), adjEast.toFloat(), adjAltitude.toFloat())
    }
}