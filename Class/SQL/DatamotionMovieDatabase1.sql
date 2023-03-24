use DatamotionMovieDatabase;

-- 1. 영화 '퍼스트 맨'의 제작 연도, 영문 제목, 러닝 타임, 플롯을 출력하세요
SELECT ReleaseYear, Title, RunningTime, Plot
FROM movie
WHERE KoreanTitle = '퍼스트 맨';


-- 2. 2003년에 개봉한 영화의 한글 제목과 영문 제목을 출력하세요
SELECT KoreanTitle, Title
FROM movie
WHERE ReleaseYear = 2003;


-- 3. 영화 '글래디에이터'의 작곡가를 고르세요
SELECT Name
FROM 
	Person INNER JOIN appear ON person.personID = appear.personID
    INNER JOIN movie ON appear.MovieID = movie.MovieID
    INNER JOIN role ON appear.RoleID = role.RoleID
WHERE RoleKorname = '작곡' AND KoreanTitle = '글래디에이터';


-- 4. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
SELECT COUNT(Name)
FROM
	Person INNER JOIN appear ON person.personID = appear.personID
    INNER JOIN movie ON appear.MovieID = movie.MovieID
    INNER JOIN role ON appear.RoleID = role.RoleID
WHERE KoreanTitle = '매트릭스' AND RoleKorname = '감독';


-- 5. 감독이 2명 이상인 영화를 출력하세요
SELECT title
FROM
	Movie INNER JOIN Appear ON movie.movieid = appear.movieid
    INNER JOIN role ON appear.roleid = role.roleid
WHERE roleKorname = '감독'
GRoup by title
HAVING COUNT(roleKorname) >= 2;


-- 6. '한스 짐머'가 참여한 영화 중 아카데미를 수상한 영화를 출력하세요
SELECT title
FROM
	Movie INNER JOIN Appear ON movie.movieid = appear.movieid
    INNER JOIN person ON appear.personid = person.personid
    INNER JOIN AwardInvolve ON appear.appearID = AwardInvolve.appearid
    INNER JOIN Winning ON AwardInvolve.winningID = winning.winningID
WHERE KoreanName = '한스 짐머' AND winornot = 'winner';


-- 7. 감독이 '제임스 카메론'이고 '아놀드 슈워제네거'가 출연한 영화를 출력하세요 (V)
SELECT Title
FROM
	Appear INNER JOIN Person ON Appear.personID = Person.PersonID
    INNER JOIN Movie ON Appear.MovieID = Movie.MovieID
    INNER JOIN Role ON Role.ROleID = Appear.RoleID
WHERE (RoleKorName = '감독' AND Person.KoreanName = "제임스 카메론")
	AND Person.KoreanName = '아놀드 슈워제네거';

		
	
-- 8. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 고르시오
SELECT title
FROM Movie
WHERE RunningTime = 100 IN (
	SELECT KoreanName
    FROM person
    WHERE KoreanName = '레오나르도 디카프리오');


-- 9. 청소년 관람불가 등급의 영화 중 가장 많은 수익을 얻은 영화를 고르시오
SELECT title
FROM
	Movie INNER JOIN GradeInKorea ON Movie.GradeInKoreaId = GradeInKorea.GradeInKoreaID
WHERE GradeInKoreaName = '청소년 관람불가'
ORDER BY BoxOfficeWWGross DESC
LIMIT 1;
	
    
-- 10. 1999년 이전에 제작된 영화의 수익 평균을 고르시오
SELECT AVG(BoxOfficeWWGross)
FROM Movie
WHERE ReleaseYear < 1999;


-- 11. 가장 많은 제작비가 투입된 영화를 감독한 사람은 누구입니까?
SELECT Name
FROM
	Movie INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
    INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
ORDER BY Budget DESC
LIMIT 1;

-- 12. 제작한 영화의 제작비 총합이 가장 높은 감독은 누구입니까?
SELECT Name
FROM
	Movie INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
    INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
WHERE RoleKorName = '감독'
GROUP BY Name
ORDER BY SUM(Budget) DESC
Limit 1;

	
-- 13. 출연한 영화의 모든 수익을 합하여, 총 수입이 가장 많은 배우를 출력하세요.
SELECT Name
FROM
	Movie INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
    INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
