package projects.nyinyihtunlwin.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.softShadow(shape: Shape = RectangleShape, shadowColor: Color, elevation: Dp) = this
    .padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
    .shadow(spotColor = shadowColor, elevation = elevation, shape = shape)