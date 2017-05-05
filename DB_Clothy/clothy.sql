-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-05-2017 a las 16:31:15
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clothy`
--

-- --------------------------------------------------------

CREATE DATABASE Clothy character set utf8 collate utf8_general_ci;

USE Clothy;



--
-- Estructura Stand-in para la vista `all_articles`
--
CREATE TABLE `all_articles` (
`id` int(10) unsigned
,`nombre_articulo` varchar(50)
,`marca` varchar(50)
,`categoria` varchar(50)
,`precio` float(8,2)
,`existencias` int(11)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

CREATE TABLE `articulos` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` float(8,2) NOT NULL,
  `categoria` int(10) UNSIGNED DEFAULT NULL,
  `marca` int(10) UNSIGNED DEFAULT NULL,
  `existencias` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`id`, `nombre`, `descripcion`, `precio`, `categoria`, `marca`, `existencias`) VALUES
(1, 'Camisa Negra', 'Rayas blancas', 19.90, 1, 2, 19),
(2, 'Camisa Negra', '', 14.90, 1, 1, 15),
(3, 'Camisa Blanca', 'Cuello alto', 12.95, 1, 4, 20),
(4, 'Camisa Blanca', 'Rayas Negras', 18.90, 1, 5, 25),
(5, 'Camisa Azul', 'Circulos', 17.90, 1, 2, 15),
(6, 'Camisa Rosa', '', 14.90, 1, 1, 21),
(7, 'Polo Negro', '', 29.90, 2, 1, 23),
(8, 'Polo Blanco', '', 14.95, 2, 3, 28),
(9, 'Polo Azul', 'Azul Marino', 18.90, 2, 4, 15),
(10, 'Polo Verde', 'Verde Pistacho', 27.90, 2, 6, 14),
(11, 'Polo Rosa', '', 17.90, 2, 5, 9),
(12, 'Camiseta Street', '', 9.90, 3, 2, 33),
(13, 'Camiseta Age', '', 14.95, 3, 4, 37),
(14, 'Camiseta Casual', 'Azul Marino', 12.90, 3, 5, 42),
(15, 'Camiseta Mountain', 'Verde Pistacho', 4.99, 3, 1, 22),
(16, 'Camiseta Bop', '', 7.90, 3, 3, 29),
(17, 'Chaqueta Piel', 'Cuero del bueno', 59.90, 4, 1, 15),
(18, 'Chaqueta Vaquera', '', 74.95, 4, 3, 10),
(19, 'Camiseta Casual', 'Azul', 32.90, 4, 5, 12),
(20, 'Chaqueta Traje', 'Rosa', 44.99, 4, 1, 7),
(21, 'Chaqueta Faldon', 'Gabardina', 47.90, 4, 2, 9),
(22, 'Traje Boda', 'Negro', 79.90, 5, 8, 10),
(23, 'Traje Fiesta', 'Granate', 94.95, 5, 3, 9),
(24, 'Traje Casual', 'Azul Marino', 52.90, 5, 1, 7),
(25, 'Jeans Vaqueros', 'Desgastados', 20.90, 6, 1, 20),
(26, 'Jeans Urban', '', 24.95, 6, 2, 22),
(27, 'Jeans Azul', 'Azul roto', 22.90, 6, 9, 22),
(28, 'Jeans Traje', '', 34.99, 6, 10, 17),
(29, 'Jeans Squish', 'Negro', 27.90, 6, 4, 19),
(30, 'Bermudas Vaqueras', '', 20.90, 7, 9, 15),
(31, 'Bermudas Urban', '', 24.95, 7, 10, 15),
(32, 'Bermudas Classic', '', 22.90, 7, 1, 17),
(33, 'Bermudas Traje', '', 24.99, 7, 2, 19),
(34, 'Bermudas Bougn', 'Blanco', 27.90, 7, 3, 22),
(35, 'Bañador Street', 'Lunares', 9.90, 8, 1, 15),
(36, 'Bañador QS', 'Tattoo', 7.95, 8, 2, 15),
(37, 'Bañador Classic', '', 8.90, 8, 3, 17),
(38, 'Bañador Ko', 'Negro', 14.99, 8, 4, 19),
(39, 'Bañador Vint', 'Blanco rayas', 17.90, 8, 5, 22),
(40, 'Zapatos Mocasin', 'Negro', 40.90, 9, 1, 15),
(41, 'Zapatos Urban', 'Blancas', 34.95, 9, 2, 15),
(42, 'Zapatos Classic', '', 42.90, 9, 4, 17),
(43, 'Zapatos Suit', '', 54.99, 9, 5, 19),
(44, 'Zapatos Mocasin', 'Blanco', 67.90, 9, 3, 22),
(45, 'Zapatillas Sport', 'Negro', 30.90, 10, 1, 25),
(46, 'Zapatillas Urban', 'Blancas', 24.95, 10, 2, 12),
(47, 'Zapatillas Classic', '', 32.90, 10, 4, 13),
(48, 'Zapatillas OhG', '', 44.99, 10, 5, 17),
(49, 'Zapatillas Verb', 'Blanco', 37.90, 10, 3, 24),
(50, 'Chaqueta Soft', 'Chaleco', 29.50, 4, 7, 15),
(51, 'Polo', 'Amarillo', 29.50, 2, 7, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Camisas', 'Manga larga'),
(2, 'Polos', 'Manga Corta'),
(3, 'Camisetas', ''),
(4, 'Chaquetas', ''),
(5, 'Trajes', 'Conjuntos'),
(6, 'Jeans', 'Casual'),
(7, 'Bermudas', ''),
(8, 'Bañadores', ''),
(9, 'Zapatos', 'Casual y vestir'),
(10, 'Deportes', 'Zapatillas futbol, correr, etc...');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) UNSIGNED NOT NULL,
  `nif` varchar(15) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) DEFAULT '',
  `direccion` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefono` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `id` int(11) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) DEFAULT '',
  `direccion` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` varchar(25) NOT NULL,
  `id_login` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`id`, `nombre`, `apellidos`, `direccion`, `email`, `telefono`, `id_login`, `password`) VALUES
