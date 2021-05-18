DROP SCHEMA IF EXISTS `automotriz_delux` ;

CREATE SCHEMA IF NOT EXISTS `automotriz_delux` DEFAULT CHARACTER SET utf8mb4 ;
USE `automotriz_delux` ;

-- Table 'dealers'
DROP TABLE IF EXISTS `automotriz_delux`.`dealers` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`dealers` (
  `id_dealer` INT NOT NULL,
  `name` VARCHAR(25) NULL DEFAULT NULL,
  `address` VARCHAR(40) NULL DEFAULT NULL,
  `phone` INT NULL DEFAULT NULL,
  `country` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_dealer`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table 'subsidiaries'
DROP TABLE IF EXISTS `automotriz_delux`.`subsidiaries` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`subsidiaries` (
  `id` INT NOT NULL,
  `name` VARCHAR(25) NULL DEFAULT NULL,
  `address` VARCHAR(40) NULL DEFAULT NULL,
  `phone` INT NULL DEFAULT NULL,
  `country` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table 'dealer_orders'
DROP TABLE IF EXISTS `automotriz_delux`.`dealer_orders` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`dealer_orders` (
  `order_number` INT NOT NULL,
  `order_date` DATETIME NULL DEFAULT NULL,
  `delivery_date` DATETIME NULL DEFAULT NULL,
  `days_delay` INT NULL DEFAULT NULL,
  `delivery_status` VARCHAR(1) NULL DEFAULT NULL,
  `dealer_id` INT NULL DEFAULT NULL,
  `subsidiary_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`order_number`),
  CONSTRAINT `dealer_id`
    FOREIGN KEY (`dealer_id`)
    REFERENCES `automotriz_delux`.`dealers` (`id_dealer`),
  CONSTRAINT `subsidiary_id`
    FOREIGN KEY (`subsidiary_id`)
    REFERENCES `automotriz_delux`.`subsidiaries` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table 'parts'
