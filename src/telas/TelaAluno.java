package telas;
import java.sql.*;
import dal.ModuloConexao;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class TelaAluno extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaAluno() {
        initComponents();
        
        conexao = ModuloConexao.connector();
    }
    
    private void adicionar(){
        
        String sql = "insert into tb_aluno(cpf_aluno,nome_aluno,sobrenome_aluno,data_nasc_aluno,turno_aluno,telefone_aluno,email_aluno,rua_aluno,numero,bairro,cidade,cep,estado,matricula_aluno,id_prof,id_turma) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
        pst = conexao.prepareStatement(sql);
        //pst.setString(1,  txtId.getText());
        pst.setString(1,  txtCpf.getText());
        pst.setString(2,  txtNome.getText());
        pst.setString(3,  txtSobrenome.getText());
        pst.setString(4,  txtDataNasc.getText());
        pst.setString(5,  cboTurno.getSelectedItem().toString());
        pst.setString(6,  txtFone.getText());
        pst.setString(7,  txtEmail.getText());
        pst.setString(8,  txtRua.getText());
        pst.setString(9, txtNumero.getText());
        pst.setString(10, txtBairro.getText());
        pst.setString(11, txtCidade.getText());
        pst.setString(12, txtCep.getText());
        pst.setString(13, txtEstado.getText());
        pst.setString(14, txtMatricula.getText());
        pst.setString(15, txtIdProfessor.getText());
        pst.setString(16, txtIdTurma.getText());
       
        
        if((txtNome.getText().isEmpty()) || (txtCpf.getText().isEmpty()) || (txtSobrenome.getText().isEmpty())||(txtDataNasc.getText().isEmpty())||(cboTurno.getSelectedItem().toString().isEmpty()) ||(txtFone.getText().isEmpty()) ||(txtBairro.getText().isEmpty()) ||(txtRua.getText().isEmpty()) ||(txtNumero.getText().isEmpty())||(txtCidade.getText().isEmpty()) ||(txtCep.getText().isEmpty())||(txtEstado.getText().isEmpty()) ||(txtMatricula.getText().isEmpty())) {
               JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        
        }else{
            int adicionado = pst.executeUpdate();
            
            if(adicionado>0){
            
            JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso!");   
                    
                    txtCpf.setText(null);
                    txtNome.setText(null);
                    txtSobrenome.setText(null);
                    txtDataNasc.setText(null);
                    cboTurno.setSelectedItem(null);
                    txtNomeTurma.setText(null);
                    txtFone.setText(null);
                    txtEmail.setText(null);
                    txtRua.setText(null);
                    txtNumero.setText(null);
                    txtBairro.setText(null);
                    txtCidade.setText(null);
                    txtCep.setText(null);
                    txtEstado.setText(null);
                    txtMatricula.setText(null);
                    txtIdProfessor.setText(null);
                    txtIdTurma.setText(null);
                    txtNomeProf.setText(null);
                    txtSobrenomeProf.setText(null);
                    txtEmailProf.setText(null);
                   
        } 
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar(){
     
        String sql = "select * from tb_aluno where nome_aluno like ?";
        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblAluno.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void setar_campos() {
        int setar = tblAluno.getSelectedRow();
        txtId.setText(tblAluno.getModel().getValueAt(setar, 0).toString());
        txtCpf.setText(tblAluno.getModel().getValueAt(setar, 1).toString());
        txtNome.setText(tblAluno.getModel().getValueAt(setar, 2).toString());
        txtSobrenome.setText(tblAluno.getModel().getValueAt(setar, 3).toString());
        txtDataNasc.setText(tblAluno.getModel().getValueAt(setar, 4).toString());
        cboTurno.setSelectedItem(tblAluno.getModel().getValueAt(setar, 5).toString());
        txtFone.setText(tblAluno.getModel().getValueAt(setar, 6).toString());
        txtEmail.setText(tblAluno.getModel().getValueAt(setar, 7).toString());
        txtRua.setText(tblAluno.getModel().getValueAt(setar, 8).toString());
        txtNumero.setText(tblAluno.getModel().getValueAt(setar, 9).toString());
        txtBairro.setText(tblAluno.getModel().getValueAt(setar, 10).toString());
        txtCidade.setText(tblAluno.getModel().getValueAt(setar, 11).toString());
        txtCep.setText(tblAluno.getModel().getValueAt(setar, 12).toString());
        txtEstado.setText(tblAluno.getModel().getValueAt(setar, 13).toString());
        txtMatricula.setText(tblAluno.getModel().getValueAt(setar, 14).toString());
        txtIdProfessor.setText(tblAluno.getModel().getValueAt(setar, 15).toString());
        txtIdTurma.setText(tblAluno.getModel().getValueAt(setar, 16).toString());
        btAdicionar.setEnabled(false);
        txtNomeProf.setText(null);
        txtSobrenomeProf.setText(null);
        txtEmailProf.setText(null);
        txtNomeTurma.setText(null);
        }
     private void consultar() {
        String sql = "select * from tb_professor where id_prof =?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdProfessor.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNomeProf.setText(rs.getString(3));
                txtSobrenomeProf.setText(rs.getString(4));
                txtEmailProf.setText(rs.getString(9));

            } else {
                txtNomeProf.setText(null);
                txtSobrenomeProf.setText(null);
                txtEmailProf.setText(null);
                JOptionPane.showMessageDialog(null, "Professor não cadastrado!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
      private void consultar_turma() {
        String sql = "select * from tb_turma where id_turma =?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdTurma.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNomeTurma.setText(rs.getString(2));

            } else {
                txtNomeTurma.setText(null);
                JOptionPane.showMessageDialog(null, "Turma não encontrada!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
      private void editar(){
        
        String sql = "update tb_aluno set cpf_aluno =?,nome_aluno =?,sobrenome_aluno =?,data_nasc_aluno =?,turno_aluno =?,telefone_aluno =?,email_aluno =?,rua_aluno =?,numero =?,bairro =?,cidade =?,cep =?,estado =?,matricula_aluno =?,id_prof =?,id_turma =? where id_aluno =?";
     try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCpf.getText());
                pst.setString(2, txtNome.getText());
                pst.setString(3, txtSobrenome.getText());
                pst.setString(4, txtDataNasc.getText());
                pst.setString(5, cboTurno.getSelectedItem().toString());
                pst.setString(6, txtFone.getText());
                pst.setString(7, txtEmail.getText());
                pst.setString(8, txtRua.getText());
                pst.setString(9, txtNumero.getText());
                pst.setString(10, txtBairro.getText());
                pst.setString(11, txtCidade.getText());
                pst.setString(12, txtCep.getText());
                pst.setString(13, txtEstado.getText());
                pst.setString(14, txtMatricula.getText());
                pst.setString(15, txtIdProfessor.getText());
                pst.setString(16, txtIdTurma.getText());
                pst.setString(17, txtId.getText());
            
            
           if((txtId.getText().isEmpty()) || (txtCpf.getText().isEmpty()) || (txtNome.getText().isEmpty())|| (txtSobrenome.getText().isEmpty())|| (txtDataNasc.getText().isEmpty()) || (txtFone.getText().isEmpty()) || (txtRua.getText().isEmpty())|| (txtNumero.getText().isEmpty())|| (txtBairro.getText().isEmpty())|| (txtCidade.getText().isEmpty())|| (txtCep.getText().isEmpty())|| (txtEstado.getText().isEmpty())|| (txtMatricula.getText().isEmpty())|| (txtIdTurma.getText().isEmpty())|| (txtIdProfessor.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {
                int editado = pst.executeUpdate();

                if (editado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do aluno atualizados!");
                   
                    txtCpf.setText(null);
                    txtNome.setText(null);
                    txtSobrenome.setText(null);
                    txtDataNasc.setText(null);
                    cboTurno.setSelectedItem(null);
                    txtNomeTurma.setText(null);
                    txtFone.setText(null);
                    txtEmail.setText(null);
                    txtRua.setText(null);
                    txtNumero.setText(null);
                    txtBairro.setText(null);
                    txtCidade.setText(null);
                    txtCep.setText(null);
                    txtEstado.setText(null);
                    txtMatricula.setText(null);
                    txtIdProfessor.setText(null);
                    txtIdTurma.setText(null);
                    txtNomeProf.setText(null);
                    txtSobrenomeProf.setText(null);
                    txtEmailProf.setText(null);
                }
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
      private void limpar(){
        
        txtId.setText(null);
        txtIdProfessor.setText(null);
        txtNome.setText(null);
        txtDataNasc.setText(null);
        cboTurno.setSelectedItem(null);
        txtRua.setText(null);
        txtCep.setText(null);
        txtPesquisar.setText(null);
        txtCidade.setText(null);
        txtEmail.setText(null);
        txtNomeTurma.setText(null);
        txtNumero.setText(null);
        txtNomeProf.setText(null);
        txtBairro.setText(null);
        txtEstado.setText(null);
        txtFone.setText(null);
        txtMatricula.setText(null);
        txtCpf.setText(null);
        txtIdTurma.setText(null);
        txtSobrenomeProf.setText(null);
        txtEmailProf.setText(null);
        txtSobrenome.setText(null);
        btAdicionar.setEnabled(true);
        btPesquisar.setEnabled(true);
        //btPesquisarAluno.setEnabled(true);
        tblAluno.setVisible(true);
        ((DefaultTableModel) tblAluno.getModel()).setRowCount(0);
    }
      
      private void remover(){
          
          String sql = "delete from tb_aluno where id_aluno =?";
          
          try{
              pst = conexao.prepareStatement(sql);
              pst.setString(1, txtId.getText());
              
              int apagado = pst.executeUpdate();
              
              if (apagado > 0){
                  JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!");
                
                  txtId.setText(null);
                txtIdProfessor.setText(null);
                txtNome.setText(null);
                txtDataNasc.setText(null);
                cboTurno.setSelectedItem(null);
                txtRua.setText(null);
                txtCep.setText(null);
                txtPesquisar.setText(null);
                txtCidade.setText(null);
                txtEmail.setText(null);
                txtNomeTurma.setText(null);
                txtNumero.setText(null);
                txtNomeProf.setText(null);
                txtBairro.setText(null);
                txtEstado.setText(null);
                txtFone.setText(null);
                txtMatricula.setText(null);
                txtCpf.setText(null);
                txtIdTurma.setText(null);
                txtSobrenomeProf.setText(null);
                txtEmailProf.setText(null);
                txtSobrenome.setText(null);
                tblAluno.setVisible(true);
        ((DefaultTableModel) tblAluno.getModel()).setRowCount(0);
          }
              
              
              
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
          }
      }
      
       private void imprimir(){
           try{
       JasperReport report = JasperCompileManager.compileReport("C:\\Users\\Carlos\\JaspersoftWorkspace\\MyReports\\ProfessorAluno.jrxml");
        
        JasperPrint print = JasperFillManager.fillReport(report, null, conexao);
        
        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setVisible(true);
        viewer.setTitle("Lista de Professores e Alunos");
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
     }
       private void soma_alunos(){
            
            String sql = "select count(*) from tb_aluno";
            
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
            
            String sql = "select count(*) from tb_professor";
            
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
            
            String sql = "select count(*) from tb_turma";
            
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
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSobrenome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDataNasc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCep = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtIdTurma = new javax.swing.JTextField();
        txtIdProfessor = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAluno = new javax.swing.JTable();
        txtPesquisar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btAdicionar = new javax.swing.JLabel();
        cboTurno = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        btEditar = new javax.swing.JLabel();
        btExcluir = new javax.swing.JLabel();
        txtNomeProf = new javax.swing.JTextField();
        txtSobrenomeProf = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtEmailProf = new javax.swing.JTextField();
        txtNomeTurma = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Aluno");
        setPreferredSize(new java.awt.Dimension(880, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("*Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 240, 60, -1));
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 370, 25));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("*Sobrenome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 110, 20));

        txtSobrenome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSobrenomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtSobrenome, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 370, 25));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("*Data de Nasc.");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 130, 30));
        getContentPane().add(txtDataNasc, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 200, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("*Turno");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, 70, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("*Telefone");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 100, 30));
        getContentPane().add(txtFone, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 280, 200, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("Email");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 80, 30));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 370, 25));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("*Rua");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 60, 25));
        getContentPane().add(txtRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 370, 25));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("*Nº");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(511, 360, 40, 30));
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 360, 200, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setText("*Bairro");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 80, 30));
        getContentPane().add(txtBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, 370, 25));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("*Cidade");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 80, 30));
        getContentPane().add(txtCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 370, 25));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setText("*CEP");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 400, 50, 30));
        getContentPane().add(txtCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 400, 200, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setText("*Estado");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 80, 27));
        getContentPane().add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 370, 25));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setText("*Matrícula");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, 90, 30));
        getContentPane().add(txtMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 440, 200, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setText("*CPF");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(511, 320, 50, 30));
        getContentPane().add(txtCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 200, 30));

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar(pequeno).png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setToolTipText("Pesquisar Aluno");
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setText("*Id Turma");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 90, 30));
        getContentPane().add(txtIdTurma, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 520, 120, 25));
        getContentPane().add(txtIdProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 520, 110, 25));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setText("*Id Professor");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 520, 110, 30));

        tblAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Sobrenome", "CPF", "Matrícula"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlunoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAluno);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 820, 100));
        getContentPane().add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 330, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton1.setText("Limpar");
        jButton1.setToolTipText("Limpar Campos");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 480, 90, 30));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar Aluno");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 70, 80));

        cboTurno.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cboTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Integral", "Noturno" }));
        getContentPane().add(cboTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 100, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setText("Nome da Turma");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 140, -1));

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar.png"))); // NOI18N
        btEditar.setToolTipText("Editar dados do Aluno");
        btEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        getContentPane().add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 570, 80, 80));

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        btExcluir.setToolTipText("Excluir Aluno");
        btExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btExcluirMouseClicked(evt);
            }
        });
        getContentPane().add(btExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 570, 80, 80));

        txtNomeProf.setEditable(false);
        txtNomeProf.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtNomeProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeProfActionPerformed(evt);
            }
        });
        getContentPane().add(txtNomeProf, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 180, -1));

        txtSobrenomeProf.setEditable(false);
        txtSobrenomeProf.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        getContentPane().add(txtSobrenomeProf, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 180, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/print.png"))); // NOI18N
        jLabel23.setToolTipText("Imprimir");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 570, 70, 80));

        txtEmailProf.setEditable(false);
        txtEmailProf.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        getContentPane().add(txtEmailProf, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 180, -1));

        txtNomeTurma.setEditable(false);
        txtNomeTurma.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        getContentPane().add(txtNomeTurma, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, 180, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton2.setText("Mais Info.");
        jButton2.setToolTipText("Mais Informações");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 609, 120, 30));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fundo-azul-e-branco-abstrato (1).jpg"))); // NOI18N
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 220, 880, 450));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setText("Email");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 60, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setText("Sobrenome");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 110, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setText("Nome do Prof.");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 130, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Id Aluno");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 80, -1));

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 70, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel24.setText("Gerenciamento de Alunos");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel25.setText("* Campos obrigatórios");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 220));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        pesquisar();
        
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void txtSobrenomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSobrenomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSobrenomeActionPerformed

    private void btAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAdicionarMouseClicked
       adicionar();
       soma_alunos();
       soma_prof();
       soma_turma();
    }//GEN-LAST:event_btAdicionarMouseClicked

    private void tblAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlunoMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblAlunoMouseClicked

    private void txtNomeProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeProfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeProfActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       consultar();
       consultar_turma();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       limpar();
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEditarMouseClicked
       editar();
       limpar();
    }//GEN-LAST:event_btEditarMouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        imprimir();
    }//GEN-LAST:event_jLabel23MouseClicked

    private void btExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirMouseClicked
       remover();
       soma_alunos();
       soma_prof();
       soma_turma();
       
    }//GEN-LAST:event_btExcluirMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAdicionar;
    private javax.swing.JLabel btEditar;
    private javax.swing.JLabel btExcluir;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JComboBox<String> cboTurno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAluno;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtDataNasc;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailProf;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFone;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdProfessor;
    private javax.swing.JTextField txtIdTurma;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeProf;
    private javax.swing.JTextField txtNomeTurma;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtSobrenome;
    private javax.swing.JTextField txtSobrenomeProf;
    // End of variables declaration//GEN-END:variables
}
