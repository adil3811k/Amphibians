package com.example.amphibians.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(
    stateui: Stateui,
    modifier: Modifier,
    Retry:()->Unit
) {
    when(stateui){
        is Stateui.Loading -> LoadingScreen(modifier = modifier)
        is Stateui.sucsess -> ListOfAmphibians(modifier = modifier, amphibians =stateui.Amphibians)
        is Stateui.Error ->ErrorScreen(Retry =Retry ,modifier = modifier)
    }
}

@Composable
fun AmphibiamAPp() {
    val amphibianVIewModel: AmphibianVIewModel = viewModel(factory = AmphibianVIewModel.Factory)
    HomeScreen(stateui = amphibianVIewModel.amphibianStateUi, modifier = Modifier, Retry = amphibianVIewModel::getDetail)
}

