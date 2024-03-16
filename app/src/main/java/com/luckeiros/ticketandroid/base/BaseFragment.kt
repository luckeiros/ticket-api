package com.luckeiros.ticketandroid.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected fun showLoader(){
        (activity as? BaseActivity)?.loader()
    }

    protected fun hideLoader(){
        (activity as? BaseActivity)?.dismissLoader()
    }
}