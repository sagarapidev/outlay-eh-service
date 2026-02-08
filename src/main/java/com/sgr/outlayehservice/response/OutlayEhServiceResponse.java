package com.sgr.outlayehservice.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutlayEhServiceResponse<T> {
    private String status;   // e.g. "SUCCESS" or "ERROR"
    private String message;  // human-readable message
    private T data;          // payload (UserDto, list, etc.)
}