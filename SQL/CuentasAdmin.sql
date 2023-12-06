USE Constancias;

INSERT INTO Usuarios (nombreUsuario, contrasena, tipoUsuario)
    VALUES ('BDMG', SHA2('bdmg123', 256), 'Administrador'),
           ('MMP', SHA2('mmp123', 256), 'Administrador'),
           ('XAOS', SHA2('xaos123', 256), 'Administrador');

INSERT INTO Usuarios (numPersonal, nombreUsuario, contrasena, tipoUsuario, nombre, fechaIngreso, fechaExpiración, gradoAcadémico, fechaNacimiento)
    VALUE (12345678, 'ocharan', SHA2('1', 256), 'Profesor', 'Jorge Octavio Ocharán Hernández', '2012-05-09', '2024-12-31', 'Dr.', '1980-11-20');

INSERT INTO Usuarios (nombreUsuario, contrasena, tipoUsuario)
VALUE ('admin', SHA2('admin', 256), 'Administrativo');