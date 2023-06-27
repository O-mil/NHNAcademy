-- -----------------------------------------------------
-- Table `exam_43`.`people`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`people` (
  `people_id` BIGINT NOT NULL AUTO_INCREMENT,
  `people_name` VARCHAR(50) NOT NULL,
  `people_real_name` VARCHAR(50) NOT NULL,
  `people_kor_name` VARCHAR(45) NOT NULL,
  `people_birth` DATE NOT NULL,
  `people_nationality` VARCHAR(45) NOT NULL,
  `people_story` TEXT NULL,
  PRIMARY KEY (`people_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exam_43`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`role` (
  `role_id` BIGINT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exam_43`.`award`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`award` (
  `award_id` BIGINT NOT NULL AUTO_INCREMENT,
  `award_name` VARCHAR(200) NOT NULL,
  `award_year` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`award_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exam_43`.`view_age`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`view_age` (
  `view_age_id` BIGINT NOT NULL AUTO_INCREMENT,
  `view_age_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`view_age_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exam_43`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`movie` (
  `movie_id` BIGINT NOT NULL AUTO_INCREMENT,
  `movie_name` VARCHAR(100) NOT NULL,
  `movie_kor_name` VARCHAR(100) NOT NULL,
  `view_age_id` BIGINT NULL,
  `release_date` DATE NULL,
  `runtime` VARCHAR(50) NOT NULL,
  `movie_story` TEXT NULL,
  `movie_budget` VARCHAR(50) NULL,
  `usa_box_office` VARCHAR(50) NULL,
  `ww_box_office` VARCHAR(50) NULL,
  `movie_trivia` TEXT NULL,
  PRIMARY KEY (`movie_id`),
  INDEX `fk_movie_view_age1_idx` (`view_age_id` ASC) VISIBLE,
  CONSTRAINT `fk_movie_view_age1`
    FOREIGN KEY (`view_age_id`)
    REFERENCES `exam_43`.`view_age` (`view_age_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exam_43`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`genre` (
  `genre_id` BIGINT NOT NULL AUTO_INCREMENT,
  `genre_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`genre_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exam_43`.`award_nomination`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`award_nomination` (
  `award_nomination_id` BIGINT NOT NULL AUTO_INCREMENT,
  `award_id` BIGINT NOT NULL,
  `movie_id` BIGINT NOT NULL,
  `people_id` BIGINT NOT NULL,
  PRIMARY KEY (`award_nomination_id`),
  INDEX `fk_award_nomination_award1_idx` (`award_id` ASC) VISIBLE,
  INDEX `fk_award_nomination_movie1_idx` (`movie_id` ASC) VISIBLE,
  INDEX `fk_award_nomination_people1_idx` (`people_id` ASC) VISIBLE,
  CONSTRAINT `fk_award_nomination_award1`
    FOREIGN KEY (`award_id`)
    REFERENCES `exam_43`.`award` (`award_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_award_nomination_movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `exam_43`.`movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_award_nomination_people1`
    FOREIGN KEY (`people_id`)
    REFERENCES `exam_43`.`people` (`people_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exam_43`.`movie_cast`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`movie_cast` (
  `movie_cast_id` BIGINT NOT NULL AUTO_INCREMENT,
  `movie_id` BIGINT NOT NULL,
  `people_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  PRIMARY KEY (`movie_cast_id`),
  INDEX `fk_movie_cast_movie1_idx` (`movie_id` ASC) VISIBLE,
  INDEX `fk_movie_cast_people1_idx` (`people_id` ASC) VISIBLE,
  INDEX `fk_movie_cast_role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_movie_cast_movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `exam_43`.`movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movie_cast_people1`
    FOREIGN KEY (`people_id`)
    REFERENCES `exam_43`.`people` (`people_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movie_cast_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `exam_43`.`role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exam_43`.`award_winner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`award_winner` (
  `award_winner_id` BIGINT NOT NULL AUTO_INCREMENT,
  `award_nomination_id` BIGINT NOT NULL,
  PRIMARY KEY (`award_winner_id`),
  INDEX `fk_award_winner_award_nomination1_idx` (`award_nomination_id` ASC) VISIBLE,
  CONSTRAINT `fk_award_winner_award_nomination1`
    FOREIGN KEY (`award_nomination_id`)
    REFERENCES `exam_43`.`award_nomination` (`award_nomination_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exam_43`.`movie_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`movie_genre` (
  `movie_id` BIGINT NOT NULL,
  `genre_id` BIGINT NOT NULL,
  PRIMARY KEY (`movie_id`, `genre_id`),
  INDEX `fk_movie_has_genre_genre1_idx` (`genre_id` ASC) VISIBLE,
  INDEX `fk_movie_has_genre_movie1_idx` (`movie_id` ASC) VISIBLE,
  CONSTRAINT `fk_movie_has_genre_movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `exam_43`.`movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movie_has_genre_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `exam_43`.`genre` (`genre_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;