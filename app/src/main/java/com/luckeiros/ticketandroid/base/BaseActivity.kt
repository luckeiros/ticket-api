package com.luckeiros.ticketandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.luckeiros.ticketandroid.R
import com.luckeiros.ticketandroid.common.view.gone
import com.luckeiros.ticketandroid.common.view.viewBinding
import com.luckeiros.ticketandroid.common.view.visible
import com.luckeiros.ticketandroid.databinding.ActivityBaseBinding

abstract class BaseActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityBaseBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun openFragment(fragment: Fragment, clearStack: Boolean = false) {
        if (clearStack) this.supportFragmentManager.popBackStackImmediate(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

        this.supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    fun loader() = binding.pbBase.visible()

    fun dismissLoader() = binding.pbBase.gone()
}