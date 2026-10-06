<?php
include 'cn.php';

$rut = $_GET['rut'] ?? $_POST['rut'] ?? '';

if (empty($rut)) {
    echo json_encode([]);
    exit;
}

$stmt = $conexion->prepare("SELECT rut, password FROM usuarios WHERE rut = ?");
$stmt->bind_param("s", $rut);
$stmt->execute();
$resultado = $stmt->get_result();

$usuario = [];
while ($fila = $resultado->fetch_assoc()) {
    $usuario[] = $fila;
}

header('Content-Type: application/json; charset=utf-8');
echo json_encode($usuario);

$stmt->close();
$conexion->close();
?>