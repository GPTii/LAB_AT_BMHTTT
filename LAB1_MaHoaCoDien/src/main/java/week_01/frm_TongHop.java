package week_01;

import javax.swing.JOptionPane;

public class frm_TongHop extends javax.swing.JFrame {

    public frm_TongHop() {
        initComponents();
        setLocationRelativeTo(null);
    }

    // ===================== GIAO DIỆN =====================
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chương trình tổng hợp - 6 thuật toán");
        getContentPane().setLayout(null);

        jLabel1 = new javax.swing.JLabel("CHƯƠNG TRÌNH TỔNG HỢP 6 THUẬT TOÁN CỔ ĐIỂN");
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        jLabel1.setBounds(20, 10, 570, 34);
        getContentPane().add(jLabel1);

        jLabel2 = new javax.swing.JLabel("Thuật toán:");
        jLabel2.setBounds(20, 55, 100, 22);
        getContentPane().add(jLabel2);

        cbo_algorithm = new javax.swing.JComboBox<>(new String[] {
                "Caesar", "Vigenere", "Rail Fence", "Playfair", "Transposition", "Autokey" });
        cbo_algorithm.setBounds(120, 52, 200, 28);
        cbo_algorithm.addActionListener(evt -> cbo_algorithmActionPerformed(evt));
        getContentPane().add(cbo_algorithm);

        lbl_hint = new javax.swing.JLabel("Khóa mẫu: 3");
        lbl_hint.setBounds(330, 55, 260, 22);
        getContentPane().add(lbl_hint);

        jLabel3 = new javax.swing.JLabel("Plaintext:");
        jLabel3.setBounds(20, 90, 100, 22);
        getContentPane().add(jLabel3);

        txt_plaintext = new javax.swing.JTextArea();
        txt_plaintext.setLineWrap(true);
        txt_plaintext.setRows(3);
        jScrollPane1 = new javax.swing.JScrollPane(txt_plaintext);
        jScrollPane1.setBounds(20, 115, 570, 70);
        getContentPane().add(jScrollPane1);

        jLabel4 = new javax.swing.JLabel("Key:");
        jLabel4.setBounds(20, 197, 100, 22);
        getContentPane().add(jLabel4);

        txt_key = new javax.swing.JTextField("3");
        txt_key.setBounds(120, 194, 200, 28);
        getContentPane().add(txt_key);

        jLabel5 = new javax.swing.JLabel("Ciphertext:");
        jLabel5.setBounds(20, 230, 100, 22);
        getContentPane().add(jLabel5);

        txt_ciphertext = new javax.swing.JTextArea();
        txt_ciphertext.setLineWrap(true);
        txt_ciphertext.setRows(3);
        jScrollPane2 = new javax.swing.JScrollPane(txt_ciphertext);
        jScrollPane2.setBounds(20, 255, 570, 70);
        getContentPane().add(jScrollPane2);

        btn_encrypt = new javax.swing.JButton("Encrypt");
        btn_encrypt.setBounds(20, 340, 100, 30);
        btn_encrypt.addActionListener(evt -> btn_encryptActionPerformed(evt));
        getContentPane().add(btn_encrypt);

        btn_decrypt = new javax.swing.JButton("Decrypt");
        btn_decrypt.setBounds(130, 340, 100, 30);
        btn_decrypt.addActionListener(evt -> btn_decryptActionPerformed(evt));
        getContentPane().add(btn_decrypt);

        btn_open = new javax.swing.JButton("Open File");
        btn_open.setBounds(240, 340, 110, 30);
        btn_open.addActionListener(evt -> btn_openActionPerformed(evt));
        getContentPane().add(btn_open);

        btn_save = new javax.swing.JButton("Save File");
        btn_save.setBounds(360, 340, 110, 30);
        btn_save.addActionListener(evt -> btn_saveActionPerformed(evt));
        getContentPane().add(btn_save);

        btn_sql = new javax.swing.JButton("Kết nối PostgreSQL");
        btn_sql.setBounds(20, 380, 170, 30);
        btn_sql.addActionListener(evt -> btn_sqlActionPerformed(evt));
        getContentPane().add(btn_sql);

        btn_history = new javax.swing.JButton("Xem lịch sử");
        btn_history.setBounds(200, 380, 130, 30);
        btn_history.addActionListener(evt -> btn_historyActionPerformed(evt));
        getContentPane().add(btn_history);

        lbl_status = new javax.swing.JLabel("Sẵn sàng.");
        lbl_status.setBounds(20, 420, 570, 22);
        getContentPane().add(lbl_status);

        getContentPane().setPreferredSize(new java.awt.Dimension(610, 455));
        pack();
    }

    // ===================== SỰ KIỆN (code trong PDF) =====================
    private void cbo_algorithmActionPerformed(java.awt.event.ActionEvent evt) {
        String algorithm = (String) cbo_algorithm.getSelectedItem();
        txt_key.setText(CipherTongHop.sampleKey(algorithm));
        lbl_hint.setText("Khóa mẫu: " + CipherTongHop.sampleKey(algorithm));
    }

    private void btn_encryptActionPerformed(java.awt.event.ActionEvent evt) {
        String algorithm = (String) cbo_algorithm.getSelectedItem();
        String input = txt_plaintext.getText();
        try {
            String result = CipherTongHop.encrypt(algorithm, input, txt_key.getText());
            txt_ciphertext.setText(result);
            String saved = HistoryDao.tryInsert(algorithm, "ENCRYPT", input, result);
            lbl_status.setText("Đã mã hóa " + algorithm + ". " + saved);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_decryptActionPerformed(java.awt.event.ActionEvent evt) {
        String algorithm = (String) cbo_algorithm.getSelectedItem();
        String input = txt_ciphertext.getText();
        try {
            String result = CipherTongHop.decrypt(algorithm, input, txt_key.getText());
            txt_plaintext.setText(result);
            String saved = HistoryDao.tryInsert(algorithm, "DECRYPT", input, result);
            lbl_status.setText("Đã giải mã " + algorithm + ". " + saved);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_openActionPerformed(java.awt.event.ActionEvent evt) {
        String path = FileTextIO.openText(this, txt_ciphertext);
        if (path != null) {
            lbl_status.setText("Đã mở tệp: " + path);
        }
    }

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {
        String path = FileTextIO.saveText(this, txt_ciphertext, "ciphertext.txt");
        if (path != null) {
            lbl_status.setText("Đã lưu tệp: " + path);
        }
    }

    private void btn_sqlActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_KetNoiSQL().setVisible(true);
    }

    private void btn_historyActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_LichSu().setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_TongHop().setVisible(true));
    }

    // ===================== KHAI BÁO BIẾN =====================
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox<String> cbo_algorithm;
    private javax.swing.JLabel lbl_hint;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txt_plaintext;
    private javax.swing.JTextField txt_key;
    private javax.swing.JTextArea txt_ciphertext;
    private javax.swing.JButton btn_encrypt;
    private javax.swing.JButton btn_decrypt;
    private javax.swing.JButton btn_open;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_sql;
    private javax.swing.JButton btn_history;
    private javax.swing.JLabel lbl_status;
}