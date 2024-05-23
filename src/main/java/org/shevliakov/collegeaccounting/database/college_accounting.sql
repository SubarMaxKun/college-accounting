-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Час створення: Трв 23 2024 р., 18:04
-- Версія сервера: 10.4.32-MariaDB
-- Версія PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База даних: `college_accounting`
--
CREATE DATABASE IF NOT EXISTS `college_accounting` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `college_accounting`;

-- --------------------------------------------------------

--
-- Структура таблиці `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `rank` int(11) NOT NULL,
  `full_name` varchar(80) NOT NULL,
  `birth_date` date NOT NULL,
  `registration_number` varchar(80) NOT NULL,
  `military_specialty` int(8) NOT NULL,
  `training` int(11) NOT NULL,
  `accounting_category` varchar(30) NOT NULL,
  `degree` varchar(100) NOT NULL,
  `id_info` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `employees`
--

INSERT INTO `employees` (`id`, `rank`, `full_name`, `birth_date`, `registration_number`, `military_specialty`, `training`, `accounting_category`, `degree`, `id_info`) VALUES
(1, 1, 'Ворченко Микола Петрович', '1981-05-07', 'РНОКПП2943607634', 900, 1, 'І, ТП№211130', 'вища, банківські справа, КВ№22937940', 'МЕ№783517, виданий Шевченківським РУ ГУ МВС України в м.Києві, 29.09.2006'),
(2, 2, 'Семенченко Недан Зорянович', '1989-09-21', 'РНОКПП1943607689', 921, 1, 'І, ТП№218730', 'вища, банківські справа, КВ№22937970', 'МЕ№754317, виданий Шевченківським РУ ГУ МВС України в м.Києві, 12.04.2010'),
(3, 3, 'Бедзик Фауст Костянтинович', '1976-03-17', '567891278098665543227', 790, 1, 'ІІ, ТП№234730', 'вища, банківські справа, КВ№22937970', 'МЕ№729317, виданий Шевченківським РУ ГУ МВС України в м.Києві, 12.04.2010'),
(4, 4, 'Чортківський Йозеф Глібович', '1996-03-07', 'РНОКПП1760607689', 921, 2, 'І, ТП№235200', 'вища, банківські справа, КВ№22937970', 'МЕ№879317, виданий Шевченківським РУ ГУ МВС України в м.Києві, 12.04.2010'),
(5, 5, 'Іванов Іван Іванович', '1990-09-19', '876543218765432187613', 505, 2, 'І, ТП№218730', 'вища, банківські справа, КВ№22937970', 'МЕ№865317, виданий Шевченківським РУ ГУ МВС України в м.Києві, 12.04.2010'),
(6, 6, 'Петров Петро Петрович', '1985-03-14', '567812345678123456781', 303, 2, 'ІІ, ТП№234730', 'вища, банківські справа, КВ№22937970', 'МЕ№879567, виданий Шевченківським РУ ГУ МВС України в м.Києві, 12.04.2010');

-- --------------------------------------------------------

--
-- Структура таблиці `groups`
--

DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `groups`
--

INSERT INTO `groups` (`id`, `code`) VALUES
(1, 'КН-42'),
(2, 'БС-12'),
(3, 'ЗВ-31');

-- --------------------------------------------------------

--
-- Структура таблиці `lecturers`
--

DROP TABLE IF EXISTS `lecturers`;
CREATE TABLE `lecturers` (
  `id` int(11) NOT NULL,
  `full_name` varchar(80) NOT NULL,
  `position` varchar(80) NOT NULL,
  `last_certification` int(4) NOT NULL,
  `next_certification` int(4) NOT NULL,
  `hours` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `lecturers`
--

INSERT INTO `lecturers` (`id`, `full_name`, `position`, `last_certification`, `next_certification`, `hours`) VALUES
(1, 'Олександр Григорович Литвиненко', 'Викладач англійської мови', 2020, 2025, '2015-48\r\n2020-12'),
(2, 'Ірина Василівна Ковальчук', 'Викладач англійської мови як другої мови (ESL)', 2018, 2023, '2018-32\r\n2023-16'),
(3, 'Максим Олегович Петренко', 'Викладач аеронавтики та астронавтики', 2020, 2025, '2015-64\r\n2018-32\r\n2020-8\r\n2023-48'),
(4, 'Анна Юріївна Шевченко', 'Диригент джазового вокального ансамблю', 2024, 2029, '2015-64\r\n2018-32\r\n2020-24\r\n2023-12\r\n2024-18'),
(5, 'Віталій Сергійович Мельник', 'Директор програми магістра фізичної асистентури', 2020, 2025, '2015-36\r\n2020-24\r\n2022-18'),
(6, 'Ольга Павлівна Лисенко', 'Викладач математики', 2020, 2025, '2015-36\r\n2020-24\r\n2022-48'),
(7, 'Денис Вікторович Гончаренко', 'Асистент викладача', 2023, 2028, '2020-24\r\n2022-48\r\n2023-24'),
(8, 'Тетяна Іванівна Кравченко', 'Спеціаліст з навчальних програм', 2020, 2025, '2020-24\r\n2022-48\r\n2023-24'),
(18, 'Микола Мирослав Іванович', 'Викладач комп\'ютерних дисциплін', 2020, 2025, '2020-32\n2022-42');

-- --------------------------------------------------------

--
-- Структура таблиці `ranks`
--

DROP TABLE IF EXISTS `ranks`;
CREATE TABLE `ranks` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `ranks`
--

INSERT INTO `ranks` (`id`, `name`) VALUES
(1, 'Рекрут'),
(2, 'Солдат'),
(3, 'Старший солдат'),
(4, 'Молодший сержант'),
(5, 'Сержант'),
(6, 'Старший сержант'),
(7, 'Головний сержант'),
(8, 'Штаб-сержант'),
(9, 'Старший майстер-сержант'),
(10, 'Головний майстер-сержант'),
(11, 'Молодший лейтенант'),
(12, 'Лейтенант'),
(13, 'Старший лейтенант'),
(14, 'Капітан'),
(15, 'Майор'),
(16, 'Підполковник'),
(17, 'Полковник'),
(18, 'Бригадний генерал'),
(19, 'Генерал-майор'),
(20, 'Генерал-лейтенант'),
(21, 'Генерал');

-- --------------------------------------------------------

--
-- Структура таблиці `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` bigint(20) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `birth_date` varchar(255) DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `group_code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `students`
--

