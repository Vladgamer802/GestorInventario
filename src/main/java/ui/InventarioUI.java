package ui;

import javax.swing.*;
import java.awt.*;
import service.InventarioManager;
import model.Categoria;
import model.Producto;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class InventarioUI extends JFrame {

    private InventarioManager manager;

    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtUnidades;
    private JTextField txtStock;
    private JComboBox<Categoria> comboCategoria;

    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;

    private JTable tablaProductos;

    public InventarioUI() {
        manager = new InventarioManager();

        setTitle("Gestión de Inventario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));

        txtNombre = new JTextField();
        txtDescripcion = new JTextField();
        txtUnidades = new JTextField();
        txtStock = new JTextField();
        comboCategoria = new JComboBox<>();

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Descripción:"));
        panelFormulario.add(txtDescripcion);

        panelFormulario.add(new JLabel("Unidades:"));
        panelFormulario.add(txtUnidades);

        panelFormulario.add(new JLabel("Stock:"));
        panelFormulario.add(txtStock);

        panelFormulario.add(new JLabel("Categoría:"));
        panelFormulario.add(comboCategoria);

        add(panelFormulario, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        tablaProductos = new JTable();
        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        cargarCategorias();
        cargarTablaProductos();

        // BOTÓN AGREGAR
        btnAgregar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                int unidades = Integer.parseInt(txtUnidades.getText());
                int stock = Integer.parseInt(txtStock.getText());
                Categoria categoria = (Categoria) comboCategoria.getSelectedItem();

                Producto p = new Producto();
                p.setNombre(nombre);
                p.setDescripcion(descripcion);
                p.setUnidades(unidades);
                p.setStock(stock);
                p.setCategoria_id(categoria.getId());

                if (manager.agregarProducto(p)) {
                    JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
                    cargarTablaProductos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar producto.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Unidades y stock deben ser números.");
            }
        });

        // BOTÓN ACTUALIZAR
        btnActualizar.addActionListener(e -> {
            int fila = tablaProductos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla.");
                return;
            }

            try {
                int id = Integer.parseInt(tablaProductos.getValueAt(fila, 0).toString());
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                int unidades = Integer.parseInt(txtUnidades.getText());
                int stock = Integer.parseInt(txtStock.getText());
                Categoria categoria = (Categoria) comboCategoria.getSelectedItem();

                Producto p = new Producto();
                p.setId(id);
                p.setNombre(nombre);
                p.setDescripcion(descripcion);
                p.setUnidades(unidades);
                p.setStock(stock);
                p.setCategoria_id(categoria.getId());

                if (manager.actualizarProducto(p)) {
                    JOptionPane.showMessageDialog(this, "Producto actualizado.");
                    cargarTablaProductos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar producto.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Unidades y stock deben ser números.");
            }
        });

        // BOTÓN ELIMINAR
        btnEliminar.addActionListener(e -> {
            int fila = tablaProductos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla.");
                return;
            }

            int id = Integer.parseInt(tablaProductos.getValueAt(fila, 0).toString());

            if (manager.eliminarProducto(id)) {
                JOptionPane.showMessageDialog(this, "Producto eliminado.");
                cargarTablaProductos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar producto.");
            }
        });

        // SELECCIONAR FILA
        tablaProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tablaProductos.getSelectedRow();
                if (fila != -1) {
                    txtNombre.setText(tablaProductos.getValueAt(fila, 1).toString());
                    txtDescripcion.setText(tablaProductos.getValueAt(fila, 2).toString());
                    txtUnidades.setText(tablaProductos.getValueAt(fila, 3).toString());
                    txtStock.setText(tablaProductos.getValueAt(fila, 4).toString());

                    int categoriaId = Integer.parseInt(tablaProductos.getValueAt(fila, 5).toString());

                    for (int i = 0; i < comboCategoria.getItemCount(); i++) {
                        if (comboCategoria.getItemAt(i).getId() == categoriaId) {
                            comboCategoria.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });

        setVisible(true);
    }

    private void cargarCategorias() {
        comboCategoria.removeAllItems();

        List<Categoria> categorias = manager.listarCategorias();

        for (Categoria c : categorias) {
            comboCategoria.addItem(c);
        }
    }

    private void cargarTablaProductos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("Unidades");
        modelo.addColumn("Stock");
        modelo.addColumn("Categoría");

        for (Producto p : manager.listarProductos()) {
            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getUnidades(),
                    p.getStock(),
                    p.getCategoria_id()
            });
        }

        tablaProductos.setModel(modelo);
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtUnidades.setText("");
        txtStock.setText("");
        comboCategoria.setSelectedIndex(0);
    }
}
