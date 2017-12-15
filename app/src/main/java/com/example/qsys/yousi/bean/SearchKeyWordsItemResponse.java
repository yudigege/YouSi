package com.example.qsys.yousi.bean;

import java.util.List;

/**
 * @author hanshaokai
 * @date 2017/11/20 16:25
 */


public class SearchKeyWordsItemResponse extends BaseResponse {


    /**
     * results : [{"id":12,"key_words":"12","frequency":12,"uuid":"12","create_time":1510903805000,"update_time":1510903805000},{"id":17,"key_words":"搜索记录2","frequency":4,"uuid":"","create_time":1510908576000,"update_time":1510908576000},{"id":16,"key_words":"搜索记录1","frequency":3,"uuid":null,"create_time":1510889211000,"update_time":1510889211000},{"id":19,"key_words":"古","frequency":2,"uuid":"","create_time":1510916524000,"update_time":1510916524000},{"id":18,"key_words":"搜索","frequency":1,"uuid":"","create_time":1510916466000,"update_time":1510916466000}]
     * errors : null
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
         * id : 12
         * key_words : 12
         * frequency : 12
         * uuid : 12
         * create_time : 1510903805000
         * update_time : 1510903805000
         */

        private int id;
        private String key_words;
        private int frequency;
        private String uuid;
        private long create_time;
        private long update_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKey_words() {
            return key_words;
        }

        public void setKey_words(String key_words) {
            this.key_words = key_words;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
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
    }
}
