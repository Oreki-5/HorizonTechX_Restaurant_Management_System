package com.Oreki5.RestaurantManagementSystem.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseObj {
    private Object data;
    private String warningMsg;

    public ResponseObj(Object data) {
        this.data = data;
    }

    public ResponseObj(Object data, String warningMsg) {
        this.data = data;
        this.warningMsg = warningMsg;
    }
}
