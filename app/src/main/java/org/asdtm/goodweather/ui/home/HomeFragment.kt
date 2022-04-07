package org.asdtm.goodweather.ui.home

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import org.asdtm.goodweather.R
import org.asdtm.goodweather.common.base.BaseFragment
import org.asdtm.goodweather.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layout: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}