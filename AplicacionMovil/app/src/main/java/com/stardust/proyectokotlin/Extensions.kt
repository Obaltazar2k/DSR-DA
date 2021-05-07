package com.stardust.proyectokotlin

fun String.isAnEmail(): Boolean {
    if(this.isNotEmpty()) {
        val regex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}".toRegex()
        return matches(regex)
    }
    return false
}