-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 24, 2024 at 06:55 PM
-- Server version: 10.11.8-MariaDB-0ubuntu0.24.04.1
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `college_accounting`
--
CREATE DATABASE IF NOT EXISTS `college_accounting` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `college_accounting`;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
                             `id` int(11) NOT NULL,
                             `rank` int(11) NOT NULL,
                             `full_name` varchar(80) NOT NULL,
                             `birth_date` date NOT NULL,
                             `address_living` varchar(100) NOT NULL,
                             `address_registered` varchar(100) NOT NULL,
                             `tck_name` varchar(50) NOT NULL,
                             `family` varchar(150) NOT NULL,
                             `job_info` varchar(150) NOT NULL,
                             `registration_number` varchar(80) NOT NULL,
                             `military_specialty` int(8) NOT NULL,
                             `training` int(11) NOT NULL,
                             `accounting_category` varchar(30) NOT NULL,
                             `degree` varchar(100) NOT NULL,
                             `id_info` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `rank`, `full_name`, `birth_date`, `address_living`, `address_registered`, `tck_name`, `family`, `job_info`, `registration_number`, `military_specialty`, `training`, `accounting_category`, `degree`, `id_info`) VALUES
    (1, 1, 'Ворченко Микола Петрович', '1981-05-07', 'м. Ужгород, вул. Петефі 12, 65', 'м. Ужгород, вул. Мукачівсьа 2, 12', 'Ужгородський РТЦК та СП', 'Дружина: Ворченко Катерина Петрівна 1998.02.12', 'Бухгалтер, 23451', 'РНОКПП2943607634', 900, 1, 'І, ТП№211130', 'вища, банківські справа, КВ№22937940', 'МЕ№783517, виданий Шевченківським РУ ГУ МВС України в м.Києві, 29.09.2006');

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
                          `id` int(11) NOT NULL,
                          `code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`id`, `code`) VALUES
                                        (1, 'КН-42'),
                                        (2, 'БС-12'),
                                        (3, 'ЗВ-31'),
                                        (4, 'КН-12');

-- --------------------------------------------------------

--
-- Table structure for table `lecturers`
--

DROP TABLE IF EXISTS `lecturers`;
CREATE TABLE `lecturers` (
                             `id` int(11) NOT NULL,
                             `full_name` varchar(80) NOT NULL,
                             `position` varchar(80) NOT NULL,
                             `employment_date` int(4) NOT NULL,
                             `experience` varchar(100) NOT NULL,
                             `category` int(11) NOT NULL,
                             `title` int(11) NOT NULL,
                             `last_certification` int(4) NOT NULL,
                             `next_certification` int(4) NOT NULL,
                             `previous_certification_result` varchar(100) NOT NULL,
                             `hours` varchar(200) NOT NULL,
                             `certificate` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lecturers`
--

