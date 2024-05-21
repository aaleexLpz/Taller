function consultData() {
    fetch('consultData.php')
        .then(response => response.text())
        .then(data => {
            document.querySelector('#tablaClientes tbody').innerHTML = data;
        })
        .catch(error => console.error('Error al obtener los datos de la base de datos:', error));
}