DROP TABLE IF EXISTS `automotriz_delux`.`parts` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`parts` (
  `part_code` INT NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `widthDimension` INT NULL DEFAULT NULL,
  `tallDimension` INT NULL DEFAULT NULL,
  `longDimension` INT NULL DEFAULT NULL,
  `netWeight` INT NULL DEFAULT NULL,
  PRIMARY KEY (`part_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table 'order_details'
DROP TABLE IF EXISTS `automotriz_delux`.`order_details` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`order_details` (
  `id_order_detail` INT NOT NULL,
  `order_id` INT NOT NULL,
  `part_id` INT NOT NULL,
  `quantity` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_order_detail`),
  CONSTRAINT `order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `automotriz_delux`.`dealer_orders` (`order_number`),
  CONSTRAINT `part_id`
    FOREIGN KEY (`part_id`)
    REFERENCES `automotriz_delux`.`parts` (`part_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table 'parts_modifications'
DROP TABLE IF EXISTS `automotriz_delux`.`parts_modifications` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`parts_modifications` (
  `id_part` INT NOT NULL,
  `last_modification` DATETIME NULL DEFAULT NULL,
  `normal_price` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_part`),
  CONSTRAINT `part`
    FOREIGN KEY (`id_part`)
    REFERENCES `automotriz_delux`.`parts` (`part_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table 'subsidiaries_stock'
DROP TABLE IF EXISTS `automotriz_delux`.`subsidiaries_stock` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`subsidiaries_stock` (
  `id_part` INT NOT NULL,
  `id_subsidiary` INT NOT NULL,
  `quantity` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_part`, `id_subsidiary`),
  CONSTRAINT `id_part`
    FOREIGN KEY (`id_part`)
    REFERENCES `automotriz_delux`.`parts` (`part_code`),
  CONSTRAINT `subsidiary`
    FOREIGN KEY (`id_subsidiary`)
    REFERENCES `automotriz_delux`.`subsidiaries` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Table 'users'
DROP TABLE IF EXISTS `automotriz_delux`.`users` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`users` (
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `id_subsidiary` INT NOT NULL,
  `role` VARCHAR(15) NOT NULL,
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
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (1, "Llanta", 100, 100, 100, 100);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (2, "Puerta trasera derecha", 30, 40, 30, 40);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (3, "Puerta trasera izquierda", 30, 40, 30, 40);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (4, "Puerta delantera derecha", 30, 40, 30, 40);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (5, "Puerta delantera izquierda", 30, 40, 30, 40);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (6, "Puerta del maletero", 80, 30, 80, 30);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (7, "Foco LED delantero", 15, 15, 15, 15);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (8, "Espejo retrovisor", 100, 55, 100, 100);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (9, "Espejo derecho", 33, 100, 100, 100);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (10, "Espejo izquierdo", 40, 100, 99, 33);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (11, "Caja de cambios", 40, 33, 55, 100);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (12, "Asiento delantero", 55, 40, 100, 92);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (13, "Asiento trasero", 92, 100, 33, 100);
insert into parts (part_code, description, widthDimension, tallDimension, longDimension, netWeight) values (14, "Apoyacabezas", 92, 33, 100, 92);

-- create dealer_orders
insert into dealer_orders (order_number, order_date, delivery_date, days_delay, delivery_status, dealer_id, subsidiary_id) values (1, '2021-04-30 20:23:06', '2021-04-01 14:39:09', 93, 'P', 7, 5);
insert into dealer_orders (order_number, order_date, delivery_date, days_delay, delivery_status, dealer_id, subsidiary_id) values (2, '2020-09-15 21:49:21', '2021-01-08 02:10:04', 2, 'C', 2, 5);
insert into dealer_orders (order_number, order_date, delivery_date, days_delay, delivery_status, dealer_id, subsidiary_id) values (3, '2021-04-03 16:00:53', '2021-05-02 01:41:36', 91, 'P', 3, 1);
insert into dealer_orders (order_number, order_date, delivery_date, days_delay, delivery_status, dealer_id, subsidiary_id) values (4, '2020-07-06 20:23:40', '2021-01-23 23:56:40', 34, 'P', 6, 4);
insert into dealer_orders (order_number, order_date, delivery_date, days_delay, delivery_status, dealer_id, subsidiary_id) values (5, '2021-01-04 05:38:39', '2020-12-18 10:45:18', 56, 'P', 10, 4);
insert into dealer_orders (order_number, order_date, delivery_date, days_delay, delivery_status, dealer_id, subsidiary_id) values (6, '2020-06-02 20:19:01', '2020-09-04 17:15:25', 66, 'P', 7, 5);
insert into dealer_orders (order_number, order_date, delivery_date, days_delay, delivery_status, dealer_id, subsidiary_id) values (7, '2021-03-15 17:53:03', '2020-09-20 04:27:58', 71, 'P', 1, 4);
insert into dealer_orders (order_number, order_date, delivery_date, days_delay, delivery_status, dealer_id, subsidiary_id) values (8, '2021-03-24 21:14:13', '2020-05-11 20:31:24', 75, 'P', 8, 3);
insert into dealer_orders (order_number, order_date, delivery_date, days_delay, delivery_status, dealer_id, subsidiary_id) values (9, '2020-11-30 14:48:08', '2020-07-30 12:34:15', 28, 'F', 4, 3);
insert into dealer_orders (order_number, order_date, delivery_date, days_delay, delivery_status, dealer_id, subsidiary_id) values (10, '2020-12-17 02:32:44', '2020-05-15 01:22:51', 3, 'C', 3, 1);

-- create subsidiaries_stock
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (1, 1, 2);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (1, 2, 2);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (1, 3, 2);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (1, 4, 0);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (1, 5, 4);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (2, 1, 5);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (2, 2, 6);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (2, 3, 7);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (2, 4, 9);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (2, 5, 3);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (3, 1, 4);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (3, 2, 2);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (3, 3, 3);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (3, 4, 0);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (3, 5, 4);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (4, 1, 3);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (4, 2, 3);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (4, 3, 2);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (4, 4, 5);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (4, 5, 6);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (5, 1, 7);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (5, 2, 8);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (5, 3, 5);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (5, 4, 4);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (5, 5, 4);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (6, 1, 6);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (6, 2, 7);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (6, 3, 11);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (6, 4, 23);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (6, 5, 5);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (7, 1, 5);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (7, 2, 6);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (7, 3, 7);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (7, 4, 6);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (7, 5, 3);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (8, 1, 5);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (8, 2, 6);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (8, 3, 8);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (8, 4, 7);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (8, 5, 33);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (9, 1, 0);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (9, 2, 45);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (9, 3, 0);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (9, 4, 45);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (9, 5, 45);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (10, 1, 92);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (10, 2, 33);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (10, 3, 6);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (10, 4, 4);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (10, 5, 2);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (11, 1, 4);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (11, 2, 45);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (11, 3, 3);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (11, 4, 45);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (11, 5, 2);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (12, 1, 3);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (12, 2, 5);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (12, 3, 7);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (12, 4, 3);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (12, 5, 2);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (13, 1, 3);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (13, 2, 6);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (13, 3, 4);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (13, 4, 3);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (13, 5, 7);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (14, 1, 6);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (14, 2, 4);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (14, 3, 2);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (14, 4, 6);
insert into subsidiaries_stock (id_part, id_subsidiary, quantity) values (14, 5, 6);

-- create parts_modifications

insert into parts_modifications (id_part, last_modification, normal_price) values (1, '2021-03-02 19:21:01', 762);
insert into parts_modifications (id_part, last_modification, normal_price) values (1, '2021-04-02 19:21:01', 862);
insert into parts_modifications (id_part, last_modification, normal_price) values (1, '2021-05-02 19:21:01', 962);
insert into parts_modifications (id_part, last_modification, normal_price) values (2, '2021-01-29 11:11:04', 2305);
insert into parts_modifications (id_part, last_modification, normal_price) values (3, '2020-10-31 20:28:28', 837);
insert into parts_modifications (id_part, last_modification, normal_price) values (3, '2020-11-31 20:28:28', 650);
insert into parts_modifications (id_part, last_modification, normal_price) values (4, '2020-06-23 21:15:44', 2243);
insert into parts_modifications (id_part, last_modification, normal_price) values (5, '2020-11-08 04:04:17', 1236);
insert into parts_modifications (id_part, last_modification, normal_price) values (6, '2020-06-06 13:39:38', 1119);
insert into parts_modifications (id_part, last_modification, normal_price) values (6, '2020-07-06 13:39:38', 1200);
insert into parts_modifications (id_part, last_modification, normal_price) values (7, '2021-02-27 16:27:51', 358);
insert into parts_modifications (id_part, last_modification, normal_price) values (8, '2021-02-13 07:50:58', 2223);
insert into parts_modifications (id_part, last_modification, normal_price) values (8, '2021-02-14 07:50:58', 2200);
insert into parts_modifications (id_part, last_modification, normal_price) values (8, '2021-02-15 07:50:58', 2500);
insert into parts_modifications (id_part, last_modification, normal_price) values (9, '2021-03-18 15:32:56', 1722);
insert into parts_modifications (id_part, last_modification, normal_price) values (10, '2021-03-17 04:11:05', 2151);
insert into parts_modifications (id_part, last_modification, normal_price) values (12, '2020-08-31 16:53:30', 1431);
insert into parts_modifications (id_part, last_modification, normal_price) values (14, '2020-07-22 19:59:11', 1998);

-- create order_details
insert into order_details (id_order_detail, order_id, part_id, quantity) values (1, 1, 1, 2);
insert into order_details (id_order_detail, order_id, part_id, quantity) values (2, 2, 2, 2);
insert into order_details (id_order_detail, order_id, part_id, quantity) values (3, 2, 3, 1);
insert into order_details (id_order_detail, order_id, part_id, quantity) values (4, 3, 4, 4);
insert into order_details (id_order_detail, order_id, part_id, quantity) values (5, 3, 5, 2);
insert into order_details (id_order_detail, order_id, part_id, quantity) values (6, 5, 5, 1);
insert into order_details (id_order_detail, order_id, part_id, quantity) values (7, 6, 6, 3);
insert into order_details (id_order_detail, order_id, part_id, quantity) values (8, 7, 10, 4);
insert into order_details (id_order_detail, order_id, part_id, quantity) values (9, 8, 10, 5);
insert into order_details (id_order_detail, order_id, part_id, quantity) values (10, 8, 11, 2);
insert into order_details (id_order_detail, order_id, part_id, quantity) values (11, 9, 14, 3);
insert into order_details (id_order_detail, order_id, part_id, quantity) values (12, 10, 14, 4);

-- create users
insert into users (username, password, id_subsidiary, role) values ('gembleton0', 'IH4YH7kAk', 1, 'admin_BR');
insert into users (username, password, id_subsidiary, role) values ('jspurgeon1', '0xpbqqetW1xC', 2, 'admin_AR');
insert into users (username, password, id_subsidiary, role) values ('rtrouncer2', 'RJFdKwtOE', 3, 'admin_UY');
insert into users (username, password, id_subsidiary, role) values ('amoxon3', 'A98vXM2llsb', 4, 'admin_CO');
insert into users (username, password, id_subsidiary, role) values ('gsyversen4', 'EXMUyGRqefX', 5, 'admin_VE');