@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package projects.nyinyihtunlwin.cocktails.presentation.screens.listing

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicatorDefaults
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.launch
import projects.nyinyihtunlwin.cocktails.presentation.model.DrinkUiModel
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesCard
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesTab
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesTabRow
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesToolbar
import projects.nyinyihtunlwin.designsystem.components.TAB_MODE
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.designsystem.theme.Gray_50
import projects.nyinyihtunlwin.designsystem.theme.Light_Orange
import projects.nyinyihtunlwin.designsystem.theme.LocalSpacing
import projects.nyinyihtunlwin.designsystem.theme.Mix_White
import projects.nyinyihtunlwin.designsystem.theme.Orange

@Composable
fun CocktailListScreen(
    onEvent: (CocktailListEvent) -> Unit = {}
) {
    val viewModel: CocktailListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit){
        viewModel.getCocktails(isAlcoholic = true)
        viewModel.getCocktails(isAlcoholic = false)
    }

    CocktailListContent(
        alcoholicCocktails = uiState.alcoholicCocktails,
        nonAlcoholicCocktails = uiState.nonAlcoholicCocktails,
        isRefreshing = uiState.loading,
        onRefresh = { isAlcoholic ->
            viewModel.getCocktails(isAlcoholic)
        },
        onCocktailSelected = {

        }
    )
}

@Composable
fun CocktailListContent(
    alcoholicCocktails: List<DrinkUiModel>,
    nonAlcoholicCocktails: List<DrinkUiModel>,
    isRefreshing: Boolean,
    onRefresh: (Boolean) -> Unit,
    onCocktailSelected: (DrinkUiModel) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

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
            HorizontalPager(state = pagerStateTabs) { page ->
                CocktailList(
                    cocktails = if (page == 0) alcoholicCocktails else nonAlcoholicCocktails,
                    isRefreshing = isRefreshing,
                    onRefresh = {
                        onRefresh.invoke(page == 0)
                    },
                    onCocktailSelected = onCocktailSelected
                )
            }
        }
    }
}

@Composable
fun CocktailList(
    cocktails: List<DrinkUiModel>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onCocktailSelected: (DrinkUiModel) -> Unit
) {
    val state = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
        onRefresh.invoke()
    })
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pullRefresh(state),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.spaceXSmall),
            verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.spaceXSmall),
            contentPadding = PaddingValues(
                bottom = 100.dp,
                top = LocalSpacing.current.spaceNormal,
                start = LocalSpacing.current.spaceNormal,
                end = LocalSpacing.current.spaceNormal
            )
        ) {
            items(items = cocktails) { data ->
                CocktailItem(data = data, onCocktailSelected = onCocktailSelected)
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = state,
            modifier = Modifier
                .align(Alignment.TopCenter),
            colors = PullRefreshIndicatorDefaults.colors(
                contentColor = Orange,
                containerColor = Mix_White
            )
        )
    }
}

@Composable
fun CocktailItem(
    data: DrinkUiModel,
    onCocktailSelected: (DrinkUiModel) -> Unit
) {
    DiyRecipesCard(
        onClick = {
            onCocktailSelected.invoke(data)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = data.strDrinkThumb,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = data.strDrink,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Gray_50)
                    .padding(LocalSpacing.current.spaceSmall)
                    .align(Alignment.BottomStart),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                color = Light_Orange,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CocktailListContentPreview() {
    DiyRecipesTheme {
        CocktailListContent(
            nonAlcoholicCocktails = emptyList(),
            alcoholicCocktails = emptyList(),
            isRefreshing = false,
            onRefresh = {},
            onCocktailSelected = {}
        )
    }
}