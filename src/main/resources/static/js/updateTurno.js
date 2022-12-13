window.addEventListener("load", function () {
  const url = "/turnos/modificar";
  const formularioUpdateTurnos = document.querySelector("#update_turno");

  formularioUpdateTurnos.addEventListener("submit", function (event) {
    const formDataUpdateTurno = {
        id: document.querySelector("#id_turno").value,
      paciente: {
        id: document.querySelector("#id_paciente").value
      },
      odontologo: {
        id: document.querySelector("#id_odontologo").value
      },
      fecha: document.querySelector("#fecha_turno").value,
      hora: document.querySelector("#hora_turno").value
    };

    const settingsUpdateTurno = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formDataUpdateTurno),
    };

    fetch(url, settingsUpdateTurno)
      .then((response) => response.json())
      .then((data) => {
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong></strong> paciente agregado </div>";

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
    document.querySelector("#id").value = "";
    document.querySelector("#id_odontologo").value = "";
    document.querySelector("#id_paciente").value = "";
    document.querySelector("#fecha_turno").value = "";
    document.querySelector("#hora_turno").value = "";
  }
});
