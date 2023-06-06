create table IF NOT EXISTS odontologos(id int auto_increment primary key, nombre varchar(255), apellido varchar (255), matricula int);
create table IF NOT EXISTS administradores(id int auto_increment primary key, nombre varchar(255), apellido varchar (255), domicilio_id int, dni int, fechaAlta Date);
create table IF NOT EXISTS pacientes(id int auto_increment primary key, nombre varchar(255), apellido varchar (255), domicilio_id int, dni int, fechaAlta TIMESTAMP WITHOUT TIME ZONE);
create table IF NOT EXISTS domicilios(id int auto_increment primary key, calle varchar(255), numero int, localidad varchar(255), provincia varchar(255));
create table IF NOT EXISTS turnos(id int auto_increment primary key, paciente_id int, odontologo_id int, fechaTurno TIMESTAMP);