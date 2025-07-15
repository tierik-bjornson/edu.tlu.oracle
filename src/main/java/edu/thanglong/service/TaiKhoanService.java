package edu.thanglong.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;



import java.util.*;

@Service
@RequiredArgsConstructor
public class TaiKhoanService {
//    private final JdbcTemplate jdbc;  -- code cũ cần
    private final RestTemplate restTemplate;
    
    @Value("${api.base-url}") 
    private String baseUrl;

//    public void chuyenTien(Long tu, Long den, double soTien) {
//        jdbc.update("CALL ngan_hang.chuyen_tien(?, ?, ?)", tu, den, soTien);
//    }
    
    public void chuyenTien(Long tu, Long den, BigDecimal soTien) throws JsonProcessingException {
        String url = baseUrl + "/chuyentien";
        Map<String, Object> body = new HashMap<>();
        body.put("from", tu);
        body.put("to", den);
        body.put("amount", soTien);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(body);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(url, requestEntity, String.class);        

    }
    

//    public void rutTien(int taiKhoan, double soTien) {
//
//        jdbc.update("CALL ngan_hang.rut_tien(?, ?)", taiKhoan, soTien);
//    }
    
    public void rutTien(Long taiKhoan, BigDecimal soTien) throws JsonProcessingException {
        String url = baseUrl + "/ruttien";

        Map<String, Object> body = new HashMap<>();
        body.put("maTaiKhoan", taiKhoan);
        body.put("soTien", soTien);
        //
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(body);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(url, requestEntity, String.class);   
    }
    


//    public void guiTien(int taiKhoan, double soTien) {
//        jdbc.update("CALL ngan_hang_goi_lenh.gui_tien(?, ?)", taiKhoan, soTien);
//    }
    
    public void guiTien(Long taiKhoan, BigDecimal soTien) throws JsonProcessingException {
        String url = baseUrl + "/guitien";

        Map<String, Object> request = new HashMap<>();
        request.put("maTaiKhoan", taiKhoan);
        request.put("soTien", soTien);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(url, requestEntity, String.class);   

    }

//    public double laySoDu(int taiKhoan) {
//        return jdbc.queryForObject("SELECT ngan_hang_goi_lenh.lay_so_du(?) FROM dual", Double.class, taiKhoan);
//    }
    
    public double laySoDu(Long taiKhoan) {
        String url = baseUrl + "/laysodu/" + taiKhoan;

        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
        return response.getBody();
    }

//    public void tinhLai(double tyLe) {
//        jdbc.update("CALL ngan_hang_goi_lenh.tinh_lai(?)", tyLe);
//    }
    
    public void tinhLai(Map<String, ?> request) {
        String url = "http://localhost:8080/api/nganhang/tinhlai";
        restTemplate.postForEntity(url, request, String.class);
    }

    
   

//    public List<String> inSaoKeThang(int taiKhoan, int thang, int nam) {
//        return jdbc.query(
//            "SELECT TO_CHAR(ngay_giao_dich, 'YYYY-MM-DD') || ' | ' || loai_giao_dich || ' | ' || so_tien FROM giao_dich WHERE ma_tai_khoan = ? AND EXTRACT(MONTH FROM ngay_giao_dich) = ? AND EXTRACT(YEAR FROM ngay_giao_dich) = ? ORDER BY ngay_giao_dich",
//            (rs, rowNum) -> rs.getString(1),
//            taiKhoan, thang, nam
//        );
//    }
    

    public List<String> inSaoKeThang(Long taiKhoan, int thang, int nam) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/saoke/" + taiKhoan)
                .queryParam("thang", thang)
                .queryParam("nam", nam)
                .toUriString();

        System.out.println("Gọi URL: " + url); // kiểm tra log này

        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return response.getBody();
    }



    
}
