package com.example.data.remote;

import java.util.List;

public class DictionaryResponse {
    String total;

    class fields{
        class word extends Common.Field {}
        class translate extends Common.Field{}
        class subinf extends Common.Field{}
        class original extends Common.Field{}
    }
    class entries{
        List<DictionaryEntry> entries;
        entries(List<DictionaryEntry> entries){
            this.entries = entries;
        }
    }
}

class DictionaryEntry{
    String word;
    String translate;
    String subinf;
    String original;
    String _mby;
    String _by;
    int _modified;
    int _created;
    String _id;
}
