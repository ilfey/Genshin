package com.example.data.remotely.dictionary;

import com.example.data.remotely.Common;

import java.util.List;

public class DictionaryResponse {
    public String total;

    public Fields fields;

    public List<DictionaryEntry> entries;
}
class Fields{
    public Common.Field word;
    public Common.Field translate;
    public Common.Field subinf;
    public Common.Field original;
}