package projects.nyinyihtunlwin.diyrecipes.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import projects.nyinyihtunlwin.designsystem.components.SpacerVer
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.designsystem.theme.Light_Orange
import projects.nyinyihtunlwin.designsystem.theme.LocalSpacing
import projects.nyinyihtunlwin.designsystem.theme.Mix_White
import projects.nyinyihtunlwin.designsystem.theme.Orange
import projects.nyinyihtunlwin.diyrecipes.R
import projects.nyinyihtunlwin.diyrecipes.destinations.destinations.CocktailListDestination
import projects.nyinyihtunlwin.diyrecipes.destinations.destinations.MealCategoryListDestination

enum class BottomBarNavigationItem(
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val label: String,
) {
    Meals(
        MealCategoryListDestination.route,
        R.drawable.ic_meal_selected,
        R.drawable.ic_meal_un_selected,
        "Meals",
    ),
    Cocktails(
        CocktailListDestination.route,
        R.drawable.ic_cocktail_selected,
        R.drawable.ic_cocktail_un_selected,
        "Cocktails",
    )
}

@Composable
fun DiyRecipesBottomNavigation(
    navController: NavController
) {
    val bottomBarPadding = WindowInsets.navigationBars
        .asPaddingValues(LocalDensity.current)
        .calculateBottomPadding() / 2
    Column(
        modifier = Modifier
            .background(Mix_White)
    ) {
        Divider()
        NavigationBar(
            containerColor = Mix_White,
            tonalElevation = 0.dp,
            windowInsets = WindowInsets(bottom = bottomBarPadding),
            modifier = Modifier.padding(horizontal = LocalSpacing.current.spaceXXTiny)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            BottomBarNavigationItem.entries.forEach { item ->
                val selected = currentRoute == item.route
                DiyRecipesNavigationBarItem(
                    modifier = Modifier.weight(1f),
                    selected = selected,
                    icon = if (selected) item.selectedIcon else item.unselectedIcon,
                    label = item.label,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun DiyRecipesNavigationBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    icon: Int,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(
                top = LocalSpacing.current.spaceTiny,
                bottom = LocalSpacing.current.spaceExtraTiny
            )
            .clickable { onClick() }
    ) {
        val color =
            if (selected)
                Orange
            else Light_Orange
        Image(
            painter = painterResource(id = icon), contentDescription = label,
            modifier = Modifier.size(28.dp)
        )
        SpacerVer(height = LocalSpacing.current.spaceTiny)
        Text(
            text = label, color = color, style = MaterialTheme.typography.labelSmall,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

class PreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(
            true,
            false
        )

}

@Preview
@Composable
private fun DiyRecipesNavigationBarItemPreview(
    @PreviewParameter(projects.nyinyihtunlwin.diyrecipes.navigation.PreviewParameterProvider::class) selected: Boolean
) {
    DiyRecipesTheme {
        DiyRecipesNavigationBarItem(
            selected = selected,
            icon = if (selected) R.drawable.ic_meal_selected else R.drawable.ic_meal_un_selected,
            label = "Meals",
            onClick = {}
        )
    }
}