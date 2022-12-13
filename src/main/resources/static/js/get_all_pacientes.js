window.addEventListener("load", function () {
  const url = "/pacientes/lista";
  const settings = {
    method: "GET",
  };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      for (paciente of data) {
        var table = document.getElementById("pacienteTable");
        var pacienteRow = table.insertRow();
        let tr_id = "tr_" + paciente.id;
        pacienteRow.id = tr_id;

        let deleteLink =
          "<button" +
          " id=" +
          '"' +
          "btn_delete_" +
          paciente.id +
          '"' +
          ' type="button" onclick="deleteBy(' +
          paciente.id +
          ')" class="btn btn-danger btn_delete">' +
          "&times" +
          "</button>";

        let updateLink =
          '<a id="a_update_' +
          paciente.id +
          '"' +
          ' href="../update_paciente.html"' +
          ' class="link-danger">Actualizar</a>';

        pacienteRow.innerHTML =
          '<td class="td_id">' +
          paciente.id +
          "</td>" +
          '<td class="td_apellido">' +
          paciente.apellido.toUpperCase() +
          "</td>" +
          '<td class="td_nombre">' +
          paciente.nombre.toUpperCase() +
          "</td>" +
          "<td>" +
          deleteLink +
          "</td>" +
          "<td>" +
          updateLink +
          "</td>";
      }
    });
});
