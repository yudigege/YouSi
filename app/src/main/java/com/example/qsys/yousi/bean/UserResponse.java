package com.example.qsys.yousi.bean;

/**
 * Created by hanshaokai on 2017/10/12 16:39
 */

public class UserResponse extends BaseResponse{


    /**
     * results : {"id":50,"gender":1,"mobile":"12345678","email":"邮编","avatar":"头像","job":"it","company":"公司","bio":"个人简介","blog":"博客地址","github":"wqeqwe","nickName":"都是","createTime":null,"updateTime":null,"realName":"真是名字"}
     */

    private ResultsBean results;

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 50
         * gender : 1
         * mobile : 12345678
         * email : 邮编
         * avatar : 头像
         * job : it
         * company : 公司
         * bio : 个人简介
         * blog : 博客地址
         * github : wqeqwe
         * nickName : 都是
         * createTime : null
         * updateTime : null
         * realName : 真是名字
         */

        private int id;
        private int gender;
        private String mobile;
        private String email;
        private String avatar;
        private String job;
        private String company;
        private String bio;
        private String blog;
        private String github;
        private String nickName;
        private Object createTime;
        private Object updateTime;
        private String realName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getBlog() {
            return blog;
        }

        public void setBlog(String blog) {
            this.blog = blog;
        }

        public String getGithub() {
            return github;
        }

        public void setGithub(String github) {
            this.github = github;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }
    }
}
