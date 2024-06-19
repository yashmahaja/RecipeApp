package com.example.reciepeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.recipeService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categorieState = mutableStateOf(ReciepeState())
    val categoriesState: State<ReciepeState> = _categorieState

    init {
        fetchCategories()
    }
    private fun fetchCategories(){
        viewModelScope.launch {
            try{

                val response = recipeService.getCategories()
                _categorieState.value = _categorieState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )

            }catch (e: Exception){
                _categorieState.value = _categorieState.value.copy(
                    loading = false, error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    data class ReciepeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}