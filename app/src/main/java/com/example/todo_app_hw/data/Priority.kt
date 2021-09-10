package com.example.todo_app_hw.data

enum class Priority {
    HIGH {
        override fun index(): Int = 0
    },
    MEDIUM {
        override fun index(): Int = 1
    },
    LOW {
        override fun index(): Int = 2
    };

    abstract fun index(): Int
}