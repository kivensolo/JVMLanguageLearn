package com.kingz.langfeatures.v1_5

import kotlin.jvm.JvmInline

/**
 *
 */

/**
 * Inline classes。jVM后端必须需要@JvmInline的注解
 */
@JvmInline
value class Password(val s: String)