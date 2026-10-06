<?php

$host = "database-1.ccdltyvbtwlg.us-east-1.rds.amazonaws.com";
$usuario = "pi";
$password = "20262026";
$base_datos = "test1";

$conexion = new mysqli($host, $usuario, $password, $base_datos);

$c = $conexion;

if ($conexion->connect_error) {
    die("Fallo en la conexión: " . $conexion->connect_error);
}

$conexion->set_charset("utf8mb4");
?>
