drop table student_course;
drop table student;
drop table course;

create table course(
    id int not null,
    name varchar(255) not null,
    primary key(id)
);

create table student(
    id int not null,
    name varchar(255) not null,
    primary key(id)
);

create table student_course(
    student_id int not null,
    course_id int not null,
    foreign key(student_id) references student(id),
    foreign key(course_id) references course(id)
);