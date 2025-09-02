package com.proxiserve.proximobilite

import androidx.navigation.NavController
import org.junit.Assert

/**
 * Created by dloriot on 28/11/2024.
 */
fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}

fun NavController.assertDestinationRouteName(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, currentDestination?.route)
}