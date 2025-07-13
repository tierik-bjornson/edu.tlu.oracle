package edu.thanglong.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaiKhoanService {
    private final JdbcTemplate jdbc;

    public void chuyenTien(Long tu, Long den, double soTien) {
        jdbc.update("CALL ngan_hang.chuyen_tien(?, ?, ?)", tu, den, soTien);
    }

    public void rutTien(int taiKhoan, double soTien) {
        jdbc.update("CALL ngan_hang.rut_tien(?, ?)", taiKhoan, soTien);
    }

    public void guiTien(int taiKhoan, double soTien) {
        jdbc.update("CALL ngan_hang_goi_lenh.gui_tien(?, ?)", taiKhoan, soTien);
    }

    public double laySoDu(int taiKhoan) {
        return jdbc.queryForObject("SELECT ngan_hang_goi_lenh.lay_so_du(?) FROM dual", Double.class, taiKhoan);
    }

    public void tinhLai(double tyLe) {
        jdbc.update("CALL ngan_hang_goi_lenh.tinh_lai(?)", tyLe);
    }

    public List<String> inSaoKeThang(int taiKhoan, int thang, int nam) {
        return jdbc.query(
            "SELECT TO_CHAR(ngay_giao_dich, 'YYYY-MM-DD') || ' | ' || loai_giao_dich || ' | ' || so_tien FROM giao_dich WHERE ma_tai_khoan = ? AND EXTRACT(MONTH FROM ngay_giao_dich) = ? AND EXTRACT(YEAR FROM ngay_giao_dich) = ? ORDER BY ngay_giao_dich",
            (rs, rowNum) -> rs.getString(1),
            taiKhoan, thang, nam
        );
    }
}
