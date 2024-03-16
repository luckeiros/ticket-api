package com.luckeiros.ticketandroid.feature.event.presentation.view

import android.os.Bundle
import com.luckeiros.ticketandroid.base.BaseActivity

class EventActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        openFragment(EventFragment(), true)
    }
}