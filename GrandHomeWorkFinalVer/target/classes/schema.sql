create table course(
                       id serial primary key,
                       name varchar not null,
                       date_start varchar not null,
                       date_end varchar not null
);

create table lesson(
                       lesson_id serial primary key,
                       name varchar not null,
                       day_week varchar not null,
                       time varchar not null,
                       id_course integer,
                       foreign key (id_course) references course(id)
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
