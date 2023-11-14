package com.example.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.jar.JarEntry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.model.Actor;
import com.example.repository.ActorRepository;
import com.example.repository.Repository;
import com.mysql.cj.xdevapi.JsonArray;

public class SwingApp extends JFrame {
    private Repository<Actor> ActorRepository;
    private final JTable ActorTable;
    private final DefaultTableModel tableModel;

    public void refresh() throws Exception{
        ActorRepository = new ActorRepository();
        tableModel.setRowCount(0);

        try {
            List<Actor> actors = ActorRepository.findAll();
            for (Actor actor : actors) {
                Object[] rowData = {actor.getactor_id(), actor.getFirst_name(), actor.getLast_name()};
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener la lista de actores.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public SwingApp() throws Exception{
        setTitle("Gestion Sakila");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        tableModel = new DefaultTableModel();
        ActorTable = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido");

        JScrollPane scrollPane = new JScrollPane(ActorTable);
        add(scrollPane, BorderLayout.CENTER);

        ActorRepository = new ActorRepository();

        refresh();

        JButton addButton = new JButton("Agregar");
        JButton updateButton = new JButton("Actualizar");
        JButton deleteButton = new JButton("Borrar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e-> {
            try {
                JPanel formPanel = new JPanel();
                JTextField firstNameField = new JTextField(15);
                JTextField lastNameField = new JTextField(15);
                JTextField idActorField = new JTextField(10);
                JLabel idActor = new JLabel("ID");
                JLabel firstName = new JLabel("Nombre");                
                JLabel lastName = new JLabel("Apellido");
                formPanel.add(idActor);
                formPanel.add(idActorField);
                formPanel.add(firstName);
                formPanel.add(firstNameField);
                formPanel.add(lastName);
                formPanel.add(lastNameField);
                int result = JOptionPane.showConfirmDialog(null, formPanel,
                            "Ingrese los datos para el actor", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    try {                        
                        Actor t = new Actor();
                        String primerNombre = firstNameField.getText();
                        String segundoNombre = lastNameField.getText();
                        Integer id = Integer.parseInt(idActorField.getText());
                        t.setactor_id(id);
                        t.setFirst_name(primerNombre);
                        t.setLast_name(segundoNombre);
                        System.out.println(t);
                        ActorRepository.save(t);
                        refresh();
                        // JDialog1.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                };
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        updateButton.addActionListener(e-> {
            try {
                int filaSeleccionada = ActorTable.getSelectedRow();
                if (filaSeleccionada != -1) {
                    
                    Integer id = (Integer) tableModel.getValueAt(filaSeleccionada, 0);
                    JTextField firstNameField = new JTextField(10);
                    JTextField lastNameField = new JTextField(10);
                    JLabel firstNameLabel = new JLabel("Nombre:");
                    JLabel lastNameLabel = new JLabel("Apellido:");
        
                    JPanel formPanel = new JPanel();
                    formPanel.add(firstNameLabel);
                    formPanel.add(firstNameField);
                    formPanel.add(lastNameLabel);
                    formPanel.add(lastNameField);
        
                    int result = JOptionPane.showConfirmDialog(null, formPanel,
                            "Ingrese nuevos datos para el actor", JOptionPane.OK_CANCEL_OPTION);
        
                    // Verificar si se hizo clic en "OK"
                    if (result == JOptionPane.OK_OPTION) {
                        // Obtener los nuevos datos
                        String nuevoPrimerNombre = firstNameField.getText();
                        String nuevoSegundoNombre = lastNameField.getText();

                        Actor t = new Actor();
                        t.setactor_id(id);
                        t.setFirst_name(nuevoPrimerNombre);
                        t.setLast_name(nuevoSegundoNombre);
                        System.out.println(t);
                        ActorRepository.save(t);
                        refresh();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        deleteButton.addActionListener(e-> {
            int filaSeleccionada = ActorTable.getSelectedRow();
            if (filaSeleccionada != -1) {
                Integer id = (Integer) tableModel.getValueAt(filaSeleccionada, 0);
                    try {
                        // Integer idDelete = Integer.parseInt(id.getText());
                        ActorRepository.delete(id);
                        JOptionPane.showMessageDialog(null, "Registro eliminado", "Alerta", JOptionPane.WARNING_MESSAGE);
                        refresh();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Este es un mensaje de alerta.", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }
                };
            
        });
    }        

}
