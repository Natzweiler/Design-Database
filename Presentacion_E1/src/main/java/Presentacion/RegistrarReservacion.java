/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import BO.MesaBO;
import BO.MeseroBO;
import BO.ReservacionBO;
import Interfaces.IMesaBO;
import Interfaces.IMeseroBO;
import Interfaces.IReservacionBO;
import Persistencia.MeseroDAO;
import Persistencia.ReservacionDAO;
import dtos.ClienteDTO;
import dtos.MesaDTO;
import dtos.MeseroDTO;
import dtos.ReservacionDTO;
import interfaces.IMeseroDAO;
import interfaces.IReservacionDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import negocio.exception.NegocioException;

/**
 *
 * @author Gael
 */
public class RegistrarReservacion extends javax.swing.JFrame {

    /**
     * Creates new form RegistrarReservacion
     */
    private MesaDTO mesa;
    private List<MeseroDTO> listaMeseros;
    public static MapadeMesas mapaDeMesas;
    private LocalDate fechaSeleccionada;
    private LocalTime horaSeleccionada;
   
    
    
        public void setMesaSeleccionada(MesaDTO mesa) {
        this.mesa = mesa;
        mostrarDetallesMesa();
    }
        public void setFechaHoraSeleccionada (LocalDate fecha, LocalTime hora){
        this.fechaSeleccionada = fecha;
        this.horaSeleccionada = hora;
        labelFechaSeleccionada.setText("Fecha seleccionada: " +  fecha.toString());
        labelHoraSeleccionada.setText("Hora seleccionada: " + hora.toString());
        
        
        }
        private void mostrarDetallesMesa() {
            if (mesa != null) {
                jLabelNumeroMesa.setText("Mesa " + mesa.getNumeroMesa());
            }else{
            JOptionPane.showMessageDialog(this, "Error al mostrar mesa." + JOptionPane.ERROR_MESSAGE);
            }
    }
    public RegistrarReservacion() {
        initComponents();
        cargarMeseros();
        
        mapaDeMesas = Coordinador.CoordinadorPantallas.getInstance().getMapaDeMesas();
          
    }
//cargar meseros desde la bd
private void cargarMeseros() {
    try {
        IMeseroDAO meseroDAO = MeseroDAO.getInstanceDAO();
        IMeseroBO meseroBO = new MeseroBO(meseroDAO);
        listaMeseros = meseroBO.obtenerMeserosActivos(); 

        comboMesero.removeAllItems(); 
        for (MeseroDTO mesero : listaMeseros) {
            comboMesero.addItem(mesero.getNombre());
        }

    } catch (NegocioException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar meseros: " + e.getMessage());
    }
}

