package com.luckeiros.ticketandroid.common.view.image

import android.util.DisplayMetrics

enum class ImageType(val ratio: String, val width: Int, val height: Int) {
    SMALL("16_9", 205, 115),
    MEDIUM("16_9", 640, 360),
    LARGE("16_9", 1024, 576),
    XLARGE("16_9", 2048, 1152),
    TABLET_LANDSCAPE_SMALL("3_2", 1024, 683),
    TABLET_LANDSCAPE_LARGE("16_9", 2048, 1152),
    RETINA_LANDSCAPE("16_9", 1136, 639);

    companion object {
        fun findBestMatch(densityDpi: Int, isLandscape: Boolean): ImageType {
            return when {
                isLandscape -> when (densityDpi) {
                    in DisplayMetrics.DENSITY_LOW..DisplayMetrics.DENSITY_MEDIUM -> TABLET_LANDSCAPE_SMALL
                    in DisplayMetrics.DENSITY_HIGH..DisplayMetrics.DENSITY_XHIGH -> TABLET_LANDSCAPE_LARGE
                    else -> RETINA_LANDSCAPE
                }

                else -> when (densityDpi) {
                    in DisplayMetrics.DENSITY_LOW..DisplayMetrics.DENSITY_MEDIUM -> SMALL
                    in DisplayMetrics.DENSITY_HIGH..DisplayMetrics.DENSITY_XHIGH -> MEDIUM
                    in DisplayMetrics.DENSITY_XXHIGH..DisplayMetrics.DENSITY_XXXHIGH -> LARGE
                    else -> XLARGE
                }
            }
        }
    }
}