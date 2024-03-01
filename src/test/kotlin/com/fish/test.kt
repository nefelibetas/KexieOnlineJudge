package com.fish

class A

fun main(args: Array<String>){
    val a  = A()
    val b = null
    a?.let{
        b?.let {
            println("hello")
            return
        }
        println("b is null")
        return
    }
    println("a is null")
}