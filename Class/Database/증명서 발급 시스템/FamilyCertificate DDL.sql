-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema myfamily
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema myfamily
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `myfamily` DEFAULT CHARACTER SET utf8 ;
USE `myfamily` ;

-- -----------------------------------------------------
-- Table `myfamily`.`Address_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Address_list` (
  `Address_id` BIGINT(100) NOT NULL,
  `Content` VARCHAR(45) NULL,
  PRIMARY KEY (`Address_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`User_has_Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`User_has_Address` (
  `Address_id` BIGINT(100) NOT NULL,
  `create_at` DATETIME NULL,
  `user_ad_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`user_ad_id`),
  INDEX `fk_User_has_Address_list_Address_list1_idx` (`Address_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Address_list_Address_list1`
    FOREIGN KEY (`Address_id`)
    REFERENCES `myfamily`.`Address_list` (`Address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`Hoseholder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Hoseholder` (
  `Householder_id` BIGINT(100) NOT NULL,
  `Reason` VARCHAR(45) NULL,
  `Report_date` DATETIME NULL,
  `Householdercol` VARCHAR(45) NULL,
  `User_id` BIGINT(100) NOT NULL,
  `user_ad_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Householder_id`, `User_id`),
  INDEX `fk_Householder_User1_idx` (`User_id` ASC) VISIBLE,
  INDEX `fk_Hoseholder_User_has_Address1_idx` (`user_ad_id` ASC) VISIBLE,
  CONSTRAINT `fk_Householder_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `myfamily`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Hoseholder_User_has_Address1`
    FOREIGN KEY (`user_ad_id`)
    REFERENCES `myfamily`.`User_has_Address` (`user_ad_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`Household_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Household_member` (
  `member` BIGINT(100) NOT NULL,
  `Hoseholder_id` BIGINT(100) NOT NULL,
  `User_id` BIGINT(100) NOT NULL,
  `reason` VARCHAR(45) NULL,
  `repport_date` DATETIME NULL,
  `relationships` VARCHAR(45) NULL,
  PRIMARY KEY (`member`),
  INDEX `fk_Household_member_Hoseholder1_idx` (`Hoseholder_id` ASC, `User_id` ASC) VISIBLE,
  CONSTRAINT `fk_Household_member_Hoseholder1`
    FOREIGN KEY (`Hoseholder_id` , `User_id`)
    REFERENCES `myfamily`.`Hoseholder` (`Householder_id` , `User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`User` (
  `User_id` BIGINT(100) NOT NULL,
  `Name` VARCHAR(45) NULL,
  `Birth` DATETIME NULL,
  `Gender` CHAR(1) NULL,
  `Resident_registration_number` VARCHAR(300) NULL,
  `Household_member_member` BIGINT(100) NOT NULL,
  `Death_or_live` CHAR(1) NULL,
  `Address_list_Address_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`User_id`),
  INDEX `fk_User_Household_member1_idx` (`Household_member_member` ASC) VISIBLE,
  INDEX `fk_User_Address_list1_idx` (`Address_list_Address_id` ASC) VISIBLE,
  UNIQUE INDEX `User_id_UNIQUE` (`User_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_Household_member1`
    FOREIGN KEY (`Household_member_member`)
    REFERENCES `myfamily`.`Household_member` (`member`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Address_list1`
    FOREIGN KEY (`Address_list_Address_id`)
    REFERENCES `myfamily`.`Address_list` (`Address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`Family_tie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Family_tie` (
  `Family_type_id` INT NOT NULL,
  `myself` BIGINT(100) NOT NULL,
  `target` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Family_type_id`),
  INDEX `fk_Family_tie_User1_idx` (`myself` ASC) VISIBLE,
  INDEX `fk_Family_tie_User2_idx` (`target` ASC) VISIBLE,
  CONSTRAINT `fk_Family_tie_User1`
    FOREIGN KEY (`myself`)
    REFERENCES `myfamily`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Family_tie_User2`
    FOREIGN KEY (`target`)
    REFERENCES `myfamily`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`Family_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Family_type` (
  `Family_type_id` INT NOT NULL,
  `Family_name` VARCHAR(45) NOT NULL,
  `Family_tie_id` INT NOT NULL,
  PRIMARY KEY (`Family_type_id`),
  INDEX `fk_Family_type_Family_tie_idx` (`Family_tie_id` ASC) VISIBLE,
  CONSTRAINT `fk_Family_type_Family_tie`
    FOREIGN KEY (`Family_tie_id`)
    REFERENCES `myfamily`.`Family_tie` (`Family_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`Report_person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Report_person` (
  `Report_Person_id` BIGINT(100) NOT NULL,
  `Email` VARCHAR(45) NULL,
  `Phone` VARCHAR(45) NULL,
  `User_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Report_Person_id`),
  INDEX `fk_Report_person_User1_idx` (`User_id` ASC) VISIBLE,
  CONSTRAINT `fk_Report_person_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `myfamily`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`Birth_Information`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Birth_Information` (
  `Birth_information_id` BIGINT(100) NOT NULL,
  `Report_person_Person_id` BIGINT(100) NOT NULL,
  `Birth_date` DATETIME NULL,
  `birth_address` VARCHAR(45) NULL,
  PRIMARY KEY (`Birth_information_id`),
  INDEX `fk_Birth_Information_Report_person1_idx` (`Report_person_Person_id` ASC) VISIBLE,
  CONSTRAINT `fk_Birth_Information_Report_person1`
    FOREIGN KEY (`Report_person_Person_id`)
    REFERENCES `myfamily`.`Report_person` (`Report_Person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`Birth_Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Birth_Location` (
  `Location_id` BIGINT(100) NOT NULL,
  `Location_name` VARCHAR(45) NULL,
  `Birth_Information_Birth_information_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Location_id`),
  INDEX `fk_Birth_Location_Birth_Information1_idx` (`Birth_Information_Birth_information_id` ASC) VISIBLE,
  CONSTRAINT `fk_Birth_Location_Birth_Information1`
    FOREIGN KEY (`Birth_Information_Birth_information_id`)
    REFERENCES `myfamily`.`Birth_Information` (`Birth_information_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`Death_Information`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Death_Information` (
  `Death_information_id` BIGINT(100) NOT NULL,
  `Death_date` DATETIME NULL,
  `Death_address_` VARCHAR(45) NULL,
  `Report_Person_id` BIGINT(100) NOT NULL,
  `User_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Death_information_id`),
  INDEX `fk_Death_Information_Report_person1_idx` (`Report_Person_id` ASC) VISIBLE,
  INDEX `fk_Death_Information_User1_idx` (`User_id` ASC) VISIBLE,
  CONSTRAINT `fk_Death_Information_Report_person1`
    FOREIGN KEY (`Report_Person_id`)
    REFERENCES `myfamily`.`Report_person` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Death_Information_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `myfamily`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`Death_Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`Death_Location` (
  `Location_id` BIGINT(100) NOT NULL,
  `Location_name` VARCHAR(45) NULL,
  `Death_Information_Death_information_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`Location_id`),
  INDEX `fk_Death_Location_Death_Information1_idx` (`Death_Information_Death_information_id` ASC) VISIBLE,
  CONSTRAINT `fk_Death_Location_Death_Information1`
    FOREIGN KEY (`Death_Information_Death_information_id`)
    REFERENCES `myfamily`.`Death_Information` (`Death_information_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`death_qualification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`death_qualification` (
  `qualification_id` BIGINT(100) NOT NULL,
  `name` VARCHAR(45) NULL,
  `Death_Information_Death_information_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`qualification_id`),
  INDEX `fk_death_qualification_Death_Information1_idx` (`Death_Information_Death_information_id` ASC) VISIBLE,
  CONSTRAINT `fk_death_qualification_Death_Information1`
    FOREIGN KEY (`Death_Information_Death_information_id`)
    REFERENCES `myfamily`.`Death_Information` (`Death_information_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`birth_qualification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`birth_qualification` (
  `qualification_id` BIGINT(100) NOT NULL,
  `name` VARCHAR(45) NULL,
  `Birth_Information_Birth_information_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`qualification_id`),
  INDEX `fk_birth_qualification_Birth_Information1_idx` (`Birth_Information_Birth_information_id` ASC) VISIBLE,
  CONSTRAINT `fk_birth_qualification_Birth_Information1`
    FOREIGN KEY (`Birth_Information_Birth_information_id`)
    REFERENCES `myfamily`.`Birth_Information` (`Birth_information_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`certificate` (
  `date` DATE NULL,
  `postchecknum` VARCHAR(45) NOT NULL,
  `User_User_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`postchecknum`),
  INDEX `fk_certificate_User1_idx` (`User_User_id` ASC) VISIBLE,
  CONSTRAINT `fk_certificate_User1`
    FOREIGN KEY (`User_User_id`)
    REFERENCES `myfamily`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`certificatesep`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`certificatesep` (
  `certificatesep_id` BIGINT(100) NOT NULL,
  `Name` VARCHAR(45) NULL,
  `certificate_certificate_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`certificatesep_id`),
  INDEX `fk_certificatesep_certificate1_idx` (`certificate_certificate_id` ASC) VISIBLE,
  CONSTRAINT `fk_certificatesep_certificate1`
    FOREIGN KEY (`certificate_certificate_id`)
    REFERENCES `myfamily`.`certificate` (`postchecknum`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`reporter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`reporter` (
  `report_date` DATE NOT NULL,
  `user_id` BIGINT(100) NOT NULL,
  `report_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`report_id`),
  INDEX `fk_reporter_Report_person1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_reporter_Report_person1`
    FOREIGN KEY (`user_id`)
    REFERENCES `myfamily`.`Report_person` (`Report_Person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myfamily`.`report_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myfamily`.`report_type` (
  `report_type_id` BIGINT(100) NOT NULL,
  `report_type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`report_type_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
