package edu.thanglong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChuyenTienRequest {
    private Long tuTaiKhoan;
    private Long denTaiKhoan;
    private Double soTien;
}