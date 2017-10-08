package com.samatya.yusuf.models;

/**
 * Created by yusuf on 5.10.2017.
 */

public class Notes  {
    int note_id;
    int note_user_id;
    String title;
    String content;

    public Notes() {
    }

    public Notes(int note_id, int note_user_id, String title, String content) {
        this.note_id = note_id;
        this.note_user_id = note_user_id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return note_id;
    }

    public void setId(int note_id) {
        this.note_id = note_id;
    }

    public int getNote_user_id() {
        return note_user_id;
    }

    public void setNote_user_id(int note_user_id) {
        this.note_user_id = note_user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




}
