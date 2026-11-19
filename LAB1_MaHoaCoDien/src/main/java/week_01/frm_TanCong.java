package week_01;

import javax.swing.JOptionPane;

public class frm_TanCong extends javax.swing.JFrame {

    public frm_TanCong() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Phân tích mật mã cổ điển");
        getContentPane().setLayout(null);

        jLabel1 = new javax.swing.JLabel("PHÂN TÍCH (TẤN CÔNG) MẬT MÃ CỔ ĐIỂN");
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        jLabel1.setBounds(20, 10, 620, 34);
        getContentPane().add(jLabel1);

        jLabel2 = new javax.swing.JLabel("Ciphertext:");
        jLabel2.setBounds(20, 55, 130, 22);
        getContentPane().add(jLabel2);

        txt_cipher = new javax.swing.JTextArea();
        txt_cipher.setLineWrap(true);
        txt_cipher.setRows(2);
        jScrollPane1 = new javax.swing.JScrollPane(txt_cipher);
        jScrollPane1.setBounds(150, 55, 480, 50);
        getContentPane().add(jScrollPane1);

        btn_bruteforce = new javax.swing.JButton("Brute Force Caesar");
        btn_bruteforce.setBounds(150, 115, 170, 30);
        btn_bruteforce.addActionListener(evt -> btn_bruteforceActionPerformed(evt));
        getContentPane().add(btn_bruteforce);

        btn_frequency = new javax.swing.JButton("Frequency Analysis");
        btn_frequency.setBounds(330, 115, 170, 30);
        btn_frequency.addActionListener(evt -> btn_frequencyActionPerformed(evt));
        getContentPane().add(btn_frequency);

        jLabel3 = new javax.swing.JLabel("Bản rõ đã biết:");
        jLabel3.setBounds(20, 160, 130, 22);
        getContentPane().add(jLabel3);
        txt_known_plain = new javax.swing.JTextField("HELLO");
        txt_known_plain.setBounds(150, 157, 140, 28);
        getContentPane().add(txt_known_plain);

        jLabel4 = new javax.swing.JLabel("Bản mã:");
        jLabel4.setBounds(300, 160, 60, 22);
        getContentPane().add(jLabel4);
        txt_known_cipher = new javax.swing.JTextField("RIJVS");
        txt_known_cipher.setBounds(360, 157, 140, 28);
        getContentPane().add(txt_known_cipher);

        btn_known = new javax.swing.JButton("Known Plaintext (Vigenere)");
        btn_known.setBounds(150, 195, 220, 30);
        btn_known.addActionListener(evt -> btn_knownActionPerformed(evt));
        getContentPane().add(btn_known);

        jLabel5 = new javax.swing.JLabel("Kết quả:");
        jLabel5.setBounds(20, 240, 130, 22);
        getContentPane().add(jLabel5);

        txt_result = new javax.swing.JTextArea();
        txt_result.setLineWrap(false);
        txt_result.setRows(3);
        txt_result.setEditable(false);
        txt_result.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 13));
        jScrollPane2 = new javax.swing.JScrollPane(txt_result);
        jScrollPane2.setBounds(150, 240, 480, 250);
        getContentPane().add(jScrollPane2);

        lbl_status = new javax.swing.JLabel("Sẵn sàng.");
        lbl_status.setBounds(150, 500, 480, 22);
        getContentPane().add(lbl_status);

        getContentPane().setPreferredSize(new java.awt.Dimension(660, 535));
        pack();
    }

    private void btn_bruteforceActionPerformed(java.awt.event.ActionEvent evt) {
        String cipher = txt_cipher.getText().trim();
        if (cipher.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext.");
            return;
        }
        txt_result.setText(AttackDemos.caesarBruteForce(cipher));
        txt_result.setCaretPosition(0);
        lbl_status.setText("Đã thử đủ 26 khóa Caesar.");
    }

    private void btn_frequencyActionPerformed(java.awt.event.ActionEvent evt) {
        String cipher = txt_cipher.getText().trim();
        if (cipher.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext.");
            return;
        }
        txt_result.setText(AttackDemos.frequency(cipher));
        txt_result.setCaretPosition(0);
        lbl_status.setText("Đã thống kê tần suất chữ cái.");
    }

    private void btn_knownActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String key = AttackDemos.knownPlaintext(txt_known_plain.getText(), txt_known_cipher.getText());
            txt_result.setText("Khóa Vigenère suy ra từng vị trí: " + key
                    + "\nNếu khóa ngắn hơn bản rõ, khóa sẽ lặp lại (KEY KEY ...).");
            lbl_status.setText("Khóa suy ra: " + key);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_TanCong().setVisible(true));
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txt_cipher;
    private javax.swing.JButton btn_bruteforce;
    private javax.swing.JButton btn_frequency;
    private javax.swing.JTextField txt_known_plain;
    private javax.swing.JTextField txt_known_cipher;
    private javax.swing.JButton btn_known;
    private javax.swing.JTextArea txt_result;
    private javax.swing.JLabel lbl_status;
}