package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

data class PestQuizItem(
    val name: String,
    val emoji: String,
    val dangerLevel: String,
    val sign: String,
    val recommendedTreatment: String,
    val priceEst: String
)

val PEST_QUIZ_LIST = listOf(
    PestQuizItem("Cockroaches", "🪳", "High Hazard", "Droppings in kitchen corners, egg cases near drain pipes.", "German Gel Baiting & Anti-Bacterial Spray", "₹1,499"),
    PestQuizItem("Termites", "🪵", "Severe Damage", "Mud tubes on walls, hollow sound from wooden doors.", "Drill-Fill-Seal Subterranean Barrier (5-Yr Warranty)", "₹3,499"),
    PestQuizItem("Bed Bugs", "🛏️", "Painful Bites", "Red itchy skin marks, dark spots on mattress seams.", "2-Session Deep Heat Spraying & Egg Removal", "₹1,899"),
    PestQuizItem("Rodents / Rats", "🐀", "Wire Damage", "Chewed electrical wires, nocturnal scratching sounds.", "Tamper-Proof Baiting & Mechanical Traps", "₹1,599"),
    PestQuizItem("Mosquitoes", "🦟", "Dengue Risk", "Standing water breeding, evening swarm in balconies.", "Cold Fogging & Bio-Larvicide Application", "₹1,299"),
    PestQuizItem("Wood Borer", "🪵", "Furniture Decay", "Fine yellow powder beneath wooden furniture.", "Syringe Oil Chemical Injection", "₹2,199")
)

@Composable
fun PestIdentifierQuizSection(
    onSelectPestToBook: (pestName: String, estPrice: String) -> Unit
) {
    var selectedPest by remember { mutableStateOf(PEST_QUIZ_LIST[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // Section Title
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(AmberContainer, CircleShape)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = "Pest Quiz",
                        tint = ForestGreenPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "PEST IDENTIFIER",
                        color = ForestGreenPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Identify Your Pest Infestation",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = CharcoalDark,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Tap a pest type below to reveal symptoms & recommended solution.",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Horizontal Selector Chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(PEST_QUIZ_LIST) { pest ->
                val isSelected = pest.name == selectedPest.name
                Box(
                    modifier = Modifier
                        .background(
                            if (isSelected) ForestGreenPrimary else Color.White,
                            RoundedCornerShape(20.dp)
                        )
                        .border(
                            1.dp,
                            if (isSelected) ForestGreenPrimary else Color(0xFFDDDDDD),
                            RoundedCornerShape(20.dp)
                        )
                        .clickable { selectedPest = pest }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "${pest.emoji} ${pest.name}",
                        color = if (isSelected) Color.White else CharcoalDark,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Selected Pest Detail Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = selectedPest.emoji, fontSize = 28.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = selectedPest.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = CharcoalDark
                            )
                            Text(
                                text = "Threat Level: ${selectedPest.dangerLevel}",
                                fontSize = 11.sp,
                                color = Color(0xFFD32F2F),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .background(AmberContainer, RoundedCornerShape(12.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Starts ${selectedPest.priceEst}",
                            color = ForestGreenPrimary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFEEEEEE)))
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "🚨 Key Infestation Signs:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = CharcoalDark
                )
                Text(
                    text = selectedPest.sign,
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "🛡️ Recommended Delta Force Treatment:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestGreenPrimary
                )
                Text(
                    text = selectedPest.recommendedTreatment,
                    fontSize = 12.sp,
                    color = CharcoalDark,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onSelectPestToBook(selectedPest.name, selectedPest.priceEst)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ForestGreenPrimary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Book ${selectedPest.name} Treatment",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Go",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
