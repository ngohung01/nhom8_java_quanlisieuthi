/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package nhom8_project.view.nhanvien;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import nhom8_project.entity2.HangHoa;
import nhom8_project.entity2.HoaDon;
import nhom8_project.entity2.KhachHang;
import nhom8_project.entity2.NhanVien;

/**
 *
 * @author Admin
 */
public class PhieuBanHang extends javax.swing.JFrame {

    public static ArrayList<KhachHang> kh = new ArrayList<KhachHang>();
    public static ArrayList<HangHoa> hh = new ArrayList<HangHoa>();
    public static ArrayList<HoaDon> hd = new ArrayList<HoaDon>();
    public static ArrayList<NhanVien> nv = new ArrayList<NhanVien>();
    
    int tongTienDaMua = 0;

    /**
     * Creates new form PhieuBanHang
     */
    public PhieuBanHang() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);

        readFileNV();

        //read file khach hang
        readFileKH();
        themListKH();

        //read file hang hoa
        readFileHH();
        themlistHH();

        soluong.setValue(1);

    }

    public void readFileNV() {
        String file = "NhanVien.dat";

        try {
            nv.clear();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            //DefaultTableModel tbmd = (DefaultTableModel) jTable1.getModel();

            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++) {
                String[] data = lines[i].toString().split(";");
                NhanVien a = new NhanVien();

                a.id = data[0];
                a.name = data[1];
                a.usename = data[8];
                a.password = data[9];
                
                nv.add(a);
            }

            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "kh??ng t??m th???y file nh??n vi??n");
        } catch (NumberFormatException e1) {

            //JOptionPane.showMessageDialog(this, e1);
        } catch (ArrayIndexOutOfBoundsException e2) {
            //JOptionPane.showMessageDialog(this, e2);          
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(this, ex);

        }

    }

    public void readFileKH() {
        String file = "KhachHang.dat";

        try {
            kh.clear();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            //DefaultTableModel tbmd = (DefaultTableModel) jTable1.getModel();
            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++) {
                String[] data = lines[i].toString().split(";");
                KhachHang a = new KhachHang();

                a.khachHangID = data[0];
                a.tenKhachHang = data[1];
                a.soTienMua = Integer.parseInt(data[2]);
                a.diaChi = data[3];
                a.SDT = data[4];
                kh.add(a);
            }

            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "kh??ng t??m th???y file kh??ch h??ng");
        } catch (NumberFormatException e1) {

            //JOptionPane.showMessageDialog(this, e1);
        } catch (ArrayIndexOutOfBoundsException e2) {
            //JOptionPane.showMessageDialog(this, e2);          
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(this, ex);

        }
    }

    public void readFileHH() {
        String file = "HangHoa.dat";
        try {
            hh.clear();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++) {
                String[] data = lines[i].toString().split(";");
                HangHoa a = new HangHoa();

                a.hangHoaID = data[0];
                a.tenHang = data[1];
                a.donViTinh = data[2];
                a.soLuongCon = Integer.parseInt(data[3]);
                a.soLuongBan = Integer.parseInt(data[4]);
                a.NSX = data[6];
                a.HSD = data[5];
                a.giaNhap=Integer.parseInt(data[7]);
                a.giaBan = Integer.parseInt(data[8]);
                a.loaiHang = data[9];

                hh.add(a);
            }

            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "kh??ng t??m th???y file h??ng ho??");
        } catch (NumberFormatException e1) {

            //JOptionPane.showMessageDialog(this, e1);
        } catch (ArrayIndexOutOfBoundsException e2) {
            //JOptionPane.showMessageDialog(this, e2);          
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(this, ex);

        }
    }

    public void themListKH() {
        for (int i = 0; i < kh.size(); i++) {
            cbbKhachHang.addItem(kh.get(i).khachHangID + "-" + kh.get(i).tenKhachHang);
        }

    }

    public void themlistHH() {

        DefaultListModel modelthem = new DefaultListModel();

        for (int i = 0; i < hh.size(); i++) {
            modelthem.addElement(hh.get(i).tenHang.toString());
        }
        danhSachHang.setModel(modelthem);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        listHang = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        danhSachHang = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        soluong = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        tenHang = new javax.swing.JTextField();
        btThemSP = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btXoaSP = new javax.swing.JButton();
        btXuatHD = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btInHD = new javax.swing.JButton();
        listHangMua = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbbKhachHang = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSanPhamMua = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hoadonform = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon2/Drawer Open.png"))); // NOI18N
        jLabel1.setText("danh s??ch h??ng");

        danhSachHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        danhSachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                danhSachHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(danhSachHang);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon2/Pencil.png"))); // NOI18N
        jLabel5.setText("S??? l?????ng");

        soluong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/Actions-view-choose-icon-24.png"))); // NOI18N
        jLabel6.setText("T??n h??ng");

        tenHang.setEditable(false);
        tenHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout listHangLayout = new javax.swing.GroupLayout(listHang);
        listHang.setLayout(listHangLayout);
        listHangLayout.setHorizontalGroup(
            listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(listHangLayout.createSequentialGroup()
                        .addGroup(listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(soluong)
                            .addComponent(tenHang))
                        .addGap(14, 14, 14))
                    .addGroup(listHangLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        listHangLayout.setVerticalGroup(
            listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(soluong)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btThemSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon2/Button White Add.png"))); // NOI18N
        btThemSP.setText("Th??m");
        btThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listHang, javax.swing.GroupLayout.PREFERRED_SIZE, 299, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btThemSP)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btXoaSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btXoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon2/Stop.png"))); // NOI18N
        btXoaSP.setText("Xo?? s???n ph???m");
        btXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaSPActionPerformed(evt);
            }
        });

        btXuatHD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btXuatHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon2/Box Add.png"))); // NOI18N
        btXuatHD.setText("Xu???t ho?? ????n");
        btXuatHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXuatHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btXoaSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btXuatHD)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btXoaSP)
                    .addComponent(btXuatHD))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btInHD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btInHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon2/Printer.png"))); // NOI18N
        btInHD.setText("In ho?? ????n");
        btInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(257, Short.MAX_VALUE)
                .addComponent(btInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(206, 206, 206))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout listHangMuaLayout = new javax.swing.GroupLayout(listHangMua);
        listHangMua.setLayout(listHangMuaLayout);
        listHangMuaLayout.setHorizontalGroup(
            listHangMuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        listHangMuaLayout.setVerticalGroup(
            listHangMuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon2/Document 2 Add.png"))); // NOI18N
        jLabel2.setText("s???n ph???m mua");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/Person-Male-Light-icon-24.png"))); // NOI18N
        jLabel4.setText("Kh??ch h??ng");

        cbbKhachHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kh??ng" }));

        tableSanPhamMua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableSanPhamMua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "T??n h??ng", "Gi??", "S??? l?????ng"
            }
        ));
        jScrollPane2.setViewportView(tableSanPhamMua);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );

        hoadonform.setColumns(20);
        hoadonform.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hoadonform.setRows(5);
        hoadonform.setEnabled(false);
        jScrollPane1.setViewportView(hoadonform);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon2/Document 2.png"))); // NOI18N
        jLabel3.setText("Ho?? ????n");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26)
                        .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(listHangMua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(246, 246, 246)
                                .addComponent(jLabel3))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(79, 79, 79)
                                            .addComponent(listHangMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(37, 37, 37))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(21, 21, 21)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInHDActionPerformed
        //in ho?? ????n v?? ghi file 
        if (hd.size() > 0) {
            saveAndUpdateData();
        } else {
            JOptionPane.showMessageDialog(null, "Kh??ng c?? s???n ph???m ???????c ch???n");
        }


    }//GEN-LAST:event_btInHDActionPerformed

    public boolean checksoluong(String ten, int soluong) {
        for (int i = 0; i < hh.size(); i++) {
            if (hh.get(i).tenHang.compareTo(ten) == 0) {
                if (hh.get(i).soLuongCon < soluong) {
                    JOptionPane.showMessageDialog(null, hh.get(i).tenHang + " ch??? c??n " + hh.get(i).soLuongCon + " " + hh.get(i).donViTinh);
                    return false;//s??? l?????ng thi???u tr??? v??? false
                }
            }
        }
        return true;//s??? l?????ng ?????
    }

    private void btThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemSPActionPerformed

        if (Integer.parseInt(soluong.getValue().toString()) <= 0) {
            soluong.setValue(1);
        }
        if (danhSachHang.getSelectedValue() == null) {

        } else {
            if (sosanh(danhSachHang.getSelectedValue()) == false) {

                if (checksoluong(danhSachHang.getSelectedValue(), (int) soluong.getValue())) {
                    HoaDon a = new HoaDon();
                    a.hangHoaID = getIDByName(danhSachHang.getSelectedValue());
                    a.soLuong = (int) soluong.getValue();
                    hd.add(a);
                }
            }
        }

        //in ra b???ng
        inbang();

    }//GEN-LAST:event_btThemSPActionPerformed

    public void inbang() {//in b???ng d??? li???u h??ng ??ang ch???n
        DefaultTableModel model = (DefaultTableModel) tableSanPhamMua.getModel();
        model.setRowCount(0);

        if (hd.size() == 0) {

        } else {
            for (int i = 0; i < hd.size(); i++) {
                for (int j = 0; j < hh.size(); j++) {
                    if (hd.get(i).hangHoaID.compareTo(hh.get(j).hangHoaID) == 0) {
                        model.addRow(new Object[]{hh.get(j).tenHang, hh.get(j).giaBan, hd.get(i).soLuong});
                        break;
                    }

                }
            }
        }
    }

    public boolean sosanh(String a) {

        for (int i = 0; i < hd.size(); i++) {
            if (hd.get(i).hangHoaID.compareTo(getIDByName(a)) == 0) {
                return true;
            }

        }
        return false;
    }

    public String getIDByName(String name) {// l???y id h??ng t??? t??n h??ng

        for (int i = 0; i < hh.size(); i++) {
            if (hh.get(i).tenHang.compareTo(name) == 0) {
                return hh.get(i).hangHoaID;
            }
        }
        return "";
    }

    public String getNameByID(String id) {//l???y t??n h??ng t??? id h??ng
        for (int i = 0; i < hh.size(); i++) {
            if (hh.get(i).hangHoaID.compareTo(id) == 0) {
                return hh.get(i).tenHang;

            }
        }
        return "";
    }


    private void btXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaSPActionPerformed
        if (tableSanPhamMua.getSelectedRow() != -1) {
            hd.remove(tableSanPhamMua.getSelectedRow());
            inbang();
        }
    }//GEN-LAST:event_btXoaSPActionPerformed

    private void danhSachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_danhSachHangMouseClicked
        String th = "";
        th = danhSachHang.getSelectedValue();
        tenHang.setText(th);
    }//GEN-LAST:event_danhSachHangMouseClicked

    private void btXuatHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXuatHDActionPerformed

        String idKhach = "";
        //l???y id kh??ch
        if (cbbKhachHang.getSelectedItem().toString().compareTo("Kh??ng") == 0) {
            idKhach = "";
        } else {
            String[] data;
            data = cbbKhachHang.getSelectedItem().toString().split("-");
            idKhach = data[0];
        }

        //t???o id ho?? ????n
        String idhd = "HD" + idAuto();

        //id nhan vien
        String idnv = "";
        if (nhom8_project.view.nhanvien.NhanVienForm.MANHANVIEN != null) {
            idnv = nhom8_project.view.nhanvien.NhanVienForm.MANHANVIEN;
        } else {
            idnv = "";
        }

        //l???y th???i gian t???o ho?? ????n
        String time = dateTime();

        for (int i = 0; i < hd.size(); i++) {
            hd.get(i).khachHangID = idKhach;
            hd.get(i).hoaDonID = idhd;
            hd.get(i).nhanVienID = idnv;
            hd.get(i).thoiGian = time;
        }

        //xu???t b???n ghi ho?? ????n
        if (hd.size() > 0) {
            formHD(idhd, getKhachByID(idKhach), getTenNVByID(idnv), idKhach);

        } else {
            hoadonform.setText("");
            JOptionPane.showMessageDialog(null, "kh??ng c?? s???n ph???m ???????c ch???n");
        }

    }//GEN-LAST:event_btXuatHDActionPerformed

    public String idAuto() {
        ZonedDateTime obj = ZonedDateTime.now();
        Instant instant = obj.toInstant();
        long msec = instant.toEpochMilli();
        String time = String.valueOf(msec);
        return time;
    }

    public String dateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        return formattedDate;
    }

    public String getKhachByID(String id) {
        if (id != "") {
            for (int i = 0; i < kh.size(); i++) {
                if (kh.get(i).khachHangID.compareTo(id) == 0) {
                    return kh.get(i).tenKhachHang;
                }
            }
        }
        return "";
    }

    public String getTenNVByID(String id) {
        if (id != "") {
            for (int i = 0; i < nv.size(); i++) {
                if (nv.get(i).id.compareTo(id) == 0) {
                    return nv.get(i).name;
                }
            }
        }
        return "";
    }

    public void formHD(String mhd, String tenKhach, String tennv, String makh) {
        String tenhang = "", nv = "", khach = "";
        int gia = 0;
        int stt = 1;

        hoadonform.setText("\t\tSI??U TH??? MINI ABCHK"
                + "\n\t\t  ?????a ch???: H?? N???i"
                + "\n\t\t  ??T: 0968844313\n"
                + "\n\t\tHO?? ????N MUA H??NG\n"
                + "\nM??: " + mhd + "\t\t\tNg??y:" + dateTime()
                + "\nKh??ch h??ng: " + tenKhach
                + "\nNh??n vi??n: " + tennv + ""
                + "\n------------------------------------------------------------------------------------------------------------\n");
        hoadonform.append(String.format("\n%.5s\t%.20s\t%.20s\t%.15s\t%.15s\t%.20s", "STT", "T??n S???n Ph???m", "??VT", "S??? l?????ng", "Gi??", "Th??nh Ti???n"));

        hoadonform.append("\n---------------------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < hd.size(); i++) {
            for (int j = 0; j < hh.size(); j++) {
                if (hd.get(i).hangHoaID == hh.get(j).hangHoaID) {
                    hoadonform.append(String.format("\n%.5s\t%.20s\t%.20s\t%.15s\t%.15s\t%.20s",
                            (stt++), hh.get(j).tenHang, hh.get(j).donViTinh, hd.get(i).soLuong, hh.get(j).giaBan, tinhTien(hd.get(i).soLuong, hh.get(j).giaBan)));
                    tongTienDaMua += tinhTien(hd.get(i).soLuong, hh.get(j).giaBan);
                    break;
                }
            }
        }
        hoadonform.append("\n----------------------------------------------------------------------------------------------------------------\n");
        hoadonform.append("T???ng ti???n:\t\t\t\t\t" + tongTienDaMua + "\n");

        if (makh.compareTo("") != 0) {
            for (int i = 0; i < kh.size(); i++) {
                if (kh.get(i).khachHangID.compareTo(makh) == 0) {
                    hoadonform.append("\n\t\t\t\t\t-");
                    hoadonform.append("\nGi???m:\t\t\t\t\t" + giamGia((int) kh.get(i).soTienMua)+" %");
                    hoadonform.append("\n----------------------------------------------------------------------------------------------------------------\n");
                    
                    tongTienDaMua=(tongTienDaMua*(100-giamGia((int) kh.get(i).soTienMua)))/100;
                    hoadonform.append("\nT???ng ti???n:\t\t\t\t\t" + tongTienDaMua + "\n");
                    hoadonform.append("\nKh??ch h??ng: " + loaikh((int) kh.get(i).soTienMua));
                    break;
                }
            }

        }

        hoadonform.append("\n\n\tVui l??ng gi??? l???i ho?? ????n ????? nh???n ??u ????i ??? c??c l???n mua s???m sau\n\n");
        hoadonform.append("\n\t\tC???M ??N QU?? KH??CH"
                + "\n\t\t   H???N G???P L???I");

    }

    public String loaikh(int a) {
        if (a <= 100000) {
            return "?????ng kh??ng c?? ??u ????i gi???m";
        } else if (a > 100000 && a <= 500000) {
            return "B???c ??u ????i gi???m 1%";
        } else if (a > 500000 && a < 1000000) {
            return "V??ng ??u ????i gi???m 2%";
        } else {
            return "Kim c????ng ??u ????i gi???m 3%";
        }
    }

    public int giamGia(int a) {
        if (a <= 100000) {
            return 0;
        } else if (a > 100000 && a <= 500000) {
            return 1;
        } else if (a > 500000 && a < 1000000) {
            return 2;
        } else {
            return 3;
        }
    }

    public int tinhTien(int a, int b) {
        return a * b;
    }

    //l??u l???i th??ng tin kh??ch h??ng, update th??ng tin kh??ch
    //c???p nh???t th??ng tin h??ng s?? l?????ng h??ng c??n, b??n
    //l??u ho?? ????n
    public void saveAndUpdateData() {
        DefaultTableModel model = (DefaultTableModel) tableSanPhamMua.getModel();
        model.setRowCount(0);
        ghiFileHD();//ghi file Ho?? ????n
        ghiFileKH();//Ghi file Kh??ch h??ng
        ghiFileHH();//ghi File h??ng ho??
        hd.clear();
    }

    //ghi file
    public void updateKH() {
        if (getKhachByID(hd.get(0).khachHangID) != "") {
            for (int i = 0; i < kh.size(); i++) {
                if (kh.get(i).khachHangID.compareTo(hd.get(0).khachHangID) == 0) {
                    kh.get(i).soTienMua += tongTienDaMua;
                }
            }
        }
    }

    public void ghiFileKH() {
        updateKH();//c???p nh???t s??? ti???n kh??ch h??ng
        String fileWrite = "KhachHang.dat";

        try {

            File file = new File(fileWrite);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i < kh.size(); i++) {
                pw.println(kh.get(i).khachHangID + ";" + kh.get(i).tenKhachHang + ";" + kh.get(i).soTienMua + ";" + kh.get(i).getDiaChi() + ";" + kh.get(i).SDT);
            }

//????ng
            pw.close();
            bw.close();
            fw.close();

            //JOptionPane.showMessageDialog(null, "Nh???p s??? li???u th??nh c??ng");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, ioe);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void ghiFileHD() {
        String fileWrite = "HoaDon.dat";

        try {

            File file = new File(fileWrite);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i < hd.size(); i++) {
                pw.println(hd.get(i).hoaDonID + ";" + hd.get(i).soLuong + ";" + hd.get(i).thoiGian + ";" + hd.get(i).nhanVienID + ";" + hd.get(i).hangHoaID + ";" + hd.get(i).khachHangID);

            }

//????ng
            pw.close();
            bw.close();
            fw.close();

            //JOptionPane.showMessageDialog(null, "Nh???p s??? li???u th??nh c??ng");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, ioe);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void updateHH() {
        if (hd.size() > 0) {
            for (int i = 0; i < hd.size(); i++) {
                for (int j = 0; j < hh.size(); j++) {
                    if (hh.get(j).hangHoaID.compareTo(hd.get(i).hangHoaID) == 0) {
                        hh.get(j).soLuongBan += hd.get(i).soLuong;
                        hh.get(j).soLuongCon -= hd.get(i).soLuong;
                        break;
                    }
                }
            }
        }
    }

    public void ghiFileHH() {
        updateHH();//c???p nh???t s??? hh sau khi b??n
        String fileWrite = "HangHoa.dat";

        try {

            File file = new File(fileWrite);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i < hh.size(); i++) {
                pw.println(hh.get(i).hangHoaID + ";" + hh.get(i).tenHang + ";" + hh.get(i).donViTinh + ";" + hh.get(i).soLuongCon + ";" + hh.get(i).soLuongBan + ";" + hh.get(i).HSD + ";" + hh.get(i).NSX+ ";" + hh.get(i).giaNhap + ";" + hh.get(i).giaBan + ";" + hh.get(i).loaiHang);

            }

            //????ng
            pw.close();
            bw.close();
            fw.close();

            JOptionPane.showMessageDialog(null, "Nh???p s??? li???u th??nh c??ng");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, ioe);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

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
            java.util.logging.Logger.getLogger(PhieuBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhieuBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhieuBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhieuBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhieuBanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btInHD;
    private javax.swing.JButton btThemSP;
    private javax.swing.JButton btXoaSP;
    private javax.swing.JButton btXuatHD;
    private javax.swing.JComboBox<String> cbbKhachHang;
    private javax.swing.JList<String> danhSachHang;
    private javax.swing.JTextArea hoadonform;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel listHang;
    private javax.swing.JPanel listHangMua;
    private javax.swing.JSpinner soluong;
    private javax.swing.JTable tableSanPhamMua;
    private javax.swing.JTextField tenHang;
    // End of variables declaration//GEN-END:variables
}
