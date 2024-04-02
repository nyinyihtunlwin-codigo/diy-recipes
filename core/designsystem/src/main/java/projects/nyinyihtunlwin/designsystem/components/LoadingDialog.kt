package projects.nyinyihtunlwin.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.designsystem.theme.LocalSpacing
import projects.nyinyihtunlwin.designsystem.theme.Orange
import projects.nyinyihtunlwin.designsystem.theme.Pale_Orange


@Composable
fun LoadingDialog(
    showLoading: Boolean,
    onDismiss: () -> Unit = {}
) {
    if (showLoading) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            DiyRecipesLoading()
        }
    }
}

@Composable
fun DiyRecipesLoading(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .background(
                color = Pale_Orange,
                shape = CircleShape
            )
            .size(48.dp)
            .padding(all = LocalSpacing.current.spaceXSmall)
    ) {
        CircularProgressIndicator(
            color = Orange,
            modifier = Modifier.background(color = Pale_Orange)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingDialogPreview() {
    DiyRecipesTheme {
        LoadingDialog(
            showLoading = true
        )
    }
}