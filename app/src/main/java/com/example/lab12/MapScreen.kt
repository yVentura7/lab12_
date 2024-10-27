package com.example.lab12

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import androidx.compose.ui.Modifier

@Composable
fun MapScreen() {
    val arequipaLocation = LatLng(-16.4040102, -71.559611) // Arequipa, Perú
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(arequipaLocation, 10f)
    }

    // Lista de ubicaciones de interés
    val locations = listOf(
        LatLng(-16.433415, -71.5442652), // JLByR
        LatLng(-16.4205151, -71.4945209), // Paucarpata
        LatLng(-16.3524187, -71.5675994)  // Zamacola
    )

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Añadir marcador en Arequipa, Perú con ícono azul
            Marker(
                state = rememberMarkerState(position = arequipaLocation),
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                title = "Arequipa, Perú"
            )

            // Iterar sobre las ubicaciones y añadir marcadores
            locations.forEach { location ->
                Marker(
                    state = rememberMarkerState(position = location),
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN), // Cambia el color si lo deseas
                    title = "Ubicación",
                    snippet = "Punto de interés"
                )
            }
        }
    }

    // Mover la cámara a una nueva ubicación y hacer zoom
    LaunchedEffect(Unit) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(LatLng(-16.2520984, -71.6836503), 12f), // Mover a Yura
            durationMs = 3000
        )
    }
}
