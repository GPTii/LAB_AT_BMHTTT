package week_01;

public class frm_Menu extends javax.swing.JFrame {

    public frm_Menu() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LAB 1 - Mã hóa đối xứng cổ điển");
        getContentPane().setLayout(null);

        jLabel1 = new javax.swing.JLabel("LAB 1 - MÃ HÓA ĐỐI XỨNG CỔ ĐIỂN");
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
        jLabel1.setBounds(20, 10, 640, 38);
        getContentPane().add(jLabel1);

        jLabel2 = new javax.swing.JLabel("Chọn một bài để mở form tương ứng:");
        jLabel2.setBounds(20, 50, 640, 22);
        getContentPane().add(jLabel2);

        btn_caesar = new javax.swing.JButton("1.1 Caesar");
        btn_caesar.setBounds(20, 80, 200, 30);
        btn_caesar.addActionListener(evt -> btn_caesarActionPerformed(evt));
        getContentPane().add(btn_caesar);

        btn_vigenere = new javax.swing.JButton("1.2 Vigenère");
        btn_vigenere.setBounds(235, 80, 200, 30);
        btn_vigenere.addActionListener(evt -> btn_vigenereActionPerformed(evt));
        getContentPane().add(btn_vigenere);

        btn_railfence = new javax.swing.JButton("1.3 Rail Fence");
        btn_railfence.setBounds(450, 80, 200, 30);
        btn_railfence.addActionListener(evt -> btn_railfenceActionPerformed(evt));
        getContentPane().add(btn_railfence);

        btn_playfair = new javax.swing.JButton("1.4 Playfair");
        btn_playfair.setBounds(20, 125, 200, 30);
        btn_playfair.addActionListener(evt -> btn_playfairActionPerformed(evt));
        getContentPane().add(btn_playfair);

        btn_transposition = new javax.swing.JButton("1.5 Transposition");
        btn_transposition.setBounds(235, 125, 200, 30);
        btn_transposition.addActionListener(evt -> btn_transpositionActionPerformed(evt));
        getContentPane().add(btn_transposition);

        btn_autokey = new javax.swing.JButton("1.6 Autokey");
        btn_autokey.setBounds(450, 125, 200, 30);
        btn_autokey.addActionListener(evt -> btn_autokeyActionPerformed(evt));
        getContentPane().add(btn_autokey);

        btn_tonghop = new javax.swing.JButton("Nâng cao 01 - Tổng hợp");
        btn_tonghop.setBounds(20, 170, 200, 30);
        btn_tonghop.addActionListener(evt -> btn_tonghopActionPerformed(evt));
        getContentPane().add(btn_tonghop);

        btn_tancong = new javax.swing.JButton("Nâng cao 02 - Tấn công");
        btn_tancong.setBounds(235, 170, 200, 30);
        btn_tancong.addActionListener(evt -> btn_tancongActionPerformed(evt));
        getContentPane().add(btn_tancong);

        btn_sql = new javax.swing.JButton("Kết nối PostgreSQL");
        btn_sql.setBounds(450, 170, 200, 30);
        btn_sql.addActionListener(evt -> btn_sqlActionPerformed(evt));
        getContentPane().add(btn_sql);

        jLabel3 = new javax.swing.JLabel(
                "Mỗi form mở ra là một cửa sổ riêng; đóng form con không làm tắt chương trình.");
        jLabel3.setBounds(20, 220, 640, 22);
        getContentPane().add(jLabel3);

        getContentPane().setPreferredSize(new java.awt.Dimension(680, 255));
        pack();
    }

    private void btn_caesarActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_Caesar().setVisible(true);
    }

    private void btn_vigenereActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_Vigenere().setVisible(true);
    }

    private void btn_railfenceActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_RailFence().setVisible(true);
    }

    private void btn_playfairActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_Playfair().setVisible(true);
    }

    private void btn_transpositionActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_Transposition().setVisible(true);
    }

    private void btn_autokeyActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_Autokey().setVisible(true);
    }

    private void btn_tonghopActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_TongHop().setVisible(true);
    }

    private void btn_tancongActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_TanCong().setVisible(true);
    }

    private void btn_sqlActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_KetNoiSQL().setVisible(true);
    }

    // Bấm "Run" ở đây = Run Project (F6)
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_Menu().setVisible(true));
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton btn_caesar;
    private javax.swing.JButton btn_vigenere;
    private javax.swing.JButton btn_railfence;
    private javax.swing.JButton btn_playfair;
    private javax.swing.JButton btn_transposition;
    private javax.swing.JButton btn_autokey;
    private javax.swing.JButton btn_tonghop;
    private javax.swing.JButton btn_tancong;
    private javax.swing.JButton btn_sql;
}