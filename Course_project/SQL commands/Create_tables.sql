CREATE SCHEMA schedule;

CREATE TABLE subjects(
subject_id		INT			PRIMARY KEY AUTO_INCREMENT,
subject_name	VARCHAR(20)	UNIQUE	NOT NULL,
auditorium		INT 		NOT NULL
);

CREATE TABLE teachers(
teacher_id		INT 		PRIMARY KEY AUTO_INCREMENT,
teacher_name	VARCHAR(50)	NOT NULL
);

CREATE TABLE the_schedule(
teacher_id			INT 	NOT NULL,
subject_id			INT 	NOT NULL,
day_of_week			VARCHAR(15)	NOT NULL,
sequence_number		INT		NOT NULL,
classes_per_week	INT 	NOT NULL,
students_amount		INT,
CONSTRAINT	fk_teacher_id
FOREIGN KEY(teacher_id)
REFERENCES teachers(teacher_id)
ON DELETE CASCADE,
CONSTRAINT fk_subject_id
FOREIGN KEY(subject_id)
REFERENCES subjects(subject_id)
ON DELETE CASCADE
);