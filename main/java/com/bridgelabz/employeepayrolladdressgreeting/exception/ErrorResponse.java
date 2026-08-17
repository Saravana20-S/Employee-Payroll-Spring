package com.bridgelabz.employeepayrolladdressgreeting.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private String localDateTime;

    public ErrorResponse(int status,String message){
        this.status=status;
        this.message=message;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.localDateTime=LocalDateTime.now().format(formatter);
    }
}
