
create table if not exists Users (
                       user_id varchar(255) not null comment '유저 아이디' primary key,
                       user_pw varchar(255) not null comment '유저 비밀번호',
                       user_name varchar(255) not null comment '유저 이름',
                       filename varchar(500) comment '유저 프로필'
);

merge into Users key(user_id) values ('admin', '1234', '관리자', 'null');
merge into Users key(user_id) values ('user1', '1234', '유저1', 'null');
merge into Users key(user_id) values ('user2', '1234', '유저2','null');
merge into Users key(user_id) values ('user3', '1234', '유저3','null');
merge into Users key(user_id) values ('user4', '1234', '유저4','null');
merge into Users key(user_id) values ('user5', '1234', '유저5','null');
merge into Users key(user_id) values ('user6', '1234', '유저6','null');
merge into Users key(user_id) values ('user7', '1234', '유저7','null');
merge into Users key(user_id) values ('user8', '1234', '유저8','null');
merge into Users key(user_id) values ('user9', '1234', '유저9','null');
merge into Users key(user_id) values ('user10', '1234', '유저10','null');
merge into Users key(user_id) values ('user11', '1234', '유저11','null');

create table if not exists Posts (
                     post_id Long not null comment '게시글 번호' primary key,
                     title varchar(255) not null comment '게시글 제목',
                     content varchar(1000) not null comment '게시글 본문',
                     writer_id varchar(255),
                     view_count int,
                     foreign key(writer_id) references Users(user_id)
);

merge into Posts key(post_id) values (1, '가입인사', '안녕하세요', 'user1', 0);


