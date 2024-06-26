package dev.phatduong.ahm_tech_asignment.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import dev.phatduong.ahm_tech_asignment.ui.main.MainViewModel
import dev.phatduong.ahm_tech_asignment.ui.theme.AhmtechasignmentTheme

@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val projectLazyPagingItems = viewModel.projects.collectAsLazyPagingItems()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        GoogleHeader()
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = "Popular repositories",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(12.dp))
        ProjectLazyList(items = projectLazyPagingItems)
    }
}

@Preview(showBackground = true)
@Composable
fun AppContentPreview(modifier: Modifier = Modifier) {
    AhmtechasignmentTheme {
        AppContent()
    }
}