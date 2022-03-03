package com.example.data.remotely.gacha;

import com.example.data.remotely.Common;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GachaResponse {
    public Fields fields;
    public List<GachaEntry> entries;

}
class Fields{
    public Common.Field ver;
    public Common.Field name;
    public Common.Field img;
    public Common.Field ch5star;
    public Common.Field ch4star1;
    public Common.Field ch4star2;
    public Common.Field ch4star3;
    @SerializedName("double")
    public Common.Field dbl;
}