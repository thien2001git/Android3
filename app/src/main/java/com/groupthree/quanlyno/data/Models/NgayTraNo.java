package com.groupthree.quanlyno.data.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NgayTraNo implements Serializable {
    private Integer id;
    private Integer idNo;
    private Double soTien;
    private LocalDateTime ngayTra;

    public NgayTraNo() {
    }

    public NgayTraNo(Integer id, Integer idNo, Double soTien, LocalDateTime ngayTra) {
        this.id = id;
        this.idNo = idNo;
        this.soTien = soTien;
        this.ngayTra = ngayTra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdNo() {
        return idNo;
    }

    public void setIdNo(Integer idNo) {
        this.idNo = idNo;
    }

    public Double getSoTien() {
        return soTien;
    }

    public void setSoTien(Double soTien) {
        this.soTien = soTien;
    }

    public LocalDateTime getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(LocalDateTime ngayTra) {
        this.ngayTra = ngayTra;
    }
}
