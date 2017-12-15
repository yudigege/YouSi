package com.example.qsys.yousi.bean;

/**
 * Created by hanshaokai on 2017/10/12 16:39
 */

public class UserResponse extends BaseResponse{


    /**
     * results : {"id":50,"gender":1,"mobile":"12345678","email":"邮编","real_name":"真是名字","nick_name":"sdfdsdfad","avatar":"头像","job":"it","company":"公司","bio":"个人简介","blog":"博客地址","github":"wqeqwe","create_time":1506593273000,"update_time":1506593273000}
     * errors : null
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
         * real_name : 真是名字
         * nick_name : sdfdsdfad
         * avatar : 头像
         * job : it
         * company : 公司
         * bio : 个人简介
         * blog : 博客地址
         * github : wqeqwe
         * create_time : 1506593273000
         * update_time : 1506593273000
         */

        private int id;
        private int gender;
        private String mobile;
        private String email;
        private String real_name;
        private String nick_name;
        private String avatar;
        private String job;
        private String company;
        private String bio;
        private String blog;
        private String github;
        private long create_time;
        private long update_time;

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

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
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

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public long getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }
    }
}
