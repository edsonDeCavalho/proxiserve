package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proxiserve.proximobilite.core.utils.TestTag
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.FeuilleDeRouteViewModel
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.components.FeuilleDeRouteItem
import java.util.SortedMap

/**
 * Created by dloriot on 04/09/2024.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorizedLazyColumn(
    modifier: Modifier,
    sortedMap: SortedMap<Pair<String?, String?>, List<Intervention>>,
    navController: NavController,
    viewModel: FeuilleDeRouteViewModel
) {

    LazyColumn(modifier = modifier) {
        sortedMap.forEach {(datePeriode, interventions) ->
            stickyHeader {
                datePeriode.first?.let { datePeriode.second?.let { it1 -> CategoryHeader(date = it, period = it1) } }
            }
            items(interventions) { intervention ->
                FeuilleDeRouteItem(
                    intervention = intervention,
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun CategoryHeader(date: String, period: String) {
    val shape = RoundedCornerShape(
        topStart = 10.dp,
        topEnd = 10.dp,
        bottomEnd = 0.dp,
        bottomStart = 0.dp
    )

    Surface(
        shape = shape,
        color = MaterialTheme.colorScheme.primary.copy(0.40f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$date $period",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),
            textAlign = TextAlign.Center

        )
    }

}

//@Composable
//fun CategoryHeader(date: String, period: String) {
//
//}