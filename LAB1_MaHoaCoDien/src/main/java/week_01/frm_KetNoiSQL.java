package week_01;

import javax.swing.JOptionPane;

public class frm_KetNoiSQL extends javax.swing.JFrame {

    public frm_KetNoiSQL() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Kết nối PostgreSQL");
        getContentPane().setLayout(null);

        jLabel1 = new javax.swing.JLabel("KẾT NỐI POSTGRESQL");
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        jLabel1.setBounds(20, 10, 450, 34);
        getContentPane().add(jLabel1);

        jLabel2 = new javax.swing.JLabel("Server:");
        jLabel2.setBounds(20, 58, 100, 22);
        getContentPane().add(jLabel2);
        txt_server = new javax.swing.JTextField("127.0.0.1");
        txt_server.setBounds(130, 55, 320, 28);
        getContentPane().add(txt_server);

        jLabel3 = new javax.swing.JLabel("Port:");
        jLabel3.setBounds(20, 96, 100, 22);
        getContentPane().add(jLabel3);
        txt_port = new javax.swing.JTextField("5432");
        txt_port.setBounds(130, 93, 120, 28);
        getContentPane().add(txt_port);

        jLabel4 = new javax.swing.JLabel("Database:");
        jLabel4.setBounds(20, 134, 100, 22);
        getContentPane().add(jLabel4);
        txt_database = new javax.swing.JTextField("LAB1_MaHoaCoDien");
        txt_database.setBounds(130, 131, 320, 28);
        getContentPane().add(txt_database);

        jLabel5 = new javax.swing.JLabel("User:");
        jLabel5.setBounds(20, 172, 100, 22);
        getContentPane().add(jLabel5);
        txt_user = new javax.swing.JTextField("lab1_user");
        txt_user.setBounds(130, 169, 320, 28);
        getContentPane().add(txt_user);

        jLabel6 = new javax.swing.JLabel("Password:");
        jLabel6.setBounds(20, 210, 100, 22);
        getContentPane().add(jLabel6);
        txt_password = new javax.swing.JPasswordField();
        txt_password.setBounds(130, 207, 320, 28);
        getContentPane().add(txt_password);

        btn_test = new javax.swing.JButton("Test Connection");
        btn_test.setBounds(130, 250, 150, 30);
        btn_test.addActionListener(evt -> btn_testActionPerformed(evt));
        getContentPane().add(btn_test);

        btn_history = new javax.swing.JButton("Xem lịch sử");
        btn_history.setBounds(290, 250, 130, 30);
        btn_history.addActionListener(evt -> btn_historyActionPerformed(evt));
        getContentPane().add(btn_history);

        lbl_status = new javax.swing.JLabel("Chưa kết nối.");
        lbl_status.setBounds(20, 290, 450, 40);
        getContentPane().add(lbl_status);

        getContentPane().setPreferredSize(new java.awt.Dimension(490, 345));
        pack();
    }

    private void btn_testActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int port = Integer.parseInt(txt_port.getText().trim());
            DbConfig.set(txt_server.getText().trim(), port, txt_database.getText().trim(),
                    txt_user.getText().trim(), txt_password.getPassword());
            lbl_status.setText("<html>" + DbConfig.test() + "</html>");
        } catch (Exception ex) {
            DbConfig.clear();
            lbl_status.setText("<html>Kết nối thất bại: " + ex.getMessage() + "</html>");
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Không kết nối được PostgreSQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_historyActionPerformed(java.awt.event.ActionEvent evt) {
        new frm_LichSu().setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_KetNoiSQL().setVisible(true));
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txt_server;
    private javax.swing.JTextField txt_port;
    private javax.swing.JTextField txt_database;
    private javax.swing.JTextField txt_user;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JButton btn_test;
    private javax.swing.JButton btn_history;
    private javax.swing.JLabel lbl_status;
}