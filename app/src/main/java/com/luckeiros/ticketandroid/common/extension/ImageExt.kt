package com.luckeiros.ticketandroid.common.extension

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.luckeiros.ticketandroid.R
import com.luckeiros.ticketandroid.common.view.TopRoundedCornersTransformation

fun loadImage(context: Context, url: String, image: ImageView) {
    Glide.with(context)
        .load(url)
        .transform(TopRoundedCornersTransformation(context.resources.getDimension(R.dimen.small_grid)))
        .into(image)
}