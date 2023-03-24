USE DatamotionMovieDatabase;

-- 1. DBManager 라는 이름을 가지는 사용자를 작성하세요.
CREATE User DBManager identified by '1234';


-- 2. User 라는 이름을 가지는 사용자를 작성하세요.
CREATE User User identified by '1234';


-- 3. DBManger 사용자는 DatamotionMovieDatabase의 모든 개체에 모든 권한을 가지되, 다른 데이터베이스에 대한 권한은 가지지 않아야 합니다.
GRANT ALL ON DatamotionMovieDatabase.* To DBManager;


-- 4. User 사용자는 가지는 데이터베이스의 모든 개체에 대한 읽기 권한과, PersonTrivia 테이블과 MovieTrivia 테이블에는 읽기 및 쓰기 권한을 가집니다.
GRANT SELECT ON * To User;
GRANT UPDATE ON DataMotionMovieDatabase.PersonTrivia TO User;
GRANT UPDATE ON DataMotionMovieDatabase.MovieTrivia To User;


-- 5. '영화'에 출연한 '배우'와 관련된 뷰를 작성하세요.
CREATE VIEW ActorInfo
AS
	SELECT Person.*, Movie.*
	FROM
		Person INNER JOIN Appear ON Person.PersonID = Appear.PersonID
        INNER JOIN Role ON Appear.RoleID = Role.RoleID
        INNER JOIN Movie ON appear.movieID = movie.movieID
	WHERE Role.RoleKorName = '배우';

SELECT * FROM ActorInfo;


-- 6. '영화'를 감독한 '감독'과 관련된 뷰를 작성하세요.
CREATE VIEW DirectorInfo
AS
	SELECT Person.*, Movie.*
    FROM
		Person INNER JOIN Appear ON Person.PersonID = Appear.PersonID
        INNER JOIN Role ON Appear.RoleID = Role.RoleID
        INNER JOIN Movie ON appear.movieID = movie.movieID
	WHERE Role.RoleKorName = '감독';

SELECT * FROM DirectorInfo;

		
-- 7. '아카데미 시상 결과'과 관련된 뷰를 작성하세요.
CREATE VIEW ResultInfo
AS
	SELECT m.KoreanTitle, ay.`Year`, w.WinOrNot
    FROM
		AwardInvolve ai INNER JOIN Winning w ON ai.WinningID = w.WinningID
        INNER JOIN AwardYear ay ON ai.AwardYearID = ay.AwardYearID
        INNER JOIN Award a ON ay.AwardID = a.AwardID
        INNER JOIN Appear ap ON ai.appearID = ap.appearID
        INNER JOIN Movie m ON ap.movieID = m.movieID
	ORDER By ay.`year` DESC;

SELECT * FROM ResultInfo;


-- 8. '영화 장르별 제작비와 흥행 수익'에 관련된 뷰를 작성하세요.
CREATE VIEW BudgetGrossInfo
AS
	SELECT g.GenreKorName, SUM(m.Budget), SUM(m.BoxOfficeWWGross), SUM(m.BoxOfficeUSGross)
    FROM
		Movie m INNER JOIN MovieGenre mg ON m.movieID = mg.movieID
        INNER JOIN Genre g ON mg.genreID = g.genreID
	GROUP By GenreKorName;
        
SELECT * FROM BudgetGrossInfo;
        
        
-- 9. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
SELECT COUNT(Name)
FROM
	Person p INNER JOIN appear a ON p.personID = a.personID
    INNER JOIN movie m ON a.MovieID = m.MovieID
    INNER JOIN `role` r ON a.RoleID = r.RoleID
WHERE m.KoreanTitle = '매트릭스' AND r.RoleKorname = '감독';

-- 10. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 출력하세요.
SELECT title
FROM Movie
WHERE RunningTime = 100 IN (
	SELECT KoreanName
    FROM person
    WHERE KoreanName = '레오나르도 디카프리오');
    
    
-- 11. '알란 실버스트리'가 음악을 작곡한 영화와 '애런 소킨'이 각본을 쓴 영화를 출력하세요. (애런 소킨 데이터를 찾아보세요)
SELECT Title
FROM
	Movie m INNER JOIN Appear a ON m.MovieID = a.MovieID
    INNER JOIN `Role` r ON a.RoleID = r.RoleID
    INNER JOIN Person p ON a.PersonID = p.PersonID
WHERE (r.RoleKorName = '작곡' AND p.KoreanName = '알란 실버스트리') OR (r.RoleKorName = '각본가' AND p.KoreanName = '아론 소킨');


