package telas;

import java.awt.event.KeyEvent;
import java.sql.*;
import dal.ModuloConexao;
import java.awt.Color;
import javax.swing.JOptionPane;
import static telas.TelaPrincipal.txtAluno;

public class TelaLogin extends javax.swing.JFrame {
    
        Connection  conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
    public void logar(){
            
        String sql = "select * from tb_usuarios1 where login_usuario = ? and senha_usuario =?";
        
        try{
        
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtLogin.getText());
        pst.setString(2, txtSenha.getText());
        
        rs = pst.executeQuery();
        
        if(rs.next()){
            
           String perfil_usuario = rs.getString(5); 
           
           if(perfil_usuario.equals ("admin")){
            
            TelaPrincipal principal = new TelaPrincipal();
            principal.setVisible(true);   
            TelaPrincipal.menuCadUser.setEnabled(true);
            TelaPrincipal.txtUsuario.setText(rs.getString(2));
            TelaPrincipal.txtUsuario.setForeground(Color.red);
            TelaPrincipal.menuEscola.setEnabled(true);
            
            
            this.dispose();    
           }
           if(perfil_usuario.equals ("user")){
            
            TelaPrincipal principal = new TelaPrincipal();
            principal.setVisible(true);
            
           }
        }else{
         
        JOptionPane.showMessageDialog(null, "Usuário/Senha inválidos!")  ;  
            
        txtLogin.setText(null);
        txtSenha.setText(null);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    public TelaLogin() {
        initComponents();
        
       conexao = ModuloConexao.connector();
        if (conexao != null){
            txtStatus.setText("Conectado");
            
        }else{
            txtStatus.setText("Desconectado");
            txtStatus.setForeground(Color.red);
            btLogar.setForeground(Color.red);
        }
    }

        private void soma_de_alunos(){
            
            String sql = "select count(*) from tb_aluno1";
            
            try{
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    String soma = rs.getString("count(*)");
                TelaPrincipal.txtAluno.setText(soma);
                }
                
            }catch(Exception e){
               
            }
        }
        
        
        private void soma_prof(){
            
            String sql = "select count(*) from tb_professor1";
            
            try{
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next()){
                
                String contar = rs.getString("count(*)");
                
                TelaPrincipal.txtProfessores.setText(contar);
            }
            }catch(Exception e){
                
            }
            }
        
        private void soma_turma(){
            
            String sql = "select count(*) from tb_turma1";
            
            try{
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    String contar = rs.getString("count(*)");
                    TelaPrincipal.txtTurmas.setText(contar);
                    
                }
                
            }catch(Exception e){
               
            }
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btLogar = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        txtLogin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel7.setText("jLabel7");

        jLabel10.setText("jLabel10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Limpar");
        jButton1.setPreferredSize(new java.awt.Dimension(63, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 80, 40));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/education_computer_school_graduate_cap_monitor_icon_149680.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 70, 70));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("SIED");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 40, -1));

        jLabel4.setText("Status");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, -1, -1));

        txtStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtStatus.setForeground(new java.awt.Color(0, 153, 0));
        getContentPane().add(txtStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 100, 16));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cadeado.png"))); // NOI18N
        jLabel2.setToolTipText("SENHA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, 50));

        btLogar.setBackground(new java.awt.Color(0, 0, 0));
        btLogar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btLogar.setText("Entrar");
        btLogar.setToolTipText("ENTRAR");
        btLogar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btLogar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogarActionPerformed(evt);
            }
        });
        getContentPane().add(btLogar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 90, 40));

        txtSenha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSenhaMouseClicked(evt);
            }
        });
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });
        getContentPane().add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 280, 31));

        txtLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        getContentPane().add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 280, 33));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuario.png"))); // NOI18N
        jLabel1.setToolTipText("USUÁRIO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 30, 33));

        jLabel5.setText("Informe usuário e senha para continuar!");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 46, 250, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("BEM-VINDO!");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fundo-azul-e-branco-abstrato (1).jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, -5, 350, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            logar();
            soma_de_alunos();
            soma_prof();
            soma_turma();
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSenhaMouseClicked

    }//GEN-LAST:event_txtSenhaMouseClicked

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

    private void btLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogarActionPerformed
        logar();
        soma_de_alunos();
        soma_prof();
        soma_turma();
    }//GEN-LAST:event_btLogarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables
}
