package com.kingz.kt.generic

/**

 * @author: King.Z
 * @since: 2020/4/29 22:16 <br>
 * @desc: Kotlin泛型使用研究
 */
class ProblemOfGeneric {
    fun onDataResult(data:MutableList<Result>){}

    inner class Response:ICallback{

        override fun <T> getData(t: T) {
//            if(t is MutableList<Result>){
                // 如果直接写 t is MutableList<Result>， 则无法检查已擦除类型的实例:MutableList<Result>
                // 因为运行时，类型被擦除，所以jvm只知道是一个MutableList，却不知道具体类型是什么,
                // 所以此处无法判断是一个Result对象的列表，“只知道是列表”
//            }

            // 为什么不用 <Object>
            // 对于java来说, 泛型中的<Object>并不是像以前那样有继承关系的，
            // 也就是说List<Object>和List<String>是毫无关系的！！！！
            // 所以kotlin中也不能用<Any>
//            if(t is MutableList<Any>){
//            }

            /**
             * 不清楚List集合装载的元素是什么类型的，
             * List<Objcet>这样是行不通的........那怎么办？？
             * 于是Java泛型提供了类型通配符 ?
             * Kotlin提供了  * 星号投影语句
             */
            if(t is MutableList<*>){
                // 根据业务，我知道在t属于MutableList的时候，集合中的数据类型则一定是Result数据类型，
                // 则可以直接强转
                @Suppress("UNCHECKED_CAST")
                this@ProblemOfGeneric.onDataResult(t as MutableList<Result>)
            }
        }
    }
}
//            if(t is MutableList<*>){
//                this@ProblemOfGeneric.onDataResult(t)
//            }

data class Result(var result:String)

interface ICallback{
    //  类型参数声明
    fun   <T>   getData(t:T)
}