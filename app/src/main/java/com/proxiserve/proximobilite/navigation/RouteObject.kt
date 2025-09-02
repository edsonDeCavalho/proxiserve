package com.proxiserve.proximobilite.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Android
import androidx.compose.ui.graphics.vector.ImageVector
import com.proxiserve.proximobilite.R

/**
 * Created by dloriot on 02/07/2024.
 */
interface RouteObject {
    val route: String
    val label: String
    val logo: Int
    val defaultLogo: Int
        get() = R.drawable.symbole_pxs_red_trans
    val icon: ImageVector
    val defaultIcon: ImageVector
        get() = Icons.Filled.Android


}