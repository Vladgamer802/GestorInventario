package service;

import DAO.ProductoDAO;
import DAO.CategoriaDAO;
import model.Producto;
import model.Categoria;

import java.util.List;

public class InventarioManager {

    private ProductoDAO productoDAO;
    private CategoriaDAO categoriaDAO;

    public InventarioManager() {
        this.productoDAO = new ProductoDAO();
        this.categoriaDAO = new CategoriaDAO();
    }

    // ============================
    // PRODUCTOS
    // ============================

    public List<Producto> listarProductos() {
        return productoDAO.findAll();
    }

    public Producto buscarProductoPorId(int id) {
        return productoDAO.findById(id);
    }

    public boolean agregarProducto(Producto producto) {

        // Validación: nombre obligatorio
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            System.out.println("Error: el nombre del producto no puede estar vacío.");
            return false;
        }

        // Validación: unidades no negativas
        if (producto.getUnidades() < 0) {
            System.out.println("Error: las unidades no pueden ser negativas.");
            return false;
        }

        // Validación: stock no negativo
        if (producto.getStock() < 0) {
            System.out.println("Error: el stock no puede ser negativo.");
            return false;
        }

        // Validación: categoría existente
        Categoria categoria = categoriaDAO.findById(producto.getCategoria_id());
        if (categoria == null) {
            System.out.println("Error: la categoría seleccionada no existe.");
            return false;
        }

        return productoDAO.insert(producto);
    }

    public boolean actualizarProducto(Producto producto) {

        // Validación: producto existente
        if (productoDAO.findById(producto.getId()) == null) {
            System.out.println("Error: el producto no existe.");
            return false;
        }

        // Validación: nombre obligatorio
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            System.out.println("Error: el nombre del producto no puede estar vacío.");
            return false;
        }

        // Validación: unidades no negativas
        if (producto.getUnidades() < 0) {
            System.out.println("Error: las unidades no pueden ser negativas.");
            return false;
        }

        // Validación: stock no negativo
        if (producto.getStock() < 0) {
            System.out.println("Error: el stock no puede ser negativo.");
            return false;
        }

        // Validación: categoría existente
        Categoria categoria = categoriaDAO.findById(producto.getCategoria_id());
        if (categoria == null) {
            System.out.println("Error: la categoría seleccionada no existe.");
            return false;
        }

        return productoDAO.update(producto);
    }

    public boolean eliminarProducto(int id) {

        // Validación: producto existente
        if (productoDAO.findById(id) == null) {
            System.out.println("Error: el producto no existe.");
            return false;
        }

        return productoDAO.delete(id);
    }

    // ============================
    // CATEGORÍAS
    // ============================

    public List<Categoria> listarCategorias() {
        return categoriaDAO.findAll();
    }

    public Categoria buscarCategoriaPorId(int id) {
        return categoriaDAO.findById(id);
    }

    public boolean agregarCategoria(Categoria categoria) {

        // Validación: nombre obligatorio
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            System.out.println("Error: el nombre de la categoría no puede estar vacío.");
            return false;
        }

        return categoriaDAO.insert(categoria);
    }

    public boolean actualizarCategoria(Categoria categoria) {

        // Validación: categoría existente
        if (categoriaDAO.findById(categoria.getId()) == null) {
            System.out.println("Error: la categoría no existe.");
            return false;
        }

        // Validación: nombre obligatorio
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            System.out.println("Error: el nombre de la categoría no puede estar vacío.");
            return false;
        }

        return categoriaDAO.update(categoria);
    }

    public boolean eliminarCategoria(int id) {

        // Validación: categoría existente
        if (categoriaDAO.findById(id) == null) {
            System.out.println("Error: la categoría no existe.");
            return false;
        }

        return categoriaDAO.delete(id);
    }
}

