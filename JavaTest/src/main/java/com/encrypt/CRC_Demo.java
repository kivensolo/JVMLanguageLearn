package com.encrypt;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.CRC32;

/**
 * CRC校验手撸测试
 */
public class CRC_Demo {
  static int[] tableStand = {
            0x00000000, 0x77073096, 0xee0e612c, 0x990951ba, 0x076dc419, 0x706af48f, 0xe963a535, 0x9e6495a3,
            0x0edb8832, 0x79dcb8a4, 0xe0d5e91e, 0x97d2d988, 0x09b64c2b, 0x7eb17cbd, 0xe7b82d07, 0x90bf1d91,
            0x1db71064, 0x6ab020f2, 0xf3b97148, 0x84be41de, 0x1adad47d, 0x6ddde4eb, 0xf4d4b551, 0x83d385c7,
            0x136c9856, 0x646ba8c0, 0xfd62f97a, 0x8a65c9ec, 0x14015c4f, 0x63066cd9, 0xfa0f3d63, 0x8d080df5,
            0x3b6e20c8, 0x4c69105e, 0xd56041e4, 0xa2677172, 0x3c03e4d1, 0x4b04d447, 0xd20d85fd, 0xa50ab56b,
            0x35b5a8fa, 0x42b2986c, 0xdbbbc9d6, 0xacbcf940, 0x32d86ce3, 0x45df5c75, 0xdcd60dcf, 0xabd13d59,
            0x26d930ac, 0x51de003a, 0xc8d75180, 0xbfd06116, 0x21b4f4b5, 0x56b3c423, 0xcfba9599, 0xb8bda50f,
            0x2802b89e, 0x5f058808, 0xc60cd9b2, 0xb10be924, 0x2f6f7c87, 0x58684c11, 0xc1611dab, 0xb6662d3d,
            0x76dc4190, 0x01db7106, 0x98d220bc, 0xefd5102a, 0x71b18589, 0x06b6b51f, 0x9fbfe4a5, 0xe8b8d433,
            0x7807c9a2, 0x0f00f934, 0x9609a88e, 0xe10e9818, 0x7f6a0dbb, 0x086d3d2d, 0x91646c97, 0xe6635c01,
            0x6b6b51f4, 0x1c6c6162, 0x856530d8, 0xf262004e, 0x6c0695ed, 0x1b01a57b, 0x8208f4c1, 0xf50fc457,
            0x65b0d9c6, 0x12b7e950, 0x8bbeb8ea, 0xfcb9887c, 0x62dd1ddf, 0x15da2d49, 0x8cd37cf3, 0xfbd44c65,
            0x4db26158, 0x3ab551ce, 0xa3bc0074, 0xd4bb30e2, 0x4adfa541, 0x3dd895d7, 0xa4d1c46d, 0xd3d6f4fb,
            0x4369e96a, 0x346ed9fc, 0xad678846, 0xda60b8d0, 0x44042d73, 0x33031de5, 0xaa0a4c5f, 0xdd0d7cc9,
            0x5005713c, 0x270241aa, 0xbe0b1010, 0xc90c2086, 0x5768b525, 0x206f85b3, 0xb966d409, 0xce61e49f,
            0x5edef90e, 0x29d9c998, 0xb0d09822, 0xc7d7a8b4, 0x59b33d17, 0x2eb40d81, 0xb7bd5c3b, 0xc0ba6cad,
            0xedb88320, 0x9abfb3b6, 0x03b6e20c, 0x74b1d29a, 0xead54739, 0x9dd277af, 0x04db2615, 0x73dc1683,
            0xe3630b12, 0x94643b84, 0x0d6d6a3e, 0x7a6a5aa8, 0xe40ecf0b, 0x9309ff9d, 0x0a00ae27, 0x7d079eb1,
            0xf00f9344, 0x8708a3d2, 0x1e01f268, 0x6906c2fe, 0xf762575d, 0x806567cb, 0x196c3671, 0x6e6b06e7,
            0xfed41b76, 0x89d32be0, 0x10da7a5a, 0x67dd4acc, 0xf9b9df6f, 0x8ebeeff9, 0x17b7be43, 0x60b08ed5,
            0xd6d6a3e8, 0xa1d1937e, 0x38d8c2c4, 0x4fdff252, 0xd1bb67f1, 0xa6bc5767, 0x3fb506dd, 0x48b2364b,
            0xd80d2bda, 0xaf0a1b4c, 0x36034af6, 0x41047a60, 0xdf60efc3, 0xa867df55, 0x316e8eef, 0x4669be79,
            0xcb61b38c, 0xbc66831a, 0x256fd2a0, 0x5268e236, 0xcc0c7795, 0xbb0b4703, 0x220216b9, 0x5505262f,
            0xc5ba3bbe, 0xb2bd0b28, 0x2bb45a92, 0x5cb36a04, 0xc2d7ffa7, 0xb5d0cf31, 0x2cd99e8b, 0x5bdeae1d,
            0x9b64c2b0, 0xec63f226, 0x756aa39c, 0x026d930a, 0x9c0906a9, 0xeb0e363f, 0x72076785, 0x05005713,
            0x95bf4a82, 0xe2b87a14, 0x7bb12bae, 0x0cb61b38, 0x92d28e9b, 0xe5d5be0d, 0x7cdcefb7, 0x0bdbdf21,
            0x86d3d2d4, 0xf1d4e242, 0x68ddb3f8, 0x1fda836e, 0x81be16cd, 0xf6b9265b, 0x6fb077e1, 0x18b74777,
            0x88085ae6, 0xff0f6a70, 0x66063bca, 0x11010b5c, 0x8f659eff, 0xf862ae69, 0x616bffd3, 0x166ccf45,
            0xa00ae278, 0xd70dd2ee, 0x4e048354, 0x3903b3c2, 0xa7672661, 0xd06016f7, 0x4969474d, 0x3e6e77db,
            0xaed16a4a, 0xd9d65adc, 0x40df0b66, 0x37d83bf0, 0xa9bcae53, 0xdebb9ec5, 0x47b2cf7f, 0x30b5ffe9,
            0xbdbdf21c, 0xcabac28a, 0x53b39330, 0x24b4a3a6, 0xbad03605, 0xcdd70693, 0x54de5729, 0x23d967bf,
            0xb3667a2e, 0xc4614ab8, 0x5d681b02, 0x2a6f2b94, 0xb40bbe37, 0xc30c8ea1, 0x5a05df1b, 0x2d02ef8d,
        };

