package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.AmberContainer
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.ForestGreenPrimary

@Composable
fun ComparisonTableSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Section Header
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .background(AmberContainer, CircleShape)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Compare,
                        contentDescription = "Compare",
                        tint = ForestGreenPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "SMART COMPARISON",
                        color = ForestGreenPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Local Spray vs. Delta Force",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = CharcoalDark,
                textAlign = TextAlign.Center
            )

            Text(
                text = "See why 10,000+ homeowners & businesses in Solapur choose Delta Force.",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Comparison Cards Grid
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Table Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "FEATURES",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Gray,
                        modifier = Modifier.weight(1.2f)
                    )
                    Text(
                        text = "OTHERS ❌",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFFC62828),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "DELTA FORCE ✅",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = ForestGreenPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1.2f)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFEEEEEE)))

                // Comparison Items
                ComparisonRowItem(
                    title = "Formulation",
                    others = "Harsh Strong Odor",
                    deltaForce = "100% Odorless & Herbal"
                )

                ComparisonRowItem(
                    title = "Kid & Pet Safety",
                    others = "Toxic & Irritating",
                    deltaForce = "Child & Pet Safe"
                )

                ComparisonRowItem(
                    title = "Service Guarantee",
                    others = "Zero Warranty",
                    deltaForce = "Up to 5-Yr Warranty"
                )

                ComparisonRowItem(
                    title = "Technicians",
                    others = "Unverified Labor",
                    deltaForce = "Background Verified"
                )

                ComparisonRowItem(
                    title = "Kitchen Cleaning",
                    others = "Empty all cabinets",
                    deltaForce = "No Shifting Required"
                )
            }
        }
    }
}

@Composable
private fun ComparisonRowItem(
    title: String,
    others: String,
    deltaForce: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CharcoalDark,
                modifier = Modifier.weight(1.2f)
            )

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Cancel,
                    contentDescription = "No",
                    tint = Color(0xFFE53935),
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = others,
                    fontSize = 10.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
            }

            Row(
                modifier = Modifier.weight(1.2f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Yes",
                    tint = ForestGreenPrimary,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = deltaForce,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestGreenPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFF5F5F5)))
    }
}
