-- Crear base de datos (ejecutar esto primero si la base de datos no existe)
-- CREATE DATABASE gestor_inventario;

-- Conectarse a la base de datos
\c gestor_inventario;

-- Crear tabla categoria
CREATE TABLE IF NOT EXISTS categoria (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Crear tabla producto
CREATE TABLE IF NOT EXISTS producto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    unidades INTEGER NOT NULL DEFAULT 0,
    stock INTEGER NOT NULL DEFAULT 0,
    categoria_id INTEGER NOT NULL,
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id) ON DELETE CASCADE
);

-- Insertar datos de ejemplo (opcional)
INSERT INTO categoria (nombre) VALUES 
('Electrónica'),
('Ropa'),
('Alimentos'),
('Hogar');

INSERT INTO producto (nombre, descripcion, unidades, stock, categoria_id) VALUES 
('Laptop', 'Laptop HP 15.6"', 10, 5, 1),
('Camiseta', 'Camiseta de algodón talla M', 50, 30, 2),
('Arroz', 'Arroz blanco 1kg', 100, 80, 3),
('Silla', 'Silla de oficina ergonómica', 15, 10, 4);
