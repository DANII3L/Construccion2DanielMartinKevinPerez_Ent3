-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-04-2024 a las 06:40:49
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `veterinary`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` int(11) NOT NULL,
  `petId` int(11) NOT NULL,
  `ownerId` bigint(20) NOT NULL,
  `producto` varchar(30) NOT NULL,
  `valor` double NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `orderId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `petId`, `ownerId`, `producto`, `valor`, `cantidad`, `fecha`, `orderId`) VALUES
(1, 1, 12, 'Prueba medic', 60000, 4, '2024-03-15', 0),
(6, 7, 123122, 'PRUEBA', 2000, 20, '2024-04-25', 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historia`
--

CREATE TABLE `historia` (
  `fecha` date NOT NULL,
  `mascota` int(11) NOT NULL,
  `medico` bigint(20) NOT NULL,
  `motivo` text NOT NULL,
  `sintomatologia` text NOT NULL,
  `procedimiento` varchar(30) NOT NULL,
  `medicamento` varchar(30) NOT NULL,
  `orderId` int(11) NOT NULL,
  `vacunacion` text NOT NULL,
  `alergia` text NOT NULL,
  `detalles_procedimiento` text NOT NULL,
  `diagnosis` varchar(30) NOT NULL,
  `dosis` varchar(30) NOT NULL,
  `ordercancelation` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `historia`
--

INSERT INTO `historia` (`fecha`, `mascota`, `medico`, `motivo`, `sintomatologia`, `procedimiento`, `medicamento`, `orderId`, `vacunacion`, `alergia`, `detalles_procedimiento`, `diagnosis`, `dosis`, `ordercancelation`) VALUES
('2024-04-25', 7, 120301203, 'Prueba', 'Prueba', 'Prueba', 'Prueba', 20, 'Prueba', 'Prueba', 'Prueba', 'Prueba', 'Prueba', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascota`
--

CREATE TABLE `mascota` (
  `id` int(11) NOT NULL,
  `propietario` bigint(10) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `edad` int(11) NOT NULL,
  `peso` double NOT NULL,
  `raza` varchar(10) NOT NULL,
  `especie` varchar(10) NOT NULL,
  `caracteristicas` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mascota`
--

INSERT INTO `mascota` (`id`, `propietario`, `nombre`, `edad`, `peso`, `raza`, `especie`, `caracteristicas`) VALUES
(1, 12, 'Firulais', 2, 2, 'Doberman', 'Perro', 'Grande, gordo'),
(7, 123122, 'Dakota', 2, 2, 'Prueba', 'Prueba', 'Prueba');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orden`
--

CREATE TABLE `orden` (
  `id` int(11) NOT NULL,
  `mascota` int(11) NOT NULL,
  `propietario` bigint(20) NOT NULL,
  `medico` bigint(20) NOT NULL,
  `medicamento` varchar(30) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `orden`
--

INSERT INTO `orden` (`id`, `mascota`, `propietario`, `medico`, `medicamento`, `fecha`) VALUES
(8, 1, 12, 1, 'PRUEBA', '2024-03-15'),
(12, 7, 123122, 120301203, 'Prueba', '2024-04-25'),
(13, 7, 123122, 120301203, 'Prueba', '2024-04-25'),
(14, 7, 123122, 120301203, 'Prueba', '2024-04-25'),
(15, 7, 123122, 120301203, 'Prueba', '2024-04-25'),
(16, 7, 123122, 120301203, 'Prueba', '2024-04-25'),
(17, 7, 123122, 120301203, 'Prueba', '2024-04-25'),
(18, 7, 123122, 120301203, 'Prueba', '2024-04-25'),
(19, 7, 123122, 120301203, 'Prueba', '2024-04-25'),
(20, 7, 123122, 120301203, 'Prueba', '2024-04-25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `cedula` bigint(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `rol` varchar(20) NOT NULL,
  `edad` int(3) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`cedula`, `nombre`, `rol`, `edad`, `username`, `password`) VALUES
(1, 'Daniel M', 'Veterinario', 21, 'DMARTIN', '123'),
(12, 'Felipe', 'Dueño', 21, 'FELIPE', '123'),
(1234, 'Felipe', 'Vendedor', 19, 'FMARTIN', '123'),
(12345, 'Admin', 'Administrador', 20, 'Admin', '123'),
(123122, 'Jeison', 'Dueño', 21, 'JEISON', '123'),
(1234567, 'Juan', 'Dueño', 15, 'JMARTIN', '123'),
(120301203, 'Juanes', 'Veterinario', 19, 'Juanes', '123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesion`
--

CREATE TABLE `sesion` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `role` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sesion`
--

INSERT INTO `sesion` (`id`, `username`, `role`) VALUES
(39, 'Juanes', 'Veterinario'),
(40, 'Juanes', 'Veterinario'),
(41, 'Juanes', 'Veterinario'),
(42, 'Juanes', 'Veterinario'),
(43, 'Juanes', 'Veterinario'),
(44, 'Juanes', 'Veterinario'),
(45, 'Juanes', 'Veterinario'),
(46, 'Juanes', 'Veterinario'),
(47, 'Juanes', 'Veterinario'),
(48, 'Juanes', 'Veterinario'),
(49, 'Juanes', 'Veterinario'),
(50, 'Juanes', 'Veterinario'),
(51, 'Juanes', 'Veterinario'),
(52, 'Juanes', 'Veterinario'),
(53, 'Juanes', 'Veterinario'),
(54, 'Juanes', 'Veterinario'),
(55, 'Juanes', 'Veterinario'),
(56, 'Juanes', 'Veterinario'),
(59, 'FMARTIN', 'Vendedor'),
(60, 'FMARTIN', 'Vendedor'),
(61, 'FMARTIN', 'Vendedor'),
(62, 'FMARTIN', 'Vendedor');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`),
  ADD KEY `factura_mascota` (`petId`) USING BTREE,
  ADD KEY `factura_propietario` (`ownerId`) USING BTREE,
  ADD KEY `factura_orden` (`orderId`);

--
-- Indices de la tabla `historia`
--
ALTER TABLE `historia`
  ADD PRIMARY KEY (`fecha`);

--
-- Indices de la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mascota_propietario` (`propietario`) USING BTREE;

--
-- Indices de la tabla `orden`
--
ALTER TABLE `orden`
  ADD PRIMARY KEY (`id`),
  ADD KEY `orden_mascota` (`mascota`),
  ADD KEY `orden_propietario` (`propietario`),
  ADD KEY `orden_veterinario` (`medico`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`cedula`);

--
-- Indices de la tabla `sesion`
--
ALTER TABLE `sesion`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `mascota`
--
ALTER TABLE `mascota`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `orden`
--
ALTER TABLE `orden`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `sesion`
--
ALTER TABLE `sesion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_mascota` FOREIGN KEY (`petId`) REFERENCES `mascota` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `factura_propietario` FOREIGN KEY (`ownerId`) REFERENCES `persona` (`cedula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD CONSTRAINT `mascota_ibfk_1` FOREIGN KEY (`propietario`) REFERENCES `persona` (`cedula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `orden`
--
ALTER TABLE `orden`
  ADD CONSTRAINT `orden_mascota` FOREIGN KEY (`mascota`) REFERENCES `mascota` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orden_propietario` FOREIGN KEY (`propietario`) REFERENCES `persona` (`cedula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orden_veterinario` FOREIGN KEY (`medico`) REFERENCES `persona` (`cedula`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
