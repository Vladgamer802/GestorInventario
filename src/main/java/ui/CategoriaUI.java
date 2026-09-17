package ui;

import javax.swing.*;
import java.awt.*;
import service.InventarioManager;
import model.Categoria;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CategoriaUI extends JFrame {

    private InventarioManager manager;

    private JTextField txtNombre;
    private JTable tablaCategorias;

    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;

    public CategoriaUI() {
        manager = new InventarioManager();

        setTitle("Gestión de Categorías");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Panel superior (formulario)
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2));

        txtNombre = new JTextField();

        panelFormulario.add(new JLabel("Nombre de categoría:"));
        panelFormulario.add(txtNombre);

        add(panelFormulario, BorderLayout.NORTH);

        // Panel inferior (botones)
        JPanel panelBotones = new JPanel();

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        // Tabla de categorías
        tablaCategorias = new JTable();
        add(new JScrollPane(tablaCategorias), BorderLayout.CENTER);

        cargarTablaCategorias();

        // BOTÓN AGREGAR
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();

            Categoria c = new Categoria();
            c.setNombre(nombre);

            if (manager.agregarCategoria(c)) {
                JOptionPane.showMessageDialog(this, "Categoría agregada correctamente.");
                cargarTablaCategorias();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar categoría.");
            }
        });

        // BOTÓN ACTUALIZAR
        btnActualizar.addActionListener(e -> {
            int fila = tablaCategorias.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una categoría.");
                return;
            }

            int id = Integer.parseInt(tablaCategorias.getValueAt(fila, 0).toString());
            String nombre = txtNombre.getText();

            Categoria c = new Categoria();
            c.setId(id);
            c.setNombre(nombre);

            if (manager.actualizarCategoria(c)) {
                JOptionPane.showMessageDialog(this, "Categoría actualizada.");
                cargarTablaCategorias();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar categoría.");
            }
        });

        // BOTÓN ELIMINAR
        btnEliminar.addActionListener(e -> {
            int fila = tablaCategorias.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una categoría.");
                return;
            }

            int id = Integer.parseInt(tablaCategorias.getValueAt(fila, 0).toString());

            if (manager.eliminarCategoria(id)) {
                JOptionPane.showMessageDialog(this, "Categoría eliminada.");
                cargarTablaCategorias();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar categoría.");
            }
        });

        // SELECCIONAR FILA
        tablaCategorias.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tablaCategorias.getSelectedRow();
                if (fila != -1) {
                    txtNombre.setText(tablaCategorias.getValueAt(fila, 1).toString());
                }
            }
        });

        setVisible(true);
    }

    private void cargarTablaCategorias() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");

        List<Categoria> categorias = manager.listarCategorias();

        for (Categoria c : categorias) {
            modelo.addRow(new Object[]{
                    c.getId(),
                    c.getNombre()
            });
        }

        tablaCategorias.setModel(modelo);
    }

    private void limpiarCampos() {
        txtNombre.setText("");
    }
}
