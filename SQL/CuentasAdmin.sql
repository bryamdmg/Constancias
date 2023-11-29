USE Constancias;

INSERT INTO Usuarios (nombreUsuario, contrasena, tipoUsuario)
    VALUES ('BDMG', SHA2('bdmg123', 256), 'Administrador'),
           ('MMP', SHA2('mmp123', 256), 'Administrador'),
           ('XAOS', SHA2('xaos123', 256), 'Administrador');