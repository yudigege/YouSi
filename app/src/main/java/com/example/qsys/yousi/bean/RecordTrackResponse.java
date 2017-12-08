package com.example.qsys.yousi.bean;

/**
 * @author hanshaokai
 * @date 2017/12/8 15:42
 */


public class RecordTrackResponse extends BaseResponse {


    /**
     * results : {"id":2,"user_id":50,"read_item_number":0,"daily_item_number":1,"read_word_number":0,"daily_word_number":3,"read_record_time_count":0,"daily_record_time_count":1391750,"continue_days":1,"uuid":"null","create_time":1512632393000,"update_time":1512632393000,"continue_date_time":1512632391312}
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
         * id : 2
         * user_id : 50
         * read_item_number : 0
         * daily_item_number : 1
         * read_word_number : 0
         * daily_word_number : 3
         * read_record_time_count : 0
         * daily_record_time_count : 1391750
         * continue_days : 1
         * uuid : null
         * create_time : 1512632393000
         * update_time : 1512632393000
         * continue_date_time : 1512632391312
         */

        private int id;
        private int user_id;
        private int read_item_number;
        private int daily_item_number;
        private int read_word_number;
        private int daily_word_number;
        private int read_record_time_count;
        private int daily_record_time_count;
        private int continue_days;
        private String uuid;
        private long create_time;
        private long update_time;
        private long continue_date_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getRead_item_number() {
            return read_item_number;
        }

        public void setRead_item_number(int read_item_number) {
            this.read_item_number = read_item_number;
        }

        public int getDaily_item_number() {
            return daily_item_number;
        }

        public void setDaily_item_number(int daily_item_number) {
            this.daily_item_number = daily_item_number;
        }

        public int getRead_word_number() {
            return read_word_number;
        }

        public void setRead_word_number(int read_word_number) {
            this.read_word_number = read_word_number;
        }

        public int getDaily_word_number() {
            return daily_word_number;
        }

        public void setDaily_word_number(int daily_word_number) {
            this.daily_word_number = daily_word_number;
        }

        public int getRead_record_time_count() {
            return read_record_time_count;
        }

        public void setRead_record_time_count(int read_record_time_count) {
            this.read_record_time_count = read_record_time_count;
        }

        public int getDaily_record_time_count() {
            return daily_record_time_count;
        }

        public void setDaily_record_time_count(int daily_record_time_count) {
            this.daily_record_time_count = daily_record_time_count;
        }

        public int getContinue_days() {
            return continue_days;
        }

        public void setContinue_days(int continue_days) {
            this.continue_days = continue_days;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
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

        public long getContinue_date_time() {
            return continue_date_time;
        }

        public void setContinue_date_time(long continue_date_time) {
            this.continue_date_time = continue_date_time;
        }
    }
}
