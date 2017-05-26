-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-05-2017 a las 21:42:06
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

CREATE DATABASE clothy CHARSET utf8 COLLATE utf8_general_ci;

use clothy;

--
-- Estructura de tabla para la tabla `articulos`
--

CREATE TABLE `articulos` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` float(8,2) NOT NULL,
  `categoria` int(10) UNSIGNED DEFAULT NULL,
  `marca` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`id`, `nombre`, `descripcion`, `precio`, `categoria`, `marca`) VALUES
(1, 'Camisa Negra', 'Rayas blancas', 19.90, 1, 2),
(2, 'Camisa Negra', '', 14.90, 1, 1),
(3, 'Camisa Blanca', 'Cuello alto', 12.95, 1, 4),
(4, 'Camisa Blanca', 'Rayas Negras', 18.90, 1, 5),
(5, 'Camisa Azul', 'Circulos', 17.90, 1, 2),
(6, 'Camisa Rosa', '', 14.90, 1, 1),
(7, 'Polo Negro', '', 29.90, 2, 1),
(8, 'Polo Blanco', '', 14.95, 2, 3),
(9, 'Polo Azul', 'Azul Marino', 18.90, 2, 4),
(10, 'Polo Verde', 'Verde Pistacho', 27.90, 2, 6),
(11, 'Polo Rosa', '', 17.90, 2, 5),
(12, 'Camiseta Street', '', 9.90, 3, 2),
(13, 'Camiseta Age', '', 14.95, 3, 4),
(14, 'Camiseta Casual', 'Azul Marino', 12.90, 3, 5),
(15, 'Camiseta Mountain', 'Verde Pistacho', 4.99, 3, 1),
(16, 'Camiseta Bop', '', 7.90, 3, 3),
(17, 'Chaqueta Piel', 'Cuero del bueno', 59.90, 4, 1),
(18, 'Chaqueta Vaquera', '', 74.95, 4, 3),
(19, 'Camiseta Casual', 'Azul', 32.90, 4, 5),
(20, 'Chaqueta Traje', 'Rosa', 44.99, 4, 1),
(21, 'Chaqueta Faldon', 'Gabardina', 47.90, 4, 2),
(22, 'Traje Boda', 'Negro', 79.90, 5, 8),
(23, 'Traje Fiesta', 'Granate', 94.95, 5, 3),
(24, 'Traje Casual', 'Azul Marino', 52.90, 5, 1),
(25, 'Jeans Vaqueros', 'Desgastados', 20.90, 6, 1),
(26, 'Jeans Urban', '', 24.95, 6, 2),
(27, 'Jeans Azul', 'Azul roto', 22.90, 6, 9),
(28, 'Jeans Traje', '', 34.99, 6, 10),
(29, 'Jeans Squish', 'Negro', 27.90, 6, 4),
(30, 'Bermudas Vaqueras', '', 20.90, 7, 9),
(31, 'Bermudas Urban', '', 24.95, 7, 10),
(32, 'Bermudas Classic', '', 22.90, 7, 1),
(33, 'Bermudas Traje', '', 24.99, 7, 2),
(34, 'Bermudas Bougn', 'Blanco', 27.90, 7, 3),
(35, 'Bañador Street', 'Lunares', 9.90, 8, 1),
(36, 'Bañador QS', 'Tattoo', 7.95, 8, 2),
(37, 'Bañador Classic', '', 8.90, 8, 3),
(38, 'Bañador Ko', 'Negro', 14.99, 8, 4),
(39, 'Bañador Vint', 'Blanco rayas', 17.90, 8, 5),
(40, 'Zapatos Mocasin', 'Negro', 40.90, 9, 1),
(41, 'Zapatos Urban', 'Blancas', 34.95, 9, 2),
(42, 'Zapatos Classic', '', 42.90, 9, 4),
(43, 'Zapatos Suit', '', 54.99, 9, 5),
(44, 'Zapatos Mocasin', 'Blanco', 67.90, 9, 3),
(45, 'Zapatillas Sport', 'Negro', 30.90, 10, 1),
(46, 'Zapatillas Urban', 'Blancas', 24.95, 10, 2),
(47, 'Zapatillas Classic', '', 32.90, 10, 4),
(48, 'Zapatillas OhG', '', 44.99, 10, 5),
(49, 'Zapatillas Verb', 'Blanco', 37.90, 10, 3),
(50, 'Chaqueta Soft', 'Chaleco', 29.50, 4, 7),
(51, 'Polo', 'Amarillo', 29.50, 2, 7),
(53, 'asd', 'asd', 33.00, 11, 2);

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
(10, 'Deportes', 'Zapatillas futbol, correr, etc...'),
(11, 'asd', 'uuuuuuuuu');

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
(4, 'Sonia', 'Peris', 'C/Carrer n2 pta5', 'sonia@gmail.com', '96 345 64 78', 'sonia', 'sonia'),
(5, 'Javier', 'Rogriguez', 'C/ Maestro n13', 'javi@gmail.com', '646 31 25 12', 'javier', 'javier');

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
(22, 4, 3, 45, 1, 30.90),
(23, 5, 1, 26, 2, 49.90),
(24, 5, 2, 37, 1, 17.80),
(25, 5, 3, 49, 1, 75.80),
(26, 6, 1, 1, 1, 19.90),
(27, 6, 2, 14, 1, 12.90),
(28, 6, 3, 14, 1, 32.90),
(29, 7, 1, 9, 2, 37.80),
(30, 7, 2, 24, 1, 105.80),
(31, 7, 3, 43, 1, 109.98),
(32, 8, 1, 1, 2, 39.80),
(33, 8, 2, 5, 2, 35.80),
(35, 10, 1, 1, 1, 19.90),
(36, 10, 2, 7, 2, 29.90),
(37, 10, 3, 26, 2, 24.95),
(38, 11, 1, 1, 1, 19.90),
(39, 11, 2, 43, 1, 54.99),
(40, 12, 1, 43, 1, 54.99),
(41, 12, 2, 5, 2, 17.90),
(42, 12, 3, 6, 1, 14.90);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `contacto` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`id`, `nombre`, `direccion`, `telefono`, `email`, `contacto`) VALUES
(1, 'Ralph Lauren', 'Pol. Fuente del Jarro', '96 542 15 48', 'ralph@lauren.com', 'Manolo Peris'),
(2, 'Valentino', 'Pol. Industrial s/n', '96 542 97 87', 'valen@tino.com', 'Sandra Hernandez'),
(3, 'Lacoste', 'C/ Naranjos n13', '675 48 59 65', 'la@coste.com', 'Javier Rodriguez'),
(4, 'Gucci', 'C/ Puerto n16', '91 546 21 00', 'gu@cci.com', 'Raul Perez Beni'),
(5, 'Prada', 'Av. Huerta n6', '96 541 00 23', 'pra@da.com', 'Rafa Martinez'),
(6, 'Tommy Hilfiger', 'C/Cuenca n45', '675 21 54 98', 'tommy@hilfiger.com', 'Tomás Hilfi'),
(7, 'Emporio Armani', 'C/ Burriana n21', '96 321 54 87', 'emporio@armani.com', 'Don Empo Arma'),
(8, 'Hugo Boss', 'Av. Oeste s/n', '666 54 12 00', 'hugo@boss.com', 'Hiugo el BOSS'),
(9, 'Pepe Jeans', 'Pl. Virgen n3', '96 452 12 10', 'pepe@jeans.com', 'Pepe Vaqueros'),
(10, 'Levis', 'C/San Vicente 200', '654 45 12 00', 'le@vis.com', 'Don Le Vi');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(10) UNSIGNED NOT NULL,
  `fecha` datetime NOT NULL,
  `articulo` int(10) NOT NULL,
  `talla` int(10) NOT NULL,
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
(1, 1, 0),
(1, 2, 13),
(1, 3, 0),
(1, 4, 7),
(1, 5, 0),
(1, 6, 0),
(1, 8, 0),
(1, 9, 4),
(1, 10, 6),
(1, 11, 4),
(1, 12, 1),
(1, 13, 7),
(1, 14, 5),
(1, 15, 4),
(1, 16, 5),
(1, 17, 13),
(1, 18, 6),
(1, 19, 7),
(1, 20, 4),
(1, 21, 12),
(1, 22, 3),
(1, 23, 8),
(1, 24, 5),
(1, 25, 0),
(1, 26, 1),
(1, 27, 4),
(1, 28, 4),
(1, 29, 10),
(1, 30, 6),
(1, 31, 0),
(1, 32, 13),
(1, 33, 8),
(1, 34, 13),
(1, 35, 14),
(1, 36, 2),
(1, 37, 12),
(1, 38, 9),
(1, 50, 9),
(1, 51, 4),
(2, 1, 8),
(2, 2, 1),
(2, 3, 10),
(2, 4, 2),
(2, 5, 11),
(2, 6, 3),
(2, 7, 11),
(2, 8, 5),
(2, 9, 7),
(2, 10, 5),
(2, 11, 3),
(2, 12, 1),
(2, 13, 10),
(2, 14, 5),
(2, 15, 8),
(2, 16, 11),
(2, 17, 0),
(2, 18, 1),
(2, 19, 3),
(2, 20, 11),
(2, 21, 4),
(2, 22, 1),
(2, 23, 7),
(2, 24, 5),
(2, 25, 4),
(2, 26, 6),
(2, 27, 4),
(2, 28, 1),
(2, 29, 11),
(2, 30, 5),
(2, 31, 10),
(2, 32, 5),
(2, 33, 10),
(2, 34, 7),
(2, 35, 4),
(2, 37, 0),
(2, 39, 5),
(2, 50, 7),
(2, 51, 9),
(3, 1, 7),
(3, 2, 11),
(3, 3, 2),
(3, 4, 7),
(3, 5, 1),
(3, 6, 0),
(3, 7, 11),
(3, 8, 12),
(3, 9, 13),
(3, 10, 0),
(3, 11, 7),
(3, 12, 4),
(3, 13, 14),
(3, 14, 12),
(3, 15, 5),
(3, 16, 6),
(3, 17, 14),
(3, 18, 6),
(3, 19, 3),
(3, 20, 1),
(3, 21, 9),
(3, 22, 12),
(3, 23, 6),
(3, 24, 7),
(3, 25, 4),
(3, 26, 14),
(3, 27, 13),
(3, 28, 8),
(3, 29, 1),
(3, 30, 11),
(3, 31, 8),
(3, 32, 10),
(3, 33, 9),
(3, 34, 3),
(3, 35, 2),
(3, 36, 2),
(3, 38, 4),
(3, 39, 1),
(3, 50, 7),
(3, 51, 4),
(4, 1, 0),
(4, 2, 3),
(4, 3, 2),
(4, 4, 2),
(4, 5, 1),
(4, 6, 3),
(4, 7, 10),
(4, 8, 12),
(4, 9, 3),
(4, 10, 7),
(4, 11, 12),
(4, 12, 11),
(4, 13, 3),
(4, 14, 14),
(4, 15, 14),
(4, 16, 14),
(4, 17, 0),
(4, 18, 4),
(4, 19, 3),
(4, 20, 4),
(4, 21, 12),
(4, 22, 5),
(4, 23, 4),
(4, 24, 4),
(4, 25, 9),
(4, 26, 4),
(4, 27, 7),
(4, 28, 9),
(4, 29, 8),
(4, 30, 14),
(4, 31, 2),
(4, 32, 13),
(4, 33, 0),
(4, 34, 8),
(4, 38, 9),
(4, 39, 8),
(6, 40, 12),
(6, 41, 8),
(6, 42, 4),
(6, 43, 12),
(6, 44, 4),
(6, 45, 12),
(6, 46, 6),
(6, 47, 11),
(6, 48, 4),
(6, 49, 6),
(7, 40, 1),
(7, 41, 3),
(7, 42, 13),
(7, 43, 13),
(7, 44, 8),
(7, 45, 4),
(7, 46, 10),
(7, 47, 10),
(7, 48, 4),
(7, 49, 8),
(8, 40, 12),
(8, 41, 6),
(8, 42, 8),
(8, 43, 8),
(8, 44, 2),
(8, 45, 4),
(8, 46, 11),
(8, 47, 14),
(8, 48, 6),
(8, 49, 5),
(9, 40, 8),
(9, 41, 9),
(9, 42, 8),
(9, 43, 12),
(9, 44, 7),
(9, 45, 0),
(9, 46, 8),
(9, 47, 13),
(9, 48, 10),
(9, 49, 13);

--
-- Disparadores `tallas_articulos_map`
--
DELIMITER $$
CREATE TRIGGER `realiza_pedido_BU` AFTER UPDATE ON `tallas_articulos_map` FOR EACH ROW BEGIN
	IF (NEW.stock= 0) THEN
    INSERT INTO pedidos (fecha, articulo, talla) VALUES(CURRENT_TIMESTAMP(), OLD.id_articulo, OLD.id_talla);
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id` int(10) UNSIGNED NOT NULL,
  `cliente` int(10) UNSIGNED NOT NULL,
  `empleado` int(10) UNSIGNED NOT NULL,
  `fecha` date DEFAULT NULL,
  `metodo_pago` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id`, `cliente`, `empleado`, `fecha`, `metodo_pago`) VALUES
(1, 1, 4, '2017-05-20', 'Efectivo'),
(2, 1, 4, '2017-05-20', 'Efectivo'),
(3, 1, 2, '2017-05-21', 'Efectivo'),
(4, 2, 3, '2017-05-23', 'Tarjeta'),
(5, 2, 3, '2017-05-23', 'Tarjeta'),
(6, 3, 3, '2017-05-23', 'Tarjeta'),
(7, 1, 1, '2017-05-02', 'Efectivo'),
(8, 1, 1, '2017-05-23', 'Efectivo'),
(9, 1, 1, '2017-05-20', 'Efectivo'),
(10, 3, 1, '2017-01-02', 'Tarjeta'),
(11, 1, 1, '2017-02-02', 'Efectivo'),
(12, 3, 2, '2017-05-06', 'Tarjeta');

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
,`Fecha` date
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
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;
--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `lineas_ventas`
--
ALTER TABLE `lineas_ventas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
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
