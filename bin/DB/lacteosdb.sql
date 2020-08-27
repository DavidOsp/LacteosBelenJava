-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-07-2020 a las 23:05:38
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.2.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `lacteosdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuento_por_transporte`
--

CREATE TABLE `descuento_por_transporte` (
  `id` int(11) NOT NULL,
  `id_kilometros` int(11) NOT NULL,
  `id_vehiculos` int(11) NOT NULL,
  `descuento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `descuento_por_transporte`
--

INSERT INTO `descuento_por_transporte` (`id`, `id_kilometros`, `id_vehiculos`, `descuento`) VALUES
(1, 1, 1, -12),
(2, 1, 2, -22),
(3, 1, 3, -34),
(4, 1, 4, -44),
(5, 1, 5, -73),
(6, 2, 1, -12),
(7, 2, 2, -29),
(8, 2, 3, -40),
(9, 2, 4, -53),
(10, 2, 5, -95),
(11, 3, 1, -13),
(12, 3, 2, -34),
(13, 3, 3, -45),
(14, 3, 4, -62),
(15, 3, 5, -108),
(16, 4, 1, -16),
(17, 4, 2, -39),
(18, 4, 3, -53),
(19, 4, 4, -70),
(20, 4, 5, -124),
(21, 5, 1, -16),
(22, 5, 2, -41),
(23, 5, 3, -59),
(24, 5, 4, -81),
(25, 5, 5, -141);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kilometros`
--

CREATE TABLE `kilometros` (
  `id` int(11) NOT NULL,
  `rango` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `kilometros`
--

INSERT INTO `kilometros` (`id`, `rango`) VALUES
(1, '0 - 25'),
(2, '26 - 50'),
(3, '51 - 75'),
(4, '76 - 100'),
(5, '101 - 125'),
(6, '126 - 150'),
(7, '151 - 175'),
(8, '176 - 200'),
(9, '201 - 225'),
(10, '226 - 250'),
(11, '251 - 275'),
(12, '276 - 300'),
(13, '301 - 325'),
(14, '326 - 350'),
(15, '351 - 375'),
(16, '376 - 400');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `id` int(11) NOT NULL,
  `tipo` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`id`, `tipo`) VALUES
(1, 'TRACTO CAMION'),
(2, 'CAMION GRANDE TANQUE'),
(3, 'CAMION GRANDE CANTINAS'),
(4, 'CAMION PEQUEÑO TANQUE'),
(5, 'CAMION PEQUEÑO CANTINAS');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `descuento_por_transporte`
--
ALTER TABLE `descuento_por_transporte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_vehiculos` (`id_vehiculos`),
  ADD KEY `id_kilometros` (`id_kilometros`);

--
-- Indices de la tabla `kilometros`
--
ALTER TABLE `kilometros`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `descuento_por_transporte`
--
ALTER TABLE `descuento_por_transporte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `kilometros`
--
ALTER TABLE `kilometros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `descuento_por_transporte`
--
ALTER TABLE `descuento_por_transporte`
  ADD CONSTRAINT `descuento_por_transporte_ibfk_1` FOREIGN KEY (`id_vehiculos`) REFERENCES `vehiculos` (`id`),
  ADD CONSTRAINT `descuento_por_transporte_ibfk_2` FOREIGN KEY (`id_kilometros`) REFERENCES `kilometros` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
