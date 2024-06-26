package dev.phatduong.ahm_tech_asignment.ui.components

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.phatduong.ahm_tech_asignment.ui.theme.AhmtechasignmentTheme

@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier,
    message: String
) {
    Text(
        text = message,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorMessagePreview() {
    AhmtechasignmentTheme {
        ErrorMessage(message = "error msg...")
    }
}