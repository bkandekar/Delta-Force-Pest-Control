/* ==========================================================================
   DELTA FORCE PEST CONTROL — JavaScript Logic
   ========================================================================== */

/* RULE 1: SINGLE SOURCE OF TRUTH FOR BUSINESS CONTACT INFO */
const BUSINESS_CONFIG = {
  phone: "9067257872",          // digits only, no +91 for tel: links
  whatsapp: "918329931123",      // digits only with country code, no + for wa.me links
  email: "contact@deltaforcepestcontrol.com",
  businessName: "Delta Force Pest Control and Sanitization Cleaning Services",
  tagline: "100% Odorless & Eco-Friendly Pest Control Services in Solapur"
};

/* DATA CONSTANTS — PHASE 2 REFINED CONTENT */
const PAIN_POINTS = [
  { 
    pain: "I've tried treatments before but pests keep coming back", 
    solution: "We provide structured Annual Maintenance Contracts (AMC) with guaranteed long-term protection and free re-treatment visits, not just quick one-time sprays." 
  },
  { 
    pain: "I'm worried about toxic chemicals around my kids and pets", 
    solution: "We exclusively use Government-approved, 100% odorless, child & pet-safe herbal formulations applied with medical precision." 
  },
  { 
    pain: "I don't know how much pest control will actually cost", 
    solution: "Use our transparent instant cost calculator right on this page to get an accurate, upfront estimate with no hidden fees." 
  },
  { 
    pain: "I don't trust unverified technicians in my private space", 
    solution: "Every Delta Force specialist is background-verified, professionally trained, uniformed, and carries official photo identification." 
  },
  { 
    pain: "Other companies take forever or don't show up on time", 
    solution: "We guarantee punctual appointment confirmations with automated reminders and committed on-site arrival times." 
  }
];

const SERVICES_LIST = [
  {
    icon: "🐜",
    badge: "Most Popular",
    title: "General Pest Control",
    desc: "Comprehensive herbal spray & gel baiting targeting ants, silverfish, spiders, and common household insects.",
    features: ["100% Safe for Children & Pets", "Odorless Chemical Solution", "Single Visit or Quarterly AMC"],
    price: "Starts at ₹1,199"
  },
  {
    icon: "🪳",
    badge: "High Demand",
    title: "Cockroach Control",
    desc: "Advanced German cockroach eradication using odorless Bayer gel baiting in kitchens, cabinets & drains.",
    features: ["No Need to Empty Kitchen", "Odorless Herbal Gel", "100% Eradication Guarantee"],
    price: "Starts at ₹1,499"
  },
  {
    icon: "🪵",
    badge: "5-Yr Warranty",
    title: "Termite Control",
    desc: "Deep drill-fill-seal subterranean termite barrier treatment protecting woodwork and structural foundations.",
    features: ["5-Year Written Warranty", "Government Approved Chemical", "Prevents Structural Damage"],
    price: "Starts at ₹3,499"
  },
  {
    icon: "🛏️",
    badge: "100% Elimination",
    title: "Bed Bug Treatment",
    desc: "Comprehensive 2-session spray treatment destroying live bed bugs, nymphs, and unhatched eggs in mattresses.",
    features: ["2-Stage Deep Treatment", "High Temperature Spray", "Sleep Peacefully Tonight"],
    price: "Starts at ₹1,899"
  },
  {
    icon: "🐀",
    badge: "Commercial Grade",
    title: "Rodent Control",
    desc: "Trapping, tamper-resistant poison baiting, and entry-point audit against rats and mice for businesses & homes.",
    features: ["Safe Poison Bait Stations", "Entry Point Audit", "Prevents Wire Damage"],
    price: "Starts at ₹1,599"
  },
  {
    icon: "🦟",
    badge: "Outdoor & Indoor",
    title: "Mosquito Control",
    desc: "Thermal fogging and bio-larvicide spraying in garden, balcony, and drain areas to halt mosquito breeding.",
    features: ["Halts Dengue & Malaria", "Outdoor Fogging", "Safe Herbal Larvicide"],
    price: "Starts at ₹1,299"
  },
  {
    icon: "🪵",
    badge: "Wood Protection",
    title: "Wood Borer Treatment",
    desc: "Specialized oil-base chemical injection into wooden furniture, doors, and cabinets to destroy wood borer larvae.",
    features: ["Syringe Injection", "Preserves Expensive Furniture", "2-Year Warranty"],
    price: "Starts at ₹2,199"
  },
  {
    icon: "🐝",
    badge: "High-Reach Safe",
    title: "Hornet & Wasp Extermination",
    desc: "Safe removal of wasp and hornet nests from high building ledges and balconies without stings.",
    features: ["Certified Safety Gear", "Complete Nest Removal", "Prevents Re-nesting"],
    price: "Starts at ₹1,299"
  },
  {
    icon: "🕷️",
    badge: "Deep Clean",
    title: "Spider Extermination",
    desc: "Web removal and residual chemical spraying on ceilings, corners, and window frames.",
    features: ["Web Removal Included", "Long Lasting Barrier", "Clean Finish"],
    price: "Starts at ₹999"
  },
  {
    icon: "🐝",
    badge: "Eco Removal",
    title: "Bee Removal",
    desc: "Humane and safe relocation/removal of wild beehives from residential roofs and balconies.",
    features: ["Non-toxic Method", "Eco Relocation", "Immediate Response"],
    price: "Starts at ₹1,499"
  },
  {
    icon: "📝",
    badge: "Save 20%",
    title: "Annual Maintenance Contract (AMC)",
    desc: "Year-round protection covering 3 to 4 scheduled visits per year with free emergency call-outs.",
    features: ["3-4 Scheduled Services/Yr", "Free Emergency Support", "Best Value for Homes"],
    price: "Starts at ₹3,999/yr"
  }
];

