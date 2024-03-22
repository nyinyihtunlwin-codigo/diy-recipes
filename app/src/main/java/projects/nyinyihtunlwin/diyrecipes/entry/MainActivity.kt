package projects.nyinyihtunlwin.diyrecipes.entry

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import projects.nyinyihtunlwin.common.event.GlobalEvent
import projects.nyinyihtunlwin.designsystem.components.ContentWithMessageBar
import projects.nyinyihtunlwin.designsystem.components.MessageStatus
import projects.nyinyihtunlwin.designsystem.components.rememberMessageBarState
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.designsystem.theme.LocalEntryPadding
import projects.nyinyihtunlwin.diyrecipes.destinations.NavGraphs
import projects.nyinyihtunlwin.diyrecipes.destinations.destinations.CocktailListDestination
import projects.nyinyihtunlwin.diyrecipes.destinations.destinations.MealCategoryListDestination
import projects.nyinyihtunlwin.diyrecipes.navigation.DiyRecipesBottomNavigation
import projects.nyinyihtunlwin.diyrecipes.navigation.loadTransitions

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        val configuration = Configuration(newBase.resources.configuration)
        configuration.fontScale = 1.0f
        applyOverrideConfiguration(configuration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        splashScreen.setKeepOnScreenCondition {
            false
        }

        setContent {
            val messageState = rememberMessageBarState()
            var bottomBarState by remember { mutableStateOf(false) }
            val showBottomNav = remember { derivedStateOf { bottomBarState } }

            val engine = rememberNavHostEngine(
                navHostContentAlignment = Alignment.Center,
                rootDefaultAnimations = RootNavGraphDefaultAnimations(
                    enterTransition = {
                        loadTransitions(isMainEntry = showBottomNav.value).enterTransition
                    },
                    exitTransition = {
                        loadTransitions(isMainEntry = showBottomNav.value).exitTransition
                    },
                    popEnterTransition = {
                        loadTransitions(isMainEntry = showBottomNav.value).popEnterTransition
                    },
                    popExitTransition = {
                        loadTransitions(isMainEntry = showBottomNav.value).popExitTransition
                    }
                )
            )

            val navController: NavHostController = engine.rememberNavController()
            val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute =
                currentNavBackStackEntry?.destination?.route ?: MealCategoryListDestination.route

            val startRoute = remember {
                derivedStateOf { MealCategoryListDestination }
            }

            bottomBarState = when (currentRoute) {
                MealCategoryListDestination.route,
                CocktailListDestination.route -> true

                else -> false
            }
            LaunchedEffect(key1 = Unit) {
                viewModel.globalEvent.event
                    .collectLatest {
                        when (it) {
                            is GlobalEvent.Event.Idle -> {
                                messageState.hide()
                            }

                            is GlobalEvent.Event.Error -> {
                                messageState.show(
                                    status = MessageStatus.Error,
                                    message = it.msg
                                )
                            }

                            is GlobalEvent.Event.Success -> {
                                messageState.show(
                                    status = MessageStatus.Success,
                                    message = it.msg
                                )
                            }
                        }
                    }
            }
            DiyRecipesTheme {
                ContentWithMessageBar(
                    state = messageState,
                    onDismissClicked = { viewModel.globalEvent.emit(GlobalEvent.Event.Idle) },
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        contentWindowInsets = WindowInsets(top = 0.dp, bottom = 0.dp),
                        bottomBar = {
                            AnimatedVisibility(
                                visible = showBottomNav.value,
                                enter = slideInVertically(initialOffsetY = { it }),
                                exit = slideOutVertically(targetOffsetY = { it })
                            ) {
                                DiyRecipesBottomNavigation(navController)
                            }
                        }
                    ) { innerPadding ->
                        CompositionLocalProvider(
                            LocalEntryPadding provides innerPadding,
                        ) {
                            DestinationsNavHost(
                                engine = engine,
                                navController = navController,
                                navGraph = NavGraphs.root,
                                startRoute = startRoute.value,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiyRecipesTheme {
        Greeting("Android")
    }
}