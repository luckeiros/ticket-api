package com.luckeiros.ticketandroid.common.view

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

class TopRoundedCornersTransformation(private val radius: Float) : BitmapTransformation() {

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val bitmap = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        drawTopRoundRect(canvas, paint, outWidth.toFloat(), outHeight.toFloat())
        return bitmap
    }

    private fun drawTopRoundRect(canvas: Canvas, paint: Paint, width: Float, height: Float) {
        val rectF = RectF(0f, 0f, width, height)
        canvas.drawRoundRect(rectF, radius, radius, paint)
        canvas.drawRect(rectF.left, rectF.top + radius, rectF.right, rectF.bottom, paint)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(("top_rounded_corners$radius").toByteArray())
    }
}