const PROCESS_STEPS = [
  { step: "1", title: "Instant Booking / Estimate", desc: "Select service or use our cost calculator to submit your inquiry via WhatsApp or direct booking." },
  { step: "2", title: "Free On-Site Inspection", desc: "Our certified technician arrives at your Solapur property for a thorough pest audit." },
  { step: "3", title: "Odorless Treatment", desc: "We apply Govt-approved, 100% odorless & non-toxic herbal formulations safely." },
  { step: "4", title: "Warranty & AMC Support", desc: "Receive your official written warranty certificate and automated maintenance reminders." }
];

/* SAMPLE TESTIMONIAL — replace with real customer quote before going live */
const REVIEWS = [
  { 
    name: "Prakash Deshmukh", 
    locality: "Jule Solapur", 
    service: "Termite Control", 
    stars: "⭐⭐⭐⭐⭐", 
    comment: "Termites were destroying my wooden wardrobes in Jule Solapur. Delta Force completed the drill-fill treatment 2 years ago and not a single termite has returned. Exceptional service and clear written warranty!",
    photoPlaceholder: "[PLACEHOLDER: Customer photo — Google Drawings 200x200 circular]"
  },
  { 
    name: "Dr. Anjali Patil", 
    locality: "Vijayapur Road", 
    service: "Cockroach Herbal Gel", 
    stars: "⭐⭐⭐⭐⭐", 
    comment: "The odorless herbal gel treatment was completed without having to vacate or shift any kitchen items in my clinic residential quarters on Vijayapur Road. Very professional and polite staff.",
    photoPlaceholder: "[PLACEHOLDER: Customer photo — Google Drawings 200x200 circular]"
  },
  { 
    name: "Suresh Kulkarni", 
    locality: "Sat Rasta", 
    service: "Bed Bug Treatment", 
    stars: "⭐⭐⭐⭐⭐", 
    comment: "We struggled with bed bugs for over two months. Delta Force did a 2-stage extermination process in our Sat Rasta home and eliminated them completely. Slept peacefully from day one!",
    photoPlaceholder: "[PLACEHOLDER: Customer photo — Google Drawings 200x200 circular]"
  },
  { 
    name: "Vikram Shah (Hotel Manager)", 
    locality: "Hotgi Road", 
    service: "Commercial AMC", 
    stars: "⭐⭐⭐⭐⭐", 
    comment: "We hired Delta Force for an Annual Maintenance Contract for our restaurant and kitchen on Hotgi Road. Prompt quarterly visits, eco-friendly chemicals, and zero customer complaints.",
    photoPlaceholder: "[PLACEHOLDER: Customer photo — Google Drawings 200x200 circular]"
  }
];

