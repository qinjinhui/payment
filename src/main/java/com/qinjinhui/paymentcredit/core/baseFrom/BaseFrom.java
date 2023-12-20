package com.qinjinhui.paymentcredit.core.baseFrom;

import com.qinjinhui.paymentcredit.validation.annotations.NotEmpty;
import lombok.Builder;
import lombok.Data;

/**
 * @Author qinjinhui
 * @Date 2022/11/25
 * @Describe
 **/
@Data
@Builder
public class BaseFrom {

    @NotEmpty(describe = "不能为空",paraphrase = "这是什么东西")
    String token;

    @NotEmpty(describe = "不能为空",paraphrase = "这是什么东西")
    String token1;
}
