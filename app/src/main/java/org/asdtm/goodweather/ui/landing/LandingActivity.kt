package org.asdtm.goodweather.ui.landing

import dagger.hilt.android.AndroidEntryPoint
import org.asdtm.goodweather.R
import org.asdtm.goodweather.common.base.BaseActivity
import org.asdtm.goodweather.databinding.ActivityLandingBinding

@AndroidEntryPoint
class LandingActivity : BaseActivity<ActivityLandingBinding>() {
    override val layout: Int
        get() = R.layout.activity_landing
}