/* INITIALIZATION ON DOM LOAD */
document.addEventListener("DOMContentLoaded", () => {
  renderPainPoints();
  renderServices();
  renderProcess();
  renderReviews();
  calculateEstimate();
});

/* CALCULATOR LOGIC */
function calculateEstimate() {
  const propType = document.getElementById("calcPropertyType").value;
  const size = document.getElementById("calcPropertySize").value;
  const pest = document.getElementById("calcPestConcern").value;
  const freq = document.getElementById("calcFrequency").value;

  let basePrice = 1200;

  // Size multiplier
  if (size === "500–1000 sq ft") basePrice += 400;
  if (size === "1000–2000 sq ft") basePrice += 900;
  if (size === "2000+ sq ft") basePrice += 1800;

  // Pest multiplier
  if (pest.includes("Termite")) basePrice += 1800;
  if (pest.includes("Bed Bug")) basePrice += 600;
  if (pest.includes("AMC")) basePrice += 2000;
  if (pest.includes("Wood Borer")) basePrice += 800;

  // Property multiplier
  if (propType.includes("Villa")) basePrice += 500;
  if (propType.includes("Warehouse")) basePrice += 1500;

  // Frequency adjustment
  if (freq.includes("Quarterly")) basePrice = Math.round(basePrice * 2.2);
  if (freq.includes("Annual")) basePrice = Math.round(basePrice * 3.1);

  const minVal = Math.round(basePrice * 0.9);
  const maxVal = Math.round(basePrice * 1.25);

  const resultText = `₹${minVal.toLocaleString()} – ₹${maxVal.toLocaleString()}`;
  document.getElementById("calcEstimateText").innerText = resultText;
}

/* RENDERING FUNCTIONS */
function renderPainPoints() {
  const container = document.getElementById("painPointsGrid");
  if (!container) return;
  container.innerHTML = PAIN_POINTS.map(p => `
    <div class="pain-card">
      <div class="pain-problem">❌ ${p.pain}</div>
      <div class="pain-solution">✔ Delta Force Solution: ${p.solution}</div>
    </div>
  `).join('');
}

function renderServices() {
  const container = document.getElementById("servicesGrid");
  if (!container) return;
  container.innerHTML = SERVICES_LIST.map(s => `
    <div class="service-card">
      <div class="service-card-top">
        <div class="service-icon">${s.icon}</div>
        <span class="service-badge">${s.badge}</span>
      </div>
      <h3 class="service-title">${s.title}</h3>
      <p class="service-desc">${s.desc}</p>
      <ul class="service-features">
        ${s.features.map(f => `<li>${f}</li>`).join('')}
      </ul>
      <div class="service-card-bottom">
        <div>
          <span class="service-price-label">${s.price.startsWith('Starts') ? '' : 'Starting at'}</span>
          <span class="service-price-val">${s.price}</span>
        </div>
        <button class="btn btn-primary" onclick="bookPresetService('${s.title}', '${s.price}')">Get Quote</button>
      </div>
    </div>
  `).join('');
}

function renderProcess() {
  const container = document.getElementById("processGrid");
  if (!container) return;
  container.innerHTML = PROCESS_STEPS.map(pr => `
    <div class="process-card">
      <div class="process-num">${pr.step}</div>
      <div>
        <h3 class="process-title">${pr.title}</h3>
        <p class="process-desc">${pr.desc}</p>
      </div>
    </div>
  `).join('');
}

