-- Module03 p.54 (Lab3: 테이블 생성~)

use Module06;

CREATE TABLE Category (
CategoryNo int,
CategoryName varchar(50) NOT NULL,
CONSTRAINT pk_Category PRIMARY KEY(CategoryNo));

CREATE TABLE Product (
	ProdutNo int PRIMARY KEY,
    ProductName varchar(100) NOT NULL,
    UnitPrice int DEFAULT 0 NOT NULL,
    Description text,
    CategoryNo int,
    CONSTRAINT fk_Product_Category FOREIGN KEY(CategoryNo) REFERENCES Category(CategoryNO));
    
CREATE TABLE Customer (
	CustomerNo int,
    CustomerName nvarchar(10),
    Email varchar(40),
    Password varchar(16),
    CONSTRAINT pk_Customer PRIMARY KEY(CustomerNo)
) ENGINE = MyISAM CHARSET = utf8;


CREATE TABLE Orders (
	OrderNo int,
    OrderDate date,
    CustomerNo int,
    
    CONSTRAINT pk_Order PRIMARY KEY(OrderNo),
    CONSTRAINT fk_Order_Customer FOREIGN KEY(CustomerNo) REFERENCES Customer(CustomerNO));
    
-- 이걸 실행하고 위 질의를 실행해야 에러가 나지 않음.    
ALTER TABLE Customer ENGINE=INNODB;

-- UNION: 합집합 / OR (중복 데이터 출력 안 함)
SELECT * FROM Passenger WHERE grade = 10
UNION
SELECT * FROM Passenger WHERE Age > 40;

-- UNION ALL: UNION과 같으나 데이터가 중복되더라도 모두 보여줌.
SELECT * FROM Passenger WHERE grade = 10
UNION ALL
SELECT * FROM Passenger WHERE Age > 40;


EXPLAIN
SELECT * FROM Passenger WHERE grade > 5
UNION
SELECT * FROM Passenger WHERE Age > 40;

-- 위와 같은 결과값을 나타내가 보기 편함.
EXPLAIN
SELECT * FROM Passenger
WHERE
	grade > 5
	or
    Age > 40;


-- 모든 비행기의 종류, 항공사를 구하라
-- π KindOfAircraft,Airline(Aircraft) ∪ π KindOfAircraft,Airline(Aircraft)
-- π: Select
-- (  ): FROM
-- σ: WHERE
-- U: UNION
SELECT KindOfAircraft, Airline
FROM Aircraft
UNION
SELECT KindOfAircraft, Airline
FROM Aircraft;

SELECT KindOfAircraft, Airline
FROM Aircraft
UNION ALL
SELECT KindOfAircraft, Airline
FROM Aircraft;


-- 인천에서 출발하는 비행기와 제주에 도착하는 비행기의 비행기 번호와 항공사를 구하라
-- π AircraftNo, Airline(tempFlight ⋈ Aircraft)
-- 𝝆(tempFlight, (σDepareture=인천Flight) ∪ (σArrivals=제주Flight))
-- π(항공사, (σDepareture=인천Flight) ∪ (σArrivals=제주Flight) ⋈ ...?)

-- *MySQL에서는 INTERSECT와 EXCEPT, Subquery를 실행 할 수 없음
-- INTERSECT: 교집합 / AND
-- EXCEPT: 차집합


-- 대한항공에서 운항하는 항공편의 항공편 번호, 비행기 번호, 출발지, 도착지, 가격을 구하라
-- join을 사용한 쿼리
EXPLAIN
SELECT FlightNo, Deparetures, Arrival, Price
FROM Flight AS f INNER JOIN Aircraft AS a ON f.AircraftNo = a.AircraftNo
WHERE a.Airline = '대한항공';

-- 서브 쿼리를 이용한 쿼리
-- 서브쿼리: SELECT문 안에 SELECT문
-- EXPLAIN: mysql에서 테이블들이 어떤 순서로 join 하는지에 대한 정보를 포함해서, select문을 처리하는 방법에 대해 알려줌
EXPLAIN
SELECT FlightNo, Deparetures, Arrival, Price
FROM Flight
WHERE AircraftNo IN (
	SELECT AircraftNO
    FROM Aircraft
    WHERE Airline = '대한항공');
    
    
