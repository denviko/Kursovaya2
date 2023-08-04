alter table student
    add constraint age check ( age >= 16 );

alter table student
    alter column name set not null,
    add constraint name_unique unique (name);

alter table faculty
    add constraint name_color_unique unique (name, color);

alter table student
    alter column age set default 20;
create table people(
                       id real,
                       name text primary key ,
                       age integer,
                       drivers_license boolean,
                       car_id text references car(id)

);

create table car(
                    id text primary key ,
                    brand text,
                    model text,
                    cash integer
);

select name, age, drivers_license, brand, model, cash
from people
         inner join car c on c.id = people.car_id;


select student.name, student.age, f.name
from student
         inner join faculty f on f.id = student."faculty id";

select name
from avatar
         inner join student s on s.id = avatar.student_id