@file:OptIn(ExperimentalFoundationApi::class)

package projects.nyinyihtunlwin.cocktails.presentation.screens.listing

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesTab
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesTabRow
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesToolbar
import projects.nyinyihtunlwin.designsystem.components.SpacerVer
import projects.nyinyihtunlwin.designsystem.components.TAB_MODE
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.designsystem.theme.LocalSpacing

@Composable
fun CocktailListScreen(
    onEvent: (CocktailListEvent) -> Unit = {}
) {
    CocktailListContent()
}

@Composable
fun CocktailListContent() {
    val spacing = LocalSpacing.current
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { 2 }
    var currentPage by remember {
        mutableIntStateOf(0)
    }

    val tabs = listOf(
        DiyRecipesTab("Alcoholic"),
        DiyRecipesTab("Non-alcoholic")
    )
    var selectedTab by remember { mutableIntStateOf(0) }
    val pagerStateTabs = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0F
    ) {
        tabs.size
    }
    selectedTab = pagerStateTabs.currentPage

    Scaffold(
        topBar = {
            DiyRecipesToolbar(title = "DIY Recipes", subTitle = "Cocktails")
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            DiyRecipesTabRow(
                tabs = tabs,
                selectedTabIndex = selectedTab,
                onTabClick = {
                    selectedTab = it
                    coroutineScope.launch {
                        pagerStateTabs.animateScrollToPage(it)
                    }
                },
                tabMode = TAB_MODE.FILLED,
            )
            SpacerVer(height = spacing.spaceMedium)
            HorizontalPager(state = pagerStateTabs) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CocktailListContentPreview() {
    DiyRecipesTheme {
        CocktailListContent()
    }
}