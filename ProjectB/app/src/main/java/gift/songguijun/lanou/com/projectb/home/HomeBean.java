package gift.songguijun.lanou.com.projectb.home;

import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class HomeBean {



    private String code;
    private String msg;
    private long now;
    private int cost;
    private List<DataBeanX> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {


        private int type;
        private String title;
        private boolean showTitle;
        private int size;
        private MoreDataBean moreData;
        private String pathKey;
        private String enTitle;
        private List<DataBean> data;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isShowTitle() {
            return showTitle;
        }

        public void setShowTitle(boolean showTitle) {
            this.showTitle = showTitle;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public MoreDataBean getMoreData() {
            return moreData;
        }

        public void setMoreData(MoreDataBean moreData) {
            this.moreData = moreData;
        }

        public String getPathKey() {
            return pathKey;
        }

        public void setPathKey(String pathKey) {
            this.pathKey = pathKey;
        }

        public String getEnTitle() {
            return enTitle;
        }

        public void setEnTitle(String enTitle) {
            this.enTitle = enTitle;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class MoreDataBean {
            /**
             * supportPage : false
             * supportTransverseSlider : false
             * show : false
             */

            private boolean supportPage;
            private boolean supportTransverseSlider;
            private boolean show;

            public boolean isSupportPage() {
                return supportPage;
            }

            public void setSupportPage(boolean supportPage) {
                this.supportPage = supportPage;
            }

            public boolean isSupportTransverseSlider() {
                return supportTransverseSlider;
            }

            public void setSupportTransverseSlider(boolean supportTransverseSlider) {
                this.supportTransverseSlider = supportTransverseSlider;
            }

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }
        }

        public static class DataBean {


            private String dataType;
            private int videoId;
            private String type;
            private String title;
            private String desc;
            private String posterPic;
            private int totalView;
            private String clickUrl;
            private String regdate;
            private boolean isVchart;
            private String dataTypeUrl;
            private boolean ad;
            private String icon ;
            private String url;
            private List<ArtistsBean> artists;
            private List<Integer> videoTypes;

            public String getIcon() {
                return icon;
            }

            public DataBean setIcon(String icon) {
                this.icon = icon;
                return this;
            }

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPosterPic() {
                return posterPic;
            }

            public void setPosterPic(String posterPic) {
                this.posterPic = posterPic;
            }

            public int getTotalView() {
                return totalView;
            }

            public void setTotalView(int totalView) {
                this.totalView = totalView;
            }

            public String getClickUrl() {
                return clickUrl;
            }

            public void setClickUrl(String clickUrl) {
                this.clickUrl = clickUrl;
            }

            public String getRegdate() {
                return regdate;
            }

            public void setRegdate(String regdate) {
                this.regdate = regdate;
            }

            public boolean isIsVchart() {
                return isVchart;
            }

            public void setIsVchart(boolean isVchart) {
                this.isVchart = isVchart;
            }

            public String getDataTypeUrl() {
                return dataTypeUrl;
            }

            public void setDataTypeUrl(String dataTypeUrl) {
                this.dataTypeUrl = dataTypeUrl;
            }

            public boolean isAd() {
                return ad;
            }

            public void setAd(boolean ad) {
                this.ad = ad;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public List<ArtistsBean> getArtists() {
                return artists;
            }

            public void setArtists(List<ArtistsBean> artists) {
                this.artists = artists;
            }

            public List<Integer> getVideoTypes() {
                return videoTypes;
            }

            public void setVideoTypes(List<Integer> videoTypes) {
                this.videoTypes = videoTypes;
            }

            public static class ArtistsBean {
                /**
                 * artistId : 36176
                 * artistName : 鹿晗
                 * artistAvatar : http://img2.yytcdn.com/artist/fan/150804/0/-M-60bda4a3e5b055308c71349dbae82c49_0x0.jpg
                 * area : ML
                 */

                private int artistId;
                private String artistName;
                private String artistAvatar;
                private String area;

                public int getArtistId() {
                    return artistId;
                }

                public void setArtistId(int artistId) {
                    this.artistId = artistId;
                }

                public String getArtistName() {
                    return artistName;
                }

                public void setArtistName(String artistName) {
                    this.artistName = artistName;
                }

                public String getArtistAvatar() {
                    return artistAvatar;
                }

                public void setArtistAvatar(String artistAvatar) {
                    this.artistAvatar = artistAvatar;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }
            }
        }
    }
}
