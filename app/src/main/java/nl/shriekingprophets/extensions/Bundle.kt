package nl.shriekingprophets.extensions

import android.os.Bundle
import android.os.Parcelable

inline fun <reified T : Parcelable> Bundle.writeParcelableList(parcelable: List<T>, key: String? = null): Bundle {
    putParcelableArray(key ?: T::class.java.name, parcelable.toTypedArray())
    return this
}

inline fun <reified T : Parcelable> Bundle?.readParcelableList(key: String? = null): List<T>? {
    @Suppress("UNCHECKED_CAST")
    return this?.getParcelableArray(key ?: T::class.java.name)?.toList() as? List<T>?
}

inline fun <reified T : Parcelable> Bundle.writeParcelable(parcelable: T?, key: String? = null): Bundle {
    if (parcelable != null) {
        putParcelable(key ?: T::class.java.name, parcelable)
    }

    return this
}

inline fun <reified T : Parcelable> Bundle?.readParcelable(key: String? = null): T? {
    return this?.getParcelable(key ?: T::class.java.name) as? T
}

inline fun <reified T : Parcelable> T.toBundle(key: String? = null): Bundle {
    return Bundle().writeParcelable(this, key)
}

inline fun <reified T : Parcelable> List<T>.toBundle(key: String? = null): Bundle {
    return Bundle().writeParcelableList(this, key)
}

fun Bundle?.getInt(key: String, defaultValue: Int) = this?.getInt(key, defaultValue) ?: defaultValue

fun Bundle?.orEmpty() = this ?: Bundle.EMPTY