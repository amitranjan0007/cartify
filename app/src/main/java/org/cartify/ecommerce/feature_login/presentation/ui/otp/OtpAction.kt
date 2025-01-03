package org.cartify.ecommerce.feature_login.presentation.ui.otp

sealed class OtpAction {
    data class onEnteredNumber(val number:Int?,val focusedIdx:Int): OtpAction()
    data class onChangeFieldFocused(val currentFocussedIdx:Int): OtpAction()
    data object onBackButtonPressed: OtpAction()
}