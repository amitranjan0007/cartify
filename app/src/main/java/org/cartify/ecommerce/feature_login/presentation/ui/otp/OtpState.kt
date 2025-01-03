package org.cartify.ecommerce.feature_login.presentation.ui.otp

data class OtpState(
    val code: List<Int?> = (1..4).map { null },
    val focussedIndex:Int?=null,
    val isValid:Boolean?=false
)