-- 12. '쿠엔틴 타란티노'가 감독한 영화에 출연한 배우들이 출연한 영화의 수익율을 배우별로 출력하세요.
SELECT p.KoreanName, SUM(m.BoxOfficeWWGross - m.Budget) / SUM(m.Budget) * 100 AS '수익률'
FROM
	Movie m INNER JOIN Appear a ON m.MovieID = a.MovieID
    INNER JOIN `Role` r ON a.RoleID = r.RoleID
    INNER JOIN Person p ON a.PersonID = p.PersonID
WHERE r.RoleKorName = '배우' AND m.Title IN (
	SELECT m.Title
    FROM
		Movie m INNER JOIN Appear a ON m.MovieID = a.MovieID
        INNER JOIN `Role` r ON a.RoleID = r.RoleID
		INNER JOIN Person p ON a.PersonID = p.PersonID
    WHERE RoleKorName = '감독' AND p.KoreanName = '쿠엔틴 타란티노')
GROUP by p.KoreanName;


SELECT KoreanName, SUM(BoxOfficeWWGross - Budget) / SUM(Budget) * 100 AS '수익률'
FROM ActorInfo
WHERE title IN (
	SELECT Title
    FROM DirectorInfo
    WHERE KoreanName = '쿠엔틴 타란티노')
GROUP BY KoreanName;


-- 13. 위 문제들 중, 생성한 뷰에서 질의가 어려운 쿼리에 대한 뷰를 작성하세요.

CREATE VIEW earnInfo
AS
	SELECT KoreanName, SUM(BoxOfficeWWGross - Budget) / SUM(Budget) * 100 AS '수익률'
	FROM ActorInfo
	WHERE title IN (
		SELECT Title
		FROM DirectorInfo
		WHERE KoreanName = '쿠엔틴 타란티노')
	GROUP BY KoreanName;


-- 14. 새로 생성한 뷰를 사용해서 쿼리를 다시 작성하세요.
SELECT 수익률
FROM earnInfo
WHERE KoreanName = '케리 워싱턴';


-- 14. 사용자 테이블을 작성하세요. 사용자 테이블은 사용자의 ID, 이름, 패스워드, 전자메일 주소를 필드로 가집니다.
CREATE TABLE `User` (
	UserID int PRIMARY KEY,
    UserName char(100),
    PassWord char(100),
    Mail char(100)); 
    
INSERT INTO USER VALUES (1, '김화정', '1234', 'dfjl@naver.com');
INSERT INTO USER VALUES (2, '김윤민', '1245', 'lsldk@naver.com');
    

-- 15. 사용자가  MovieTrivia 테이블과 PersonTrivia 테이블에 투플을 삽입할 수 있고, 각 투플에 삽입한 사용자를 식별할 수 있는 정보가 포함되어야 할 때, 두 테이블의 릴레이션 스키마를 수정하세요.
ALTER TABLE MovieTrivia ADD UserID int;
ALTER TABLE PersonTrivia ADD UserID int;


-- 16. 수정된 테이블 두 테이블(MovieTrivia, PersonTrivia)과 사용자 테이블 사이의 참조 무결성을 위한 제약조건을 설정하세요.
ALTER TABLE PersonTrivia ADD CONSTRAINT fk_personTrivia_User FOREIGN KEY(UserID) REFERENCES `User`(UserID);
ALTER TABLE MovieTrivia ADD CONSTRAINT fk_MovieTrivia_User FOREIGN KEY(UserID) REFERENCES `User`(UserID);


-- 17. User 사용자가 두 테이블(MovieTrivia, PersonTrivia)에 데이터를 삽입할 수 있도록 권한을 설정하세요.
GRANT INSERT ON DatamotionMovieDatabase.MovieTrivia To User;
Grant INSERT ON DatamotionMovieDatabase.PersonTrivia To User;


-- 18. MovieTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE InsertData (TriviaID int, MovieID int, USERID int)
BEGIN
	INSERT INTO MovieTrivia VALUES (TriviaID, MovieID, UserID);
END $$
DELIMITER ;

CALL InsertData(2, 13613, 1);
CALL InsertData(3, 13613, 2);


-- 19. PersonTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE InsertData2 (TriviaID int, PersonID int, USERID int)
BEGIN
	INSERT INTO PersonTrivia VALUES (TriviaID, PersonID, UserID);
END $$
DELIMITER ;

CALL InsertData2(1, 13613, 1);
CALL InsertData2(4, 7, 2);


