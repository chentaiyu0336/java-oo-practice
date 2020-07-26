package com.twu;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean mainMenuFlg=true; // 主菜单界面状态标志位
        boolean userMenuFlg=true; // 用户界面状态标志位
        boolean admiMenuFlg=true; // 管理员界面状态标志位
        int selectNum=0;
        String codeNum="admin123"; // 管理员密码
        Scanner input=new Scanner(System.in);
        // 进入主界面
        while(mainMenuFlg) {
            System.out.println("欢迎来到热搜排行榜，你是？\n1. 用户\n2. 管理员\n3. 退出");
            selectNum=input.nextInt();
            if(selectNum>3||selectNum<1) {
                System.out.println("输入无效，请输入0~3序号进行选择！\n");
                continue;
            }
            switch (selectNum) {
                case 1: // 选择用户
                    userMenuFlg=true;
                    System.out.println("请输入你的昵称：");
                    String name1=input.next();
                    while (userMenuFlg) {
                        System.out.println("你好，用户"+name1+"，你可以选择：\n1. 查看热搜排行榜\n2. 给热搜事件投票" +
                                "\n3. 购买热搜\n4. 添加热搜\n5. 退出");
                        selectNum=input.nextInt();
                        if(selectNum>5||selectNum<1) {
                            System.out.println("输入无效，请输入0~5序号进行选择！\n");
                            continue;
                        }
                        switch (selectNum) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                userMenuFlg=false; // 关闭用户选择界面
                                break;
                        }
                    }
                    break;
                case 2: // 选择管理员
                    admiMenuFlg=true;
                    System.out.println("请输入你的昵称：");
                    String name2=input.next();
                    System.out.println("请输入管理员密码：");
                    String code1=input.next();
                    if(code1.equals(codeNum)) { // 验证管理员密码：密码// 正确
                        while (admiMenuFlg) {
                            System.out.println("你好，管理员"+name2+"，你可以选择：\n1. 查看热搜排行榜\n2. 添加热搜" +
                                    "\n3. 添加超级热搜\n4. 退出");
                            selectNum=input.nextInt();
                            if(selectNum>4||selectNum<1) {
                                System.out.println("输入无效，请输入0~4序号进行选择！\n");
                                continue;
                            }
                            switch (selectNum) {
                                case 1:
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    admiMenuFlg=false; // 关闭管理员选择界面
                                    break;
                            }
                        }
                    }
                    else { // 密码验证失败
                        System.out.println("密码验证失败！您的权限被拒绝！\n");
                    }
                    admiMenuFlg=false; // 关闭管理员选择界面
                    break;
                case 3:
                    mainMenuFlg=false;
                    System.out.println("已退出！");
                    break;
            }

        }
    }

}
