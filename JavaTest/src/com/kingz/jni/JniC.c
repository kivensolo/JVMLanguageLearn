#include <JniDemo.h>
#include <string.h>

int num = 0;
String str = "hello jni!"
JNIEXPORT jint JNICALL Java_com_kingz_jni_JniDemo_getNum(JNIEnv *env, jclass obj)
{
   return num;
}
JNIEXPORT void JNICALL Java_com_kingz_jni_JniDemo_setNum(JNIEnv *env, jclass obj, jint numOut)
{
  num = numOut;
}
JNIEXPORT void JNICALL Java_com_kingz_jni_JniDemo_setStr(JNIEnv *env, jclass obj, jstring strOut)
{
    //(*env)->GetStringUTFChars()这个方法, 是用来在Java和C之间转换字符串的,
    //因为Java本身都使用了双字节的字符, 而C语言本身都是单字节的字符, 所以需要进行转换.
    const char* str = (*env)->GetStringUTFChars(env, strOut, 0); //UTF8转换为C的编码格式
    char cap[128];
    strcpy(cap, str);
     //用来释放对象的, 在Java中有虚拟机进行垃圾回收, 但是在C语言中, 这些对象必须手动回收
    (*env)->ReleaseStringUTFChars(env, strOut, 0);
    str = (*env)->NewStringUTF(env, strupr(cap)); //根据C的字符串返回一个UTF8字符串
}

JNIEXPORT jstring JNICALL Java_com_kingz_jni_JniDemo_getStr(JNIEnv *env, jclass obj)
{
    return str;
}
