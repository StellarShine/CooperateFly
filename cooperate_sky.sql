-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-10-11 16:34:26
-- 服务器版本： 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cooperate_sky`
--

-- --------------------------------------------------------

--
-- 表的结构 `catalog_table`
--

CREATE TABLE IF NOT EXISTS `catalog_table` (
`id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `data_info`
--

CREATE TABLE IF NOT EXISTS `data_info` (
`id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type` int(11) NOT NULL,
  `package_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `data_structure`
--

CREATE TABLE IF NOT EXISTS `data_structure` (
`id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `data_value`
--

CREATE TABLE IF NOT EXISTS `data_value` (
`id` int(11) NOT NULL,
  `version_id` int(11) NOT NULL,
  `data_info_id` int(11) NOT NULL,
  `value` mediumtext NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `model_info`
--

CREATE TABLE IF NOT EXISTS `model_info` (
`id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `state` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `package_info`
--

CREATE TABLE IF NOT EXISTS `package_info` (
`id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `pid` varchar(1000) DEFAULT NULL,
  `sid` varchar(1000) DEFAULT NULL,
  `child_count` int(11) DEFAULT NULL,
  `director_id` int(11) DEFAULT NULL,
  `extra_attributes` mediumtext
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `package_version`
--

CREATE TABLE IF NOT EXISTS `package_version` (
`id` int(11) NOT NULL,
  `submit_time` date DEFAULT NULL,
  `package_id` int(11) NOT NULL,
  `version_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `staff_structure`
--

CREATE TABLE IF NOT EXISTS `staff_structure` (
`id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `user_info`
--

CREATE TABLE IF NOT EXISTS `user_info` (
`id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(45) NOT NULL,
  `package_id` varchar(100) DEFAULT NULL,
  `role` int(11) DEFAULT '2'
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `user_info`
--

INSERT INTO `user_info` (`id`, `user_name`, `password`, `package_id`, `role`) VALUES
(1, 'ligonghui', '123456', '23', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `catalog_table`
--
ALTER TABLE `catalog_table`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_info`
--
ALTER TABLE `data_info`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_structure`
--
ALTER TABLE `data_structure`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_value`
--
ALTER TABLE `data_value`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `model_info`
--
ALTER TABLE `model_info`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `package_info`
--
ALTER TABLE `package_info`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `package_version`
--
ALTER TABLE `package_version`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `staff_structure`
--
ALTER TABLE `staff_structure`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `catalog_table`
--
ALTER TABLE `catalog_table`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `data_info`
--
ALTER TABLE `data_info`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `data_structure`
--
ALTER TABLE `data_structure`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `data_value`
--
ALTER TABLE `data_value`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `model_info`
--
ALTER TABLE `model_info`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `package_info`
--
ALTER TABLE `package_info`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `package_version`
--
ALTER TABLE `package_version`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `staff_structure`
--
ALTER TABLE `staff_structure`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
