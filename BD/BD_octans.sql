//Creando BD

CREATE SCHEMA `octans` DEFAULT CHARACTER SET utf8 ;

//TAblas

CREATE TABLE `octans`.`rol` (
  `id_rol` INT NOT NULL AUTO_INCREMENT,
 
 `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`id_rol`));

CREATE TABLE `octans`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  
`nombre` VARCHAR(45) NOT NULL,
  `activo` CHAR(1) NOT NULL,
  `id_rol` INT NULL,
  
PRIMARY KEY (`id_usuario`),
   INDEX `id_rol_id_rolusuario_idx` (`id_rol` ASC) VISIBLE,
  CONSTRAINT `id_rol_id_rolusuario`
    FOREIGN KEY (`id_rol`)
    REFERENCES `octans`.`rol` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


//iNSERTANDO ROLES.

INSERT INTO `octans`.`rol` (`nombre`) VALUES ('ADMINISTRADOR'), ('AUDITOR'), ('AUXILIAR');
