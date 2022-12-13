window.addEventListener("load", function () {
  (function () {
    const url = "/turnos/lista";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        for (turno of data) {
          var table = document.getElementById("turnoTable");
          var turnoRow = table.insertRow();
          let tr_id = "tr_" + turno.id;
          turnoRow.id = tr_id;

          let deleteLink =
            "<button" +
            " id=" +
            '"' +
            "btn_delete_" +
            turno.id +
            '"' +
            ' type="button" onclick="deleteBy(' +
            turno.id +
            ')" class="btn btn-danger btn_delete">' +
            "&times" +
            "</button>";

          let updateLink =
            '<a id="a_update_' +
            turno.id +
            '"' +
            ' href="../update_turno.html"' +
            ' class="link-danger">Actualizar</a>';

          turnoRow.innerHTML =
            '<td class="td_id">' +
            turno.id +
            "</td>" +
            '<td class="td_paciente_id">' +
            turno.paciente.nombre.toUpperCase() +
            " " +
            turno.paciente.apellido +
            "</td>" +
            '<td class="td_odontologo_id">' +
            turno.odontologo.nombre.toUpperCase() +
            " " +
            turno.odontologo.apellido +
            "</td>" +
            '<td class="td_fecha">' +
            turno.fecha +
            "</td>" +
            '<td class="td_hora">' +
            turno.hora +
            "</td>" +
            "<td>" +
            deleteLink +
            "</td>" +
            "<td>" +
            updateLink +
            "</td>";
        }
      });
  })(function () {
    let pathname = window.location.pathname;
    if (pathname == "/verTurnos.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  });
});
