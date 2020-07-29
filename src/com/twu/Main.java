package com.twu;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean mainMenuFlg=true; // 主菜单界面状态标志位
        boolean userMenuFlg=true; // 用户界面状态标志位
        boolean adminMenuFlgMenuFlg =true; // 管理员界面状态标志位
        HotSearch hot_search=new HotSearch(); // 实例化一个热搜对象
        int selectNum=0;
        //String codeNum="admin123"; // 管理员密码
        Scanner input=new Scanner(System.in);
        // 进入主界面
        while(mainMenuFlg) {
            System.out.println("欢迎来到热搜排行榜，你是？\n1. 用户\n2. 管理员\n3. 退出");
            selectNum=input.nextInt(); // 选择操作序号
            if(selectNum>3||selectNum<1) {
                System.out.println("Warning：输入无效，请输入0~3序号进行选择！\n");
                continue;
            }
            switch (selectNum) {
                case 1: // 选择用户
                    userMenuFlg=true;
                    System.out.println("请输入你的昵称：");
                    String name1=input.next();
                    User user=new User();  // 实例化一个用户对象
                    while (userMenuFlg) {
                        System.out.println("你好，用户"+name1+"，你可以选择：\n1. 查看热搜排行榜\n2. 给热搜事件投票" +
                                "\n3. 购买热搜\n4. 添加热搜\n5. 退出");
                        selectNum=input.nextInt(); // 选择操作序号
                        if(selectNum>5||selectNum<1) {
                            System.out.println("Warning：输入无效，请输入0~5序号进行选择！\n");
                            continue;
                        }
                        switch (selectNum) {
                            case 1:  // 查看热搜榜
                                hot_search.showHotSearch();
                                break;
                            case 2:  // 给热搜投票
                                if(hot_search.hotSearchList.isEmpty()) {
                                    System.out.println("Warning：暂无热搜榜单！无法投票！\n");
                                    break;
                                }
                                if(user.votNum==0) {
                                    System.out.println("Warning：你的票数已用完！无法投票！\n");
                                    break;
                                }
                                System.out.println("请输入你要投票的热搜名：");
                                String str_name=input.next();
                                /*boolean non_exit_flg=true; // 检验被投票热搜是否存在的标志位
                                for(HotSearchContent hs:hot_search.hotSearchList) {
                                    if(str_name.equals(hs.item_name)) // 在热搜列表中匹配该热搜名
                                        non_exit_flg=false;
                                }
                                if(non_exit_flg) { // 投票热搜不存在
                                    System.out.println("该热搜不在榜单中，投票失败！\n");
                                    break;
                                }*/
                                if(checkHotSearchNonExistence(str_name,hot_search.hotSearchList)) { // 检验该热搜是否存在
                                    System.out.println("Warning：该热搜不在榜单中，投票失败！\n");
                                    break;
                                }
                                System.out.println("请输入你要投的票数：(当前还有"+user.votNum+"票)");
                                int count1=input.nextInt();
                                if(count1>0&&count1<=user.votNum) {
                                    hot_search.vote_up(str_name,count1);
                                    user.votNum-=count1;
                                }
                                else
                                    System.out.println("Warning：投票无效！请在剩余票数范围内投票！\n");
                                break;
                            case 3:  // 购买热搜
                                if(hot_search.hotSearchList.isEmpty()) {
                                    System.out.println("Warning：暂无热搜榜单！无法购买！\n");
                                    break;
                                }
                                System.out.println("请输入你要购买的热搜名：");
                                String name_buy=input.next();
                                if(checkHotSearchNonExistence(name_buy,hot_search.hotSearchList)) { // 检验该热搜是否存
                                    System.out.println("Warning：该热搜不存在！无法购买！\n");
                                    break;
                                }
                                System.out.println("请输入拟购买的热搜位置：");
                                int pos=input.nextInt();  // 拟购买位置
                                System.out.println("请输入拟购买的金额：");
                                int money=input.nextInt();  // 拟购买金额
                                hot_search.buy_Search(name_buy,pos,money); // 购买热搜
                                break;
                            case 4:  // 添加热搜
                                System.out.println("请输入你要添加的热搜名称：");
                                String str=input.next();
                                hot_search.addHotSearch(str);
                                break;
                            case 5:
                                userMenuFlg=false; // 关闭用户选择界面
                                break;
                        }
                    }
                    break;
                case 2: // 选择管理员
                    adminMenuFlgMenuFlg =true;
                    System.out.println("请输入你的昵称：");
                    String name2=input.next();
                    System.out.println("请输入管理员密码：");
                    String code1=input.next();
                    Administrators admin=new Administrators(); // 实例化管理员对象
                    if(admin.testifyCodeNum(name2,code1)) { // 验证管理员身份及密码：身份密码// 正确
                        while (adminMenuFlgMenuFlg) {
                            System.out.println("你好，管理员"+name2+"，你可以选择：\n1. 查看热搜排行榜\n2. 添加热搜" +
                                    "\n3. 添加超级热搜\n4. 退出");
                            selectNum=input.nextInt(); // 选择操作序号
                            if(selectNum>4||selectNum<1) {
                                System.out.println("Warning：输入无效，请输入0~4序号进行选择！\n");
                                continue;
                            }
                            switch (selectNum) {
                                case 1:  // 查看热搜榜
                                    hot_search.showHotSearch();
                                    break;
                                case 2:  // 添加热搜
                                    System.out.println("请输入你要添加的热搜名称：");
                                    String str=input.next();
                                    hot_search.addHotSearch(str);
                                    break;
                                case 3:  // 添加超级热搜
                                    System.out.println("请输入你要添加的超级热搜名称：");
                                    String super_str=input.next();
                                    hot_search.addHotSearch_Super(super_str);
                                    break;
                                case 4:
                                    adminMenuFlgMenuFlg =false; // 关闭管理员选择界面
                                    break;
                            }
                        }
                    }
                    else { // 身份密码验证失败
                        System.out.println("Warning：身份密码验证失败！您的权限被拒绝！\n");
                    }
                    adminMenuFlgMenuFlg =false; // 关闭管理员选择界面
                    break;
                case 3: // 退出主界面
                    mainMenuFlg=false;
                    System.out.println("已退出！");
                    break;
            }

        }
    }

    /*检验热搜在榜单中是否存在（不存在返回true）*/
    public static boolean checkHotSearchNonExistence(String checkname, ArrayList<HotSearchContent> hotSearchList) {
        boolean non_exit_flg=true; // 检验被投票热搜是否存在的标志位
        for(HotSearchContent hs:hotSearchList) {
            if(checkname.equals(hs.item_name)) // 在热搜列表中匹配该热搜名
                non_exit_flg=false;
        }
        return non_exit_flg;
    }

}
