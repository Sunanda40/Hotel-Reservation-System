function login() {

    const user = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    };

    fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(user)
    })
    .then(res => {
        if (!res.ok) throw new Error();
        return res.json();
    })
    .then(data => {

        // store role
        localStorage.setItem("role", data.role);

        // redirect
        if (data.role === "ADMIN") {
            window.location.href = "admin.html";
        } else {
            window.location.href = "rooms.html";
        }
    })
    .catch(() => alert("Invalid Username or Password"));
}