package com.kingz.parser.clazz.base


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.CLASS)
annotation class Parser(val name: String = "")