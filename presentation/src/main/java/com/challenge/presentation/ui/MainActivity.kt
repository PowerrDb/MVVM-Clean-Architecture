package com.challenge.presentation.ui

import android.os.Bundle
import com.challenge.presentation.R
import com.challenge.presentation.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun getNavControllerId(): Int = R.id.nav_fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
