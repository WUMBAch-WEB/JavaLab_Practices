insert into course (name, date_start, date_end)
values ('Начальная математика', '01.09.2021', '30.05.2022');
insert into course (name, date_start, date_end)
values ('ToadLab', '01.09.2021', '30.05.2022');
insert into course (name, date_start, date_end)
values ('BestPractices', '15.07.2021', '29.07.2021');
insert into course (name, date_start, date_end)
values ('How to read manga', '01.08.2021', '31.08.2021');

insert into lesson(name, day_week, time, id_course)
values ('Сколько будет 2 + 2?', 'Monday', '14:00', 1);
insert into lesson(name, day_week, time, id_course)
values ('Учимся делить столбиком', 'Tuesday', '15:00', 1);
insert into lesson(name, day_week, time, id_course)
values ('Изучаем треугольники', 'Wednesday', '16:00', 1);
insert into lesson(name, day_week, time, id_course)
values ('JDBC', 'Monday', '11:00', 3);
insert into lesson(name, day_week, time, id_course)
values ('Изучаем SQL', 'Monday', '17:00', 2);
insert into lesson(name, day_week, time, id_course)
values ('Разбираемс с DB', 'Thursday', '20:00', 2);
insert into lesson(name, day_week, time, id_course)
values ('Какой-нибудь урок', 'Friday', '40:00', 4);

insert into teacher(first_name, last_name, experience)
values ('Марсель', 'Сидиков', 'Тысячилетие');
insert into teacher(first_name, last_name, experience)
values ('Марат', 'Арсланов', '100 лет');
insert into teacher(first_name, last_name, experience)
values ('Марсель', 'Сидиков', 'Тысячилетие');
insert into teacher(first_name, last_name, experience)
values ('Наруто', 'Узумаки', '25 лет');

insert into student(first_name, last_name, group_number)
values ('Александр', 'Кузнецов', '11-005');
insert into student(first_name, last_name, group_number)
values ('Карина', 'Обшарова', '11-005');
insert into student(first_name, last_name, group_number)
values ('Байгулова', 'Эльмира', '11-005');
insert into student(first_name, last_name, group_number)
values ('Калугин', 'Артем', '11-004');


insert into courses_students_relation(student_id, course_id)
values (1,1);
insert into courses_students_relation(student_id, course_id)
values (1,2);
insert into courses_students_relation(student_id, course_id)
values (1,3);
insert into courses_students_relation(student_id, course_id)
values (1,4);
insert into courses_students_relation(student_id, course_id)
values (2,2);
insert into courses_students_relation(student_id, course_id)
values (2,1);
insert into courses_students_relation(student_id, course_id)
values (3,3);
insert into courses_students_relation(student_id, course_id)
values (4,2);

insert into courses_teachers_relation(teacher_id, course_id)
values (1,2);
insert into courses_teachers_relation(teacher_id, course_id)
values (2,1);
insert into courses_teachers_relation(teacher_id, course_id)
values (3,3);
insert into courses_teachers_relation(teacher_id, course_id)
values (4,4);