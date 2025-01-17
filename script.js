// Fetch buses based on search criteria
document.getElementById('searchForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const from = document.getElementById('from').value;
    const to = document.getElementById('to').value;
  
    const response = await fetch(`http://localhost:8080/api/buses/search?from=${from}&to=${to}`);
    const buses = await response.json();
  
    const busList = document.getElementById('busList');
    busList.innerHTML = buses.map(bus => `
      <div class="bus-card">
        <h5>${bus.busNumber}</h5>
        <p>From: ${bus.departure} | To: ${bus.destination}</p>
        <p>Departure: ${bus.departureTime} | Arrival: ${bus.arrivalTime}</p>
        <p>Price: $${bus.price}</p>
      </div>
    `).join('');
  });
  
  // Handle booking form submission
  document.getElementById('bookingForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const busId = document.getElementById('busId').value;
    const passengerName = document.getElementById('passengerName').value;
  
    const response = await fetch('http://localhost:8080/api/buses/book', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ busId, passengerName })
    });
  
    if (response.ok) {
      alert('Booking successful!');
    } else {
      alert('Booking failed. Please try again.');
    }
  });
  
  // Fetch and display booking history
  async function loadBookingHistory() {
    const response = await fetch('http://localhost:8080/api/bookings');
    const bookings = await response.json();
  
    const bookingHistory = document.getElementById('bookingHistory');
    bookingHistory.innerHTML = bookings.map(booking => `
      <div class="bus-card">
        <h5>Booking ID: ${booking.id}</h5>
        <p>Bus: ${booking.bus.busNumber}</p>
        <p>Passenger: ${booking.passenger.name}</p>
        <p>Booking Time: ${booking.bookingTime}</p>
      </div>
    `).join('');
  }
  
  loadBookingHistory();