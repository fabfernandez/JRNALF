DROP SCHEMA IF EXISTS `automotriz_delux` ;
CREATE SCHEMA IF NOT EXISTS `automotriz_delux` DEFAULT CHARACTER SET utf8mb4 ;
USE `automotriz_delux` ;

-- Table `automotriz_delux`.`dealers`
DROP TABLE IF EXISTS `automotriz_delux`.`dealers` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`dealers` (
  `id_dealer` INT NOT NULL,
  `name` VARCHAR(25) NULL DEFAULT NULL,
  `address` VARCHAR(40) NULL DEFAULT NULL,
  `phone` INT NULL DEFAULT NULL,
  `country` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_dealer`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table `automotriz_delux`.`subsidiaries`
DROP TABLE IF EXISTS `automotriz_delux`.`subsidiaries` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`subsidiaries` (
  `id` INT NOT NULL,
  `name` VARCHAR(25) NOT NULL,
  `address` VARCHAR(40) NOT NULL,
  `phone` INT NULL DEFAULT NULL,
  `country` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table `automotriz_delux`.`dealer_orders`
DROP TABLE IF EXISTS `automotriz_delux`.`dealer_orders` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`dealer_orders` (
  `order_number` INT NOT NULL,
  `dealer_id` INT NOT NULL,
  `subsidiary_id` INT NOT NULL,
  `order_date` DATETIME NOT NULL,
  `order_status` VARCHAR(1) NOT NULL,
  `delivery_date` DATETIME NULL DEFAULT NULL,
  `days_delay` INT NULL DEFAULT NULL,
  PRIMARY KEY (`order_number`),
  CONSTRAINT `dealer_id`
    FOREIGN KEY (`dealer_id`)
    REFERENCES `automotriz_delux`.`dealers` (`id_dealer`),
  CONSTRAINT `subsidiary_id`
    FOREIGN KEY (`subsidiary_id`)
    REFERENCES `automotriz_delux`.`subsidiaries` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table `automotriz_delux`.`parts`
DROP TABLE IF EXISTS `automotriz_delux`.`parts` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`parts` (
  `part_code` INT NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `maker` VARCHAR(20) NULL DEFAULT NULL,
  `widthDimension` INT NULL DEFAULT NULL,
  `tallDimension` INT NULL DEFAULT NULL,
  `longDimension` INT NULL DEFAULT NULL,
  `netWeight` INT NULL DEFAULT NULL,
  `normal_price` INT NULL DEFAULT NULL,
  `urgent_price` INT NULL DEFAULT NULL,
  `last_modification` DATETIME NULL DEFAULT NULL,
  `last_price_modification` DATETIME NULL DEFAULT NULL,
  `discount_type` VARCHAR(5) NULL DEFAULT NULL,
  PRIMARY KEY (`part_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table `automotriz_delux`.`order_details`
DROP TABLE IF EXISTS `automotriz_delux`.`order_details` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`order_details` (
  `id_order_detail` INT NOT NULL,
  `order_id` INT NOT NULL,
  `part_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `account_type` VARCHAR(1) NULL DEFAULT NULL,
  `reason` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id_order_detail`),
  CONSTRAINT `order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `automotriz_delux`.`dealer_orders` (`order_number`),
  CONSTRAINT `part_id`
    FOREIGN KEY (`part_id`)
    REFERENCES `automotriz_delux`.`parts` (`part_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table `automotriz_delux`.`subsidiaries_stock`
DROP TABLE IF EXISTS `automotriz_delux`.`subsidiaries_stock` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`subsidiaries_stock` (
  `id_subsidiary` INT NOT NULL,
  `part_code` INT NULL DEFAULT NULL,
  `quantity` INT NULL DEFAULT NULL,
  CONSTRAINT `part`
    FOREIGN KEY (`part_code`)
    REFERENCES `automotriz_delux`.`parts` (`part_code`),
  CONSTRAINT `subsidiary`
    FOREIGN KEY (`id_subsidiary`)
    REFERENCES `automotriz_delux`.`subsidiaries` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table `automotriz_delux`.`users`
DROP TABLE IF EXISTS `automotriz_delux`.`users` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`users` (
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `id_subsidiary` INT NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `id_subsidiary`
    FOREIGN KEY (`id_subsidiary`)
    REFERENCES `automotriz_delux`.`subsidiaries` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- inserts
use automotriz_delux;

-- create subsidiary
insert into subsidiaries (id, name, address, phone, country) values (1, 'Casa Central', 'Sao Paulo 3304', 12344321, 'Brasil');
insert into subsidiaries (id, name, address, phone, country) values (2, 'Casa Buenos Aires', 'Avenida Callao 2000', 23455432, 'Argentina');
insert into subsidiaries (id, name, address, phone, country) values (3, 'Casa Montevideo', '18 de Julio 1023', 34566543, 'Uruguay');
insert into subsidiaries (id, name, address, phone, country) values (4, 'Casa Bogota', 'Medellin 332', 45677654, 'Colombia');
insert into subsidiaries (id, name, address, phone, country) values (5, 'Casa Caracas','Avenida Boyaca 992', 56788765, 'Venezuela');

-- create dealers
insert into dealers (id_dealer, name, address, phone, country) values (1, 'Dealer Brasil One', 'Paulo 3304', 12344321, 'Brasil');
insert into dealers (id_dealer, name, address, phone, country) values (2, 'Dealer Brasil Two', 'Paulo 3304', 12344321, 'Brasil');
insert into dealers (id_dealer, name, address, phone, country) values (3, 'Dealer Argentina One', 'Callao 2300', 23455432, 'Argentina');
insert into dealers (id_dealer, name, address, phone, country) values (4, 'Dealer Argentina Two', 'Callao 2500', 23455432, 'Argentina');
insert into dealers (id_dealer, name, address, phone, country) values (5, 'Dealer Uruguay One', 'Julio 1200', 34566543, 'Uruguay');
insert into dealers (id_dealer, name, address, phone, country) values (6, 'Dealer Uruguay Two', 'Julio 1400', 34566543, 'Uruguay');
insert into dealers (id_dealer, name, address, phone, country) values (7, 'Dealer Colombia One', 'Mede 450', 45677654, 'Colombia');
insert into dealers (id_dealer, name, address, phone, country) values (8, 'Dealer Colombia Two', 'Mede 650', 45677654, 'Colombia');
insert into dealers (id_dealer, name, address, phone, country) values (9, 'Dealer Venezuela One','Boyaca 1300', 56788765, 'Venezuela');
insert into dealers (id_dealer, name, address, phone, country) values (10, 'Dealer Venezuela Two','Boyaca 1500', 56788765, 'Venezuela');

-- create parts
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (1, "Llanta", "Fiat", 100, 100, 100, 100, 762, 900, '2021-03-02 19:21:01', '2021-03-10 19:21:01', "AA");
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (2, "Puerta trasera derecha", "Fiat", 30, 40, 30, 40, 862, 900, '2021-03-02 19:21:01', '2021-04-02 19:21:01', "AA");
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (3, "Puerta trasera izquierda", "Fiat", 30, 40, 30, 40, 962, 1000, '2021-01-29 11:11:04', '2021-03-29 11:11:04', "AA");
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (4, "Puerta delantera derecha", "Fiat", 30, 40, 30, 40, 2305, 2400, '2020-10-31 20:28:28', NULL, "AA");
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (5, "Puerta delantera izquierda", "Fiat", 30, 40, 30, 40, 837, 900, '2020-06-23 21:15:44', NULL, 'BB');
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (6, "Puerta del maletero", "Fiat", 80, 30, 80, 30, 650, 700, '2020-11-08 04:04:17', '2021-02-13 07:50:58', 'BB');
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (7, "Foco LED delantero", "Fiat", 15, 15, 15, 15, 2243, 2300, '2020-06-06 13:39:38', NULL, 'BB');
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (8, "Espejo retrovisor", "Fiat", 100, 55, 100, 100, 1236, 1300, '2021-02-13 07:50:58', NULL, 'BB');
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (9, "Espejo derecho", "Toyota", 33, 100, 100, 100, 1119, 1200, '2021-02-13 07:50:58', NULL, 'CC');
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (10, "Espejo izquierdo", "Toyota", 40, 100, 99, 100, 1200, 1300, '2021-02-13 07:50:58', '2021-02-27 07:50:58', 'CC');
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (11, "Caja de cambios", "Toyota", 40, 33, 55, 100, 358, 400, '2021-02-13 07:50:58', '2021-04-13 07:50:58', 'CC');
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (12, "Asiento delantero", "Toyota", 55, 40, 100, 92, 2223, 2400, '2021-02-14 07:50:58', '2021-03-14 07:50:58', 'CC');
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (13, "Asiento trasero", "Toyota", 92, 100, 33, 100, 2200, 2300, '2020-07-22 19:59:11', '2020-08-22 19:59:11', 'CC');
insert into parts (part_code, description, maker, widthDimension, tallDimension, longDimension, netWeight, normal_price, urgent_price, last_modification, last_price_modification, discount_type) values (14, "Apoyacabezas", "Toyota", 92, 33, 100, 92, 2500, 2800, '2021-03-17 04:11:05', '2021-04-25 04:11:05', "DD");

-- create dealer_orders
insert into dealer_orders (order_number, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (1, 7, 5, '2021-04-30 20:23:06', 'P', '2021-05-15 20:23:06', 0);
insert into dealer_orders (order_number, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (2, 2, 5, '2020-09-15 21:49:21', 'D', '2020-09-20 21:49:21', 0);
insert into dealer_orders (order_number, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (3, 3, 1, '2021-04-03 16:00:53', 'F', '2021-05-03 16:00:53', 3);
insert into dealer_orders (order_number, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (4, 6, 4, '2020-07-06 20:23:40', 'C', '2020-08-06 20:23:40', 4);
insert into dealer_orders (order_number, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (5, 10, 4, '2021-01-04 05:38:39', 'F', '2021-03-04 05:38:39', 0);
insert into dealer_orders (order_number, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (6, 7, 5, '2020-06-02 20:19:01', 'P', '2020-07-02 20:19:01', 0);
insert into dealer_orders (order_number, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (7, 1, 4, '2021-03-15 17:53:03', 'C', '2021-05-15 17:53:03', 1);
insert into dealer_orders (order_number, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (8, 8, 3, '2021-03-24 21:14:13', 'D', '2021-03-30 21:14:13', 2);
insert into dealer_orders (order_number, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (9, 3, 3, '2020-11-30 14:48:08', 'F', '2020-12-15 14:48:08', 0);

-- create subsidiaries_stock
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 1, 2);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 1, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 1, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 1, 0);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 1, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 2, 5);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 2, 6);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 2, 7);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 2, 9);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 2, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 3, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 3, 2);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 3, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 3, 0);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 3, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 4, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 4, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 4, 2);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 4, 5);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 4, 6);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 5, 7);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 5, 8);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 5, 5);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 5, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 5, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 6, 6);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 6, 7);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 6, 9);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 6, 2);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 6, 5);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 7, 5);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 7, 6);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 7, 7);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 7, 6);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 7, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 8, 5);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 8, 6);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 8, 8);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 8, 7);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 8, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 9, 0);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 9, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 9, 0);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 9, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 9, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 10, 9);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 10, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 10, 6);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 10, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 10, 2);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 11, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 11, 5);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 11, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 11, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 11, 2);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 12, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 12, 5);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 12, 7);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 12, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 12, 2);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 13, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 13, 6);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 13, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 13, 3);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 13, 7);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (1, 14, 6);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (2, 14, 4);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (3, 14, 2);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (4, 14, 6);
insert into subsidiaries_stock (id_subsidiary, part_code, quantity) values (5, 14, 6);

