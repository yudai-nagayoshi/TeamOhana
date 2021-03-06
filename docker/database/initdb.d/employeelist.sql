-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- ホスト: employeelist_mysql_host
-- 生成日時: 2021 年 5 月 15 日 18:17
-- サーバのバージョン： 5.7.34
-- PHP のバージョン: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `employee_db`
--
CREATE DATABASE IF NOT EXISTS `employee_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `employee_db`;

-- --------------------------------------------------------


-- --------------------------------------------------------

--
-- テーブルの構造 `positions`
--

DROP TABLE IF EXISTS `positions`;
CREATE TABLE `positions` (
  `position_id` int(11) NOT NULL,
  `position` varchar(20) NOT NULL COLLATE utf8mb4_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `positions`
--
ALTER TABLE `positions`
  ADD PRIMARY KEY (`position_id`);

--
-- データ挿入
--

INSERT INTO `positions` VALUES (1,"代表取締役社長");
INSERT INTO `positions` VALUES (2,"専務取締役");
INSERT INTO `positions` VALUES (3,"部長");
INSERT INTO `positions` VALUES (4,"課長");
INSERT INTO `positions` VALUES (5,"一般社員");

--
-- テーブルの AUTO_INCREMENT `positions`
--


-- --------------------------------------------------------

--
-- テーブルの構造 `departments`
--

DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `department_id` int(11) NOT NULL,
  `department` varchar(20) NOT NULL COLLATE utf8mb4_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `departments`
--

ALTER TABLE `departments`
  ADD PRIMARY KEY (`department_id`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `departments`

--
-- データ挿入
--

INSERT INTO `departments` VALUES (1,"総務部");
INSERT INTO `departments` VALUES (2,"人事部");
INSERT INTO `departments` VALUES (3,"経理部");
INSERT INTO `departments` VALUES (4,"営業部");
INSERT INTO `departments` VALUES (5,"開発部");
INSERT INTO `departments` VALUES (6,"所属なし");
--
--
-- テーブルの構造 `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `employee_id` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL COLLATE utf8mb4_unicode_ci,
  `furigana` varchar(20) NOT NULL COLLATE utf8mb4_unicode_ci,
  `email` varchar(40) NOT NULL COLLATE utf8mb4_unicode_ci,
  `phone_number` varchar(20) NOT NULL COLLATE utf8mb4_unicode_ci,
  `joining_date` date NOT NULL,
  `position_id` int NOT NULL ,
  `department_id` int NOT NULL,
  `adding_date` DATETIME NOT NULL,
  `last_update_date` DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`);

--
-- 外部キーの設定
--
ALTER TABLE `employees` 
  ADD FOREIGN KEY (`department_id`) REFERENCES `departments`(`department_id`);

ALTER TABLE `employees`
 ADD FOREIGN KEY (`position_id`) REFERENCES `positions`(`position_id`);

--
-- CURRENT_TIMESTAMPの設定
--

ALTER TABLE `employees`
  MODIFY `adding_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE `employees`
  MODIFY `last_update_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
COMMIT;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
