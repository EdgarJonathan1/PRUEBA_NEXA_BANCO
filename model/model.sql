-- MySQL Workbench Forward Engineering
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client` (
  `idclient` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `country_birthday` VARCHAR(45) NULL,
  `middle_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `second_last_name` VARCHAR(45) NULL,
  `dpi` VARCHAR(45) NULL,
  `birthday` TIMESTAMP NULL,
  `gender` TINYINT NULL,
  PRIMARY KEY (`idclient`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`status` (
  `idstatus` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`idstatus`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`type_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`type_product` (
  `idtype_account` INT NOT NULL,
  `name` ENUM('AHORRO', 'MONETARIO') NULL,
  PRIMARY KEY (`idtype_account`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `idproduct` INT NOT NULL,
  `type_account_idtype_account` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `interest_calculation` DECIMAL(15,0) NULL,
  `interest_rate` DECIMAL(15,0) NULL,
  PRIMARY KEY (`idproduct`),
  INDEX `fk_product_type_account1_idx` (`type_account_idtype_account` ASC) VISIBLE,
  CONSTRAINT `fk_product_type_account1`
    FOREIGN KEY (`type_account_idtype_account`)
    REFERENCES `mydb`.`type_product` (`idtype_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`account` (
  `idaccount` INT NOT NULL,
  `status_idstatus` INT NOT NULL,
  `product_idproduct` INT NOT NULL,
  `client_idclient` INT NOT NULL,
  `balance` DECIMAL(15,2) NULL,
  PRIMARY KEY (`idaccount`),
  INDEX `fk_account_status1_idx` (`status_idstatus` ASC) VISIBLE,
  INDEX `fk_account_product1_idx` (`product_idproduct` ASC) VISIBLE,
  INDEX `fk_account_client1_idx` (`client_idclient` ASC) VISIBLE,
  CONSTRAINT `fk_account_status1`
    FOREIGN KEY (`status_idstatus`)
    REFERENCES `mydb`.`status` (`idstatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_product1`
    FOREIGN KEY (`product_idproduct`)
    REFERENCES `mydb`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_client1`
    FOREIGN KEY (`client_idclient`)
    REFERENCES `mydb`.`client` (`idclient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`operation_code`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`operation_code` (
  `idoperation_code` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`idoperation_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transaction` (
  `idtransaction` INT NOT NULL,
  `operation_code_idoperation_code` INT NOT NULL,
  `account_idaccount` INT NOT NULL,
  `account_product_idproduct` INT NOT NULL,
  `amount` DECIMAL(15,2) NULL,
  `transaction_date` DATETIME NULL,
  PRIMARY KEY (`idtransaction`),
  INDEX `fk_transaction_operation_code1_idx` (`operation_code_idoperation_code` ASC) VISIBLE,
  INDEX `fk_transaction_account1_idx` (`account_idaccount` ASC, `account_product_idproduct` ASC) VISIBLE,
  CONSTRAINT `fk_transaction_operation_code1`
    FOREIGN KEY (`operation_code_idoperation_code`)
    REFERENCES `mydb`.`operation_code` (`idoperation_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_account1`
    FOREIGN KEY (`account_idaccount`)
    REFERENCES `mydb`.`account` (`idaccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
