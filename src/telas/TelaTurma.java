package telas;

import java.awt.event.KeyEvent;
import dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
public class TelaTurma extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaTurma() {
        initComponents();
        conexao = ModuloConexao.connector();
    }
    private void adicionar(){
        
        String sql = "insert into tb_turma1(nome_turma,turno,id_escola) values (?,?,?)";
        
        try{
            
            pst = conexao.prepareStatement(sql); 
            //pst.setString(1, txtId.getText());
            pst.setString(1, txtNome.getText());
            pst.setString(2, cboTurno.getSelectedItem().toString());
            pst.setString(3, txtEscola.getText());
            
           if((txtNome.getText().isEmpty()) || (cboTurno.getSelectedItem().toString().isEmpty())) {
               JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
           
           }else{
               int adicionado = pst.executeUpdate();
               if(adicionado > 0){
                   
                JOptionPane.showMessageDialog(null, "Turma adicionada com sucesso!");   
                   //txtId.setText(null);
                   txtNome.setText(null);
                   cboTurno.setSelectedItem(null);
                   txtEscola.setText(null);
                   
               }
           }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void pesquisar(){
     
String sql = "select id_turma1 as Id,nome_turma as Nome,turno as Turno,id_escola as IdEscola from tb_turma where nome_turma  like ?";        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblTurma.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    private void setar_campos() {
        int setar = tblTurma.getSelectedRow();
        txtId.setText(tblTurma.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblTurma.getModel().getValueAt(setar, 1).toString());
        cboTurno.setSelectedItem(tblTurma.getModel().getValueAt(setar, 2).toString());
        txtEscola.setText(tblTurma.getModel().getValueAt(setar, 3).toString());
        txtNomeEscola.setText(null);
        txtCep.setText(null);
        txtCodigo.setText(null);
        
        btAdicionar.setEnabled(false);
    }

    
    private void editar(){
        
        String sql = "update tb_turma1 set nome_turma =?,turno =?,id_escola =? where id_turma =?";
     try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, cboTurno.getSelectedItem().toString());
            pst.setString(3, txtEscola.getText());
            pst.setString(4, txtId.getText());

            if ((txtId.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (cboTurno.getSelectedItem().toString().isEmpty()) || (txtEscola.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados da turma atualizados!");
                    txtNome.setText(null);
                    txtEscola.setText(null);
                    txtCodigo.setText(null);
                    txtNomeEscola.setText(null);
                    cboTurno.setSelectedItem(null);
                    txtEscola.setText(null);
                }
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar_escola(){
        
        String sql = "select * from tb_escola1 where id_escola=?";
        
        try{
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEscola.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                txtNomeEscola.setText(rs.getString(2));
                txtCodigo.setText(rs.getString(3));
                txtCep.setText(rs.getString(8));

            }else{
                txtNomeEscola.setText(null);
                txtCodigo.setText(null);
                txtCep.setText(null);
                JOptionPane.showMessageDialog(null, "Escola não encontrada");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
    }
    }    
    private void limpar(){
        
        txtId.setText(null);
        txtNome.setText(null);
        cboTurno.setSelectedItem(null);
        txtEscola.setText(null);
        txtNomeEscola.setText(null);
        txtCodigo.setText(null);
        txtCep.setText(null);
        txtPesquisar.setText(null);
        btAdicionar.setEnabled(true);
        btPesquisar.setEnabled(true);
        btPesquisarEs.setEnabled(true);
        tblTurma.setVisible(true);
        ((DefaultTableModel) tblTurma.getModel()).setRowCount(0);
}
     private void remover() {
        String sql = "delete from tb_turma1 where id_turma1 = ? ";
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover essa turma?", "Atenção!", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtId.getText());
                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Turma removida com sucesso!");
                txtNome.setText(null);
                cboTurno.setSelectedItem(null);
                txtEscola.setText(null);
                }

            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            }
            }
     private void soma_alunos(){
            
            String sql = "select count(*) from tb_aluno1";
            
            try{
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    String soma = rs.getString("count(*)");
                TelaPrincipal.txtAluno.setText(soma);
                }
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
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
                JOptionPane.showMessageDialog(null, e);
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
                JOptionPane.showMessageDialog(null, e);
            }
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEscola = new javax.swing.JTextField();
        btLimpar = new javax.swing.JButton();
        cboTurno = new javax.swing.JComboBox<>();
        btAdicionar = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btExcluir = new javax.swing.JLabel();
        btEditar = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNomeEscola = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        btPesquisarEs = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCep = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTurma = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtPesquisar = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Turma");
        setPreferredSize(new java.awt.Dimension(884, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Id Escola");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 150, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 120, 30));
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 650, 28));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Turno");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 120, 30));

        txtEscola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEscolaActionPerformed(evt);
            }
        });
        getContentPane().add(txtEscola, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 260, 28));

        btLimpar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btLimpar.setText("Limpar");
        btLimpar.setToolTipText("Limpar Campos");
        btLimpar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 480, 90, 30));

        cboTurno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cboTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Integral", "Noturno" }));
        cboTurno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(cboTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 110, 30));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar Turma");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 560, 70, 80));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel9.setText("Gerenciamento de Turmas");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 280, 50));

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        btExcluir.setToolTipText("Excluir Turma");
        btExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btExcluirMouseClicked(evt);
            }
        });
        getContentPane().add(btExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 560, 80, 80));

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar.png"))); // NOI18N
        btEditar.setToolTipText("Editar Turma");
        btEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        getContentPane().add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 560, 70, 80));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Nome da Escola");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 160, 30));

        txtNomeEscola.setEditable(false);
        getContentPane().add(txtNomeEscola, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 650, 28));

        txtCodigo.setEditable(false);
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 260, 28));

        btPesquisarEs.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btPesquisarEs.setText("Mais Info.");
        btPesquisarEs.setToolTipText("Mais informações");
        btPesquisarEs.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btPesquisarEs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisarEs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarEsActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisarEs, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 600, 130, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Código Inep");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 110, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("CEP");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 70, 30));

        txtCep.setEditable(false);
        getContentPane().add(txtCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 380, 250, 28));

        tblTurma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Turno", "Nome da Escola"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTurma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTurmaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTurma);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 810, 100));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fundo-azul-e-branco-abstrato (1).jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 870, 460));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPesquisar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
        });
        jPanel2.add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 320, 40));

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar(pequeno).png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setToolTipText("Pesquisar Turma");
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        jPanel2.add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, -1, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Id");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, -1, 40));

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 160, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel10.setText("Todos os campos são obrigatórios");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 230));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        pesquisar();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void txtEscolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEscolaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEscolaActionPerformed

    private void tblTurmaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTurmaMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblTurmaMouseClicked

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btLimparActionPerformed

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            pesquisar();
       }
    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void btAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAdicionarMouseClicked
        adicionar();
        soma_alunos();
        soma_turma();
        soma_prof();
    }//GEN-LAST:event_btAdicionarMouseClicked

    private void btEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEditarMouseClicked
        editar();
    }//GEN-LAST:event_btEditarMouseClicked

    private void btExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirMouseClicked
       remover();
       soma_alunos();
       soma_prof();
       soma_turma();
    }//GEN-LAST:event_btExcluirMouseClicked

    private void btPesquisarEsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarEsActionPerformed
        pesquisar_escola();
    }//GEN-LAST:event_btPesquisarEsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAdicionar;
    private javax.swing.JLabel btEditar;
    private javax.swing.JLabel btExcluir;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btPesquisarEs;
    private javax.swing.JComboBox<String> cboTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTurma;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEscola;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeEscola;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
