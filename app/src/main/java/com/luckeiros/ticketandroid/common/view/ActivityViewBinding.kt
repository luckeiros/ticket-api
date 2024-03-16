package com.luckeiros.ticketandroid.common.view

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> AppCompatActivity.viewBinding(
    noinline viewBindingFactory: (LayoutInflater) -> T
) = ActivityViewBindingDelegate(this, viewBindingFactory)

class ActivityViewBindingDelegate<T : ViewBinding>(
    private val activity: AppCompatActivity,
    val viewBindingFactory: (LayoutInflater) -> T
) {
    private var binding: T? = null

    init {
        activity.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                binding = null
            }
        })
    }

    operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        val binding = binding

        binding?.let { return it }

        val lifecycle = activity.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Activity views are destroyed.")
        }

        return viewBindingFactory(activity.layoutInflater).also { this.binding = it }
    }
}
