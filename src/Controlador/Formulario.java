package Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Arrays;

public class Formulario {
    private JPanel PanelPrin;
    private JLabel LabelFecha;

    private JTextField FieldDesc;
    private JLabel LabelHora;
    private JLabel LabelDescripcion;
    private JTextField FieldTarea;
    private JLabel LabelTarea;
    private JLabel Titulo;
    private JLabel LabelEstado;
    private JComboBox ComboEstado;
    private JPanel PanelLista;
    private JPanel PanelBotones;
    private JButton BotonAgregar;
    private JButton BotonEdit;
    private JButton BotonEliminar;
    private JButton BotonCambiarEs;
    private JTable tabla;
    private JComboBox Dia;
    private JComboBox Mes;
    private JComboBox Anio;
    private JLabel Ayuda;
    private JComboBox Hora;
    private JComboBox Min;
    private JButton BotonFiltrar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario");
        frame.setBounds(0, 0, 750, 700);
        frame.setContentPane(new Formulario().PanelPrin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    Formulario() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tarea");
        model.addColumn("Fecha");
        model.addColumn("Hora");
        model.addColumn("Descripcion");
        model.addColumn("Estado");
        model.addColumn("Fecha de creacion");
        tabla.setModel(model);

        BotonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tarea = FieldTarea.getText();
                String hora = Hora.getSelectedItem() + ":" + Min.getSelectedItem();
                String fecha = Dia.getSelectedItem() + "/" + Mes.getSelectedItem() + "/" + Anio.getSelectedItem();
                String descripcion = FieldDesc.getText();
                String estado = ComboEstado.getSelectedItem().toString();
                String fechaCreacion = LocalDate.now().toString();

                if (!tarea.isEmpty() && !hora.isEmpty() && !fecha.isEmpty() && descripcion.isEmpty() && !estado.isEmpty()) {

                    String[] objTab = {tarea, fecha, hora, "", estado, fechaCreacion};
                    model.addRow(objTab);
                    FieldTarea.setText("");
                    FieldDesc.setText("");


                } else if (!tarea.isEmpty() && !hora.isEmpty() && !fecha.isEmpty() && !descripcion.isEmpty() && !estado.isEmpty()) {

                    String[] objTab = {tarea, fecha, hora, descripcion, estado, fechaCreacion};
                    model.addRow(objTab);
                    FieldTarea.setText("");
                    FieldDesc.setText("");

                } else {

                    JOptionPane.showMessageDialog(null, "Tienes que introducir los parametros obligatorios");
                }
            }
        });

        BotonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = tabla.getSelectedRow();

                model.removeRow(id);

                JOptionPane.showMessageDialog(null, "Has eliminado la tarea con el id: " + id);
            }
        });

        BotonCambiarEs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = tabla.getSelectedRow();
                String estado = tabla.getValueAt(id, 4).toString();
                String estadoCambiar = ComboEstado.getSelectedItem().toString();

                if (estado.equals(estadoCambiar)) {
                    JOptionPane.showMessageDialog(null, "Has introducido el mismo estado");
                } else {

                    tabla.setValueAt(estadoCambiar, id, 4);

                }

            }
        });

        Ayuda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                JOptionPane.showMessageDialog(null, "La aplicacion consiste en un gestor de tareas, teniendo que introducir de forma obligatoria" +
                        "tanto el nombre de la tarea, la fecha y hora y su estado. Teniendo de forma opcional para anotar una descripcion de la nota");
            }
        });

        BotonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = tabla.getSelectedRow();
                String tareaCambiar = FieldTarea.getText();
                String descrCambiar = FieldDesc.getText();

                if (!tareaCambiar.isEmpty()) {
                    tabla.setValueAt(tareaCambiar, id, 0);

                    FieldDesc.setText("");
                    FieldTarea.setText("");
                }

                if (!descrCambiar.isEmpty()) {
                    tabla.setValueAt(descrCambiar, id, 3);

                    FieldDesc.setText("");
                    FieldTarea.setText("");
                }
            }
        });

        BotonFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int itemcont = tabla.getRowCount();
                String estadoRequerido = ComboEstado.getSelectedItem().toString();

                for(int i = 0;i <itemcont;i++){

                    String[] tareas = {String.valueOf(i),(String) tabla.getValueAt(i,4)};

                    //No me ha dado tiempo a terminar el metodo de filtrar, pero despues de recorrer y meter el id de la row y el estado
                    //filtraria segun a interes del ususario

                    System.out.println(Arrays.toString(tareas));



                }

            }
        });


    }


}
