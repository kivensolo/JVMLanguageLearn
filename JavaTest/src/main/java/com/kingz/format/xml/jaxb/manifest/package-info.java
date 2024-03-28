/**
 * Created by hy on 2015/5/18.
 */
@XmlSchema(
	xmlns = {
		@XmlNs(prefix = "android", namespaceURI = "http://schemas.android.com/apk/res/android"),
		@XmlNs(prefix = "tools", namespaceURI = "http://schemas.android.com/tools")
	}
) package com.kingz.format.xml.jaxb.manifest;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;

// 正常情况下，jaxb会把命名空间变成ns1、ns2等。此处使用这种方式，把相关明确的命名空间进行名称声明，
// 防止进行默认转化
