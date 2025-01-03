package org.cartify.ecommerce.presentation.utils

import androidx.annotation.StringRes
import org.cartify.ecommerce.R

sealed class UiText {
    data class DynamicString(val value:String):UiText()
    data class StringResources(@StringRes val id:Int):UiText()
    companion object{
        fun unKnownError():UiText{
            return StringResources(R.string.unkown_error)
        }
    }
}