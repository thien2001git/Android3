package com.groupthree.quanlyno.data.Models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class No implements Serializable {
    private Integer id;
    private Integer idNguoiNo;
    private Double soTienVay;
    private Double laiSuat;
    private Double soCanTraConLai;
    private LocalDateTime ngayChoVay;
    private Double tongSoCanTra;
    private LocalDateTime hanCuoi;
    private String hinhThucVay;
    private String trangThai;
    private String ghiChu;

    public static final String NAM = "năm";
    public static final String NGAY = "ngày";
    public static final String THANG = "tháng";

    public No() {
    }

    public No(Integer id, Integer idNguoiNo, Double soTienVay, Double laiSuat, Double soCanTraConLai, LocalDateTime ngayChoVay, Double tongSoCanTra, LocalDateTime hanCuoi, String hinhThucVay, String trangThai, String ghiChu) {
        this.id = id;
        this.idNguoiNo = idNguoiNo;
        this.soTienVay = soTienVay;
        this.laiSuat = laiSuat;
        this.soCanTraConLai = soCanTraConLai;
        this.ngayChoVay = ngayChoVay;
        this.tongSoCanTra = tongSoCanTra;
        this.hanCuoi = hanCuoi;
        this.hinhThucVay = hinhThucVay;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdNguoiNo() {
        return idNguoiNo;
    }

    public void setIdNguoiNo(Integer idNguoiNo) {
        this.idNguoiNo = idNguoiNo;
    }

    public Double getSoTienVay() {
        return soTienVay;
    }

    public void setSoTienVay(Double soTienVay) {
        this.soTienVay = soTienVay;
    }

    public Double getLaiSuat() {
        return laiSuat;
    }

    public void setLaiSuat(Double laiSuat) {
        this.laiSuat = laiSuat;
    }

    public Double getSoCanTraConLai() {
        return soCanTraConLai;
    }

    public void setSoCanTraConLai(Double soCanTraConLai) {
        this.soCanTraConLai = soCanTraConLai;
    }

    public LocalDateTime getNgayChoVay() {
        return ngayChoVay;
    }

    public void setNgayChoVay(LocalDateTime ngayChoVay) {
        this.ngayChoVay = ngayChoVay;
    }

    public Double getTongSoCanTra() {
        return tongSoCanTra;
    }

    public void setTongSoCanTra(Double tongSoCanTra) {
        this.tongSoCanTra = tongSoCanTra;
    }

    public LocalDateTime getHanCuoi() {
        return hanCuoi;
    }

    public void setHanCuoi(LocalDateTime hanCuoi) {
        this.hanCuoi = hanCuoi;
    }

    public String getHinhThucVay() {
        return hinhThucVay;
    }

    public void setHinhThucVay(String hinhThucVay) {
        this.hinhThucVay = hinhThucVay;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateChange() {
        Duration diff = Duration.between(hanCuoi, ngayChoVay);
        long diffDays = diff.toDays();

        if (hinhThucVay == NGAY) {
            tongSoCanTra = soTienVay * Math.pow(1 + laiSuat / 100, diffDays);
        } else if (hinhThucVay == THANG) {
            tongSoCanTra = soTienVay * Math.pow(1 + laiSuat / 100, diffDays / 30);
        } else if (hinhThucVay == NAM) {
            tongSoCanTra = soTienVay * Math.pow(1 + laiSuat / 100, diffDays / 365);
        }
        soCanTraConLai = tongSoCanTra;

    }
}
