package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phone: String,
    val locality: String,
    val propertyType: String,
    val propertySize: String,
    val pestConcern: String,
    val frequency: String,
    val estimatedCost: String,
    val preferredDate: String = "",
    val notes: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "Pending Inspection"
)
