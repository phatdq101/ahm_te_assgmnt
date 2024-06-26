package dev.phatduong.ahm_tech_asignment.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import dev.phatduong.ahm_tech_asignment.domain.model.Project
import dev.phatduong.ahm_tech_asignment.ui.theme.AhmtechasignmentTheme
import dev.phatduong.ahm_tech_asignment.utils.formatToThousandText
import kotlinx.coroutines.flow.flowOf
import java.net.UnknownHostException
import kotlin.random.Random

@Composable
fun ProjectLazyList(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<Project>,
    onError: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items.itemCount) { index ->
            items[index]?.let { safeProject ->
                ProjectItem(project = safeProject)
            }
        }
        when {
            items.loadState.refresh is LoadState.Loading || items.loadState.append is LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            items.loadState.refresh is LoadState.Error || items.loadState.append is LoadState.Error -> {
                var message = "Error"
                val err = items.loadState.refresh as? LoadState.Error
                err?.let { safeError ->
                    message =
                        when (safeError.error) {
                            is UnknownHostException -> {
                                "No internet connection, please check again."
                            }

                            else -> {
                                "Error: ${safeError.error.message.orEmpty()}\nRepositories're loaded from database..."
                            }
                        }
                    onError.invoke(message)
                }
            }

            items.loadState.refresh is LoadState.NotLoading -> {
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RepositoryItemPreview(modifier: Modifier = Modifier) {
    AhmtechasignmentTheme {
        ProjectLazyList(items = flowOf(PagingData.from(fakeItems)).collectAsLazyPagingItems()) {}
    }
}

private val fakeItems = arrayListOf<Project>().also {
    repeat(50) { index ->
        it.add(
            Project(
                id = Random.nextInt(),
                name = "Name $index",
                description = "Des $index",
                visibility = "public",
                starCount = index * 10,
                forkCount = index,
                language = "L$index"
            )
        )
    }
}