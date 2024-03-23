@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package projects.nyinyihtunlwin.designsystem.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme

@Composable
fun DiyRecipesCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    elevation: Dp = 20.dp,
    color: Color = MaterialTheme.colorScheme.surface,
    onClick: () -> Unit = {},
    onLongPressed: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Surface(
        modifier = modifier
            .bounceClick()
            .softShadow(
                shape = shape,
                shadowColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                elevation = elevation
            )
            .clip(shape)
            .hapticClickable(
                hapticFeedbackEnabled = true,
                onClick = onClick,
                enabled = true
            )
            .combinedClickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onLongClick = onLongPressed,
                onClick = onClick
            ),
        color = color
    ) {
        content()
    }
}

@Composable
fun DiyRecipesCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    elevation: Dp = 20.dp,
    color: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .softShadow(
                shape = shape,
                shadowColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                elevation = elevation
            )
            .clip(shape),
        color = color
    ) {
        content()
    }
}

fun Modifier.bounceClick(isEnable: Boolean = true) = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(
        if (buttonState == ButtonState.Pressed) {
            if (isEnable) 0.95f else 1f
        } else {
            1f
        },
        label = "",
    )

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {},
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}

enum class ButtonState { Pressed, Idle }

@Preview
@Composable
fun DiyRecipesCardPreview() {
    DiyRecipesTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DiyRecipesCard(
                modifier = Modifier.size(200.dp),
                shape = MaterialTheme.shapes.small,
                onClick = {}) {}
        }
    }
}
