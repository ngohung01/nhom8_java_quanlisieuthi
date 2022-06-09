/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package nhom8_project.view.admin;

import java.util.ArrayList;
import static java.util.Collections.list;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import nhom8_project.entity.LuongThuong;
import nhom8_project.utils.ReadWriteFile;
import nhom8_project.entity.ListLt;
import nhom8_project.entity.NhanVien;
import nhom8_project.entity.NhanVienList;
import nhom8_project.view.loginView;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author DELL
 */
public final class LuongThuongManagement extends javax.swing.JPanel {
    private  DefaultTableModel tbModel=null;
    private  Object [] Title= new Object[]{"STT","Mã nhân viên","Tên nhân viên","Lương cơ bản","Số ngày làm","Thưởng phạt","Tháng","Năm","Tình trạng","Tổng tiền "};
    private ListLt listlt;  
    private NhanVienList  nvlist,nvlt;
    private String iCheck;   
    /**
     * Creates new form LuongThuongManagement
     */
    
  
    public LuongThuongManagement() {
        initComponents();        
        BtLtThem.setEnabled(true);
        BtLtSua.setEnabled(true);
        BtLtClear.setEnabled(true);     
        initTable();
        themListNV();
        SelectRowTable();    
        
    }
    
     public void SelectRowTable(){
        
       
        ListSelectionModel listModel=LttableLt.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        listModel.addListSelectionListener(new ListSelectionListener() {
            
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                int rows[]=LttableLt.getSelectedRows();
                int cols[]=LttableLt.getSelectedColumns();
                
                if(cols.length>0&&rows.length>0){
                    
                    String id=String.valueOf(LttableLt.getValueAt(rows[0],1));
                    String lcb=String.valueOf(LttableLt.getValueAt(rows[0],3));
                    String snl=String.valueOf(LttableLt.getValueAt(rows[0],4));
                    String mon=String.valueOf(LttableLt.getValueAt(rows[0],6));
                    String yea=String.valueOf(LttableLt.getValueAt(rows[0],7));
                    String status=String.valueOf(LttableLt.getValueAt(rows[0],8));
                           
                    
                        iCheck=id;
                        TxtLtLcb.setText(lcb);
                        TxtLtSnl.setText(snl);
                        TxtLtM.setText(mon);
                        TxtLtY.setText(yea);
                        
                        if(status.equals("Đã nhận")){
                            RbLtTt.setSelected(true);
                        } else if(status.equals("Chưa nhận")){
                            RbLtTt.setSelected(false);
                        }
                                            
                        CbLtMnv.setEnabled(false);
                        TxtLtLcb.setEnabled(false);
                        TxtLtSnl.setEnabled(false);
                        TxtLtM.setEnabled(false);
                        TxtLtY.setEnabled(false);
                        BtLtThem.setEnabled(false);
                        BtLtSua.setEnabled(true);
                        BtLtClear.setEnabled(true);
                                               
                }
            }
        });                   
    }
    
   public void themListNV() {    
        nvlt = new NhanVienList();
        
        ArrayList<NhanVien> listr =new ReadWriteFile().ReadFromNhanVien();
        for(NhanVien nv:listr) {
             if(nv.isStatus()){              
            CbLtMnv.addItem(nv.getId());
             }
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
    public void showMessageError(String message){
        JOptionPane.showMessageDialog(this,message,"Thông báo",JOptionPane.ERROR_MESSAGE);
    }
    public void showMessageInf(String message){
        JOptionPane.showMessageDialog(this, message,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
    }
    public String showMessageInput(String message,String title){
        String mess= JOptionPane.showInputDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
        return mess;
    }
    public int showMessageConfirm(String message,String title){
        return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
        
    }
    
    public void initTable(){
        listlt= new ListLt();
        tbModel = new DefaultTableModel();
        tbModel.setColumnIdentifiers(Title);
        listlt.setModel(tbModel);
        LttableLt.setModel(tbModel);
    }
    
    public void ClearLt(){  
        
        TxtLtLcb.setText("");
        TxtLtSnl.setText("");
        TxtLtM.setText("");
        TxtLtY.setText("");
              
    }
    
    public void addLuongthuong(){        
               while(true){
                    ArrayList<LuongThuong> list = new ArrayList<LuongThuong>();
                    LuongThuong lt = new LuongThuong();                    
                    lt.setId(CbLtMnv.getSelectedItem().toString());
                    
                    nvlist = new NhanVienList();
                    ArrayList<NhanVien> listread = nvlist.getList();
               while(true){                  
                    NhanVien nhv = nvlist.FindById(lt.getId());
                    if(nhv!=null){                                          
                       lt.setName(nhv.getName());                        
                        break;  
                    }
                    showMessageError("Mã nhân viên không tồn tại");
                     break;
                }
                   
                    
                    if(!lt.setSalary(TxtLtLcb.getText().trim())){
                        break;
                    }
                    
                    if(!lt.setMonth(TxtLtM.getText().trim())){
                        break;
                    }
                    
                    if(!lt.setYear(TxtLtY.getText().trim())){
                        break;
                    }
                    
                    if(!lt.setWorkingDay(TxtLtSnl.getText().trim())){
                        break;
                    }
                    lt.setStatus(RbLtTt.isSelected());
                    
                    if(Integer.parseInt(TxtLtSnl.getText())>0&&Integer.parseInt(TxtLtSnl.getText())<10){
                        float i = (float) ((10-Integer.parseInt(TxtLtSnl.getText()))*(-0.01)*Integer.parseInt(TxtLtLcb.getText()));
                        lt.setThPh(Float.toString(i));
                        float k= (Float.parseFloat(TxtLtLcb.getText())+i);
                        lt.setTong(Float.toString(k));
                    } else if (Integer.parseInt(TxtLtSnl.getText())<=20){
                        lt.setThPh("0");
                        lt.setTong(TxtLtLcb.getText());
                    }else {
                        float i = (float) ((Integer.parseInt(TxtLtSnl.getText())-20)*(0.01)*Integer.parseInt(TxtLtLcb.getText()));
                        lt.setThPh(Float.toString(i));
                        float k= (Float.parseFloat(TxtLtLcb.getText())+i);
                        lt.setTong(Float.toString(k));
                    } 
                    
                    
                                
                    list.add(lt);
                    
                    new ReadWriteFile().WriteFileLt(list,"LuongThuong.dat",true);
                    showMessageInf("Thêm thành công");                   
                    initTable();
                    ClearLt();
                    break;
        }      
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel01 = new javax.swing.JLabel();
        data1 = new javax.swing.JPanel();
        jLabel02 = new javax.swing.JLabel();
        CbLtMnv = new javax.swing.JComboBox<>();
        jLabel03 = new javax.swing.JLabel();
        TxtLtLcb = new javax.swing.JTextField();
        jLabel04 = new javax.swing.JLabel();
        TxtLtSnl = new javax.swing.JTextField();
        RbLtTt = new javax.swing.JRadioButton();
        jLabel07 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        BtLtClear = new javax.swing.JButton();
        BtLtThem = new javax.swing.JButton();
        BtLtSua = new javax.swing.JButton();
        jLabel05 = new javax.swing.JLabel();
        TxtLtM = new javax.swing.JTextField();
        TxtLtY = new javax.swing.JTextField();
        jLabel06 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        LttableLt = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();

        jLabel01.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel01.setForeground(new java.awt.Color(0, 0, 255));
        jLabel01.setText("Lương thưởng");

        data1.setName(""); // NOI18N

        jLabel02.setText("Mã Nhân Viên");

        CbLtMnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbLtMnvActionPerformed(evt);
            }
        });

        jLabel03.setText("Lương cơ bản");

        TxtLtLcb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtLtLcbActionPerformed(evt);
            }
        });

        jLabel04.setText("Số ngày làm");

        RbLtTt.setText("Đã nhận");
        RbLtTt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbLtTtActionPerformed(evt);
            }
        });

        jLabel07.setText("Tình trạng");

        BtLtClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/Button-Close-icon-16.png"))); // NOI18N
        BtLtClear.setText("Clear");
        BtLtClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtLtClearActionPerformed(evt);
            }
        });

        BtLtThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/new-icon-16.png"))); // NOI18N
        BtLtThem.setText("Thêm");
        BtLtThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtLtThemActionPerformed(evt);
            }
        });

        BtLtSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/Actions-document-edit-icon-16.png"))); // NOI18N
        BtLtSua.setText("Sửa");
        BtLtSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtLtSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtLtThem, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(308, 308, 308)
                .addComponent(BtLtSua, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtLtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtLtThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtLtSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtLtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel05.setText("Tháng");

        TxtLtM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtLtMActionPerformed(evt);
            }
        });

        TxtLtY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtLtYActionPerformed(evt);
            }
        });

        jLabel06.setText("Năm");

        javax.swing.GroupLayout data1Layout = new javax.swing.GroupLayout(data1);
        data1.setLayout(data1Layout);
        data1Layout.setHorizontalGroup(
            data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(data1Layout.createSequentialGroup()
                        .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel05, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel04)
                                .addComponent(jLabel02)))
                        .addGap(30, 30, 30)
                        .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(CbLtMnv, 0, 260, Short.MAX_VALUE)
                                .addComponent(TxtLtSnl))
                            .addComponent(TxtLtM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                        .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel06, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel03, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jLabel07, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtLtY, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtLtLcb, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RbLtTt, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(138, 138, 138))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        data1Layout.setVerticalGroup(
            data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data1Layout.createSequentialGroup()
                .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(data1Layout.createSequentialGroup()
                        .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CbLtMnv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel02))
                        .addGap(29, 29, 29)
                        .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel05)
                            .addComponent(TxtLtM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel04)
                            .addComponent(TxtLtSnl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(data1Layout.createSequentialGroup()
                        .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtLtLcb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel03))
                        .addGap(35, 35, 35)
                        .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel06)
                            .addComponent(TxtLtY, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel07)
                            .addComponent(RbLtTt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        LttableLt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(LttableLt);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel01, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addComponent(data1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator3)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel01)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(data1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(241, 241, 241)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(285, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void RbLtTtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbLtTtActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_RbLtTtActionPerformed

    private void TxtLtLcbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtLtLcbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtLtLcbActionPerformed

    private void BtLtClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtLtClearActionPerformed
        try {
            ClearLt();
            BtLtThem.setEnabled(true);
            BtLtSua.setEnabled(false);
            BtLtClear.setEnabled(false); 
            CbLtMnv.setEnabled(true);
            TxtLtLcb.setEnabled(true);
            TxtLtSnl.setEnabled(true);
            TxtLtM.setEnabled(true);
            TxtLtY.setEnabled(true);
            
            
        } catch (Exception e) {
            showMessageError("Lỗi Clear");
        }    
    }//GEN-LAST:event_BtLtClearActionPerformed

    private void BtLtThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtLtThemActionPerformed
       try {
            addLuongthuong();
        } catch (Exception e) {
            showMessageError("Thêm bị lỗi ");
        }     
    }//GEN-LAST:event_BtLtThemActionPerformed

    private void CbLtMnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbLtMnvActionPerformed
         
    }//GEN-LAST:event_CbLtMnvActionPerformed

    private void TxtLtMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtLtMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtLtMActionPerformed

    private void TxtLtYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtLtYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtLtYActionPerformed

    private void BtLtSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtLtSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtLtSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtLtClear;
    private javax.swing.JButton BtLtSua;
    private javax.swing.JButton BtLtThem;
    private javax.swing.JComboBox<String> CbLtMnv;
    private javax.swing.JTable LttableLt;
    private javax.swing.JRadioButton RbLtTt;
    private javax.swing.JTextField TxtLtLcb;
    private javax.swing.JTextField TxtLtM;
    private javax.swing.JTextField TxtLtSnl;
    private javax.swing.JTextField TxtLtY;
    private javax.swing.JPanel data1;
    private javax.swing.JLabel jLabel01;
    private javax.swing.JLabel jLabel02;
    private javax.swing.JLabel jLabel03;
    private javax.swing.JLabel jLabel04;
    private javax.swing.JLabel jLabel05;
    private javax.swing.JLabel jLabel06;
    private javax.swing.JLabel jLabel07;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
