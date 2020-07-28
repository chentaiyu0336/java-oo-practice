package com.twu;

public class HotSearchContent {
    String item_name; // 热搜名
    int vote_num=0; // 该热搜得票数
    boolean isSuperClass=false; // 是否为超级热搜
    boolean isBought=false; // 是否被购买
    int paid=0; // 购买金额
    int paid_index=-1; // 购买位置索引
}
