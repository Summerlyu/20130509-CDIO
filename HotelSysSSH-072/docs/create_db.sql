create user hotelmgr identified by abc123;
grant connect,resource to hotelmgr;


conn hotelmgr/abc123;

create table hotel(
   hotel_no   int primary key,
   hotel_name varchar2(30) not null,
   hotel_addr varchar2(50) not null,
   hotel_phone varchar2(30),
   hotel_room_count  int default 0,
   hotel_pic  blob
);

create sequence seq_hotel;

create table room(
   room_id     int primary key,
   room_no     varchar2(10) not null,  --房号
   room_type   char(1) not null,
   room_equip  varchar2(20) not null,
   room_status char(1) not null,
   room_memo   varchar2(200),
   hotel_no    int not null,
   constraint FK_ROOM_HOTEL FOREIGN KEY (hotel_no) references hotel,
   constraint UK_HOTEL_ROOM_NO UNIQUE (hotel_no,room_no)
);

create sequence seq_room;

create table users(
   user_no    char(6) primary key,
   user_pwd   char(6) not null,
   user_name  varchar2(30) not null
);

insert into users values('000101','123456','张清山');
