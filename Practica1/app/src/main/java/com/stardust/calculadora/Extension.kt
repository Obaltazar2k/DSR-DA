package com.stardust.calculadora

fun String.isAnOperador(): Boolean {
    if(this.isNotEmpty()) {
        if(this.equals("+")||this.equals("-")||this.equals("/")||this.equals("X")||this.equals("=")) {
            return true
        }
    }
    return false
}