package com.ntt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    TruyenNgan("Truyện ngắn"), TieuThuyet("Tiểu thuyết"), KhoaHoc("Khoa học"), KiNangSong("Kĩ năng sống");

    private final String displayValue;

    private Category(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
