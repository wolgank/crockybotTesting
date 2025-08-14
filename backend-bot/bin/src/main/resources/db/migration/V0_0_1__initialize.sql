CREATE TABLE especialidad (
	id INT NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE comando (
	id INT NOT NULL AUTO_INCREMENT,
	titulo VARCHAR(100),
    nivel TINYINT NOT NULL,
    respuesta VARCHAR(2000),
    nombre VARCHAR(100) NOT NULL,
    id_especialidad INT NOT NULL,
    id_comando_padre INT,
	PRIMARY KEY (id),
    FOREIGN KEY (id_especialidad) REFERENCES especialidad(id),
    FOREIGN KEY (id_comando_padre) REFERENCES comando(id)
);

CREATE TABLE historial (
	id INT NOT NULL AUTO_INCREMENT,
    usuario VARCHAR(200),
    id_usuario VARCHAR(200),
    fecha TIMESTAMP,
    id_comando INT,
	PRIMARY KEY (id),
    FOREIGN KEY (id_comando) REFERENCES comando(id)
);

-- INSERTS

INSERT INTO especialidad VALUES (1,'Ingeniería Informatica'); 

INSERT INTO comando VALUES
(1,'Consultas EEGGCC', 1, 'Hola, soy el bot de EEGGCC. Pregunta lo que quieras. Elige estas opciones para poder ayudarte(No olvidar anteponer el *):','help',1,null),
(2,'Matricula', 1, 'Estas son las posibles consultas que tendrá sobre la matrícula \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/sobre-la-matricula/):','matricula',1,1),
(3,'Clases y Evaluaciones', 1, 'Información de clases y evaluaciones \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/clases-y-evaluaciones/):','clasesEvaluaciones',1,1),
(4,'Trámites y documentos', 1, 'Información de los trámites y los procesos \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/tramites-y-documentos/):','tramitesprocesos',1,1),
(5,'Calificaciones', 1, 'Información de las calificaciones \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/calificaciones/):','calificaciones',1,1),
(6,'Carné Universitario y TI', 1, 'Información de las calificaciones \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/calificaciones/):','carnet',1,1),
(7,'Acreditación del idioma', 1, 'Información de las calificaciones \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/calificaciones/):','idiomas',1,1),
(8,'Servicios', 1, 'servicios \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/servicios/c):','servicios',1,1),
(9,'Otros', 1, 'Información sobre otras cosas \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/varios/):','otros',1,1),
(10,'Matricula Extemporanea', 1, 'Información sobre la matricula extemporanea','matriculaExtemporanea',1,2),
(11,'Matrícula Primera Opción', 1, 'Información sobre la matricula por primera opción','matriculaPrimeraOpcion',1,2);