-- INLINE VIEW: 서브쿼리가 FROM절 안에서 사용됨


-- Scalar Subquery: select절에 포함된 서브 쿼리
-- 항공 편명의 출발지와 도착지, 운영 항공사를 구하시오
SELECT *
FROM flight AS f INNER JOIN aircraft AS a ON f.aircraftNo = a.aircraftNo;

-- LEFT OUTER JOIN = LEFT JOIN (왼쪽으로 모두 합침)
-- RIGHT: 오른쪽으로 합침
-- FULL: 양쪽 모두 조건이 일치하지 않는 것까지 모두 결합
SELECT flightNo, deparetures, arrival, a.aircraftNo, airline
FROM flight AS f LEFT OUTER JOIN aircraft AS a ON f.aircraftNo = a.aircraftNo;

SELECT flightNo, deparetures, arrival, (SELECT airline FROM Aircraft AS a WHERE a.aircraftNo = f.aircraftNo) AS Airline
FROM flight AS f;

SELECT Grade, AVG(Age)
FROM Passenger
GROUP BY Grade;

SELECT * FROM Passenger;

-- 내림차순 sort (null이 가장 위)
SELECT * FROM Passenger
ORDER BY Age;

-- 오름차순 sort (null이 가장 아래)
SELECt * FROM Passenger
ORDER BY Age DESC;

-- 내림차순 1번 이름 구하기
SELECT PassengerName FROM Passenger
ORDER BY Age DESC
LIMIT 1;

-- 테이블에서 젤 많은 나이 출력
SELECT MAX(Age) FROM Passenger;

-- 테이블에서 나이가 가장 많은 사람 이름 출력
SELECT PassengerName FROM Passenger
WHERE age = (SELECT MAX(age) FROM Passenger);

-- 수 세기
SELECT COUNT(*) FROM Passenger;

-- grade 수 세기
SELECT COUNT(Grade) FROM Passenger;

-- grade 수를 세는데 중복 제거
SELECT COUNT(DISTINCT Grade) FROM Passenger;

-- count시 null값 제외 (칼럼은 7, 출력값은 6)
SELECT COUNT(Age) FROM Passenger;

SELECT COUNT(Grade) FROM Passenger
GROUP BY grade;

SELECT Grade, COUNT(Grade)
FROM Passenger
GROUP BY Grade;

-- GROUP BY [그룹 대상]: 
-- HAVING: GROUP BY의 WHERE절 (조건)
SELECT Grade, MIN(Age) FROM Passenger GROUP BY Grade
HAVING MIN(Age) > 40;

-- 가장 나이가 많은 승객의 이름과 등급, 나이를 구하시오
SELECT PassengerName, Grade, Age
FROM Passenger
WHERE Age = (SELECT MAX(age) FROM Passenger);

INSERT INTO Passenger VALUES(8, '이봉창', 10, 24);

-- ORDER BY: ORDER BY절에 사용되는 컬럼을 기준으로 데이터 정렬
-- PassengerNO를 기준으로 오름차순
SELECT * FROM passenger
ORDER BY PassengerNo DESC;

-- Grade를 기준으로 정렬
SELECT * FROM Passenger
ORDER BY Grade;



-- -------------------------------------------------------------------------------------------------------------
-- INDEX: 인덱스를 지원하는 자료구조에 생성, 테이블에 지정된 컬럼의 데이터를 기준으로 정렬된 데이터를 저장함.
-- 질의에서 인덱스를 사용하면 인덱스에 포함된(정렬된) 데이터를 기준으로 데이터를 정렬함.

-- 1. 인덱스 생성
CREATE INDEX idx_passenger_grade ON Passenger(grade);

-- 2. 인덱스 생성 후, 쿼리 옵티마이저가 인덱스를 사용하도록 쿼리를 지정 -> 인덱스를 사용하여 결과를 정렬함.
SELECT PassengerName, Grade, Age
FROM Passenger
WHERE Grade > 0;



