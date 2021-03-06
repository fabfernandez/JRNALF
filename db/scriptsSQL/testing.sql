-- MySQL Script generated by MySQL Workbench
-- Mon May 10 15:07:35 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema automotriz_delux
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `automotriz_delux` ;

-- -----------------------------------------------------
-- Schema automotriz_delux
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `automotriz_delux` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `automotriz_delux` ;

-- -----------------------------------------------------
-- Table `automotriz_delux`.`dealers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `automotriz_delux`.`dealers` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`dealers` (
  `id_dealer` INT NOT NULL,
  `name` VARCHAR(25) NULL DEFAULT NULL,
  `address` VARCHAR(40) NULL DEFAULT NULL,
  `phone` INT NULL DEFAULT NULL,
  `country` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_dealer`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `automotriz_delux`.`subsidiaries`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `automotriz_delux`.`subsidiaries` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`subsidiaries` (
  `id` INT NOT NULL,
  `name` VARCHAR(25) NOT NULL,
  `address` VARCHAR(40) NOT NULL,
  `phone` INT NULL DEFAULT NULL,
  `country` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `automotriz_delux`.`dealer_orders`
-- -----------------------------------------------------
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
  INDEX `dealer_id` (`dealer_id` ASC) VISIBLE,
  INDEX `subsidiary_id` (`subsidiary_id` ASC) VISIBLE,
  CONSTRAINT `dealer_id`
    FOREIGN KEY (`dealer_id`)
    REFERENCES `automotriz_delux`.`dealers` (`id_dealer`),
  CONSTRAINT `subsidiary_id`
    FOREIGN KEY (`subsidiary_id`)
    REFERENCES `automotriz_delux`.`subsidiaries` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `automotriz_delux`.`parts`
-- -----------------------------------------------------
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
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `automotriz_delux`.`order_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `automotriz_delux`.`order_details` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`order_details` (
  `id_order_detail` INT NOT NULL,
  `order_id` INT NOT NULL,
  `part_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `account_type` VARCHAR(1) NULL DEFAULT NULL,
  `reason` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id_order_detail`),
  INDEX `order_id` (`order_id` ASC) VISIBLE,
  INDEX `part_id` (`part_id` ASC) VISIBLE,
  CONSTRAINT `order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `automotriz_delux`.`dealer_orders` (`order_number`),
  CONSTRAINT `part_id`
    FOREIGN KEY (`part_id`)
    REFERENCES `automotriz_delux`.`parts` (`part_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `automotriz_delux`.`subsidiaries_orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `automotriz_delux`.`subsidiaries_orders` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`subsidiaries_orders` (
  `order_number` INT NOT NULL,
  `subsidiary` INT NOT NULL,
  `order_date` DATETIME NOT NULL,
  `order_status` VARCHAR(1) NOT NULL,
  `delivery_date` DATETIME NULL DEFAULT NULL,
  `days_delay` INT NULL DEFAULT NULL,
  PRIMARY KEY (`order_number`),
  INDEX `subsidiary_id_idx` (`subsidiary` ASC) VISIBLE,
  CONSTRAINT `subsidiary_order`
    FOREIGN KEY (`subsidiary`)
    REFERENCES `automotriz_delux`.`subsidiaries` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `automotriz_delux`.`subsidiaries_stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `automotriz_delux`.`subsidiaries_stock` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`subsidiaries_stock` (
  `id_subsidiary` INT NOT NULL,
  `part_code` INT NULL DEFAULT NULL,
  `quantity` INT NULL DEFAULT NULL,
  INDEX `part` (`part_code` ASC) VISIBLE,
  INDEX `subsidiary` (`id_subsidiary` ASC) VISIBLE,
  CONSTRAINT `part`
    FOREIGN KEY (`part_code`)
    REFERENCES `automotriz_delux`.`parts` (`part_code`),
  CONSTRAINT `subsidiary`
    FOREIGN KEY (`id_subsidiary`)
    REFERENCES `automotriz_delux`.`subsidiaries` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `automotriz_delux`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `automotriz_delux`.`users` ;

CREATE TABLE IF NOT EXISTS `automotriz_delux`.`users` (
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `id_subsidiary` INT NOT NULL,
  PRIMARY KEY (`username`),
  INDEX `id_subsidiary` (`id_subsidiary` ASC) VISIBLE,
  CONSTRAINT `id_subsidiary`
    FOREIGN KEY (`id_subsidiary`)
    REFERENCES `automotriz_delux`.`subsidiaries` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