-- create order_details
insert into order_details (id_order_detail, order_id, part_id, quantity, account_type, reason) values (1, 1, 1, 2, 'G', "sin motivo");
insert into order_details (id_order_detail, order_id, part_id, quantity, account_type, reason) values (2, 2, 2, 2, 'R', "sin motivo");
insert into order_details (id_order_detail, order_id, part_id, quantity, account_type, reason) values (3, 2, 3, 1, 'G', "sin motivo");
insert into order_details (id_order_detail, order_id, part_id, quantity, account_type, reason) values (4, 3, 4, 4, 'G', "sin motivo");
insert into order_details (id_order_detail, order_id, part_id, quantity, account_type, reason) values (5, 3, 5, 2, 'R', "sin motivo");
insert into order_details (id_order_detail, order_id, part_id, quantity, account_type, reason) values (6, 5, 5, 1, 'G', "sin motivo");
insert into order_details (id_order_detail, order_id, part_id, quantity, account_type, reason) values (7, 6, 6, 3, 'R', "sin motivo");
insert into order_details (id_order_detail, order_id, part_id, quantity, account_type, reason) values (8, 7, 10, 4, 'G', "sin motivo");
insert into order_details (id_order_detail, order_id, part_id, quantity, account_type, reason) values (9, 8, 10, 5, 'G', "sin motivo");
insert into order_details (id_order_detail, order_id, part_id, quantity, account_type, reason) values (10, 8, 11, 2, 'G', "sin motivo");
insert into order_details (id_order_detail, order_id, part_id, quantity, account_type, reason) values (11, 9, 10, 3, 'R', "sin motivo");

-- create users
insert into users (username, password, id_subsidiary) values ('gembleton0', 'IH4YH7kAk', 1);
insert into users (username, password, id_subsidiary) values ('jspurgeon1', '0xpbqqetW1xC', 2);
insert into users (username, password, id_subsidiary) values ('rtrouncer2', 'RJFdKwtOE', 3);
insert into users (username, password, id_subsidiary) values ('amoxon3', 'A98vXM2llsb', 4);
insert into users (username, password, id_subsidiary) values ('gsyversen4', 'EXMUyGRqefX', 5);