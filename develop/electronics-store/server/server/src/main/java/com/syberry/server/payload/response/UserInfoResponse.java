package com.syberry.server.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private Long id;
    private Long cart_id;
    private Long wishlist_id;
    private String username;
    private List<String> roles;
}
