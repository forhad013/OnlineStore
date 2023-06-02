package com.greenrobotdev.onlinestore.screen.productList

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.greenrobotdev.onlinestore.screen.home.StoryHomeScreen
import io.github.xxfast.decompose.router.rememberViewModel

@Composable
fun ProductListScreen(
    onProductSelect : ()-> Unit
){

    val viewModel: ProductListViewModel = rememberViewModel(ProductListViewModel::class){
        savedState -> ProductListViewModel(savedState)
    }

    val state: ProductListState by viewModel.states.collectAsState()

    println("sonna ${state.toString()}")
    Button(
        onClick = onProductSelect,
    ){
        Text(state.toString())
    }


}