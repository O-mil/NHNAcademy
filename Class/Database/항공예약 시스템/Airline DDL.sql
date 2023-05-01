Create database AirLine;
 USE `Airline` ;

        -- -----------------------------------------------------
        -- Table `Airline`.`Airport`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Airport` (
        `id` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`ATC_advisory`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`ATC_advisory` (
        `airport_name` VARCHAR(45) NOT NULL,
        INDEX `fk_ATC_advisory_Airport_idx` (`airport_name` ASC) VISIBLE,
        CONSTRAINT `fk_ATC_advisory_Airport`
        FOREIGN KEY (`airport_name`)
        REFERENCES `Airline`.`Airport` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`accessories_type`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`accessories_type` (
        `accessories_type_ID` INT NOT NULL,
        `type` VARCHAR(45) NULL DEFAULT NULL,
        PRIMARY KEY (`accessories_type_ID`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Airline`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Airline` (
        `name` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`name`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Season`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Season` (
        `id` INT NOT NULL,
        `season` VARCHAR(45) NOT NULL,
        `year` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Flight_Plan`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Flight_Plan` (
        `id` INT NOT NULL,
        `ticket_sell_start_date` VARCHAR(45) NOT NULL DEFAULT '항공편 판매 시작일자',
        `Airline_name` VARCHAR(45) NOT NULL,
        `season_id` INT NOT NULL,
        `ticket_sell_end_date` VARCHAR(45) NOT NULL DEFAULT '항공편 판매 종료일자',
        PRIMARY KEY (`id`),
        INDEX `fk_Flight_Plan_season1_idx` (`season_id` ASC) VISIBLE,
        INDEX `fk_Flight_Plan_airline_idx` (`Airline_name` ASC) VISIBLE,
        CONSTRAINT `fk_Flight_Plan_airline`
        FOREIGN KEY (`Airline_name`)
        REFERENCES `Airline`.`Airline` (`name`),
        CONSTRAINT `fk_Flight_Plan_season1`
        FOREIGN KEY (`season_id`)
        REFERENCES `Airline`.`Season` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3
        COMMENT = '운항계획';


        -- -----------------------------------------------------
        -- Table `Airline`.`product`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`product` (
        `product_id` INT NOT NULL,
        `Airline_name` VARCHAR(45) NOT NULL,
        `name` VARCHAR(45) NOT NULL,
        `detail` VARCHAR(45) NOT NULL,
        `price` INT NULL DEFAULT NULL,
        `url` VARCHAR(45) NULL DEFAULT NULL,
        `Flight_plan_id` INT NULL DEFAULT NULL,
        PRIMARY KEY (`product_id`, `Airline_name`),
        INDEX `fk_product_Flight_plan_idx` (`Flight_plan_id` ASC) VISIBLE,
        INDEX `fk_product_Airline_idx` (`Airline_name` ASC) VISIBLE,
        CONSTRAINT `fk_product_airline`
        FOREIGN KEY (`Airline_name`)
        REFERENCES `Airline`.`Airline` (`name`),
        CONSTRAINT `fk_product_Flight_plan`
        FOREIGN KEY (`Flight_plan_id`)
        REFERENCES `Airline`.`Flight_Plan` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Accessories`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Accessories` (
        `Accessories_ID` INT NOT NULL,
        `accessories_type_accessories_type_ID` INT NULL DEFAULT NULL,
        `product_product_ID` INT NOT NULL,
        PRIMARY KEY (`product_product_ID`, `Accessories_ID`),
        INDEX `fk_Accessories_accessories_type1_idx` (`accessories_type_accessories_type_ID` ASC) VISIBLE,
        INDEX `fk_Accessories_product1_idx` (`product_product_ID` ASC) VISIBLE,
        CONSTRAINT `fk_Accessories_accessories_type1`
        FOREIGN KEY (`accessories_type_accessories_type_ID`)
        REFERENCES `Airline`.`accessories_type` (`accessories_type_ID`),
        CONSTRAINT `fk_Accessories_product1`
        FOREIGN KEY (`product_product_ID`)
        REFERENCES `Airline`.`product` (`product_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Client`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Client` (
        `client_id` INT NOT NULL,
        `name` VARCHAR(45) NOT NULL,
        `name_english` VARCHAR(45) NOT NULL,
        `email` VARCHAR(45) NOT NULL,
        `age` VARCHAR(45) NOT NULL,
        `phone_number` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`client_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Corporate_Discount`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Corporate_Discount` (
        `id` INT NOT NULL,
        `grade` VARCHAR(45) NOT NULL,
        `domestic` DECIMAL(3,2) NOT NULL,
        `international` DECIMAL(3,2) NOT NULL,
        PRIMARY KEY (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3
        COMMENT = '임직원 할인율';


        -- -----------------------------------------------------
        -- Table `Airline`.`Corporate_Grade`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Corporate_Grade` (
        `id` INT NOT NULL,
        `grade` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`id`),
        INDEX `fk_Corporate_Preferentail_Benefits_Discount1_idx` (`id` ASC) VISIBLE,
        CONSTRAINT `fk_Corporate_Preferentail_Benefits_Discount1`
        FOREIGN KEY (`id`)
        REFERENCES `Airline`.`Corporate_Discount` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3
        COMMENT = '기업 임직원(기업 복지 담당자에게 기업코드를 제공받아 회원가입하는 형태)';


        -- -----------------------------------------------------
        -- Table `Airline`.`Representitive`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Representitive` (
        `id` INT NOT NULL,
        `company_name` VARCHAR(45) NOT NULL,
        `company_code` VARCHAR(45) NOT NULL,
        `comp_location` VARCHAR(45) NULL DEFAULT NULL,
        `comp_type` VARCHAR(45) NULL DEFAULT NULL,
        `staffnumber` VARCHAR(45) NULL DEFAULT NULL,
        `address` VARCHAR(45) NULL DEFAULT NULL,
        `comp_no` VARCHAR(45) NULL DEFAULT NULL,
        `file` VARCHAR(45) NULL DEFAULT NULL,
        `boss_name` VARCHAR(45) NULL DEFAULT NULL,
        `boss_phone` VARCHAR(45) NULL DEFAULT NULL,
        `Corporate_Grade_id` INT NOT NULL,
        PRIMARY KEY (`id`),
        INDEX `fk_Representitive_Corporate_Grade1_idx` (`Corporate_Grade_id` ASC) VISIBLE,
        CONSTRAINT `fk_Representitive_Corporate_Grade1`
        FOREIGN KEY (`Corporate_Grade_id`)
        REFERENCES `Airline`.`Corporate_Grade` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3
        COMMENT = '기업의 복지 담당자';


        -- -----------------------------------------------------
        -- Table `Airline`.`Corporate_Client`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Corporate_Client` (
        `id` INT NOT NULL,
        `Representitive_id` INT NOT NULL,
        `client_id` INT NOT NULL,
        `department` VARCHAR(45) NULL DEFAULT NULL,
        `position` VARCHAR(45) NULL DEFAULT NULL,
        PRIMARY KEY (`id`),
        INDEX `fk_Corporate_Client_Coporate_Client_Employee1_idx` (`Representitive_id` ASC) VISIBLE,
        INDEX `fk_Corporate_Client_Client1_idx` (`client_id` ASC) VISIBLE,
        CONSTRAINT `fk_Corporate_Client_Client1`
        FOREIGN KEY (`client_id`)
        REFERENCES `Airline`.`Client` (`client_id`),
        CONSTRAINT `fk_Corporate_Client_Coporate_Client_Employee1`
        FOREIGN KEY (`Representitive_id`)
        REFERENCES `Airline`.`Representitive` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Departure_And_Arrival`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Departure_And_Arrival` (
        `id` INT NOT NULL,
        `mileage` VARCHAR(45) NULL DEFAULT NULL,
        PRIMARY KEY (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3
        COMMENT = '출발 및 도착 경로 정보, 적립가능 마일리지 등의 정보';


        -- -----------------------------------------------------
        -- Table `Airline`.`Passenger`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Passenger` (
        `passenger_id` VARCHAR(45) NOT NULL,
        `client_id` VARCHAR(45) NOT NULL,
        `name` VARCHAR(45) NOT NULL,
        `english_name` VARCHAR(45) NOT NULL,
        `birth` VARCHAR(45) NOT NULL,
        `phone_number` VARCHAR(45) NOT NULL,
        `Gender` CHAR(1) NOT NULL,
        `country` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`passenger_id`, `client_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`SEG`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`SEG` (
        `id` INT NOT NULL,
        `meal` VARCHAR(45) NOT NULL,
        `class` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3
        COMMENT = '비행기티켓정보';


        -- -----------------------------------------------------
        -- Table `Airline`.`Extra_Service_Reservation_Info`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Extra_Service_Reservation_Info` (
        `Passenger_Passenger_name_Record_id` VARCHAR(45) NOT NULL,
        `SEG_id` INT NOT NULL,
        `Passenger_passenger_id` VARCHAR(45) NOT NULL,
        `Passenger_client_id` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`Passenger_Passenger_name_Record_id`, `SEG_id`, `Passenger_passenger_id`, `Passenger_client_id`),
        INDEX `fk_Extra_Service_Reservation_Info_SEG1_idx` (`SEG_id` ASC) VISIBLE,
        INDEX `fk_Extra_Service_Reservation_Info_Passenger1_idx` (`Passenger_passenger_id` ASC, `Passenger_client_id` ASC) VISIBLE,
        CONSTRAINT `fk_Extra_Service_Reservation_Info_Passenger1`
        FOREIGN KEY (`Passenger_passenger_id` , `Passenger_client_id`)
        REFERENCES `Airline`.`Passenger` (`passenger_id` , `client_id`),
        CONSTRAINT `fk_Extra_Service_Reservation_Info_SEG1`
        FOREIGN KEY (`SEG_id`)
        REFERENCES `Airline`.`SEG` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`FA_Flight_Plan`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`FA_Flight_Plan` (
        `id` INT NOT NULL,
        `Flight_Plan_id` INT NOT NULL,
        `?` VARCHAR(45) NULL DEFAULT NULL,
        PRIMARY KEY (`id`),
        INDEX `fk_FA_Flight_Plan_Flight_Plan1_idx` (`Flight_Plan_id` ASC) VISIBLE,
        CONSTRAINT `fk_FA_Flight_Plan_Flight_Plan1`
        FOREIGN KEY (`Flight_Plan_id`)
        REFERENCES `Airline`.`Flight_Plan` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3
        COMMENT = '승무원 비행계획';


        -- -----------------------------------------------------
        -- Table `Airline`.`FA_Role`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`FA_Role` (
        `id` INT NOT NULL,
        `role` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Flight_Attendant`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Flight_Attendant` (
        `id` INT NOT NULL,
        `name` VARCHAR(45) NULL DEFAULT NULL,
        `phone_number` VARCHAR(45) NULL DEFAULT NULL,
        `company_join_date` DATETIME NULL DEFAULT NULL,
        `FA_Role_id` INT NOT NULL,
        PRIMARY KEY (`id`),
        INDEX `fk_Flight_Attendant_FA_Role1_idx` (`FA_Role_id` ASC) VISIBLE,
        CONSTRAINT `fk_Flight_Attendant_FA_Role1`
        FOREIGN KEY (`FA_Role_id`)
        REFERENCES `Airline`.`FA_Role` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3
        COMMENT = '승무원 (Flight Attendant -> FA)';


        -- -----------------------------------------------------
        -- Table `Airline`.`FA_Team_Formation`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`FA_Team_Formation` (
        `FA_Flight_Plan_id1` INT NOT NULL,
        `Flight_Attendant_id1` INT NOT NULL,
        `Flight_Attendant_id2` INT NOT NULL,
        `Flight_Attendant_id3` INT NOT NULL,
        PRIMARY KEY (`FA_Flight_Plan_id1`),
        INDEX `fk_FA_Team_Formation_FA_Flight_Plan1_idx` (`FA_Flight_Plan_id1` ASC) VISIBLE,
        INDEX `fk_FA_Team_Formation_Flight_Attendant1_idx` (`Flight_Attendant_id1` ASC) VISIBLE,
        INDEX `fk_FA_Team_Formation_Flight_Attendant2_idx` (`Flight_Attendant_id2` ASC) VISIBLE,
        INDEX `fk_FA_Team_Formation_Flight_Attendant3_idx` (`Flight_Attendant_id3` ASC) VISIBLE,
        CONSTRAINT `fk_FA_Team_Formation_FA_Flight_Plan1`
        FOREIGN KEY (`FA_Flight_Plan_id1`)
        REFERENCES `Airline`.`FA_Flight_Plan` (`id`),
        CONSTRAINT `fk_FA_Team_Formation_Flight_Attendant1`
        FOREIGN KEY (`Flight_Attendant_id1`)
        REFERENCES `Airline`.`Flight_Attendant` (`id`),
        CONSTRAINT `fk_FA_Team_Formation_Flight_Attendant2`
        FOREIGN KEY (`Flight_Attendant_id2`)
        REFERENCES `Airline`.`Flight_Attendant` (`id`),
        CONSTRAINT `fk_FA_Team_Formation_Flight_Attendant3`
        FOREIGN KEY (`Flight_Attendant_id3`)
        REFERENCES `Airline`.`Flight_Attendant` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3
        COMMENT = '승무원편조';


        -- -----------------------------------------------------
        -- Table `Airline`.`Favorite_Flight_Information`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Favorite_Flight_Information` (
        `Client_client_id` INT NOT NULL,
        PRIMARY KEY (`Client_client_id`),
        CONSTRAINT `fk_Favorite_Flight_Information_Client1`
        FOREIGN KEY (`Client_client_id`)
        REFERENCES `Airline`.`Client` (`client_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Flight_Path`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Flight_Path` (
        `id` INT NOT NULL,
        `Flight_Plan_id` INT NOT NULL,
        `Departure_And_Arrival_id` INT NOT NULL,
        `departure_airport` VARCHAR(45) NOT NULL,
        `departure_time` VARCHAR(45) NOT NULL,
        `arrival_airport` VARCHAR(45) NOT NULL,
        `arrival_time` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`id`, `Flight_Plan_id`),
        INDEX `fk_Flight_Path_Flight_Plan1_idx` (`Flight_Plan_id` ASC) VISIBLE,
        INDEX `fk_Flight_Path_Departure_And_Arrival1_idx` (`Departure_And_Arrival_id` ASC) VISIBLE,
        CONSTRAINT `fk_Flight_Path_Departure_And_Arrival1`
        FOREIGN KEY (`Departure_And_Arrival_id`)
        REFERENCES `Airline`.`Departure_And_Arrival` (`id`),
        CONSTRAINT `fk_Flight_Path_Flight_Plan1`
        FOREIGN KEY (`Flight_Plan_id`)
        REFERENCES `Airline`.`Flight_Plan` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Individual_Grade`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Individual_Grade` (
        `id` INT NOT NULL,
        `grade` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Individual_Client`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Individual_Client` (
        `id` VARCHAR(45) NOT NULL,
        `client_id` INT NOT NULL,
        `Client_Grade_id` INT NOT NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
        INDEX `fk_Individual_Client_Client1_idx` (`client_id` ASC) VISIBLE,
        INDEX `fk_Individual_Client_Client_Grade1_idx` (`Client_Grade_id` ASC) VISIBLE,
        CONSTRAINT `fk_Individual_Client_Client1`
        FOREIGN KEY (`client_id`)
        REFERENCES `Airline`.`Client` (`client_id`),
        CONSTRAINT `fk_Individual_Client_Client_Grade1`
        FOREIGN KEY (`Client_Grade_id`)
        REFERENCES `Airline`.`Individual_Grade` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Information_Client`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Information_Client` (
        `resident-registration_number` INT NOT NULL,
        `passport_number` VARCHAR(45) NOT NULL,
        `client_id` INT NOT NULL,
        PRIMARY KEY (`client_id`),
        UNIQUE INDEX `resident-registration_number_UNIQUE` (`resident-registration_number` ASC) VISIBLE,
        UNIQUE INDEX `passport_number_UNIQUE` (`passport_number` ASC) VISIBLE,
        CONSTRAINT `fk_Information_Client_Client1`
        FOREIGN KEY (`client_id`)
        REFERENCES `Airline`.`Client` (`client_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Passenger_Name_Record`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Passenger_Name_Record` (
        `PNR_address` INT NOT NULL,
        `client_id` INT NOT NULL,
        PRIMARY KEY (`PNR_address`),
        INDEX `fk_Passenger_name_Record_Client1_idx` (`client_id` ASC) VISIBLE,
        CONSTRAINT `fk_Passenger_name_Record_Client1`
        FOREIGN KEY (`client_id`)
        REFERENCES `Airline`.`Client` (`client_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Reguler_Member`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Reguler_Member` (
        `id` INT NOT NULL,
        `Individual_Client_id` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
        INDEX `fk_Reguler_Member_Individual_Client1_idx` (`Individual_Client_id` ASC) VISIBLE,
        CONSTRAINT `fk_Reguler_Member_Individual_Client1`
        FOREIGN KEY (`Individual_Client_id`)
        REFERENCES `Airline`.`Individual_Client` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Sepcial_Service_Request`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Sepcial_Service_Request` (
        `Passenger_Passenger_name_Record_id` VARCHAR(45) NOT NULL,
        `SEG_id` INT NOT NULL,
        `passenger_id` VARCHAR(45) NOT NULL,
        `client_id` VARCHAR(45) NOT NULL,
        `seat_code` VARCHAR(45) NULL DEFAULT NULL,
        `meal_code` VARCHAR(45) NULL DEFAULT NULL,
        `wheelchair_code` VARCHAR(45) NULL DEFAULT NULL,
        `miscellaneous_ssr_code` VARCHAR(45) NULL DEFAULT NULL,
        `assistance_code` VARCHAR(45) NULL DEFAULT NULL,
        PRIMARY KEY (`Passenger_Passenger_name_Record_id`, `SEG_id`, `passenger_id`, `client_id`),
        INDEX `fk_Sepcial_Service_Request_SEG1_idx` (`SEG_id` ASC) VISIBLE,
        INDEX `fk_Sepcial_Service_Request_Passenger1_idx` (`passenger_id` ASC, `client_id` ASC) VISIBLE,
        CONSTRAINT `fk_Sepcial_Service_Request_Passenger1`
        FOREIGN KEY (`passenger_id` , `client_id`)
        REFERENCES `Airline`.`Passenger` (`passenger_id` , `client_id`),
        CONSTRAINT `fk_Sepcial_Service_Request_SEG1`
        FOREIGN KEY (`SEG_id`)
        REFERENCES `Airline`.`SEG` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`Simple_Member`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`Simple_Member` (
        `id` INT NOT NULL,
        `Individual_Client_id` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`id`),
        INDEX `fk_Simple_Member_Individual_Client1_idx` (`Individual_Client_id` ASC) VISIBLE,
        CONSTRAINT `fk_Simple_Member_Individual_Client1`
        FOREIGN KEY (`Individual_Client_id`)
        REFERENCES `Airline`.`Individual_Client` (`id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`additional_service_details`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`additional_service_details` (
        `additional_service_details_id` INT NOT NULL,
        PRIMARY KEY (`additional_service_details_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`afflliated_type`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`afflliated_type` (
        `afflliated_type_ID` INT NOT NULL,
        `name` VARCHAR(45) NULL DEFAULT NULL,
        PRIMARY KEY (`afflliated_type_ID`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`affiliated_product`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`affiliated_product` (
        `affiliated_product_ID` INT NOT NULL,
        `afflliated_type_afflliated_type_ID` INT NOT NULL,
        `product_product_ID` INT NOT NULL,
        PRIMARY KEY (`affiliated_product_ID`, `product_product_ID`),
        INDEX `fk_affiliated_product_afflliated_type1_idx` (`afflliated_type_afflliated_type_ID` ASC) VISIBLE,
        INDEX `fk_affiliated_product_product1_idx` (`product_product_ID` ASC) VISIBLE,
        CONSTRAINT `fk_affiliated_product_afflliated_type1`
        FOREIGN KEY (`afflliated_type_afflliated_type_ID`)
        REFERENCES `Airline`.`afflliated_type` (`afflliated_type_ID`),
        CONSTRAINT `fk_affiliated_product_product1`
        FOREIGN KEY (`product_product_ID`)
        REFERENCES `Airline`.`product` (`product_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`service_type`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`service_type` (
        `service_type_ID` INT NOT NULL,
        `categoryname` VARCHAR(45) NULL DEFAULT NULL,
        `parentno` VARCHAR(45) NULL DEFAULT NULL,
        `level` VARCHAR(45) NULL DEFAULT NULL,
        PRIMARY KEY (`service_type_ID`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`extra_service`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`extra_service` (
        `service_type_service_type_ID` INT NULL DEFAULT NULL,
        `extra_service_ID` VARCHAR(45) NOT NULL,
        `product_product_ID` INT NOT NULL,
        PRIMARY KEY (`extra_service_ID`, `product_product_ID`),
        INDEX `fk_extra_service_service_type1_idx` (`service_type_service_type_ID` ASC) VISIBLE,
        INDEX `fk_extra_service_product1_idx` (`product_product_ID` ASC) VISIBLE,
        CONSTRAINT `fk_extra_service_product1`
        FOREIGN KEY (`product_product_ID`)
        REFERENCES `Airline`.`product` (`product_id`),
        CONSTRAINT `fk_extra_service_service_type1`
        FOREIGN KEY (`service_type_service_type_ID`)
        REFERENCES `Airline`.`service_type` (`service_type_ID`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`freight_charge`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`freight_charge` (
        `freight_charge_id` INT NOT NULL,
        `freight_charge_amount` VARCHAR(45) NULL DEFAULT NULL,
        PRIMARY KEY (`freight_charge_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`tax`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`tax` (
        `tax_id` INT NOT NULL,
        `tax_tx` VARCHAR(45) NULL DEFAULT NULL,
        `tax_sw` VARCHAR(45) NULL DEFAULT NULL,
        `tax_li` VARCHAR(45) NULL DEFAULT NULL,
        PRIMARY KEY (`tax_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`ticket`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`ticket` (
        `ticket_id` INT NOT NULL,
        `ticket_num` VARCHAR(45) NOT NULL,
        `tax_tax_id` INT NOT NULL,
        `freight_charge_freight_charge_id` INT NOT NULL,
        `purchase_date` VARCHAR(45) NOT NULL,
        `seats` VARCHAR(45) NOT NULL,
        `product_id` INT NOT NULL,
        `Airline_id` VARCHAR(45) NOT NULL,
        `Passenger_id` VARCHAR(45) NOT NULL,
        `Client_id` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`ticket_id`, `Passenger_id`, `Client_id`, `Airline_id`, `product_id`),
        INDEX `fk_ticket_tax1_idx` (`tax_tax_id` ASC) VISIBLE,
        INDEX `fk_ticket_freight_charge1_idx` (`freight_charge_freight_charge_id` ASC) VISIBLE,
        INDEX `fk_ticket_product1_idx` (`product_id` ASC, `Airline_id` ASC) VISIBLE,
        INDEX `fk_ticket_Passenger1_idx` (`Passenger_id` ASC, `Client_id` ASC) VISIBLE,
        INDEX `fk_ticket_product1_idx1` (`Airline_id` ASC) VISIBLE,
        CONSTRAINT `fk_ticket_freight_charge1`
        FOREIGN KEY (`freight_charge_freight_charge_id`)
        REFERENCES `Airline`.`freight_charge` (`freight_charge_id`),
        CONSTRAINT `fk_ticket_Passenger1`
        FOREIGN KEY (`Passenger_id` , `Client_id`)
        REFERENCES `Airline`.`Passenger` (`passenger_id` , `client_id`),
        CONSTRAINT `fk_ticket_product1`
        FOREIGN KEY (`Airline_id`)
        REFERENCES `Airline`.`product` (`Airline_name`),
        CONSTRAINT `fk_ticket_tax1`
        FOREIGN KEY (`tax_tax_id`)
        REFERENCES `Airline`.`tax` (`tax_id`),
        CONSTRAINT `tk_ticket_product2`
        FOREIGN KEY (`product_id`)
        REFERENCES `Airline`.`product` (`product_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`combined_details`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`combined_details` (
        `combined_details` INT NOT NULL,
        `extra_service_extra_service_ID` VARCHAR(45) NULL DEFAULT NULL,
        `affiliated_product_affiliated_product_ID` INT NULL DEFAULT NULL,
        `ticket_ticket_id` INT NULL DEFAULT NULL,
        `Accessories_product_product_ID` INT NOT NULL,
        `Accessories_Accessories_ID` INT NOT NULL,
        PRIMARY KEY (`combined_details`),
        INDEX `fk_combined_details_extra_service1_idx` (`extra_service_extra_service_ID` ASC) VISIBLE,
        INDEX `fk_combined_details_affiliated_product1_idx` (`affiliated_product_affiliated_product_ID` ASC) VISIBLE,
        INDEX `fk_combined_details_ticket1_idx` (`ticket_ticket_id` ASC) VISIBLE,
        INDEX `fk_combined_details_Accessories1_idx` (`Accessories_product_product_ID` ASC, `Accessories_Accessories_ID` ASC) VISIBLE,
        CONSTRAINT `fk_combined_details_Accessories1`
        FOREIGN KEY (`Accessories_product_product_ID` , `Accessories_Accessories_ID`)
        REFERENCES `Airline`.`Accessories` (`product_product_ID` , `Accessories_ID`),
        CONSTRAINT `fk_combined_details_affiliated_product1`
        FOREIGN KEY (`affiliated_product_affiliated_product_ID`)
        REFERENCES `Airline`.`affiliated_product` (`affiliated_product_ID`),
        CONSTRAINT `fk_combined_details_extra_service1`
        FOREIGN KEY (`extra_service_extra_service_ID`)
        REFERENCES `Airline`.`extra_service` (`extra_service_ID`),
        CONSTRAINT `fk_combined_details_ticket1`
        FOREIGN KEY (`ticket_ticket_id`)
        REFERENCES `Airline`.`ticket` (`ticket_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`combined_product`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`combined_product` (
        `combined_product_ID` INT NOT NULL,
        `product_product_ID` INT NOT NULL,
        PRIMARY KEY (`combined_product_ID`, `product_product_ID`),
        INDEX `fk_combined_product_product1_idx` (`product_product_ID` ASC) VISIBLE,
        CONSTRAINT `fk_combined_product_product1`
        FOREIGN KEY (`product_product_ID`)
        REFERENCES `Airline`.`product` (`product_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`coupon`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`coupon` (
        `coupon_id` INT NOT NULL,
        PRIMARY KEY (`coupon_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`freight_charge_type`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`freight_charge_type` (
        `freight_charge_type_id` INT NOT NULL,
        `countryname` VARCHAR(45) NULL DEFAULT NULL,
        `freight_charge_freight_charge_id` INT NOT NULL,
        PRIMARY KEY (`freight_charge_type_id`),
        INDEX `fk_freight_charge_type_freight_charge1_idx` (`freight_charge_freight_charge_id` ASC) VISIBLE,
        CONSTRAINT `fk_freight_charge_type_freight_charge1`
        FOREIGN KEY (`freight_charge_freight_charge_id`)
        REFERENCES `Airline`.`freight_charge` (`freight_charge_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`membership_discount`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`membership_discount` (
        `membership_discount_id` INT NOT NULL,
        `membership_content` VARCHAR(45) NULL DEFAULT NULL,
        `membership_title` VARCHAR(45) NULL DEFAULT NULL,
        `membershio_price` INT NULL DEFAULT NULL,
        `coupon_coupon_id` INT NOT NULL,
        PRIMARY KEY (`membership_discount_id`),
        INDEX `fk_membership_discount_coupon1_idx` (`coupon_coupon_id` ASC) VISIBLE,
        CONSTRAINT `fk_membership_discount_coupon1`
        FOREIGN KEY (`coupon_coupon_id`)
        REFERENCES `Airline`.`coupon` (`coupon_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`payment`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`payment` (
        `payment_id` INT NOT NULL,
        `payment_name` VARCHAR(45) NULL DEFAULT NULL,
        `payment_type` VARCHAR(45) NULL DEFAULT NULL,
        PRIMARY KEY (`payment_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`personal_discount`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`personal_discount` (
        `personal_discount_type_id` INT NOT NULL,
        `discount_content` VARCHAR(45) NULL DEFAULT NULL,
        `discount_title` VARCHAR(45) NULL DEFAULT NULL,
        `discount_price` INT NULL DEFAULT NULL,
        `coupon_coupon_id` INT NOT NULL,
        PRIMARY KEY (`personal_discount_type_id`),
        INDEX `fk_personal_discount_coupon1_idx` (`coupon_coupon_id` ASC) VISIBLE,
        CONSTRAINT `fk_personal_discount_coupon1`
        FOREIGN KEY (`coupon_coupon_id`)
        REFERENCES `Airline`.`coupon` (`coupon_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`refund_reason`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`refund_reason` (
        `refund_reason_id` INT NOT NULL,
        `refund_reason_name` VARCHAR(45) NULL DEFAULT NULL,
        `cancel_charge` INT NULL DEFAULT NULL,
        PRIMARY KEY (`refund_reason_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`refund`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`refund` (
        `refund_id` INT NOT NULL,
        `refund_reason_refund_reason_id` INT NOT NULL,
        PRIMARY KEY (`refund_id`),
        INDEX `fk_refund_refund_reason1_idx` (`refund_reason_refund_reason_id` ASC) VISIBLE,
        CONSTRAINT `fk_refund_refund_reason1`
        FOREIGN KEY (`refund_reason_refund_reason_id`)
        REFERENCES `Airline`.`refund_reason` (`refund_reason_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`table2`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`table2` (
        `bank` INT NOT NULL,
        `bankname` VARCHAR(45) NULL DEFAULT NULL,
        PRIMARY KEY (`bank`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;


        -- -----------------------------------------------------
        -- Table `Airline`.`tax_type`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `Airline`.`tax_type` (
        `tax_type_id` INT NOT NULL,
        `airportname` VARCHAR(45) NULL DEFAULT NULL,
        `tax_tax_id` INT NOT NULL,
        PRIMARY KEY (`tax_type_id`),
        INDEX `fk_tax_type_tax1_idx` (`tax_tax_id` ASC) VISIBLE,
        CONSTRAINT `fk_tax_type_tax1`
        FOREIGN KEY (`tax_tax_id`)
        REFERENCES `Airline`.`tax` (`tax_id`))
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8mb3;

        