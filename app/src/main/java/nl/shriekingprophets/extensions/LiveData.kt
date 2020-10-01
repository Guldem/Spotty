package nl.shriekingprophets.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import java.io.Serializable


/**
 * Combine the values from A and B into a new LiveData object that emits Pair<A, B>.
 * When either A or B are null no value will be emitted
 */
fun <A, B> LiveData<A>.combineLatest(liveB: LiveData<B>): LiveData<Pair<A, B>> {
    return MediatorLiveData<Pair<A, B>>().apply {
        var lastA: A? = null
        var lastB: B? = null

        val assignValue = {
            val a = lastA
            val b = lastB

            if (a != null && b != null) {
                value = a to b
            }
        }

        addSource(this@combineLatest) {
            lastA = it
            assignValue()
        }

        addSource(liveB) {
            lastB = it
            assignValue()
        }
    }
}

/**
 * Combine the values from A and B and C into a new LiveData object that emits Triple<A, B, C>.
 * When either A or B or C are null no value will be emitted
 */
fun <A, B, C> combineLatest(la: LiveData<A>, lb: LiveData<B>, lc: LiveData<C>): LiveData<Triple<A, B, C>> {
    return MediatorLiveData<Triple<A, B, C>>().apply {
        var lastA: A? = null
        var lastB: B? = null
        var lastC: C? = null

        val assignValue = {
            val a = lastA
            val b = lastB
            val c = lastC

            if (a != null && b != null && c != null) {
                value = Triple(a, b, c)
            }
        }

        addSource(la) {
            lastA = it
            assignValue()
        }

        addSource(lb) {
            lastB = it
            assignValue()
        }

        addSource(lc) {
            lastC = it
            assignValue()
        }
    }
}

/**
 * Represents a quartet of values
 *
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * Quadruple exhibits value semantics
 *
 * @param A type of the first value.
 * @param B type of the second value.
 * @param C type of the third value.
 * @param D type of the fourth value.
 * @property first First value.
 * @property second Second value.
 * @property third Third value.
 * @property fourth Fourth value.
 */
data class Quadruple<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
) : Serializable {

    /**
     * Returns string representation of the [Quadruple] including its [first], [second], [third] and [fourth] values.
     */
    override fun toString(): String = "($first, $second, $third, $fourth)"
}

/**
 * Represents a Quintet of values
 */
data class Quintet<out A, out B, out C, out D, out E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E
) : Serializable {

    override fun toString(): String = "($first, $second, $third, $fourth, $fifth)"
}

/**
 * Represents a Sextet of values
 */
data class Sextet<out A, out B, out C, out D, out E, out F>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
    val sixth: F
) : Serializable {

    override fun toString(): String = "($first, $second, $third, $fourth, $fifth, $sixth)"
}

/**
 * Combine the values from A and B and C into a new LiveData object that emits Triple<A, B, C>.
 * When either A or B or C are null no value will be emitted
 */
fun <A, B, C, D> combineLatest(la: LiveData<A>, lb: LiveData<B>, lc: LiveData<C>, ld: LiveData<D>): LiveData<Quadruple<A, B, C, D>> {
    return MediatorLiveData<Quadruple<A, B, C, D>>().apply {
        var lastA: A? = null
        var lastB: B? = null
        var lastC: C? = null
        var lastD: D? = null

        val assignValue = {
            val a = lastA
            val b = lastB
            val c = lastC
            val d = lastD


            if (a != null && b != null && c != null && d != null) {
                value = Quadruple(a, b, c, d)
            }
        }

        addSource(la) {
            lastA = it
            assignValue()
        }

        addSource(lb) {
            lastB = it
            assignValue()
        }

        addSource(lc) {
            lastC = it
            assignValue()
        }

        addSource(ld) {
            lastD = it
            assignValue()
        }
    }
}

fun <A, B, C, D, E> combineLatest(la: LiveData<A>, lb: LiveData<B>, lc: LiveData<C>, ld: LiveData<D>, le: LiveData<E>): LiveData<Quintet<A, B, C, D, E>> {
    return MediatorLiveData<Quintet<A, B, C, D, E>>().apply {
        var lastA: A? = null
        var lastB: B? = null
        var lastC: C? = null
        var lastD: D? = null
        var lastE: E? = null

        val assignValue = {
            val a = lastA
            val b = lastB
            val c = lastC
            val d = lastD
            val e = lastE


            if (a != null && b != null && c != null && d != null && e != null) {
                value = Quintet(a, b, c, d, e)
            }
        }

        addSource(la) {
            lastA = it
            assignValue()
        }

        addSource(lb) {
            lastB = it
            assignValue()
        }

        addSource(lc) {
            lastC = it
            assignValue()
        }

        addSource(ld) {
            lastD = it
            assignValue()
        }

        addSource(le) {
            lastE = it
            assignValue()
        }
    }
}

fun <A, B, C, D, E, F> combineLatest(la: LiveData<A>, lb: LiveData<B>, lc: LiveData<C>, ld: LiveData<D>, le: LiveData<E>, lf: LiveData<F>): LiveData<Sextet<A, B, C, D, E, F>> {
    return MediatorLiveData<Sextet<A, B, C, D, E, F>>().apply {
        var lastA: A? = null
        var lastB: B? = null
        var lastC: C? = null
        var lastD: D? = null
        var lastE: E? = null
        var lastF: F? = null

        val assignValue = {
            val a = lastA
            val b = lastB
            val c = lastC
            val d = lastD
            val e = lastE
            val f = lastF

            if (a != null && b != null && c != null && d != null && e != null && f != null) {
                value = Sextet(a, b, c, d, e, f)
            }
        }

        addSource(la) {
            lastA = it
            assignValue()
        }

        addSource(lb) {
            lastB = it
            assignValue()
        }

        addSource(lc) {
            lastC = it
            assignValue()
        }

        addSource(ld) {
            lastD = it
            assignValue()
        }

        addSource(le) {
            lastE = it
            assignValue()
        }

        addSource(lf) {
            lastF = it
            assignValue()
        }
    }
}