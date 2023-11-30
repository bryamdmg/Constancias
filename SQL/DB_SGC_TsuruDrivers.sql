DROP DATABASE IF EXISTS Constancias;

CREATE DATABASE Constancias DEFAULT CHARACTER SET utf8;
USE Constancias;

CREATE TABLE Usuarios(
    Id_usuario INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (Id_usuario),
    numPersonal INT,
    UNIQUE (numPersonal),
    nombreUsuario NVARCHAR(28) NOT NULL,
    UNIQUE (nombreUsuario),
    contrasena NVARCHAR(64) NOT NULL,
    tipoUsuario ENUM('Administrador','Profesor','Administrativo'),
    nombre NVARCHAR(50),
    fechaIngreso DATE,
    fechaExpiración DATE,
    gradoAcadémico NVARCHAR(30),
    fechaNacimiento DATE
);

CREATE TABLE ExperienciasEducativas(
	NRC INT,
    PRIMARY KEY(NRC),
    creditos INT,
    periodo NVARCHAR(20),
    bloque INT,
    sección INT
);

CREATE TABLE Facultades(
	IdFacultad INT AUTO_INCREMENT,
    PRIMARY KEY(IdFacultad),
    nombre NVARCHAR(50),
    región NVARCHAR(50),
    director NVARCHAR(80)
);

CREATE TABLE Firmas(
	IdFirma INT AUTO_INCREMENT,
    PRIMARY KEY(IdFirma),
    firma VARCHAR(64)
);

CREATE TABLE Constancias(
	IdConstancia INT AUTO_INCREMENT,
    PRIMARY KEY(IdConstancia),
    IdFirma INT,
    fechaEmisión DATE
);

CREATE TABLE ConstanciasJurado(
	IdConstanciaJurado INT AUTO_INCREMENT,
    PRIMARY KEY(IdConstanciaJurado),
    IdConstancia INT,
    alumnos TEXT,
    tituloTrabajo NVARCHAR(50),
    modalidad NVARCHAR(30),
    fechaPresentación DATE,
    resultado TEXT
);

CREATE TABLE ConstanciasProyectos(
	IdConstanciaProyecto INT AUTO_INCREMENT,
    PRIMARY KEY(IdConstanciaProyecto),
    IdConstancia INT,
    duración TIME,
    lugar NVARCHAR(50),
    proyectoRealizado TEXT,
    alumnosInvolucrados TEXT
);

CREATE TABLE ConstanciasImpartición(
	IdConstanciaImpartición INT AUTO_INCREMENT,
    PRIMARY KEY(IdConstanciaImpartición),
    IdConstancia INT,
    HSM NVARCHAR(50),
    programaEducativo NVARCHAR(50)
);

CREATE TABLE ConstanciasPLADEA(
	IdConstanciaPladea INT AUTO_INCREMENT,
    PRIMARY KEY(IdConstanciaPladea),
    IdConstancia INT,
    ejeEstratégico TEXT,
    programaEstratégico TEXT,
    objetivosGenerales TEXT,
    acciones TEXT,
    metas TEXT
);

ALTER TABLE ConstanciasJurado ADD CONSTRAINT FK_ConstanciasJurado_IdConstancia FOREIGN KEY(IdConstanciaJurado) REFERENCES Constancias(IdConstancia) ON DELETE CASCADE;
ALTER TABLE ConstanciasProyectos ADD CONSTRAINT FK_ConstanciasProyectos_IdConstancia FOREIGN KEY(IdConstanciaProyecto) REFERENCES Constancias(IdConstancia) ON DELETE CASCADE;
ALTER TABLE ConstanciasImpartición ADD CONSTRAINT FK_ConstanciasImpartición_IdConstancia FOREIGN KEY(IdConstanciaImpartición) REFERENCES Constancias(IdConstancia) ON DELETE CASCADE;
ALTER TABLE ConstanciasPLADEA ADD CONSTRAINT FK_ConstanciasPLADEA_IdConstancia FOREIGN KEY(IdConstanciaPLADEA) REFERENCES Constancias(IdConstancia) ON DELETE CASCADE;