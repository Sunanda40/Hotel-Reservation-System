const BASE_URL = "http://localhost:8080";

// LOAD ROOMS
function loadRooms() {
    fetch(BASE_URL + "/rooms/available")
        .then(res => res.json())
        .then(data => {

            let output = "";

            data.forEach(room => {

                output += `
                    <div class="col-md-3">
                        <div class="card mb-3 text-center shadow">
                            <div class="card-body">

                                <h5>Room ${room.id}</h5>
                                <p>${room.type}</p>
                                <p>₹${room.price}</p>

                                <span class="badge ${room.available ? 'bg-success' : 'bg-danger'}">
                                    ${room.available ? 'Available' : 'Booked'}
                                </span>

                                <br><br>

                                ${
                                    room.available
                                    ? `<button class="btn btn-success"
                                        onclick="goToBooking(${room.id})">
                                        Book Now
                                       </button>`
                                    : `<button class="btn btn-danger" disabled>
                                        Booked
                                       </button>`
                                }

                            </div>
                        </div>
                    </div>
                `;
            });

            document.getElementById("rooms").innerHTML = output;
        });
}

// NAVIGATE TO BOOKING
function goToBooking(id) {
    localStorage.setItem("roomId", id);
    window.location.href = "booking.html";
}

// AUTO FILL ROOM ID
window.onload = function () {
    const roomId = localStorage.getItem("roomId");
    if (document.getElementById("roomId")) {
        document.getElementById("roomId").value = roomId;
    }
};

// CONFIRM BOOKING
function confirmBooking() {

    const roomId = document.getElementById("roomId").value;

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;

    // ✅ DEBUG (VERY IMPORTANT)
    console.log("DATA:", name, email, phone, roomId);

    const customer = {
        name: name,
        email: email,
        phone: phone
    };

    fetch("http://localhost:8080/reservations/book/" + roomId, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(customer)
    })
    .then(res => res.text())
    .then(msg => {
        alert(msg);

        // ✅ FORCE REFRESH
        window.location.href = "rooms.html";
    })
    .catch(err => console.error("ERROR:", err));
}
