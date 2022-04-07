package org.asdtm.goodweather.common.base

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.asdtm.goodweather.R
import org.asdtm.goodweather.common.views.DialogLoading
import timber.log.Timber

/**
 * This abstract [BaseActivity] class defines all the common behaviour of all the activities
 * of this application and this abstract class,
 * have abstract methods for the differences,
 * which can be overridden in the actual implementations.
 * Also this makes implementing [ViewDataBinding] a lot easier.
 */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: B

    @get:LayoutRes
    protected abstract val layout: Int

    /**
     * Floating loading indicator
     */
    protected val loading: DialogLoading by lazy(mode = LazyThreadSafetyMode.NONE) {
        DialogLoading(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // makes sure the activity transition is like the iOS
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        binding = DataBindingUtil.setContentView(this, layout)
        binding.lifecycleOwner = this
    }

    /**
     * Hides the keyboard
     */
    open fun hideKeyboardFrom() {
        try {
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    /**
     * If the activity stops or goes into background
     * loading should be stopped
     */
    override fun onStop() {
        if (loading.isShowing) {
            loading.dismiss()
        }
        super.onStop()
    }

    /**
     * If activity destroyed, dismiss the loading
     */
    override fun onDestroy() {
        if (loading.isShowing) {
            loading.dismiss()
        }
        super.onDestroy()

    }


}