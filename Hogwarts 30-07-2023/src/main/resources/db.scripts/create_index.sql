- liquibase formatted sql

- changeset denis.gorozhankin:1
CREATE INDEX student_name_idx ON student (name);

- changeset denis.gorozhankin:2
CREATE INDEX faculty_color_name_idx ON faculty (name,color);
