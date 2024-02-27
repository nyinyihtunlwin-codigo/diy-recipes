package projects.nyinyihtunlwin.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val lightColorScheme = lightColorScheme(
    primary = Pale_Orange,
    secondary = Orange,
    tertiary = LightBlue,
    background = Pale_Orange,
    surface = Pale_Orange,
    onPrimary = Orange,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Orange,
    onSurface = Orange,
)

@Composable
fun DiyRecipesTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = lightColorScheme,
        typography = Typography,
        shapes = Shapes
    ) {
        CompositionLocalProvider(
            LocalRippleTheme provides DiyRecipesRippleTheme,
            content = content
        )
    }
}


private object DiyRecipesRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Pale_Orange

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Color.Transparent,
        lightTheme = !isSystemInDarkTheme()
    )
}