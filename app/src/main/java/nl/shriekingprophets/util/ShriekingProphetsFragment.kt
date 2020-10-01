package nl.shriekingprophets.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class ShriekingProphetsFragment<T : ViewBinding> : Fragment() {
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private var _binding: T? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val layoutInflater = container?.context.let { LayoutInflater.from(it) } ?: inflater
        val b = bindLayout(layoutInflater)
        _binding = b
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialized(binding)
    }

    abstract fun bindLayout(layoutInflater: LayoutInflater): T

    abstract fun initialized(binding: T)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}