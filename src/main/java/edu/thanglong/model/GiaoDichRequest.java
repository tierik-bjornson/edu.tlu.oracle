package edu.thanglong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDichRequest {
    private Long maTaiKhoan;
    private BigDecimal soTien;
    
}