 public void enviarCorreoConfirmacion(String nombre, String correo, String telefono, int mesa, String fecha, String mesero) {
    String host = "smtp.gmail.com"; 
    final String usuario = "restaurantedisoftware@gmail.com"; 
    final String contrasena = "sotg wpan zyhs bvpj"; 

    
    Properties propiedades = new Properties();
    propiedades.put("mail.smtp.host", host);
    propiedades.put("mail.smtp.port", "587");
    propiedades.put("mail.smtp.auth", "true");
    propiedades.put("mail.smtp.starttls.enable", "true"); 

    
    Session session = Session.getInstance(propiedades, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(usuario, contrasena);
        }
    });

    try {
        
        MimeMessage mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(usuario));
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correo)); 
        mensaje.setSubject("Confirmación de Reservación");
        
        
        String cuerpo = "=============== CONFIRMACIÓN DE RESERVACIÓN ===============\n"
              + "Estimado/a cliente,\n\n"
              + "Le confirmamos que su reservación ha sido registrada exitosamente.\n"
              + "A continuación, se detallan los datos correspondientes:\n\n"
              + "Nombre completo       : " + nombre + "\n"
              + "Teléfono de contacto  : " + telefono + "\n"
              + "Correo electrónico    : " + correo + "\n"
              + "Mesa asignada         : No. " + mesa + "\n"
              + "Fecha de reservación  : " + fecha + "\n"
              + "Mesero asignado       : " + mesero + "\n\n"
              + "------------------------------------------------------------\n"
              + "Agradecemos su preferencia.\n"
              + "Quedamos a su disposición para cualquier duda o modificación.\n"
              + "¡Le esperamos pronto!\n"
              + "============================================================";
        
        mensaje.setText(cuerpo);

       
        Transport.send(mensaje);

        System.out.println("Correo enviado correctamente.");

    } catch (MessagingException e) {
        e.printStackTrace();
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

        jLabel1 = new javax.swing.JLabel();
        jLabelNumeroMesa = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textoNombreCliente = new javax.swing.JTextField();
        textCorreo = new javax.swing.JTextField();
        textoTelefono = new javax.swing.JTextField();
        btnReservar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        comboMesero = new javax.swing.JComboBox<>();
        labelMesero = new javax.swing.JLabel();
        labelFechaSeleccionada = new javax.swing.JLabel();
        labelHoraSeleccionada = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Reservacion de Mesa");

        jLabelNumeroMesa.setText("Número de mesa:");

        jLabel3.setText("Nombre:");

        jLabel5.setText("Correo:");

        jLabel6.setText("Telefono:");

        textoTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoTelefonoActionPerformed(evt);
            }
        });

        btnReservar.setText("Reservar");
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        comboMesero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMeseroActionPerformed(evt);
            }
        });

        labelMesero.setText("Mesero:");

        labelFechaSeleccionada.setText("jLabel2");

        labelHoraSeleccionada.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(textCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                        .addComponent(textoTelefono)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(labelMesero))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(comboMesero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textoNombreCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)))))
                        .addContainerGap(241, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
                        .addComponent(btnReservar)
                        .addGap(187, 187, 187))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelNumeroMesa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelFechaSeleccionada)
                            .addComponent(labelHoraSeleccionada))
                        .addGap(201, 201, 201))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNumeroMesa)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHoraSeleccionada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelFechaSeleccionada)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMesero, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMesero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReservar)
                    .addComponent(btnRegresar))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoTelefonoActionPerformed

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed
        // TODO add your handling code here
        if (mesa == null) {
        JOptionPane.showMessageDialog(this, "No hay una mesa seleccionada.");
        return;
    }
    
    String nombre = textoNombreCliente.getText().trim();
    String telefono = textoTelefono.getText().trim();
    String correo = textCorreo.getText().trim();
        if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor llena todos los campos del cliente.");
            return;
        }
    
    int indice = comboMesero.getSelectedIndex();
    if (indice < 0 || listaMeseros == null || listaMeseros.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Selecciona un mesero.");
        return;
    }
    
    MeseroDTO meseroSeleccionado = listaMeseros.get(indice);
    
    ClienteDTO clienteDTO = new ClienteDTO();
    clienteDTO.setNombre(nombre);
    clienteDTO.setTelefono(telefono);
    clienteDTO.setCorreo(correo);
    
    IMesaBO mesaBO = new MesaBO();
    
    
    ReservacionDTO reservacionDTO = new ReservacionDTO();
    reservacionDTO.setMesa(mesa);
    reservacionDTO.setCliente(clienteDTO);
    reservacionDTO.setMesero(meseroSeleccionado);
    reservacionDTO.setFecha(fechaSeleccionada);
    reservacionDTO.setHora(horaSeleccionada);
    
    
      try {
          IReservacionDAO reservacionDAO = new ReservacionDAO();
          IReservacionBO reservacionBO = new ReservacionBO(reservacionDAO);
          ReservacionDTO registrada = reservacionBO.registrarReservacion(reservacionDTO);
          mesaBO.actualizarEstadoMesa(mesa.getNumeroMesa(), false);
          
          JOptionPane.showMessageDialog(this, "Reservacion registrada correctamente.");
          enviarCorreoConfirmacion(nombre, correo, telefono, mesa.getNumeroMesa(), fechaSeleccionada.toString(), meseroSeleccionado.getNombre());
              StringBuilder ticket = new StringBuilder();
                 ticket.append("=============== CONFIRMACIÓN DE RESERVACIÓN ===============\n")
                .append("Estimado/a cliente,\n\n")
                .append("Le confirmamos que su reservación ha sido registrada exitosamente.\n")
                .append("A continuación, se detallan los datos correspondientes:\n\n")
                .append("Nombre completo       : ").append(nombre).append("\n")
                .append("Teléfono de contacto  : ").append(telefono).append("\n")
                .append("Correo electrónico    : ").append(correo).append("\n")
                .append("Mesa asignada         : No. ").append(mesa.getNumeroMesa()).append("\n")
                .append("Mesero asignado       : ").append(meseroSeleccionado.getNombre()).append("\n\n")
                .append("------------------------------------------------------------\n")
                .append("Agradecemos su preferencia.\n")
                .append("Quedamos a su disposición para cualquier duda o modificación.\n")
                .append("¡Le esperamos pronto!\n")
                .append("============================================================");
    JOptionPane.showMessageDialog(this, ticket.toString(), "Se envío el ticket de confirmación a su correo.", JOptionPane.INFORMATION_MESSAGE);
            mapaDeMesas.mostrarMesasDisponiblesBD(fechaSeleccionada, horaSeleccionada);
          this.dispose();
          Coordinador.CoordinadorPantallas.getInstance().mostrarMenu();
         
          
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la reservacion: " + e.getMessage());
        }
    }//GEN-LAST:event_btnReservarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
       Coordinador.CoordinadorPantallas.getInstance().mostrarMapaMesas();
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void comboMeseroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMeseroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMeseroActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrarReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
                RegistrarReservacion r = new RegistrarReservacion();
                r.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnReservar;
    private javax.swing.JComboBox<String> comboMesero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelNumeroMesa;
    private javax.swing.JLabel labelFechaSeleccionada;
    private javax.swing.JLabel labelHoraSeleccionada;
    private javax.swing.JLabel labelMesero;
    private javax.swing.JTextField textCorreo;
    private javax.swing.JTextField textoNombreCliente;
    private javax.swing.JTextField textoTelefono;
    // End of variables declaration//GEN-END:variables
}