INSERT INTO `lecturers` (`id`, `full_name`, `position`, `employment_date`, `experience`, `category`, `title`, `last_certification`, `next_certification`, `previous_certification_result`, `hours`, `certificate`) VALUES
                                                                                                                                                                                                                       (1, 'Максим Олегович Петренко', 'Викладач аеронавтики та астронавтики', 2020, '4 роки', 3, 7, 2020, 2025, '23', '2015-64\n2018-32\n2020-8\n2023-48', ''),
                                                                                                                                                                                                                       (2, 'Віталій Сергійович Мельник', 'Директор програми магістра фізичної асистентури', 2015, '9 років', 3, 8, 2020, 2025, '60 балів', '2015-36\r\n2020-24\r\n2022-18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pedagogical_titles`
--

DROP TABLE IF EXISTS `pedagogical_titles`;
CREATE TABLE `pedagogical_titles` (
                                      `id` int(11) NOT NULL,
                                      `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pedagogical_titles`
--

INSERT INTO `pedagogical_titles` (`id`, `name`) VALUES
                                                    (1, 'Викладач-методист'),
                                                    (2, 'Учитель-методист'),
                                                    (3, 'Вихователь-методист'),
                                                    (4, 'Педагог-організатор-методист'),
                                                    (5, 'Практичний психолог — методист'),
                                                    (6, 'Керівник гуртка — методист'),
                                                    (7, 'Старший вожатий — методист'),
                                                    (8, 'Старший викладач'),
                                                    (9, 'Старший учитель'),
                                                    (10, 'Старший вихователь'),
                                                    (11, 'Майстер виробничого навчання I категорії'),
                                                    (12, 'Майстер виробничого навчання II категорії');

-- --------------------------------------------------------

--
-- Table structure for table `qualification_categories`
--

DROP TABLE IF EXISTS `qualification_categories`;
CREATE TABLE `qualification_categories` (
                                            `id` int(11) NOT NULL,
                                            `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `qualification_categories`
--

INSERT INTO `qualification_categories` (`id`, `name`) VALUES
                                                          (1, 'Спеціаліст'),
                                                          (2, 'Спеціаліст другої категорії'),
                                                          (3, 'Спеціаліст першої категорії'),
                                                          (4, 'Спеціаліст вищої категорії');

-- --------------------------------------------------------

--
-- Table structure for table `ranks`
--

DROP TABLE IF EXISTS `ranks`;
CREATE TABLE `ranks` (
                         `id` int(11) NOT NULL,
                         `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ranks`
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
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
                            `id` bigint(20) NOT NULL,
                            `full_name` varchar(100) NOT NULL,
                            `birth_date` varchar(255) DEFAULT NULL,
                            `address` varchar(100) NOT NULL,
                            `group_code` int(11) NOT NULL,
                            `on_tck` tinyint(1) NOT NULL,
                            `military_document` varchar(100) NOT NULL,
                            `specialty_rank` varchar(100) DEFAULT NULL,
                            `tck_name` varchar(150) NOT NULL,
                            `tax_card` varchar(50) NOT NULL,
                            `notes` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `full_name`, `birth_date`, `address`, `group_code`, `on_tck`, `military_document`, `specialty_rank`, `tck_name`, `tax_card`, `notes`) VALUES
    (1, 'Шевляков Максим Валентинович', '2005-04-22', 'м. Чоп', 1, 1, 'III, 123, 2020.04.15', '', 'Ужгородський РТЦК', '726380987', 'Молодець\n');

-- --------------------------------------------------------

--
-- Table structure for table `training`
--

DROP TABLE IF EXISTS `training`;
CREATE TABLE `training` (
                            `id` int(11) NOT NULL,
                            `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `training`
--

INSERT INTO `training` (`id`, `name`) VALUES
                                          (1, 'Рядовий'),
                                          (2, 'Сержантський'),
                                          (3, 'Офіцерський');

-- --------------------------------------------------------

--
-- Table structure for table `users`
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
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `is_admin`, `rw_permission`) VALUES
                                                                                    (1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1, 0),
                                                                                    (2, 'maksym_shev', 'd3fa5b799e2465cb0fae14bd4c9f0c21401ba9e548ab5d2d29ed811fbf99f93c', 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
    ADD PRIMARY KEY (`id`),
  ADD KEY `rank` (`rank`),
  ADD KEY `training` (`training`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lecturers`
--
ALTER TABLE `lecturers`
    ADD PRIMARY KEY (`id`),
  ADD KEY `category` (`category`),
  ADD KEY `title` (`title`);

--
-- Indexes for table `pedagogical_titles`
--
ALTER TABLE `pedagogical_titles`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `qualification_categories`
--
ALTER TABLE `qualification_categories`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ranks`
--
ALTER TABLE `ranks`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
    ADD PRIMARY KEY (`id`),
  ADD KEY `group_code` (`group_code`);

--
-- Indexes for table `training`
--
ALTER TABLE `training`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `lecturers`
--
ALTER TABLE `lecturers`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `pedagogical_titles`
--
ALTER TABLE `pedagogical_titles`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `qualification_categories`
--
ALTER TABLE `qualification_categories`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `ranks`
--
ALTER TABLE `ranks`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `training`
--
ALTER TABLE `training`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
    ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`rank`) REFERENCES `ranks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`training`) REFERENCES `training` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `lecturers`
--
ALTER TABLE `lecturers`
    ADD CONSTRAINT `lecturers_ibfk_1` FOREIGN KEY (`category`) REFERENCES `qualification_categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lecturers_ibfk_2` FOREIGN KEY (`title`) REFERENCES `pedagogical_titles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `students`
--
ALTER TABLE `students`
    ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`group_code`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
