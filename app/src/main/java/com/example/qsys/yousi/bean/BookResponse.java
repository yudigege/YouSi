package com.example.qsys.yousi.bean;

import java.util.List;

/**
 * Created by hanshaokai on 2017/10/18 16:54
 */

public class BookResponse extends BaseResponse {


    /**
     * results : [{"id":36,"author":"dsa","content":"内容谁","nationality":"中国","dynasty":"唐朝","uuid":"null","keywords":"das","bookname":"西游记","updatetime":null,"createtime":null,"lifecourse":"生平","deathtime":"null","birthtime":"null"}]
     *
     */

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 36
         * author : dsa
         * content : 内容谁
         * nationality : 中国
         * dynasty : 唐朝
         * uuid : null
         * keywords : das
         * bookname : 西游记
         * updatetime : null
         * createtime : null
         * lifecourse : 生平
         * deathtime : null
         * birthtime : null
         */

        private int id;
        private String author;
        private String content;
        private String nationality;
        private String dynasty;
        private String uuid;
        private String keywords;
        private String bookname;
        private Long updatetime;
        private Long createtime;
        private String lifecourse;
        private String deathtime;
        private String birthtime;
        /*下载进度 不属于下载字段*/
        private Double percent = 0.0;

        private Boolean isVIsiblePercent=false;

        public Boolean getVIsiblePercent() {
            return isVIsiblePercent;
        }

        public void setVIsiblePercent(Boolean VIsiblePercent) {
            isVIsiblePercent = VIsiblePercent;
        }

        public Double getPercent() {
            return percent;
        }

        public void setPercent(Double percent) {
            this.percent = percent;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getDynasty() {
            return dynasty;
        }

        public void setDynasty(String dynasty) {
            this.dynasty = dynasty;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getBookname() {
            return bookname;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }

        public Long getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Long updatetime) {
            this.updatetime = updatetime;
        }

        public Long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Long createtime) {
            this.createtime = createtime;
        }

        public String getLifecourse() {
            return lifecourse;
        }

        public void setLifecourse(String lifecourse) {
            this.lifecourse = lifecourse;
        }

        public String getDeathtime() {
            return deathtime;
        }

        public void setDeathtime(String deathtime) {
            this.deathtime = deathtime;
        }

        public String getBirthtime() {
            return birthtime;
        }

        public void setBirthtime(String birthtime) {
            this.birthtime = birthtime;
        }
    }
}
