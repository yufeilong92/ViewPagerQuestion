package com.example.dell.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication
 * @Package com.example.dell.myapplication
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018/12/26 23:05
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class CardList  implements Serializable{
    private int type;
    private String content;
    private ArrayList<CardList> list;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<CardList> getList() {
        return list;
    }

    public void setList(ArrayList<CardList> list) {
        this.list = list;
    }
}
