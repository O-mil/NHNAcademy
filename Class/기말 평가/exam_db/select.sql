-- 쿠엔틴 타란티노의 정보 조회
SELECT * FROM `people`
WHERE people_name = 'Quentin Tarantino';

-- 쿠엔틴 타란티노의 작품 조회
SELECT m.movie_name, m.movie_kor_name, r.role_name FROM `movie` AS m
INNER JOIN `movie_cast` AS mc ON m.movie_id = mc.movie_id
INNER JOIN `people` AS p ON mc.people_id = p.people_id
INNER JOIN `role` AS r ON mc.role_id = r.role_id
WHERE people_name = 'Quentin Tarantino';


-- 영화 출연자 조회
SELECT p.people_name, r.role_name FROM people AS p
INNER JOIN movie_cast AS mc ON p.people_id = mc.people_id
INNER JOIN movie AS m ON mc.movie_id = m.movie_id
INNER JOIN role AS r ON mc.role_id = r.role_id
WHERE movie_name = 'Once Upon a Time... in Hollywood';