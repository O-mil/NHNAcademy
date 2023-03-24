select Database();

Create Table Aircraft (
AircraftNo int,
KindOfAircraft varchar(20),
Airline varchar(10)
);

show tables;

desc aircraft;

-- primary key 설정  
ALTER TABLE Aircraft ADD CONSTRAINT pk_Aircraft PRIMARY KEY(AircraftNo); 

desc aircraft;

-- not null 설정 (modify 사용) 
ALTER TABLE Aircraft MODIFY COLUMN KindOfAircraft varchar(20) NOT NULL;

desc Aircraft;

-- 데이터 삽입 
INSERT INTO Aircraft VALUES (101, 'Boeing 747', '대한항공');
INSERT INTO Aircraft VALUES (102, 'Boeing 727', '대한항공');
INSERT INTO Aircraft VALUES (103, 'Airbus A380', '아시아나 항공');
INSERT INTO Aircraft VALUES (104, 'Airbus A300', '대한항공');
INSERT INTO Aircraft VALUES (105, 'Boeing 737-800', '제주항공');

SELECT * FROM Aircraft;

-- Flight 테이블 생성 / Deparetures, Arrival, FlightDate는 NOT NULL로 설정 / FlightNo는 PRIMARY KEY / AircraftNo는 FOREIGN KEY로 설정
CREATE TABLE Flight (
FlightNo int,
AircraftNo int,
Deparetures nvarchar(10) NOT NULL,
Arrival nvarchar(10) NOT NULL,
Price int DEFAULT 0,
FlightDate Datetime NOT NULL,
CONSTRAINT pk_Flight PRIMARY KEY(FlightNo),
CONSTRAINT fk_Flight_Aircraft FOREIGN KEY(AircraftNo) REFERENCES Aircraft(AircraftNo)
);

show tables;

desc Flight;

INSERT INTO Flight VALUES(1, 101, '인천', '샌프란시스코', 1230000, '2022-10-23 10:20');

SELECT * FROM Flight;

INSERT INTO Flight VALUES(2, 101, '샌프란시스코', '인천', 1320000, '2022-10-26 13:00');
INSERT INTO Flight VALUES(3, 105, '김포', '제주', 72000, '2022-11-23 09:00');
INSERT INTO Flight VALUES(4, 105, '김포', '김해', 68000, '2022-11-12 17:30');
INSERT INTO Flight VALUES(5, 103, '인천', '프랑크푸르트', 1480000, '2022-12-01 18:00');
INSERT INTO Flight VALUES(6, 103, '프랑크푸르트', '인천', 1460000, '2022-12-10 10:00');
INSERT INTO Flight VALUES(7, 104, '김해', '김포', 70000, '2022-11-13 11:00');
INSERT INTO Flight VALUES(8, 101, '인천', '샌프란시스코', 1230000, '2022-11-15 10:00');

SELECT * FROM Flight;

SELECT database();


-- Reservation 테이블 생성 
CREATE TABLE Reservation(
PassengerNo int,
FlightNo int,
ReserveDate date NOT NULL
);

show tables;

-- 기본키 설정 
ALTER TABLE Reservation ADD CONSTRAINT pk_Reservation PRIMARY KEY(PassengerNo, FlightNo);

-- 외래키 설정
ALTER TABLE Reservation ADD CONSTRAINT fk_Reservation_Passenger FOREIGN KEY(PassengerNo) REFERENCES Passenger(PassengerNo);
ALTER TABLE Reservation ADD CONSTRAINT fk_Reservation_Flight FOREIGN KEY(FlightNo) REFERENCES Flight(FlightNo);

-- Reservation 컬럼에 생성한 제약 조건 확인
SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, ENFOrCED FROM information_schema.table_constraints WHERE table_name = 'Reservation';


INSERT INTO Reservation VALUES
(1, 4, '2022-10-22'),
(3, 1, '2022-10-20'),
(4, 7, '2022-10-11'),
(6, 7, '2022-10-21'),
(2, 1, '2022-10-11'),
(2, 2, '2022-10-11'),
(7, 3, '2022-09-11'),
(1, 3, '2022-11-09');

desc flight;
desc Reservation;
desc Passenger;
desc Aircraft;

-- 중복 제외하고 조회
SELECT DISTINCT Grade FROM Passenger;

-- 나이가 40세 미만인 승객 조회
-- π PassengerName, Age(σAge>=40(Passenger)) - 관계 해석 
SELECT PassengerName, Age
FROM Passenger
WHERE Age <= 40;

SELECT PassengerName, Grade, AircraftNo
FROM Passenger, Aircraft;

SELECT PassengerNO, PassengerName
FROM Passenger, Flight;

SELECT PassengerNo, PassengerName
FROM Passenger, Reservation;

-- PassengerName + '님'이라고 출력됨
SELECT PassengerName + '님', Age FROM Passenger;
-- AS 'NAME'을 붙여주면 님이라는 글자는 출력되지 않음
SELECT PassengerName + '님' AS 'Name', Age FROM Passenger;

-- 승객 테이블의 컬럼 숫자 세줌
SELECT COUNT(*) FROM Passenger;

-- 나이 숫자 세줌 (NULL은 세지 않음)
SELECT COUNT(AGE) FROM Passenger;


SELECT SUM(AGE) FROM Passenger;
SELECT MIN(age) FROM Passenger;
SELECT MAX(age) FROM Passenger;
SELECT AVG(age) FROM Passenger;

-- 운항에 참여하는 모든 비행기의 종류와 항공사, 운항 날짜를 구하라”라는 질의의 관계 대수식
-- π KindOfAircraft, Airline, FlightDate(Aircraft ⋈ Flight)
SELECT KindOfAircraft, Airline, FlightDate
FROM Aircraft AS A JOIN Flight AS F
ON A.AircraftNo = F.AircraftNo;

-- 번호가 101인 비행기의 운항 일자를 구하라
-- πFlightDate(σAircraftNo=101(Aircraft ⋈Flight)
SELECT Flightdate
FROM Flight
WHERE AircraftNo = 101;

-- Passenger ⋈ Reservation ⋈ Flight ⋈ Aircraft


-- 대한항공을 예약한 모든 승객의 이름과 나이를 구하라
-- πPassenger, age(σAirline=’대한항공’(Aircraft ⋈ Flight ⋈ Reservation ⋈ Passenger)
SELECT PassengerName, Age
FROM
	Passenger AS p INNER JOIN Reservation AS r ON p.PassengerNo = r.PassengerNO
    INNER JOIN Flight AS f ON f.flightNo = r.flightNO
    INNER JOIN Aircraft AS a ON a.aircraftNo = f.aircraftNo
WHERE a.Airline = '대한항공';

SELECT PassengerName, Age
FROM
	Aircraft AS a INNER JOIN Flight AS f ON a.AircraftNo = f.AircraftNo
    INNER JOIN Reservation AS r ON f.FlightNo = r.FlightNo
    INNER JOIN Passenger AS p ON p.PassengerNo = r.PassengerNo
WHERE
	a.Airline = '대한항공';
    
SELECT PassengerName, Age
FROM
	Passenger AS p, Reservation AS r, Flight AS f, Aircraft AS a
WHERE
	p.PassengerNo = r.PassengerNo
    AND
    f.flightNo = r.flightNo
    AND
    a.aircraftNo = f.AircraftNo
    AND
    a.Airline = '대한항공';
    
    
    
    


