/*

    Author: Alex Mariano
    Dr. Dave Johannsen
    CS 4750 Mobile Application Development

 */

package com.broncospace.android.starvis.api

import retrofit2.http.GET

private const val API_KEY = "89PAUZ-9G5ZB6-49ZGHR-55XX"
private const val noradId = 25544
interface N2YOApi {
    @GET(
        "rest/v1/satellite/positions" +
                "/$noradId" +
                "/" + "34.03401" + //Latitude
                "/" + "-117.49122" + //Longitude
                "/" + "1070" + //Altitude
                "/" + "10" + //Seconds Future
                "&apiKey=$API_KEY"
    )
    suspend fun fetchSatellites(): N2YOResponse
}