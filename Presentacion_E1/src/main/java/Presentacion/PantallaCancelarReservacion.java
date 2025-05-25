package Presentacion;

import Interfaces.IReservacionBO;
import dtos.ReservacionDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class PantallaCancelarReservacion extends JFrame {
    private JComboBox<ReservacionDTO> comboReservas;
    private JButton btnCancelar;
    private IReservacionBO controlReservacion;

    public PantallaCancelarReservacion(IReservacionBO controlReservacion) {
        super("Cancelar Reservación");
        this.controlReservacion = controlReservacion;
        // Activar Look & Feel Nimbus (opcional)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(this);
                    break;
                }
            }
        } catch (Exception e) { /* ignora si falla */ }

        initComponents();
       // cargarReservaciones();
    }

    private void initComponents() {
        // Panel principal
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(20, 30, 20, 30));
        main.setBackground(Color.WHITE);

        // Título
        JLabel lblTitle = new JLabel("Cancelar Reservación");
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
        lblTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        main.add(lblTitle);

        // Etiqueta de instrucción
        JLabel lblInstruccion = new JLabel("Seleccione el ID de la reservación:");
        lblInstruccion.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblInstruccion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInstruccion.setBorder(new EmptyBorder(0, 0, 10, 0));
        main.add(lblInstruccion);

        // ComboBox
        comboReservas = new JComboBox<>();
        comboReservas.setMaximumSize(new Dimension(350, 30));
        comboReservas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboReservas.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(comboReservas);

        // Espacio
        main.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botón Cancelar
        btnCancelar = new JButton("Cancelar Reservación");
        btnCancelar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancelar.setPreferredSize(new Dimension(200, 36));
        //btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        main.add(btnCancelar);

        // Configura el frame
        setContentPane(main);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }
    /*private void cargarReservaciones() {
        try {
            List<ReservacionDTO> lista = controlReservacion.listarReservaciones();
            DefaultComboBoxModel<ReservacionDTO> model = new DefaultComboBoxModel<>();
            for (ReservacionDTO r : lista) {
                model.addElement(r);
            }
            comboReservas.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar reservas: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        ReservacionDTO sel = (ReservacionDTO) comboReservas.getSelectedItem();
        if (sel == null) {
            JOptionPane.showMessageDialog(this,
                "Selecciona una reservación primero.",
                "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            boolean ok = controlReservacion.cancelarReservacion(sel.getId());
            if (ok) {
                JOptionPane.showMessageDialog(this,
                    "Reservación cancelada correctamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarReservaciones();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al cancelar: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }*/
}
