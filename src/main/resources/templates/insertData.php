<?php

if($_SERVER["REQUEST_METHOD"] == "POST"){
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "workshop";

    $conn = new mysqli($servername, $username, $password, $dbname);

    if($conn->connect_error) {
        die("Conexión fallida: " . $conn->connect_error);
    }

    $ownerName = mysqli_real_escape_string($conn, $_POST['ownerName']);
    $carBrand = mysqli_real_escape_string($conn, $_POST['carBrand']);
    $carModel = mysqli_real_escape_string($conn, $_POST['carModel']);
    $mechanicName = mysqli_real_escape_string($conn, $_POST['mechanicName']);
    $workshopLocation = mysqli_real_escape_string($conn, $_POST['workshopLocation']);

    $sql = "INSERT INTO tabla_citas (ownerName, carBrand, carModel, mechanicName, workshopLocation) VALUES ('$ownerName', '$carBrand', '$carModel', '$mechanicName', '$workshopLocation')";

    if($conn->query($sql) === TRUE){
        echo "Datos insertados correctamente";
    }else{
        echo "Error al insertar datos: " . $conn->error;
    }

    $conn->close();
}

?>