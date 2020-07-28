package com.twu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HotSearch {
    static ArrayList<HotSearchContent> hotSearchList = new ArrayList<>(); // 热搜榜单
    static ArrayList<HotSearchContent> boughtList = new ArrayList<>(); // 被购买的热搜集合

    /*热搜榜排序*/
    public static void sort_List() {
        ArrayList<HotSearchContent> hotList_tmp=new ArrayList<>(); // 未被购买的热搜榜单
        for(HotSearchContent hs:hotSearchList) {
            if(hs.isBought) // 已被购买
                continue;
            hotList_tmp.add(hs);
        }
        hotList_tmp.sort(new Comparator<HotSearchContent>() {
            @Override
            public int compare(HotSearchContent o1, HotSearchContent o2) { // 根据票数来排序（高->低）
                return o2.vote_num-o1.vote_num;
            }
        });
        for(HotSearchContent hs:boughtList) {
            if(hs.paid_index>hotList_tmp.size()){ // 购买位置超过索引范围
                hs.paid_index=hotList_tmp.size();
            }
            hotList_tmp.add(hs.paid_index,hs);
        }
        hotSearchList=hotList_tmp;
    }

    /*被购榜排序*/
    public static void sort_boughtList() {
        boughtList.sort(new Comparator<HotSearchContent>() {
            @Override
            public int compare(HotSearchContent o1, HotSearchContent o2) { // 根据被购位置排序 (前->后)
                return o1.paid_index-o2.paid_index;
            }
        });
    }

    /*查看热搜榜单*/
    public void showHotSearch() {
        if(hotSearchList.isEmpty()) {
            System.out.println("暂无热搜榜单！无法查看！\n");
        }
        else {
            /*
            hotSearchList.sort(new Comparator<HotSearchContent>() {
                @Override
                public int compare(HotSearchContent o1, HotSearchContent o2) { // 根据票数来排序（高->低）
                    return o2.vote_num-o1.vote_num;
                }
            });
            */
            sort_List(); // 排序
            System.out.println("当前热搜榜：");
            for(int i=0;i<hotSearchList.size();i++) { // 输出热搜榜单
                int index=i+1; // 热搜序号
                System.out.println(index+" "+hotSearchList.get(i).item_name+" "+hotSearchList.get(i).vote_num+"票");
            }
            System.out.println("~~~~~~~~~~~");
        }
    }

    /*添加热搜*/
    public void addHotSearch(String searchName1) {
        boolean flg=true; // 判断热搜是否已经存在标志位，为false表示已存在
        for(HotSearchContent hs1:hotSearchList) {
            if(searchName1.equals(hs1.item_name)) {
                System.out.println("该热搜已存在！\n");
                flg=false;
                break;
            }
        }
        if(flg) { // 新热搜
            HotSearchContent hsc=new HotSearchContent();
            hsc.item_name=searchName1;
            hotSearchList.add(hsc);
            sort_List(); // 排序
            System.out.println("当前热搜添加成功！\n");
        }
    }

    /*添加超级热搜*/
    public void addHotSearch_Super(String searchName2) {
        boolean flg=true; // 判断超级热搜是否已经存在标志位，为false表示已存在
        for(HotSearchContent hs1:hotSearchList) {
            if(searchName2.equals(hs1.item_name)) {
                System.out.println("该超级热搜已存在！\n");
                flg=false;
                break;
            }
        }
        if(flg) { // 新超级热搜
            HotSearchContent hsc=new HotSearchContent();
            hsc.item_name=searchName2;
            hsc.isSuperClass=true;
            hotSearchList.add(hsc);
            sort_List(); // 排序
            System.out.println("当前超级热搜添加成功！\n");
        }
    }

    /*给热搜投票*/
    public void vote_up(String voteName, int voteNum) {
        boolean flg=true; // 判断热搜是否存在，true为不存在
        if(hotSearchList.isEmpty()) {
            System.out.println("暂无热搜榜单！无法投票！\n");
        }
        else {
            for(HotSearchContent hs1:hotSearchList) {
                if(voteName.equals(hs1.item_name)) {
                    hs1.vote_num+=voteNum;
                    if(hs1.isSuperClass) { // 超级热搜，票数多投一倍
                        hs1.vote_num+=voteNum;
                        System.out.println("已给超级热搜《"+voteName+"》投票成功！该超级热搜当前"+hs1.vote_num+"票\n");
                    }
                    else
                        System.out.println("已给《"+voteName+"》投票成功！该热搜当前"+hs1.vote_num+"票\n");
                    sort_List(); // 排序
                    flg=false;
                }
            }
            if(flg) { // 被投票热搜不存在
                System.out.println("当前被投票热搜不存在！投票失败！\n");
            }
        }
    }

    /*购买热搜*/
    public void buy_Search(String name_buy,int pos,int money) {
        if(hotSearchList.isEmpty()) {
            System.out.println("暂无热搜榜单！无法购买热搜！\n");
        }
        HotSearchContent hsc_tmp=new HotSearchContent();
        for(HotSearchContent hs:hotSearchList) {
            if(name_buy.equals(hs.item_name)) {
                hsc_tmp=hs; // 备份被购买热搜对象
                break;
            }
        }
        if(pos>=(hotSearchList.size())) { // 购买位置大于热搜数目，直接放在最后
            hotSearchList.remove(hsc_tmp);
            if(hsc_tmp.isBought) {
                boughtList.remove(hsc_tmp); // 如原先已被购买，怎在购买列表里删除原先的对象，用新的替代
            }
            hsc_tmp.isBought=true; // 购买成功
            hsc_tmp.paid=money;
            hsc_tmp.paid_index=hotSearchList.size();
            hotSearchList.add(hsc_tmp.paid_index,hsc_tmp);
            boughtList.add(hsc_tmp); // 添加入购买集合
            sort_boughtList();
            System.out.println("你的热搜已购买成功！《"+hsc_tmp.item_name+"》已位于热搜第"+hotSearchList.size()+"位！\n");
        }
        else {
            boolean can_buy_flg=true; // 能否购买标志位
            HotSearchContent hsc2=hotSearchList.get(pos-1); // 购买位置的原热搜
            if(hsc2.isBought) { // 该位置原热搜也被购买
                if(money<=hsc2.paid) {
                    System.out.println("购买金额不足！购买失败！\n");
                    can_buy_flg=false;
                }
            }
            if(can_buy_flg) {
                hotSearchList.remove(hsc_tmp);
                if(hsc_tmp.isBought) {
                    boughtList.remove(hsc_tmp); // 如被购对象原先已被购买，则在购买列表里删除原先的对象，用新的替代
                }
                hsc_tmp.isBought=true;
                hsc_tmp.paid=money;
                hsc_tmp.paid_index=pos-1;
                if(hsc2.isBought) {
                    hotSearchList.set(pos-1,hsc_tmp);  // 替换同样被买的该位置的原热搜
                    boughtList.remove(hsc2);
                }
                else {
                    hotSearchList.add(pos-1,hsc_tmp); // 将原位置未被购买的热搜依次往后挤
                }
                boughtList.add(hsc_tmp); // 添加入购买集合
                sort_boughtList(); // 给购买集合排序
                System.out.println("你的热搜已购买成功！《"+hsc_tmp.item_name+"》已位于热搜第"+pos+"位！\n");
            }
        }
    }

}
