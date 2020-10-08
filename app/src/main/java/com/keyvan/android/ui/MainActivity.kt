package com.keyvan.android.ui

import android.os.Bundle
import com.keyvan.android.R
import com.keyvan.android.utils.baseClasses.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}