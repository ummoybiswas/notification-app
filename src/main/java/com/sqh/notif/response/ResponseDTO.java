package com.sqh.notif.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ResponseDTO {
    private boolean success;
    private String message;
}