     private static int[] G_PUI32CRC32TABLE = new int[]
    {
        0x00000000, 0x1EDC6F41, 0x3DB8DE82, 0x2364B1C3,
        0x7B71BD04, 0x65ADD245, 0x46C96386, 0x58150CC7,
        0xF6E37A08, 0xE83F1549, 0xCB5BA48A, 0xD587CBCB,
        0x8D92C70C, 0x934EA84D, 0xB02A198E, 0xAEF676CF,
        0xF31A9B51, 0xEDC6F410, 0xCEA245D3, 0xD07E2A92,
        0x886B2655, 0x96B74914, 0xB5D3F8D7, 0xAB0F9796,
        0x05F9E159, 0x1B258E18, 0x38413FDB, 0x269D509A,
        0x7E885C5D, 0x6054331C, 0x433082DF, 0x5DECED9E,
        0xF8E959E3, 0xE63536A2, 0xC5518761, 0xDB8DE820,
        0x8398E4E7, 0x9D448BA6, 0xBE203A65, 0xA0FC5524,
        0x0E0A23EB, 0x10D64CAA, 0x33B2FD69, 0x2D6E9228,
        0x757B9EEF, 0x6BA7F1AE, 0x48C3406D, 0x561F2F2C,
        0x0BF3C2B2, 0x152FADF3, 0x364B1C30, 0x28977371,
        0x70827FB6, 0x6E5E10F7, 0x4D3AA134, 0x53E6CE75,
        0xFD10B8BA, 0xE3CCD7FB, 0xC0A86638, 0xDE740979,
        0x866105BE, 0x98BD6AFF, 0xBBD9DB3C, 0xA505B47D,
        0xEF0EDC87, 0xF1D2B3C6, 0xD2B60205, 0xCC6A6D44,
        0x947F6183, 0x8AA30EC2, 0xA9C7BF01, 0xB71BD040,
        0x19EDA68F, 0x0731C9CE, 0x2455780D, 0x3A89174C,
        0x629C1B8B, 0x7C4074CA, 0x5F24C509, 0x41F8AA48,
        0x1C1447D6, 0x02C82897, 0x21AC9954, 0x3F70F615,
        0x6765FAD2, 0x79B99593, 0x5ADD2450, 0x44014B11,
        0xEAF73DDE, 0xF42B529F, 0xD74FE35C, 0xC9938C1D,
        0x918680DA, 0x8F5AEF9B, 0xAC3E5E58, 0xB2E23119,
        0x17E78564, 0x093BEA25, 0x2A5F5BE6, 0x348334A7,
        0x6C963860, 0x724A5721, 0x512EE6E2, 0x4FF289A3,
        0xE104FF6C, 0xFFD8902D, 0xDCBC21EE, 0xC2604EAF,
        0x9A754268, 0x84A92D29, 0xA7CD9CEA, 0xB911F3AB,
        0xE4FD1E35, 0xFA217174, 0xD945C0B7, 0xC799AFF6,
        0x9F8CA331, 0x8150CC70, 0xA2347DB3, 0xBCE812F2,
        0x121E643D, 0x0CC20B7C, 0x2FA6BABF, 0x317AD5FE,
        0x696FD939, 0x77B3B678, 0x54D707BB, 0x4A0B68FA,
        0xC0C1D64F, 0xDE1DB90E, 0xFD7908CD, 0xE3A5678C,
        0xBBB06B4B, 0xA56C040A, 0x8608B5C9, 0x98D4DA88,
        0x3622AC47, 0x28FEC306, 0x0B9A72C5, 0x15461D84,
        0x4D531143, 0x538F7E02, 0x70EBCFC1, 0x6E37A080,
        0x33DB4D1E, 0x2D07225F, 0x0E63939C, 0x10BFFCDD,
        0x48AAF01A, 0x56769F5B, 0x75122E98, 0x6BCE41D9,
        0xC5383716, 0xDBE45857, 0xF880E994, 0xE65C86D5,
        0xBE498A12, 0xA095E553, 0x83F15490, 0x9D2D3BD1,
        0x38288FAC, 0x26F4E0ED, 0x0590512E, 0x1B4C3E6F,
        0x435932A8, 0x5D855DE9, 0x7EE1EC2A, 0x603D836B,
        0xCECBF5A4, 0xD0179AE5, 0xF3732B26, 0xEDAF4467,
        0xB5BA48A0, 0xAB6627E1, 0x88029622, 0x96DEF963,
        0xCB3214FD, 0xD5EE7BBC, 0xF68ACA7F, 0xE856A53E,
        0xB043A9F9, 0xAE9FC6B8, 0x8DFB777B, 0x9327183A,
        0x3DD16EF5, 0x230D01B4, 0x0069B077, 0x1EB5DF36,
        0x46A0D3F1, 0x587CBCB0, 0x7B180D73, 0x65C46232,
        0x2FCF0AC8, 0x31136589, 0x1277D44A, 0x0CABBB0B,
        0x54BEB7CC, 0x4A62D88D, 0x6906694E, 0x77DA060F,
        0xD92C70C0, 0xC7F01F81, 0xE494AE42, 0xFA48C103,
        0xA25DCDC4, 0xBC81A285, 0x9FE51346, 0x81397C07,
        0xDCD59199, 0xC209FED8, 0xE16D4F1B, 0xFFB1205A,
        0xA7A42C9D, 0xB97843DC, 0x9A1CF21F, 0x84C09D5E,
        0x2A36EB91, 0x34EA84D0, 0x178E3513, 0x09525A52,
        0x51475695, 0x4F9B39D4, 0x6CFF8817, 0x7223E756,
        0xD726532B, 0xC9FA3C6A, 0xEA9E8DA9, 0xF442E2E8,
        0xAC57EE2F, 0xB28B816E, 0x91EF30AD, 0x8F335FEC,
        0x21C52923, 0x3F194662, 0x1C7DF7A1, 0x02A198E0,
        0x5AB49427, 0x4468FB66, 0x670C4AA5, 0x79D025E4,
        0x243CC87A, 0x3AE0A73B, 0x198416F8, 0x075879B9,
        0x5F4D757E, 0x41911A3F, 0x62F5ABFC, 0x7C29C4BD,
        0xD2DFB272, 0xCC03DD33, 0xEF676CF0, 0xF1BB03B1,
        0xA9AE0F76, 0xB7726037, 0x9416D1F4, 0x8ACABEB5
    };

