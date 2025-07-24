package ru.netology.nmedia.util

object NumberFormatter {
    fun format(count: Int): String {
        return when {
            count < 1_000 -> count.toString()
            count < 10_000 -> "${(count / 100) / 10.0}K".replace(".0K", "K")
            count < 1_000_000 -> "${count / 1_000}K"
            else -> "${(count / 100_000) / 10.0}M".replace(".0M", "M")
        }
    }
}