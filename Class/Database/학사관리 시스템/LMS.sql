-- DROP DATABASE LMS;
-- Create Database LMS;
use LMS;

Drop Table If exists Enrollments;
Drop Table If exists Guardians;
Drop Table If exists Student;
Drop Table If exists Lectures;
Drop Table If exists Classroom;
Drop Table If exists Professor;
Drop Table If exists Major;


-- 학과
Create Table Major (
majorId VARCHAR(20) NOT NULL PRIMARY KEY,
majorName VARCHAR(50) NOT NULL,
office VARCHAR(50) NOT NULL,
majorYear VARCHAR(10) NOT NULL	-- 학과 설립 연도
);

-- 교수
Create Table Professor (
profId VARCHAR(20) NOT NULL PRIMARY KEY,
profName VARCHAR(10) NOT NULL,
phone VARCHAR(15) NOT NULL,
email VARCHAR(50) NOT NULL,
majorId VARCHAR(50) NOT NULL,
lab VARCHAR(50) NOT NULL,
lecture VARCHAR(50) NOT NULL,
FOREIGN KEY (majorId) REFERENCES Major(majorId)
);


-- 강의실
Create Table Classroom (
lectureId VARCHAR(50) NOT NULL PRIMARY KEY,
lectureroom VARCHAR(50) NOT NULL,
lotation VARCHAR(50) NOT NULL,
building VARCHAR(50) NOT NULL
);


-- 강의
Create Table Lectures (
lectureId VARCHAR(50) NOT NULL PRIMARY KEY,
lectureName VARCHAR(50) NOT NULL,
profId VARCHAR(20) NOT NULL,
lecturePlan TEXT NOT NULL,
credit INT NOT NULL,		-- 학점
FOREIGN KEY (profId) REFERENCES Professor(profId),
FOREIGN KEY (lectureId) REFERENCES Classroom(lectureId)
);

-- 수강정보
Create Table Enrollments (
lectureId VARCHAR(50) NOT NULL PRIMARY KEY,
studentId VARCHAR(20) NOT NULL,
cancel CHAR(1),
grade INT,
FOREIGN KEY (lectureId) REFERENCES Lectures(lectureId)
);


-- 학생
Create Table Student (
studentId VARCHAR(20) NOT NULL PRIMARY KEY,
StudnetName VARCHAR(10) NOT NULL,
birthdate VARCHAR(10) NOT NULL,
gender CHAR(1) NOT NULL,
majorId VARCHAR(50) NOT NULL,
phone VARCHAR(15) NOT NULL,
email VARCHAR(50) NOT NULL,
profId VARCHAR(10) NOT NULL,	-- 지도 교수
lectureId VARCHAR(50),
FOREIGN KEY (profId) REFERENCES Professor(profId),
FOREIGN KEY (majorId) REFERENCES Major(majorId),
FOREIGN KEY (lectureId) REFERENCES Lectures(lectureId)
);

-- 보호자
Create Table Guardians (
studentId VARCHAR(20) NOT NULL PRIMARY KEY,
birthplace VARCHAR(50),	-- 출생지
guardian VARCHAR(10),	-- 보호자
FOREIGN KEY (studentId) REFERENCES Student(studentId)
);




