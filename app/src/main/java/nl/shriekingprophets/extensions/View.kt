package nl.shriekingprophets.extensions

import android.view.View

inline fun <T : View> T.onClick(crossinline block: (T) -> Unit) = setOnClickListener { block(it as T) }