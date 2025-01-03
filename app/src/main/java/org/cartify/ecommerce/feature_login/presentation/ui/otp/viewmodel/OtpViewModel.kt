package org.cartify.ecommerce.feature_login.presentation.ui.otp.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.cartify.ecommerce.feature_login.presentation.ui.otp.OtpAction
import org.cartify.ecommerce.feature_login.presentation.ui.otp.OtpState
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor() : ViewModel() {
    private val otpState = MutableStateFlow(OtpState())
    val _otpState = otpState.asStateFlow()

    fun onAction(otpAction: OtpAction) {
        when (otpAction) {

            is OtpAction.onEnteredNumber -> {
                enterNumber(otpAction.number, otpAction.focusedIdx)
            }

            is OtpAction.onBackButtonPressed -> {
                val previousIdx = getPreviousFocussedIndex(otpState.value.focussedIndex)
                otpState.update {
                    it.copy(
                        code = it.code.mapIndexed { idx, value ->
                            if (idx == previousIdx) {
                                null
                            } else value
                        },
                        focussedIndex = previousIdx
                    )
                }
            }

            is OtpAction.onChangeFieldFocused -> {
                otpState.update {
                    it.copy(focussedIndex = otpAction.currentFocussedIdx)
                }
            }
        }
    }

    private fun enterNumber(number: Int?, currentIdx: Int) {
        // Access the latest state from otpState, not _otpState
        val currentCode = otpState.value.code

        // Update only the current index in the code list
        val newCode = currentCode.mapIndexed { idx, value ->
            if (idx == currentIdx) {
                number // Replace value at the focused index
            } else {
                value // Retain previous values
            }
        }

        Log.e("Current Code Before Update", currentCode.toString()) // Debugging current code
        Log.e("New Code After Update", newCode.toString()) // Debugging new code

        // Determine if a number was removed
        val wasNumberRemoved = number == null

        // Update the state with the modified code list
        otpState.update {
            it.copy(
                code = newCode,
                focussedIndex = if (wasNumberRemoved || newCode[currentIdx] == null) {
                    it.focussedIndex // Keep the current focus if invalid or removed
                } else {
                    if (currentIdx == 3) {
                        null // If it's the last field, clear focus
                    }else
                    getNextFocussedTextFieldIdx(currentIdx, newCode) // Move to the next index
                },
                isValid = if (newCode.none { it == null }) {
                    newCode.joinToString("") == "1414" // Replace with your OTP validation logic
                } else {
                    null
                }
            )
        }

        Log.e("Updated State", otpState.value.code.toString()) // Debugging the updated state
    }




    private fun getNextFocussedTextFieldIdx(
        currentFocussedIdx: Int?,
        code: List<Int?>
    ): Int? {
        if (currentFocussedIdx == null) return null
        if (currentFocussedIdx == 3) {
            return null
        }
        return getFirstEmptyFieldIdxAfterFocussedId(code, currentFocussedIdx)
    }

    private fun getFirstEmptyFieldIdxAfterFocussedId(
        code: List<Int?>,
        currentFocussedIdx: Int
    ): Int {
        code.forEachIndexed { idx, num ->
            if (idx <= currentFocussedIdx) {
                return@forEachIndexed
            }

            if (num == null) {
                return idx
            }
        }
        return currentFocussedIdx
    }

    private fun getPreviousFocussedIndex(currentIdx: Int?): Int? {
        return currentIdx?.minus(1)?.coerceAtLeast(0)
    }
}
