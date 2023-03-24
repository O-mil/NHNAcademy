-- Quiz #1

use School;

CREATE TABLE Student (	-- 학생
StudentNo int,
StudentName char(20),
Major char(20),
Age int);

CREATE TABLE Lecture (	-- 과목
LectureName char(20),
Classtime time,
Classroom char(20),
ProfNo int);

CREATE TABLE Course (	-- 수강
StudentNo int,
LectureName char(20));

CREATE TABLE Prof (	-- 교수
ProfNo int,
ProfName char(20),
MajorNo int);

INSERT INTO Student VALUES
	(1, '김떙떙', '컴공3학년', 20),
    (2, '이떙떙', '정통2학년', 21),
    (3, '박떙떙', '전자1학년', 22),
    (4, '오떙떙', '컴공3학년', 20),
    (5, '윤떙떙', '컴공4학년', 23),
    (6, '유떙떙', '정통2학년', 24);
    
INSERT INTO Lecture VALUES
	('소켓', '10:00', '101', 1),
    ('SQL', '14:00', '102', 2),
    ('JAVA', '11:00', '103', 3),
    ('PYTHON', '15:00', '101', 1);
    
INSERT INTO Course VALUES
	(1, '소켓'),
    (1, 'JAVA'),
    (3, 'PYTHON'),
    (4, 'SQL'),
    (5, 'JAVA'),
    (6, 'JAVA');
    
INSERT INTO Prof VALUES
	(1, '변재영', 1),
    (2, '김영식', 2),
    (3, '최동유', 3);

INSERT INTO Prof VALUES (4, '이순신', 1);
INSERT INTO Lecture VALUES ('배 만들기', '16:00', '105', 4);
INSERT INTO Course VALUES
	(1, '배 만들기'),
    (3, '배 만들기');
    

-- A
SELECT Student.StudentName
FROM Student, Course, Lecture, Prof
WHERE Student.StudentNo = Course.StudentNo
	AND Course.LectureName = Lecture.LectureName
    AND Lecture.ProfNo = Prof.ProfNo
    AND Prof.ProfName = '이순신'
    AND Student.Major LIKE '%3학년%';
    
-- B
SELECT MAX(Student.Age)
FROM Student, Course, Lecture, Prof
WHERE Student.StudentNo = Course.StudentNo
	AND Course.LectureName = Lecture.LectureName
    AND (Student.Major LIKE '역사' OR Lecture.ProfNo IN (
    SELECT ProfNo FROM Prof 
    WHERE ProfName = '이순신'));

-- C
SELECT DISTINCT Lecture.LectureName
FROM Lecture, Course
WHERE Lecture.LectureName = Course.LectureName
  AND (Lecture.Classroom = '1103' OR Lecture.LectureName IN (
    SELECT LectureName FROM Course
    GROUP BY LectureName
    HAVING COUNT(*) >= 5));
    
    
-- D
SELECT DISTINCT Student.StudentName
FROM Student, Course AS c1, Course AS c2, Lecture AS l1, Lecture AS l2
WHERE Student.StudentNo = Course1.StudentNo
  AND Student.StudentNo = Course2.StudentNo
  AND c1.LectureName = l1.LectureName
  AND c2.LectureName = l2.LectureName
  AND c1.LectureName < c2.LectureName
  AND l1.Classtime = l2.Classtime;
  
-- E
 SELECT DISTINCT Prof.ProfName
 FROM Prof
 INNER JOIN Course ON Prof.ProfNo = Course.ProfNo
 INNER JOIN Classroom ON Lecture.Classroom = Classroom.ClassroomNo;
 
-- F
SELECT DISTINCT Prof.ProfName
FROM Prof, Lecture
WHERE Prof.ProfNo = Lecture.ProfNo
  AND Lecture.LectureName IN (
    SELECT LectureName
    FROM Course
    GROUP BY LectureName
    HAVING COUNT(*) < 5);
    
    
-- G
SELECT Rate, AVG(AGE) AS avgAge
FROM (
  SELECT StudentName, Age, 
    CASE 
      WHEN Age >= 20 AND Age <= 23 THEN 'A'
      WHEN Age >= 24 AND Age <= 26 THEN 'B'
      WHEN Age >= 27 AND Age <= 29 THEN 'C'
      ELSE 'D'
    END AS Rate
  FROM Student
) AS StudentRate
GROUP BY Rate;


-- H
SELECT CASE 
	WHEN Age >= 20 AND Age <= 23 THEN 'A'
	WHEN Age >= 24 AND Age <= 26 THEN 'B'
	WHEN Age >= 27 AND Age <= 29 THEN 'C'
	ELSE 'D'
    END AS Rate,
    AVG(Age) AS avgAge
FROM Student
WHERE Grade != 3
GROUP BY Rate;
    

-- I
SELECT Student.StudentName
FROM Student
INNER JOIN (
	SELECT Course.StudentNo, COUNT(*) AS CourseNum
	FROM Course
	GROUP BY Course.StudentNo
	HAVING COUNT(*) = (
		SELECT MAX(CourseNum) 
		FROM (
			SELECT StudentNo, COUNT(*) AS CourseNum
			FROM Course
			GROUP BY StudentNo
		) AS temp
		)
) AS temp2 ON Student.StudentNo = temp2.StudentNo;


-- J
SELECT s.StudentName
FROM Student s LEFT OUTER JOIN Course c ON s.StudentNo = c.StudentNo
WHERE c.StudentNo IS NULL;

-- K
SELECT s.Age, MAX(Grade) AS MaxGrade
FROM Student s LEFT JOIN Course c ON s.StudentNo = c.StudentNo
LEFT JOIN Lecture l ON c.LectureName = l.LectureName
GROUP BY s.Age
