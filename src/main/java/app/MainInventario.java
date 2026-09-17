package app;

import javax.swing.*;
import java.awt.*;
import ui.InventarioUI;
import ui.CategoriaUI;

public class MainInventario extends JFrame {

    public MainInventario() {
        setTitle("Sistema de Inventario");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 1));

        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo);

        JButton btnProductos = new JButton("Gestión de Productos");
        JButton btnCategorias = new JButton("Gestión de Categorías");

        add(btnProductos);
        add(btnCategorias);

        // Abrir UI de productos
        btnProductos.addActionListener(e -> new InventarioUI());

        // Abrir UI de categorías
        btnCategorias.addActionListener(e -> new CategoriaUI());

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainInventario();
    }
}
