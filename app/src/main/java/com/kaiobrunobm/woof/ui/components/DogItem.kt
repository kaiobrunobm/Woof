package com.kaiobrunobm.woof.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.kaiobrunobm.woof.R
import com.kaiobrunobm.woof.data.Dog

@Composable
fun DogItem(
    dog: Dog,
    modifier: Modifier = Modifier,
) {
    var extended by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue = if (extended) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer
    )

    Card(modifier = modifier) {

        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium
                )
            )
                .background(color = color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))

            ) {

                DogIcon(
                    dogIcon = dog.imgRes
                )
                DogInformation(dogName = dog.name, dogAge = dog.age)
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                DogItemButton(
                    extended = extended, onClick = { extended = !extended })
            }
            if (extended) {
                DogDescription(
                    dogDescription = dog.description, modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }

    }
}

@Composable
private fun DogItemButton(
    extended: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick, extended = extended, modifier = modifier
    )
}

@Composable
fun IconButton(
    onClick: () -> Unit,
    extended: Boolean,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick, content = {
            Icon(
                imageVector = if (extended) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = stringResource(R.string.expand_button_content_description),
                tint = MaterialTheme.colorScheme.secondary,
            )
        })
}

@Composable
private fun DogDescription(
    @StringRes dogDescription: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.about), style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(dogDescription), style = MaterialTheme.typography.bodyLarge
        )
    }

}


