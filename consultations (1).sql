-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 02, 2021 at 10:21 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `consultations`
--

-- --------------------------------------------------------

--
-- Table structure for table `accountdetails`
--

CREATE TABLE `accountdetails` (
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Firstname` varchar(50) NOT NULL,
  `Lastname` varchar(50) NOT NULL,
  `Gender` varchar(7) NOT NULL,
  `DateOfBirth` varchar(15) NOT NULL,
  `Position` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accountdetails`
--

INSERT INTO `accountdetails` (`Username`, `Password`, `Firstname`, `Lastname`, `Gender`, `DateOfBirth`, `Position`) VALUES
('Anbu85', 'Anbu.Sahanna@23', 'Sahanna', 'Anbu', 'Female', '2004-04-05', 'Prefect'),
('eliznab74', 'el.ii_znab47', 'Banzile', 'Nhlebela', 'Male', '2003-10-28', 'Head Prefect'),
('ndazo78', '/*-+65412.', 'Mthandazo', 'Nkomo', 'Male', '1981-04-10', 'Teacher'),
('Zwakele', '19714456', 'Zwile', 'Nhlebela', 'Female', '2003-10-28', 'Teacher');

-- --------------------------------------------------------

--
-- Table structure for table `conlog`
--

CREATE TABLE `conlog` (
  `ID` int(5) NOT NULL,
  `StudentID` varchar(50) NOT NULL,
  `OffenceID` varchar(50) NOT NULL,
  `Sanction` varchar(50) NOT NULL,
  `Subject` varchar(50) NOT NULL,
  `ParentsTold` varchar(6) NOT NULL,
  `DateOfOffence` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `conlog`
--

INSERT INTO `conlog` (`ID`, `StudentID`, `OffenceID`, `Sanction`, `Subject`, `ParentsTold`, `DateOfOffence`) VALUES
(1, 'NHLE0017', 'L1OOB', 'Verbal Reprimand', 'Outside of Class', 'No', '2021-09-23'),
(3, 'TSUR8965', 'L1PF', 'Break Detention', 'Outside of Class', 'No', '2021-09-30'),
(4, 'NHLE0017', 'L1AL', 'Break Detention', 'Outside of Class', 'No', '2021-10-01'),
(5, 'NHLE0017', 'L1PF', 'Break Detention', 'Outside of Class', 'No', '2021-09-23');

-- --------------------------------------------------------

--
-- Table structure for table `offences`
--

CREATE TABLE `offences` (
  `OffenceID` varchar(20) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `Level` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `offences`
--

INSERT INTO `offences` (`OffenceID`, `Description`, `Level`) VALUES
('L1AL', 'Late Arrival to School', 1),
('L1CG', 'Chewing Gum', 1),
('L1DTAP', 'Disrespect to a Prefect', 1),
('L1LT', 'Act of Littering', 1),
('L1OOB', 'Being in any out of bounds place', 1),
('L1PF', 'Play Fighting', 1),
('L2DOP', 'Disruption of Peace', 2),
('L2HND', 'Homework Not Done', 2),
('L2IPC', 'Inappropriate physical contact', 2),
('L2RPL1', 'Repeated Level One Offences', 2),
('L2TR', 'Act of Trading', 2),
('L2VL', 'Using Vulgar Language', 2),
('L3BU', 'Act of Bullying', 3),
('L3DOP', 'Destruction of property or vandelism', 3),
('L3FTAD', 'Failure to attend Detention', 3),
('L3RPL2', 'Repeated Level Two offences', 3),
('L3TR', 'Act ofTruancy', 3),
('L3VSA', 'Verbal Sexual Abuse', 3),
('L4ISB', 'Inappropriate Sexual Behaviour', 4),
('L4PA', 'Physical Assault', 4),
('L4PG', 'Act of Plagiarism', 4),
('L4RPL3', 'Repeated Level 3 offences', 4),
('L4TF', 'Act of Theft', 4);

-- --------------------------------------------------------

--
-- Table structure for table `parents`
--

CREATE TABLE `parents` (
  `ID` int(11) NOT NULL,
  `StudentID` varchar(50) NOT NULL,
  `Firstname` varchar(50) NOT NULL,
  `Lastname` varchar(50) NOT NULL,
  `Gender` varchar(50) NOT NULL,
  `Relationship` varchar(50) NOT NULL,
  `Cellnumber` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parents`
--

INSERT INTO `parents` (`ID`, `StudentID`, `Firstname`, `Lastname`, `Gender`, `Relationship`, `Cellnumber`) VALUES
(1, 'NHLE0017', 'Zandile', 'Maziya', 'Female', 'Mother', '+268 7802 4503'),
(4, 'TSUR8965', 'Pardon', 'Tsuro', 'Male', 'Father', '+268 7832 4629');

-- --------------------------------------------------------

--
-- Table structure for table `security`
--

CREATE TABLE `security` (
  `Username` varchar(50) NOT NULL,
  `Question` varchar(1000) NOT NULL,
  `Answer` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `security`
--

INSERT INTO `security` (`Username`, `Question`, `Answer`) VALUES
('Anbu85', 'How old are you?', '16'),
('eliznab74', 'Who do you love most?', 'Me'),
('ndazo78', 'What subject do you mark?', 'AP Maths'),
('Zwakele', '1 plus 1', '2');

-- --------------------------------------------------------

--
-- Table structure for table `studentlog`
--

CREATE TABLE `studentlog` (
  `StudentID` varchar(50) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Gender` text NOT NULL,
  `Grade` int(3) NOT NULL,
  `DateOfBirth` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `studentlog`
--

INSERT INTO `studentlog` (`StudentID`, `FirstName`, `LastName`, `Gender`, `Grade`, `DateOfBirth`) VALUES
('ABDU0134', 'Hamaaz', 'Abdulah', 'Male', 11, '2004-06-10'),
('ANBU00118', 'Sahanna', 'Anbu', 'Female', 11, '2005-04-05'),
('BISE00115', 'Kebron', 'Bisetegn', 'Female', 12, '2004-04-14'),
('DLAM01275', 'Blessings', 'Dlamini', 'Male', 10, '2005-02-09'),
('DLAM0129', 'Sanalwami', 'Dlamini', 'Female', 11, '2004-10-30'),
('JOSE00125', 'Albetinah', 'Joseph', 'Female', 12, '2003-05-04'),
('KLOS0024', 'Grace', 'Kloss', 'Female', 12, '2003-11-16'),
('LUKH00114', 'Owethu', 'Lukhele', 'Female', 8, '2007-02-19'),
('MAPH00117', 'Phathwa', 'Maphalala', 'Female', 12, '2004-03-03'),
('MNGU00237', 'Waziwe', 'Mnguni', 'Male', 10, '2006-01-02'),
('NDLO0215', 'Mhlengi', 'Ndlovu', 'Male', 10, '2005-12-10'),
('NDZI03124', 'Heather', 'Ndzinisa', 'Female', 10, '2005-03-17'),
('NHLE0017', 'Banzile', 'Nhlebela', 'Male', 12, '2003-10-28'),
('NHLE0126', 'Zwile', 'Nhlebela', 'Male', 8, '2007-07-10'),
('SIBA0021', 'Sibusiso', 'Sibandze', 'Male', 12, '2004-01-27'),
('TSUR8965', 'Dilan', 'Tsuro', 'Male', 9, '2004-10-13'),
('WESE00241', 'Joseph', 'Wesenje', 'Male', 8, '2007-08-07'),
('YOUN02568', 'Lauren', 'Young', 'Female', 11, '2004-04-07');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accountdetails`
--
ALTER TABLE `accountdetails`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `conlog`
--
ALTER TABLE `conlog`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `StudentID` (`StudentID`),
  ADD KEY `OffenceID` (`OffenceID`);

--
-- Indexes for table `offences`
--
ALTER TABLE `offences`
  ADD PRIMARY KEY (`OffenceID`);

--
-- Indexes for table `parents`
--
ALTER TABLE `parents`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `StudentID` (`StudentID`);

--
-- Indexes for table `security`
--
ALTER TABLE `security`
  ADD PRIMARY KEY (`Username`),
  ADD KEY `Username` (`Username`);

--
-- Indexes for table `studentlog`
--
ALTER TABLE `studentlog`
  ADD PRIMARY KEY (`StudentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `conlog`
--
ALTER TABLE `conlog`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `parents`
--
ALTER TABLE `parents`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
