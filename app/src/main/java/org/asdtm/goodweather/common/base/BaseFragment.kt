package org.asdtm.goodweather.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.asdtm.goodweather.common.views.DialogLoading
import timber.log.Timber

/**
 * This abstract [BaseFragment] class defines all the common behaviour of all the fragments
 * of this application and this abstract class,
 * have abstract methods for the differences,
 * which can be overridden in the actual implementations.
 * Also this makes implementing [ViewDataBinding] a lot easier.
 */
abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    // by making binding null, we are making sure that, it is not leaking
    private var _binding: B? = null
    val binding
        get() = _binding!!

    /**
     * Floating loading indicator
     */
    protected val loading: DialogLoading by lazy(mode = LazyThreadSafetyMode.NONE) {
        DialogLoading(requireActivity())
    }


    @get:LayoutRes
    protected abstract val layout: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate<B>(inflater, layout, container, false)
                .also { currentBinding ->
                    currentBinding.lifecycleOwner = this.viewLifecycleOwner
                }

        return binding.root
    }

    /**
     * Hides the soft keyboard
     */
    protected fun hideKeyboardFrom() {
        try {
            val imm: InputMethodManager? =
                activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(binding.root.windowToken, 0)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }


    override fun onPause() {
        if (loading.isShowing) loading.dismiss()
        super.onPause()
    }

    override fun onDestroy() {
        if (loading.isShowing) loading.dismiss()
        super.onDestroy()
    }

    override fun onDestroyView() {
        if (loading.isShowing) loading.dismiss()
        hideKeyboardFrom()
        super.onDestroyView()
        _binding = null
    }
}