DROP SCHEMA IF EXISTS bank_database;
DROP TABLE IF EXISTS bank_database.client;
DROP TABLE IF EXISTS bank_database.status;
DROP TABLE IF EXISTS bank_database.type_product;
DROP TABLE IF EXISTS bank_database.product;
DROP TABLE IF EXISTS bank_database.account;
DROP TABLE IF EXISTS bank_database.operation_code;
DROP TABLE IF EXISTS bank_database.transaction;

CREATE SCHEMA IF NOT EXISTS bank_database DEFAULT CHARACTER SET utf8 ;
USE bank_database ;


CREATE TABLE IF NOT EXISTS bank_database.client (
  idclient INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NULL,
  country_birthday VARCHAR(45) NULL,
  middle_name VARCHAR(45) NULL,
  last_name VARCHAR(45) NULL,
  second_last_name VARCHAR(45) NULL,
  dpi VARCHAR(45) NULL,
  birthday TIMESTAMP NULL,
  gender VARCHAR(45) NULL,
  PRIMARY KEY (idclient)
);

CREATE TABLE IF NOT EXISTS bank_database.`status` (
  idstatus INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (idstatus)
);

CREATE TABLE IF NOT EXISTS bank_database.type_product (
  idtype_account INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (idtype_account)
);

CREATE TABLE IF NOT EXISTS bank_database.product (
  idproduct INT NOT NULL AUTO_INCREMENT,
  idtype_account INT NOT NULL,
  `name` VARCHAR(45) NULL,
  interest_calculation DECIMAL(15,0) NULL,
  interest_rate DECIMAL(15,0) NULL,
  PRIMARY KEY (idproduct),
  INDEX fk_product_type_account_idx (idtype_account ASC) VISIBLE,
  CONSTRAINT fk_product_type_account
    FOREIGN KEY (idtype_account)
    REFERENCES bank_database.type_product (idtype_account)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS bank_database.account (
  idaccount INT NOT NULL AUTO_INCREMENT,
  idstatus INT NOT NULL,
  idproduct INT NOT NULL,
  idclient INT NOT NULL,
  balance DECIMAL(15,2) NULL,
  PRIMARY KEY (idaccount),
  INDEX fk_account_status_idx (idstatus ASC) VISIBLE,
  INDEX fk_account_product_idx (idproduct ASC) VISIBLE,
  INDEX fk_account_client_idx (idclient ASC) VISIBLE,
  CONSTRAINT fk_account_status
    FOREIGN KEY (idstatus)
    REFERENCES bank_database.status (idstatus)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_product
    FOREIGN KEY (idproduct)
    REFERENCES bank_database.product (idproduct)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_client
    FOREIGN KEY (idclient)
    REFERENCES bank_database.client (idclient)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS bank_database.operation_code (
  idoperation_code INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (idoperation_code)
);

CREATE TABLE IF NOT EXISTS bank_database.transaction (
  idtransaction INT NOT NULL AUTO_INCREMENT,
  idoperation_code INT NOT NULL,
  idaccount INT NOT NULL,
  amount DECIMAL(15,2) NULL,
  transaction_date DATETIME NULL,
	description VARCHAR(255) NULL,
  PRIMARY KEY (idtransaction),
  INDEX fk_transaction_operation_code_idx (idoperation_code ASC) VISIBLE,
  INDEX fk_transaction_account_idx (idaccount ASC) VISIBLE,
  CONSTRAINT fk_transaction_operation_code
    FOREIGN KEY (idoperation_code)
    REFERENCES bank_database.operation_code (idoperation_code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_transaction_account
    FOREIGN KEY (idaccount)
    REFERENCES bank_database.account (idaccount)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

