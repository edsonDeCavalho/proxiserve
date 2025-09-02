package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.state

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.core.utils.FakeUtils
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber

/**
 * Created by dloriot on 27/08/2024.
 */
data class GetFakeFeuilleDeRoute(@ApplicationContext val context: Context) {
    private val TAG: String = "[GetFakeFeuilleDeRoute]"

    operator fun invoke(context: Context): FeuilleDeRoute {
        Timber.w("$TAG invoke")
        val fdrJson = FakeUtils.getFakeFeuilleDeRoute(context = context)
//        Timber.w("$TAG invoke $fdrJson")
        val type = object: TypeToken<FeuilleDeRoute>(){}.type
        val fdr: FeuilleDeRoute = Gson().fromJson(fdrJson, type)

        return fdr
    }
}