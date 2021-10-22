package telas;

import dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class TelaProfessor extends javax.swing.JInternalFrame {

   Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaProfessor() {
        conexao = ModuloConexao.connector();
        initComponents();
    }
     private void pesquisar_professor(){
     
        String sql = "select id_prof as Id,cpf_prof as CPF,nome_prof as Nome,sobrenome_prof as Sobrenome,data_de_nascimento_prof as DataDeNascimento,turno_prof as Turno,formacao_prof as Formação,telefone_prof as Telefone,email_prof as Email,rua_prof as Rua,numero as Número,bairro as Bairro, cidade as Cidade,cep as CEP,estado as Estado,matricula_prof as Matricula,vinculo_prof as Vinculo,id_turma as IdTurma from tb_professor where nome_prof  like ?";
        
     try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblProfessor.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
        private void setar_campos() {
        int setar = tblProfessor.getSelectedRow();
        txtIdProfessor.setText(tblProfessor.getModel().getValueAt(setar, 0).toString());
        txtCpf.setText(tblProfessor.getModel().getValueAt(setar, 1).toString());
        txtNome.setText(tblProfessor.getModel().getValueAt(setar, 2).toString());
        txtSobrenome.setText(tblProfessor.getModel().getValueAt(setar, 3).toString());
        txtDataNasc.setText(tblProfessor.getModel().getValueAt(setar, 4).toString());
        cboTurno.setSelectedItem(tblProfessor.getModel().getValueAt(setar, 5).toString());
        txtFormacao.setText(tblProfessor.getModel().getValueAt(setar, 6).toString());
        txtFone.setText(tblProfessor.getModel().getValueAt(setar, 7).toString());
        txtEmail.setText(tblProfessor.getModel().getValueAt(setar, 8).toString());
        txtRua.setText(tblProfessor.getModel().getValueAt(setar, 9).toString());
        txtNumero.setText(tblProfessor.getModel().getValueAt(setar, 10).toString());
        txtBairro.setText(tblProfessor.getModel().getValueAt(setar, 11).toString());
        txtCidade.setText(tblProfessor.getModel().getValueAt(setar, 12).toString());
        txtCep.setText(tblProfessor.getModel().getValueAt(setar, 13).toString());
        txtEstado.setText(tblProfessor.getModel().getValueAt(setar, 14).toString());
        txtMatricula.setText(tblProfessor.getModel().getValueAt(setar, 15).toString());
        txtVinculo.setText(tblProfessor.getModel().getValueAt(setar, 16).toString());
        txtIdTurma.setText(tblProfessor.getModel().getValueAt(setar, 17).toString());
        btAdicionar.setEnabled(false);
        txtTurma.setText(null);
        txtTurnoTurma.setText(null);
        }

        private void adicionar(){
         
            String sql = "insert into tb_professor(cpf_prof,nome_prof,sobrenome_prof,data_de_nascimento_prof,turno_prof,formacao_prof,telefone_prof,email_prof,rua_prof,numero,bairro,cidade,cep,estado,matricula_prof,vinculo_prof,id_turma) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            try{
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCpf.getText());
                pst.setString(2, txtNome.getText());
                pst.setString(3, txtSobrenome.getText());
                pst.setString(4, txtDataNasc.getText());
                pst.setString(5, cboTurno.getSelectedItem().toString());
                pst.setString(6, txtFormacao.getText());
                pst.setString(7, txtFone.getText());
                pst.setString(8, txtEmail.getText());
                pst.setString(9, txtRua.getText());
                pst.setString(10, txtNumero.getText());
                pst.setString(11, txtBairro.getText());
                pst.setString(12, txtCidade.getText());
                pst.setString(13, txtCep.getText());
                pst.setString(14, txtEstado.getText());
                pst.setString(15, txtMatricula.getText());
                pst.setString(16, txtVinculo.getText());
                pst.setString(17, txtIdTurma.getText());
                
                if((txtCpf.getText().isEmpty()) || (txtNome.getText().isEmpty())|| (txtSobrenome.getText().isEmpty())|| (txtDataNasc.getText().isEmpty()) || (txtFone.getText().isEmpty()) || (txtRua.getText().isEmpty())|| (txtNumero.getText().isEmpty())|| (txtBairro.getText().isEmpty())|| (txtCidade.getText().isEmpty())|| (txtCep.getText().isEmpty())|| (txtEstado.getText().isEmpty())|| (txtMatricula.getText().isEmpty())|| (txtVinculo.getText().isEmpty())|| (txtIdTurma.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                
                }else{
                    int adicionado = pst.executeUpdate();
                    if(adicionado > 0){
                        JOptionPane.showMessageDialog(null, "Professor adicionado!");
                   
                    txtIdProfessor.setText(null);
                    txtNome.setText(null);
                    txtDataNasc.setText(null);
                    cboTurno.setSelectedItem(null);
                    txtRua.setText(null);
                    txtCep.setText(null);
                    txtPesquisar.setText(null);
                    txtCidade.setText(null);
                    txtEmail.setText(null);
                    txtVinculo.setText(null);
                    txtNumero.setText(null);
                    txtFormacao.setText(null);
                    txtBairro.setText(null);
                    txtEstado.setText(null);
                    txtFone.setText(null);
                    txtMatricula.setText(null);
                    txtCpf.setText(null);
                    txtIdTurma.setText(null);
                    txtTurma.setText(null);
                    txtTurnoTurma.setText(null);
                    txtSobrenome.setText(null);

                    }
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        private void editar(){
        
        String sql = "update tb_professor set cpf_prof =?,nome_prof =?,sobrenome_prof =?,data_de_nascimento_prof =?,turno_prof =?,formacao_prof =?,telefone_prof =?,email_prof =?,rua_prof =?,numero =?,bairro =?,cidade =?,cep =?,estado =?,matricula_prof =?,vinculo_prof =?,id_turma =? where id_prof =?";
     try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCpf.getText());
                pst.setString(2, txtNome.getText());
                pst.setString(3, txtSobrenome.getText());
                pst.setString(4, txtDataNasc.getText());
                pst.setString(5, cboTurno.getSelectedItem().toString());
                pst.setString(6, txtFormacao.getText());
                pst.setString(7, txtFone.getText());
                pst.setString(8, txtEmail.getText());
                pst.setString(9, txtRua.getText());
                pst.setString(10, txtNumero.getText());
                pst.setString(11, txtBairro.getText());
                pst.setString(12, txtCidade.getText());
                pst.setString(13, txtCep.getText());
                pst.setString(14, txtEstado.getText());
                pst.setString(15, txtMatricula.getText());
                pst.setString(16, txtVinculo.getText());
                pst.setString(17, txtIdTurma.getText());
                pst.setString(18, txtIdProfessor.getText());
                
            
           if((txtIdProfessor.getText().isEmpty()) || (txtCpf.getText().isEmpty()) || (txtNome.getText().isEmpty())|| (txtSobrenome.getText().isEmpty())|| (txtDataNasc.getText().isEmpty()) || (txtFone.getText().isEmpty()) || (txtRua.getText().isEmpty())|| (txtNumero.getText().isEmpty())|| (txtBairro.getText().isEmpty())|| (txtCidade.getText().isEmpty())|| (txtCep.getText().isEmpty())|| (txtEstado.getText().isEmpty())|| (txtMatricula.getText().isEmpty())|| (txtVinculo.getText().isEmpty())|| (txtIdTurma.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do professor atualizados!");
                   
                   txtCpf.setText(null);
                   txtNome.setText(null);
                   txtSobrenome.setText(null);
                   txtDataNasc.setText(null);
                   cboTurno.setSelectedItem(null);
                   txtFormacao.setText(null);
                   txtFone.setText(null);
                   txtEmail.setText(null);
                   txtRua.setText(null);
                   txtNumero.setText(null);
                   txtBairro.setText(null);
                   txtCidade.setText(null);
                   txtCep.setText(null);
                   txtEstado.setText(null);
                   txtMatricula.setText(null);
                   txtVinculo.setText(null);
                   txtIdTurma.setText(null);
                   txtTurma.setText(null);
                   txtTurnoTurma.setText(null);
                }
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
        private void limpar(){
        
        txtIdProfessor.setText(null);
        txtNome.setText(null);
       
        txtDataNasc.setText(null);
        cboTurno.setSelectedItem(null);
        txtRua.setText(null);
        txtCep.setText(null);
        txtPesquisar.setText(null);
        txtCidade.setText(null);
        txtEmail.setText(null);
        txtVinculo.setText(null);
        txtNumero.setText(null);
        txtFormacao.setText(null);
        txtBairro.setText(null);
        txtEstado.setText(null);
        txtFone.setText(null);
        txtMatricula.setText(null);
        txtCpf.setText(null);
        txtIdTurma.setText(null);
        txtTurma.setText(null);
        txtTurnoTurma.setText(null);
        txtSobrenome.setText(null);
        btAdicionar.setEnabled(true);
        btPesquisar.setEnabled(true);
        //btPesquisarAluno.setEnabled(true);
        tblProfessor.setVisible(true);
        ((DefaultTableModel) tblProfessor.getModel()).setRowCount(0);
    }
         private void remover() {
        String sql = "delete from tb_professor where id_prof = ? ";
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse Professor?", "Atenção!", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdProfessor.getText());
                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Professor removido com sucesso!");
                
                txtIdProfessor.setText(null);
                txtNome.setText(null);
                cboTurno.setSelectedItem(null);
                txtDataNasc.setText(null);
                
                txtRua.setText(null);
                txtCep.setText(null);
                txtPesquisar.setText(null);
                txtCidade.setText(null);
                txtEmail.setText(null);
                txtVinculo.setText(null);
                txtNumero.setText(null);
                txtFormacao.setText(null);
                txtBairro.setText(null);
                txtEstado.setText(null);
                txtFone.setText(null);
                txtMatricula.setText(null);
                txtCpf.setText(null);
                txtIdTurma.setText(null);
                txtSobrenome.setText(null);
                }

            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
        private void pesquisar_turma(){

        String sql = "select * from tb_turma where id_turma =?";
        
        try{
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1,  txtIdTurma.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                txtTurma.setText(rs.getString(2));
                txtTurnoTurma.setText(rs.getString(3));

            }else{
                txtTurma.setText(null);
                txtTurnoTurma.setText(null);
                JOptionPane.showMessageDialog(null, "Turma não encontrada");
            }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
    }
    }  
         private void imprimir(){
           try{
        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\Carlos\\JaspersoftWorkspace\\MyReports\\ProfessorTurma.jrxml");
        
        JasperPrint print = JasperFillManager.fillReport(report, null, conexao);
        
        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setVisible(true);
        viewer.setTitle("Lista de Professores e suas Turmas");
        
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
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSobrenome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDataNasc = new javax.swing.JTextField();
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
        jLabel16 = new javax.swing.JLabel();
        txtIdTurma = new javax.swing.JTextField();
        txtIdProfessor = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProfessor = new javax.swing.JTable();
        txtPesquisar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFormacao = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtVinculo = new javax.swing.JTextField();
        btLimpar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtTurma = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        btEditar = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btExcluir = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btAdicionar = new javax.swing.JLabel();
        txtTurnoTurma = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btPesquisar = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cboTurno = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Professor");
        setPreferredSize(new java.awt.Dimension(880, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("*Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 267, 80, 30));
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 340, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("*Sobrenome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 110, 30));
        getContentPane().add(txtSobrenome, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 340, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("*Data de Nasc.");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, 30));
        getContentPane().add(txtDataNasc, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 140, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("*Telefone");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 80, 30));
        getContentPane().add(txtFone, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 200, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("Email");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 387, 80, 30));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 340, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("*Rua");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 60, 30));

        txtRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRuaActionPerformed(evt);
            }
        });
        getContentPane().add(txtRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 340, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("*Nº");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 50, 30));
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 230, 200, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setText("*Bairro");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 70, 30));
        getContentPane().add(txtBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 340, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("*Cidade");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 70, 30));
        getContentPane().add(txtCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 340, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setText("*CEP");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 50, 30));
        getContentPane().add(txtCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 200, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setText("*Estado");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 70, 27));
        getContentPane().add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 470, 200, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setText("*Matrícula");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 90, 30));
        getContentPane().add(txtMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, 200, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setText("*CPF");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 227, 50, 30));
        getContentPane().add(txtCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 340, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setText("*Id da Turma");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 510, 110, 30));
        getContentPane().add(txtIdTurma, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 510, 200, 30));

        txtIdProfessor.setEditable(false);
        getContentPane().add(txtIdProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 200, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Id Professor");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 100, 20));

        tblProfessor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "CPF", "Nome", "Sobrenome", "DataDeNascimento", "Turno", "Formação", "Telefone", "Email", "Rua", "Número", "Bairro", "Cidade", "CEP", "Estado", "Matricula", "Vinculo", "Título 18"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProfessorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProfessor);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 820, 100));

        txtPesquisar.setToolTipText("Digite o nome do professor que deseja visualizar");
        getContentPane().add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 270, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Formação");
        jLabel3.setToolTipText("Formação");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, 90, 30));
        getContentPane().add(txtFormacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, 200, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setText("*Vinculo");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 70, 30));
        getContentPane().add(txtVinculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 430, 200, 30));

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
        getContentPane().add(btLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 553, 100, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Nome da Turma");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, -1, -1));

        txtTurma.setEditable(false);
        txtTurma.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        getContentPane().add(txtTurma, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 200, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Turno da Turma");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 120, -1));

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar.png"))); // NOI18N
        btEditar.setToolTipText("Editar Professor");
        btEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        getContentPane().add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 570, 80, 80));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/print.png"))); // NOI18N
        jLabel23.setToolTipText("Imprimir");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 580, 70, 70));

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        btExcluir.setToolTipText("Excluir Professor");
        btExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btExcluirMouseClicked(evt);
            }
        });
        getContentPane().add(btExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 570, 80, 80));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton1.setText("Mais Info.");
        jButton1.setToolTipText("");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 610, 100, 30));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar Professor");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 70, 80));

        txtTurnoTurma.setEditable(false);
        getContentPane().add(txtTurnoTurma, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 200, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fundo-azul-e-branco-abstrato (1).jpg"))); // NOI18N
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 870, 470));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar(pequeno).png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setToolTipText("Pesquisar Professor");
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, 40));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel22.setText("Administração de Professores");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("*Turno");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 60, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel24.setText("* Campos obrigatórios");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

        cboTurno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cboTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Integral", "Noturno" }));
        cboTurno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTurnoActionPerformed(evt);
            }
        });
        jPanel1.add(cboTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRuaActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        pesquisar_professor();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void tblProfessorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProfessorMouseClicked
       setar_campos();
    }//GEN-LAST:event_tblProfessorMouseClicked

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btLimparActionPerformed

    private void btAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAdicionarMouseClicked
        adicionar();
        soma_prof();
        soma_alunos();
        soma_turma();
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

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
    imprimir();       
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    
    pesquisar_turma();        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTurnoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAdicionar;
    private javax.swing.JLabel btEditar;
    private javax.swing.JLabel btExcluir;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JComboBox<String> cboTurno;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProfessor;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtDataNasc;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFone;
    private javax.swing.JTextField txtFormacao;
    private javax.swing.JTextField txtIdProfessor;
    private javax.swing.JTextField txtIdTurma;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtSobrenome;
    private javax.swing.JTextField txtTurma;
    private javax.swing.JTextField txtTurnoTurma;
    private javax.swing.JTextField txtVinculo;
    // End of variables declaration//GEN-END:variables
}
