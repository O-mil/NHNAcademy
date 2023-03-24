-- Quiz #2

-- A
SELECT DISTINCT Part.PartName 
FROM Catalog
INNER JOIN Part ON Catalog.PartID = Part.PartID;


-- B
SELECT Supplier.SupplierName
FROM Supplier
WHERE NOT EXISTS (
    SELECT Part.PartID
    FROM Part
    WHERE NOT EXISTS (
        SELECT Catalog.SupplierID
        FROM Catalog
        WHERE Catalog.PartID = Part.PartID
        AND Catalog.SupplierID = Supplier.SupplierID));

        
-- C
SELECT DISTINCT Supplier.SupplierName 
FROM Supplier
INNER JOIN Catalog ON Supplier.SupplierID = Catalog.SupplierID
INNER JOIN Part ON Catalog.PartID = Part.PartID
WHERE Part.Color = '적색';

-- D
SELECT DISTINCT Part.PartName 
FROM Catalog
INNER JOIN Part ON Catalog.PartID = Part.PartID
WHERE Catalog.SupplierID = (
    SELECT SupplierID
    FROM Supplier
    WHERE SupplierName = '홍길동');
    

-- E
SELECT DISTINCT Supplier.SupplierName
FROM Catalog
INNER JOIN Supplier ON Catalog.SupplierID = Supplier.SupplierID
WHERE Catalog.PartID IN (
    SELECT PartID 
    FROM Catalog
    GROUP BY PartID
    HAVING AVG(Price) < (
        SELECT AVG(Price) 
        FROM Catalog))
AND Catalog.Price > (
    SELECT AVG(Price) 
    FROM Catalog);
    

-- F
SELECT SupplierName 
FROM Supplier, Catalog 
WHERE Supplier.SupplierID = Catalog.SupplierID 
AND Catalog.UnitPrice = (
    SELECT MAX(UnitPrice) 
    FROM Catalog 
    WHERE PartID = Catalog.PartID) 
GROUP BY Catalog.PartID;



-- G
SELECT DISTINCT Supplier.SupplierName
FROM Supplier
INNER JOIN Catalog ON Supplier.SupplierID = Catalog.SupplierID
INNER JOIN Part ON Catalog.PartID = Part.PartID
WHERE Part.Color = '적색';


-- H
SELECT Supplier.SupplierName
FROM Supplier
INNER JOIN Catalog ON Supplier.SupplierID = Catalog.SupplierID
INNER JOIN Part ON Catalog.PartID = Part.PartID
WHERE Part.Color IN ('적색', '녹색')
GROUP BY Supplier.SupplierName
HAVING COUNT(DISTINCT Part.Color) = 2;


-- I
SELECT SupplierName 
FROM Supplier 
WHERE SupplierID IN (
    SELECT SupplierID 
    FROM Catalog 
    WHERE PartID IN (
        SELECT PartID 
        FROM Part 
        WHERE Color = '적색' OR Color = '녹색'));

