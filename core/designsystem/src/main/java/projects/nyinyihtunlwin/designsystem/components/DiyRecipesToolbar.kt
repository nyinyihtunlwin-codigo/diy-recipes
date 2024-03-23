package projects.nyinyihtunlwin.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import projects.nyinyihtunlwin.designsystem.R
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.designsystem.theme.LocalSpacing
import projects.nyinyihtunlwin.designsystem.theme.Orange
import projects.nyinyihtunlwin.designsystem.theme.Mix_White

@Composable
fun DiyRecipesToolbar(
    modifier: Modifier = Modifier,
    title: String = "",
    containerColor: @Composable () -> Color = { Mix_White },
) {
    val spacing = LocalSpacing.current
    Surface(
        color = containerColor()
    ) {
        Column {
            ConstraintLayout(
                modifier = modifier
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(horizontal = spacing.spaceMedium)
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                val (
                    icon,
                    titleText
                ) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.ic_app),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp).constrainAs(icon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom,spacing.spaceXTiny)
                    },
                    colorFilter = ColorFilter.tint(Orange)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.constrainAs(titleText) {
                        start.linkTo(icon.end, spacing.spaceSmall)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = Orange
                )
            }
        }
    }

}


@Preview
@Composable
fun DiyRecipesToolbarPreview() {
    DiyRecipesTheme {
        Column {
            DiyRecipesToolbar(title = "DIY Recipes")
        }
    }
}