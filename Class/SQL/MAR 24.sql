show databases;
use Module06;
SELECT host, user FROM mysql.user;

-- user 생성 '1234'는 비번
r: create user Celine identified by '1234';
r: select host, user from mysql.user;
r: show database();

-- Celine로 sql 시작
c: msql -u Celine -p
-- Celine에게 권한 부여 (module06 aircraft 조회가능)
r: GRANT SElect ON Module06.Aircraft To Celine

c: use Module06;
c: show tables;
c: select * from aircraft;

-- 오류남 -> aircraft 테이블만 권한을 줬으므로 
c: select *from passenger;

-- 오류남 -> 조회만 가능하게 해뒀으므로
c: update airline = '진에어' where aircraftno = 101;

-- 권한 수거
r: REOKE SELECT ON Module06.Aircraft FROM Celine;

-- VIEW: 가상 테이블
-- 사용자마다 특정 객체만 조회할 수 있도록 하기 위함 (모든 사원이 모든 정보를 볼 필요는 없음)
-- CREATE VIEW ~~ AS SELECT ~~~; 문
CREATE VIEW ReserveInfo
AS
	SELECT p.PassengerNo, p.PassengerName, p.Grade, p.Age, r.ReserveDate, f.flightNO
    FROM Passenger AS p INNER JOIN Reservation AS r ON p.passengerNO = r.PassengerNO
		INNER JOIN Flight AS f ON f.flightNo = r.flightNo;
        
SELECT * FROM ReserveInfo;


-- 프로시저
DELIMITER $$
CREATE PROCEDURE GetReserveInfo()
BEGIN
	SELECT p.PassengerNo, p.PassengerName, p.Grade, p.Age, r.ReserveDate, f.flightNO
    FROM Passenger AS p INNER JOIN Reservation AS r ON p.passengerNO = r.PassengerNO
		INNER JOIN Flight AS f ON f.flightNo = r.flightNo;
END $$
DELIMITER ;

DROP PROCEDURE getReserveInfo;
CALL GetReserveInfo();



DELIMITER $$
CREATE PROCEDURE GetReserveInfoPassenger
(
	passenger int
)
BEGIN
	SELECT p.PassengerNo, p.PassengerName, p.Grade, p.Age, r.ReserveDate, f.flightNO
    FROM Passenger AS p INNER JOIN Reservation AS r ON p.passengerNO = r.PassengerNO
		INNER JOIN Flight AS f ON f.flightNo = r.flightNo
	WHERE p.PassengerNo = passenger;
END $$
DELIMITER ;

DROP Procedure GetReserveInfoPassenger;
CALL GetReserveInfoPassenger(1);