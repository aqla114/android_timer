package com.example.timer.repositories

interface DataHolderRepository<T> {
    fun push(value: T)
}