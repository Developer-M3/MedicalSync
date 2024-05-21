package com.mthree.medicalsync.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mthree.medicalsync.feature.addcontrol.navigation.addControlGraph
import com.mthree.medicalsync.feature.addmedication.navigation.addMedicationGraph
import com.mthree.medicalsync.feature.calendar.navigation.calendarGraph
import com.mthree.medicalsync.feature.control.navigation.ControlDestination
import com.mthree.medicalsync.feature.history.historyGraph
import com.mthree.medicalsync.feature.home.navigation.HomeDestination
import com.mthree.medicalsync.feature.home.navigation.homeGraph
import com.mthree.medicalsync.feature.control.navigation.controlGraph
import com.mthree.medicalsync.feature.medicationconfirm.navigation.MEDICATION
import com.mthree.medicalsync.feature.medicationconfirm.navigation.MedicationConfirmDestination
import com.mthree.medicalsync.feature.medicationconfirm.navigation.medicationConfirmGraph
import com.mthree.medicalsync.feature.medicationdetail.MedicationDetailDestination
import com.mthree.medicalsync.feature.medicationdetail.medicationDetailGraph


import com.mthree.medicalsync.feature.controlconfirm.navigation.CONTROL
import com.mthree.medicalsync.feature.controlconfirm.navigation.ControlConfirmDestination
import com.mthree.medicalsync.feature.controlconfirm.navigation.controlConfirmGraph
import com.mthree.medicalsync.feature.controldetail.ControlDetailDestination
import com.mthree.medicalsync.feature.controldetail.controlDetailGraph
import com.mthree.medicalsync.util.navigateSingleTop

@Composable
fun MedicalNavHost(
    bottomBarVisibility: MutableState<Boolean>,
    fabVisibility: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            navigateToMedicationDetail = {
                val bundle = Bundle()
                bundle.putParcelable(MEDICATION, it)
                navController.currentBackStackEntry?.savedStateHandle.apply {
                    this?.set(MEDICATION, bundle)
                }
                navController.navigate(MedicationDetailDestination.route)
            }
        )
        controlGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            navigateToControlDetail = {
                val bundle = Bundle()
                bundle.putParcelable(CONTROL, it)
                navController.currentBackStackEntry?.savedStateHandle.apply {
                    this?.set(CONTROL, bundle)
                }
                navController.navigate(ControlDetailDestination.route)
            }
        )
        controlDetailGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            onBackClicked = { navController.navigateUp() }
        )
        calendarGraph(bottomBarVisibility, fabVisibility)
        addControlGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            onBackClicked = { navController.navigateUp() },
            navigateToControlConfirm = {
                // TODO: Replace with control id
                val bundle = Bundle()
                bundle.putParcelableArrayList(CONTROL, ArrayList(it))
                navController.currentBackStackEntry?.savedStateHandle.apply {
                    this?.set(CONTROL, bundle)
                }
                navController.navigate(ControlConfirmDestination.route)
            }
        )
        controlConfirmGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            onBackClicked = { navController.navigateUp() },
            navigateToHome = {
                navController.navigateSingleTop(ControlDestination.route)
            }
        )

        medicationDetailGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            onBackClicked = { navController.navigateUp() }
        )
        addMedicationGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            onBackClicked = { navController.navigateUp() },
            navigateToMedicationConfirm = {
                // TODO: Replace with medication id
                val bundle = Bundle()
                bundle.putParcelableArrayList(MEDICATION, ArrayList(it))
                navController.currentBackStackEntry?.savedStateHandle.apply {
                    this?.set(MEDICATION, bundle)
                }
                navController.navigate(MedicationConfirmDestination.route)
            }
        )
        medicationConfirmGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility,
            fabVisibility = fabVisibility,
            onBackClicked = { navController.navigateUp() },
            navigateToHome = {
                navController.navigateSingleTop(HomeDestination.route)
            }
        )
    }
}
