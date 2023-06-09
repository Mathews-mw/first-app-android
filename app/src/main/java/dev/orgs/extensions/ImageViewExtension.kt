package dev.orgs.extensions

import android.widget.ImageView
import coil.load
import dev.orgs.R

fun ImageView.tentaCarregarImagem(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro);
        error(R.drawable.erro);
        placeholder(R.drawable.placeholder);
    }
}

