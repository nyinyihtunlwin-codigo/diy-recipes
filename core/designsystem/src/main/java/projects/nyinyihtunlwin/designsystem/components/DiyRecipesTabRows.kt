package projects.nyinyihtunlwin.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.designsystem.theme.LocalSpacing
import projects.nyinyihtunlwin.designsystem.theme.Mix_White
import projects.nyinyihtunlwin.designsystem.theme.Mix_White_50
import projects.nyinyihtunlwin.designsystem.theme.Orange


enum class TAB_MODE {
    FILLED, NORMAL
}

open class DiyRecipesTab(val label: String)

@Composable
fun DiyRecipesTabRow(
    modifier: Modifier = Modifier,
    tabs: List<DiyRecipesTab>,
    selectedTabIndex: Int,
    background: Color = Mix_White_50,
    indicatorColor: Color = Orange,
    selectedContentColor: Color = Orange,
    unselectedContentColor: Color = Orange,
    onTabClick: (index: Int) -> Unit,
    tabMode: TAB_MODE = TAB_MODE.NORMAL,
) {
    val spacing = LocalSpacing.current

    Column(modifier = modifier.fillMaxWidth()) {
        Row {
            if (tabMode == TAB_MODE.NORMAL) {
                Spacer(modifier = Modifier.weight(1f))
            }
            TabRow(
                modifier = Modifier.weight(3f),
                selectedTabIndex = 0,
                contentColor = unselectedContentColor,
                containerColor = background,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(
                            currentTabPosition = tabPositions[selectedTabIndex],
                        ),
                        height = 2.dp,
                        color = indicatorColor,
                    )
                },
            ) {
                tabs.forEachIndexed { tabIndex, tab ->
                    val selected = selectedTabIndex == tabIndex
                    Row(
                        modifier = Modifier
                            .clip(
                                MaterialTheme.shapes.small,
                            )
                            .clickable {
                                onTabClick.invoke(tabIndex)
                            }
                            .padding(
                                top = spacing.spaceSmall,
                                bottom = spacing.spaceNormal,
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        val data = tabs[tabIndex]
                        Text(
                            text = data.label,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                            textAlign = TextAlign.Center,
                            color = if (selected) selectedContentColor else unselectedContentColor,
                        )
                    }
                }
            }
            if (tabMode == TAB_MODE.NORMAL) {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomTabRowPreview() {
    DiyRecipesTheme() {
        DiyRecipesTabRow(
            tabs = listOf(
                DiyRecipesTab("Tab 1"),
                DiyRecipesTab("Tab 2")
            ),
            selectedTabIndex = 0,
            onTabClick = {},
            tabMode = TAB_MODE.FILLED,
        )
    }
}
