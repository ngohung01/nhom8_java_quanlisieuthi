/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package nhom8_project.view.admin;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import nhom8_project.entity.lichsu.LichSu;
import nhom8_project.entity.nhanvien.NhanVien;
import nhom8_project.entity.nhanvien.NhanVienList;
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
        SelectRowTable();
    }
    public void SelectRowTable(){
        
       //Tạo danh sách lựa chọn của nhân viên trên bảng và thiết lập chỉ chọn 1
        ListSelectionModel listModel=tableNVM.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Thêm hàm sự kiện cho bảng table
        listModel.addListSelectionListener(new ListSelectionListener() {
            //Thêm hàng thay đổi giá trị lựa chọn
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //Lấy danh sách các hàng và các cột lựa chọn
                int rows[]=tableNVM.getSelectedRows();
                int cols[]=tableNVM.getSelectedColumns();
                //Nếu có hàng được lựa chọn thì chạy tiếp
                if(cols.length>0&&rows.length>0){
                    //Gán các giá trị từ bảng vào các ô TextField 
                    String id=String.valueOf(tableNVM.getValueAt(rows[0],0));
                    NhanVien a = nvlist.FindById(id);       
                    
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
                        btnAddNVM.setEnabled(false);
                        btnEditNVM.setEnabled(true);
                        btnDetailNVM.setEnabled(true);
                        btnSathai.setEnabled(true);                       
                }
            }
        });                   
    }
    public void initTable(){
        nvlist= new NhanVienList();
        tbModel = new DefaultTableModel();
        tbModel.setColumnIdentifiers(Title);
        nvlist.setModel(tbModel);
        tableNVM.setModel(tbModel);
    }
    //Hiển thị các bảng thông báo
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
    //
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
                    
                    ArrayList<LichSu> listr = new ArrayList<LichSu>();
                    LichSu ls = new LichSu();
                    ls.setTime(new DateFormat().DateNow());
                    ls.setType("Nhân viên");
                    ls.setId(id);
                    ls.setName(nameNVM.getText().trim());
                    ls.setActive("Thêm");
                    ls.setStatus(true);
                    ls.setDescribe("Không");
                    listr.add(ls);
                    new ReadWriteFile().WriteFileLs(listr,"LichSu.dat",true);
                    
                    
                    nv.setChucVu(cbcv.getSelectedIndex(),id);                                     
                    nv.setSex(rdNam.isSelected());
                    list.add(nv);
                    new ReadWriteFile().WriteToNhanVien(list,"nhanvien.dat",true);
                    showMessageInf("Thêm thành công");                   
                    initTable();
                    ClearNV();
                    break;
        }      
    }
    public void EditNV(){   
        if(showMessageConfirm("Bạn có chắc chắn muốn sửa", "Thông báo")==JOptionPane.YES_OPTION){                 
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
                        
                    ArrayList<LichSu> listr = new ArrayList<LichSu>();
                    LichSu ls = new LichSu();
                    ls.setTime(new DateFormat().DateNow());
                    ls.setType("Nhân viên");
                    ls.setId(idCheck);
                    ls.setName(nameNVM.getText().trim());
                    ls.setActive("Sửa");
                    ls.setStatus(true);
                    ls.setDescribe("Không");
                    listr.add(ls);
                    new ReadWriteFile().WriteFileLs(listr,"LichSu.dat",true);
                        
                        nv.setChucVu(cbcv.getSelectedIndex(),idCheck);                                     
                        nv.setSex(rdNam.isSelected());
                        int index= listread.indexOf(nv);
                        listread.remove(index);
                        listread.add(index, nv);
                        new ReadWriteFile().WriteToNhanVien(listread,"nhanvien.dat",false);
                        showMessageInf("Sửa thành công");
                        break;  
                    }
                    showMessageError("Mã nhân viên không tồn tại");
                     break;
                }
        }           
    }
    public void ClearNV(){  
        txtSearchID.setText("");
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
                showMessageError("Không tìm thấy mã nhân viên "+id);
            }           
    }   
    public void Detail(){
       nvdetail = new NhanVienDetailDialog(new Admin(), true,nameNVM.getText().trim(), birthdayNVM.getText().trim(),
                rdNam.isSelected(),phoneNVM.getText().trim() , emailNVM.getText().trim(), addressNVM.getText().trim(), status, describ);
            nvdetail.setVisible(true);         
    }
    
    public void Dismissal(){    
       int x=showMessageConfirm("Bạn chắc chắn muốn sa thải không", "Thông báo");
       if(x==JOptionPane.YES_OPTION){
           status=false;  
           describ=showMessageInput("Lý do sa thải", "Sa thải");                  
           nvlist = new NhanVienList();
           ArrayList<NhanVien> listread = nvlist.getList();
               while(true){                  
                    NhanVien nv = nvlist.FindById(idCheck);
                    if(nv!=null){                                          
                        nv.setStatus(status);
                        nv.setUserName(null);
                        nv.setPassword(null);
                        if(!nv.setDescribed(describ)){
                            break;
                        }
                        
                    ArrayList<LichSu> listr = new ArrayList<LichSu>();
                    LichSu ls = new LichSu();
                    ls.setTime(new DateFormat().DateNow());
                    ls.setType("Nhân viên");
                    ls.setId(idCheck);
                    ls.setName(nameNVM.getText().trim());
                    ls.setActive("Sa thải");
                    ls.setStatus(false);
                    ls.setDescribe(nv.getDescribed());
                    listr.add(ls);
                    new ReadWriteFile().WriteFileLs(listr,"LichSu.dat",true);
                        
                        int index= listread.indexOf(nv);
                        listread.remove(index);
                        listread.add(index, nv);
                        new ReadWriteFile().WriteToNhanVien(listread,"nhanvien.dat",false);
                        showMessageInf("Sa thải thành công");
                        break;  
                    }
                    showMessageError("Mã nhân viên không tồn tại");
                     break;
        }
       }
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
        btnSathai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSathaiActionPerformed(evt);
            }
        });

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
            showMessageError("Lỗi Clear");
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
            showMessageError("Lỗi tìm kiếm");
        }                   
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDetailNVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailNVMActionPerformed
        try {
           Detail();
        } catch (Exception e) {
            showMessageError("Chi tiết bị lỗi");
        }
        
    }//GEN-LAST:event_btnDetailNVMActionPerformed

    private void btnAddNVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNVMActionPerformed
        try {
            addNhanVien();
        } catch (Exception e) {
            showMessageError("Thêm bị lỗi ");
        }     
    }//GEN-LAST:event_btnAddNVMActionPerformed

    private void btnEditNVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditNVMActionPerformed
        try {
            EditNV();
            initTable();
        } catch (Exception e) {
            showMessageError("Edit bị lỗi");
        }      
        
    }//GEN-LAST:event_btnEditNVMActionPerformed

    private void btnSathaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSathaiActionPerformed
        try {
            Dismissal();
            initTable();
        } catch (Exception e) {
            showMessageError("Sa thải bị lỗi");
        }   
    }//GEN-LAST:event_btnSathaiActionPerformed


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
