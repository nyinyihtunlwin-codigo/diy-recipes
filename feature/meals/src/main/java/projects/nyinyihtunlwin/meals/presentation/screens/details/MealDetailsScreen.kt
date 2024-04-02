package projects.nyinyihtunlwin.meals.presentation.screens.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import projects.nyinyihtunlwin.common.extension.openUrl
import projects.nyinyihtunlwin.designsystem.components.BackButton
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesCard
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesToolbarWithAction
import projects.nyinyihtunlwin.designsystem.components.LoadingDialog
import projects.nyinyihtunlwin.designsystem.components.SpacerHor
import projects.nyinyihtunlwin.designsystem.components.SpacerVer
import projects.nyinyihtunlwin.designsystem.components.bounceClick
import projects.nyinyihtunlwin.designsystem.theme.Gray_50
import projects.nyinyihtunlwin.designsystem.theme.Light_Orange
import projects.nyinyihtunlwin.designsystem.theme.LocalSpacing
import projects.nyinyihtunlwin.designsystem.theme.Mix_White
import projects.nyinyihtunlwin.designsystem.theme.Orange
import projects.nyinyihtunlwin.designsystem.theme.Pale_Orange
import projects.nyinyihtunlwin.meals.R
import projects.nyinyihtunlwin.meals.domain.model.Meal
import projects.nyinyihtunlwin.meals.presentation.model.MealUiModel
import projects.nyinyihtunlwin.network.BuildConfig

val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
val EXPANDED_TOP_BAR_HEIGHT = 400.dp

@Composable
fun MealDetailsScreen(
    mealId: String,
    onEvent: (MealDetailsEvent) -> Unit = {}
) {
    val viewModel: MealDetailsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = { viewModel.getMealDetailsById(id = mealId) })
    LoadingDialog(showLoading = uiState.loading)
    MealDetailsContent(meal = uiState.meal, onBackPressed = { onEvent(MealDetailsEvent.Exit) })
}

@Composable
fun MealDetailsContent(
    meal: MealUiModel?,
    onBackPressed: () -> Unit = {}
) {
    val listState = rememberLazyListState()

    val overlapHeightPx = with(LocalDensity.current) {
        EXPANDED_TOP_BAR_HEIGHT.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx()
    }

    val isCollapsed: Boolean by remember {
        derivedStateOf {
            val isFirstItemHidden = listState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || listState.firstVisibleItemIndex > 0
        }
    }
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            LazyColumn(
                state = listState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    ExpandedTopBar(
                        onBack = onBackPressed,
                        title = meal?.strMeal ?: "",
                        imgUrl = meal?.strMealThumb ?: "",
                        videoUrl = meal?.strYoutube ?: ""
                    )
                }
                meal?.let {
                    item { MealDetailsInfo(meal = it) }
                }
            }
            AnimatedVisibility(
                visible = isCollapsed,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                DiyRecipesToolbarWithAction(
                    title = meal?.strMeal ?: stringResource(R.string.details),
                    onBackPress = onBackPressed
                )
            }
        }
    }
}

@Composable
fun MealDetailsInfo(
    meal: MealUiModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        SpacerVer(height = LocalSpacing.current.spaceNormal)
        Text(
            text = stringResource(R.string.ingredients),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = LocalSpacing.current.spaceMedium),
            color = Orange
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.spaceXSmall),
            contentPadding = PaddingValues(
                bottom = LocalSpacing.current.spaceNormal,
                top = LocalSpacing.current.spaceNormal,
                start = LocalSpacing.current.spaceMedium,
                end = LocalSpacing.current.spaceMedium
            )
        ) {
            items(items = meal.strIngredients.filter { it.isNotEmpty() }) {
                IngredientItem(it)
            }
        }
        Text(
            text = stringResource(R.string.measures),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = LocalSpacing.current.spaceMedium),
            color = Orange
        )
        SpacerVer(height = LocalSpacing.current.spaceNormal)
        meal.strIngredients.filter { it.isNotEmpty() }.map { ingredient ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.spaceMedium)
                    .padding(bottom = LocalSpacing.current.spaceXTiny),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = ingredient,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                    color = Color.Gray
                )
                Text(
                    text = meal.strMeasures[meal.strIngredients.indexOf(ingredient)],
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                    color = Color.Gray
                )
            }
        }
        SpacerVer(height = LocalSpacing.current.spaceNormal)
        Text(
            text = stringResource(R.string.instructions),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = LocalSpacing.current.spaceMedium),
            color = Orange
        )
        SpacerVer(height = LocalSpacing.current.spaceNormal)
        Text(
            text = meal.strInstructions,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalSpacing.current.spaceMedium),
            color = Color.Gray
        )
        SpacerVer(height = LocalSpacing.current.spaceLarge)
    }
}

@Composable
private fun ExpandedTopBar(
    onBack: () -> Unit,
    title: String,
    imgUrl: String,
    videoUrl: String,
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.BottomStart,
    ) {
        AsyncImage(
            model = imgUrl,
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            if (videoUrl.isNotEmpty()) {
                DiyRecipesCard(
                    shape = CircleShape,
                    modifier = Modifier.padding(end = spacing.spaceMedium),
                    color = Orange,
                    onClick = {
                        context.openUrl(videoUrl)
                    }
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(48.dp)) {
                        Image(
                            painter = painterResource(id = projects.nyinyihtunlwin.designsystem.R.drawable.baseline_arrow_right_24),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
                VerticalDivider(
                    thickness = 4.dp, color = Pale_Orange, modifier = Modifier
                        .height(16.dp)
                        .padding(end = spacing.spaceLargeX)
                )
            }
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(topStart = 20.dp))
                    .background(Pale_Orange)
                    .padding(start = spacing.spaceXSmall)
                    .fillMaxWidth()
            ) {
                val (
                    backButton,
                    titleText
                ) = createRefs()
                BackButton(
                    onBackPress = onBack,
                    tint = Orange,
                    modifier = Modifier
                        .bounceClick()
                        .constrainAs(backButton) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top, spacing.spaceMedium)
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                        }
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.constrainAs(titleText) {
                        start.linkTo(backButton.end, spacing.spaceExtraTiny)
                        end.linkTo(parent.end, spacing.spaceExtraLarge)
                        top.linkTo(parent.top, spacing.spaceMedium)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    color = Orange
                )
            }
        }
    }
}

@Composable
fun IngredientItem(
    data: String
) {
    DiyRecipesCard {
        Box(
            modifier = Modifier
                .width(160.dp)
                .height(200.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = "${BuildConfig.BASE_MEAL_IMG_URL}${data}.png",
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = data,
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


