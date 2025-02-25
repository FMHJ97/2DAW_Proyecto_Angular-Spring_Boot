-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-02-2025 a las 13:19:31
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
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `comentario` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_relato` int(11) NOT NULL,
  `contenido` text NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
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
(11, 'Paranormal'),
(12, 'Policial'),
(13, 'Psicológico'),
(14, 'Romántico'),
(15, 'Suspense'),
(16, 'Terror'),
(17, 'Thriller');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relato`
--

CREATE TABLE `relato` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `resumen` text NOT NULL,
  `contenido` text NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `portada_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `relato`
--

INSERT INTO `relato` (`id`, `id_usuario`, `titulo`, `resumen`, `contenido`, `fecha_publicacion`, `portada_url`) VALUES
(3, 2, 'La Última Llama', 'En un futuro distópico, un joven llamado Aldo se enfrenta al fin del mundo. Con el último refugio de la humanidad en ruinas, se embarca en un viaje hacia el desierto para encontrar una fuente de energía que podría salvar a los últimos sobrevivientes.', '<p>Aldo había crecido escuchando historias sobre un misterioso artefacto conocido como \"la Llama\", una fuente de energía que, según las leyendas, podía revivir el ecosistema y devolver la vida a la Tierra. Pero no todos creían en ella; muchos decían que solo era un mito. Sin embargo, cuando las noticias de la caída del último refugio humano comenzaron a extenderse, Aldo sintió que no había más opciones.</p><p>El mundo estaba moribundo. El cielo ya no mostraba su color azul; solo una neblina gris flotaba por encima de los escombros. La Tierra había perdido su capacidad para generar recursos. Las grandes fábricas que antes producían todo lo necesario para la humanidad ahora estaban sumidas en el abandono, y las ciudades eran sólo sombras de lo que fueron.</p><p>Aldo se unió a un pequeño grupo de sobrevivientes que se dirigían hacia el desierto, donde se creía que la Llama aún podía encontrarse. En el camino, las tormentas de arena eran su único enemigo constante, pero algo más estaba acechando: la desesperación. Los miembros del grupo, poco a poco, iban desapareciendo, ya sea por el desierto o por el deterioro mental que los efectos de la desesperanza provocaban.</p><p>Después de días de lucha, Aldo llegó finalmente a las ruinas de un antiguo templo. Allí, en el corazón del desierto, encontró un relicario que brillaba débilmente. Era la Llama. Al activarla, el artefacto no solo restauró la energía, sino que comenzó a sanar el mundo. Los vientos cesaron, las plantas comenzaron a brotar y el aire se volvió respirable nuevamente. Aldo había encontrado la chispa para un nuevo comienzo. Pero sabía que su trabajo no terminaba ahí, pues el mundo necesitaba más que solo un fuego encendido.</p>', '2025-02-25', NULL),
(4, 2, 'El Bosque de los Susurros', 'Una joven exploradora llamada Luna se adentra en un bosque misterioso conocido por sus extraños susurros. Pronto descubre que el bosque está lleno de secretos y que las voces provienen de criaturas antiguas que desean liberar su poder.', '<p>Luna había escuchado las historias del Bosque de los Susurros desde pequeña. Los aldeanos evitaban el lugar, temerosos de las voces que flotaban en el aire, como si los árboles pudieran hablar. Sin embargo, su curiosidad era más fuerte que cualquier advertencia. Equipó su mochila con lo esencial y partió al amanecer.</p><p>El bosque era más oscuro de lo que había imaginado. Las sombras parecían moverse con vida propia, y los susurros comenzaron en cuanto cruzó el umbral. Al principio, pensó que eran simples sonidos del viento, pero pronto se dio cuenta de que las voces eran claras y coherentes. Le hablaban, aunque no podía entender todo lo que decían. La sensación de estar siendo observada la acompañaba constantemente.</p><p>Mientras caminaba, notó que los árboles parecían cambiar a medida que avanzaba, sus raíces se entrelazaban formando caminos que solo ella podía ver. Después de horas de exploración, llegó a una gran cueva, en cuyo interior se encontraba un círculo de piedras antiguas. En el centro, una figura apareció: una criatura con ojos de fuego, que se presentó como El Guardián del Bosque.</p><p>La criatura le explicó que el bosque estaba lleno de espíritus de antiguos seres que deseaban liberarse de su prisión eterna. Para hacerlo, necesitaban que Luna completara una serie de pruebas. Cada desafío la enfrentó a sus propios miedos, y al final, entendió que el bosque no solo era un lugar de magia antigua, sino un reflejo de las emociones humanas: la culpa, el amor perdido, los secretos enterrados. Con su último esfuerzo, liberó a los espíritus, pero a cambio, parte de su alma quedó atrapada en el bosque para siempre. Ahora, Luna también se convirtió en parte de ese lugar, una susurradora más, guiando a aquellos que, como ella, buscan respuestas.</p>', '2025-02-25', NULL),
(5, 1, 'Ecos de la Mente', 'En un futuro donde la tecnología permite leer pensamientos, un psicólogo se enfrenta a la angustiante realidad de descubrir oscuros secretos en la mente de sus pacientes.', '<p>Dr. Mateo Castellanos era conocido por su habilidad para ayudar a sus pacientes a través de un revolucionario implante cerebral que le permitía leer sus pensamientos más oscuros. Este avance lo había hecho famoso, pero también lo había aislado. Sus colegas temían que el acceso a la mente humana pudiera corromper al psicólogo, pero él se mantenía firme, confiando en su ética profesional.</p><p>Un día, comenzó a recibir pensamientos inquietantes de sus pacientes. Algunos eran pequeños ecos de dudas y miedos, pero otros… otros eran aterradores. Un paciente comenzó a pensar en voz alta: \"Sé lo que estás haciendo, doctor. Puedo escuchar todo lo que piensas. No puedes esconderlo\". Esto desconcertó al Dr. Castellanos, ya que era incapaz de escuchar sus propios pensamientos.</p><p>Pronto, la situación se volvió aún más extraña. Los pensamientos de los pacientes comenzaban a mezclarse con los suyos, y las voces que antes había considerado meros ecos de la mente se volvían más nítidas y directas. En medio de esta confusión, comenzó a dudar si lo que estaba experimentando era real o el resultado de una distorsión cerebral. ¿Era alguien controlando sus pensamientos?</p><p>Después de semanas de paranoia, Mateo descubrió que había sido víctima de un experimento secreto de manipulación mental llevado a cabo por una poderosa corporación. Ellos, al igual que él, habían implantado dispositivos en sus cerebros para controlar las emociones humanas. Al darse cuenta de la magnitud de lo que estaba ocurriendo, Mateo intentó escapar, pero ya era tarde. Sus propios pensamientos habían sido secuestrados, y el control ya no estaba en sus manos. La mente humana nunca volvería a ser la misma.</p>', '2025-02-25', NULL),
(6, 3, 'El Último Hombre en la Tierra', 'Después de una pandemia mundial que ha exterminado a la humanidad, un hombre llamado Daniel sobrevive en una ciudad desierta. Mientras busca respuestas, descubre que no está solo, pero las criaturas que lo acompañan no son lo que parecen.', '<p>Daniel despertó solo, como cada mañana, en una ciudad desierta. Las calles de lo que alguna vez fue una próspera metrópoli ahora estaban vacías, sumidas en el polvo y la quietud. Los autos abandonados y los edificios destruidos eran recordatorios de lo que una vez fue un mundo lleno de vida. La pandemia que había arrasado con la humanidad dejaba a Daniel como el único sobreviviente conocido.</p><p>Su rutina consistía en buscar provisiones, explorar y mantenerse alerta. Había señales de que la pandemia no solo había acabado con los humanos, sino que había alterado a los seres vivos. Animales mutados vagaban por la ciudad, y algunas criaturas, extrañas e indescriptibles, comenzaban a aparecer. A veces, las sombras en los rincones de las calles le daban la sensación de que algo lo observaba.</p><p>Un día, mientras investigaba lo que parecía una antigua estación de radio, escuchó un sonido: una voz. Una transmisión en la que se anunciaba que el mundo no había terminado, que había otros sobrevivientes, pero que la naturaleza había evolucionado de formas que nadie podía comprender. Las criaturas, que él pensaba que eran monstruos, estaban desarrollando una conciencia propia.</p><p>La revelación fue escalofriante: las criaturas no eran sus enemigos, sino sus nuevos compañeros. Pero la pregunta que lo atormentaba era: ¿quién o qué había realmente quedado en la Tierra?</p>', '2025-02-25', NULL),
(7, 3, 'La Magia de las Estrellas', 'En un pequeño pueblo costero, una niña llamada Alba encuentra un antiguo libro que le permite invocar estrellas fugaces. Pero cada vez que usa la magia, algo en el mundo cambia y las consecuencias se vuelven cada vez más oscuras.', '<p>Alba siempre había soñado con las estrellas. En las noches despejadas, se sentaba en el muelle a observarlas, perdiéndose en su brillo. Un día, encontró un libro antiguo y polvoriento en la biblioteca del pueblo. En él, descubrió cómo invocar estrellas fugaces. Al principio, pensó que era solo un juego, algo para entretener su imaginación. Pero cuando leyó las palabras en voz alta, una estrella fugaz cruzó el cielo, y algo extraño ocurrió: el mar dejó de moverse, como si el tiempo se hubiera detenido por un instante.</p><p>Intrigada, Alba continuó con la magia. Cada vez que deseaba algo, lo veía cumplido: la salud de su madre mejoraba, los problemas del pueblo desaparecían. Pero pronto, Alba notó que cada deseo tenía una consecuencia. Cuando pidió que el sol nunca se pusiera, el pueblo comenzó a perder su color y su vida. La gente ya no reía ni cantaba. La oscuridad de la eternidad comenzó a apoderarse de todo.</p><p>La niña comprendió que cada estrella traía consigo un precio. Por cada deseo cumplido, algo debía perderse. La última estrella que invocó hizo que el mar devorara el pueblo entero, dejando solo ruinas. Alba, ahora sola, entendió la lección de las estrellas: la magia era demasiado poderosa para ser controlada por manos humanas. Y aunque ella había deseado devolver todo a la normalidad, las estrellas ya no volverían a caer.</p>', '2025-02-25', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relato_genero`
--

