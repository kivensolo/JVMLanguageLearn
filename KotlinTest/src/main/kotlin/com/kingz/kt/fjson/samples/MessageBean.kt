package com.kingz.kt.fjson.samples

import com.alibaba.fastjson.annotation.JSONField

/**
 * @JsonField 注解用于变量上，常用
 */
class MessageBean {
    @JSONField(name = "id")
    var messageId: String = ""
}