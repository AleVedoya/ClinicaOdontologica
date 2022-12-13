window.addEventListener("load", function () {
  const url = "/turnos/create";
  const formulario = document.querySelector("#add_new_turno");

  formulario.addEventListener("submit", function (event) {
    const formData = {
      paciente: {
        id: document.querySelector("#id_paciente").value
      },
      odontologo: {
        id: document.querySelector("#id_odontologo").value
      },
      fecha: document.querySelector("#fecha_turno").value,
      hora: document.querySelector("#hora_turno").value
    };

    const settings = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong></strong> Turno agregado </div>";

        document.querySelector("#response").innerHTML = successAlert;
        document.querySelector("#response").style.display = "block";
        resetUploadForm();
      })
      .catch((error) => {
        let errorAlert =
          '<div class="alert alert-danger alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong> Error intente nuevamente</strong> </div>";

        document.querySelector("#response").innerHTML = errorAlert;
        document.querySelector("#response").style.display = "block";
        resetUploadForm();
      });
  });

  function resetUploadForm() {
    document.querySelector("#fecha_turno").value = "";
    document.querySelector("#hora_turno").value = "";
    document.querySelector("#id_paciente").value = "";
    document.querySelector("#id_odontologo").value = "";
  }

/*
  (function () {
    let pathname = window.location.pathname;
    if (pathname === "/") {
      document.querySelector(".nav .nav-item a:first").addClass("active");
    } else if (pathname == "/verTurnos.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  })();
*/
});
