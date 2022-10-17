package com.groupthree.quanlyno.data.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NguoiNo implements Serializable {
    private Integer id;
    private String ten;
    private byte[] anh;
    private String moiQuanHe;
    private String ngheNghiep;
    private String queQuan;
    private String diaChi;
    private String cmnd;
    private String sdt;
    private String email;
    private LocalDateTime ngaySinh;
    private Boolean gioiTinh;

    public LocalDateTime getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDateTime ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public NguoiNo() {
        gioiTinh = true;
    }

    public NguoiNo(Integer id, String ten, byte[] anh, String moiQuanHe, String ngheNghiep, String queQuan, String diaChi, String cmnd, String sdt, String email) {
        this.id = id;
        this.ten = ten;
        this.anh = anh;
        this.moiQuanHe = moiQuanHe;
        this.ngheNghiep = ngheNghiep;
        this.queQuan = queQuan;
        this.diaChi = diaChi;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public String getMoiQuanHe() {
        return moiQuanHe;
    }

    public void setMoiQuanHe(String moiQuanHe) {
        this.moiQuanHe = moiQuanHe;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
