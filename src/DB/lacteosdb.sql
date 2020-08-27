-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-08-2020 a las 14:04:21
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
-- Estructura de tabla para la tabla `cadena_de_frio`
--

CREATE TABLE `cadena_de_frio` (
  `id` int(11) NOT NULL,
  `id_bacterias` int(11) NOT NULL,
  `bonificacion` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cadena_de_frio`
--

INSERT INTO `cadena_de_frio` (`id`, `id_bacterias`, `bonificacion`) VALUES
(1, 1, '15.00'),
(2, 2, '15.00'),
(3, 3, '15.00'),
(4, 4, '10.00'),
(5, 5, '10.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `certificado_bpg`
--

CREATE TABLE `certificado_bpg` (
  `id` int(11) NOT NULL,
  `enunciado` varchar(100) NOT NULL,
  `valor` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `certificado_bpg`
--

INSERT INTO `certificado_bpg` (`id`, `enunciado`, `valor`) VALUES
(1, 'Con Certificacion en BPG', '14.50'),
(2, 'Sin Certificacion en BPG\r\n', '0.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `certificado_hato`
--

CREATE TABLE `certificado_hato` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `valor` decimal(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `certificado_hato`
--

INSERT INTO `certificado_hato` (`id`, `descripcion`, `valor`) VALUES
(1, 'Hato libre de una enfermedad', '14.50'),
(2, 'Hato libre de dos enfermedades', '29.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuento_por_transporte`
--

CREATE TABLE `descuento_por_transporte` (
  `id` int(11) NOT NULL,
  `id_kilometros` int(11) NOT NULL,
  `id_vehiculos` int(11) NOT NULL,
  `descuento` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `descuento_por_transporte`
--

INSERT INTO `descuento_por_transporte` (`id`, `id_kilometros`, `id_vehiculos`, `descuento`) VALUES
(1, 1, 1, '-12.00'),
(2, 1, 2, '-22.00'),
(3, 1, 3, '-34.00'),
(4, 1, 4, '-44.00'),
(5, 1, 5, '-73.00'),
(6, 2, 1, '-12.00'),
(7, 2, 2, '-29.00'),
(8, 2, 3, '-40.00'),
(9, 2, 4, '-53.00'),
(10, 2, 5, '-95.00'),
(11, 3, 1, '-13.00'),
(12, 3, 2, '-34.00'),
(13, 3, 3, '-45.00'),
(14, 3, 4, '-62.00'),
(15, 3, 5, '-108.00'),
(16, 4, 1, '-16.00'),
(17, 4, 2, '-39.00'),
(18, 4, 3, '-53.00'),
(19, 4, 4, '-70.00'),
(20, 4, 5, '-124.00'),
(21, 5, 1, '-16.00'),
(22, 5, 2, '-41.00'),
(23, 5, 3, '-59.00'),
(24, 5, 4, '-81.00'),
(25, 5, 5, '-141.00'),
(26, 6, 1, '-21.00'),
(27, 6, 2, '-45.00'),
(28, 6, 3, '-67.00'),
(29, 6, 4, '-90.00'),
(30, 6, 5, '-155.00'),
(31, 7, 1, '-22.00'),
(32, 7, 2, '-52.00'),
(33, 7, 3, '-70.00'),
(34, 7, 4, '-101.00'),
(35, 7, 5, '-173.00'),
(36, 8, 1, '-23.00'),
(37, 8, 2, '-57.00'),
(38, 8, 3, '-78.00'),
(39, 8, 4, '-108.00'),
(40, 8, 5, '-187.00'),
(41, 9, 1, '-25.00'),
(42, 9, 2, '-59.00'),
(43, 9, 3, '-82.00'),
(44, 9, 4, '-115.00'),
(45, 9, 5, '-206.00'),
(46, 10, 1, '-29.00'),
(47, 10, 2, '-64.00'),
(48, 10, 3, '-90.00'),
(49, 10, 4, '-125.00'),
(50, 10, 5, '-221.00'),
(51, 11, 1, '-29.00'),
(52, 11, 2, '-68.00'),
(53, 11, 3, '-98.00'),
(54, 11, 4, '-138.00'),
(55, 11, 5, '-236.00'),
(56, 12, 1, '-32.00'),
(57, 12, 2, '-72.00'),
(58, 12, 3, '-103.00'),
(59, 12, 4, '-143.00'),
(60, 12, 5, '-255.00'),
(61, 13, 1, '-34.00'),
(62, 13, 2, '-78.00'),
(63, 13, 3, '-112.00'),
(64, 13, 4, '-153.00'),
(65, 13, 5, '-270.00'),
(66, 14, 1, '-38.00'),
(67, 14, 2, '-81.00'),
(68, 14, 3, '-115.00'),
(69, 14, 4, '-166.00'),
(70, 14, 5, '-285.00'),
(71, 15, 1, '-39.00'),
(72, 15, 2, '-84.00'),
(73, 15, 3, '-122.00'),
(74, 15, 4, '-173.00'),
(75, 15, 5, '-304.00'),
(76, 16, 1, '-39.00'),
(77, 16, 2, '-90.00'),
(78, 16, 3, '-130.00'),
(79, 16, 4, '-182.00'),
(80, 16, 5, '-320.00');

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
-- Estructura de tabla para la tabla `valores_de_higiene`
--

CREATE TABLE `valores_de_higiene` (
  `id` int(11) NOT NULL,
  `valores` varchar(100) NOT NULL,
  `valor_a_pagar` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `valores_de_higiene`
--

INSERT INTO `valores_de_higiene` (`id`, `valores`, `valor_a_pagar`) VALUES
(1, '0 - 25,000\r\n', '97.00'),
(2, '25,001 - 50,000', '82.00'),
(3, '50,001 - 100,000', '66.00'),
(4, '100,001 - 150,000', '49.00'),
(5, '150,001 - 175,000', '31.00'),
(6, '175,001 - 200,000', '0.00'),
(7, '200,001 - 300,000', '-17.00'),
(8, '300,001 - 400,000', '-31.00'),
(9, '400,001 - 500,000', '-49.00'),
(10, '500,001 - 600,000', '-66.00'),
(11, 'Mayores a 600.000', '-82.00');

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
-- Indices de la tabla `cadena_de_frio`
--
ALTER TABLE `cadena_de_frio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kilometros` (`id_bacterias`);

--
-- Indices de la tabla `certificado_bpg`
--
ALTER TABLE `certificado_bpg`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `certificado_hato`
--
ALTER TABLE `certificado_hato`
  ADD PRIMARY KEY (`id`);

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
-- Indices de la tabla `valores_de_higiene`
--
ALTER TABLE `valores_de_higiene`
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
-- AUTO_INCREMENT de la tabla `cadena_de_frio`
--
ALTER TABLE `cadena_de_frio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `certificado_bpg`
--
ALTER TABLE `certificado_bpg`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `certificado_hato`
--
ALTER TABLE `certificado_hato`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `descuento_por_transporte`
--
ALTER TABLE `descuento_por_transporte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT de la tabla `kilometros`
--
ALTER TABLE `kilometros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `valores_de_higiene`
--
ALTER TABLE `valores_de_higiene`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cadena_de_frio`
--
ALTER TABLE `cadena_de_frio`
  ADD CONSTRAINT `cadena_de_frio_ibfk_1` FOREIGN KEY (`id_bacterias`) REFERENCES `valores_de_higiene` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
