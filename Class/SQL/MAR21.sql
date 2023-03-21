-- A
-- 외래 키 제약 조건의 예
-- 기본키는 null값이 될 수 없음
-- 외래키는 참조할 수 없는 값을 가질 수 없음
-- 릴레이션에는 최소한 하나의 키가 존재해야 함
-- 특정 속성값은 서로 달라야 함

-- B
CREATE TABLE Staff (
StaffNo INTEGER,
StaffName CHAR(20),
Age INTEGER,
Money double);

CREATE TABLE Work (
StaffNo INTEGER,
DepartNo INTEGER,
WorkTime INTEGER);

CREATE TABLE Depart (
DepartNo Integer,
DepartName CHAR(50),
Budget DOUBLE,
LeaderNo Integer);

desc Work;
desc Staff;
desc Depart;

ALTER TABLE Work ADD CONSTRAINT pk_Staff PRIMARY KEY(StaffNo);
ALTER TABLE Depart ADD CONSTRAINT pk_Depart PRIMARY KEY(DepartNo);
ALTER TABLE Staff ADD CONSTRAINT pk_Staff PRIMARY KEY(StaffNo);

ALTER TABLE Work ADD constraint fk_DepartNo foreign key(DepartNo) REFERENCES Depart(DepartNo);
ALTER TABLE Work ADD constraint fk_StaffNo foreign key(StaffNo) REFERENces Staff(StaffNo);



-- C
ALTER TABLE Depart MODIFY COLUMN LeaderNo Integer NOT NULL;

INSERT INTO Depart(DepartNo, LeaderNo) VALUES(1, 2);
INSERT INTO Depart VALUES(2, "경영팀", 34000.0, 1);
INSERT INTO Depart VALUES(3, "HR", 4500.0, 1);


-- D
UPDATE Staff SET Money = Money * 1.1;

-- E
DELETE FROM Depart WHERE DepartName = 'HR';


