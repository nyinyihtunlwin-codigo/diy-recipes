@file:OptIn(ExperimentalMaterial3Api::class)

package projects.nyinyihtunlwin.meals.presentation.screens.category

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.designsystem.theme.LocalSpacing
import projects.nyinyihtunlwin.designsystem.theme.Mix_White
import projects.nyinyihtunlwin.designsystem.theme.Mix_White_50
import projects.nyinyihtunlwin.designsystem.theme.Orange
import projects.nyinyihtunlwin.meals.presentation.model.MealCategoryUiModel

@Composable
fun MealCategoryListScreen(
    onEvent: (MealCategoryListEvent) -> Unit = {}
) {
    val viewModel: MealCategoryListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    MealCategoryListContent(
        categories = uiState.categories,
        isRefreshing = uiState.loading,
        onRefresh = {
            viewModel.getMealCategories()
        },
        onCategorySelected = {
            onEvent(
                MealCategoryListEvent.MealList(
                    categoryId = it.idCategory,
                    categoryName = it.strCategory
                )
            )
        })
}

@Composable
fun MealCategoryListContent(
    categories: List<MealCategoryUiModel>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onCategorySelected: (MealCategoryUiModel) -> Unit,
) {
    val state = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
        onRefresh.invoke()
    })

    Scaffold(
        topBar = {
            DiyRecipesToolbar(title = "DIY Recipes", subTitle = "Meals")
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
                    bottom = 100.dp,
                    top = LocalSpacing.current.spaceNormal,
                    start = LocalSpacing.current.spaceNormal,
                    end = LocalSpacing.current.spaceNormal
                )
            ) {
                items(items = categories) { data ->
                    MealCategoryItem(data = data, onCategorySelected = onCategorySelected)
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
fun MealCategoryItem(
    data: MealCategoryUiModel,
    onCategorySelected: (MealCategoryUiModel) -> Unit
) {
    DiyRecipesCard(
        onClick = {
            onCategorySelected.invoke(data)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = LocalSpacing.current.spaceMedium),
                model = data.strCategoryThumb,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Text(
                text = data.strCategory,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        end = LocalSpacing.current.spaceSmall,
                        bottom = LocalSpacing.current.spaceSmall
                    )
                    .clip(shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                    .background(Mix_White_50)
                    .padding(LocalSpacing.current.spaceSmall)
                    .align(Alignment.BottomStart),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                color = Orange,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealCategoryListContentPreview() {
    DiyRecipesTheme {
        MealCategoryListContent(
            categories = emptyList(),
            isRefreshing = false,
            onRefresh = {},
            onCategorySelected = {})
    }
}