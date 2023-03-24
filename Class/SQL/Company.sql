-- Quiz #3

CREATE TABLE Employee (
EmpID INTEGER PRIMARY KEY,
EmpName VARCHAR(255) NOT NULL,
Age INTEGER,
Salary FLOAT
);

CREATE TABLE Works (
EmpID INTEGER,
DepartID INTEGER,
Percentage INTEGER,
PRIMARY KEY(EmpID, DepartID),
FOREIGN KEY(EmpID) REFERENCES Employee(EmpID),
FOREIGN KEY(DepartID) REFERENCES Department(DepartID)
);

CREATE TABLE Department (
DepartID INTEGER PRIMARY KEY,
Budget FLOAT,
ManagerID INTEGER,
FOREIGN KEY(ManagerID) REFERENCES Employee(EmpID)
);


-- A
SELECT e.EmpName, e.Age 
FROM Employees e 
INNER JOIN Works w1 ON e.EmpID = w1.EmpID AND w1.DepartID = 1 -- 하드웨어 부서
INNER JOIN Works w2 ON e.EmpID = w2.EmpID AND w2.DepartID = 2; -- 소프트웨어 부서


-- B
SELECT w.DepartID, COUNT(*) AS CntEmp
FROM Works w
INNER JOIN Employees e ON w.EmpID = e.EmpID
WHERE e.Salary >= 20000 -- 풀타임 직원의 급여가 20000 이상인 경우
GROUP BY w.DepartID
HAVING COUNT(*) >= 20; -- 풀타임 직원이 20명 이상인 경우


-- C (자신 = 홍길동)
SELECT e.EmpName
FROM Employees e
INNER JOIN Works w ON e.EmpID = w.EmpID
INNER JOIN Departments d ON w.DepartID = d.DepartID
WHERE e.Salary > d.Budget
AND w.DepartID = (
  SELECT DepartID
  FROM Works
  INNER JOIN Employees ON Works.EmpID = Employees.EmpID
  WHERE Employees.EmpName = '홍길동');


-- D
SELECT e.EmpName
FROM Employees e
INNER JOIN Departments d ON e.EmpID = d.ManagerID
WHERE d.Budget > 100000000;


-- E
SELECT d.ManagerID
FROM Departments d
GROUP BY d.ManagerID
HAVING SUM(d.Budget) >= 50000000;


-- F
SELECT d.ManagerID
FROM Departments d
GROUP BY d.ManagerID
ORDER BY SUM(d.budget) DESC
LIMIT 1;


-- G
ALTER TABLE Employees
ADD CONSTRAINT Salary_check CHECK (Salary >= 50000000);


-- H
ALTER TABLE Departments
ADD CONSTRAINT Manager_Age_check CHECK (Manager_Age >= 30);


-- I (자신 = 홍길동)
DELETE FROM Employee
WHERE EmpID IN (
    SELECT EmpID
    FROM Works
    WHERE DepartID IN (
        SELECT DepartID
        FROM Department
        WHERE ManagerID = (
            SELECT EmpID
            FROM Employee
            WHERE EmpName = '홍길동')
            AND Budget < ALL (
				SELECT Salary
				FROM Employee
				WHERE EmpID IN (
					SELECT EmpID
					FROM Works
					WHERE DepartID = Department.DepartID))));