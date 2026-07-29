package com.example.ui

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.BookingEntity
import com.example.data.BookingRepository
import com.example.data.BusinessConfig
import com.example.model.PestData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BookingRepository
    val userBookings: StateFlow<List<BookingEntity>>

    init {
        val db = AppDatabase.getDatabase(application)
        repository = BookingRepository(db.bookingDao())
        userBookings = repository.allBookings.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    // Selected Navigation Section
    private val _activeSection = MutableStateFlow("Home")
    val activeSection: StateFlow<String> = _activeSection.asStateFlow()

    fun setActiveSection(section: String) {
        _activeSection.value = section
    }

    // Calculator State
    private val _calcPropertyType = MutableStateFlow(PestData.PROPERTY_TYPES[0])
    val calcPropertyType: StateFlow<String> = _calcPropertyType.asStateFlow()

    private val _calcPropertySize = MutableStateFlow(PestData.PROPERTY_SIZES[1]) // 500-1000 sq ft
    val calcPropertySize: StateFlow<String> = _calcPropertySize.asStateFlow()

    private val _calcPestConcern = MutableStateFlow(PestData.PEST_TYPES[0]) // General Pest Control
    val calcPestConcern: StateFlow<String> = _calcPestConcern.asStateFlow()

    private val _calcFrequency = MutableStateFlow(PestData.FREQUENCIES[2]) // Annual AMC (Save 20%)
    val calcFrequency: StateFlow<String> = _calcFrequency.asStateFlow()

    private val _calcEstimateRange = MutableStateFlow(
        PestData.calculateEstimate(
            PestData.PROPERTY_TYPES[0],
            PestData.PROPERTY_SIZES[1],
            PestData.PEST_TYPES[0],
            PestData.FREQUENCIES[2]
        )
    )
    val calcEstimateRange: StateFlow<Pair<Int, Int>> = _calcEstimateRange.asStateFlow()

    fun updateCalculator(
        propertyType: String = _calcPropertyType.value,
        propertySize: String = _calcPropertySize.value,
        pestConcern: String = _calcPestConcern.value,
        frequency: String = _calcFrequency.value
    ) {
        _calcPropertyType.value = propertyType
        _calcPropertySize.value = propertySize
        _calcPestConcern.value = pestConcern
        _calcFrequency.value = frequency
        _calcEstimateRange.value = PestData.calculateEstimate(
            propertyType, propertySize, pestConcern, frequency
        )
    }

    // Booking Modal Dialog State
    private val _isBookingModalVisible = MutableStateFlow(false)
    val isBookingModalVisible: StateFlow<Boolean> = _isBookingModalVisible.asStateFlow()

    private val _bookingName = MutableStateFlow("")
    val bookingName: StateFlow<String> = _bookingName.asStateFlow()

    private val _bookingPhone = MutableStateFlow("")
    val bookingPhone: StateFlow<String> = _bookingPhone.asStateFlow()

    private val _bookingLocality = MutableStateFlow("Jule Solapur")
    val bookingLocality: StateFlow<String> = _bookingLocality.asStateFlow()

    private val _bookingPropertyType = MutableStateFlow(PestData.PROPERTY_TYPES[0])
    val bookingPropertyType: StateFlow<String> = _bookingPropertyType.asStateFlow()

    private val _bookingPestConcern = MutableStateFlow(PestData.PEST_TYPES[0])
    val bookingPestConcern: StateFlow<String> = _bookingPestConcern.asStateFlow()

    private val _bookingEstimatedCost = MutableStateFlow("")
    val bookingEstimatedCost: StateFlow<String> = _bookingEstimatedCost.asStateFlow()

    private val _bookingPreferredDate = MutableStateFlow("")
    val bookingPreferredDate: StateFlow<String> = _bookingPreferredDate.asStateFlow()

    private val _bookingNotes = MutableStateFlow("")
    val bookingNotes: StateFlow<String> = _bookingNotes.asStateFlow()

    private val _bookingErrorName = MutableStateFlow<String?>(null)
    val bookingErrorName: StateFlow<String?> = _bookingErrorName.asStateFlow()

    private val _bookingErrorPhone = MutableStateFlow<String?>(null)
    val bookingErrorPhone: StateFlow<String?> = _bookingErrorPhone.asStateFlow()

    private val _isRedirectingWhatsApp = MutableStateFlow(false)
    val isRedirectingWhatsApp: StateFlow<Boolean> = _isRedirectingWhatsApp.asStateFlow()

    fun openBookingDialog(
        presetPestConcern: String? = null,
        presetPropertyType: String? = null,
        presetEstimatedCost: String? = null
    ) {
        if (presetPestConcern != null) _bookingPestConcern.value = presetPestConcern
        else _bookingPestConcern.value = _calcPestConcern.value

        if (presetPropertyType != null) _bookingPropertyType.value = presetPropertyType
        else _bookingPropertyType.value = _calcPropertyType.value

        if (presetEstimatedCost != null) {
            _bookingEstimatedCost.value = presetEstimatedCost
        } else {
            val range = _calcEstimateRange.value
            _bookingEstimatedCost.value = "₹${range.first} – ₹${range.second}"
        }

        _bookingErrorName.value = null
        _bookingErrorPhone.value = null
        _isRedirectingWhatsApp.value = false
        _isBookingModalVisible.value = true
    }

    fun dismissBookingDialog() {
        _isBookingModalVisible.value = false
        _isRedirectingWhatsApp.value = false
    }

    fun setBookingName(v: String) {
        _bookingName.value = v
        if (v.isNotBlank()) _bookingErrorName.value = null
    }

    fun setBookingPhone(v: String) {
        _bookingPhone.value = v
        if (v.length >= 10) _bookingErrorPhone.value = null
    }

    fun setBookingLocality(v: String) { _bookingLocality.value = v }
    fun setBookingPropertyType(v: String) { _bookingPropertyType.value = v }
    fun setBookingPestConcern(v: String) { _bookingPestConcern.value = v }
    fun setBookingPreferredDate(v: String) { _bookingPreferredDate.value = v }
    fun setBookingNotes(v: String) { _bookingNotes.value = v }

    fun submitBookingAndRedirectWhatsApp(context: Context) {
        var isValid = true
        if (_bookingName.value.trim().isEmpty()) {
            _bookingErrorName.value = "Please enter your full name"
            isValid = false
        }
        val cleanPhone = _bookingPhone.value.trim()
        if (cleanPhone.length < 10) {
            _bookingErrorPhone.value = "Please enter a valid 10-digit mobile number"
            isValid = false
        }

        if (!isValid) return

        _isRedirectingWhatsApp.value = true

        val booking = BookingEntity(
            name = _bookingName.value.trim(),
            phone = cleanPhone,
            locality = _bookingLocality.value.trim(),
            propertyType = _bookingPropertyType.value,
            propertySize = _calcPropertySize.value,
            pestConcern = _bookingPestConcern.value,
            frequency = _calcFrequency.value,
            estimatedCost = _bookingEstimatedCost.value,
            preferredDate = _bookingPreferredDate.value.trim(),
            notes = _bookingNotes.value.trim()
        )

        viewModelScope.launch {
            repository.insertBooking(booking)

            // Construct WhatsApp formatted string
            val msgBuilder = StringBuilder()
            msgBuilder.append("Hello Delta Force Pest Control! I would like to book a free inspection/treatment:\n\n")
            msgBuilder.append("👤 *Name:* ${booking.name}\n")
            msgBuilder.append("📞 *Phone:* ${booking.phone}\n")
            msgBuilder.append("📍 *Locality:* ${booking.locality}, Solapur\n")
            msgBuilder.append("🏢 *Property Type:* ${booking.propertyType}\n")
            msgBuilder.append("🐛 *Pest Concern:* ${booking.pestConcern}\n")
            if (booking.estimatedCost.isNotBlank()) {
                msgBuilder.append("💰 *Estimated Cost:* ${booking.estimatedCost}\n")
            }
            if (booking.preferredDate.isNotBlank()) {
                msgBuilder.append("📅 *Preferred Date:* ${booking.preferredDate}\n")
            }
            if (booking.notes.isNotBlank()) {
                msgBuilder.append("📝 *Notes:* ${booking.notes}\n")
            }
            msgBuilder.append("\nPlease confirm the free on-site inspection slot.")

            val waUrl = BusinessConfig.getWhatsAppUri(msgBuilder.toString())
            
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(waUrl))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "Opening WhatsApp...", Toast.LENGTH_SHORT).show()
            }

            _isBookingModalVisible.value = false
            _isRedirectingWhatsApp.value = false
        }
    }

    fun makeDirectPhoneCall(context: Context) {
        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse(BusinessConfig.getTelUri()))
        callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(callIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "Calling ${BusinessConfig.PHONE}", Toast.LENGTH_SHORT).show()
        }
    }

    fun openDirectWhatsApp(context: Context, defaultMessage: String = "Hello Delta Force Pest Control! I'm reaching out from Solapur regarding pest control services.") {
        val waUrl = BusinessConfig.getWhatsAppUri(defaultMessage)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(waUrl))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Opening WhatsApp...", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteBooking(id: Int) {
        viewModelScope.launch {
            repository.deleteBooking(id)
        }
    }
}
