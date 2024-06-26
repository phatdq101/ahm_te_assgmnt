package dev.phatduong.ahm_tech_asignment.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.phatduong.ahm_tech_asignment.domain.model.Project
import dev.phatduong.ahm_tech_asignment.ui.theme.AhmtechasignmentTheme
import dev.phatduong.ahm_tech_asignment.utils.formatToThousandText

@Composable
fun ProjectItem(
    modifier: Modifier = Modifier,
    project: Project
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                1.dp,
                Color.LightGray,
                RoundedCornerShape(16f)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 16.dp
            )
    ) {
        Column {
            Text(
                text = project.name.orEmpty(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(6.dp))
            Text(
                text = project.description.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
            Spacer(modifier = Modifier.size(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!project.language.isNullOrBlank()) {
                    SourceText(
                        icon = Icons.Default.PlayArrow,
                        text = project.language
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                SourceText(
                    icon = Icons.Default.Star,
                    text = project.starCount.formatToThousandText()
                )
                Spacer(modifier = Modifier.size(10.dp))
                SourceText(
                    icon = Icons.Default.Share,
                    text = project.forkCount.formatToThousandText()
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .border(
                    1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(Int.MAX_VALUE.dp)
                )
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = project.visibility.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RepositoryItemPreview(modifier: Modifier = Modifier) {
    AhmtechasignmentTheme {
        ProjectItem(
            project = Project(
                name = "Lorem Ipsum",
                description = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...",
                visibility = "public",
                starCount = 102123,
                forkCount = 534,
                language = "Kotlin"
            )
        )
    }
}

