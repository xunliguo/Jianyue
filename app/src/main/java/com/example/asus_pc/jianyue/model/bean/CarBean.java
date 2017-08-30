package com.example.asus_pc.jianyue.model.bean;

import java.util.List;

/**
 * Created by ASUS-PC on 2017/8/25.
 */

public class CarBean {



    private String listId;
    private String type;
    private int expiredTime;
    private int currentPage;
    private int totalPage;
    private int topsize;
    private List<ItemBean> item;

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(int expiredTime) {
        this.expiredTime = expiredTime;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTopsize() {
        return topsize;
    }

    public void setTopsize(int topsize) {
        this.topsize = topsize;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ItemBean {
        /**
         * type : text_live
         * thumbnail : http://d.ifengimg.com/w198_h141_q100/p3.ifengimg.com/a/2017_34/cf3ed187154078c_size158_w750_h340.jpg
         * online : 0
         * title : 为了美食和美女，我们拼了！成都车展任务
         * source : 凤凰汽车
         * subscribe : {"cateid":"凤凰汽车","type":"source","catename":"凤凰汽车","description":""}
         * id : 108991
         * documentId : imcp_crc_2373949021
         * staticId : piclive_108991
         * style : {"view":"longimg","backreason":["不感兴趣","不想看:凤凰汽车","内容质量差","旧闻、重复","标题党"]}
         * styleType : live
         * liveExt : {"startStamp":"1503626400","endStamp":"1503651600","startTime":"2017-08-25 10:00:00","status":"2","hasVideo":true,"hasVr":false}
         * link : {"type":"text_live","url":"108991","weburl":"http://liveshare.iclient.ifeng.com/live_share?liveId=108991&vt=5"}
         * reftype : editor
         * showType : 0
         * updateTime : 2017/08/25 14:17:04
         * commentsUrl : sub_27324563
         * comments : 0
         * commentsall : 0
         */

        private String type;
        private String thumbnail;
        private String online;
        private String title;
        private String source;
        private SubscribeBean subscribe;
        private String id;
        private String documentId;
        private String staticId;
        private StyleBean style;
        private String styleType;
        private LiveExtBean liveExt;
        private LinkBean link;
        private String reftype;
        private String showType;
        private String updateTime;
        private String commentsUrl;
        private String comments;
        private String commentsall;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public SubscribeBean getSubscribe() {
            return subscribe;
        }

        public void setSubscribe(SubscribeBean subscribe) {
            this.subscribe = subscribe;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDocumentId() {
            return documentId;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public String getStaticId() {
            return staticId;
        }

        public void setStaticId(String staticId) {
            this.staticId = staticId;
        }

        public StyleBean getStyle() {
            return style;
        }

        public void setStyle(StyleBean style) {
            this.style = style;
        }

        public String getStyleType() {
            return styleType;
        }

        public void setStyleType(String styleType) {
            this.styleType = styleType;
        }

        public LiveExtBean getLiveExt() {
            return liveExt;
        }

        public void setLiveExt(LiveExtBean liveExt) {
            this.liveExt = liveExt;
        }

        public LinkBean getLink() {
            return link;
        }

        public void setLink(LinkBean link) {
            this.link = link;
        }

        public String getReftype() {
            return reftype;
        }

        public void setReftype(String reftype) {
            this.reftype = reftype;
        }

        public String getShowType() {
            return showType;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getCommentsall() {
            return commentsall;
        }

        public void setCommentsall(String commentsall) {
            this.commentsall = commentsall;
        }

        public static class SubscribeBean {
            /**
             * cateid : 凤凰汽车
             * type : source
             * catename : 凤凰汽车
             * description :
             */

            private String cateid;
            private String type;
            private String catename;
            private String description;

            public String getCateid() {
                return cateid;
            }

            public void setCateid(String cateid) {
                this.cateid = cateid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class StyleBean {
            /**
             * view : longimg
             * backreason : ["不感兴趣","不想看:凤凰汽车","内容质量差","旧闻、重复","标题党"]
             */

            private String view;
            private List<String> backreason;

            public String getView() {
                return view;
            }

            public void setView(String view) {
                this.view = view;
            }

            public List<String> getBackreason() {
                return backreason;
            }

            public void setBackreason(List<String> backreason) {
                this.backreason = backreason;
            }
        }

        public static class LiveExtBean {
            /**
             * startStamp : 1503626400
             * endStamp : 1503651600
             * startTime : 2017-08-25 10:00:00
             * status : 2
             * hasVideo : true
             * hasVr : false
             */

            private String startStamp;
            private String endStamp;
            private String startTime;
            private String status;
            private boolean hasVideo;
            private boolean hasVr;

            public String getStartStamp() {
                return startStamp;
            }

            public void setStartStamp(String startStamp) {
                this.startStamp = startStamp;
            }

            public String getEndStamp() {
                return endStamp;
            }

            public void setEndStamp(String endStamp) {
                this.endStamp = endStamp;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public boolean isHasVideo() {
                return hasVideo;
            }

            public void setHasVideo(boolean hasVideo) {
                this.hasVideo = hasVideo;
            }

            public boolean isHasVr() {
                return hasVr;
            }

            public void setHasVr(boolean hasVr) {
                this.hasVr = hasVr;
            }
        }

        public static class LinkBean {
            /**
             * type : text_live
             * url : 108991
             * weburl : http://liveshare.iclient.ifeng.com/live_share?liveId=108991&vt=5
             */

            private String type;
            private String url;
            private String weburl;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getWeburl() {
                return weburl;
            }

            public void setWeburl(String weburl) {
                this.weburl = weburl;
            }
        }
    }
}
