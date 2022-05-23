DROP DATABASE IF EXISTS match_it;
CREATE DATABASE IF NOT EXISTS match_it;

USE match_it;

/*                     */

DROP TABLE IF EXISTS jugadores;
CREATE TABLE IF NOT EXISTS jugadores(
    id_jugador int unsigned auto_increment not null,
    nombre varchar(8) not null, 
    numero_pasos_total int unsigned null,
    primary key (id_jugador),
    unique index ak_nombre(nombre)
);


DROP TABLE IF EXISTS niveles;
CREATE TABLE IF NOT EXISTS niveles(
    id_nivel int unsigned auto_increment not null,
    codigo_nivel varchar(32) not null,
    primary key (id_nivel),
    unique index ak_codigo_nivel(codigo_nivel)

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


DELIMITER $$
DROP PROCEDURE IF EXISTS introducir_mapa$$
CREATE PROCEDURE introducir_mapa (in codigo varchar(32))
MODIFIES SQL DATA
BEGIN
	DECLARE MENSAJE VARCHAR(128);
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
		BEGIN
			SET MENSAJE= CONCAT( ' ERROR AL  HACER LA INSERRCIÓN   DE: \t',codigo,'\n'); 
				SELECT MENSAJE;
		END;  
        
    INSERT INTO niveles (codigo_nivel) VALUES (codigo);
    
END$$

DROP PROCEDURE IF EXISTS introducir_jugador$$
CREATE PROCEDURE introducir_jugador (in nombre varchar(8))
MODIFIES SQL DATA
BEGIN
	DECLARE MENSAJE VARCHAR(128);
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
		BEGIN
			SET MENSAJE= CONCAT( ' ERROR AL  HACER LA INSERRCIÓN   DE: \t', nombre,'\n'); 
				SELECT MENSAJE;
		END;  
        
    INSERT INTO jugadores (nombre) VALUES (nombre);
    
END$$

DROP PROCEDURE IF EXISTS introducir_nivel_completado$$
CREATE PROCEDURE introducir_nivel_completado (in nombre_in varchar(8), in codigo varchar(32), in pasos int)
MODIFIES SQL DATA
BEGIN
	DECLARE MENSAJE VARCHAR(128);
    declare id_jugador int unsigned;
    declare id_nivel int unsigned;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
		BEGIN
			SET MENSAJE= CONCAT( ' ERROR AL  HACER LA INSERRCIÓN   DE: \t', nombre_in,'\n'); 
				SELECT MENSAJE;
		END;
        
	select id_jugador from jugadores where nombre = nombre_in;
	select id_nivel from niveles where codigo_nivel = codigo;
    
    INSERT INTO niveles_jugadores (id_jugador, id_nivel, numero_pasos) VALUES (id_jugador, id_nivel, pasos);
    
END$$

DROP PROCEDURE IF EXISTS actualizar_pasos$$
CREATE PROCEDURE actualizar_pasos (in nombre varchar(8), in codigo varchar(32), in pasos int)
MODIFIES SQL DATA
BEGIN
	DECLARE MENSAJE VARCHAR(128);
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
		BEGIN
			SET MENSAJE= CONCAT( ' ERROR AL  HACER LA INSERRCIÓN   DE: \t', nombre,'\n'); 
				SELECT MENSAJE;
		END;
	
    INSERT INTO niveles_jugadores (id_jugador, id_nivel, numero_pasos) VALUES (nombre, codigo, pasos);
    
END$$