    public static void main(String[] args) {
        int data = 95279527; //0010 1001 1010
        byte[] result = new byte[4];
        ////大端排序
        result[0] = (byte)((data >> 24) & 0xFF);
        result[1] = (byte)((data >> 16) & 0xFF);
        result[2] = (byte)((data >> 8) & 0xFF);
        result[3] = (byte)(data & 0xFF);
        System.out.println("calCRC_32() Big-IOS =" + partialCrc(result));

        // 小端
        //result[3] = (byte)((data >> 24) & 0xFF);
        //result[2] = (byte)((data >> 16) & 0xFF);
        //result[1] = (byte)((data >> 8) & 0xFF);
        //result[0] = (byte)(data & 0xFF);
        int littleOrder = ByteBuffer.wrap(result).order(ByteOrder.LITTLE_ENDIAN).getInt();
        //System.out.println("calCRC_32() Small   =" + partialCrc(littleOrder));

        System.out.println("calCRC_32() BigJava =" + calQueryCRC_32());
    }

     /**
     * 计算666的CRC-32校验码
     * 根据工具，计算出来的是: E54A640A
     * 看能不能算出这个值。
     *
     * @return 校验码
     */
    private static long calCRC_32() {
        CRC32 crc = new CRC32();
        crc.update(666);
        return crc.getValue();
    }

