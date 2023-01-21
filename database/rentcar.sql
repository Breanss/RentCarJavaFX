-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 21 Sty 2023, 13:08
-- Wersja serwera: 10.4.22-MariaDB
-- Wersja PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `rentcar`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `car`
--

CREATE TABLE `car` (
  `id` int(10) NOT NULL,
  `brand` varchar(40) NOT NULL,
  `model` varchar(40) NOT NULL,
  `engine` varchar(40) NOT NULL,
  `image` varchar(700) NOT NULL,
  `price` float NOT NULL,
  `avalible` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `car`
--

INSERT INTO `car` (`id`, `brand`, `model`, `engine`, `image`, `price`, `avalible`) VALUES
(1, 'Ford', 'Mustang', '5.0', 'src/main/resources/com/example/demo1/img/mustang.jpg', 700, 1),
(2, 'Lamorghini', 'Huracan', '5.2', 'src/main/resources/com/example/demo1/img/lambo.jpg', 1600, 1),
(3, 'Chevrolet', 'Corvette', '6.2', 'src/main/resources/com/example/demo1/img/corvette.png', 1000, 1),
(4, 'Dodge', 'Challenger', '5.7', 'src/main/resources/com/example/demo1/img/dodge.jpg', 900, 1),
(5, 'Nissan', 'Gtr', '3.8', 'src/main/resources/com/example/demo1/img/nissan.jpg', 950, 1),
(6, 'Audi', 'Rs', '2.5', 'src/main/resources/com/example/demo1/img/audi.jpg', 600, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `admin`) VALUES
(39, 'admin', 'YWRtaW4x', 'admin@o2.pl', 1),
(40, 'jnowak', 'bm93YWsx', 'jnowak@o2.pl', 0),
(41, 'kkowalski', 'a293YWxza2kx', 'kkowalski@o2.pl', 0),
(43, 'mbiernat', 'YXNkZ2ZkZzI=', 'biernat2@op.pl', 0);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `car`
--
ALTER TABLE `car`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
