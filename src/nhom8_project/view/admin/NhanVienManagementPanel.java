/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package nhom8_project.view.admin;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import nhom8_project.entity.NhanVien;
import nhom8_project.entity.NhanVienList;
import nhom8_project.utils.DateFormat;
import nhom8_project.utils.ReadWriteFile;

/**
 *
 * @author Admin
 */
public final class NhanVienManagementPanel extends javax.swing.JPanel {
    private DefaultTableModel tbModel=null;
    private Object [] Title= new Object[]{"Mã nhân viên","Tên nhân viên","Ngày sinh","Địa chỉ","Chức vụ","Giới tính","Email","Số điện thoại","Tình trạng"};
    private NhanVienList nvlist;
    private NhanVienDetailDialog nvdetail;
    private String idCheck,describ;
    private boolean status;
    /**
     * Creates new form NhanVienManagementPanel
     */
  
    public NhanVienManagementPanel() {
        initComponents();
        btnEditNVM.setEnabled(false);
        btnDetailNVM.setEnabled(false);
        btnSathai.setEnabled(false);
        initTable();
        
    }
    public void initTable(){
        nvlist= new NhanVienList();
        tbModel = new DefaultTableModel();
        tbModel.setColumnIdentifiers(Title);
        nvlist.setModel(tbModel);
        tableNVM.setModel(tbModel);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
//      public void showListNV(List<NhanVien> list){
//        int size=list.size();
//        Object [][] nhanvien =new Object[size][8];
//        for(int i=0;i<size;i++){
//            nhanvien[i][0]=list.get(i).getId();
//            nhanvien[i][1]=list.get(i).getName();
//            nhanvien[i][2]=list.get(i).getBirthday();
//            nhanvien[i][3]=list.get(i).getAddress();
//            nhanvien[i][4]=list.get(i).getChucVu();
//            if(list.get(i).isMale()){
//                nhanvien[i][5]="Nam"; 
//            }else{
//                nhanvien[i][5]="Nữ"; 
//            }
//            nhanvien[i][6]=list.get(i).getEmail();
//            nhanvien[i][7]=list.get(i).getPhone();
//                   
//        }
//         tableNVM.setModel(new DefaultTableModel(nhanvien, Title));
//    }
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this,message);
    }
       public void addNhanVien(){        
               while(true){
                    ArrayList<NhanVien> list = new ArrayList<NhanVien>();
                    NhanVien nv = new NhanVien();
                    int size=new ReadWriteFile().ReadFromNhanVien().size();       
                    String id=Integer.toString(new DateFormat().Year(LocalDate.now()))+"nv"+Integer.toString(++size);
                    nv.setId(id);
                    if(!nv.setName(nameNVM.getText().trim())){
                        break;
                    }
                    if(!nv.setBirthday(birthdayNVM.getText().trim())){
                        break;
                    }
                    if(!nv.setAddress(addressNVM.getText().trim())){
                        break;
                    }
                    if(!nv.setPhone(phoneNVM.getText().trim())){
                        break;
                    }
                    if(!nv.setEmail(emailNVM.getText().trim())){
                        break;
                    }     
                    nv.setChucVu(cbcv.getSelectedIndex(),id);                                     
                    nv.setSex(rdNam.isSelected());
                    list.add(nv);
                    new ReadWriteFile().WriteToFile(list,"nhanvien.dat",true);
                    JOptionPane.showMessageDialog(this,"Thêm thành công");
                    initTable();
                    ClearNV();
                    break;
        }      
    }
    public void EditNV(){    
           nvlist = new NhanVienList();
           ArrayList<NhanVien> listread = nvlist.getList();
               while(true){                  
                    NhanVien nv = nvlist.FindById(idCheck);
                    if(nv!=null){                                          
                        if(!nv.setName(nameNVM.getText().trim())){
                            break;
                        }
                        if(!nv.setBirthday(birthdayNVM.getText().trim())){
                            break;
                        }
                        if(!nv.setAddress(addressNVM.getText().trim())){
                            break;
                        }
                        if(!nv.setPhone(phoneNVM.getText().trim())){
                            break;
                        }
                        if(!nv.setEmail(emailNVM.getText().trim())){
                            break;
                        }     
                        nv.setChucVu(cbcv.getSelectedIndex(),idCheck);                                     
                        nv.setSex(rdNam.isSelected());
                        int index= listread.indexOf(nv);
                        listread.remove(index);
                        listread.add(index, nv);
                        new ReadWriteFile().WriteToFile(listread,"nhanvien.dat",false);
                        showMessage("Sửa thành công");
                        break;  
                    }
                    showMessage("Mã nhân viên không tồn tại");
                     break;
        }           
    }
    public void ClearNV(){      
        nameNVM.setText("");
        birthdayNVM.setText("");
        addressNVM.setText("");
        phoneNVM.setText("");
        emailNVM.setText("");      
    }
    public void searchNV(String id){             
        NhanVien a = nvlist.FindById(id);       
            if(a!=null){
                idCheck=id;
                nameNVM.setText(a.getName());
                birthdayNVM.setText(a.getBirthday());
                addressNVM.setText(a.getAddress());
                phoneNVM.setText(a.getPhone());
                emailNVM.setText(a.getEmail());
                if(a.getChucVu().equals("Bán hàng")){
                    cbcv.setSelectedIndex(0);
                }else if(a.getChucVu().equals("Bảo vệ")){
                    cbcv.setSelectedIndex(2);
                }else{
                    cbcv.setSelectedIndex(1);
                }
                if("Nam".equals(a.getSex())){
                    rdNam.setSelected(true);
                }else if(a.getSex().equals("Nữ")){
                    rdNu.setSelected(true);
                }
                status=a.isStatus();
                describ=a.getDescribed();                      
            }else{
                showMessage("Không tìm thấy mã nhân viên "+id);
            }           
    }   
    public void Detail(){
       nvdetail = new NhanVienDetailDialog(new Admin(), true,nameNVM.getText(), birthdayNVM.getText(),
                rdNam.isSelected(),phoneNVM.getText() , emailNVM.getText(), addressNVM.getText(), status, describ);
            nvdetail.setVisible(true);         
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNVM = new javax.swing.JTable();
        data = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameNVM = new javax.swing.JTextField();
        birthdayNVM = new javax.swing.JTextField();
        addressNVM = new javax.swing.JTextField();
        phoneNVM = new javax.swing.JTextField();
        emailNVM = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cbcv = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnEditNVM = new javax.swing.JButton();
        btnAddNVM = new javax.swing.JButton();
        btnClearNVM = new javax.swing.JButton();
        btnDetailNVM = new javax.swing.JButton();
        btnSathai = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtSearchID = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Quản lý nhân viên");

        jScrollPane1.setViewportView(tableNVM);

        data.setName(""); // NOI18N

        jLabel7.setText("Địa chỉ");

        jLabel8.setText("Số điện thoại");

        buttonGroup1.add(rdNam);
        rdNam.setSelected(true);
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        jLabel3.setText("Họ tên");

        jLabel9.setText("Email");

        jLabel10.setText("Giới tính");

        jLabel4.setText("Ngày sinh");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/Person-Male-Light-icon-48.png"))); // NOI18N

        btnBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/open-file-icon-16.png"))); // NOI18N
        btnBrowse.setText("Open File");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(btnBrowse)
                .addGap(57, 57, 57))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setText("Chức vụ");

        cbcv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bán hàng", "Bảo trì hệ thống", "Bảo vệ" }));

        btnEditNVM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/Actions-document-edit-icon-16.png"))); // NOI18N
        btnEditNVM.setText("Sửa");
        btnEditNVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditNVMActionPerformed(evt);
            }
        });

        btnAddNVM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/new-icon-16.png"))); // NOI18N
        btnAddNVM.setText("Thêm");
        btnAddNVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNVMActionPerformed(evt);
            }
        });

        btnClearNVM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/Button-Close-icon-16.png"))); // NOI18N
        btnClearNVM.setText("Clear");
        btnClearNVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearNVMActionPerformed(evt);
            }
        });

        btnDetailNVM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/Actions-view-choose-icon-24.png"))); // NOI18N
        btnDetailNVM.setText("Chi tiết");
        btnDetailNVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailNVMActionPerformed(evt);
            }
        });

        btnSathai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/Actions-edit-delete-icon-16.png"))); // NOI18N
        btnSathai.setText("Sa thải");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDetailNVM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClearNVM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditNVM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddNVM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnSathai, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddNVM, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnSathai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnEditNVM, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDetailNVM, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClearNVM, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dataLayout = new javax.swing.GroupLayout(data);
        data.setLayout(dataLayout);
        dataLayout.setHorizontalGroup(
            dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addGap(62, 62, 62)
                .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dataLayout.createSequentialGroup()
                        .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nameNVM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addressNVM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailNVM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneNVM)
                    .addComponent(cbcv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dataLayout.createSequentialGroup()
                        .addComponent(birthdayNVM, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        dataLayout.setVerticalGroup(
            dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(dataLayout.createSequentialGroup()
                        .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dataLayout.createSequentialGroup()
                                .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(nameNVM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(birthdayNVM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phoneNVM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addressNVM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(emailNVM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel2)
                                    .addComponent(cbcv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(dataLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel11.setText("Mã Nhân Viên :");

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nhom8_project/icon/search-icon-16.png"))); // NOI18N
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(31, 31, 31)
                .addComponent(txtSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator5)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearNVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearNVMActionPerformed
        try {
            ClearNV();
            btnAddNVM.setEnabled(true);
            btnEditNVM.setEnabled(false);
            btnDetailNVM.setEnabled(false);
            btnSathai.setEnabled(false);
            txtSearchID.setText("");
        } catch (Exception e) {
            showMessage("Lỗi Clear");
        }      
    }//GEN-LAST:event_btnClearNVMActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            searchNV(txtSearchID.getText());
            btnAddNVM.setEnabled(false);
            btnEditNVM.setEnabled(true);
            btnDetailNVM.setEnabled(true);
            btnSathai.setEnabled(true);   
        } catch (Exception e) {
            showMessage("Lỗi tìm kiếm");
        }                   
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDetailNVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailNVMActionPerformed
        try {
           Detail();
        } catch (Exception e) {
            showMessage("Lỗi Detail");
        }
        
    }//GEN-LAST:event_btnDetailNVMActionPerformed

    private void btnAddNVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNVMActionPerformed
        try {
            addNhanVien();
        } catch (Exception e) {
            showMessage("Lỗi thêm ");
        }     
    }//GEN-LAST:event_btnAddNVMActionPerformed

    private void btnEditNVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditNVMActionPerformed
        try {
            EditNV();
            initTable();
        } catch (Exception e) {
            showMessage("Lỗi Edit");
        }      
        
    }//GEN-LAST:event_btnEditNVMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressNVM;
    private javax.swing.JTextField birthdayNVM;
    private javax.swing.JButton btnAddNVM;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnClearNVM;
    private javax.swing.JButton btnDetailNVM;
    private javax.swing.JButton btnEditNVM;
    private javax.swing.JButton btnSathai;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbcv;
    private javax.swing.JPanel data;
    private javax.swing.JTextField emailNVM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField nameNVM;
    private javax.swing.JTextField phoneNVM;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tableNVM;
    private javax.swing.JTextField txtSearchID;
    // End of variables declaration//GEN-END:variables
}
