package org.cartify.ecommerce.presentation.utils

import java.time.LocalDateTime

data class BasicApiResponse<T>(
    val message: String,
    val statusCode: Int,
    val isSuccess: Boolean,
    val timestamp: LocalDateTime,
    val data: T
)