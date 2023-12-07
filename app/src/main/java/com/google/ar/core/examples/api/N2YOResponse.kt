/*

    Author: Alex Mariano
    Dr. Dave Johannsen
    CS 4750 Mobile Application Development

 */

package com.google.ar.core.examples.api
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class N2YOResponse(
    val positions: List<PositionItem>
)