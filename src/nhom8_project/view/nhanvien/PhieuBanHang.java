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
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import nhom8_project.entity.HangHoa;
import nhom8_project.entity.HoaDon;
import nhom8_project.entity.KhachHang;
import nhom8_project.entity.NhanVien;
import static nhom8_project.view.loginView.nv;

/**
 *
 * @author Admin
 */
public class PhieuBanHang extends javax.swing.JFrame {

    public static ArrayList<KhachHang> kh = new ArrayList<KhachHang>();
    public static ArrayList<HangHoa> hh = new ArrayList<HangHoa>();
    public static ArrayList<HoaDon> hd = new ArrayList<HoaDon>();
    int tongTienDaMua = 0;

    /**
     * Creates new form PhieuBanHang
     */
    public PhieuBanHang() {
        initComponents();
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
        String file = "NhanVien.txt";

        try {
            nv.clear();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            //DefaultTableModel tbmd = (DefaultTableModel) jTable1.getModel();

            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++) {
                String[] data = lines[i].toString().split(",");
                NhanVien a = new NhanVien();

                a.nhanVienID = data[0];
                a.accountName = data[1];
                a.accountPass = data[2];
                a.tenNhanVien = data[3];
                nv.add(a);
            }

            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "không tìm thấy file nhân viên");
        } catch (NumberFormatException e1) {

            //JOptionPane.showMessageDialog(this, e1);
        } catch (ArrayIndexOutOfBoundsException e2) {
            //JOptionPane.showMessageDialog(this, e2);          
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(this, ex);

        }

    }

    public void readFileKH() {
        String file = "KhachHang.txt";

        try {
            kh.clear();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            //DefaultTableModel tbmd = (DefaultTableModel) jTable1.getModel();
            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++) {
                String[] data = lines[i].toString().split(",");
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
            JOptionPane.showMessageDialog(this, "không tìm thấy file khách hàng");
        } catch (NumberFormatException e1) {

            //JOptionPane.showMessageDialog(this, e1);
        } catch (ArrayIndexOutOfBoundsException e2) {
            //JOptionPane.showMessageDialog(this, e2);          
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(this, ex);

        }
    }

    public void readFileHH() {
        String file = "HangHoa.txt";
        try {
            hh.clear();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++) {
                String[] data = lines[i].toString().split(",");
                HangHoa a = new HangHoa();

                a.hangHoaID = data[0];
                a.tenHang = data[1];
                a.donViTinh = data[2];
                a.soLuongCon = Integer.parseInt(data[3]);
                a.soLuongBan = Integer.parseInt(data[4]);
                a.NSX = data[5];
                a.HSD = data[6];
                a.giaBan = Integer.parseInt(data[7]);
                a.loaiHang = data[8];
                a.hoaDonNhapID = data[9];

                hh.add(a);
            }

            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "không tìm thấy file");
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
        setPreferredSize(new java.awt.Dimension(1366, 768));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("danh sách hàng");

        danhSachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                danhSachHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(danhSachHang);

        jLabel5.setText("số lượng");

        jLabel6.setText("Tên hàng");

        tenHang.setEditable(false);

        javax.swing.GroupLayout listHangLayout = new javax.swing.GroupLayout(listHang);
        listHang.setLayout(listHangLayout);
        listHangLayout.setHorizontalGroup(
            listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(listHangLayout.createSequentialGroup()
                        .addGroup(listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(soluong)
                            .addComponent(tenHang, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        listHangLayout.setVerticalGroup(
            listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tenHang, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(listHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(soluong, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btThemSP.setText("Thêm");
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
                        .addGap(91, 91, 91)
                        .addComponent(btThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        btXoaSP.setText("Xoá sản phẩm");
        btXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaSPActionPerformed(evt);
            }
        });

        btXuatHD.setText("Xuất hoá đơn");
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
                .addGap(40, 40, 40)
                .addComponent(btXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(btXuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btXuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btInHD.setText("In hoá đơn");
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
                .addContainerGap(282, Short.MAX_VALUE)
                .addComponent(btInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(242, 242, 242))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
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

        jLabel2.setText("sản phẩm mua");

        jLabel4.setText("Khách hàng");

        cbbKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không" }));

        tableSanPhamMua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên hàng", "Giá", "Số lượng"
            }
        ));
        jScrollPane2.setViewportView(tableSanPhamMua);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
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
        jLabel3.setText("Hoá đơn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(listHangMua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(260, 260, 260)))
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
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(listHangMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        //in hoá đơn và ghi file 
        saveAndUpdateData();


    }//GEN-LAST:event_btInHDActionPerformed


    private void btThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemSPActionPerformed

        if (Integer.parseInt(soluong.getValue().toString()) <= 0) {
            soluong.setValue(1);
        }
        if (danhSachHang.getSelectedValue() == null) {

        } else {
            if (sosanh(danhSachHang.getSelectedValue()) == false) {

                HoaDon a = new HoaDon();
                a.hangHoaID = getIDByName(danhSachHang.getSelectedValue());
                a.soLuong = (int) soluong.getValue();
                hd.add(a);
            }
        }

        //in ra bảng
        inbang();

    }//GEN-LAST:event_btThemSPActionPerformed

    public void inbang() {//in bảng dữ liệu hàng đang chọn
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

    public String getIDByName(String name) {// lấy id hàng từ tên hàng

        for (int i = 0; i < hh.size(); i++) {
            if (hh.get(i).tenHang.compareTo(name) == 0) {
                return hh.get(i).hangHoaID;
            }
        }
        return "";
    }

    public String getNameByID(String id) {//lấy tên hàng từ id hàng
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
        //lấy id khách
        if (cbbKhachHang.getSelectedItem().toString().compareTo("Không") == 0) {
            idKhach = "";
        } else {
            String[] data;
            data = cbbKhachHang.getSelectedItem().toString().split("-");
            idKhach = data[0];
        }

        //tạo id hoá đơn
        String idhd = "HD"+idAuto();

        //id nhan vien
        String idnv = "1";

        //lấy thời gian tạo hoá đơn
        String time = dateTime();

        for (int i = 0; i < hd.size(); i++) {
            hd.get(i).khachHangID = idKhach;
            hd.get(i).hoaDonID = idhd;
            hd.get(i).nhanVienID = idnv;
            hd.get(i).thoiGian = time;
        }

        //xuất bản ghi hoá đơn
        formHD(idhd, getKhachByID(idKhach), getTenNVByID(idnv));


    }//GEN-LAST:event_btXuatHDActionPerformed

    public String idAuto() {
        ZonedDateTime obj = ZonedDateTime.now();
        Instant instant = obj.toInstant();
        long msec = instant.toEpochMilli();
        String time=String.valueOf(msec);
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
                if (nv.get(i).nhanVienID.compareTo(id) == 0) {
                    return nv.get(i).tenNhanVien;
                }
            }
        }
        return "";
    }

    public void formHD(String mhd, String tenKhach, String tennv) {
        String tenhang = "", nv = "", khach = "";
        int gia = 0;
        int stt = 1;

        hoadonform.setText("\t\tSIÊU THỊ MINI ABCHK"
                + "\n\t\t  Địa chỉ: Hà Nội"
                + "\n\t\t  ĐT: 0968844313\n"
                + "\n\t\tHOÁ ĐƠN MUA HÀNG\n"
                + "\nMã: " + mhd + "\t\t\tNgày:" + dateTime()
                + "\nKhách hàng: " + tenKhach
                + "\nNhân viên: " + tennv + ""
                + "\n------------------------------------------------------------------------------------------------------------------\n");
        hoadonform.append("STT   Tên Sản Phẩm\t   ĐVT\tSố lượng\tGiá\tThành Tiền\n");
        hoadonform.append("---------------------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < hd.size(); i++) {
            for (int j = 0; j < hh.size(); j++) {
                if (hd.get(i).hangHoaID == hh.get(j).hangHoaID) {
                    hoadonform.append((stt++) + "       " + hh.get(j).tenHang + "\t      " + hh.get(j).donViTinh + "\t" + hd.get(i).soLuong + "\t" + hh.get(j).giaBan + "\t" + tinhTien(hd.get(i).soLuong, hh.get(j).giaBan) + "\n");
                    tongTienDaMua += tinhTien(hd.get(i).soLuong, hh.get(j).giaBan);
                    break;
                }
            }
        }
        hoadonform.append("\n----------------------------------------------------------------------------------------------------------------\n");
        hoadonform.append("Tổng tiền:\t\t\t\t\t" + tongTienDaMua);

        hoadonform.append("\n\nVui lòng giữ lại hoá đơn để nhận ưu đãi ở các lần mua sắm sau\n\n");
        hoadonform.append("\n\t\tCẢM ƠN QUÝ KHÁCH"
                + "\n\t\t   HẸN GẶP LẠI");

    }

    public int tinhTien(int a, int b) {
        return a * b;
    }

    //lưu lại thông tin khách hàng, update thông tin khách
    //cập nhật thông tin hàng sô lượng hàng còn, bán
    //lưu hoá đơn
    public void saveAndUpdateData() {
        ghiFileHD();//ghi file Hoá đơn
        ghiFileKH();//Ghi file Khách hàng
        ghiFileHH();//ghi File hàng hoá
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
        updateKH();//cập nhật số tiền khách hàng
        String fileWrite = "KhachHang.txt";

        try {

            File file = new File(fileWrite);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i < kh.size(); i++) {
                pw.println(kh.get(i).khachHangID + "," + kh.get(i).tenKhachHang + "," + kh.get(i).soTienMua + "," + kh.get(i).getDiaChi() + "," + kh.get(i).SDT);
            }

//đóng
            pw.close();
            bw.close();
            fw.close();

            //JOptionPane.showMessageDialog(null, "Nhập số liệu thành công");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, ioe);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void ghiFileHD() {
        String fileWrite = "HoaDon.txt";

        try {

            File file = new File(fileWrite);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i < hd.size(); i++) {
                pw.println(hd.get(i).hoaDonID + "," + hd.get(i).soLuong + "," + hd.get(i).thoiGian + "," + hd.get(i).nhanVienID + "," + hd.get(i).hangHoaID + "," + hd.get(i).khachHangID);

            }

//đóng
            pw.close();
            bw.close();
            fw.close();

            //JOptionPane.showMessageDialog(null, "Nhập số liệu thành công");
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
        updateHH();//cập nhật số hh sau khi bán
        String fileWrite = "HangHoa.txt";

        try {

            File file = new File(fileWrite);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i < hh.size(); i++) {
                pw.println(hh.get(i).hangHoaID + "," + hh.get(i).tenHang + "," + hh.get(i).donViTinh + "," + hh.get(i).soLuongCon + "," + hh.get(i).soLuongBan + "," + hh.get(i).HSD + "," + hh.get(i).NSX + "," + hh.get(i).giaBan + "," + hh.get(i).loaiHang + "," + hh.get(i).hoaDonNhapID);

            }

            //đóng
            pw.close();
            bw.close();
            fw.close();

            JOptionPane.showMessageDialog(null, "Nhập số liệu thành công");
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