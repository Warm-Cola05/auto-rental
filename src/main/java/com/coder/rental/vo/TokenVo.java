package com.coder.rental.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenVo {
    private String token;
    private Long expireTime;
}
