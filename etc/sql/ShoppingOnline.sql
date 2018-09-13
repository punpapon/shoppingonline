CREATE DATABASE PG_EXAM_SHOPPING;

CREATE TABLE `user` (
  `USER_ID` int(11) NOT NULL auto_increment,
  `LOGIN_NAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `FIRST_NAME` varchar(100) NOT NULL,
  `LAST_NAME` varchar(100) NOT NULL,
  `ADMIN` varchar(1) NOT NULL default 'N',
  PRIMARY KEY  (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=tis620;

CREATE TABLE `product_type` (
  `TYPE_ID` int(11) NOT NULL auto_increment,
  `TYPE_DESC` varchar(100) NOT NULL,
  `SEQ` int(11) NULL,
  `IMAGE_PATH` varchar(100) NULL,
  PRIMARY KEY  (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=tis620;

CREATE TABLE `product` (
  `ID` int(11) NOT NULL auto_increment,
  `CODE` varchar(100) NOT NULL,
  `DESC` varchar(100) NULL,
  `PRICE` int(11) NULL,
  `STOCK_NUM` int(11) NULL,
  `IMAGE_NAME` varchar(100) NULL,
  `TYPE_ID` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=tis620;

CREATE TABLE `order_main` (
  `ID` int(11) NOT NULL auto_increment,
  `NO` int(11) NOT NULL,
  `TOTAL_PRICE` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `ORDER_DATE` varchar(8) NOT NULL,
  `SHIP` varchar(1) NOT NULL  default 'N',
  `SHIP_DATE` varchar(8) NULL,
  `TRACKING_NO` varchar(20) NULL,
  `CANCEL` varchar(1) NOT NULL  default 'N',
`NOTE` varchar(500) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=tis620;

CREATE TABLE `order_detail` (
  `ID` int(11) NOT NULL auto_increment,
  `PRODUCT_ID` int(11) NOT NULL,
  `TOTAL_NUM` int(11) NOT NULL,
  `TOTAL_PRICE` varchar(8) NOT NULL,
  `ORDER_ID` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=tis620;

INSERT INTO user
(LOGIN_NAME, PASSWORD, FIRST_NAME, LAST_NAME, ADMIN) VALUES
('manit.r', '123456', 'มานิตย์', 'รัศมีวงศ์', 'N'), 
('chujai.m', '123456', 'ชูใจ', 'มานะ', 'N'), 
('kanda.m', '123456', 'กาญดา', 'มาที', 'N'), 
('arunya.k', '123456', 'อรัญญา', 'ขุนแร่', 'Y')
;

INSERT INTO product_type
(TYPE_DESC, SEQ, IMAGE_PATH) VALUES
('เสื้อผ้า', 1, NULL), 
('รองเท้า', 2, NULL), 
('เฟอร์นิเจอร์', 3, NULL), 
('เครื่องประดับ', 4, NULL)
;

INSERT INTO product
(CODE, DESC, PRICE, STOCK_NUM, IMAGE_NAME, TYPE_ID) VALUES
('S001', 'ชุดกระโปรงทำงาน สีขาว ชมพู', 1500, 5, NULL, 1), 
('S002', 'ชุดกระโปรงทำงาน สีน้ำตาลอ่อน ดำ', 1500, 5, NULL, 1), 
('S003', 'ชุดกระโปรงทำงาน สีดำ ดำขาว', 1500, 5, NULL, 1), 
('S004', 'ชุดกระโปรงทำงาน สีเทา', 1000, 0, NULL, 1), 
('S006', 'ชุดกระโปรงทำงาน สีดำ', 1000, 10, NULL, 1), 
('S007', 'ชุดกระโปรงทำงาน สีขาว น้ำตาล', 1000, 10, NULL, 1), 
('S008', 'ชุดกระโปรงทำงาน สีขาว ดำ ลายจุด', 1000, 10, NULL, 1), 
('S009', 'ชุดกระโปรทำงาน ทั่วไป', 450, 2, NULL, 1), 
('S010', 'ชุดกระโปรทำงาน กับเสื้อแขนยาว', 450, 2, NULL, 1), 
('S011', 'ชุดกระโปรงทำงาน สไตล์เกาหลี', 450, 2, NULL, 1), 
('S012', 'ชุดกางเกง ลด 50%', 100, 99, NULL, 1), 
('S005', 'ชุดกระโปรง ใส่เข้าร่วมประชุม', 3500, 0, NULL, 1), 
('S013', 'ชุดกางเกง ลด 50%', 100, 0, NULL, 1), 
('T001', 'รองเท้าส้นสูงสีชมพู', 100, 3, NULL, 2), 
('T002', 'รองเท้าสีครีมมีสายรัด', 100, 0, NULL, 2), 
('T003', 'รองเท้าส้นตึกสีแดง', 100, 20, NULL, 2), 
('T004', 'รองเท้าโบว์แดง', 100, 20, NULL, 2), 
('T005', 'รองเท้ายางสีแดงมีสายรัด', 100, 20, NULL, 2), 
('T006', 'รองเท้าส้นหนาพร้อมเชือกรัด', 100, 0, NULL, 2), 
('T007', 'รองเท้า', 100, 0, NULL, 2), 
('U001', 'แหวนเพชร', 10000, 3, NULL, 4), 
('U002', 'สร้อยคอฝังเพชร', 25000, 1, NULL, 4), 
('U003', 'แหวนโทแพช', 5000, 30, NULL, 4), 
('U004', 'แหวนอเมทิสต์', 3500, 40, NULL, 4), 
('U005', 'สร้อยคอทองคำสลักลาย', 19500, 1, '', 4), 
('V001', 'วิกก์ยา ขาตั้งถาด', 900, 5, NULL, 3), 
('V002', 'วาดโฮลมา โต๊ะเตรียมอาหาร', 16000, 5, NULL, 3), 
('V003', 'เบียแควม รถเข็นอาหาร', 2500, 5, NULL, 3), 
('V004', 'เฟอร์เฮยย่า รถเข็นอาหาร ราคาลดลงกว่าเดิม', 4000, 5, NULL, 3), 
('V005', 'เบียแควม เก้าอี้ 2 ชั้น สีดำ', 900, 5, NULL, 3), 
('V006', 'เบียแควม เก้าอี้ 2 ชั่น สีขาว', 900, 5, NULL, 3)
;

INSERT INTO order_main
(NO, TOTAL_PRICE, USER_ID, ORDER_DATE, SHIP, SHIP_DATE, TRACKING_NO, CANCEL, NOTE) VALUES
(1850027038, 2000, 1, '20180801', 'N', NULL, NULL, 'N', NULL)
;

INSERT INTO order_detail
(PRODUCT_ID, TOTAL_NUM, TOTAL_PRICE, ORDER_ID) VALUES
(6, 1, '1000', 1), 
(13, 1, '100', 1), 
(30, 1, '900', 1)
;