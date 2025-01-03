package org.cartify.ecommerce.presentation.utils


typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data:T?=null,val uiText: UiText?=null) {
     class SUCCESS<T>(data:T) : Resource<T>(data)
     class ERROR<T>(data: T?,uiText:UiText?):Resource<T>(data,uiText)
}