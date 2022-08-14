package com.kingz.parser.clazz.constant_pool

import com.kingz.kt.utils.HexUtil
import com.kingz.parser.clazz.GeneralInformation
import com.kingz.parser.clazz.utils.BufferedInputStream
import java.io.InputStream
import java.nio.charset.Charset

/**
 * 常量池数据解析器
 */
class CPInfosParser {
    //常量池计数器
    var constant_pool_count:ByteArray = ByteArray(2)
    fun parser(ins:InputStream){
        val localIns = ins as BufferedInputStream
        localIns.read(constant_pool_count)
        val cpCounts = HexUtil.readInt(constant_pool_count)
        GeneralInformation.constantPoolCount = cpCounts
        println("\nConstant pool:")
//        for(index in 0..cpCounts) {
            //Check cp_info type
            val type = localIns.read()
            //TODO 根据不同的常量进行常量数据解析
            if(type == 1){ //utf-8
                //获取utf-8编码字符串所占的字符数
                val constantUtf8Info = CP_Infos.CONSTANT_Utf8_Info()
                localIns.read(constantUtf8Info.length)
                val size = HexUtil.readInt(constantUtf8Info.length)
                constantUtf8Info.bytesLegth = size
                val stringBytes = ByteArray(size)
                localIns.read(stringBytes)
                val infoString = stringBytes.toString(Charset.forName("UTF-8"))
                println("#1 = ${CP_Tag.getTypeName(type)}\t\t\t $infoString")
            }
//        }
    }
}