WHERE RoleKorName = '배우'
Group BY Name
ORDER BY SUM(BoxOfficeWWGross) DESC
LIMIT 1;


-- 14. 제작비가 가장 적게 투입된 영화의 수익을 고르세요. (제작비가 0인 영화는 제외합니다)
SELECT BoxOfficeWWGross
FROM Movie
ORDER BY Budget Is NULL ASC
LIMIT 1;

-- 15. 제작비가 5000만 달러 이하인 영화의 미국내 평균 수익을 고르세요
SELECT AVG(BoxOfficeUSGross)
FROM Movie
Where Budget <= 50000000;


-- 16. 액션 장르 영화의 평균 수익을 집계하세요.
SELECT AVG(BoxOfficeWWGross)
FROM
	Movie INNER JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID
    INNER JOIN Genre ON MovieGenre.GenreID = Genre.GenreID
WHERE GenreKorName = '액션';


-- 17. 드라마, 전쟁 장르의 영화를 고르세요.
SELECT Title
FROM
	Movie INNER JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID
    INNER JOIN Genre ON MovieGenre.GenreID = Genre.GenreID
WHERE GenreKorName = '드라마' OR GenreKorName = '전쟁';


-- 18. 톰 행크스가 출연한 영화 중 상영 시간이 가장 긴 영화의 제목, 한글제목, 개봉연도를 출력하세요.
SELECT Title, KoreanTitle, releaseYear
FROM
	Movie INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
WHERE KoreanName = '톰 행크스'
ORDER By RunningTime DESC
limit 1;


-- 19. 아카데미 남우주연상을 가장 많이 수상한 배우를 고르시오
SELECT Name
FROM
	Person INNER Join Appear ON Person.personID = Appear.personID
    INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN AwardInvolve ON Appear.appearID = AwardInvolve.appearID
    INNER JOIN Sector ON AwardInvolve.sectorID = Sector.sectorID
    INNER JOIN Winning ON AwardInvolve.WinningID = Winning.WinningID
WHERE RoleKorName = '배우' AND SectorKorName = '남우주연상' AND WinOrNot = 'Winner'
GROUP BY Name
ORDER BY COUNT(*) DESC
Limit 1;


-- 20. 아카데미상을 가장 많이 수상한 영화인을 고르시오 ('수상자 없음'이 이름인 영화인은 제외합니다)
SELECT Name
FROM
	Person INNER Join Appear ON Person.personID = Appear.personID
    INNER JOIN Role ON Appear.roleID = Role.roleID
    INNER JOIN AwardInvolve ON Appear.appearID = AwardInvolve.appearID
    INNER JOIN AwardYear ON AwardInvolve.awardYearID = AwardYear.awardYearID
    INNER JOIN Award ON AwardYear.awardID = Award.awardID
    INNER JOIN Winning ON AwardInvolve.winningID = Winning.winningID
WHERE AwardKoreanTitle = '아카데미 영화제' AND RoleKorName = '배우' AND WinOrNot = 'Winner'
Group BY Name
ORDER BY count(*) DESC
LIMIT 1;


-- 21. 아카데미 남우주연상을 2번 이상 수상한 배우를 고르시오
SELECT Name
FROM
	Person INNER Join Appear ON Person.personID = Appear.personID
    INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN AwardInvolve ON Appear.appearID = AwardInvolve.appearID
    INNER JOIN Sector ON AwardInvolve.sectorID = Sector.sectorID
    INNER JOIN Winning ON AwardInvolve.WinningID = Winning.WinningID
WHERE RoleKorName = '배우' AND SectorKorName = '남우주연상' AND WinOrNot = 'Winner'
GROUP BY Name
HAVING COUNT(*) >= 2;


-- 23. 아카데미상을 가장 많이 수상한 사람을 고르세요.
SELECT Name
FROM
	Person INNER Join Appear ON Person.personID = Appear.personID
    INNER JOIN AwardInvolve ON Appear.appearID = AwardInvolve.appearID
    INNER JOIN AwardYear ON AwardInvolve.awardYearID = AwardYear.awardYearID
    INNER JOIN Award ON AwardYear.awardID = Award.awardID
    INNER JOIN Winning ON AwardInvolve.winningID = Winning.winningID
