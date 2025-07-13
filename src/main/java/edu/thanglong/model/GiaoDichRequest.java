package edu.thanglong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDichRequest {
    private int taiKhoan;
    private double soTien;
    
    void setTaiKhoan(int taiKhoan) {
    	this.taiKhoan = taiKhoan;
    }
    int taiKhoan() {
    	return taiKhoan;
    }
}