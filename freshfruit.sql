-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 26, 2021 at 09:01 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `freshfruit`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `bill_id` int(11) NOT NULL,
  `bill_date` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bill_product` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `root_price` double NOT NULL,
  `bill_price` double NOT NULL,
  `bill_information` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_user` int(11) NOT NULL,
  `name_user` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bill_id`, `bill_date`, `bill_product`, `root_price`, `bill_price`, `bill_information`, `id_user`, `name_user`) VALUES
(19, '2021/09/26', 'Fuji Apple - 8.0 x 2, Sầu riêng ri6 - 6.0 x 2, ', 28, 26.6, '12345678 - Da Nang', 2, 'giahunguser'),
(20, '2021/09/26', 'Fuji Apple - 8.0 x 4, Sầu riêng thường - 2.0 x 2, Cam Cao Phong Hòa Bình - 3.2 x 1, ', 39.2, 37.2, 'Ha Noi', 41, 'quocdeptrai');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `comment_content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `comment_datecreated` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_user` int(11) NOT NULL,
  `name_user` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `news_detail_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `comment_content`, `comment_datecreated`, `id_user`, `name_user`, `news_detail_id`) VALUES
(105, 'bài viết hay !', '26/09/2021', 2, 'hunguser', 16),
(108, 'haha', '26/09/2021', 41, 'quoc', 17);

-- --------------------------------------------------------

--
-- Table structure for table `commentchildren`
--

