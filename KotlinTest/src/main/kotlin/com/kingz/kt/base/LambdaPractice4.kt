import com.kingz.kt.base.Observer
import com.kingz.kt.base.Target

fun lambdaOf1_4(){
    Target<Int>().observe(3, Observer {

    })
}