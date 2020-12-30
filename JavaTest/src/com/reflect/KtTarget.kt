package com.reflect

class KtTarget{
    companion object{
        @JvmStatic
        fun registerStatic() {
            System.out.println("static register.")
        }
    }
    fun register(){
        System.out.println("hello")
    }

}