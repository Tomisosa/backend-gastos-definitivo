document.getElementById("btnLogin").addEventListener("click", () => {
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();

  if (!email || !password) {
    alert("Por favor, completá ambos campos.");
    return;
  }

  fetch("http://localhost:8080/api/usuarios/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ email, password })
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("Error en el login");
      }
      return response.json();
    })
    .then(data => {
      console.log("Login exitoso:", data);

      // 🔥 Guarda el token
      localStorage.setItem("token", data.token);

      // 🔥 Guarda el ID del usuario
      localStorage.setItem("userId", data.id);

      // 🔥 Guarda el nombre (sirve para mostrar en el dashboard)
      localStorage.setItem("userName", data.nombre);

      // 🔥 Redirige al dashboard
      window.location.href = "dashboard.html";
    })
    .catch(error => {
      console.error("Error:", error);
      alert("Credenciales inválidas o error de conexión.");
    });
});
