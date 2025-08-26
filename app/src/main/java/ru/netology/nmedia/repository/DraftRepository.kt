package ru.netology.nmedia.repository

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DraftRepository(context: Context) {
    private val prefs = context.getSharedPreferences("drafts", Context.MODE_PRIVATE)
    private val key = "newPostContent"

    private var draft = ""
    private val data = MutableLiveData(draft)

    init {
        draft = prefs.getString(key, "") ?: ""
        data.value = draft
    }

    fun get(): LiveData<String> = data

    fun save(content: String) {
        draft = content
        data.value = draft
        prefs.edit { putString(key, content) }
    }

    fun clear() {
        save("")
    }
}
