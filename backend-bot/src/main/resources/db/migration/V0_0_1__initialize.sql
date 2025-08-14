CREATE TABLE especialidad (
	id INT NOT NULL,
	nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(100),
	PRIMARY KEY (id)
);

CREATE TABLE comando (
	id INT NOT NULL AUTO_INCREMENT,
	titulo VARCHAR(100),
    respuesta VARCHAR(2000),
    nombre VARCHAR(100) NOT NULL,
    id_especialidad INT NOT NULL,
    id_comando_padre INT,
    imagen_url VARCHAR(500),
    activo BIT NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (id_especialidad) REFERENCES especialidad(id),
    FOREIGN KEY (id_comando_padre) REFERENCES comando(id)
);

CREATE TABLE usuario (
	id INT NOT NULL AUTO_INCREMENT,
    id_usuario_plataforma VARCHAR(25),
    codigo VARCHAR(20) UNIQUE,
    nombre_usuario VARCHAR(100),
    correo VARCHAR(100),
    id_especialidad INT NOT NULL,
    verificado BIT,
	PRIMARY KEY (id),
    FOREIGN KEY (id_especialidad) REFERENCES especialidad(id)
);

CREATE TABLE token (
	id_autor VARCHAR(25),
    nombre_usuario_autor VARCHAR(100),
    id_usuario_a_verificar INT,
    codigo_verificacion VARCHAR(5),
    numero_intentos TINYINT,
	PRIMARY KEY (id_autor),
    FOREIGN KEY (id_usuario_a_verificar) REFERENCES usuario(id)
);

CREATE TABLE historial (
	id INT NOT NULL AUTO_INCREMENT,
	id_especialidad INT NOT NULL, 
    id_usuario INT,
    fecha TIMESTAMP,
    id_comando INT NULL,
    mensaje_personalizado VARCHAR(2100) NULL,
    personalizado BIT,
	PRIMARY KEY (id),
    FOREIGN KEY (id_comando) REFERENCES comando(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_especialidad) REFERENCES especialidad(id)
);

-- INSERTS

INSERT INTO especialidad VALUES (1,'Estudios Generales Ciencias','bot.consultas.pucp@gmail.com'); 

INSERT INTO comando VALUES
(1,'Consultas EEGGCC', 'Hola, soy el bot de EEGGCC. Pregunta lo que quieras. Elige estas opciones para poder ayudarte(No olvidar anteponer el *):','help',1,null,null,1),
(2,'Matricula', 'Estas son las posibles consultas que tendrá sobre la matrícula \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/sobre-la-matricula/):','matricula',1,1,null,1),
(3,'Clases y Evaluaciones', 'Información de clases y evaluaciones \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/clases-y-evaluaciones/):','clasesEvaluaciones',1,1,null,1),
(4,'Trámites y documentos', 'Información de los trámites y los procesos \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/tramites-y-documentos/):','tramitesprocesos',1,1,null,1),
(5,'Calificaciones', 'Información de las calificaciones \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/calificaciones/):','calificaciones',1,1,null,1),
(6,'Carné Universitario y TI', 'Información de las calificaciones \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/calificaciones/):','carnet',1,1,null,1),
(7,'Acreditación del idioma', 'Información de las calificaciones \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/calificaciones/):','idiomas',1,1,null,1),
(8,'Servicios', 'servicios \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/servicios/c):','servicios',1,1,null,1),
(9,'Otros', 'Información sobre otras cosas \n (puedes encontrar información adicional en https://facultad.pucp.edu.pe/generales-ciencias/preguntas-frecuentes/varios/):','otros',1,1,null,1),
(10,'Matricula Extemporanea', 'Información sobre la matricula extemporanea','matriculaExtemporanea',1,2,null,1),
(11,'Matrícula Primera Opción', 'Información sobre la matricula por primera opción','matriculaPrimeraOpcion',1,2,null,1);

INSERT INTO usuario VALUES 
(1,null,'20173002',null,'rodriguez.diego@pucp.edu.pe',1,0);
