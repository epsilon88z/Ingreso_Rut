<?php
include("cn.php");

$rut = $_GET["rut"] ?? $_POST["rut"] ?? "";
$password = $_GET["password"] ?? $_POST["password"] ?? "";

if (empty($rut) || empty($password)) {
    echo "Faltan datos requeridos (RUT o contraseña)";
    exit;
}

if (strlen($rut) < 8 || strlen($rut) > 10) {
    echo "Longitud de RUT inválida";
    exit;
}

$stmt = $conexion->prepare("INSERT INTO usuarios(rut, password) VALUES (?, ?)");
$stmt->bind_param("ss", $rut, $password);

if ($stmt->execute()) {
    echo "Registro insertado exitoso";
} else {
    if ($conexion->errno === 1062) {
        echo "El RUT ya se encuentra registrado";
    } else {
        echo "Fallo en el registro: " . $conexion->error;
    }
}

$stmt->close();
$conexion->close();
?>