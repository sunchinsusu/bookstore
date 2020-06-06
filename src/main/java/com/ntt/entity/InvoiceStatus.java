package com.ntt.entity;

public enum InvoiceStatus {
    Da_xac_nhan("Đã xác nhận"), Dang_van_chuyen("Đang vận chuyển"), Da_giao_hang("Đã giao hàng"), Da_huy("Đã hủy");

    private final String displayValue;

    private InvoiceStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
