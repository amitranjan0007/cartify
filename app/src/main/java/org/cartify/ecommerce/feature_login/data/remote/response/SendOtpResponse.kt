package org.cartify.ecommerce.feature_profile.data.remote.response

import org.cartify.ecommerce.common.Constants

data class SendOtpResponse(
    val message:String,
  //  val status:OtpStatus,
    val otpReferenceId:String
)

//enum class OtpStatus {
//    NOTEXIST(Constants.OTP_NOT_EXIST),
//    EXPIRED(Constants.OTP_EXPIRED),
//    VERIFIED(Constants.VERIFIED_OTP),
//    INVALID(Constants.INVALID_OTP),
//    SENT_SUCCESS(Constants.OTP_SUCCESS_MSG),
//    FAILED(Constants.OTP_FAILED_MSG),
//    OTP_ALREADY_GENERATED(Constants.OTP_ALREADY_GENERATED);
//
//    private val message: String? = null
//}