package com.greenrobotdev.onlinestore.screen.productDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.greenrobotdev.onlinestore.domain.entity.Product
import com.greenrobotdev.onlinestore.utils.navigationBarPadding
import com.seiko.imageloader.rememberAsyncImagePainter
import io.github.xxfast.decompose.router.rememberViewModel

@Composable
fun ProductDetailsScreen(
    product: Product,
    onBack: () -> Unit,
) {

    val viewModel: ProductDetailsViewModel =
        rememberViewModel(ProductDetailsViewModel::class) { savedState ->
            ProductDetailsViewModel(savedState, product)
        }

    val state: ProductDetailsState by viewModel.states.collectAsState()

    ProductDetailsView(
        onBack = onBack,
        product = state.product,
        onFavoritePressed = { viewModel.onFavoriteButtonPressed() },
        onAddToCartPressed = { viewModel.onAddToCartPressed() },
        isSaved = state.isSaved,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsView(
    product: Product?,
    onBack: () -> Unit,
    onFavoritePressed: () -> Unit,
    onAddToCartPressed: () -> Unit,
    isSaved: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Rounded.ArrowBack, contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBarPadding),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {

                    IconButton(
                        onClick = onFavoritePressed,
                    ) {
                        Icon(
                            imageVector = if (isSaved) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = null
                        )
                    }

                    Button(
                        onClick = onAddToCartPressed,
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ShoppingCart,
                            contentDescription = null
                        )
                        Text("Add to cart")
                    }

                }
            }
        }
    ) { scaffoldPadding ->
        Box(
            modifier = Modifier.padding(scaffoldPadding).wrapContentSize()
        ) {
            if (product != null) ProductDetails(product)
        }
    }
}

@Composable
fun ProductDetails(
    product: Product
) {
    Column {
        Image(
            painter = rememberAsyncImagePainter(product.image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .clip(CircleShape)
                .padding(14.dp)
                .size(180.dp)
        )

        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            val price = "${'$'}${product.price.toInt()}"
            Text(
                text = price,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge,
            )

            Card(
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier.padding(2.dp).wrapContentSize(),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Text(
                    text = product.category,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(6.dp)
                )
            }

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
