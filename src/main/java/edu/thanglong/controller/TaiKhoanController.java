package edu.thanglong.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.thanglong.model.ChuyenTienRequest;
import edu.thanglong.model.GiaoDichRequest;
import edu.thanglong.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/ngan_hang_goi_lenh")
@RequiredArgsConstructor
public class TaiKhoanController {
    private final TaiKhoanService taiKhoanService;

    @PostMapping("/chuyen_tien")
    public ResponseEntity<String> chuyenTien(@RequestBody ChuyenTienRequest request) {
        taiKhoanService.chuyenTien(request.getFrom(), request.getTo(), request.getAmount());
        return ResponseEntity.ok("Chuyen tien thanh cong");
    }

    @PostMapping("/rut_tien")
    public ResponseEntity<String> rutTien(@RequestBody GiaoDichRequest request) {
        taiKhoanService.rutTien(request.getMaTaiKhoan(), request.getSoTien());
        return ResponseEntity.ok("Rut tien thanh cong");
    }


    @PostMapping("/gui_tien")
    public ResponseEntity<String> guiTien(@RequestBody GiaoDichRequest request) {
        taiKhoanService.guiTien(request.getMaTaiKhoan(), request.getSoTien());
        return ResponseEntity.ok("Gui tien thanh cong");
    }

    @GetMapping("/lay_so_du/{taiKhoan}")
    public ResponseEntity<Double> laySoDu(@PathVariable Long taiKhoan) {
        return ResponseEntity.ok(taiKhoanService.laySoDu(taiKhoan));
    }

    @PostMapping("/tinh_lai")
    public ResponseEntity<String> tinhLai(@RequestBody Map<String, BigDecimal> tyLe) {
        taiKhoanService.tinhLai(tyLe);
        return ResponseEntity.ok("Tinh lai thanh cong");
    }

    @GetMapping("/in_sao_ke_thang")
    public ResponseEntity<List<String>> inSaoKeThang(
            @RequestParam Long taiKhoan,
            @RequestParam int thang,
            @RequestParam int nam) {
        return ResponseEntity.ok(taiKhoanService.inSaoKeThang(taiKhoan, thang, nam));
    }
}