package week_01;

public class frm_LichSu extends javax.swing.JFrame {

    public frm_LichSu() {
        initComponents();
        setLocationRelativeTo(null);
        tbl_history.setModel(HistoryDao.emptyModel());
        loadData();
    }

    /** Đọc 50 dòng mới nhất từ PostgreSQL và hiện lên JTable. */
    private void loadData() {
        try {
            tbl_history.setModel(HistoryDao.loadLatest(50));
            lbl_status.setText("Đã tải " + tbl_history.getRowCount() + " dòng mới nhất từ public.history.");
        } catch (java.sql.SQLException ex) {
            lbl_status.setText("Không đọc được lịch sử: " + ex.getMessage());
        }
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lịch sử thao tác (PostgreSQL)");
        getContentPane().setLayout(null);

        jLabel1 = new javax.swing.JLabel("LỊCH SỬ THAO TÁC - BẢNG PUBLIC.HISTORY");
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        jLabel1.setBounds(20, 10, 760, 34);
        getContentPane().add(jLabel1);

        tbl_history = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane(tbl_history);
        jScrollPane1.setBounds(20, 50, 760, 260);
        getContentPane().add(jScrollPane1);

        btn_refresh = new javax.swing.JButton("Tải lại");
        btn_refresh.setBounds(20, 320, 110, 30);
        btn_refresh.addActionListener(evt -> btn_refreshActionPerformed(evt));
        getContentPane().add(btn_refresh);

        lbl_status = new javax.swing.JLabel("Đang tải...");
        lbl_status.setBounds(140, 324, 640, 22);
        getContentPane().add(lbl_status);

        getContentPane().setPreferredSize(new java.awt.Dimension(800, 365));
        pack();
    }

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {
        loadData();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_LichSu().setVisible(true));
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_history;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JLabel lbl_status;
}