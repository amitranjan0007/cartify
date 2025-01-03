package org.cartify.ecommerce.feature_login.presentation.ui.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.cartify.ecommerce.R
import org.cartify.ecommerce.feature_login.presentation.ui.otp.viewmodel.OtpViewModel
import org.cartify.ecommerce.presentation.ui.theme.SpaceMedium
import org.cartify.ecommerce.presentation.ui.theme.SpaceSmall


@Composable
fun OtpScreen(
    modifier: Modifier=Modifier,
    viewModel: OtpViewModel = hiltViewModel()
){
    val focusRequester = remember {
         List(4){
            FocusRequester()
        }
    }
    val state by viewModel._otpState.collectAsState()
    val focusManager = LocalFocusManager.current
    val keyBoardManager = LocalSoftwareKeyboardController.current

    LaunchedEffect(state.code) {
        if (state.focussedIndex != null && state.focussedIndex!!.toInt()<=3)
            state.focussedIndex?.let {idx->
            focusRequester.getOrNull(idx)?.requestFocus()
       }
    }

    LaunchedEffect(state.code,keyBoardManager) {
        val allNumberEntered =  state.code.none{it==null}
        if(allNumberEntered){
            focusRequester.forEach { it.freeFocus() }
            focusManager.clearFocus()
            keyBoardManager?.hide()
        }
    }
    OtpScreenLayout(
        state=state,
        onAction = {action->
            when(action){
                is OtpAction.onEnteredNumber->{
                    if (action.number!=null){
                        focusRequester[action.focusedIdx].freeFocus()

                    }
                }
                else->Unit
            }
            viewModel.onAction(action)
        },
        focusRequesters= focusRequester,
        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun OtpScreenLayout(
    state: OtpState,
    focusRequesters: List<FocusRequester>,
    onAction:(OtpAction)->Unit,

    modifier: Modifier
){

   Column(
       modifier=modifier.fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center
   ) {
       Text(
           text = stringResource(id = R.string.enter_your_otp),
           textAlign = TextAlign.Center,
           style = MaterialTheme.typography.titleMedium,
       )
       Box(modifier = Modifier.height(SpaceSmall) )
       Row(
           modifier=Modifier.padding(SpaceMedium),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.spacedBy(8.dp,Alignment.CenterHorizontally)
       ) {
           state.code.forEachIndexed { idx,number->
               OtpInputField(
                   number =number,
                   focusRequester = focusRequesters[idx],
                   onFocusChanged = {isFocussed->
                       if (isFocussed){
                           onAction(OtpAction.onChangeFieldFocused(idx))
                       }
                   },
                   onKeyboardBack = {
                      onAction(OtpAction.onBackButtonPressed)
                   },
                   onNumberChanged = { value->
                     onAction(OtpAction.onEnteredNumber(value,idx))
                   },
                   modifier = Modifier
                       .weight(1f)
                       .aspectRatio(1f)

               )
           }
       }
       state.isValid?.let { isValid->

           Text(text = if(isValid) stringResource(id = R.string.valid_otp) else stringResource(id = R.string.invalid_otp) , color =  if(isValid) Color.Green else Color.Red)
       }

   }
}