INSERT INTO `students` (`id`, `full_name`, `birth_date`, `address`, `group_code`) VALUES
(2, 'Штимак Максим Мирославович', '2005-12-08', 'м. Ужгород', 2),
(3, 'Котлар Данієлла Сергіївна', '2004-09-12', 'м. Ужгород', 1),
(4, 'Макаревич Сергій Сергійович', '2004-12-09', 'м. Чоп', 2),
(5, 'Шевляков Максим Валентинович', '2005-04-22', 'м. Чоп', 1);

-- --------------------------------------------------------

--
-- Структура таблиці `training`
--

DROP TABLE IF EXISTS `training`;
CREATE TABLE `training` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `training`
--

INSERT INTO `training` (`id`, `name`) VALUES
(1, 'Рядовий'),
(2, 'Сержантський'),
(3, 'Офіцерський');

-- --------------------------------------------------------

--
-- Структура таблиці `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  `is_admin` tinyint(1) NOT NULL,
  `rw_permission` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `is_admin`, `rw_permission`) VALUES
(1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1, 0),
(2, 'maksym_shev', 'd3fa5b799e2465cb0fae14bd4c9f0c21401ba9e548ab5d2d29ed811fbf99f93c', 0, 0);

--
-- Індекси збережених таблиць
--

--
-- Індекси таблиці `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rank` (`rank`),
  ADD KEY `training` (`training`);

--
-- Індекси таблиці `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `lecturers`
--
ALTER TABLE `lecturers`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `ranks`
--
ALTER TABLE `ranks`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD KEY `group_code` (`group_code`);

--
-- Індекси таблиці `training`
--
ALTER TABLE `training`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для збережених таблиць
--

--
-- AUTO_INCREMENT для таблиці `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблиці `groups`
--
ALTER TABLE `groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблиці `lecturers`
--
ALTER TABLE `lecturers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT для таблиці `ranks`
--
ALTER TABLE `ranks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT для таблиці `students`
--
ALTER TABLE `students`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблиці `training`
--
ALTER TABLE `training`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблиці `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Обмеження зовнішнього ключа збережених таблиць
--

--
-- Обмеження зовнішнього ключа таблиці `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`rank`) REFERENCES `ranks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`training`) REFERENCES `training` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`group_code`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
