package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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

data class ImagePlaceholderItem(
    val id: String,
    val title: String,
    val description: String,
    val dimension: String,
    val iconEmoji: String
)

val GALLERY_IMAGE_PLACEHOLDERS = listOf(
    ImagePlaceholderItem("g1", "Termite Drill-Fill-Seal", "Technician performing barrier treatment in Solapur villa", "800 x 600 px", "🪵"),
    ImagePlaceholderItem("g2", "Herbal Cockroach Gel", "Gel application in restaurant kitchen cabinet", "800 x 600 px", "🪳"),
    ImagePlaceholderItem("g3", "Thermal Mosquito Fogging", "Outdoor fogging in Jule Solapur residential area", "800 x 600 px", "🦟"),
    ImagePlaceholderItem("g4", "Wasp Nest Extermination", "High-reach nest removal from building balcony", "800 x 600 px", "🐝"),
    ImagePlaceholderItem("g5", "Bed Bug Heat Treatment", "Mattress and bedroom deep chemical spray", "800 x 600 px", "🛏️"),
    ImagePlaceholderItem("g6", "Rodent Baiting Audit", "Tamper-proof rat station setup in warehouse", "800 x 600 px", "🐀")
)

@Composable
fun GallerySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // Section Header
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
                        imageVector = Icons.Default.Collections,
                        contentDescription = "Gallery",
                        tint = ForestGreenPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "WORK GALLERY",
                        color = ForestGreenPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "On-Field Work Highlights",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = CharcoalDark,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Real treatment photos & field operations in Solapur",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Grid of Image Placeholders
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            GALLERY_IMAGE_PLACEHOLDERS.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowItems.forEach { item ->
                        Box(modifier = Modifier.weight(1f)) {
                            ImagePlaceholderCard(item = item)
                        }
                    }
                    if (rowItems.size < 2) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun ImagePlaceholderCard(item: ImagePlaceholderItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            // Visual Image Frame
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFE8F5E9),
                                Color(0xFFC8E6C9)
                            )
                        )
                    )
                    .border(1.dp, Color(0xFFA5D6A7), RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = item.iconEmoji, fontSize = 28.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.85f), CircleShape)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CameraAlt,
                                contentDescription = "Camera",
                                tint = ForestGreenPrimary,
                                modifier = Modifier.size(10.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = item.dimension,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = ForestGreenPrimary
                            )
                        }
                    }
                }
            }

            // Caption
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = item.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = CharcoalDark
                )
                Text(
                    text = item.description,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    lineHeight = 13.sp
                )
            }
        }
    }
}