-- 20. 주어진 감독이 감독한 영화를 모두 출력하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE Movie_Director(Name varchar(200))
BEGIN
	SELECT p.KoreanName, m.Koreantitle
    FROM
		Movie m INNER JOIN Appear a ON m.MovieID = a.MovieID
        INNER JOIN `Role` r ON a.RoleID = r.RoleID
        INNER JOIN Person p ON a.personID = p.PersonID
	WHERE r.RoleKorName = '감독' AND p.KoreanName = Name;
END $$
DELIMITER ;
drop Procedure movie_director;
CALL Movie_Director('제임스 카메론');


-- 21. 주어진 영화에 출연한 배우를 모두 출력하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE Movie_Actor(Title varchar(250))
BEGIN
	SELECT r.RoleKorName, p.KoreanName
    FROM
		Movie m INNER JOIN Appear a ON m.MovieID = a.MovieID
        INNER JOIN `Role` r ON a.RoleID = r.RoleID
        INNER JOIN Person p ON a.personID = p.PersonID
	WHERE r.RoleKorName = '배우' AND m.KoreanTitle = Title;
END $$
DELIMITER ;

CALL Movie_Actor('아바타');


-- 22. 주어진 영화에 참여한, 감독, 각본, 배우를 제외한 모든 사람을 출력하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE Movie_People(Title varchar(250))
BEGIN
	SELECT p.KoreanName, r.RoleKorName
    FROM
		Movie m INNER JOIN Appear a ON m.MovieID = a.MovieID
        INNER JOIN `Role` r ON a.RoleID = r.RoleID
        INNER JOIN Person p ON a.personID = p.PersonID
	WHERE m.KoreanTitle = Title AND r.RoleKorName NOT IN (
		SELECT RoleKorName
        FROM `Role`
        WHERE RoleKorName = '감독' OR RoleKorName = '각본가' OR RoleKorName = '배우');
END $$
DELIMITER ;

CALL Movie_People('아바타');


-- 23. 주어진 사람이 '참여'한 영화를 출력하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE Movie_People2(KoreanName varchar(200))
BEGIN
	SELECT DISTINCT m.KoreanTitle
    FROM
		Person p INNER JOIN Appear a ON p.personID = a.personID
        INNER JOIN Movie m ON a.movieID = m.movieID
	WHERE p.KoreanName = KoreanName;
END $$
DELIMITER ;

CALL Movie_People2('클린트 이스트우드');


-- 24. 주어진 장르에 대한 제작비, 전체 수익과 수익율을 출력하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE Budget_Gross (GenreKorName varchar(50))
BEGIN
	SELECT g.GenreKorName, SUM(Budget) AS '제작비', SUM(BoxOfficeWWGross) AS '전체 수익',SUM(BoxOfficeWWGross - Budget) / SUM(Budget) * 100 AS '수익률'
    FROM
		Movie m INNER JOIN MovieGenre mg ON m.movieID = mg.movieID
        INNER JOIN Genre g ON mg.genreID = g.genreID
	WHERE g.GenreKorName = GenreKorName
    Group By g.GenreKorName;
END $$
DELIMITER ;

DROP PROCEDURE Budget_Gross;
CALL Budget_Gross('드라마');



-- <수업하지 않은 내용>
-- 25. 저장 프로시저의 파라미터는 출력/입력/입출력 형태의 파라미터를 가질 수 있습니다.
-- 주어진 영화(MovieID)로 출연/참여자 정보를 제외한 모든 정보를 출력 파라미터로 가지고, 실행 결과가 출력 파라미터에 기록되도록 하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE Movie_return(IN movieID int, OUT Title varchar(250), OUT KoreanTitle varchar(250), out plot varchar(4000), out releaseyear smallINT, out runningtime smallint, out gradeID smallint, out gradeINkoreaid smallint, out poster varchar(200), out releaseDateInKorea date, out boxofficewwgross bigint, out boxofficeusgross bigint, out budget bigint, out originalAuthor varchar(200), out originalsourse varchar(400))
BEGIN
	SELECT Title = Title, KoreanTitle = KoreanTitle, plot = plot, releaseyear = releaseyear, runningtime = runningtime, gradeID = gradeID, gradeINkoreaid = gradeINkoreaid, poster = poster, releaseDateInKorea = releaseDateInKorea, boxofficewwgross = boxofficewwgross, boxofficeusgross = boxofficeusgross, budget = budget, originalAuthor = originalAuthor, originalsourse = originalsourse
    FROM Movie
    WHERE MovieID = movieID;
END $$
DELIMITER ;

DROP PROCEDURE Movie_return;
CALL Movie_return(3);