CREATE TABLE `relato_genero` (
  `id` int(11) NOT NULL,
  `id_relato` int(11) NOT NULL,
  `id_genero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `relato_genero`
--

INSERT INTO `relato_genero` (`id`, `id_relato`, `id_genero`) VALUES
(6, 3, 2),
(5, 3, 3),
(8, 4, 2),
(9, 4, 6),
(7, 4, 9),
(10, 5, 3),
(12, 5, 13),
(11, 5, 17),
(14, 6, 15),
(13, 6, 16),
(17, 7, 2),
(16, 7, 5),
(15, 7, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relato_usuario`
--

CREATE TABLE `relato_usuario` (
  `id` int(11) NOT NULL,
  `id_relato` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `me_gusta` tinyint(1) NOT NULL DEFAULT 0,
  `favorito` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `sexo` enum('Hombre','Mujer','Sin especificar') NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `pais` enum('España','Inglaterra','Francia','Italia','Alemania') NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(320) NOT NULL,
  `rol` enum('admin','usuario') NOT NULL DEFAULT 'usuario'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `sexo`, `fecha_nacimiento`, `pais`, `usuario`, `password`, `email`, `rol`) VALUES
(1, 'Francisco Manuel', 'Hernández', 'Hombre', '1997-01-05', 'España', 'fmhj97', '$2a$10$TuzO8moB2hgOJikoplGJWeWq3t/WgvIxIv26I6EYZsURl3kIkJluC', 'francisco@gmail.com', 'usuario'),
(2, 'David', 'Campos', 'Hombre', '1990-10-10', 'España', 'davidCC', '$2a$10$pWatgcy0K/zsSs1Psa02ieMSHPoux5VCj99do4f.Q0UE06EWz8lnu', 'davidcc@gmail.com', 'usuario'),
(3, 'Sara', 'Hernández', 'Mujer', '1990-10-10', 'Italia', 'saraher', '$2a$10$3hppFOFhe2Ry.eH9hkQSxOnzhmxi8NDT79YzEL5sHg76JysR/v59q', 'sara@gmail.com', 'usuario'),
(4, 'Steve', 'Kerr', 'Hombre', '1990-10-10', 'Alemania', 'stevekerr', '$2a$10$6eBGe3pHIreSY0.whZjNb.sJmz/F99.LnnYgYPQCY4xga00XIg3DK', 'steve@gmail.com', 'admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk1` (`id_usuario`),
  ADD KEY `fk2` (`id_relato`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `relato`
--
ALTER TABLE `relato`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk3` (`id_usuario`);

--
-- Indices de la tabla `relato_genero`
--
ALTER TABLE `relato_genero`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_relato` (`id_relato`,`id_genero`),
  ADD KEY `fk5` (`id_genero`);

--
-- Indices de la tabla `relato_usuario`
--
ALTER TABLE `relato_usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_relato` (`id_relato`,`id_usuario`),
  ADD KEY `fk7` (`id_usuario`);

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
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `relato`
--
ALTER TABLE `relato`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `relato_genero`
--
ALTER TABLE `relato_genero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `relato_usuario`
--
ALTER TABLE `relato_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2` FOREIGN KEY (`id_relato`) REFERENCES `relato` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `relato`
--
ALTER TABLE `relato`
  ADD CONSTRAINT `fk3` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `relato_genero`
--
ALTER TABLE `relato_genero`
  ADD CONSTRAINT `fk4` FOREIGN KEY (`id_relato`) REFERENCES `relato` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk5` FOREIGN KEY (`id_genero`) REFERENCES `genero` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `relato_usuario`
--
ALTER TABLE `relato_usuario`
  ADD CONSTRAINT `fk6` FOREIGN KEY (`id_relato`) REFERENCES `relato` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk7` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
