window.addEventListener("load", function () {
  const url = "/odontologos/modificar";

  const formularioUpdateOdontologo =
    document.querySelector("#update_odontologo");

  formularioUpdateOdontologo.addEventListener("submit", function (event) {
    const formDataUpdateOdontologo = {
      id: parseInt(document.querySelector("#id_odontologo").value),
      apellido: document.querySelector("#apellido").value,
      nombre: document.querySelector("#nombre").value,
      matricula: document.querySelector("#matricula").value,
    };

    const settingsUpdateOdontologo = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formDataUpdateOdontologo),
    };

    fetch(url, settingsUpdateOdontologo)
      .then((response) => response.json())
      .then((data) => {
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong></strong> Odontologo agregado </div>";

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
    document.querySelector("#apellido").value = "";
    document.querySelector("#nombre").value = "";
    document.querySelector("#matricula").value = "";
  }
});
