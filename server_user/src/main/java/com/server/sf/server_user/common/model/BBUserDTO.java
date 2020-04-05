package com.server.sf.server_user.common.model;

import lombok.Data;

@Data
public class BBUserDTO {
    String userId;
    String userName;
    String userPhone;
    int jwtVersion;
}
