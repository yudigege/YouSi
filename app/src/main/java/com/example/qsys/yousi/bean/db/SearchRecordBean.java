package com.example.qsys.yousi.bean.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author hanshaokai
 * @date 2017/11/22 14:35
 */

@Entity
public class SearchRecordBean {
    @Id
    private Long id;
    @Unique
    private String keyWords;
    private String creatTime;
    @Generated(hash = 2097314889)
    public SearchRecordBean(Long id, String keyWords, String creatTime) {
        this.id = id;
        this.keyWords = keyWords;
        this.creatTime = creatTime;
    }
    @Generated(hash = 1260263942)
    public SearchRecordBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKeyWords() {
        return this.keyWords;
    }
    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
    public String getCreatTime() {
        return this.creatTime;
    }
    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }
   
}
