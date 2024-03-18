package com.example.amphibians.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.data.AmphibiansDetailRepository
import com.example.amphibians.modul.Amphibian
import com.example.amphibians.ui.theme.AmphibianApplication
import kotlinx.coroutines.launch

sealed interface Stateui{
    data class sucsess(val Amphibians: List<Amphibian>): Stateui
    object Loading: Stateui
    object Error: Stateui
}


class AmphibianVIewModel(
    private val amphibiansDetailRepository: AmphibiansDetailRepository
) :ViewModel(){
    var amphibianStateUi: Stateui by mutableStateOf(Stateui.Loading)
        private set

    init {
        getDetail()
    }
    fun getDetail(){
        viewModelScope.launch {
            amphibianStateUi = Stateui.Loading
            amphibianStateUi =try {
                Stateui.sucsess(
                    amphibiansDetailRepository.getAmphibiansDetail()
                )
            }catch(e:Exception){
                Stateui.Error
            }
        }
    }
    companion object{
        val Factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibianApplication)
                val amphibiansRepository = application.container.amphibiansDetailRepository
                AmphibianVIewModel(amphibiansDetailRepository = amphibiansRepository)
            }
        }
    }
}