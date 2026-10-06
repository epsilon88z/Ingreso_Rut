-- Estructura de tabla para la tabla `usuarios`
-- Base de datos: `sitio2`

CREATE TABLE IF NOT EXISTS `usuarios` (
  `rut` varchar(12) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`rut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;