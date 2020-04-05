package com.server.sf.video.common.model;

import lombok.Data;

@Data
public class BBUserDTO {
    String userId;
    String userName;
    String userPhone;
    int jwtVersion;
}