CREATE TABLE `commentchildren` (
  `comment_children_id` int(11) NOT NULL,
  `comment_children_content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `comment_children_datecreated` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_user` int(11) NOT NULL,
  `name_user` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `news_detail_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `contact_id` int(11) NOT NULL,
  `contact_phone` int(11) NOT NULL,
  `contact_subject` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `contact_message` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`contact_id`, `contact_phone`, `contact_subject`, `contact_message`) VALUES
(23, 905123456, 'Trái cây không được tươi', 'Lần trước mình có đặt bên bạn mà hàng ship về chưa được tươi như quảng cáo làm mình hơi thất vọng :(('),
(24, 374893025, 'Trái cây có nhiều quả héo', 'Hơn 1/4 là bị héo rồi mình hơi thất vọng về shop'),
(25, 857304934, 'Táo rất ngon !', 'Táo shop bán ngon lắm ạ khi nào hết mình sẽ đặt thêm'),
(26, 459068485, 'Nho rất ngon !', 'Nho hôm bữa mua trúng đượt giảm giá nên mua cùng giá tiền mà nhiều hơn mọi khi, sẽ ủng hộ shop dài dài');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `parent_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`menu_id`, `menu_name`, `parent_id`) VALUES
(1, 'Hoa quả nhập khẩu', 0),
(2, 'Táo Nhật Bản', 1),
(3, 'Cherry Mỹ', 1),
(4, 'Hoa quả nội địa', 0),
(5, 'Cam', 4),
(6, 'Sầu riêng', 4);

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `news_id` int(11) NOT NULL,
  `news_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`news_id`, `news_name`) VALUES
(1, 'Bạn cần biết ?'),
(2, 'Kiến thức cho bạn');

-- --------------------------------------------------------

--
-- Table structure for table `news_detail`
--

CREATE TABLE `news_detail` (
  `news_detail_id` int(11) NOT NULL,
  `news_detail_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `news_detail_date` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `news_detail_image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `news_detail_description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `news_id` int(11) NOT NULL,
  `news_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `news_detail`
--

INSERT INTO `news_detail` (`news_detail_id`, `news_detail_name`, `news_detail_date`, `news_detail_image`, `news_detail_description`, `news_id`, `news_name`) VALUES
(16, 'Chuối là loại trái cây hoàn hảo', '23/09/2021', 'chuoi-2489847986400.jpg', '<p>Sự đa dạng về gen l&agrave; ch&igrave;a kh&oacute;a tuổi thọ của một lo&agrave;i. Thật kh&ocirc;ng may, đ&oacute; l&agrave; điều m&agrave; chuối kh&ocirc;ng c&ograve;n c&oacute;. Trong khi c&oacute; hơn 300 loại chuối kh&aacute;c nhau, c&oacute; một loại chuối được ti&ecirc;u thụ &aacute;p đảo ở ch&acirc;u &Acirc;u, Canada v&agrave; Hoa Kỳ: chuối Cavendish.</p>\r\n\r\n<p>Đ&acirc;y l&agrave; một giống chuối ngon, tiện lợi v&agrave; được trồng tr&ecirc;n to&agrave;n thế giới, từ Hawaii đến Th&aacute;i Lan đến miền Trung Mỹ.</p>\r\n\r\n<p>Vấn đề l&agrave; mỗi một loại chuối Cavendish, kh&ocirc;ng quan trọng nơi n&oacute; được trồng v&agrave; n&oacute; đến từ đ&acirc;u, đều giống hệt nhau về mặt di truyền.</p>\r\n\r\n<p>Điều n&agrave;y l&agrave; một nhược điểm. Kh&ocirc;ng c&oacute; sự kh&aacute;c biệt di truyền, một loại nấm đơn hoặc bệnh c&oacute; thể ti&ecirc;u diệt to&agrave;n bộ giống chuối phổ biến nhất v&agrave; điều đ&oacute; đ&atilde; xảy ra một lần.</p>\r\n\r\n<p>Chuối Gros Michel giữ vị tr&iacute; l&agrave; một trong những giống chuối phổ biến nhất tr&ecirc;n thế giới cho đến khi bệnh nấm ti&ecirc;u diệt gần hết c&aacute;c c&acirc;y chuối Gros Michel, t&agrave;n ph&aacute; nền kinh tế của c&aacute;c nước sản xuất chuối như Honduras.</p>\r\n\r\n<p>H&agrave;ng tỷ đ&ocirc;la đ&atilde; được đổ v&agrave;o ng&agrave;nh c&ocirc;ng nghiệp chuối để t&igrave;m một sự thay thế cho giống chuối Gros Michel đ&atilde; bị hủy diệt.</p>\r\n\r\n<p>Mặc d&ugrave; những cố gắng để tạo ra một loại giống khoẻ hơn, đa dạng hơn về mặt di truyền, giống Cavendish vẫn đang phải đối mặt với c&ugrave;ng một loại tuyệt chủng như loại giống tiền nhiệm của n&oacute;.</p>\r\n\r\n<p>Một phần vấn đề l&agrave; do chuối kh&ocirc;ng được trồng từ hạt giống. Thay v&agrave;o đ&oacute;, phần lớn chuối được thụ phấn bằng tay, v&agrave; những bụi c&acirc;y mang quả sẽ mọc lại c&agrave;nh v&agrave; th&acirc;n c&acirc;y trong nhiều năm, hạn chế số lượng đa dạng di truyền trong c&acirc;y trồng.</p>\r\n\r\n<p>Một phần kh&aacute;c l&agrave; do nguồn gốc của chuối: hầu như tất cả chuối hiện nay đều l&agrave; đột biến của khoảng 10 c&acirc;y ban đầu được trồng tr&ecirc;n lục địa ch&acirc;u Phi.</p>\r\n', 1, 'Bạn cần biết ?'),
(17, 'Dưa hấu là một nguồn nước?', '23/09/2021', 'duahau-3487759057200.jpg', '<p>Trong khi ch&uacute;ng ta nghĩ rằng dưa hấu chủ yếu l&agrave; một m&oacute;n ngon trong một ng&agrave;y h&egrave; n&oacute;ng nực, ban đầu ch&uacute;ng được trồng v&igrave; một l&yacute; do rất thực tế.</p>\r\n\r\n<p>Một số t&agrave;i liệu tham khảo sớm nhất về việc trồng dưa hấu c&oacute; thể được t&igrave;m thấy trong chữ tượng h&igrave;nh Ai Cập c&oacute; ni&ecirc;n đại hơn 5.000 n&atilde;m.</p>\r\n\r\n<p>C&aacute;c nền văn h&oacute;a khắp ch&acirc;u Phi, Ấn Độ, Địa Trung Hải cũng đều c&oacute; những ghi ch&eacute;p đề cập đến dưa hấu. Ch&iacute;nh David Livingstone đ&atilde; x&aacute;c nhận nguồn gốc của dưa hấu, khi &ocirc;ng t&igrave;m thấy những c&aacute;nh đồng hoang d&atilde; mọc l&ecirc;n ở ch&acirc;u Phi.</p>\r\n\r\n<p>Dưa hấu ph&aacute;t triển mạnh ở những v&ugrave;ng đất kh&ocirc; v&agrave; từ l&acirc;u ch&uacute;ng đ&atilde; được sử dụng cho mục đ&iacute;ch quan trọng hơn việc chỉ l&agrave; một phần l&agrave;nh mạnh trong bữa ăn.</p>\r\n\r\n<p>Khoảng 92% của dưa hấu l&agrave; nước, v&agrave; trong nhiều v&ugrave;ng kh&ocirc; cằn của ch&acirc;u Phi, loại quả n&agrave;y từ l&acirc;u đ&atilde; được khai th&aacute;c v&agrave; sử dụng như một nguồn nước cho cả người v&agrave; động vật. Nhiều bằng chứng được t&igrave;m thấy c&ograve;n cho thấy rằng ch&uacute;ng đ&atilde; được c&aacute;c nh&agrave; th&aacute;m hiểm mang theo như một chai nước tự nhi&ecirc;n.</p>\r\n\r\n<p>Một lợi &iacute;ch kh&aacute;c của dưa hấu l&agrave; kh&ocirc;ng bỏ một thứ g&igrave;, v&agrave; tất cả c&aacute;c phần của quả đều c&oacute; thể ăn được. Ngo&agrave;i lớp thịt ngon ngọt, hạt dưa hấu c&oacute; thể được rang, v&agrave; thậm ch&iacute; cả vỏ c&oacute; thể được l&agrave;m mứt.</p>\r\n\r\n<p>Ở một số v&ugrave;ng, ch&uacute;ng được ng&acirc;m v&agrave; bỏ v&agrave;o lọ hoặc được ng&acirc;m muối để bảo quản. Nước &eacute;p dưa hấu ngọt ng&agrave;o được sử dụng để l&agrave;m bia ở Nga, v&agrave; n&oacute; cũng c&oacute; thể được sử dụng để l&agrave;m si r&ocirc;.</p>\r\n', 1, 'Bạn cần biết ?'),
(18, 'Ăn táo giúp tránh xa bệnh tật', '23/09/2021', 'tao-3553304599600.jpg', '<p>Ch&uacute;ng ta đ&atilde; từng nghe n&oacute;i: &ldquo;Mỗi ng&agrave;y một quả t&aacute;o sẽ kh&ocirc;ng phải đến gặp b&aacute;c sĩ&rdquo; rất nhiều lần. C&oacute; rất nhiều bằng chứng khoa học chứng minh đ&oacute; l&agrave; một sự thật tuyệt đối, v&agrave; việc ăn t&aacute;o thường xuy&ecirc;n c&oacute; thể cho bạn một số lợi &iacute;ch sức khỏe m&agrave; ngay cả ch&uacute;ng ta kh&ocirc;ng nghĩ tới.</p>\r\n\r\n<p>Một nghi&ecirc;n cứu từ Đại học bang Florida đ&atilde; chỉ ra rằng ăn một số &iacute;t t&aacute;o kh&ocirc; trong khoảng thời gian 6 th&aacute;ng sẽ khiến cho cholesterol xấu giảm mạnh, v&agrave; nghi&ecirc;n cứu n&agrave;y cho thấy một mối tương quan giữa t&aacute;o v&agrave; giảm c&acirc;n.</p>\r\n\r\n<p>Ch&uacute;ng ta đều biết rằng t&aacute;o c&oacute; đầy đủ những thứ tốt đẹp như vitamin C, nhưng điều đ&oacute; gi&uacute;p &iacute;ch g&igrave; trong qu&atilde;ng thời gian d&agrave;i?</p>\r\n\r\n<p>Những nghi&ecirc;n cứu d&agrave;i hạn cho thấy rằng những người ăn t&aacute;o &iacute;t c&oacute; nguy cơ bị đột quỵ, cải thiện chức năng h&ocirc; hấp v&agrave; dễ d&agrave;ng duy tr&igrave; số c&acirc;n l&agrave;nh mạnh.</p>\r\n\r\n<p>T&aacute;o cũng c&oacute; thể l&agrave;m giảm c&ograve;n một nửa nguy cơ bị một số bệnh ung thư, cải thiện chức năng n&atilde;o bộ v&agrave; sức khỏe tế b&agrave;o, ngăn ngừa chứng mất tr&iacute; v&agrave; c&aacute;c bệnh li&ecirc;n quan đến sự ph&aacute;t triển, v&agrave; giảm nguy cơ ph&aacute;t triển bệnh tiểu đường ở một người.</p>\r\n\r\n<p>Những quả t&aacute;o b&igrave;nh thường đ&ocirc;i khi bị lu mờ bởi một số si&ecirc;u tr&aacute;i c&acirc;y hợp xu hướng hơn, nhưng lịch sử cho thấy rằng lợi &iacute;ch sức khỏe của ch&uacute;ng đ&atilde; được biết đến trong nhiều thế kỷ.</p>\r\n\r\n<p>Những chứng t&iacute;ch của quả t&aacute;o đ&atilde; được t&igrave;m thấy trong c&aacute;c khu định cư thời tiền sử c&oacute; ni&ecirc;n đại 6.500 trước C&ocirc;ng nguy&ecirc;n, v&agrave; ch&uacute;ng ta vẫn đang thưởng thức c&ugrave;ng một số giống t&aacute;o m&agrave; tổ ti&ecirc;n xa xưa của ch&uacute;ng ta từng thưởng thức.</p>\r\n', 1, 'Bạn cần biết ?'),
(19, 'Lợi ích tuyệt vời từ quả nho', '23/09/2021', 'nho-4063862805900.jpg', '<p>C&ocirc;ng dụng ưu việt của nho đối với sức khỏe</p>\r\n\r\n<p>Cải thiện sức khỏe tim mạch</p>\r\n\r\n<p>Resveratrol một chất chống oxy h&oacute;a dồi d&agrave;o c&oacute; trong nho gi&uacute;p ngăn ngừa xơ vữa động mạch hoặc xơ cứng động mạch.&nbsp;</p>\r\n\r\n<p>Polyphenol gi&uacute;p bạn c&oacute; một tr&aacute;i tim khỏe mạnh bằng c&aacute;ch tăng mức HDL (cholesterol tốt) v&agrave; giảm mức độ vi&ecirc;m trong cơ thể. Lượng kali c&oacute; trong nho gi&uacute;p ổn định mức huyết &aacute;p, do đ&oacute; tạo điều kiện cho d&ograve;ng m&aacute;u chảy qua tim trơn tru v&agrave; ngăn ngừa nguy cơ đột quỵ.&nbsp;</p>\r\n\r\n<p>Hỗ trợ gi&uacute;p xương chắc khỏe hơn</p>\r\n\r\n<p>Loại quả n&agrave;y c&oacute; chứa nhiều chất dinh dưỡng cần thiết cho sự ph&aacute;t triển của xương như: canxi, magie, photpho, mangan v&agrave; vitamin K.</p>\r\n\r\n<p>L&agrave;m chậm qu&aacute; tr&igrave;nh l&atilde;o h&oacute;a, cải thiện l&agrave;n da</p>\r\n\r\n<p>Người ta thấy rằng,&nbsp;resveratrol một chất được t&igrave;m thấy trong nho ngăn ngừa c&aacute;c dấu hiệu l&atilde;o h&oacute;a v&agrave; c&aacute;c vấn đề về da kh&aacute;c gi&uacute;p da tươi trẻ v&agrave; khỏe hơn.</p>\r\n\r\n<p>Tăng cường n&atilde;o bộ, cải thiện tr&iacute; nhớ&nbsp;</p>\r\n\r\n<p>Một số nghi&ecirc;n cứu đ&atilde; ph&aacute;t hiện ra rằng, resveratrol gi&uacute;p tăng lưu lượng m&aacute;u đến n&atilde;o, do đ&oacute; n&oacute; c&oacute; thể gi&uacute;p tăng tốc độ phản ứng tinh thần v&agrave; chứng minh l&agrave; c&oacute; lợi cho những người mắc c&aacute;c bệnh li&ecirc;n quan đến n&atilde;o như Alzheimer.&nbsp;</p>\r\n\r\n<p>T&aacute;c dụng tốt cho c&aacute;c bệnh về mắt</p>\r\n\r\n<p>Một nghi&ecirc;n cứu trong ph&ograve;ng th&iacute; nghiệm đ&atilde; ph&aacute;t hiện ra rằng, resveratrol - một hoạt chất trong nho - c&oacute; khả năng bảo vệ c&aacute;c tế b&agrave;o v&otilde;ng mạc mắt trước tia cực t&iacute;m A. Gi&uacute;p giảm nguy cơ bệnh tho&aacute;i h&oacute;a điểm v&agrave;ng li&ecirc;n quan đến tuổi t&aacute;c (AMD), tăng nh&atilde;n &aacute;p, đục thủy tinh thể v&agrave; biến chứng mắt do tiểu đường.&nbsp;</p>\r\n\r\n<p>Ngo&agrave;i ra, n&oacute;&nbsp;c&oacute; chứa chất chống oxy h&oacute;a lutein v&agrave; zeaxanthin c&aacute;c hợp chất n&agrave;y gi&uacute;p bảo vệ mắt khỏi bị hư hại từ &aacute;nh s&aacute;ng xanh.</p>\r\n\r\n<p>Hỗ trợ giảm huyết &aacute;p</p>\r\n\r\n<p>Nho chứa nhiều kali, gi&uacute;p giảm huyết &aacute;p bằng c&aacute;ch c&acirc;n bằng c&aacute;c t&aacute;c động ti&ecirc;u cực của muối. Chế độ ăn &iacute;t natri c&oacute; lợi cho những người đang c&oacute;&nbsp;vấn đề huyết &aacute;p cao.&nbsp;</p>\r\n', 1, 'Bạn cần biết ?');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_price` double NOT NULL,
  `product_promotion` double NOT NULL,
  `product_description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `product_name`, `product_price`, `product_promotion`, `product_description`, `product_image`, `menu_id`, `menu_name`) VALUES
(358, 'Sầu riêng thường', 2, 10, '<p>Sầu ri&ecirc;ng l&agrave; loại quả c&oacute; nguồn gốc tại Đ&ocirc;ng Nam &Aacute;, cụ thể l&agrave; v&ugrave;ng Borneo v&agrave; Sumatra của Malaysia. V&agrave;o cuối thế kỷ 18, sầu ri&ecirc;ng bắt đầu được trồng nhiều tại c&aacute;c v&ugrave;ng n&ocirc;ng th&ocirc;n Đ&ocirc;ng Nam &Aacute;. Quả sầu ri&ecirc;ng được mệnh danh l&agrave; &ldquo;vua của c&aacute;c loại tr&aacute;i c&acirc;y&rdquo; nhờ m&ugrave;i hương nồng, ngọt đậm đ&agrave; b&ecirc;n trong. Ng&agrave;y nay, c&aacute;c nước trồng nhiều sầu ri&ecirc;ng nhất l&agrave; Việt Nam, Indonesia, Ấn Độ, Th&aacute;i Lan, L&agrave;o,&hellip;</p>\r\n\r\n<p>Tại Việt Nam, sầu ri&ecirc;ng xuất hiện lần đầu khoảng hơn 100 năm trước, do cha cố Gernt đem c&acirc;y giống từ Indonesia trở về. Sầu ri&ecirc;ng sau đ&oacute; được trồng ở nhiều nơi tr&ecirc;n cả nước, ph&acirc;n bố chủ yếu ở T&acirc;y Nguy&ecirc;n v&agrave; miền T&acirc;y. Trong đ&oacute;, sầu ri&ecirc;ng Đắk Lắk nổi tiếng với hương vị thơm lừng, ngon mềm, được rất nhiều người y&ecirc;u th&iacute;ch.</p>\r\n\r\n<p>Sầu ri&ecirc;ng l&agrave; một trong c&aacute;c loại tr&aacute;i c&acirc;y thường xuy&ecirc;n được lựa chọn xuất khẩu qua Trung Quốc, Mỹ,&hellip; Gi&aacute; xuất khẩu sầu ri&ecirc;ng t&ugrave;y từng loại m&agrave; sẽ c&oacute; mức gi&aacute; kh&aacute;c nhau, dao động từ 100,000 VND &ndash; 420,000 VND/kg.</p>\r\n', 'saurieng-3850974080100.jpg', 6, 'Sầu riêng - Hoa quả nội địa'),
(361, 'Sầu riêng ri6', 6, 5, '<p>Sầu ri&ecirc;ng ri6 thường được nhận dạng bởi dạng bầu dục, phần đ&aacute;y hẹp, vỏ quả mỏng c&oacute; m&agrave;u v&agrave;ng xanh, được trồng nhiều tại Đồng Bằng S&ocirc;ng Cửu Long.</p>\r\n\r\n<p>C&acirc;y sầu ri&ecirc;ng ri6 trồng khoảng 3 năm th&igrave; c&oacute; tr&aacute;i. Khi sầu ri&ecirc;ng ch&iacute;n, c&aacute;c gai nở bung, vỏ nứt ra rất dễ trong việc t&aacute;ch m&uacute;i. Th&ocirc;ng thường, sầu ri&ecirc;ng ri6 c&oacute; trọng lượng dao động từ 3 - 6kg.</p>\r\n\r\n<p>B&ecirc;n trong sầu ri&ecirc;ng ri6 l&agrave; những m&uacute;i sầu ri&ecirc;ng cơm kh&ocirc; r&aacute;o, d&agrave;y, vị ngọt, b&eacute;o vừa phải, khi cầm kh&ocirc;ng bị d&iacute;nh tay v&agrave; tỷ lệ hạt l&eacute;p l&ecirc;n tới 40%.</p>\r\n', 'sauriengri6-5221044237000.jpg', 6, 'Sầu riêng - Hoa quả nội địa'),
(362, 'Sầu riêng Thái', 5.5, 0, '<p>Sầu ri&ecirc;ng Th&aacute;i cơm m&agrave;u v&agrave;ng nhạt, vị ngọt b&eacute;o b&ugrave;i, thơm trung b&igrave;nh, cơm kh&ocirc; r&aacute;o, c&oacute; thể bảo quản được l&acirc;u. Hạt rất l&eacute;p, tỉ lệ cơm cao tr&ecirc;n 30%.</p>\r\n\r\n<p>Tr&aacute;i h&igrave;nh trứng hoặc c&oacute; h&igrave;nh chữ nhật trung b&igrave;nh mỗi tr&aacute;i nặng 2 &ndash; 6kg, vỏ mỏng m&agrave;u xanh k&eacute;t. Mật độ của gai d&agrave;y v&agrave; d&agrave;i 1,25 cm. Cuống 5 &ndash; 8 cm.</p>\r\n\r\n<p>Sầu ri&ecirc;ng th&aacute;i l&agrave; giống sầu ri&ecirc;ng ở th&aacute;i lan v&agrave; được du nhập về Việt Nam. Một trong những giống được đ&aacute;nh gi&aacute; l&agrave; ngon nhất được thị trường thế giới c&ocirc;ng nhận. Giống sầu ri&ecirc;ng c&oacute; chất lượng v&agrave; cả năng suất ổn định, hiệu quả cao .</p>\r\n\r\n<p>C&acirc;y&nbsp;sầu ri&ecirc;ng th&aacute;i ph&aacute;t triển mạnh, t&aacute;n tho&aacute;ng, tuổi thọ c&acirc;y đạt từ 25 đến 30 năm. C&agrave;nh thưa v&agrave; ph&aacute;t triển vu&ocirc;ng g&oacute;c với th&acirc;n. Thu hoạch sau 120 đến 135 ng&agrave;y sau khi đậu quả.</p>\r\n\r\n<p>Tại Việt Nam trồng phổ biến hai giống đ&oacute; l&agrave; sầu ri&ecirc;ng monthong th&aacute;i v&agrave; Sầu ri&ecirc;ng th&aacute;i da xanh (sầu ri&ecirc;ng Th&aacute;i dona), ngo&agrave;i ra c&ograve;n giống sầu ri&ecirc;ng Th&aacute;i cũ đ&iacute;t bằng chất lượng thấp hơn.</p>\r\n', 'sauriengthai-5743412589100.jpg', 6, 'Sầu riêng - Hoa quả nội địa'),
(363, 'Sầu riêng Cái Mơn', 4, 5, '<p>Miền T&acirc;y s&ocirc;ng nước Cửu Long được ph&ugrave; sa bồi đắp quanh năm, đất đai thổ nhưỡng tốt tươi tạo n&ecirc;n những c&ugrave; lao trồng c&acirc;y tr&aacute;i ăn quả. Sở dĩ v&ugrave;ng đất n&agrave;y tạo n&ecirc;n được giống sầu ri&ecirc;ng nổi tiếng cũng bởi chất lượng đất đặc biệt. Mặt kh&aacute;c, sầu ri&ecirc;ng trồng ở v&ugrave;ng n&agrave;y l&agrave; hạt l&eacute;p n&ecirc;n khi lai tạo, nếu trồng&nbsp;vườn sầu ri&ecirc;ng C&aacute;i Mơn&nbsp;thường kh&ocirc;ng giữ lại được những chất lượng tuyệt hảo như c&acirc;y mẹ cho ra ban đầu.&nbsp;</p>\r\n\r\n<p>Để giữ lại được chất lượng nguy&ecirc;n vẹn của giống sầu ri&ecirc;ng tại C&aacute;i Mơn, bắt buộc phải sử dụng nhiều biện ph&aacute;p kh&aacute;c nhau từ chiết gh&eacute;p đến lai tạo hay nh&acirc;n giống từ c&acirc;y gốc. L&agrave;m c&aacute;ch n&agrave;o tốt nhất để sầu ri&ecirc;ng vừa bảo đảm được độ thơm ngon vốn c&oacute;, m&agrave; lại c&ograve;n tăng th&ecirc;m khả năng chống lại s&acirc;u bệnh tật. Một mặt bảo tồn, mặt kh&aacute;c lại ph&aacute;t triển v&agrave; nh&acirc;n rộng ra để tăng năng suất, đưa loại quả n&agrave;y giới thiệu khắp mọi nơi.&nbsp;</p>\r\n', 'sauriengcaimon-6429379969600.jpg', 6, 'Sầu riêng - Hoa quả nội địa'),
(364, 'Cam Cao Phong Hòa Bình', 3.2, 0, '<p>Cam Cao Phong c&oacute; m&agrave;u sắc v&agrave;ng nhạt, quả to, mọng nước, kh&ocirc;ng v&agrave;ng &oacute;ng như cam Trung Quốc. Nếu cấu (b&oacute;c vỏ ra) c&oacute; m&ugrave;i thơm rất đặc trưng, đặc biệt khi cắt khỏi c&agrave;nh khoảng một&nbsp;ng&agrave;y th&igrave; cả l&aacute; v&agrave; quả đều h&eacute;o&nbsp;v&igrave; kh&ocirc;ng d&ugrave;ng chất bảo quản, nhưng chất lượng th&igrave; kh&ocirc;ng ảnh hưởng, chứ kh&ocirc;ng tươi như cam Trung Quốc.</p>\r\n\r\n<p>Loại quả n&agrave;y được trồng ở tỉnh H&ograve;a B&igrave;nh, v&agrave;o vụ từ th&aacute;ng 9 đến đầu th&aacute;ng 3 năm sau. V&agrave;i năm trở lại đ&acirc;y, cam Cao Phong bắt đầu c&oacute; thương hiệu, chỗ đứng tr&ecirc;n thị trường nhờ vị ngọt thanh, đậm đ&agrave;. Với hơn 600 ha, tỉnh H&ograve;a B&igrave;nh kỳ vọng kh&ocirc;ng chỉ ph&aacute;t triển loại quả n&agrave;y phục vụ thị trường trong nước m&agrave; c&ograve;n hướng tới xuất khẩu.</p>\r\n', 'camcaophonghoabinh-7529364236000.jpg', 5, 'Cam - Hoa quả nội địa'),
(365, 'Cam sành Hà Giang', 2.7, 8, '<p>Cam s&agrave;nh H&agrave; Giang l&agrave; một trong những đặc sản nổi tiếng của H&agrave; Giang. Được thừa hưởng kh&iacute; hậu thuận lợi, những c&acirc;y cam ở đ&acirc;y&nbsp;sinh trưởng v&agrave; ph&aacute;t triển rất tốt cho ra những tr&aacute;i ngon đặc trưng của v&ugrave;ng n&uacute;i.&nbsp;</p>\r\n\r\n<p>Với đặc điểm l&agrave; vỏ d&agrave;y, sần, l&otilde;i cam v&agrave;ng sậm, c&oacute; hạt, ăn c&oacute; vị ngọt kh&eacute; lẫn vị chua d&ocirc;n dốt,&nbsp;đậm đ&agrave;, cam s&agrave;nh H&agrave; Giang lu&ocirc;n l&agrave; lựa chọn số một của người ti&ecirc;u d&ugrave;ng. Nhưng những năm gần đ&acirc;y, giống c&acirc;y ăn quả truyền thống n&agrave;y bị tho&aacute;i h&oacute;a, giảm cả về diện t&iacute;ch, chất lượng, chỗ đứng tr&ecirc;n thị trường. Thực trạng đ&oacute; đ&ograve;i hỏi H&agrave; Giang cần c&oacute; c&aacute;c giải ph&aacute;p, nhằm lấy lại vị thế của v&ugrave;ng cam truyền thống.</p>\r\n', 'camsanhhagiang-8527715168200.jpg', 5, 'Cam - Hoa quả nội địa'),
(366, 'Cam bù Hà Tĩnh', 3.45, 12, '<p>Cam b&ugrave; Hương Sơn mọng nước, vị ngọt thanh v&agrave; thơm, nhiều năm qua trở th&agrave;nh đặc sản qu&yacute; của người d&acirc;n Hương Sơn (H&agrave; Tĩnh), thường được đem l&agrave;m qu&agrave; biếu.</p>\r\n\r\n<p>M&ugrave;a thu hoạch cam b&ugrave; Hương Sơn bắt đầu từ đầu th&aacute;ng 12 &Acirc;m lịch v&agrave; k&eacute;o d&agrave;i đến qua Tết nguy&ecirc;n đ&aacute;n. Mỗi c&acirc;y cam b&ugrave; Hương Sơn cao khoảng 2m, cho chừng 100 quả, c&oacute; c&acirc;y gần 200 quả. Để cho quả kh&ocirc;ng s&agrave; xuống mặt đất, người d&acirc;n đ&atilde; phải chống cọc tre xung quanh c&aacute;c c&agrave;nh c&acirc;y. Tuổi thọ trung b&igrave;nh của mỗi c&acirc;y l&agrave; 10-15 năm, sau đ&oacute; phải thay mới.&nbsp;</p>\r\n', 'cambuhatinh-637552816900.jpg', 5, 'Cam'),
(368, 'Cam Khe Mây Hà Tĩnh', 2.4, 5, '<p>Từ l&acirc;u, Hương Kh&ecirc; (H&agrave; Tĩnh) đ&atilde; nổi tiếng với cam Khe M&acirc;y ngọt l&agrave;nh, thơm m&aacute;t. Tuy nhi&ecirc;n, sản phẩm cam Khe M&acirc;y vẫn&nbsp;chủ yếu được ti&ecirc;u thụ qua thương l&aacute;i. Do chưa c&oacute; thương hiệu ri&ecirc;ng n&ecirc;n cam Khe M&acirc;y vẫn kh&oacute; cạnh tranh với thị trường ngoại tỉnh, thậm ch&iacute; c&ograve;n bị giả mạo bởi những giống cam kh&aacute;c.</p>\r\n\r\n<p>Cam Khe M&acirc;y đ&atilde; khẳng định được thương hiệu bởi vị ngọt thanh, hương thơm đặc trưng, t&eacute;p mọng nước. Cam đ&atilde; trở th&agrave;nh một trong những c&acirc;y trồng chủ lực mang lại thu nhập ch&iacute;nh cho người d&acirc;n v&ugrave;ng Khe M&acirc;y (x&atilde; Hương Đ&ocirc;).</p>\r\n', 'camkhemayhatinh-12421145951600.jpg', 5, 'Cam - Hoa quả nội địa'),
(369, 'Fuji Apple', 8, 5, '<p>Nhắc đến loại t&aacute;o b&aacute;n chạy nhất ở Nhật Bản kh&ocirc;ng thể n&agrave;o kh&ocirc;ng nhắc đến Fuji Apples. Đ&acirc;y l&agrave; một lo&agrave;i t&aacute;o nổi tiếng bởi cả hương vị v&agrave; thẩm mĩ. Fuji l&agrave; một giống t&aacute;o của Nhật Bản được sản xuất bằng c&aacute;ch thụ phấn ch&eacute;o giữa c&aacute;c giống Red Delicious v&agrave; Virginia Ralls Janet v&agrave;o cuối những năm 1930.</p>\r\n\r\n<p>Loại t&aacute;o n&agrave;y kh&ocirc;ng chỉ c&oacute; lớp vỏ m&agrave;u v&agrave;ng đỏ hấp dẫn; m&agrave; c&ograve;n nổi tiếng với vị ngọt đặc biệt. T&aacute;o c&oacute; độ chua thấp, độ mọng nước, độ săn chắc v&agrave; gi&ograve;n. Nhờ những yếu tố tuyệt vời n&agrave;y v&agrave; thời gian bảo quản l&acirc;u; những quả t&aacute;o Fuji thơm m&aacute;t l&agrave; một trong những giống t&aacute;o được trồng phổ biến nhất tr&ecirc;n khắp thế giới. Ch&uacute;ng đắt v&igrave; kh&iacute; hậu ở Nhật Bản kh&ocirc;ng th&iacute;ch hợp cho việc trồng t&aacute;o. V&igrave; vậy, mỗi quả cần được bọc trong giấy b&oacute;ng k&iacute;nh khi n&oacute; vẫn đang ph&aacute;t triển tr&ecirc;n c&acirc;y. V&agrave; loại t&aacute;o n&agrave;y cần sự chăm s&oacute;c v&ocirc; c&ugrave;ng kĩ lưỡng trong suốt một thời gian d&agrave;i.</p>\r\n', 'fujiapple-616749650600.jpg', 2, 'Táo Nhật Bản - Hoa quả nhập khẩu');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_MODIFIER'),
(3, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `information` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `role_id` int(11) NOT NULL,
  `enabled` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `fullname`, `password`, `information`, `image`, `email`, `role_id`, `enabled`) VALUES
(1, 'hungadmin', 'giahungadmin', '$2a$10$Vibe.t3ir7EsZZa4gvLpaedcT9VgxtAfhbw270b.QFv0Ft0AAZpYy', '12345678 - Hà Nội', 'adminstrator.jpg', '', 1, 1),
(2, 'hunguser', 'giahunguser', '$2a$10$y75s5PmiE6Ev4ZRQmI0XWureFoQ1Rh/aX20lUqh/33AVQp4g2f7FG', '12345678 - Ha Noi', 'chuoi-2489847986400.jpg', 'hungpbc99@gmail.com', 3, 1),
(3, 'hungmod', 'giahungmod', '$2a$10$Vibe.t3ir7EsZZa4gvLpaedcT9VgxtAfhbw270b.QFv0Ft0AAZpYy', '12345678 - Hồ Chí Minh', 'moderator.png', '', 2, 1),
(41, 'quoc', 'quocdeptrai', '$2a$10$Oo7UINnoJk0rj1.FyLX96u0HqXhdiNi1/yp1Bc7.WdVbHrbnKqzFm', 'Da Nang', 'avatar-12572388936200.png', 'hungpbc99@gmail.com', 3, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`);

--
-- Indexes for table `commentchildren`
--
ALTER TABLE `commentchildren`
  ADD PRIMARY KEY (`comment_children_id`),
  ADD KEY `comment_id` (`comment_id`);

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`contact_id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menu_id`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`news_id`);

--
-- Indexes for table `news_detail`
--
ALTER TABLE `news_detail`
  ADD PRIMARY KEY (`news_detail_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- AUTO_INCREMENT for table `commentchildren`
--
ALTER TABLE `commentchildren`
  MODIFY `comment_children_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `contact_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `news_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `news_detail`
--
ALTER TABLE `news_detail`
  MODIFY `news_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=379;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
