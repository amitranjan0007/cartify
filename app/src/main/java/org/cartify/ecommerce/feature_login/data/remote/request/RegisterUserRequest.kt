package org.cartify.ecommerce.feature_profile.data.remote.request

data class RegisterUserRequest(
    val fName:String,
    val lName:String,
    val password:String,
    val mobileNmbr:String,
    val emailId:String,
    val deviceId: String? = null,
    val deviceModel: String? = null,
    val osVersion: String? = null,
    val deviceManufacturer: String? = null,
    val notificationToken: String? = null,
    val isDeviceRooted:Boolean = false,
    val addresses: List<AddressDetails>? = null

)

data class AddressDetails(
    val cityName: String? = null,
    val stateName: String? = null,
    val countryName: String? = null,
    val lat:Double = 0.0,
    val lng:Double = 0.0
)

