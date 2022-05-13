DROP DATABASE IF EXISTS MATCH_IT;
CREATE DATABASE IF NOT EXISTS MATCH_IT;

USE MATCH_IT;


/*                     */

DROP TABLE IF EXISTS jugadores;
CREATE TABLE IF NOT EXISTS jugadores(
	id_jugador int unsigned auto_increment not null,
    nombre varchar(8) not null, 
    numero_pasos_total int unsigned null,
    primary key (id_jugador)
);


DROP TABLE IF EXISTS niveles;
CREATE TABLE IF NOT EXISTS niveles(
	id_nivel int unsigned auto_increment not null,
    nombre_nivel varchar(8) not null,
    primary key (id_nivel)

);

DROP TABLE IF EXISTS niveles_jugadores;
CREATE TABLE IF NOT EXISTS niveles_jugadores(
	id_jugador int unsigned not null,
    id_nivel int unsigned not null,
    numero_pasos int unsigned not null,
    primary key (id_jugador,id_nivel),
	foreign key (id_jugador) references jugadores(id_jugador)
    on delete cascade
    on update cascade,
    foreign key (id_nivel) references niveles(id_nivel)
    on delete cascade
    on update cascade
    
);

INSERT INTO `match_it`.`niveles` (`nombre_nivel`) VALUES ('nivel1');
INSERT INTO `match_it`.`niveles` (`nombre_nivel`) VALUES ('nivel2');

