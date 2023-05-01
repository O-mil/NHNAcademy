-- 1.항공사 (항공사 ID, 이름)
INSERT INTO Airline VALUES
("Jeju Air"),
("JIn Air"),
("Air Busan"),
("Eastar Jet"),
("T'way Air"),
("Air Seoul"),
("Korean Air"),
("Asiana Arilnes");

INSERT INTO Airport VALUES
	("ICN"),("SPN"), ("GMP"), ("CJU"), ("TAE"), ("USN"), ("CJJ"), ("WJU"), ("YNY"), ("MWX"), ("KWJ"), ("RSU"), ("KPO"), ("KUV"), ("PUS"),
    ("HND"), ("HIJ"), ("FUK"), ("KIJ"),
    ("YMX"), ("WAS"), ("SAN"), ("SFO");
    



-- 2.항공계획 시즌
INSERT INTO Season VALUES
	(1,"S","23"),
    (2, "W", "23");

-- 3. 항공계획(id,판매 시작날짜,항공사 id,시즌권 id,판매 종료날짜) 
INSERT INTO Flight_plan VALUES
    (1, "2023-03-02", "Jeju Air", 1, "2023-05-30"),
    (2, "2023-03-03", "Jeju Air", 1, "2023-05-29"),
    (3, "2023-03-04", "Jeju Air", 1, "2023-05-30"),
    (4, "2023-03-05", "Jeju Air", 1, "2023-05-30"),
    (5, "2023-03-06", "Jeju Air", 1, "2023-05-29"),
    (6, "2023-03-06", "Jeju Air", 1, "2023-05-29");
    

-- 4. 출도착지 마일리지(ID, 마일리지)
INSERT INTO Departure_And_Arrival VALUES
	(1,525), (2, 215), (3, 276), (4, 194), (5, 203);
	
-- 5. 항공 경로(id,항공계획 id,출도착지 id, 출발공항id, 출발시간, 도착공항 id,도착시간)
INSERT INTO Flight_path VALUES
	(1,1,1,"ICN","2023/05/03 09:30:00 AM","SPN","2023/05/03 15:10:00 AM"),
    (2,2,1,"SPN","2023/05/10 22:10:00 PM","ICN","2023/05/11 03:40:00 AM"),
    (3,3,3,"ICN", "2023/05/11 08:10:00 AM","CJU","2023/05/11 09:10:00 AM"),
    (4,4,3,"TAE","2023/05/09 09:50:00 AM","ICN","2023/05/09 10:55:00 AM"),
    (5,5,1,"PUS","2023/05/11 15:10:00 PM","CJU","2023/05/11 16:20:00 PM"),
    (6,6,1,"ICN","2023/05/03 15:10:00 PM","SPN","2023/05/13 20:40:00 PM");
    
    
    
-- 6. client 정보
INSERT INTO Client VALUES
	(1,"마르코","marco","marco@nhn.com",32,"010-1234-5678"),
    (2,"홍길동","HongGildong","hong@naver.com",22,"010-1234-5678"),
    (3,"이재명","LeeJaemyung","lee@naver.com",49,"010-1234-5678"),
    (4,"윤석열","YoonSeokyeol","yoon@daum.com",55,"010-1234-5678"),
    (5,"유재석","YooJaeSuk","yoo@nhn.com",48,"010-1234-5678");

-- 7. passenger 정보(승객id, client id, 이름, 영어이름, 생년월일,전화번호,성별,국적)
INSERT INTO Passenger VALUES
	(1,1,"마르코","marco","1992/05/13","010-1234-5678",'M',"Republic of Korea"),
    (2,2,"홍길동","HongGildong", "1988/05/20", "010-1234-5678", "M","Republic of Korea"),
    (3,3,"이재명","LeeJaemyung","1970/02/30", "010-1234-5678", "M","Republic of Korea"),
    (4,4,"윤석열","YoonSeokyeol","1960/12/18", "010-1234-5678", "M","Republic of Korea"),
    (5,5,"유재석","YooJaeSuk","1972/08/14", "010-1234-5678", "M","Republic of Korea");
    
    
-- 8. TAX(PSC & 질병치료, 출국자 사용료, 보안 서비스)
INSERT INTO tax VALUES
	(1,"28000","9500","7600"),
    (2, "23000", "9500", "7600");

-- 9. freight_charge
INSERT INTO freight_charge VALUES
	(1,"20000"),
    (2,"30000"),
    (3,"40000"),
    (4,"50000"),
    (5,"60000");
    
-- 10. product(상품 id,항공사 id,이름, 디테일, 가격, url, 항공계획 id)
INSERT INTO product VALUES
	(1,"Jeju Air","인천-사이판 특가","굉장히 쌈",30000,"http://hi.com",1),
    (2,"Jeju Air", "사이판-인천","평일 프로모션", 46000,"http://hi.com", 2),
    (3,"Jeju Air","인천-제주","방학 특가", 53000,"http://hi.com", 3),
    (4,"Jeju Air","대구-인천","평일 프로모션", 40000,"http://hi.com", 4),
    (5,"Jeju Air","부산-주제","주말 나들이 특가", 60000,"http://hi.com", 5);
    

-- 11. ticket 정보(ID, 티켓 번호, tax_ID, 운임요금_ID, 구매날짜, 좌석번호, 상품_ID, 항공사_ID, 승객_ID, 고객_ID)
INSERT INTO ticket VALUES
	(1,"159027",1,1,"2023/04/30 15:40:00","12D",1,1,2,1),
    (2,"121442",1,2,"2023/05/01 17:35:10","15A",2,1,2,2),
    (3,"333945",1,2,"2023/05/02 06:50:00","25B",3,1,3,3),
    (4,"553112",1,2,"2023/05/02 11:45:00","38E",2,1,4,4),
    (5,"124421",1,2,"2023/05/03 14:10:00","33A",5,1,5,5);
    
            
-- 날짜 및 출발공항 설정
SELECT f_path.departure_airport, f_path.departure_time, 
		f_path.arrival_airport, f_path.arrival_time
FROM Flight_path f_path
Inner Join Flight_plan on Flight_plan.id = f_path.Flight_plan_id
Inner Join Airline on Airline.name = Flight_plan.airline_name
where LOCATE("2023/05/03", departure_time)and
	f_path.departure_airport="ICN" and arrival_airport="SPN";
    
-- 날짜 및 도착공항 설정
SELECT f_path.departure_airport, f_path.departure_time, 
		f_path.arrival_airport, f_path.arrival_time
FROM Flight_path f_path
Inner Join Flight_plan on Flight_plan.id = f_path.Flight_plan_id
Inner Join Airline on Airline.name = Flight_plan.airline_name
where LOCATE("2023/05/10", departure_time)and
	f_path.departure_airport="SPN" and arrival_airport="ICN";

-- 해당 인원이 들고있는 티켓
SELECT DISTINCT Passenger.name,ticket.ticket_num, ticket.seats,
			Flight_path.departure_airport, 
            Flight_path.departure_time, Flight_path.arrival_airport,
            Flight_path.arrival_time
FROM ticket
INNER JOIN Passenger on Passenger.passenger_id = ticket.passenger_id
INNER JOIN product on product.product_id = ticket.product_id
INNER JOIN Airline on Airline.name = product.airline_name
INNER JOIN Flight_plan on Flight_plan.airline_name = airline.name
INNER JOIN Flight_path on Flight_path.Flight_plan_id=Flight_plan.id
WHERE Passenger.name="홍길동" and product.flight_plan_id = Flight_plan.id;



