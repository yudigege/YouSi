package com.example.qsys.yousi.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author hanshaokai
 * @date 2017/10/24
 */


public class DaysResportResponse extends BaseResponse {


    /**
     * results : [{"id":16,"type":1,"content":"fds","title":"sfd","uuid":null,"createtime":null,"updatetime":null,"bookname":"sfd","keywords":"23","userid":11,"bgcolor":"#00000000","bgimgUrl":null,"txtsize":13},{"id":17,"type":0,"content":"dsf","title":null,"uuid":null,"createtime":null,"updatetime":null,"bookname":"sdf","keywords":"fds","userid":11,"bgcolor":"dfs","bgimgUrl":null,"txtsize":13},{"id":18,"type":1,"content":"jkjk","title":null,"uuid":null,"createtime":null,"updatetime":null,"bookname":"knl","keywords":"ihhl","userid":11,"bgcolor":"#00000000","bgimgUrl":null,"txtsize":13}]
     * errors : null
     */

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Parcelable {
        /**
         * id : 16
         * type : 1
         * content : fds
         * title : sfd
         * uuid : null
         * createtime : null
         * updatetime : null
         * bookname : sfd
         * keywords : 23
         * userid : 11
         * bgcolor : #00000000
         * bgimgUrl : null
         * txtsize : 13
         */

        private int id;
        private int type;
        private String content;
        private String title;
        private String uuid;
        private Long createtime;
        private Long updatetime;
        private String bookname;
        private String keywords;
        private int userid;
        private String bgcolor;
        private String bgimgUrl;
        private int txtsize;
        private long record_consume_start_time;
        private long record_consume_end_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public Long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Long createtime) {
            this.createtime = createtime;
        }

        public Long getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Long updatetime) {
            this.updatetime = updatetime;
        }

        public String getBookname() {
            return bookname;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getBgcolor() {
            return bgcolor;
        }

        public void setBgcolor(String bgcolor) {
            this.bgcolor = bgcolor;
        }

        public String getBgimgUrl() {
            return bgimgUrl;
        }

        public void setBgimgUrl(String bgimgUrl) {
            this.bgimgUrl = bgimgUrl;
        }

        public int getTxtsize() {
            return txtsize;
        }

        public void setTxtsize(int txtsize) {
            this.txtsize = txtsize;
        }

        public long getRecord_consume_start_time() {
            return record_consume_start_time;
        }

        public void setRecord_consume_start_time(long record_consume_start_time) {
            this.record_consume_start_time = record_consume_start_time;
        }

        public long getRecord_consume_end_time() {
            return record_consume_end_time;
        }

        public void setRecord_consume_end_time(long record_consume_end_time) {
            this.record_consume_end_time = record_consume_end_time;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.type);
            dest.writeString(this.content);
            dest.writeString(this.title);
            dest.writeString(this.uuid);
            dest.writeValue(this.createtime);
            dest.writeValue(this.updatetime);
            dest.writeString(this.bookname);
            dest.writeString(this.keywords);
            dest.writeInt(this.userid);
            dest.writeString(this.bgcolor);
            dest.writeString(this.bgimgUrl);
            dest.writeInt(this.txtsize);
            dest.writeLong(this.record_consume_start_time);
            dest.writeLong(this.record_consume_end_time);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this.id = in.readInt();
            this.type = in.readInt();
            this.content = in.readString();
            this.title = in.readString();
            this.uuid = in.readString();
            this.createtime = (Long) in.readValue(Long.class.getClassLoader());
            this.updatetime = (Long) in.readValue(Long.class.getClassLoader());
            this.bookname = in.readString();
            this.keywords = in.readString();
            this.userid = in.readInt();
            this.bgcolor = in.readString();
            this.bgimgUrl = in.readString();
            this.txtsize = in.readInt();
            this.record_consume_start_time = in.readLong();
            this.record_consume_end_time = in.readLong();
        }

        public static final Parcelable.Creator<ResultsBean> CREATOR = new Parcelable.Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }

}
