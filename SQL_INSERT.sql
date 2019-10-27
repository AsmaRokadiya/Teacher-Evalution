CREATE TABLE Persons (
  PID int NOT NULL primary key,
  fname varchar(255),
  lname varchar(255),
  email varchar(255),
  phone int,
  address varchar(255),
  postal varchar(255)
);

create table Teacher
(
PID int not null primary key,
designation varchar(255),
FOREIGN KEY (PID) REFERENCES Persons(PID)
);

create table Student
(
PID int not null primary key,
program varchar(255),
FOREIGN KEY (PID) REFERENCES Persons(PID)
);

create table Subject
(
SID int not null primary key,
sname varchar(255),
description varchar(255),
PIDT int,
FOREIGN KEY (PIDT) REFERENCES Teacher(PID)
);


create table StuSub
(
PIDS int not null,
SIDS int not null,
FOREIGN KEY (PIDS) REFERENCES Student(PID),
FOREIGN KEY (SIDS) REFERENCES Subject(SID),
primary key(PIDS,SIDS)
);

create table Rating
(
RID int not null primary key,
PIDS int not null,
SIDS int not null,
PIDT int not null,
feedback varchar(255),
rating decimal(1,2),
FOREIGN KEY (PIDS) REFERENCES Student(PID),
FOREIGN KEY (SIDS) REFERENCES Subject(SID),
FOREIGN KEY (PIDT) REFERENCES Teacher(PID)

);
