CREATE SCHEMA IF NOT EXISTS `exam_43` DEFAULT CHARACTER SET utf8mb4;
USE `exam_43`;

DROP TABLE IF EXISTS employee_department;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

-- -----------------------------------------------------
-- Table `exam_43`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`department`
(
    `department_code` VARCHAR(50) NOT NULL,
    `department_name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`department_code`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exam_43`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`employee`
(
    `employee_id`   BIGINT      NOT NULL,
    `employee_name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`employee_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exam_43`.`employee_department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exam_43`.`employee_department`
(
    `employee_department_id` BIGINT      NOT NULL AUTO_INCREMENT,
    `employee_id`            BIGINT      NOT NULL,
    `department_code`        VARCHAR(50) NOT NULL,
    PRIMARY KEY (`employee_department_id`),
    INDEX `fk_employee_department_employee1_idx` (`employee_id` ASC) VISIBLE,
    INDEX `fk_employee_department_department1_idx` (`department_code` ASC) VISIBLE,
    CONSTRAINT `fk_employee_department_employee1`
        FOREIGN KEY (`employee_id`)
            REFERENCES `exam_43`.`employee` (`employee_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_employee_department_department1`
        FOREIGN KEY (`department_code`)
            REFERENCES `exam_43`.`department` (`department_code`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- departmenet.csv
INSERT INTO `exam_43`.`department` (`department_name`, `department_code`)
VALUES ('CS1팀', 'CS001'),
       ('CS2팀', 'CS002'),
       ('CS3팀', 'CS003'),
       ('CS4팀', 'CS004'),
       ('CS5팀', 'CS005');

INSERT INTO `exam_43`.`employee` (`employee_id`, `employee_name`)
VALUES (20230501, '최상수'),
       (20230502, '문동준'),
       (20230503, '임혜수'),
       (20230504, '조명훈'),
       (20230505, '정예은'),
       (20230506, '정지태'),
       (20230507, '황명훈'),
       (20230508, '정주혁'),
       (20230509, '손성훈'),
       (20230510, '윤민지');

INSERT INTO `exam_43`.`employee_department` (`employee_id`, `department_code`)
VALUES (20230501, 'CS001'),
       (20230502, 'CS002'),
       (20230503, 'CS003'),
       (20230504, 'CS004'),
       (20230505, 'CS005'),
       (20230506, 'CS001'),
       (20230507, 'CS002'),
       (20230508, 'CS003'),
       (20230509, 'CS001'),
       (20230510, 'CS002');


-- department.json
INSERT INTO `exam_43`.`department` (`department_name`, `department_code`)
VALUES ('백엔드1팀', 'A9001'),
       ('백엔드2팀', 'A9002'),
       ('백엔드3팀', 'A9003'),
       ('백엔드4팀', 'A9004'),
       ('백엔드5팀', 'A9005');

INSERT INTO `exam_43`.`employee` (`employee_id`, `employee_name`)
VALUES ('20220201', '박은별'),
       ('20220202', '강나리'),
       ('20220203', '하빛나'),
       ('20220204', '하초롱'),
       ('20220205', '표조은'),
       ('20220206', '설아리'),
       ('20220207', '강아라'),
       ('20220208', '안누리'),
       ('20220210', '강아라');

INSERT INTO `exam_43`.`employee_department` (`employee_id`, `department_code`)
VALUES (20220201, 'A9001'),
       (20220202, 'A9002'),
       (20220203, 'A9003'),
       (20220204, 'A9004'),
       (20220205, 'A9005'),
       (20220206, 'A9001'),
       (20220207, 'A9002'),
       (20220208, 'A9003'),
       (20220206, 'A9004'),
       (20220210, 'A9005');


-- department-1.txt
INSERT INTO `exam_43`.`department` (`department_name`, `department_code`)
VALUES ('백엔드1팀', 'L1001'),
       ('백엔드2팀', 'L1002'),
       ('백엔드3팀', 'L1003'),
       ('백엔드4팀', 'L1004'),
       ('백엔드5팀', 'L1005');

INSERT INTO `exam_43`.`employee` (`employee_id`, `employee_name`)
VALUES (20202201, '김이름'),
       (20202202, '김이름');

INSERT INTO `exam_43`.`employee_department` (`department_code`, `employee_id`)
VALUES ('L1001', 20202201),
       ('L1002', 20202202),
       ('L1003', 20202201),
       ('L1004', 20202201),
       ('L1005', 20202202),
       ('L1001', 20202202);


-- department-2.txt
INSERT INTO `exam_43`.`employee_department` (`department_code`, `employee_id`)
VALUES
    ('L1001', 20230501),
    ('L1002', 20230502),
    ('L1003', 20230503),
    ('L1004', 20230504),
    ('L1005', 20230505),
    ('L1001', 20230506),
    ('L1002', 20230507),
    ('L1003', 20230508),
    ('L1001', 20230509),
    ('L1002', 20230510);