WHERE AwardKoreanTitle = '아카데미 영화제' AND WinOrNot = 'Winner'
Group BY Name
ORDER BY count(*) DESC
LIMIT 1;


-- 24. 아카데미상에 가장 많이 노미네이트 된 영화를 고르세요.
SELECT Title
FROM
	Movie INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
    INNER JOIN AwardInvolve ON Appear.appearID = AwardInvolve.appearID
    INNER JOIN AwardYear ON AwardInvolve.awardYearID = AwardYear.awardYearID
    INNER JOIN Award ON AwardYear.awardID = Award.awardID
    INNER JOIN Winning ON AwardInvolve.winningID = Winning.winningID
WHERE AwardKoreanTitle = '아카데미 영화제' AND WinOrNot = 'Nominated'
Group By Title
Order By Count(*) DESC
LIMIT 1;


-- 25. 가장 많은 영화에 출연한 여배우를 고르세요.
SELECT Name
FROM
	Movie INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
    INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
WHERE RoleName = 'Actress'
Group By Name
Order by Count(*) DESC
LIMIT 1;


-- 26. 수익이 가장 높은 영화 TOP 10을 출력하세요.
SELECT Title
FROM Movie
ORDER BY BoxOfficeWWGross DESC
LIMIT 10;


-- 27. 수익이 10억불 이상인 영화중 제작비가 1억불 이하인 영화를 고르시오.
SELECT Title
FROM Movie
WHERE BoxOfficeWWGross >= 1000000000 AND Budget <= 100000000;


-- 28. 전쟁 영화를 가장 많이 감독한 사람을 고르세요.
SELECT Name
FROM
	Person INNER JOIN Appear ON Person.personID = Appear.appearID
    INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Movie ON Appear.movieID = Movie.movieID
    INNER JOIN Moviegenre ON Movie.movieID = MovieGenre.movieID
    INNER JOIN Genre ON MovieGenre.genreID = Genre.genreID
WHERE RoleKorName = '감독' AND GenreKorName = '전쟁'
Group By Name
Order By Count(*) DESC
Limit 1;


-- 29. 드라마에 가장 많이 출연한 사람을 고르세요.
SELECT Name
FROM
	Person INNER JOIN Appear ON Person.personID = Appear.appearID
    INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Movie ON Appear.movieID = Movie.movieID
    INNER JOIN Moviegenre ON Movie.movieID = MovieGenre.movieID
    INNER JOIN Genre ON MovieGenre.genreID = Genre.genreID
WHERE GenreKorName = '드라마' AND RoleKorName = '배우'
Group By Name
Order By count(*) DESC
LIMIT 1;


-- 30. 드라마 장르에 출연했지만 호러 영화에 한번도 출연하지 않은 사람을 고르세요. (V)
SELECT Name
FROM
	Person INNER JOIN Appear ON Person.personID = Appear.appearID
    INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Movie ON Appear.movieID = Movie.movieID
    INNER JOIN Moviegenre ON Movie.movieID = MovieGenre.movieID
    INNER JOIN Genre ON MovieGenre.genreID = Genre.genreID
WHERE GenreKorName = '드라마' AND RoleKorName = '배우'
NOT IN (
SELECT Name
FROM
	Person INNER JOIN Appear ON Person.personID = Appear.appearID
    INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Movie ON Appear.movieID = Movie.movieID
    INNER JOIN Moviegenre ON Movie.movieID = MovieGenre.movieID
    INNER JOIN Genre ON MovieGenre.genreID = Genre.genreID
WHERE GenreKorName = '호러' AND RoleKorName = '배우');


-- 31. 아카데미 영화제가 가장 많이 열린 장소는 어디인가요?
SELECT Location
FROM
	AwardYear INNER JOIN Award ON AwardYear.AwardID = Award.awardID
WHERE AwardKoreanTitle = '아카데미 영화제'
Group By location
order by count(*) DESC
limit 1;


-- 33. 첫 번째 아카데미 영화제가 열린지 올해 기준으로 몇년이 지났나요?
SELECT (2023 - year)
FROM AwardYear
ORDER By Year
LIMIT 1;


