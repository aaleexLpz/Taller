<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "workshop";

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

$sql = "SELECT c.nombre AS cliente, ca.brand AS coche, me.nombre AS mecanico, t.location AS taller
        FROM cliente c
        JOIN car ca ON c.car = ca.id
        JOIN mechanic me ON ca.id = me.car
        JOIN taller t ON me.dni = t.mechanic";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $data = array();
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
    echo json_encode($data);
} else {
    echo "No hay resultados";
}
$conn->close();
?>