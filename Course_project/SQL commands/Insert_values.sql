INSERT teachers(teacher_name) VALUES('Олексюк Олена Михайлівна');
INSERT teachers(teacher_name) VALUES('Гандзюк Олексій Степанович');
INSERT teachers(teacher_name) VALUES('Фіївська Наталя Олександрівна');
INSERT teachers(teacher_name) VALUES('Макаренко Микола Миколайович');
INSERT teachers(teacher_name) VALUES('Стерненко Сергій Вячеславович');
INSERT teachers(teacher_name) VALUES('Щербина Олена Миколаївна');
INSERT teachers(teacher_name) VALUES('Позенко Олег Іванович');
INSERT teachers(teacher_name) VALUES('Батурин Євген Андрійович');
INSERT teachers(teacher_name) VALUES('Моринець Анастасія Миколаївна');

INSERT subjects(subject_name, auditorium) VALUES('Математика', 215);
INSERT subjects(subject_name, auditorium) VALUES('Фізика', 223);
INSERT subjects(subject_name, auditorium) VALUES('Географія', 103);
INSERT subjects(subject_name, auditorium) VALUES('Українська мова', 432);
INSERT subjects(subject_name, auditorium) VALUES('Історія України', 214);
INSERT subjects(subject_name, auditorium) VALUES('Зарубіжна література', 254);
INSERT subjects(subject_name, auditorium) VALUES('Економіка', 322);
INSERT subjects(subject_name, auditorium) VALUES('Хімія', 217);
INSERT subjects(subject_name, auditorium) VALUES('Біологія', 154);
INSERT subjects(subject_name, auditorium) VALUES('Інформатика', 125);
INSERT subjects(subject_name, auditorium) VALUES('Українська література', 365);
INSERT subjects(subject_name, auditorium) VALUES('Всесвітня історія', 214);

INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(1, 1, 'Понеділок', 1, 2, 20);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(2, 2, 'Понеділок', 2, 1, 23);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(1, 3, 'Понеділок', 3, 2, 22);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(1, 1, 'Понеділок', 4, 2, 22);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(3, 4, 'Вівторок', 1, 1, 20);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(4, 5, 'Вівторок', 2, 3, 24);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(4, 6, 'Вівторок', 3, 2, 21);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(3, 12, 'Вівторок', 4, 2, 25);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(1, 3, 'Вівторок', 5, 3, 22);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(5, 7, 'Середа', 1, 1, 24);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(6, 8, 'Середа', 2, 2, 22);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(1, 3, 'Середа', 3, 2, 20);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(2, 2, 'Середа', 4, 2, 20);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(3, 4, 'Середа', 5, 2, 23);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(7, 9, 'Четвер', 1, 1, 24);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(8, 10, 'Четвер', 2, 1, 21);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(9, 4, 'Четвер', 3, 2, 25);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(9, 12, 'Четвер', 4, 2, 25);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(4, 5, "П'ятниця", 1, 2, 22);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(8, 10, "П'ятниця", 2, 2, 23);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(4, 13, "П'ятниця", 3, 2, 20);
INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) 
VALUES(7, 9, "П'ятниця", 4, 2, 22);