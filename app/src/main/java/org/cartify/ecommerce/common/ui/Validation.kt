package org.cartify.ecommerce.common.ui

fun String.isMobileNumberValid(): Boolean {
    println("validation---"+this)
    return Regex("^\\+?[0-9]{10}$").matches(this)
}