/* SAMPLE TESTIMONIAL — replace with real customer quote before going live */
function renderReviews() {
  const container = document.getElementById("reviewsGrid");
  if (!container) return;
  container.innerHTML = REVIEWS.map(r => `
    <!-- SAMPLE TESTIMONIAL — replace with real customer quote before going live -->
    <div class="review-card">
      <div class="review-top">
        <span class="review-stars">${r.stars}</span>
        <span class="review-verified">✔ Verified Customer</span>
      </div>
      <p class="review-comment">"${r.comment}"</p>
      <div class="review-author" style="display: flex; align-items: center; gap: 12px; margin-top: 16px;">
        <div class="avatar-circle" style="width: 42px; height: 42px; border-radius: 50%; background: var(--primary-light); color: var(--primary); display: flex; align-items: center; justify-content: center; font-weight: 700; font-size: 1rem;" title="${r.photoPlaceholder || ''}">
          ${r.name.split(' ').map(n => n[0]).join('')}
        </div>
        <div>
          <div class="review-name" style="font-weight: 700; font-size: 0.95rem;">${r.name}</div>
          <div class="review-meta" style="font-size: 0.825rem; color: var(--text-muted);">${r.locality} • ${r.service}</div>
        </div>
      </div>
    </div>
  `).join('');
}

/* BOOKING MODAL & WHATSAPP REDIRECT */
function openBookingModal() {
  document.getElementById("bookingModal").classList.add("active");
}

function closeBookingModal() {
  document.getElementById("bookingModal").classList.remove("active");
  document.getElementById("modalEstimateNotice").style.display = "none";
}

function bookFromCalculator() {
  const pest = document.getElementById("calcPestConcern").value;
  const prop = document.getElementById("calcPropertyType").value;
  const est = document.getElementById("calcEstimateText").innerText;

  document.getElementById("modalPestConcern").value = pest;
  document.getElementById("modalPropertyType").value = prop;
  document.getElementById("modalEstimateVal").innerText = est;
  document.getElementById("modalEstimateNotice").style.display = "block";

  openBookingModal();
}

function bookPresetService(serviceTitle, price) {
  document.getElementById("modalPestConcern").value = serviceTitle;
  document.getElementById("modalEstimateVal").innerText = price;
  document.getElementById("modalEstimateNotice").style.display = "block";

  openBookingModal();
}

function handleBookingSubmit(event) {
  event.preventDefault();

  const name = document.getElementById("modalName").value.trim();
  const phone = document.getElementById("modalPhone").value.trim();
  const locality = document.getElementById("modalLocality").value.trim() || "Solapur";
  const property = document.getElementById("modalPropertyType").value;
  const pest = document.getElementById("modalPestConcern").value;
  const date = document.getElementById("modalDate").value.trim() || "As soon as possible";
  const notes = document.getElementById("modalNotes").value.trim();

  const estimateText = document.getElementById("modalEstimateVal").innerText || "";

  let message = `Hello Delta Force Pest Control! I would like to book a Free On-Site Inspection:\n\n` +
    `👤 Name: ${name}\n` +
    `📞 Phone: ${phone}\n` +
    `📍 Locality: ${locality}, Solapur\n` +
    `🏢 Property: ${property}\n` +
    `🐛 Pest Concern: ${pest}\n` +
    `📅 Preferred Date: ${date}`;

  if (estimateText) {
    message += `\n💰 Estimated Cost: ${estimateText}`;
  }

  if (notes) {
    message += `\n📝 Notes: ${notes}`;
  }

  const encodedMsg = encodeURIComponent(message);
  const waUrl = `https://wa.me/${BUSINESS_CONFIG.whatsapp}?text=${encodedMsg}`;

  const statusMsg = document.getElementById("modalStatusMsg");
  statusMsg.style.color = "var(--primary)";
  statusMsg.innerText = "Redirecting you to WhatsApp...";

  setTimeout(() => {
    window.open(waUrl, "_blank");
    closeBookingModal();
  }, 600);
}
