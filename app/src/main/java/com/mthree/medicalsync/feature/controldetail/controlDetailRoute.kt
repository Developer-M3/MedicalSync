package com.mthree.medicalsync.feature.controldetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mthree.medicalsync.R
import com.mthree.medicalsync.analytics.AnalyticsEvents
import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.extension.toFormattedDateString
import com.mthree.medicalsync.extension.toFormattedTimeString
import com.mthree.medicalsync.feature.controldetail.viewmodel.ControlDetailViewModel
import com.mthree.medicalsync.util.SnackbarUtil.Companion.showSnackbar

@Composable
fun ControlDetailRoute(
    control: Control?,
    onBackClicked: () -> Unit,
    viewModel: ControlDetailViewModel = hiltViewModel()
) {
    control?.let {
        ControlDetailScreen(control, viewModel, onBackClicked)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ControlDetailScreen(
    control: Control,
    viewModel: ControlDetailViewModel,
    onBackClicked: () -> Unit,

) {
    var isTakenTapped by remember { mutableStateOf(control.controlTaken) }
    var isSkippedTapped by remember { mutableStateOf(!control.controlTaken) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                navigationIcon = {
                    FloatingActionButton(
                        onClick = {
                            viewModel.logEvent(AnalyticsEvents.CONTROL_DETAIL_ON_BACK_CLICKED)
                            onBackClicked()
                        },
                        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                },
                title = { }
            )
        },
        bottomBar = {
            Column {
                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    SegmentedButton(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        selected = isTakenTapped,
                        shape = MaterialTheme.shapes.extraLarge,
                        onClick = {
                            isTakenTapped = !isTakenTapped
                            if (isTakenTapped) {
                                isSkippedTapped = false
                            }
                            viewModel.logEvent(AnalyticsEvents.CONTROL_DETAIL_TAKEN_CLICKED)
                            viewModel.updateControl(control, isTakenTapped)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.taken),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    SegmentedButton(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        selected = isSkippedTapped,
                        shape = MaterialTheme.shapes.extraLarge,
                        onClick = {
                            isSkippedTapped = !isSkippedTapped
                            if (isSkippedTapped) {
                                isTakenTapped = false
                            }
                            viewModel.logEvent(AnalyticsEvents.CONTROL_DETAIL_SKIPPED_CLICKED)
                            viewModel.updateControl(control, isTakenTapped)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.skipped),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(56.dp),
                    onClick = {
                        viewModel.logEvent(AnalyticsEvents.CONTROL_DETAIL_DONE_CLICKED)
                        showSnackbar(context.getString(R.string.control_logged))
                        onBackClicked()
                    },
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text(
                        text = stringResource(id = R.string.done),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge,
                text = control.controlTime.toFormattedDateString(),
                color = MaterialTheme.colorScheme.primary
            )

            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }

            Text(
                text = control.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = stringResource(
                    id = R.string.control_dose_details,
                    control.dosage,
                    control.controlTime.toFormattedTimeString()
                ),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