(1, 'admin', '*', '*', '*', '*', 'admin', '*'),
(2, 'Marta', 'Sanchez', 'Av.de los Naranjos n14 pta23', 'marta@gmail.com', '96 154 98 65', 'marta', 'marta123'),
(3, 'Pepe', 'Ramirez', 'C/Salvador n23 pta43', 'pepe@gmail.com', '676 45 12 52', 'pepe', 'pepe123'),
(4, 'Sonia', 'Peris', 'C/Carrer n2 pta5', 'sonia@gmail.com', '96 345 64 78', 'sonia', 'sonia123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lineas_ventas`
--

CREATE TABLE `lineas_ventas` (
  `id` int(10) UNSIGNED NOT NULL,
  `articulo` int(10) UNSIGNED NOT NULL,
  `venta_id` int(10) UNSIGNED NOT NULL,
  `descuento` float(8,2) DEFAULT '0.00',
  `cantidad` int(5) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`id`, `nombre`) VALUES
(1, 'Ralph Lauren'),
(2, 'Valentino'),
(3, 'Lacoste'),
(4, 'Gucci'),
(5, 'Prada'),
(6, 'Tommy Hilfiger'),
(7, 'Emporio Armani'),
(8, 'Hugo Boss'),
(9, 'Pepe Jeans'),
(10, 'Levis');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(10) UNSIGNED NOT NULL,
  `fecha` datetime NOT NULL,
  `descripcion` text NOT NULL,
  `empleado` int(10) UNSIGNED NOT NULL,
  `estado` varchar(10) NOT NULL DEFAULT 'Pendiente'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `stocktotal_categorias`
--
CREATE TABLE `stocktotal_categorias` (
`nombre` varchar(50)
,`Stock_Total` decimal(32,0)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `stock_total`
--
CREATE TABLE `stock_total` (
`Stock_Total` decimal(32,0)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tallas`
--

CREATE TABLE `tallas` (
  `id` int(5) NOT NULL,
  `valor_talla` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tallas`
--

INSERT INTO `tallas` (`id`, `valor_talla`) VALUES
(1, 'S'),
(2, 'M'),
(3, 'L'),
(4, 'XL'),
(5, 'XXL'),
(6, '39'),
(7, '41'),
(8, '43'),
(9, '45');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tallas_articulos_map`
--

CREATE TABLE `tallas_articulos_map` (
  `id_talla` int(5) NOT NULL,
  `id_articulo` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tallas_articulos_map`
--

INSERT INTO `tallas_articulos_map` (`id_talla`, `id_articulo`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25),
(1, 26),
(1, 27),
(1, 28),
(1, 29),
(1, 30),
(1, 31),
(1, 32),
(1, 33),
(1, 34),
(1, 35),
(1, 36),
(1, 37),
(1, 38),
(1, 50),
(1, 51),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(2, 11),
(2, 12),
(2, 13),
(2, 14),
(2, 15),
(2, 16),
(2, 17),
(2, 18),
(2, 19),
(2, 20),
(2, 21),
(2, 22),
(2, 23),
(2, 24),
(2, 25),
(2, 26),
(2, 27),
(2, 28),
(2, 29),
(2, 30),
(2, 31),
(2, 32),
(2, 33),
(2, 34),
(2, 35),
(2, 37),
(2, 39),
(2, 50),
(2, 51),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 6),
(3, 7),
(3, 8),
(3, 9),
(3, 10),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(3, 15),
(3, 16),
(3, 17),
(3, 18),
(3, 19),
(3, 20),
(3, 21),
(3, 22),
(3, 23),
(3, 24),
(3, 25),
(3, 26),
(3, 27),
(3, 28),
(3, 29),
(3, 30),
(3, 31),
(3, 32),
(3, 33),
(3, 34),
(3, 35),
(3, 36),
(3, 38),
(3, 39),
(3, 50),
(3, 51),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(4, 5),
(4, 6),
(4, 7),
(4, 8),
(4, 9),
(4, 10),
(4, 11),
(4, 12),
(4, 13),
(4, 14),
(4, 15),
(4, 16),
(4, 17),
(4, 18),
(4, 19),
(4, 20),
(4, 21),
(4, 22),
(4, 23),
(4, 24),
(4, 25),
(4, 26),
(4, 27),
(4, 28),
(4, 29),
(4, 30),
(4, 31),
(4, 32),
(4, 33),
(4, 34),
(4, 38),
(4, 39),
(6, 40),
(6, 41),
(6, 42),
(6, 43),
(6, 44),
(6, 45),
(6, 46),
(6, 47),
(6, 48),
(6, 49),
(7, 40),
(7, 41),
(7, 42),
(7, 43),
(7, 44),
(7, 45),
(7, 46),
(7, 47),
(7, 48),
(7, 49),
(8, 40),
(8, 41),
(8, 42),
(8, 43),
(8, 44),
(8, 45),
(8, 46),
(8, 47),
(8, 48),
(8, 49),
(9, 40),
(9, 41),
(9, 42),
(9, 43),
(9, 44),
(9, 45),
(9, 46),
(9, 47),
(9, 48),
(9, 49);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `total_articles_with_sizes`
--
CREATE TABLE `total_articles_with_sizes` (
`nombre` varchar(50)
,`valor_talla` varchar(15)
,`marca` varchar(50)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id` int(10) UNSIGNED NOT NULL,
  `cliente` int(10) UNSIGNED NOT NULL,
  `empleado` int(10) UNSIGNED NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `metodo_pago` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura para la vista `all_articles`
--
DROP TABLE IF EXISTS `all_articles`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `all_articles`  AS  (select `a`.`id` AS `id`,`a`.`nombre` AS `nombre_articulo`,`m`.`nombre` AS `marca`,`c`.`nombre` AS `categoria`,`a`.`precio` AS `precio`,`a`.`existencias` AS `existencias` from ((`articulos` `a` join `categorias` `c`) join `marcas` `m`) where ((`c`.`id` = `a`.`categoria`) and (`m`.`id` = `a`.`marca`)) order by 1) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `stocktotal_categorias`
--
DROP TABLE IF EXISTS `stocktotal_categorias`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `stocktotal_categorias`  AS  (select `c`.`nombre` AS `nombre`,sum(`a`.`existencias`) AS `Stock_Total` from (`articulos` `a` join `categorias` `c`) where (`c`.`id` = `a`.`categoria`) group by `a`.`categoria` order by 1) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `stock_total`
--
DROP TABLE IF EXISTS `stock_total`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `stock_total`  AS  (select sum(`articulos`.`existencias`) AS `Stock_Total` from `articulos`) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `total_articles_with_sizes`
--
DROP TABLE IF EXISTS `total_articles_with_sizes`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `total_articles_with_sizes`  AS  (select `a`.`nombre` AS `nombre`,`t`.`valor_talla` AS `valor_talla`,`m`.`nombre` AS `marca` from (((`tallas_articulos_map` `tam` join `articulos` `a`) join `tallas` `t`) join `marcas` `m`) where ((`a`.`id` = `tam`.`id_articulo`) and (`t`.`id` = `tam`.`id_talla`) and (`m`.`id` = `a`.`marca`)) order by 3,1) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_art_marca` (`marca`),
  ADD KEY `fk_art_categoria` (`categoria`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `lineas_ventas`
--
ALTER TABLE `lineas_ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_lin_ven_art` (`articulo`),
  ADD KEY `fk_lin_ven_venta` (`venta_id`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ped_empleado` (`empleado`);

--
-- Indices de la tabla `tallas`
--
ALTER TABLE `tallas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tallas_articulos_map`
--
ALTER TABLE `tallas_articulos_map`
  ADD PRIMARY KEY (`id_talla`,`id_articulo`),
  ADD KEY `fk_tallas_art_map_articulo` (`id_articulo`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ventas_cliente` (`cliente`),
  ADD KEY `fk_ventas_empleado` (`empleado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulos`
--
ALTER TABLE `articulos`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `lineas_ventas`
--
ALTER TABLE `lineas_ventas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD CONSTRAINT `fk_art_categoria` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`id`),
  ADD CONSTRAINT `fk_art_marca` FOREIGN KEY (`marca`) REFERENCES `marcas` (`id`);

--
-- Filtros para la tabla `lineas_ventas`
--
ALTER TABLE `lineas_ventas`
  ADD CONSTRAINT `fk_lin_ven_art` FOREIGN KEY (`articulo`) REFERENCES `articulos` (`id`),
  ADD CONSTRAINT `fk_lin_ven_venta` FOREIGN KEY (`venta_id`) REFERENCES `ventas` (`id`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_ped_empleado` FOREIGN KEY (`empleado`) REFERENCES `empleados` (`id`);

--
-- Filtros para la tabla `tallas_articulos_map`
--
ALTER TABLE `tallas_articulos_map`
  ADD CONSTRAINT `fk_tallas_art_map_articulo` FOREIGN KEY (`id_articulo`) REFERENCES `articulos` (`id`),
  ADD CONSTRAINT `fk_tallas_art_map_talla` FOREIGN KEY (`id_talla`) REFERENCES `tallas` (`id`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `fk_ventas_cliente` FOREIGN KEY (`cliente`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `fk_ventas_empleado` FOREIGN KEY (`empleado`) REFERENCES `empleados` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
