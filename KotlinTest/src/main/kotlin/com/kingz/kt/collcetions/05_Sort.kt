package com.kingz.kt.collcetions

/**

 * @author: King.Z
 * @since: 2020/7/4 17:00 <br>
 * @desc:
 * 编写一个扩展函数，可实现：
 *  返回以消费者订单(Order)数量为排行的消费者List
 */
fun Shop.getCustomersSortedByNumberOfOrders():List<Customer>{
    // 对 List<Customer> 进行排序，排序规则是orders的size.
    return customers.sortedBy {
        it.orders.size
    }
}

