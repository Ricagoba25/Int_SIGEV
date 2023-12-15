-- -----------------------------------------------------
-- Table `SIGEV`.`color`
-- -----------------------------------------------------
INSERT INTO `sigev`.`color` (`nombreColor`, `codigo`)
VALUES
('Gris','#839192'),
('Rojo','#FF0000'),
('Azul','#0000FF'),
('Verde','#00FF00');

-- -----------------------------------------------------
-- Table `SIGEV`.`estado`
-- -----------------------------------------------------
INSERT INTO `sigev`.`estado` (`nombre`)
VALUES
('Aguascalientes'),
('Baja California'),
('Baja California Sur'),
('Campeche'),
('Chiapas'),
('Chihuahua'),
('Coahuila de Zaragoza'),
('Colima'),
('Ciudad de México'),
('Durango'),
('Guanajuato'),
('Guerrero'),
('Hidalgo'),
('Jalisco'),
('México'),
('Michoacán de Ocampo'),
('Morelos'),
('Nayarit'),
('Nuevo León'),
('Oaxaca'),
('Puebla'),
('Querétaro'),
('Quintana Roo'),
('San Luis Potosí'),
('Sinaloa'),
('Sonora'),
('Tabasco'),
('Tamaulipas'),
('Tlaxcala'),
('Veracruz de Ignacio de la Llave'),
('Yucatán'),
('Zacatecas');

-- -----------------------------------------------------
-- Table `SIGEV`.`rol`
-- -----------------------------------------------------
INSERT INTO `sigev`.`rol`(`nombreRol`) 
VALUES 
('Administrador'),('Organización'),('Voluntario');


-- -----------------------------------------------------
-- Table `SIGEV`.`direccion`
-- -----------------------------------------------------
INSERT INTO `sigev`.`direccion`
(`calle`,`colonia`,`municipio`,`noExterior`,`noInterior`,`estado_idEstado`)
VALUES
('Av. Plan de Ayala', 'Vicente Guerrero', 'Cuernavaca', '1005', 'S/N', 17);

-- -----------------------------------------------------
-- Table `SIGEV`.`usuario`
-- -----------------------------------------------------
INSERT INTO `sigev`.`usuario` (`correo`,`contrasena`,`telefono`,`rol_idRol`)
VALUES
('admin_sigev@gmail.com',sha2('1234',256),'7772517026', 1),
('organizacion_sigev@gmail.com',sha2('1234',256),'7772517556', 2),
('voluntario_sigev@gmail.com',sha2('1234',256),'7772514626', 3);

-- -----------------------------------------------------
-- Table `SIGEV`.`persona`
-- -----------------------------------------------------
INSERT INTO `sigev`.`persona`
(`nombrePersona`,
`primerApellido`,
`segundoApellido`,
`usuario_idUsuario`)
VALUES
('Ricardo','Gonzalez','Bahena',1),
('Juan','Perez','Gomez',3),
('Jose','Lopez','Gomez',3);

-- -----------------------------------------------------
-- Table `SIGEV`.`voluntario`
-- -----------------------------------------------------
INSERT INTO `sigev`.`voluntario`(`curp`,`persona_idPersona`)
VALUES
('PEGJ990213HTLZNB06',2),
('LOGJ990213HTLZNB06',3);

-- -----------------------------------------------------
-- Table `SIGEV`.`organizacion`
-- -----------------------------------------------------
INSERT INTO `sigev`.`organizacion`
(`rfc`,`nombreOrganizacion`,`razonSocial`,`imagenLogotipo`,`usuario_idUsuario`,`color_idColor`, `direccion_idDireccion`)
VALUES
('RDL0904102F4', 'Rayo de sol', 'Rayo de sol', 'https://i.pinimg.com/1200x/10/21/f7/1021f751af1bc3351a4c550e0a6f77b7.jpg',2,1,1);

-- -----------------------------------------------------
-- Table `SIGEV`.`evento`
-- -----------------------------------------------------
INSERT INTO `sigev`.`evento`
(`nombreEvento`,`descripcion`,`fecha`,`direccion_idDireccion`)
VALUES
('Rescatando perritos', 'Rescateremos perritos', '2022-07-26',1);


-- -----------------------------------------------------
-- Table `SIGEV`.`notificacion`
-- -----------------------------------------------------
INSERT INTO `sigev`.`notificacion` (`mensaje`) 
VALUES 
('Voluntario aceptado'),
('Evento aceptado'),
('Organizacion aceptada'),
('Organizacion rechazada');

-- -----------------------------------------------------
-- Table `SIGEV`.`voluntario_evento`
-- -----------------------------------------------------
INSERT INTO `sigev`.`voluntario_notificacion` (`voluntario_idVoluntario`,`notificacion_idNotificacion`)
VALUES
(1,1),
(2,1);

-- -----------------------------------------------------
-- Table `SIGEV`.`voluntario_evento`
-- -----------------------------------------------------
INSERT INTO `sigev`.`notificacion_organizacion` (`organizacion_idOrganizacion`,`notificacion_idNotificacion`)
VALUES
(1,3);

-- -----------------------------------------------------
-- Table `SIGEV`.`evaluacion`
-- -----------------------------------------------------
INSERT INTO `sigev`.`evaluacion` (`nombreEvaluacion`,`fechaRegistro`,`organizacion_idOrganizacion`)
VALUES
('Evaluación psicométrica','2022-07-26', 1);

-- -----------------------------------------------------
-- Table `SIGEV`.`pregunta`
-- -----------------------------------------------------
INSERT INTO `sigev`.`pregunta` (`textoPregunta`,`evaluacion_idEvaluacion`)
VALUES
('Del 1-10 que tan rápido creas nuevas amistades', 1),
('Del 1-10 que tan tanto confias en los demas', 1),
('Del 1-10 que tan rápido que tanta paciencia tienes', 1);

-- -----------------------------------------------------
-- Table `SIGEV`.`evaluacion_organizacion_evento`
-- -----------------------------------------------------
INSERT INTO `sigev`.`evaluacion_organizacion_evento` (`evaluacion_idEvaluacion`,`organizacion_idOrganizacion`,`evento_idEvento`)
VALUES
(1,1,1);

-- -----------------------------------------------------
-- Table `SIGEV`.`voluntario_evaluacion`
-- -----------------------------------------------------
INSERT INTO `sigev`.`voluntario_evaluacion` (`voluntario_idVoluntario`,`evaluacion_organizacion_evento_idEvaluacionOrganizacionEvento`)
VALUES
(1,1),
(2,1);

-- -----------------------------------------------------
-- Table `SIGEV`.`respuesta`
-- -----------------------------------------------------
INSERT INTO `sigev`.`respuesta` (`textoRespuesta`,`pregunta_idPregunta`,`voluntario_evaluacion_idVoluntarioEvaluacion`)
VALUES
('10',1,1),
('5',2,1),
('7',3,1),
('8',1,2),
('2',2,2),
('10',3,2);



