package es.jolusan.dogbreedspictures.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import es.jolusan.dogbreedspictures.R

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .error(ContextCompat.getDrawable(context, R.mipmap.ic_launcher))
        .into(this)
}

fun String.upperCaseFirst() : String {
    return replaceFirstChar { it.uppercase() }
}