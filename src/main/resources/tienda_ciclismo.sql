CREATE DATABASE IF NOT EXISTS tienda_ciclismo;
USE tienda_ciclismo;

-- Tabla Sedes
CREATE TABLE sedes (
    idsede INT AUTO_INCREMENT PRIMARY KEY,
    nombreSede VARCHAR(45),
    direccion VARCHAR(45)
);

-- Tabla Trabajadores
CREATE TABLE trabajadores (
    dni VARCHAR(45) PRIMARY KEY,
    nombres VARCHAR(45),
    apellidos VARCHAR(45),
    correo VARCHAR(45),
    idsede INT,
    FOREIGN KEY (idsede) REFERENCES sedes(idsede)
);

-- Tabla Marcas
CREATE TABLE marcas (
    idmarca INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45)
);

-- Tabla Tipos
CREATE TABLE tipos (
    idtipo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45)
);

-- Tabla Inventario
CREATE TABLE inventario (
    idinventario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45),
    numeroserie VARCHAR(45),
    idsede INT,
    idmarca INT,
    idtipo INT,
    estado VARCHAR(45),
    FOREIGN KEY (idsede) REFERENCES sedes(idsede),
    FOREIGN KEY (idmarca) REFERENCES marcas(idmarca),
    FOREIGN KEY (idtipo) REFERENCES tipos(idtipo)
);

-- Datos de prueba opcionales para visualización inmediata
INSERT INTO sedes (nombreSede, direccion) VALUES 
('Pueblo Libre', 'Av Universitaria 123123'),
('Miraflores', 'Av Benavides 140');

INSERT INTO marcas (nombre) VALUES ('Specialized'), ('Trinx'), ('Shimano');

INSERT INTO tipos (nombre) VALUES ('Marco'), ('Timón'), ('Llantas');