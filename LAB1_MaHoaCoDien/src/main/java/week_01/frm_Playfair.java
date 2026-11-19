package week_01;

import javax.swing.JOptionPane;

public class frm_Playfair extends javax.swing.JFrame {

    public frm_Playfair() {
        initComponents();
        setLocationRelativeTo(null);
    }

    // ===================== GIAO DIỆN =====================
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Playfair Cipher");
        getContentPane().setLayout(null);

        jLabel1 = new javax.swing.JLabel("MẬT MÃ PLAYFAIR");
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        jLabel1.setBounds(20, 10, 570, 34);
        getContentPane().add(jLabel1);

        jLabel2 = new javax.swing.JLabel("Plaintext:");
        jLabel2.setBounds(20, 50, 100, 22);
        getContentPane().add(jLabel2);

        txt_plaintext = new javax.swing.JTextArea();
        txt_plaintext.setLineWrap(true);
        txt_plaintext.setRows(3);
        jScrollPane1 = new javax.swing.JScrollPane(txt_plaintext);
        jScrollPane1.setBounds(20, 75, 570, 70);
        getContentPane().add(jScrollPane1);

        jLabel3 = new javax.swing.JLabel("Key:");
        jLabel3.setBounds(20, 155, 100, 22);
        getContentPane().add(jLabel3);

        txt_key = new javax.swing.JTextField("KEYWORD");
        txt_key.setBounds(20, 180, 150, 28);
        getContentPane().add(txt_key);

        jLabel4 = new javax.swing.JLabel("Chỉ gồm chữ A-Z, ví dụ KEYWORD");
        jLabel4.setBounds(180, 183, 410, 22);
        getContentPane().add(jLabel4);

        // ---- Riêng Playfair: ô hiển thị ma trận khóa 5x5 ----
        jLabel6 = new javax.swing.JLabel("Key Matrix:");
        jLabel6.setBounds(20, 230, 100, 22);
        getContentPane().add(jLabel6);

        txt_matrix = new javax.swing.JTextArea();
        txt_matrix.setEditable(false);
        txt_matrix.setLineWrap(false);
        txt_matrix.setRows(5);
        txt_matrix.setColumns(9);
        txt_matrix.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 14));
        jScrollPane3 = new javax.swing.JScrollPane(txt_matrix);
        jScrollPane3.setBounds(20, 255, 170, 112);
        getContentPane().add(jScrollPane3);

        btn_matrix = new javax.swing.JButton("Generate Key Matrix");
        btn_matrix.setBounds(210, 255, 170, 30);
        btn_matrix.addActionListener(evt -> btn_matrixActionPerformed(evt));
        getContentPane().add(btn_matrix);

        jLabel5 = new javax.swing.JLabel("Ciphertext:");
        jLabel5.setBounds(20, 370, 100, 22);
        getContentPane().add(jLabel5);

        txt_ciphertext = new javax.swing.JTextArea();
        txt_ciphertext.setLineWrap(true);
        txt_ciphertext.setRows(3);
        jScrollPane2 = new javax.swing.JScrollPane(txt_ciphertext);
        jScrollPane2.setBounds(20, 395, 570, 70);
        getContentPane().add(jScrollPane2);

        btn_encrypt = new javax.swing.JButton("Encrypt");
        btn_encrypt.setBounds(20, 480, 100, 30);
        btn_encrypt.addActionListener(evt -> btn_encryptActionPerformed(evt));
        getContentPane().add(btn_encrypt);

        btn_decrypt = new javax.swing.JButton("Decrypt");
        btn_decrypt.setBounds(130, 480, 100, 30);
        btn_decrypt.addActionListener(evt -> btn_decryptActionPerformed(evt));
        getContentPane().add(btn_decrypt);

        btn_open = new javax.swing.JButton("Open Ciphertext File");
        btn_open.setBounds(240, 480, 170, 30);
        btn_open.addActionListener(evt -> btn_openActionPerformed(evt));
        getContentPane().add(btn_open);

        btn_save = new javax.swing.JButton("Save Ciphertext File");
        btn_save.setBounds(420, 480, 170, 30);
        btn_save.addActionListener(evt -> btn_saveActionPerformed(evt));
        getContentPane().add(btn_save);

        lbl_status = new javax.swing.JLabel("Sẵn sàng.");
        lbl_status.setBounds(20, 520, 570, 22);
        getContentPane().add(lbl_status);

        getContentPane().setPreferredSize(new java.awt.Dimension(610, 555));
        pack();
    }

    // ===================== SỰ KIỆN =====================
    private void btn_encryptActionPerformed(java.awt.event.ActionEvent evt) {
        String plaintext = txt_plaintext.getText();
        if (plaintext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
            return;
        }
        try {
            String key = txt_key.getText().trim();
            txt_ciphertext.setText(PlayfairCipher.encrypt(plaintext, key));
            lbl_status.setText("Đã mã hóa Playfair (bỏ dấu cách, J -> I, chèn X).");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_decryptActionPerformed(java.awt.event.ActionEvent evt) {
        String ciphertext = txt_ciphertext.getText();
        if (ciphertext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext trước khi giải mã.");
            return;
        }
        try {
            String key = txt_key.getText().trim();
            txt_plaintext.setText(PlayfairCipher.decrypt(ciphertext, key));
            lbl_status.setText("Đã giải mã Playfair (còn ký tự đệm X).");
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
        String path = FileTextIO.saveText(this, txt_ciphertext, "playfair_ciphertext.txt");
        if (path != null) {
            lbl_status.setText("Đã lưu tệp: " + path);
        }
    }

    private void btn_matrixActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            txt_matrix.setText(PlayfairCipher.getKeyMatrix(txt_key.getText()));
            txt_matrix.setCaretPosition(0);
            lbl_status.setText("Đã tạo ma trận khóa 5x5 (I và J dùng chung ô).");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Khóa không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_Playfair().setVisible(true));
    }

    // ===================== KHAI BÁO BIẾN =====================
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txt_plaintext;
    private javax.swing.JTextField txt_key;
    private javax.swing.JTextArea txt_ciphertext;
    private javax.swing.JButton btn_encrypt;
    private javax.swing.JButton btn_decrypt;
    private javax.swing.JButton btn_open;
    private javax.swing.JButton btn_save;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txt_matrix;
    private javax.swing.JButton btn_matrix;
}