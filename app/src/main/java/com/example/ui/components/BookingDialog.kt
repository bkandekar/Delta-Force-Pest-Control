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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.PestData
import com.example.ui.MainViewModel
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.WhatsappGreen

@Composable
fun BookingDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    val name by viewModel.bookingName.collectAsState()
    val phone by viewModel.bookingPhone.collectAsState()
    val locality by viewModel.bookingLocality.collectAsState()
    val propertyType by viewModel.bookingPropertyType.collectAsState()
    val pestConcern by viewModel.bookingPestConcern.collectAsState()
    val estimatedCost by viewModel.bookingEstimatedCost.collectAsState()
    val preferredDate by viewModel.bookingPreferredDate.collectAsState()
    val notes by viewModel.bookingNotes.collectAsState()

    val errorName by viewModel.bookingErrorName.collectAsState()
    val errorPhone by viewModel.bookingErrorPhone.collectAsState()
    val isRedirecting by viewModel.isRedirectingWhatsApp.collectAsState()

    var showPropertyDropdown by remember { mutableStateOf(false) }
    var showPestDropdown by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .padding(vertical = 20.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "BOOK FREE INSPECTION",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = ForestGreenPrimary,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Delta Force Solapur",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = CharcoalDark
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFFF0F4F2), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = CharcoalDark,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                if (estimatedCost.isNotBlank()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ForestGreenDark, RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Estimated Package Cost:", color = Color.White, fontSize = 12.sp)
                            Text(
                                text = estimatedCost,
                                color = AmberAccent,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Full Name Input
                OutlinedTextField(
                    value = name,
                    onValueChange = { viewModel.setBookingName(it) },
                    label = { Text("Full Name *") },
                    isError = errorName != null,
                    supportingText = errorName?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Phone Input
                OutlinedTextField(
                    value = phone,
                    onValueChange = { viewModel.setBookingPhone(it) },
                    label = { Text("Phone Number (WhatsApp) *") },
                    isError = errorPhone != null,
                    supportingText = errorPhone?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Locality Input
                OutlinedTextField(
                    value = locality,
                    onValueChange = { viewModel.setBookingLocality(it) },
                    label = { Text("Area / Locality in Solapur") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Property Type Dropdown
                Text(text = "Property Type", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = CharcoalDark)
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0F4F2), RoundedCornerShape(8.dp))
                        .border(1.dp, Color(0xFFD0DDD6), RoundedCornerShape(8.dp))
                        .clickable { showPropertyDropdown = true }
                        .padding(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = propertyType, fontSize = 14.sp, color = CharcoalDark)
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Select", tint = ForestGreenPrimary)
                    }

                    DropdownMenu(
                        expanded = showPropertyDropdown,
                        onDismissRequest = { showPropertyDropdown = false }
                    ) {
                        PestData.PROPERTY_TYPES.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    viewModel.setBookingPropertyType(item)
                                    showPropertyDropdown = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Pest Concern Dropdown
                Text(text = "Pest Concern", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = CharcoalDark)
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0F4F2), RoundedCornerShape(8.dp))
                        .border(1.dp, Color(0xFFD0DDD6), RoundedCornerShape(8.dp))
                        .clickable { showPestDropdown = true }
                        .padding(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = pestConcern, fontSize = 14.sp, color = CharcoalDark, fontWeight = FontWeight.SemiBold)
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Select", tint = ForestGreenPrimary)
                    }

                    DropdownMenu(
                        expanded = showPestDropdown,
                        onDismissRequest = { showPestDropdown = false }
                    ) {
                        PestData.PEST_TYPES.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    viewModel.setBookingPestConcern(item)
                                    showPestDropdown = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Preferred Date
                OutlinedTextField(
                    value = preferredDate,
                    onValueChange = { viewModel.setBookingPreferredDate(it) },
                    label = { Text("Preferred Inspection Date (e.g. Tomorrow 10 AM)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Notes
                OutlinedTextField(
                    value = notes,
                    onValueChange = { viewModel.setBookingNotes(it) },
                    label = { Text("Additional Notes (Optional)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(18.dp))

                if (isRedirecting) {
                    Text(
                        text = "Redirecting you to WhatsApp...",
                        color = ForestGreenPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(
                    onClick = {
                        viewModel.submitBookingAndRedirectWhatsApp(context)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsappGreen,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Submit",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Book & Send via WhatsApp",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
