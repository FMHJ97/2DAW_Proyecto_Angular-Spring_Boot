-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-02-2025 a las 21:40:53
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `escritores`
--
DROP DATABASE IF EXISTS `escritores`;
CREATE DATABASE IF NOT EXISTS `escritores` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `escritores`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor`
--

CREATE TABLE `autor` (
  `id` int(11) NOT NULL,
  `full_name` varchar(150) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `fecha_fallecimiento` date DEFAULT NULL,
  `pais` varchar(50) NOT NULL,
  `biografia` text NOT NULL,
  `imagen` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`id`, `nombre`) VALUES
(1, 'Acción'),
(2, 'Aventura'),
(3, 'Ciencia Ficción'),
(4, 'Comedia'),
(5, 'Drama'),
(6, 'Fantasía'),
(7, 'Ficción'),
(8, 'Histórico'),
(9, 'Misterio'),
(10, 'No Ficción'),
(11, 'Poesía'),
(12, 'Policial'),
(13, 'Psicológico'),
(14, 'Romántico'),
(15, 'Suspense'),
(16, 'Terror'),
(17, 'Thriller');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero_obra`
--

CREATE TABLE `genero_obra` (
  `id` int(11) NOT NULL,
  `id_obra` int(11) NOT NULL,
  `id_genero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `obra`
--

CREATE TABLE `obra` (
  `id` int(11) NOT NULL,
  `id_autor` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `sinopsis` text NOT NULL,
  `imagen` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `obra_usuario`
--

CREATE TABLE `obra_usuario` (
  `id` int(11) NOT NULL,
  `id_obra` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `estado_lectura` enum('Pendiente','Completado') NOT NULL DEFAULT 'Pendiente',
  `favorito` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(150) NOT NULL,
  `sexo` enum('Hombre','Mujer','Sin especificar') NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `pais` enum('España','Inglaterra','Francia','Italia','Alemania') NOT NULL,
  `email` varchar(320) NOT NULL,
  `rol` enum('admin','usuario') NOT NULL DEFAULT 'usuario'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `usuario`, `password`, `full_name`, `sexo`, `fecha_nacimiento`, `pais`, `email`, `rol`) VALUES
(1, 'admin', 'admin', 'Francisco Manuel', 'Hombre', '1997-01-05', 'España', 'admin@gmail.com', 'admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `genero_obra`
--
ALTER TABLE `genero_obra`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_obra_genero` (`id_obra`,`id_genero`) USING BTREE,
  ADD KEY `fk2` (`id_genero`);

--
-- Indices de la tabla `obra`
--
ALTER TABLE `obra`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk1` (`id_autor`);

--
-- Indices de la tabla `obra_usuario`
--
ALTER TABLE `obra_usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_obra_usuario` (`id_obra`,`id_usuario`) USING BTREE,
  ADD KEY `fk5` (`id_usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `autor`
--
ALTER TABLE `autor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `genero_obra`
--
ALTER TABLE `genero_obra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `obra`
--
ALTER TABLE `obra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `obra_usuario`
--
ALTER TABLE `obra_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `genero_obra`
--
ALTER TABLE `genero_obra`
  ADD CONSTRAINT `fk2` FOREIGN KEY (`id_genero`) REFERENCES `genero` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk3` FOREIGN KEY (`id_obra`) REFERENCES `obra` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `obra`
--
ALTER TABLE `obra`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `obra_usuario`
--
ALTER TABLE `obra_usuario`
  ADD CONSTRAINT `fk4` FOREIGN KEY (`id_obra`) REFERENCES `obra` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk5` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
