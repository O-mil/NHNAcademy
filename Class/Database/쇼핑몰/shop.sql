-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema 김화정_shop
-- -----------------------------------------------------
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Skin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Skin` (
  `userId` VARCHAR(45) NOT NULL,
  `skinType` VARCHAR(45) NULL,
  `skinTrouble` VARCHAR(45) NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `userId` VARCHAR(45) NOT NULL,
  `userPassword` VARCHAR(45) NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `userEmail` VARCHAR(45) NULL,
  `userPhone` VARCHAR(45) NULL,
  `gender` TINYINT NOT NULL,
  `birth` VARCHAR(45) NOT NULL,
  `sendEmail` TINYINT NOT NULL,
  `sendSMS` TINYINT NOT NULL,
  `Skin_userId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userId`),
  INDEX `fk_User_Skin1_idx` (`Skin_userId` ASC) VISIBLE,
  CONSTRAINT `fk_User_Skin1`
    FOREIGN KEY (`Skin_userId`)
    REFERENCES `mydb`.`Skin` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Category` (
  `categoryId` VARCHAR(45) NOT NULL,
  `categoryName` VARCHAR(45) NULL,
  PRIMARY KEY (`categoryId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Product` (
  `productName` VARCHAR(45) NOT NULL,
  `price` VARCHAR(45) NOT NULL,
  `productId` VARCHAR(45) NOT NULL,
  `Category_categoryId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`productId`, `Category_categoryId`),
  INDEX `fk_Product_Category1_idx` (`Category_categoryId` ASC) VISIBLE,
  CONSTRAINT `fk_Product_Category1`
    FOREIGN KEY (`Category_categoryId`)
    REFERENCES `mydb`.`Category` (`categoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Review` (
  `userId` VARCHAR(45) NOT NULL,
  `createdAt` VARCHAR(45) NOT NULL,
  `star` VARCHAR(45) NOT NULL,
  `productId` VARCHAR(45) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `fileId` VARCHAR(45) NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `fk_Review_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Category` (
  `categoryId` VARCHAR(45) NOT NULL,
  `categoryName` VARCHAR(45) NULL,
  PRIMARY KEY (`categoryId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Question` (
  `questionId` INT NOT NULL,
  `categoryId` VARCHAR(45) NULL,
  `orderId` VARCHAR(45) NULL,
  `title` VARCHAR(45) NULL,
  `content` VARCHAR(45) NULL,
  `fileId` VARCHAR(45) NULL,
  `createdAt` TIMESTAMP NULL,
  `answer` TINYINT NULL,
  `User_userId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`questionId`),
  INDEX `fk_Question_Category1_idx` (`categoryId` ASC) VISIBLE,
  INDEX `fk_Question_User1_idx` (`User_userId` ASC) VISIBLE,
  CONSTRAINT `fk_Question_Category1`
    FOREIGN KEY (`categoryId`)
    REFERENCES `mydb`.`Category` (`categoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Question_User1`
    FOREIGN KEY (`User_userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`File`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`File` (
  `fileId` VARCHAR(45) NOT NULL,
  `fileLink` VARCHAR(45) NULL,
  PRIMARY KEY (`fileId`),
  CONSTRAINT `fk_File_Review1`
    FOREIGN KEY (`fileId`)
    REFERENCES `mydb`.`Review` (`fileId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_File_Question1`
    FOREIGN KEY (`fileId`)
    REFERENCES `mydb`.`Question` (`fileId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Notice` (
  `noticeId` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NULL,
  `userId` VARCHAR(45) NULL,
  `createdAt` TIMESTAMP NULL,
  `content` VARCHAR(45) NULL,
  PRIMARY KEY (`noticeId`),
  INDEX `fk_Notice_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_Notice_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Busket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Busket` (
  `userId` INT NOT NULL,
  `productId` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL DEFAULT 1,
  `like` TINYINT NOT NULL DEFAULT 'F',
  PRIMARY KEY (`userId`),
  CONSTRAINT `fk_Busket_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Order` (
  `orderId` VARCHAR(45) NOT NULL,
  `quantity` INT NULL,
  `User_userId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`orderId`),
  INDEX `fk_Order_User1_idx` (`User_userId` ASC),
  CONSTRAINT `fk_Order_User1`
    FOREIGN KEY (`User_userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`NonUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`NonUser` (
  `name` VARCHAR(45) NOT NULL,
  `number` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `Order_orderId` VARCHAR(45) NOT NULL,
  INDEX `fk_NonUser_Order1_idx` (`Order_orderId` ASC) VISIBLE,
  PRIMARY KEY (`Order_orderId`),
  CONSTRAINT `fk_NonUser_Order1`
    FOREIGN KEY (`Order_orderId`)
    REFERENCES `mydb`.`Order` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AddressList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`AddressList` (
  `userId` VARCHAR(45) NOT NULL,
  `recipient` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `number` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NOT NULL,
  `message` VARCHAR(45) NULL,
  `addressId` INT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `fk_Address_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Address` (
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `number` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NOT NULL,
  `securityNum` TINYINT NOT NULL,
  `message` VARCHAR(45) NULL,
  `NonUser_orderId` VARCHAR(45) NOT NULL,
  `NonUser_Order_orderId` VARCHAR(45) NOT NULL,
  INDEX `fk_Address_NonUser1_idx` (`NonUser_Order_orderId` ASC) VISIBLE,
  PRIMARY KEY (`NonUser_Order_orderId`),
  CONSTRAINT `fk_Address_NonUser1`
    FOREIGN KEY (`NonUser_Order_orderId`)
    REFERENCES `mydb`.`NonUser` (`Order_orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
