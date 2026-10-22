<?php
$conexion = new mysqli("localhost", "root", "", "clinicadb");

if ($conexion->connect_error) {
    die("Error de conexión: " . $conexion->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $nombre = $_POST['nombre'];
    $descripcion = $_POST['descripcion'];

    $sql = "INSERT INTO usuarios (nombre, descripcion) VALUES ('$nombre', '$descripcion')";

    if ($conexion->query($sql) === TRUE) {
        echo "<h1>¡Registro guardado con éxito en la base de datos!</h1>";
        echo "<a href='index.html'>Volver al formulario</a>";
    } else {
        echo "Error: " . $sql . "<br>" . $conexion->error;
    }
}

$conexion->close();
?>