package com.mthree.medicalsync.feature.control

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mthree.medicalsync.R
import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.extension.hasPassed
import com.mthree.medicalsync.extension.toFormattedDateString
import com.mthree.medicalsync.extension.toFormattedTimeString
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ControlCard(
    control: Control,
    navigateToControlDetail: (Control) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = { navigateToControlDetail(control) },
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(2f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    style = MaterialTheme.typography.titleSmall,
                    text = control.controlTime.toFormattedDateString().uppercase(),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = control.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                val controlStatusText = when {
                    control.controlTime.hasPassed() -> {
                        if (control.controlTaken) {
                            stringResource(
                                id = R.string.control_taken_at,
                                control.controlTime.toFormattedTimeString()
                            )
                        } else {
                            stringResource(
                                id = R.string.control_skipped_at,
                                control.controlTime.toFormattedTimeString()
                            )
                        }
                    }
                    else -> stringResource(
                        id = R.string.control_scheduled_at,
                        control.controlTime.toFormattedTimeString()
                    )
                }
                Text(
                    text = controlStatusText,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}


@Preview
@Composable
private fun ControlCardTakeNowPreview() {
    ControlCard(
        Control(
            id = 123L,
            name = "A big big name for a little control I needs to take",
            description = "",
            dosage = "Davin",
            recurrence = "2",
            endDate = Date(),
            controlTime = Date(),
            controlTaken = false
        )
    ) { }
}

@Preview
@Composable
private fun ControlCardTakenPreview() {
    ControlCard(
        Control(
            id = 123L,
            name = "A big big name for a little control I needs to take",
            description = "",
            dosage = "Davin",
            recurrence = "2",
            endDate = Date(),
            controlTime = Date(),
            controlTaken = true
        )
    ) { }
}
