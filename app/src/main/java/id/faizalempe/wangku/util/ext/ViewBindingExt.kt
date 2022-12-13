package id.faizalempe.wangku.util.ext

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version ViewBindingExt, v 0.1 13/12/22 17.32 by Faizal Muhammad Priyowivowo
 */

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { getViewBind(bindingInflater) }

inline fun <T : ViewBinding> Fragment.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { getViewBind(bindingInflater) }

inline fun <T : ViewBinding> AppCompatActivity.getViewBind(
    crossinline bindingInflater: (LayoutInflater) -> T
) = bindingInflater.invoke(layoutInflater)

inline fun <T : ViewBinding> Fragment.getViewBind(
    crossinline bindingInflater: (LayoutInflater) -> T
) = bindingInflater.invoke(layoutInflater)