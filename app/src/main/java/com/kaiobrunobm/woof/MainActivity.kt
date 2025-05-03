package com.kaiobrunobm.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.kaiobrunobm.woof.data.dogs
import com.kaiobrunobm.woof.ui.components.DogItem
import com.kaiobrunobm.woof.ui.components.WoofTopAppBar
import com.kaiobrunobm.woof.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WoofTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WoofApp(

                    )
                }
            }
        }
    }
}

@Composable
fun WoofApp(modifier: Modifier = Modifier) {
    Scaffold (
        topBar = {
            WoofTopAppBar()
        }
    ) {
        LazyColumn(
            contentPadding = it
        ) {
            items(dogs) {
                DogItem(
                    dog = it,
                    modifier = modifier
                        .padding(
                            dimensionResource(R.dimen.padding_small)
                        )
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun WoofPreview() {
    WoofTheme(darkTheme = false) {
        WoofApp()
    }
}
