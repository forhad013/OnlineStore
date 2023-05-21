package com.greenrobotdev.onlinestore.screen.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import io.github.xxfast.decompose.router.Router
import io.github.xxfast.decompose.router.content.RoutedContent
import io.github.xxfast.decompose.router.rememberRouter
import com.greenrobotdev.onlinestore.screen.home.StoryHomeScreen.*
import com.greenrobotdev.onlinestore.screen.productDetails.ProductDetails
import com.greenrobotdev.onlinestore.screen.productList.ProductListScreen

//class Greeting {
//    private val platform: Platform = getPlatform()
//
//    fun greet(): String {
//        return "Hello, ${platform.name}!"
//    }
//}
//
//@Composable
//fun App(){
//   HomeScreen()
//}

//
//@Composable
//internal fun HomeScreen(){
//    Text("home")
//}
//
//
//@Composable
//fun App(){
//    HomeScreen()
//}


@Composable
fun HomeScreen() {
    val router: Router<StoryHomeScreen> = rememberRouter(StoryHomeScreen::class, listOf(StoryHomeScreen.List))

    RoutedContent(
        router = router,
        animation = stackAnimation(slide())
    ) { screen ->
        when(screen){
            List -> ProductListScreen(
                onProductSelect = {router.push(Details)}
            )

            is Details -> ProductDetails()
        }
    }
}

