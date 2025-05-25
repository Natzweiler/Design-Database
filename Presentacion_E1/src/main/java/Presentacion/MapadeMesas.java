/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import BO.MesaBO;
import BO.ReservacionBO;
import Interfaces.IMesaBO;
import Interfaces.IReservacionBO;
import Persistencia.MesaDAO;
import Persistencia.ReservacionDAO;
import dtos.MesaDTO;
import interfaces.IMesaDAO;
import interfaces.IReservacionDAO;
import java.awt.Color;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JOptionPane;


/**
 *
 * @author Gael
 */
public class MapadeMesas extends javax.swing.JFrame {
    private static MapadeMesas instancia;
   
   private Map<JButton, MesaDTO> mapaBotonesMesas = new HashMap<>();

   private MesaDTO mesaSeleccionada;
   
   public MesaDTO getMesaSeleccionada() {
    return mesaSeleccionada;
}
    public static MapadeMesas getInstance() {
        if (instancia == null) {
            instancia = new MapadeMesas();
        }
        return instancia;
    }
   
    
    /**
     * Creates new form MapadeMesas
     */
    public MapadeMesas() {
        initComponents();
     
        cargarMesasDisponiblesBD();
        
        
        for (int i = 2; i <= 31; i++) {
            cbDia.addItem(String.valueOf(i));
        }
        for (int hora = 9; hora <= 21; hora++) {
            String horaFormateada = String.format("%02d:00", hora);
            cbHora.addItem(horaFormateada);
        }
  }

