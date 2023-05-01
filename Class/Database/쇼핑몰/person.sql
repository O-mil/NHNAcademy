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
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Parent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Parent` (
  `Parent_ID` BIGINT(100) NOT NULL,
  `Father_ID` BIGINT(100) NULL,
  `Mather_ID` BIGINT(100) NULL,
  PRIMARY KEY (`Parent_ID`),
  INDEX `fk_Parent_User1_idx` (`Mather_ID` ASC, `Father_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Parent_User1`
    FOREIGN KEY (`Mather_ID` , `Father_ID`)
    REFERENCES `mydb`.`User` (`User_ID` , `User_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `User_ID` BIGINT(100) NOT NULL,
  `Name` VARCHAR(45) NULL,
  `Birth` VARCHAR(45) NULL,
  `Gender` CHAR(1) NULL,
  `Resident_registration_number` VARCHAR(300) NULL,
  `Parent_ID` BIGINT(100) NOT NULL,
  PRIMARY KEY (`User_ID`),
  INDEX `fk_User_User1_idx` (`Parent_ID` ASC) VISIBLE,
  CONSTRAINT `fk_User_User1`
    FOREIGN KEY (`Parent_ID`)
    REFERENCES `mydb`.`Parent` (`Parent_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Address_List`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Address_List` (
  `Address_Id` BIGINT(100) NOT NULL,
  `Content` VARCHAR(45) NULL,
  PRIMARY KEY (`Address_Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_has_Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User_has_Address` (
  `User_ID` BIGINT(100) NOT NULL,
  `Address_Id` BIGINT(100) NOT NULL,
  `create_At` VARCHAR(45) NULL,
  INDEX `fk_User_has_Address_User_idx` (`User_ID` ASC) VISIBLE,
  INDEX `fk_User_has_Address_Address1_idx` (`Address_Id` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Address_User`
    FOREIGN KEY (`User_ID`)
    REFERENCES `mydb`.`User` (`User_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Address_Address1`
    FOREIGN KEY (`Address_Id`)
    REFERENCES `mydb`.`Address_List` (`Address_Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`table1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`table1` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Householder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Householder` (
  `Householder_ID` BIGINT(100) NOT NULL,
  `Reason` VARCHAR(45) NOT NULL,
  `Report_Date` DATETIME NOT NULL,
  `User_ID` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Householder_ID`, `User_ID`),
  INDEX `fk_Householder_User1_idx` (`User_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Householder_User1`
    FOREIGN KEY (`User_ID`)
    REFERENCES `mydb`.`User` (`User_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Qualification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Qualification` (
  `Qualification_ID` BIGINT(100) NOT NULL,
  `Name` VARCHAR(45) NULL,
  PRIMARY KEY (`Qualification_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Report_Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Report_Person` (
  `Report_Person_ID` BIGINT(100) NOT NULL,
  `Report_Personcol` VARCHAR(45) NULL,
  `User_User_ID` BIGINT(100) NOT NULL,
  `Email` VARCHAR(45) NULL,
  `Phone` CHAR(11) NULL,
  `Qualification_Qualification_ID` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Report_Person_ID`),
  INDEX `fk_Report_Person_User1_idx` (`User_User_ID` ASC) VISIBLE,
  INDEX `fk_Report_Person_Qualification1_idx` (`Qualification_Qualification_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Report_Person_User1`
    FOREIGN KEY (`User_User_ID`)
    REFERENCES `mydb`.`User` (`User_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Report_Person_Qualification1`
    FOREIGN KEY (`Qualification_Qualification_ID`)
    REFERENCES `mydb`.`Qualification` (`Qualification_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Location` (
  `Location_ID` BIGINT(100) NOT NULL,
  `Location_Name` VARCHAR(45) NULL,
  PRIMARY KEY (`Location_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Birth_Information`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Birth_Information` (
  `Birth_Information_ID` BIGINT(100) NOT NULL,
  `User_ID` BIGINT(100) NOT NULL,
  `Report_Person_Report_Person_ID` BIGINT(100) NOT NULL,
  `Location_Location_ID` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Birth_Information_ID`, `User_ID`),
  INDEX `fk_Birth_Information_User1_idx` (`User_ID` ASC) VISIBLE,
  INDEX `fk_Birth_Information_Report_Person1_idx` (`Report_Person_Report_Person_ID` ASC) VISIBLE,
  INDEX `fk_Birth_Information_Location1_idx` (`Location_Location_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Birth_Information_User1`
    FOREIGN KEY (`User_ID`)
    REFERENCES `mydb`.`User` (`User_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Birth_Information_Report_Person1`
    FOREIGN KEY (`Report_Person_Report_Person_ID`)
    REFERENCES `mydb`.`Report_Person` (`Report_Person_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Birth_Information_Location1`
    FOREIGN KEY (`Location_Location_ID`)
    REFERENCES `mydb`.`Location` (`Location_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Death_Information`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Death_Information` (
  `Death_Information-ID` BIGINT(100) NOT NULL,
  `Death_Date` DATETIME NULL,
  `Report_Person` BIGINT(100) NOT NULL,
  `Death_address` VARCHAR(45) NULL,
  `Location_Location_ID` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Death_Information-ID`),
  INDEX `fk_Death_Information_Report_Person1_idx` (`Report_Person` ASC) VISIBLE,
  INDEX `fk_Death_Information_Location1_idx` (`Location_Location_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Death_Information_Report_Person1`
    FOREIGN KEY (`Report_Person`)
    REFERENCES `mydb`.`Report_Person` (`Report_Person_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Death_Information_Location1`
    FOREIGN KEY (`Location_Location_ID`)
    REFERENCES `mydb`.`Location` (`Location_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`table2`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`table2` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Family_Composition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Family_Composition` (
  `Composition` BIGINT(100) NOT NULL,
  `Householder_Family_ID` BIGINT(100) NOT NULL,
  `Reason` VARCHAR(45) NOT NULL,
  `Report_Date` DATETIME NOT NULL,
  `Relationships` VARCHAR(45) NOT NULL,
  `User_ID` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Composition`),
  INDEX `fk_Family_Composition_Householder1_idx` (`Householder_Family_ID` ASC) VISIBLE,
  INDEX `fk_Family_Composition_User1_idx` (`User_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Family_Composition_Householder1`
    FOREIGN KEY (`Householder_Family_ID`)
    REFERENCES `mydb`.`Householder` (`Householder_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Family_Composition_User1`
    FOREIGN KEY (`User_ID`)
    REFERENCES `mydb`.`User` (`User_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Copy_Of_Resident`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Copy_Of_Resident` (
  `Certification_Sep_ID` BIGINT(100) NOT NULL,
  `Certification_Certification_ID` BIGINT(100) NOT NULL,
  `Certification_Name` VARCHAR(45) NULL,
  `User_ID` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Certification_Sep_ID`),
  INDEX `fk_Certification_Sep_User1_idx` (`User_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Certification_Sep_User1`
    FOREIGN KEY (`User_ID`)
    REFERENCES `mydb`.`User` (`User_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Family_Certification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Family_Certification` (
  `Certification_ID` BIGINT(100) NOT NULL,
  `User_User_ID` BIGINT(100) NOT NULL,
  `Certification_Number` VARCHAR(100) NULL,
  `Certification_Date` DATETIME NULL,
  `User_ID` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Certification_ID`),
  INDEX `fk_Certification_User1_idx` (`User_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Certification_User1`
    FOREIGN KEY (`User_ID`)
    REFERENCES `mydb`.`User` (`User_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
