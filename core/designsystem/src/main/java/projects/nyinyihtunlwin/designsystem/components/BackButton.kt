package projects.nyinyihtunlwin.designsystem.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import projects.nyinyihtunlwin.designsystem.R
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
    tint: Color? = null
) {

    ActionButton(
        modifier = modifier,
        onActionPress = onBackPress,
        actionIcon = { R.drawable.ic_back },
        tint = tint ?: LocalContentColor.current
    )
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    onActionPress: () -> Unit,
    actionIcon: () -> Int,
    tint: Color? = null
) {

    IconButton(
        modifier = modifier,
        onClick = onActionPress
    ) {
        Icon(
            painter = painterResource(id = actionIcon()),
            contentDescription = "actionIcon",
            tint = tint ?: LocalContentColor.current
        )
    }
}

@Preview
@Composable
fun BackButtonPreview() {
    DiyRecipesTheme {
        BackButton(onBackPress = {})
    }
}