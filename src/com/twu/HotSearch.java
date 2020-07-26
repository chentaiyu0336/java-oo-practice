package com.twu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HotSearch {
    static ArrayList<HotSearchContent> hotSearchList = new ArrayList<>(); // 热搜榜单

    /*查看热搜榜单*/
    public void showHotSearch() {
        if(hotSearchList.isEmpty()) {
            System.out.println("暂无热搜榜单！无法查看！\n");
        }
        else {
            //Collections.sort(hotSreachList);
            hotSearchList.sort(new Comparator<HotSearchContent>() {
                @Override
                public int compare(HotSearchContent o1, HotSearchContent o2) { // 根据票数来排序（高->低）
                    return o2.vote_num-o1.vote_num;
                }
            });
            System.out.println("当前热搜排行榜：");
            for(int i=0;i<hotSearchList.size();i++) { // 输出热搜榜单
                int index=i+1; // 热搜序号
                System.out.println(index+" "+hotSearchList.get(i).item_name+" "+hotSearchList.get(i).vote_num+"票");
            }
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
                        System.out.println("已给超级热搜 "+voteName+" 投票成功！该超级热搜当前"+hs1.vote_num+"票\n");
                    }
                    else
                        System.out.println("已给 "+voteName+" 投票成功！该热搜当前"+hs1.vote_num+"票\n");
                    flg=false;
                }
            }
            if(flg) { // 被投票热搜不存在
                System.out.println("当前被投票热搜不存在！投票失败！\n");
            }
        }
    }

    /*购买热搜(undone)*/
    public void buy_Search(String nametobuy) {
        if(hotSearchList.isEmpty()) {
            System.out.println("暂无热搜榜单！无法购买热搜！\n");
        }
    }

}
