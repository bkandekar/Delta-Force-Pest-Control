package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.MainViewModel
import com.example.ui.components.BookingDialog
import com.example.ui.components.CalculatorSection
import com.example.ui.components.FooterSection
import com.example.ui.components.HeaderTopBar
import com.example.ui.components.HeroSection
import com.example.ui.components.MyBookingsSection
import com.example.ui.components.PainPointsSection
import com.example.ui.components.ProcessSection
import com.example.ui.components.ReviewsSection
import com.example.ui.components.ServicesSection
import com.example.ui.components.WhyChooseUsSection
import com.example.ui.theme.BackgroundOffWhite
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.WhatsappGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundOffWhite
                ) {
                    DeltaForcePestApp()
                }
            }
        }
    }
}

@Composable
fun DeltaForcePestApp(viewModel: MainViewModel = viewModel()) {
    val context = LocalContext.current
    val activeSection by viewModel.activeSection.collectAsState()
    val isBookingModalVisible by viewModel.isBookingModalVisible.collectAsState()

    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HeaderTopBar(
                viewModel = viewModel,
                activeSection = activeSection,
                onBookNowClick = { viewModel.openBookingDialog() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.openDirectWhatsApp(context) },
                containerColor = WhatsappGreen,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "WhatsApp Quick Chat",
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // Render sections based on active section or full page layout
                when (activeSection) {
                    "Services" -> {
                        ServicesSection(
                            onBookServiceClick = { title, price ->
                                viewModel.openBookingDialog(
                                    presetPestConcern = title,
                                    presetEstimatedCost = price
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        FooterSection(
                            viewModel = viewModel,
                            onBookNowClick = { viewModel.openBookingDialog() }
                        )
                    }

                    "Calculator" -> {
                        CalculatorSection(
                            viewModel = viewModel,
                            onBookPackageClick = { pest, propType, cost ->
                                viewModel.openBookingDialog(
                                    presetPestConcern = pest,
                                    presetPropertyType = propType,
                                    presetEstimatedCost = cost
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        FooterSection(
                            viewModel = viewModel,
                            onBookNowClick = { viewModel.openBookingDialog() }
                        )
                    }

                    "Why Us" -> {
                        WhyChooseUsSection()
                        Spacer(modifier = Modifier.height(16.dp))
                        PainPointsSection()
                        Spacer(modifier = Modifier.height(16.dp))
                        ProcessSection()
                        Spacer(modifier = Modifier.height(16.dp))
                        FooterSection(
                            viewModel = viewModel,
                            onBookNowClick = { viewModel.openBookingDialog() }
                        )
                    }

                    "Reviews" -> {
                        ReviewsSection()
                        Spacer(modifier = Modifier.height(16.dp))
                        FooterSection(
                            viewModel = viewModel,
                            onBookNowClick = { viewModel.openBookingDialog() }
                        )
                    }

                    "My Saved" -> {
                        MyBookingsSection(viewModel = viewModel)
                        Spacer(modifier = Modifier.height(16.dp))
                        FooterSection(
                            viewModel = viewModel,
                            onBookNowClick = { viewModel.openBookingDialog() }
                        )
                    }

                    else -> {
                        // Home Tab (Full Landing View)
                        HeroSection(
                            onBookNowClick = { viewModel.openBookingDialog() },
                            onCalculateCostClick = { viewModel.setActiveSection("Calculator") }
                        )

                        CalculatorSection(
                            viewModel = viewModel,
                            onBookPackageClick = { pest, propType, cost ->
                                viewModel.openBookingDialog(
                                    presetPestConcern = pest,
                                    presetPropertyType = propType,
                                    presetEstimatedCost = cost
                                )
                            }
                        )

                        ServicesSection(
                            onBookServiceClick = { title, price ->
                                viewModel.openBookingDialog(
                                    presetPestConcern = title,
                                    presetEstimatedCost = price
                                )
                            }
                        )

                        PainPointsSection()

                        WhyChooseUsSection()

                        ProcessSection()

                        ReviewsSection()

                        MyBookingsSection(viewModel = viewModel)

                        FooterSection(
                            viewModel = viewModel,
                            onBookNowClick = { viewModel.openBookingDialog() }
                        )
                    }
                }
            }

            if (isBookingModalVisible) {
                BookingDialog(
                    viewModel = viewModel,
                    onDismiss = { viewModel.dismissBookingDialog() }
                )
            }
        }
    }
}
