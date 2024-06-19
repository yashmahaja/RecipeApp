package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.reciepeapp.Category
import com.example.reciepeapp.MainViewModel
import com.example.reciepeapp.RecipeScreen

@Composable

fun RecipeApp(){

    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        composable(Screen.RecipeScreen.route){
           RecipeScreen(viewState = viewstate, navigateToDetail = {
               navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
               navController.navigate(Screen.DetailScreen.route)
           })
        }

        composable(Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?: 
            Category("","","","")
            
            CategoryDetalScreen(category = category)
            
        }

        }
    }
