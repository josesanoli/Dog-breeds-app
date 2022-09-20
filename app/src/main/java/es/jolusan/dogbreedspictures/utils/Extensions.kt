package es.jolusan.dogbreedspictures.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .error(ColorDrawable(Color.GRAY))
        .into(this)
}
