package week_01;

import javax.swing.JOptionPane;

public class frm_RailFence extends javax.swing.JFrame {

    public frm_RailFence() {
        initComponents();
        setLocationRelativeTo(null);
    }

    // ===================== GIAO DIỆN =====================
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rail Fence Cipher");
        getContentPane().setLayout(null);

        jLabel1 = new javax.swing.JLabel("MẬT MÃ RAIL FENCE");
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

        txt_key = new javax.swing.JTextField("3");
        txt_key.setBounds(20, 180, 150, 28);
        getContentPane().add(txt_key);

        jLabel4 = new javax.swing.JLabel("Số hàng (rails) >= 2, ví dụ 3");
        jLabel4.setBounds(180, 183, 410, 22);
        getContentPane().add(jLabel4);

        jLabel5 = new javax.swing.JLabel("Ciphertext:");
        jLabel5.setBounds(20, 220, 100, 22);
        getContentPane().add(jLabel5);

        txt_ciphertext = new javax.swing.JTextArea();
        txt_ciphertext.setLineWrap(true);
        txt_ciphertext.setRows(3);
        jScrollPane2 = new javax.swing.JScrollPane(txt_ciphertext);
        jScrollPane2.setBounds(20, 245, 570, 70);
        getContentPane().add(jScrollPane2);

        btn_encrypt = new javax.swing.JButton("Encrypt");
        btn_encrypt.setBounds(20, 330, 100, 30);
        btn_encrypt.addActionListener(evt -> btn_encryptActionPerformed(evt));
        getContentPane().add(btn_encrypt);

        btn_decrypt = new javax.swing.JButton("Decrypt");
        btn_decrypt.setBounds(130, 330, 100, 30);
        btn_decrypt.addActionListener(evt -> btn_decryptActionPerformed(evt));
        getContentPane().add(btn_decrypt);

        btn_open = new javax.swing.JButton("Open Ciphertext File");
        btn_open.setBounds(240, 330, 170, 30);
        btn_open.addActionListener(evt -> btn_openActionPerformed(evt));
        getContentPane().add(btn_open);

        btn_save = new javax.swing.JButton("Save Ciphertext File");
        btn_save.setBounds(420, 330, 170, 30);
        btn_save.addActionListener(evt -> btn_saveActionPerformed(evt));
        getContentPane().add(btn_save);

        lbl_status = new javax.swing.JLabel("Sẵn sàng.");
        lbl_status.setBounds(20, 370, 570, 22);
        getContentPane().add(lbl_status);

        getContentPane().setPreferredSize(new java.awt.Dimension(610, 405));
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
            int rails = Integer.parseInt(txt_key.getText().trim());
            txt_ciphertext.setText(RailFenceCipher.encrypt(plaintext, rails));
            lbl_status.setText("Đã mã hóa với " + rails + " hàng.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số hàng phải là số nguyên, ví dụ 3.",
                    "Khóa không hợp lệ", JOptionPane.ERROR_MESSAGE);
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
            int rails = Integer.parseInt(txt_key.getText().trim());
            txt_plaintext.setText(RailFenceCipher.decrypt(ciphertext, rails));
            lbl_status.setText("Đã giải mã với " + rails + " hàng.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số hàng phải là số nguyên, ví dụ 3.",
                    "Khóa không hợp lệ", JOptionPane.ERROR_MESSAGE);
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
        String path = FileTextIO.saveText(this, txt_ciphertext, "railfence_ciphertext.txt");
        if (path != null) {
            lbl_status.setText("Đã lưu tệp: " + path);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_RailFence().setVisible(true));
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
}