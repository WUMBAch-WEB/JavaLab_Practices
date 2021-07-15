create table course(
  id serial primary key,
  name varchar not null,
  date_start varchar not null,
  date_end varchar not null,
  unique (name)
);

create table lesson(
  lesson_id serial primary key,
  name varchar not null,
  day_week varchar not null,
  time varchar not null
);

create table teacher(
  teacher_id serial primary key,
  first_name varchar,
  last_name varchar,
  experience varchar
);

create table student(
  student_id serial primary key ,
  first_name varchar,
  last_name varchar,
  group_number varchar
);

create table courses_students_relation(
    student_id integer,
    course_id integer,
    foreign key (student_id) references student(student_id),
    foreign key (course_id) references course(id)
);

create table courses_teachers_relation(
    teacher_id integer,
    course_id integer,
    foreign key (teacher_id) references teacher(teacher_id),
    foreign key (course_id) references course(id)
);

create table courses_lessons_relation(
  lesson_id integer,
  course_id integer,
  foreign key (lesson_id) references lesson(lesson_id),
  foreign key (course_id) references course(id)
);

create table account(
    account_id serial primary key,
    nickname varchar,
    password varchar,
    status varchar
);

create table accounts_students_relation(
    account_id integer,
    student_id integer,
    foreign key (account_id) references account(account_id),
    foreign key (student_id) references student(student_id)
);

create table accounts_teachers_relation(
    account_id integer,
    teacher_id integer,
    foreign key (account_id) references account(account_id),
    foreign key (teacher_id) references teacher(teacher_id)
);

drop table courses_teachers_relation;
drop table courses_students_relation;
drop table lesson;
drop table student;
drop table teacher;
drop table course;
drop table accounts_students_relation;
drop table accounts_teachers_relation;
drop table account;