    //BD
        private void cargarMesasDisponiblesBD() {
    try {
        
        IMesaBO mesaBO = new MesaBO();
        List<MesaDTO> mesas = mesaBO.obtenerTodasLasMesas();

        
        JButton[] botones = {mesa1, mesa2, mesa3, mesa4, mesa5, mesa6, mesa7};

        mapaBotonesMesas.clear(); 

        for (int i = 0; i < botones.length && i < mesas.size(); i++) {
            JButton boton = botones[i];
            MesaDTO mesa = mesas.get(i);
            mapaBotonesMesas.put(boton, mesa);
            configurarMesaBD(boton, mesa);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar las mesas: " + e.getMessage());
    }
}
public void mostrarMesasDisponiblesBD(LocalDate fecha, LocalTime hora){
    try {
        IMesaBO mesaBO = new MesaBO();
        IReservacionDAO dao = ReservacionDAO.getInstanceDAO();
        IReservacionBO reservacionBO = new ReservacionBO(dao);

        List<MesaDTO> mesas = mesaBO.obtenerTodasLasMesas();

        JButton[] botones = {mesa1, mesa2, mesa3, mesa4, mesa5, mesa6, mesa7};
        mapaBotonesMesas.clear(); // Limpia el mapa por si cambia la asignación

        for (int i = 0; i < botones.length && i < mesas.size(); i++) {
            MesaDTO mesa = mesas.get(i);
            JButton boton = botones[i];

            boolean disponible = reservacionBO.estadoMesaDisponible(mesa.getNumeroMesa(), fecha, hora);
            mesa.setDisponible(disponible);

            mapaBotonesMesas.put(boton, mesa); // Asocia el botón con la mesa
            configurarMesaBD(boton, mesa);     // Actualiza visualmente el botón
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}

    //BD
    private void configurarMesaBD(JButton mesaBtn, MesaDTO mesa) {
    mesaBtn.setText("Mesa " + mesa.getNumeroMesa());

    if (mesa.isDisponible()) {
        mesaBtn.setBackground(Color.GREEN);
        mesaBtn.setEnabled(true);
    } else {
        mesaBtn.setBackground(Color.RED);
        mesaBtn.setEnabled(false);
    }

    for (ActionListener al : mesaBtn.getActionListeners()) {
        mesaBtn.removeActionListener(al); 
    }

    mesaBtn.addActionListener(e -> {
        try {
            int dia = Integer.parseInt(cbDia.getSelectedItem().toString());
            int mesNum = cbMes.getSelectedIndex() + 1;
            int año = Integer.parseInt(cbAño.getSelectedItem().toString());
            String horaStr = cbHora.getSelectedItem().toString();
            int horaNum = Integer.parseInt(horaStr.split(":")[0]);

            LocalDate fechaSeleccionada = LocalDate.of(año, mesNum, dia);
            LocalTime hora = LocalTime.of(horaNum, 0);

            
            IReservacionDAO reservacionDAO = ReservacionDAO.getInstanceDAO();
            IReservacionBO reservacionBO = new ReservacionBO(reservacionDAO);

            boolean disponible = reservacionBO.estadoMesaDisponible(mesa.getNumeroMesa(), fechaSeleccionada, hora);

            if (disponible) {
                mesaSeleccionada = mesa;

                RegistrarReservacion registrar = new RegistrarReservacion();
                registrar.setMesaSeleccionada(mesa);
                registrar.setFechaHoraSeleccionada(fechaSeleccionada, hora);
                registrar.setLocationRelativeTo(null);
                registrar.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "La mesa " + mesa.getNumeroMesa() + " ya está ocupada.");
                mesaBtn.setBackground(Color.RED);
                mesaBtn.setEnabled(false);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al verificar disponibilidad: " + ex.getMessage());
        }
    });
}

    //BD

   // Verifica disponibilidad y configura cada botón según estado
/*public void mostrarMesasDisponiblesBD(LocalDate fecha, LocalTime hora) {
    System.out.println("mostrarMesasDisponiblesBD - fecha: " + fecha + ", hora: " + hora);
    try {
        IMesaBO mesaBO = new MesaBO();
        IReservacionDAO dao = new ReservacionDAO();
        IReservacionBO reservacionBO = new ReservacionBO(dao);

        List<MesaDTO> mesas = mesaBO.obtenerTodasLasMesas();

        for (MesaDTO mesa : mesas) {
            boolean disponible = reservacionBO.estadoMesaDisponible(mesa.getNumeroMesa(), fecha, hora);
            System.out.println("MESA" + mesa.getNumeroMesa() + "Disponible" + disponible);
            mesa.setDisponible(disponible);
        }

       /* if (mesas.size() >= 7) {
            configurarMesaBD(mesa1, mesas.get(0));
            configurarMesaBD(mesa2, mesas.get(1));
            configurarMesaBD(mesa3, mesas.get(2));
            configurarMesaBD(mesa4, mesas.get(3));
            configurarMesaBD(mesa5, mesas.get(4));
            configurarMesaBD(mesa6, mesas.get(5));
            configurarMesaBD(mesa7, mesas.get(6));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}
        JButton[] botones = {mesa1, mesa2, mesa3, mesa4, mesa5, mesa6, mesa7};
        mapaBotonesMesas.clear();

        
        for (int i = 0; i < botones.length; i++) {
            JButton boton = botones[i];
            MesaDTO mesa = (i < mesas.size()) ? mesas.get(i) : null;

            if (mesa != null) {
                boton.setText("Mesa " + mesa.getNumeroMesa());
                if (mesa.isDisponible()) {
                    boton.setBackground(Color.GREEN);
                    boton.setEnabled(true);
                } else {
                    boton.setBackground(Color.RED);
                    boton.setEnabled(false);
                }

                
                for (ActionListener al : boton.getActionListeners()) {
                    boton.removeActionListener(al);
                }

                boton.addActionListener(e -> abrirFormularioReservacion(mesa, boton));
            } else {
                
                boton.setText("N/A");
                boton.setBackground(null);
                boton.setEnabled(false);

                for (ActionListener al : boton.getActionListeners()) {
                    boton.removeActionListener(al);
                }
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}


    //BD

private void configurarMesaBD(JButton mesaBtn, MesaDTO mesa) {
    mesaBtn.setText("Mesa " + mesa.getNumeroMesa());

    if (mesa.isDisponible()) {
        mesaBtn.setBackground(Color.GREEN);
        mesaBtn.setEnabled(true);
    } else {
        mesaBtn.setBackground(Color.RED);
        mesaBtn.setEnabled(false);
    }

    for (ActionListener al : mesaBtn.getActionListeners()) {
        mesaBtn.removeActionListener(al);
    }

    mesaBtn.addActionListener(e -> {
        abrirFormularioReservacion(mesa, mesaBtn);
    });
}

private void abrirFormularioReservacion(MesaDTO mesa, JButton mesaBtn) {
    try {
        int dia = Integer.parseInt(cbDia.getSelectedItem().toString());
        int mesNum = cbMes.getSelectedIndex() + 1;
        int año = Integer.parseInt(cbAño.getSelectedItem().toString());
        String horaStr = cbHora.getSelectedItem().toString();
        int horaNum = Integer.parseInt(horaStr.split(":")[0]);

        LocalDate fechaSeleccionada = LocalDate.of(año, mesNum, dia);
        LocalTime hora = LocalTime.of(horaNum, 0);

        IReservacionBO reservacionBO = new ReservacionBO(new ReservacionDAO());
        boolean disponible = reservacionBO.estadoMesaDisponible(mesa.getNumeroMesa(), fechaSeleccionada, hora);

        if (disponible) {
            mesaSeleccionada = mesa;
            RegistrarReservacion registrar = new RegistrarReservacion();
            registrar.setMesaSeleccionada(mesa);
            registrar.setFechaHoraSeleccionada(fechaSeleccionada, hora);
            registrar.setLocationRelativeTo(null);
            registrar.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "La mesa " + mesa.getNumeroMesa() + " ya está ocupada.");
            mesaBtn.setBackground(Color.RED);
            mesaBtn.setEnabled(false);
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al verificar disponibilidad: " + ex.getMessage());
    }
}





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        mesa4 = new javax.swing.JButton();
        mesa5 = new javax.swing.JButton();
        mesa6 = new javax.swing.JButton();
        mesa7 = new javax.swing.JButton();
        mesa1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbHora = new javax.swing.JComboBox<>();
        cbDia = new javax.swing.JComboBox<>();
        cbMes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbAño = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        mesa2 = new javax.swing.JButton();
        mesa3 = new javax.swing.JButton();
        labelFondoMesa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 379, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, -20, 360, 180));

        btnRegresar.setBackground(new java.awt.Color(204, 255, 204));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 720, -1, -1));

        mesa4.setText("Mesa 4");
        getContentPane().add(mesa4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 470, -1, -1));

        mesa5.setText("Mesa 5");
        getContentPane().add(mesa5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, -1, -1));

        mesa6.setText("Mesa 6");
        getContentPane().add(mesa6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 470, -1, -1));

        mesa7.setText("Mesa 7");
        getContentPane().add(mesa7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 570, -1, -1));

        mesa1.setText("Mesa 1");
        mesa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesa1ActionPerformed(evt);
            }
        });
        getContentPane().add(mesa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, -1, -1));

        jLabel1.setText("Seleccione la hora:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 106, -1, -1));

        jLabel2.setText("Seleccione la fecha:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 147, -1, -1));

        cbHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08:00" }));
        cbHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHoraActionPerformed(evt);
            }
        });
        getContentPane().add(cbHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 103, -1, -1));

        cbDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));
        cbDia.setToolTipText("Mes");
        getContentPane().add(cbDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 144, -1, -1));

        cbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        getContentPane().add(cbMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 144, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Mapa de Mesas");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 53, -1, -1));

        cbAño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2025", "2026" }));
        cbAño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAñoActionPerformed(evt);
            }
        });
        getContentPane().add(cbAño, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 144, -1, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 192, -1, -1));

        mesa2.setText("Mesa 2");
        mesa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesa2ActionPerformed(evt);
            }
        });
        getContentPane().add(mesa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, 70, -1));

        mesa3.setText("Mesa 3");
        getContentPane().add(mesa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, -1, -1));

        labelFondoMesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondoMapaMesas.png"))); // NOI18N
        getContentPane().add(labelFondoMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        Coordinador.CoordinadorPantallas.getInstance().mostrarMenu();
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void mesa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mesa1ActionPerformed

    private void cbHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbHoraActionPerformed

    private void cbAñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAñoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAñoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
         int dia = Integer.parseInt(cbDia.getSelectedItem().toString());
        int mes = cbMes.getSelectedIndex()+1;
        int año = Integer.parseInt(cbAño.getSelectedItem().toString());
        String horaSeleccionada = (String) cbHora.getSelectedItem();
        
        if (horaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una hora.");
            return;
        }
        LocalDate fecha = LocalDate.of(año, mes, dia);
        LocalTime hora = LocalTime.parse(horaSeleccionada);
        mostrarMesasDisponiblesBD(fecha, hora);   

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void mesa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mesa2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MapadeMesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MapadeMesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MapadeMesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MapadeMesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MapadeMesas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbAño;
    private javax.swing.JComboBox<String> cbDia;
    private javax.swing.JComboBox<String> cbHora;
    private javax.swing.JComboBox<String> cbMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelFondoMesa;
    private javax.swing.JButton mesa1;
    private javax.swing.JButton mesa2;
    private javax.swing.JButton mesa3;
    private javax.swing.JButton mesa4;
    private javax.swing.JButton mesa5;
    private javax.swing.JButton mesa6;
    private javax.swing.JButton mesa7;
    // End of variables declaration//GEN-END:variables
}
