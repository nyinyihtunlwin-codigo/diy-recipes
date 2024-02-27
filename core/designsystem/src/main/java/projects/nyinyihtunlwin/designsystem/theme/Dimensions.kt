package projects.nyinyihtunlwin.designsystem.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** usecase
 * val spacing = LocalSpacing.current
 * spacing.default
 * */

data class Dimensions(
    val default: Dp = 0.dp,
    val spaceXXXTiny: Dp = 1.dp,
    val spaceXXTiny: Dp = 2.dp,
    val spaceXTiny: Dp = 4.dp,
    val spaceExtraTiny: Dp = 6.dp,
    val spaceTiny: Dp = 8.dp,
    val spaceXSmall: Dp = 10.dp,
    val spaceSmall: Dp = 12.dp,
    val spaceXNormal: Dp = 14.dp,
    val spaceNormal: Dp = 16.dp,
    val spaceXMedium: Dp = 18.dp,
    val spaceMedium: Dp = 20.dp,
    val spaceMediumX: Dp = 22.dp,
    val spaceBig: Dp = 24.dp,
    val spaceXBig: Dp = 28.dp,
    val spaceXXBig: Dp = 30.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceLargeX: Dp = 40.dp,
    val spaceExtraLarge: Dp = 48.dp,
    val spaceXLarge: Dp = 52.dp,
    val spaceXExtraLarge: Dp = 56.dp,
    val spaceXXLarge: Dp = 64.dp,
    val spaceXXXLarge: Dp = 80.dp,
    val spaceXXXXLarge: Dp = 84.dp,

    val space36: Dp =36.dp,
    val space77: Dp =77.dp,
    val space84: Dp =84.dp,
    val space100: Dp =100.dp,
    val space150: Dp =150.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }
