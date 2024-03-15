-- MySQL Script generated by MySQL Workbench
-- Wed Mar  6 12:03:02 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema intelafdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `intelafdb` ;

-- -----------------------------------------------------
-- Schema intelafdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `intelafdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `intelafdb` ;

-- -----------------------------------------------------
-- Table `intelafdb`.`store`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`store` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`store` (
  `id_store` VARCHAR(10) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `phone1` VARCHAR(8) NOT NULL,
  `phone2` VARCHAR(8) NULL,
  `email` VARCHAR(80) NULL,
  `store_hours` VARCHAR(80) NULL,
  PRIMARY KEY (`id_store`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`shipping_time`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`shipping_time` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`shipping_time` (
  `id_store1` VARCHAR(10) NOT NULL,
  `id_store2` VARCHAR(10) NOT NULL,
  `time` INT NOT NULL,
  PRIMARY KEY (`id_store1`, `id_store2`),
  INDEX `fk_shipping_time_store1_idx` (`id_store2` ASC) VISIBLE,
  CONSTRAINT `fk_shipping_time_store`
    FOREIGN KEY (`id_store1`)
    REFERENCES `intelafdb`.`store` (`id_store`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shipping_time_store1`
    FOREIGN KEY (`id_store2`)
    REFERENCES `intelafdb`.`store` (`id_store`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`product` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`product` (
  `id_product` VARCHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `manufacturer` VARCHAR(100) NOT NULL,
  `price` DECIMAL NOT NULL,
  `description` VARCHAR(500) NULL,
  `guaranty_months` INT NULL,
  PRIMARY KEY (`id_product`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`user` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `nit` VARCHAR(9) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(8) NOT NULL,
  `dpi` VARCHAR(13) NULL,
  `email` VARCHAR(100) NULL,
  `address` VARCHAR(100) NULL,
  `password` VARCHAR(100) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`employee` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`employee` (
  `id_user` INT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_user`),
  CONSTRAINT `fk_employee_User1`
    FOREIGN KEY (`id_user`)
    REFERENCES `intelafdb`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`customer` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`customer` (
  `user_id_user` INT NOT NULL,
  `credit` DECIMAL (10,2) NULL,
  PRIMARY KEY (`user_id_user`),
  CONSTRAINT `fk_customer_user1`
    FOREIGN KEY (`user_id_user`)
    REFERENCES `intelafdb`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`store_has_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`store_has_product` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`store_has_product` (
  `store_id_store` VARCHAR(10) NOT NULL,
  `product_id_product` VARCHAR(10) NOT NULL,
  `stock` INT NULL,
  PRIMARY KEY (`store_id_store`, `product_id_product`),
  INDEX `fk_store_has_product_product1_idx` (`product_id_product` ASC) VISIBLE,
  INDEX `fk_store_has_product_store1_idx` (`store_id_store` ASC) VISIBLE,
  CONSTRAINT `fk_store_has_product_store1`
    FOREIGN KEY (`store_id_store`)
    REFERENCES `intelafdb`.`store` (`id_store`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_has_product_product1`
    FOREIGN KEY (`product_id_product`)
    REFERENCES `intelafdb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`sale`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`sale` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`sale` (
  `id_sale` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  `total` DOUBLE NULL,
  `id_customer` INT NOT NULL,
  PRIMARY KEY (`id_sale`, `id_customer`),
  INDEX `fk_sale_customer1_idx` (`id_customer` ASC) VISIBLE,
  CONSTRAINT `fk_sale_customer1`
    FOREIGN KEY (`id_customer`)
    REFERENCES `intelafdb`.`customer` (`user_id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`payment_sale`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`payment_sale` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`payment_sale` (
  `id_payment_sale` INT NOT NULL AUTO_INCREMENT,
  `sale_id_sale` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `amount` DECIMAL NOT NULL,
  PRIMARY KEY (`id_payment_sale`),
  CONSTRAINT `fk_payment_sale1`
    FOREIGN KEY (`sale_id_sale`)
    REFERENCES `intelafdb`.`sale` (`id_sale`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`sale_has_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`sale_has_product` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`sale_has_product` (
  `sale_id_sale` INT NOT NULL,
  `product_id_product` VARCHAR(10) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`sale_id_sale`, `product_id_product`),
  INDEX `fk_sale_has_product_product1_idx` (`product_id_product` ASC) VISIBLE,
  INDEX `fk_sale_has_product_sale1_idx` (`sale_id_sale` ASC) VISIBLE,
  CONSTRAINT `fk_sale_has_product_sale1`
    FOREIGN KEY (`sale_id_sale`)
    REFERENCES `intelafdb`.`sale` (`id_sale`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sale_has_product_product1`
    FOREIGN KEY (`product_id_product`)
    REFERENCES `intelafdb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`store_has_employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`store_has_employee` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`store_has_employee` (
  `store_id_store` VARCHAR(10) NOT NULL,
  `employee_id_user` INT NOT NULL,
  PRIMARY KEY (`store_id_store`, `employee_id_user`),
  INDEX `fk_store_has_employee_employee1_idx` (`employee_id_user` ASC) VISIBLE,
  INDEX `fk_store_has_employee_store1_idx` (`store_id_store` ASC) VISIBLE,
  CONSTRAINT `fk_store_has_employee_store1`
    FOREIGN KEY (`store_id_store`)
    REFERENCES `intelafdb`.`store` (`id_store`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_has_employee_employee1`
    FOREIGN KEY (`employee_id_user`)
    REFERENCES `intelafdb`.`employee` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`order` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`order` (
  `id_order` INT NOT NULL AUTO_INCREMENT,
  `id_store_shipping` VARCHAR(10) NOT NULL,
  `id_store_receive` VARCHAR(10) NOT NULL,
  `date_departure` DATETIME NOT NULL,
  `date_entry` DATETIME NULL,
  `total` DECIMAL NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id_order`),
  INDEX `fk_order_store1_idx` (`id_store_shipping` ASC) VISIBLE,
  INDEX `fk_order_store2_idx` (`id_store_receive` ASC) VISIBLE,
  CONSTRAINT `fk_order_store1`
    FOREIGN KEY (`id_store_shipping`)
    REFERENCES `intelafdb`.`store` (`id_store`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_store2`
    FOREIGN KEY (`id_store_receive`)
    REFERENCES `intelafdb`.`store` (`id_store`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`order_has_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`order_has_product` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`order_has_product` (
  `order_id_order` INT NOT NULL,
  `product_id_product` VARCHAR(10) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`order_id_order`, `product_id_product`),
  INDEX `fk_order_has_product_product1_idx` (`product_id_product` ASC) VISIBLE,
  INDEX `fk_order_has_product_order1_idx` (`order_id_order` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_product_order1`
    FOREIGN KEY (`order_id_order`)
    REFERENCES `intelafdb`.`order` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_product_product1`
    FOREIGN KEY (`product_id_product`)
    REFERENCES `intelafdb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`payment_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`payment_order` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`payment_order` (
  `id_payment_order` INT NOT NULL AUTO_INCREMENT,
  `order_id_order` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `amount` DECIMAL NOT NULL,
  PRIMARY KEY (`id_payment_order`),
  CONSTRAINT `fk_payment_order_order1`
    FOREIGN KEY (`order_id_order`)
    REFERENCES `intelafdb`.`order` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intelafdb`.`product_images`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `intelafdb`.`product_images` ;

CREATE TABLE IF NOT EXISTS `intelafdb`.`product_images` (
  `id_product_images` INT NOT NULL AUTO_INCREMENT,
  `path` VARCHAR(200) NULL,
  `product_id_product` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_product_images`, `product_id_product`),
  INDEX `fk_product_images_product1_idx` (`product_id_product` ASC) VISIBLE,
  CONSTRAINT `fk_product_images_product1`
    FOREIGN KEY (`product_id_product`)
    REFERENCES `intelafdb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
