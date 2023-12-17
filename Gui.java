/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Bab10;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Gui extends javax.swing.JFrame {

    /**
     * Creates new form Gui
     */
    public Gui() {
        initComponents();
        tampil();
        
    }
public void batal() {
        tnama.setText("");
        thasil.setText("");
        tmlm.setText("");
        txtNIK.setText("");
        tbanyak.setText("");
        btnjk.clearSelection();
        btnmakanan.clearSelection();
        btnminuman.clearSelection();
    }
    String nama1, jk1,  makanan1, minuman1, type1;
    int jlm,mlm,total,bnyk,totalmk,totalmn,nik1;
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM tb_matkul WHERE kode_mk='" + tnama.getText() + "'";
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
        }
        refresh();
    }
     public Connection conn;
     public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/oop_penyewaan_kamar?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, es);
        }
    }
     public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("Nama");
        tabelhead.addColumn("Jenis Kelamin");
        tabelhead.addColumn("NIK");
        tabelhead.addColumn("Makanan");
        tabelhead.addColumn("Minuman");
        tabelhead.addColumn("Tipe Kamar");
        tabelhead.addColumn("Total Pembayaran");
               try {
            koneksi();
            String sql = "SELECT * FROM tb_kamar";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7),});
            }
            table_penyewaan.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
     public void kamar() {
        try {
            koneksi();
            String sql = "SELECT Kamar FROM tb_kamar order by nim asc";
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[3];
                ob[0] = res.getString(1);
                cmbkamar.addItem((String) ob[0]);
            }
            res.close();
            stt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
       public void refresh() {
        new Gui().setVisible(true);
        this.setVisible(false);
    }
    public void insert() {
        
        String nama = tnama.getText();
        String jk;
        
        if (Rlaki.isSelected()) {
            jk = "L";
        } else {
            jk = "P";
        }
        int nik = Integer.parseInt(txtNIK.getText());
        String makanan;
        int bnyk1,totalmk1;
       
        if (Cpotato.isSelected()) {
            makanan = "Potato";
            bnyk1 = Integer.parseInt(tbanyak.getText());
            totalmk1 = 50000*bnyk1 ;
        } else if (Csalad.isSelected()) {
          makanan = "Salad";
          bnyk1 = Integer.parseInt(tbanyak.getText());
          totalmk1 = 60000*bnyk1 ;
        } else {
          makanan= "Nasgor";
          bnyk1 = Integer.parseInt(tbanyak.getText());
          totalmk1 = 40000*bnyk1 ;
        }
        String minuman;
        int totalmn1;
        if (CApel.isSelected()) {
        minuman = "jusApel";
        totalmn1 = 10000 ;
        } else if (Calpukat.isSelected()) {
        minuman = "jusAlpukat";
        totalmn1 = 15000 ;
        } else {
        minuman = "jusMangga";
        totalmn1 = 20000;
        }
        
        String tipe = "";
        int mlm1;
        kamar1 kmr = new kamar1();
        if (cmbkamar.getSelectedIndex() == 0) {
            int total_tagihan;
            kmr.jenis = kmr.kmr1();
            kmr.malam = Integer.parseInt(tmlm.getText());
            total = kmr.total();
            total_tagihan = totalmn1+totalmk1+kmr.total();
        } 
        else if (cmbkamar.getSelectedIndex() == 1) {
            int total_tagihan,total1;
            kmr.jenis = kmr.kmr2();
            kmr.malam = Integer.parseInt(tmlm.getText());
            total = kmr.total();
            total_tagihan = totalmn1+totalmk1+kmr.total();
        } else if (cmbkamar.getSelectedIndex() == 2) {
            int total_tagihan,total1;
            kmr.jenis = kmr.kmr3();
            kmr.malam = Integer.parseInt(tmlm.getText());
            total = kmr.total();
            total_tagihan = totalmn1+totalmk1+kmr.total();
        }
        int total_tagihan;
        total_tagihan = totalmn1+totalmk1+kmr.total();
        try {
            koneksi();
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO tb_kamar (`Nama`,`Jenis Kelamin`,`NIK`,`Makanan`,`Minuman`,`Tipe Kamar`,`Total Pembayaran`)"
                    + "VALUES('" + nama + "','" + jk + "','" + nik + "','" + makanan + "','" + minuman + "','" + kmr.jenis + "',"
                    + "'" + total_tagihan + "')");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Nilai!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        }
        refresh();
    }
      public void update() {
        String jk;
        String nama = tnama.getText();
        int nik = Integer.parseInt(txtNIK.getText());
        if (Rlaki.isSelected()) {
            jk = "L";
        } else {
            jk = "P";
        }
        
        
        if (Rlaki.isSelected()) {
            jk = "L";
        } else {
            jk = "P";
        }
        String makanan;
        int bnyk1,totalmk1;
       
        if (Cpotato.isSelected()) {
            makanan = "Potato";
            bnyk1 = Integer.parseInt(tbanyak.getText());
            totalmk1 = 50000*bnyk1 ;
        } else if (Csalad.isSelected()) {
          makanan = "Salad";
          bnyk1 = Integer.parseInt(tbanyak.getText());
          totalmk1 = 60000*bnyk1 ;
        } else {
          makanan= "Nasgor";
          bnyk1 = Integer.parseInt(tbanyak.getText());
          totalmk1 = 40000*bnyk1 ;
        }
        String minuman;
        int totalmn1;
        if (CApel.isSelected()) {
        minuman = "jusApel";
        totalmn1 = 10000 ;
        } else if (Calpukat.isSelected()) {
        minuman = "jusAlpukat";
        totalmn1 = 15000 ;
        } else {
        minuman = "jusMangga";
        totalmn1 = 20000;
        }
        
        String tipe = "";
        int mlm1;
        kamar1 kmr = new kamar1();
        if (cmbkamar.getSelectedIndex() == 0) {
            int total_tagihan;
            kmr.jenis = kmr.kmr1();
            kmr.malam = Integer.parseInt(tmlm.getText());
            total = kmr.total();
            total_tagihan = totalmn1+totalmk1+kmr.total();
        } 
        else if (cmbkamar.getSelectedIndex() == 1) {
            int total_tagihan,total1;
            kmr.jenis = kmr.kmr2();
            kmr.malam = Integer.parseInt(tmlm.getText());
            total = kmr.total();
            total_tagihan = totalmn1+totalmk1+kmr.total();
        } else if (cmbkamar.getSelectedIndex() == 2) {
            int total_tagihan,total1;
            kmr.jenis = kmr.kmr3();
            kmr.malam = Integer.parseInt(tmlm.getText());
            total = kmr.total();
            total_tagihan = totalmn1+totalmk1+kmr.total();
        }
        int total_tagihan;
        total_tagihan = totalmn1+totalmk1+kmr.total();
        String namalama = nama;

        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE tb_kamar SET Nama='" + nama + "'," + "Kenis Kelamin='" + jk + "'"
                    + ",NIK='" + nik + "',Makanan='" + makanan + "',Minuman='" + minuman + "',NP3='" + kmr.jenis+ "',Tagihan='" + kmr.total() + "',Where Nama='" + namalama + "'");
            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Update Data Nilai!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnjk = new javax.swing.ButtonGroup();
        btnmakanan = new javax.swing.ButtonGroup();
        btnminuman = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tnama = new javax.swing.JTextField();
        Rlaki = new javax.swing.JRadioButton();
        Rperempuan = new javax.swing.JRadioButton();
        CApel = new javax.swing.JCheckBox();
        Cpotato = new javax.swing.JCheckBox();
        Calpukat = new javax.swing.JCheckBox();
        Csalad = new javax.swing.JCheckBox();
        Cmangga = new javax.swing.JCheckBox();
        CNasgor = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        cmbkamar = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tbanyak = new javax.swing.JTextField();
        tmlm = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_penyewaan = new javax.swing.JTable();
        txtNIK = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnsimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        thasil = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Penyewaan Kamar Hotel");

        jLabel2.setText("Nama Pengunjung");

        jLabel3.setText("Jenis Kelamin");

        jLabel4.setText("Makanan & Minuman");

        btnjk.add(Rlaki);
        Rlaki.setText("Laki-Laki");

        btnjk.add(Rperempuan);
        Rperempuan.setText("Perempuan");

        btnminuman.add(CApel);
        CApel.setText("Juice Aple");

        btnmakanan.add(Cpotato);
        Cpotato.setText("Potato Wadges");

        btnminuman.add(Calpukat);
        Calpukat.setText("Jus Alpukat");

        btnmakanan.add(Csalad);
        Csalad.setText("Salad Buah");

        btnminuman.add(Cmangga);
        Cmangga.setText("Jus Mangga");

        btnmakanan.add(CNasgor);
        CNasgor.setText("Nasi Goreng");
        CNasgor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CNasgorActionPerformed(evt);
            }
        });

        jLabel5.setText("Type Kamar");

        cmbkamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "StandartRoom", "Deluxe", "FamilyRoom", " " }));

        jLabel6.setText("Banyak Pesanan");

        jLabel7.setText("Berapa Malam");

        table_penyewaan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama", "Jenis Kelamin", "NIK", "Makanan", "Minuman", "Type Kamar", "Total Tagihan"
            }
        ));
        jScrollPane2.setViewportView(table_penyewaan);

        jLabel9.setText("Masukan NIK Anda");

        btnsimpan.setText("Simpian");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnHapus.setText("hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel8.setText("total Tagihan Kamar");

        thasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thasilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9))
                        .addGap(119, 119, 119)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Rlaki, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CApel)
                                            .addComponent(Csalad))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(402, 402, 402)
                                                .addComponent(CNasgor))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(173, 173, 173)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(Rperempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Cpotato)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(Calpukat)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(Cmangga)))))))
                                    .addComponent(txtNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(261, 261, 261)
                                        .addComponent(jLabel1)))
                                .addGap(238, 238, 238))
                            .addComponent(tnama)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbkamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpdate)))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGap(145, 145, 145)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tbanyak, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                    .addComponent(tmlm)
                                    .addComponent(thasil))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(Rperempuan)
                    .addComponent(Rlaki))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNIK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CApel)
                    .addComponent(Calpukat)
                    .addComponent(Cmangga))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cpotato)
                    .addComponent(Csalad)
                    .addComponent(CNasgor))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbkamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tbanyak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tmlm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnsimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(thasil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CNasgorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CNasgorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CNasgorActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        insert();
       
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
    delete();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
     batal();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void thasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thasilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thasilActionPerformed

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
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CApel;
    private javax.swing.JCheckBox CNasgor;
    private javax.swing.JCheckBox Calpukat;
    private javax.swing.JCheckBox Cmangga;
    private javax.swing.JCheckBox Cpotato;
    private javax.swing.JCheckBox Csalad;
    private javax.swing.JRadioButton Rlaki;
    private javax.swing.JRadioButton Rperempuan;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup btnjk;
    private javax.swing.ButtonGroup btnmakanan;
    private javax.swing.ButtonGroup btnminuman;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox<String> cmbkamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_penyewaan;
    private javax.swing.JTextField tbanyak;
    private javax.swing.JTextField thasil;
    private javax.swing.JTextField tmlm;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField txtNIK;
    // End of variables declaration//GEN-END:variables
}
