package projects.nyinyihtunlwin.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.coroutines.delay
import projects.nyinyihtunlwin.designsystem.R
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.designsystem.theme.LocalSpacing
import projects.nyinyihtunlwin.designsystem.theme.Orange

enum class MessageStatus {
    Success, Error
}

@Composable
fun rememberMessageBarState(): MessageBarState {
    return remember { MessageBarState() }
}

class MessageBarState {
    var status by mutableStateOf<MessageStatus?>(null)
        private set
    var message by mutableStateOf<String?>(null)
        private set
    var isShow by mutableStateOf(false)
        private set

    suspend fun show(status: MessageStatus, message: String) {
        if (isShow) {
            isShow = false
            delay(300)
        }
        this.status = status
        this.message = message
        isShow = true
        delay(5000)
        hide()
    }

    suspend fun hide() {
        isShow = false
        delay(300)
    }
}

@Composable
fun ContentWithMessageBar(
    modifier: Modifier = Modifier,
    state: MessageBarState,
    onDismissClicked: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        content()

        AnimatedVisibility(
            visible = state.isShow,
            enter = slideInVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessHigh
                ),
            ) { -it },
            exit = slideOutVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessHigh
                ),
            ) { -it } + fadeOut(),
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = LocalSpacing.current.spaceSmall)
                .padding(horizontal = LocalSpacing.current.spaceMedium)
        ) {
            state.status?.let { status ->
                state.message?.let { message ->
                    MessageBar(
                        status = status,
                        message = message,
                        modifier = Modifier.align(Alignment.TopCenter),
                        onDismissClicked = onDismissClicked
                    )
                }
            }
        }
    }
}

@Composable
fun MessageBar(
    status: MessageStatus,
    message: String,
    modifier: Modifier = Modifier,
    onDismissClicked: () -> Unit
) {
    val icon by remember(status) {
        mutableIntStateOf(
            when (status) {
                MessageStatus.Success -> R.drawable.ic_check_white
                MessageStatus.Error -> R.drawable.ic_error_white
            }
        )
    }

    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .background(Orange)
            .fillMaxWidth()
            .padding(LocalSpacing.current.spaceNormal),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "MessageBar",
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.White,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(start = LocalSpacing.current.spaceXSmall)
                .weight(1F)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_close_white),
            contentDescription = "Close",
            modifier = Modifier.clickable {
                onDismissClicked.invoke()
            }
        )
    }
}

class MessageBarPreviewParameterProvider : PreviewParameterProvider<MessageStatus> {
    override val values = sequenceOf(
        MessageStatus.Success,
        MessageStatus.Error,
    )
}

@Preview
@Composable
fun MessageBarPreview(
    @PreviewParameter(MessageBarPreviewParameterProvider::class) status: MessageStatus
) {
    DiyRecipesTheme {
        MessageBar(
            status = status,
            message = "File has to be in jpeg, heic or png format"
        ) {

        }
    }
}