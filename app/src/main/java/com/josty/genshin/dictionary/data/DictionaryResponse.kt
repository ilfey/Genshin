package com.josty.genshin.dictionary.data

data class DictionaryResponse (
    val fields: Any,
    val entries: List<DictionaryEntity>,
    val total: Int
)