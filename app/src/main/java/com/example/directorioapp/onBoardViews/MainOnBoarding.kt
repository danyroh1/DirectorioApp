package com.example.directorioapp.onBoardViews

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.directorioapp.R
import com.example.directorioapp.data.PageData
import com.example.directorioapp.dataStore.StoreBoarding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class,
    ExperimentalFoundationApi::class)
@Composable
fun MainOnBoarding(navController: NavController, store: StoreBoarding){
    val items= ArrayList<PageData>()

    items.add(
        PageData(
            R.raw.page1,
            "Bienvenido a tu Directorio",
            "Aquí encontrarás toda la información " +
                    "importante de tus contactos en un solo lugar."

        )
    )
    items.add(
        PageData(
            R.raw.page2,
            "Fácil de Usar",
            "Agrega y Controla tus contactos"
        )
    )
    items.add(
        PageData(
            R.raw.page3,
            "¡Todo Listo!",
            "Presiona Continuar"
        )
    )

    val pagerState= rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
    )

    OnBoardingPager(
        item =items, pagerState =pagerState, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White), navController,store
    )
}