-- Create Database 김화정_Board;
use 김화정_Board;

drop table IF EXISTS View;
drop table IF EXISTS Comment;
drop table IF EXISTS Post;
drop table IF EXISTS Category;
drop table IF EXISTS User;


Create Table User (
userId VARCHAR(20) NOT NULL PRIMARY KEY,
userPassword VARCHAR(50) NOT NULL
);


Create Table Category (
categoryId INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT: 자동증가
categoryName VARCHAR(50) NOT NULL
);

Create Table Post (
postId INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(50) NOT NULL,
content TEXT NOT NULL,
createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
categoryId INT NOT NULL,
userId VARCHAR(20) NOT NULL,
views INT DEFAULT 0,
CommentId INT NOT NULL,
FOREIGN KEY(userId) REFERENCES User(userId),
FOREIGN KEY(categoryId) REFERENCES Category(categoryId)
);


Create Table Comment (
commentId INT PRIMARY KEY AUTO_INCREMENT,
content TEXT NOT NULL,
createdAt DATETIME NOT NULL,
userId VARCHAR(20) NOT NULL,
postId INT NOT NULL,
parentCommentId INT,
FOREIGN KEY (userId) REFERENCES User(userId),
FOREIGN KEY (postId) REFERENCES Post(postId)
);


Create Table View (
postId INT PRIMARY KEY,
title VARCHAR(50) NOT NULL,
content TEXT NOT NULL, 
categoryId INT NOT NULL,
createdAt DATETIME NOT NULL,
userId VARCHAR(20) NOT NULL,
views INT,
FOREIGN KEY (postId) REFERENCES Post(postId),
FOREIGN KEY (userId) REFERENCES User(userId),
FOREIGN KEY (categoryId) REFERENCES Category(categoryId)
);