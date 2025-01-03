package org.cartify.ecommerce.feature_profile.data.remote

import org.cartify.ecommerce.feature_profile.data.remote.request.RegisterUserRequest
import org.cartify.ecommerce.presentation.utils.BasicApiResponse

interface AuthApi {
    suspend fun sendOtp(userLoginType:String,type:String)
    suspend fun login(userLoginType:String, password:String, type:String):BasicApiResponse<Boolean>
    suspend fun register(request: RegisterUserRequest):BasicApiResponse<Boolean>
    suspend fun updatePassword(request: RegisterUserRequest):BasicApiResponse<Boolean>
}