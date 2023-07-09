package com.greenrobotdev.onlinestore.screen.cart

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.greenrobotdev.onlinestore.domain.entity.CartItem
import com.greenrobotdev.onlinestore.domain.entity.Product
import com.seiko.imageloader.rememberAsyncImagePainter
import io.github.xxfast.decompose.router.rememberViewModel

@Composable
fun CartScreen(
    onBack: () -> Unit,
    onProductSelect: (product: Product) -> Unit
) {
    val viewModel: CartViewModel =
        rememberViewModel(CartViewModel::class) { savedState ->
            CartViewModel(savedState)
        }

    val state: CartState by viewModel.states.collectAsState()

    CartView(
        state = state,
        onProductSelect = onProductSelect,
        onBack = onBack
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartView(
    state: CartState,
    onProductSelect: (product: Product) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping cart") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Rounded.ArrowBack, contentDescription = null
                        )
                    }
                }
            )
        },
        modifier = Modifier
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .scrollable(rememberScrollState(), Orientation.Vertical)
                .fillMaxSize()
                .padding(scaffoldPadding)
        ) {

            if (state.products != nothing) CartProductList(
                products = state.products,
                onProductSelect = onProductSelect
            )

            AnimatedVisibility(
                visible = state.products == nothing,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Such empty")
                }
            }
        }
    }
}

@Composable
fun CartProductList(
    products: List<CartItem>,
    onProductSelect: (product: Product) -> Unit,
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        items(products) { cartItem ->
            CartItem(
                item = cartItem,
                onProductSelect = { onProductSelect(cartItem.product) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun CartItem(
    item: CartItem,
    onProductSelect: (cartItem: CartItem) -> Unit,
) {
    Card(
        onClick = { onProductSelect(item) },
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.padding(8.dp).background(MaterialTheme.colorScheme.tertiaryContainer),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.product.image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .background(Color.White)
                    .size(100.dp)
            )

            Column(
                modifier = Modifier.padding(6.dp),
            ) {
                Text(
                    text = item.product.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Light
                )

                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    val price = "${'$'}${item.product.price.toInt()}"

                    Text(
                        text = price,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth() ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = Modifier.weight(0.5f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CartItemButton(
                            modifier = Modifier
                                .width(40.dp) ,
                            title = "-",
                            onClick = { }
                        )
                        AnimatedContent(targetState = item.quantity){
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .align(Alignment.CenterVertically)
                                    .padding(8.dp) ,
                                textAlign = TextAlign.Center,
                                text = item.quantity.toString()
                            )
                        }

                        CartItemButton( modifier = Modifier
                            .width(40.dp) ,
                            title = "+",
                            onClick = { }
                        )
                    }

                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        )
                    ){
                        Text("Remove")
                    }
                }


            }
        }
    }
}

@Composable
internal fun CartItemButton(
    modifier: Modifier = Modifier,
    title : String,
    onClick: () -> Unit,
){
    TextButton(
        modifier = modifier,
        onClick = {},
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ){
        Text(text= title, fontSize = 22.sp)
    }
}
