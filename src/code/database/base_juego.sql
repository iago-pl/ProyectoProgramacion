DROP DATABASE IF EXISTS match_it;
CREATE DATABASE IF NOT EXISTS match_it;

USE match_it;

/*                     */

DROP TABLE IF EXISTS jugadores;
CREATE TABLE IF NOT EXISTS jugadores (
    id_jugador INT UNSIGNED AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(8) NOT NULL,
    numero_pasos_total INT UNSIGNED NULL,
    PRIMARY KEY (id_jugador),
    UNIQUE INDEX ak_nombre (nombre)
);


DROP TABLE IF EXISTS niveles;
CREATE TABLE IF NOT EXISTS niveles(
    id_nivel int unsigned auto_increment not null,
    codigo_nivel varchar(32) not null,
    primary key (id_nivel),
    unique index ak_codigo_nivel(codigo_nivel)

);

DROP TABLE IF EXISTS niveles_jugadores;
CREATE TABLE IF NOT EXISTS niveles_jugadores (
    id_jugador INT UNSIGNED NOT NULL,
    id_nivel INT UNSIGNED NOT NULL,
    numero_pasos INT UNSIGNED NOT NULL,
    fecha_completado datetime not null,
    PRIMARY KEY (id_jugador , id_nivel),
    FOREIGN KEY (id_jugador)
        REFERENCES jugadores (id_jugador)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_nivel)
        REFERENCES niveles (id_nivel)
        ON DELETE CASCADE ON UPDATE CASCADE
);


DELIMITER $$
DROP PROCEDURE IF EXISTS introducir_mapa$$
CREATE PROCEDURE introducir_mapa (in codigo varchar(32))
MODIFIES SQL DATA
BEGIN
	DECLARE MENSAJE VARCHAR(128);
    declare id_nivel_in int unsigned;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
		BEGIN
			SET MENSAJE= CONCAT( ' ERROR AL  HACER LA INSERRCIÓN   DE: \t',codigo,'\n'); 
				SELECT MENSAJE;
		END;  
	
	select id_nivel into id_nivel_in
    from niveles where codigo_nivel = codigo;
    
    if id_nivel_in is null
		then
			INSERT INTO niveles (codigo_nivel) VALUES (codigo);
	END IF;
        
    
END$$

DROP PROCEDURE IF EXISTS introducir_jugador$$
CREATE PROCEDURE introducir_jugador (in nombre_in varchar(8))
MODIFIES SQL DATA
BEGIN
	DECLARE MENSAJE VARCHAR(128);
    declare id_jugador_in int unsigned;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
		BEGIN
			SET MENSAJE= CONCAT( ' ERROR AL  HACER LA INSERRCIÓN   DE: \t', nombre_in,'\n'); 
				SELECT MENSAJE;
		END;
        
	select id_jugador into id_jugador_in
    from jugadores where nombre = nombre_in;
    
    if id_jugador_in is null
		then
			INSERT INTO jugadores (nombre, numero_pasos_total) VALUES (nombre_in, 0);
	END IF;
        
    
END$$

DROP PROCEDURE IF EXISTS introducir_nivel_completado$$
CREATE PROCEDURE introducir_nivel_completado (in nombre_in varchar(8), in codigo varchar(32), in pasos int)
MODIFIES SQL DATA
BEGIN
	DECLARE MENSAJE VARCHAR(128);
    declare id_jugador_in int unsigned;
    declare id_nivel_in int unsigned;
    declare pasos_actuales int unsigned;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
		BEGIN
			SET MENSAJE= CONCAT( ' ERROR AL  HACER LA INSERRCIÓN   DE: \t', nombre_in,'\n'); 
				SELECT MENSAJE;
		END;
        
	select id_jugador into id_jugador_in
    from jugadores where nombre = nombre_in;
	select id_nivel into id_nivel_in
    from niveles where codigo_nivel = codigo;
    
    select numero_pasos into pasos_actuales
	from niveles_jugadores
    where id_jugador = id_jugador_in and id_nivel = id_nivel_in;
    
    if pasos_actuales is not null
		then
			if pasos < pasos_actuales and pasos > 0
				then
					delete from niveles_jugadores where id_jugador = id_jugador_in and id_nivel = id_nivel_in;
					INSERT INTO niveles_jugadores (id_jugador, id_nivel, numero_pasos, fecha_completado) VALUES (id_jugador_in, id_nivel_in, pasos, current_timestamp());					
			END IF;
		else	
			INSERT INTO niveles_jugadores (id_jugador, id_nivel, numero_pasos, fecha_completado) VALUES (id_jugador_in, id_nivel_in, pasos, current_timestamp());	
	END IF;
    
    
    update jugadores
		set numero_pasos_total = numero_pasos_total + pasos
    where id_jugador = id_jugador_in;
    
END$$