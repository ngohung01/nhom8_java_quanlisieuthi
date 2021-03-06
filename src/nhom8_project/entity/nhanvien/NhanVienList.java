/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom8_project.entity.nhanvien;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import nhom8_project.utils.ReadWriteFile;

/**
 *
 * @author Admin
 */
public class NhanVienList {
    private ArrayList<NhanVien> list = new ReadWriteFile().ReadFromNhanVien();
    public ArrayList<NhanVien> getList(){
        return list;
    }  
    public void add(NhanVien nv){
        list.add(nv);
    }
    public String Status(boolean status){
        if(status){
            return "Đang làm việc";
        }else{
            return "Đã sa thải";
        }
    }
    public void setModel(DefaultTableModel tbModel){
        tbModel.setRowCount(0);
        for(NhanVien nv:list){
            Object[] row = new Object[]{
                nv.getId(),nv.getName(),nv.getBirthday(),nv.getAddress(),nv.getChucVu(),nv.getSex()
                    ,nv.getEmail(),nv.getPhone(),Status(nv.isStatus())
            };
        tbModel.addRow(row);
            
        }
        tbModel.fireTableDataChanged();
    }
    public NhanVien FindById(String id){
        for(NhanVien nv:list){
            if(nv.getId().equals(id)){
                return nv;
            }
        }
        return null;
    }
    public boolean deleteByID(String id){
        for(NhanVien nv:list){
            if(nv.getId().equals(id)){
                list.remove(nv);
                return true;
            }         
        }
        return false;
    }
}