-- 34. 아카데미에 가장 많이 노미네이트된 사람은 누구인가요?
SELECT Name
FROM
	Person INNER JOIN Appear ON Person.personID = Appear.personID
    INNER JOIN AwardInvolve ON Appear.appearID = AwardInvolve.appearID
    INNER JOIN AwardYear ON AwardInvolve.awardYearID = AwardYear.awardYearID
    INNER JOIN Award ON AwardYear.awardID = Award.awardID
    INNER JOIN Winning ON AwardInvolve.winningID = Winning.winningID
WHERE AwardKoreanTitle = '아카데미 영화제' AND WinOrNot = 'Nominated'
Group By Name
Order By Count(*) DESC
LIMIT 1;


-- 35. 1999년에서 2009년 사이에 남우 주연상을 수상한 배우를 모두 고르세요.
SELECT Name
FROM
	Person INNER Join Appear ON Person.personID = Appear.personID
    INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN AwardInvolve ON Appear.appearID = AwardInvolve.appearID
    INNER JOIN AwardYear ON AwardInvolve.awardYearID = AwardYear.awardYearID
    INNER JOIN Sector ON AwardInvolve.sectorID = Sector.sectorID
    INNER JOIN Winning ON AwardInvolve.WinningID = Winning.WinningID
WHERE RoleKorName = '배우' AND SectorKorName = '남우주연상' AND WinOrNot = 'Winner'
	AND ( Year >= 1999 AND Year <= 2009);


-- 36. 아카데미를 한번 수상한 후, 가장 짧은 기간에 한번 더 수상한 사람을 고르세요. (V)
SELECT Name
FROM
	Person INNER JOIN Appear ON Person.personID = Appear.personID
    INNER JOIN AwardInvolve ai1 ON Appear.appearID = ai1.appearID
    INNER JOIN AwardInvolve ai2 ON Appear.appearID = ai2.appearID
    INNER JOIN AwardYear ay1 ON ai1.awardYearID = ay1.awardYearID
    INNER JOIN AwardYear ay2 ON ai2.awardYearID = ay2.awardYearID
    INNER JOIN Winning w1 ON ai1.WinningID = w1.WinningID
    INNER JOIN Winning w2 ON ai2.WinningID = w2.WinningID
WHERE w1.WinOrNOt = 'Winner' AND w2.WinOrNot = 'winner' AND (ay2.Year > ay1.Year)
Group By Name
HAVING COUNT(*) >= 2
Order By MIN(DATEDIFF(ay2.Year, ay1.Year))
LIMIT 1;


-- 37. '제임스 카메론'의 영화에 출연한 모든 배우를 고르세요
SELECT Name
FROM
	Movie INNER JOIN Appear ON Movie.movieID = appear.movieID
    INNER JOIN Role ON Appear.roleID = Role.roleID
    INNER JOIN Person ON Appear.personID = Person.personID
WHERE RoleKorName = '배우' AND Title IN (
SELECT Title
FROM
	Movie INNER JOIN Appear ON Movie.movieID = appear.movieID
	INNER JOIN Role ON Appear.roleID = Role.roleID
	INNER JOIN Person ON Appear.personID = Person.personID
WHERE RoleKorName = '감독' AND KoreanName = '제임스 카메론');


-- 38. '월드 디즈니'가 수상한 아카데미상의 종류를 고르세요
SELECT DISTINCT SectorKorName
FROM
	Person INNER JOIN Appear ON Person.personID = appear.personID
    INNER JOIN AwardInvolve ON Appear.appearID = AwardInvolve.appearID
    INNER JOIN Sector ON AwardInvolve.sectorID = Sector.sectorID
    INNER JOIN Winning ON AwardInvolve.winningID = Winning.winningID
WHERE KoreanName = '월트 디즈니' AND WinOrNot = 'Winner';

    
-- 39. 장르별 영화의 제작비 평균을 구하세요.
SELECT GenreKorName, AVG(Budget) AS '제작비 평균'
FROM
	Movie INNER JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID
    INNER JOIN Genre ON MovieGenre.GenreID = Genre.GenreID
Group by GenreKorName;
    
-- 40. 장르별 영화의 제작비 대비 수익률을 구하세요.
SELECT GenreKorName, SUM(BoxOfficeWWGross - Budget) / SUM(Budget) * 100 AS '수익률'
FROM
	Movie INNER JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID
    INNER JOIN Genre ON MovieGenre.GenreID = Genre.GenreID
Group by GenreKorName;

