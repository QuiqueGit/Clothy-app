-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-05-2017 a las 16:52:32
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

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `nif`, `nombre`, `apellidos`, `direccion`, `email`, `telefono`) VALUES
(1, '0000', 'Cliente00', 'asd', 'asd', 'asd', 'asd'),
(2, '21325645-N', 'Mario', 'Rodriguez Molina', 'C/Ruzafa n25', 'mario@gmail.com', '96 312 54 65'),
(3, '64521489-Y', 'Sandra', 'Velazquez Martín', 'C/Puente n10', 'sandra@hotmail.com', '649 52 12 32');

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
(1, 'admin', '*', '*', '*', '*', 'admin', ''),
(2, 'Marta', 'Sanchez', 'Av.Naranjos n14 pta23', 'marta@gmail.com', '96 154 98 65', 'marta', 'marta'),
(3, 'Pepe', 'Ramirez', 'C/Salvador n23 pta43', 'pepe@gmail.com', '676 45 12 52', 'pepe', 'pepe'),
(4, 'Sonia', 'Peris', 'C/Carrer n2 pta5', 'sonia@gmail.com', '96 345 64 78', 'sonia', 'sonia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lineas_ventas`
--

CREATE TABLE `lineas_ventas` (
  `id` int(10) UNSIGNED NOT NULL,
  `venta_id` int(10) UNSIGNED NOT NULL,
  `num_linea` int(10) UNSIGNED NOT NULL,
  `articulo` int(10) UNSIGNED NOT NULL,
  `cantidad` int(5) DEFAULT '1',
  `importe` float(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `lineas_ventas`
--

INSERT INTO `lineas_ventas` (`id`, `venta_id`, `num_linea`, `articulo`, `cantidad`, `importe`) VALUES
(13, 1, 1, 13, 1, 14.95),
(14, 1, 2, 40, 1, 40.90),
(15, 1, 3, 51, 1, 29.50),
(16, 2, 1, 9, 2, 37.80),
(17, 2, 2, 13, 1, 29.90),
(18, 3, 1, 6, 2, 29.80),
(19, 3, 2, 42, 1, 85.80),
(20, 4, 1, 1, 1, 19.90),
(21, 4, 2, 6, 1, 14.90),
(22, 4, 3, 45, 1, 30.90);

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
  `id_articulo` int(10) UNSIGNED NOT NULL,
  `stock` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tallas_articulos_map`
--

INSERT INTO `tallas_articulos_map` (`id_talla`, `id_articulo`, `stock`) VALUES
(1, 1, NULL),
(1, 2, NULL),
(1, 3, NULL),
(1, 4, NULL),
(1, 5, NULL),
(1, 6, NULL),
(1, 8, NULL),
(1, 9, NULL),
(1, 10, NULL),
(1, 11, NULL),
(1, 12, NULL),
(1, 13, NULL),
(1, 14, NULL),
(1, 15, NULL),
(1, 16, NULL),
(1, 17, NULL),
(1, 18, NULL),
(1, 19, NULL),
(1, 20, NULL),
(1, 21, NULL),
(1, 22, NULL),
(1, 23, NULL),
(1, 24, NULL),
(1, 25, NULL),
(1, 26, NULL),
(1, 27, NULL),
(1, 28, NULL),
(1, 29, NULL),
(1, 30, NULL),
(1, 31, NULL),
(1, 32, NULL),
(1, 33, NULL),
(1, 34, NULL),
(1, 35, NULL),
(1, 36, NULL),
(1, 37, NULL),
(1, 38, NULL),
(1, 50, NULL),
(1, 51, NULL),
(2, 1, NULL),
(2, 2, NULL),
(2, 3, NULL),
(2, 4, NULL),
(2, 5, NULL),
(2, 6, NULL),
(2, 7, NULL),
(2, 8, NULL),
(2, 9, NULL),
(2, 10, NULL),
(2, 11, NULL),
(2, 12, NULL),
(2, 13, NULL),
(2, 14, NULL),
(2, 15, NULL),
(2, 16, NULL),
(2, 17, NULL),
(2, 18, NULL),
(2, 19, NULL),
(2, 20, NULL),
(2, 21, NULL),
(2, 22, NULL),
(2, 23, NULL),
(2, 24, NULL),
(2, 25, NULL),
(2, 26, NULL),
(2, 27, NULL),
(2, 28, NULL),
(2, 29, NULL),
(2, 30, NULL),
(2, 31, NULL),
(2, 32, NULL),
(2, 33, NULL),
(2, 34, NULL),
(2, 35, NULL),
(2, 37, NULL),
(2, 39, NULL),
(2, 50, NULL),
(2, 51, NULL),
(3, 1, NULL),
(3, 2, NULL),
(3, 3, NULL),
(3, 4, NULL),
(3, 5, NULL),
(3, 6, NULL),
(3, 7, NULL),
(3, 8, NULL),
(3, 9, NULL),
(3, 10, NULL),
(3, 11, NULL),
(3, 12, NULL),
(3, 13, NULL),
(3, 14, NULL),
(3, 15, NULL),
(3, 16, NULL),
(3, 17, NULL),
(3, 18, NULL),
(3, 19, NULL),
(3, 20, NULL),
(3, 21, NULL),
(3, 22, NULL),
(3, 23, NULL),
(3, 24, NULL),
(3, 25, NULL),
(3, 26, NULL),
(3, 27, NULL),
(3, 28, NULL),
(3, 29, NULL),
(3, 30, NULL),
(3, 31, NULL),
(3, 32, NULL),
(3, 33, NULL),
(3, 34, NULL),
(3, 35, NULL),
(3, 36, NULL),
(3, 38, NULL),
(3, 39, NULL),
(3, 50, NULL),
(3, 51, NULL),
(4, 1, NULL),
(4, 2, NULL),
(4, 3, NULL),
(4, 4, NULL),
(4, 5, NULL),
(4, 6, NULL),
(4, 7, NULL),
(4, 8, NULL),
(4, 9, NULL),
(4, 10, NULL),
(4, 11, NULL),
(4, 12, NULL),
(4, 13, NULL),
(4, 14, NULL),
(4, 15, NULL),
(4, 16, NULL),
(4, 17, NULL),
(4, 18, NULL),
(4, 19, NULL),
(4, 20, NULL),
(4, 21, NULL),
(4, 22, NULL),
(4, 23, NULL),
(4, 24, NULL),
(4, 25, NULL),
(4, 26, NULL),
(4, 27, NULL),
(4, 28, NULL),
(4, 29, NULL),
(4, 30, NULL),
(4, 31, NULL),
(4, 32, NULL),
(4, 33, NULL),
(4, 34, NULL),
(4, 38, NULL),
(4, 39, NULL),
(6, 40, NULL),
(6, 41, NULL),
(6, 42, NULL),
(6, 43, NULL),
(6, 44, NULL),
(6, 45, NULL),
(6, 46, NULL),
(6, 47, NULL),
(6, 48, NULL),
(6, 49, NULL),
(7, 40, NULL),
(7, 41, NULL),
(7, 42, NULL),
(7, 43, NULL),
(7, 44, NULL),
(7, 45, NULL),
(7, 46, NULL),
(7, 47, NULL),
(7, 48, NULL),
(7, 49, NULL),
(8, 40, NULL),
(8, 41, NULL),
(8, 42, NULL),
(8, 43, NULL),
(8, 44, NULL),
(8, 45, NULL),
(8, 46, NULL),
(8, 47, NULL),
(8, 48, NULL),
(8, 49, NULL),
(9, 40, NULL),
(9, 41, NULL),
(9, 42, NULL),
(9, 43, NULL),
(9, 44, NULL),
(9, 45, NULL),
(9, 46, NULL),
(9, 47, NULL),
(9, 48, NULL),
(9, 49, NULL);

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

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id`, `cliente`, `empleado`, `fecha`, `metodo_pago`) VALUES
(1, 1, 4, '2017-05-20 00:00:00', 'Efectivo'),
(2, 1, 4, '2017-05-20 00:00:00', 'Efectivo'),
(3, 1, 2, '2017-05-21 00:00:00', 'Efectivo'),
(4, 2, 3, '2017-05-23 00:00:00', 'Tarjeta');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `ventas_articulos`
--
CREATE TABLE `ventas_articulos` (
`Articulo` varchar(50)
,`valor_talla` varchar(15)
,`descripcion` varchar(255)
,`precio` float(8,2)
,`Categoria` varchar(50)
,`Marca` varchar(50)
,`stock` int(5)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `ventas_lineasventa`
--
CREATE TABLE `ventas_lineasventa` (
`ID_Venta` int(10) unsigned
,`Num_Linea` int(10) unsigned
,`Cliente` varchar(50)
,`Empleado` varchar(50)
,`Fecha` datetime
,`Metodo_Pago` varchar(20)
,`Articulo` varchar(50)
,`Cantidad` int(5)
,`Importe` float(8,2)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `ventas_articulos`
--
DROP TABLE IF EXISTS `ventas_articulos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ventas_articulos`  AS  (select `a`.`nombre` AS `Articulo`,`t`.`valor_talla` AS `valor_talla`,`a`.`descripcion` AS `descripcion`,`a`.`precio` AS `precio`,`c`.`nombre` AS `Categoria`,`m`.`nombre` AS `Marca`,`tam`.`stock` AS `stock` from ((((`tallas_articulos_map` `tam` join `tallas` `t`) join `articulos` `a`) join `categorias` `c`) join `marcas` `m`) where ((`a`.`id` = `tam`.`id_articulo`) and (`t`.`id` = `tam`.`id_talla`) and (`a`.`categoria` = `c`.`id`) and (`a`.`marca` = `m`.`id`)) order by `tam`.`id_articulo`,`tam`.`id_talla`) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `ventas_lineasventa`
--
DROP TABLE IF EXISTS `ventas_lineasventa`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ventas_lineasventa`  AS  (select `v`.`id` AS `ID_Venta`,`lv`.`num_linea` AS `Num_Linea`,`c`.`nombre` AS `Cliente`,`e`.`nombre` AS `Empleado`,`v`.`fecha` AS `Fecha`,`v`.`metodo_pago` AS `Metodo_Pago`,`a`.`nombre` AS `Articulo`,`lv`.`cantidad` AS `Cantidad`,`lv`.`importe` AS `Importe` from ((((`ventas` `v` join `lineas_ventas` `lv`) join `clientes` `c`) join `articulos` `a`) join `empleados` `e`) where ((`c`.`id` = `v`.`cliente`) and (`e`.`id` = `v`.`empleado`) and (`a`.`id` = `lv`.`articulo`) and (`v`.`id` = `lv`.`venta_id`)) order by 1,2) ;

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
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `lineas_ventas`
--
ALTER TABLE `lineas_ventas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
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