    /**
     * 查表法实现CRC32校验码的计算
     * （1）将上次循环计算出的CRC和一个字节进行XOR 运算；
     * （2）用运算出的值和0xff进行按位与运算；
     * （3）将第二步运算出的值去查表；
     * （4）将上次循环计算出的CRC右移八位；
     * （5）将右移八位的CRC和第三步查找的结果进行XOR运算
     * （6）循环处理所有数据
     * （7）将得到的CRC再和0xFFFFFFFF进行XOR运算
     *
     * @return crc32校验值
     *
     * uint32  95279527  -->  0x 08920aa7  IOS算出来的
     *
     * 原生: 3268974039    网页：E54A640A --->  3846857738
     */
    public static int calQueryCRC_32() {
        int data = 95279527; //0010 1001 1010
        byte[] result = new byte[4];
        //大端排序
        result[0] = (byte)((data >> 24) & 0xFF);
        result[1] = (byte)((data >> 16) & 0xFF);
        result[2] = (byte)((data >> 8) & 0xFF);
        result[3] = (byte)(data & 0xFF);
        int checkNUm = 0xffffffff;
        int CRCIndex;
        for (byte b : result) {
            CRCIndex = (checkNUm ^ b) & 0xff;
            byte[] litteleByteOfTableValue = intToByteLittle(G_PUI32CRC32TABLE[CRCIndex]);
            checkNUm = (checkNUm >>> 8) ^ bytes2IntBig(litteleByteOfTableValue);
        }
         // flip bits
        checkNUm = ~checkNUm;
        return checkNUm;
    }


    /**
     * uint32  95279527  -->  0x08920aa7  IOS算出来的
     * @param pvData  8920AA7
     * @return 143788711
     */
    private static int partialCrc(byte[] pvData){
        int CRCIndex, i;
        int tempCRC = 0;
        for (i = 0; i <pvData.length; i++){
            CRCIndex = (pvData[i] ^ (tempCRC >> 24)) & 0xff;
            tempCRC = (tempCRC << 8) ^ G_PUI32CRC32TABLE[CRCIndex];
        }
        return tempCRC;
    }

    public static byte[] changeBytes(byte[] a) {
        byte[] b = new byte[a.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = a[b.length - i - 1];
        }
        return b;
    }

     /**
  * 将int转为低字节在前，高字节在后的byte数组（小端）
  * @param n int
  * @return byte[]
  */
    public static byte[] intToByteLittle(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

       /**
     * byte数组到int的转换(小端)
     * @param bytes
     * @return
     */
    public static int bytes2IntLittle(byte[] bytes )
    {
        int int1=bytes[0]&0xff;
        int int2=(bytes[1]&0xff)<<8;
        int int3=(bytes[2]&0xff)<<16;
        int int4=(bytes[3]&0xff)<<24;

        return int1|int2|int3|int4;
    }

      /**
     * byte数组到int的转换(大端)
     * @param bytes
     * @return
     */
    public static int bytes2IntBig(byte[] bytes )
    {
        int int1=bytes[3]&0xff;
        int int2=(bytes[2]&0xff)<<8;
        int int3=(bytes[1]&0xff)<<16;
        int int4=(bytes[0]&0xff)<<24;

        return int1|int2|int3|int4;
    }

       /**
     * 获取CRC-32校验码
     * 32位
     */
//    fun crc32(data: ByteArray): Int {
//        //crc32-InitCode
//        var checksum = 0xFFFFFFFF.toInt()
////        var checksum = 0
//        var tabIndex: Int
//        data.forEach { byte ->
//            //Swift代码 写法  但在, ‭0111 0000 1000 0000‬  会得到-1  异常
////            tabIndex = byte.toInt() xor checksum shr(24)
////            checksum = checksum.shl(8) xor (CRC32TableArry[tabIndex].toInt())
//
//            //查表法(大段?)
//            tabIndex = checksum.xor(byte.toInt()).and(0xFF)
//            val tabValue = CRC32TableArray[tabIndex]
//            checksum = tabValue xor checksum.shr(8)
//        }
//        checksum = checksum.xor(0xFFFFFFFF.toInt())
//        return checksum
//    }
}
