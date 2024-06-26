package dev.phatduong.ahm_tech_asignment.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.phatduong.ahm_tech_asignment.R
import dev.phatduong.ahm_tech_asignment.ui.theme.AhmtechasignmentTheme
import dev.phatduong.ahm_tech_asignment.utils.formatToThousandText

@Composable
fun GoogleHeader(
    modifier: Modifier = Modifier,
    followers: Int = 41123,
    url: String = "https://opensource.google"
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .border(
                    BorderStroke(1.dp, Color.LightGray),
                    RoundedCornerShape(8.dp)
                )
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Text(
                text = "Google",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "Google ❤ Open Source",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.size(8.dp))
            SourceText(
                icon = Icons.Default.Face,
                text = "${followers.formatToThousandText()} followers"
            )
            Spacer(modifier = Modifier.size(4.dp))
            SourceText(
                icon = Icons.Default.LocationOn,
                text = "United States of America"
            )
            Spacer(modifier = Modifier.size(4.dp))
            SourceText(
                icon = Icons.Default.Share,
                text = url
            )
        }
    }
}

@Composable
fun SourceText(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun GoogleHeaderPreview(modifier: Modifier = Modifier) {
    AhmtechasignmentTheme {
        GoogleHeader()
    }
}