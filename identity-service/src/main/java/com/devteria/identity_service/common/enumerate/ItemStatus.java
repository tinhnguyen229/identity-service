package com.devteria.identity_service.common.enumerate;

public enum ItemStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");


    private String label;
    ItemStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
