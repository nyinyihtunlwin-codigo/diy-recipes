@file:OptIn(ExperimentalMaterial3Api::class)

package projects.nyinyihtunlwin.meals.presentation.screens.listing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesCard
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesToolbar
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesToolbarWithAction
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.designsystem.theme.Gray_50
import projects.nyinyihtunlwin.designsystem.theme.Light_Orange
import projects.nyinyihtunlwin.designsystem.theme.LocalSpacing
import projects.nyinyihtunlwin.designsystem.theme.Mix_White
import projects.nyinyihtunlwin.designsystem.theme.Mix_White_50
import projects.nyinyihtunlwin.designsystem.theme.Orange
import projects.nyinyihtunlwin.meals.presentation.model.MealUiModel

@Composable
fun MealListScreen(
    categoryName: String,
    onEvent: (MealListEvent) -> Unit = {}
) {
    val viewModel: MealListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = { viewModel.getMealListByCategory(categoryName) })

    MealListContent(
        categoryName = categoryName,
        meals = uiState.meals,
        isRefreshing = uiState.loading,
        onRefresh = {
            viewModel.getMealListByCategory(categoryName)
        },
        onMealSelected = {
            onEvent(MealListEvent.Details(it.idMeal))
        },
        onBackPressed = {
            onEvent(MealListEvent.Exit)
        }
    )
}

@Composable
fun MealListContent(
    categoryName: String,
    meals: List<MealUiModel>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onMealSelected: (MealUiModel) -> Unit,
    onBackPressed: () -> Unit,
) {
    val state = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
        onRefresh.invoke()
    })

    Scaffold(
        topBar = {
            DiyRecipesToolbarWithAction(title = categoryName, onBackPress = onBackPressed)
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .pullRefresh(state),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.spaceXSmall),
                verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.spaceXSmall),
                contentPadding = PaddingValues(
                    bottom = LocalSpacing.current.spaceNormal,
                    top = LocalSpacing.current.spaceNormal,
                    start = LocalSpacing.current.spaceNormal,
                    end = LocalSpacing.current.spaceNormal
                )
            ) {
                items(items = meals) { data ->
                    MealItem(data = data, onMealSelected = onMealSelected)
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
}

@Composable
fun MealItem(
    data: MealUiModel,
    onMealSelected: (MealUiModel) -> Unit
) {
    DiyRecipesCard(
        onClick = {
            onMealSelected.invoke(data)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = data.strMealThumb,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = data.strMeal,
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
fun MealListContentPreview() {
    DiyRecipesTheme {
        MealListContent(
            categoryName = "Beef",
            meals = emptyList(),
            isRefreshing = false,
            onRefresh = {},
            onMealSelected = {},
            onBackPressed = {}
        )
    }
}