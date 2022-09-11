package com.kingz.parser.clazz.utils

import com.kingz.kt.utils.HexUtil
import java.util.function.Consumer

/**
 * Class字节码类和属性的访问修饰符工具类
 * access_flags 在字节码文件结构中，信息以2字节储存
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1-200-E.1
 */
object ClassAccessFlagUtils {
    private val classAccessFlagMap: MutableMap<Int, String> = HashMap()

    init {
        classAccessFlagMap[0x0001] = "ACC_PUBLIC"
        // 不允许继承
        classAccessFlagMap[0x0010] = "ACC_FINAL"
        // JDK1.0.2之后编译的文件都会有这个值
        classAccessFlagMap[0x0020] = "ACC_SUPER"
        // 接口
        classAccessFlagMap[0x0200] = "ACC_INTERFACE"
        // 抽象类
        classAccessFlagMap[0x0400] = "ACC_ABSTRACT"
        // 此类或接口是由编译器生成(合成类)，并且不出现在源代码中
        classAccessFlagMap[0x1000] = "ACC_SYNTHETIC"
        // 注解类型
        classAccessFlagMap[0x2000] = "ACC_ANNOTATION"
        // 枚举类型
        classAccessFlagMap[0x4000] = "ACC_ENUM"
    }

    /**
     * 获取16进制对应的访问标志字符串表示 （仅用于类的访问标志）
     *
     * @param flag 访问标志
     * @return
     */
    fun toClassAccString(flag: ByteArray): String {
        val flagVlaue: Int = HexUtil.readInt(flag)
        val flagBuild = StringBuilder()
        classAccessFlagMap.keys.forEach(
            Consumer { key: Int ->
                if ((flagVlaue and key) == key) {
                    flagBuild.append(classAccessFlagMap[key]).append(",")
                }
            }
        )
        return if (flagBuild.isNotEmpty() && flagBuild[flagBuild.length - 1] == ',') {
            flagBuild.substring(0, flagBuild.length - 1)
        } else {
            flagBuild.toString()
        }
    }
}
