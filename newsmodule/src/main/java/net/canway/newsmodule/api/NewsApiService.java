package net.canway.newsmodule.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author 张文建 king
 * @class Android高级开发实践
 * @desc  网络接口封装类
 */
public interface NewsApiService {

    /*-----------------新闻部分网络接口-----------------*/
    String NEWS_BASE_URL = "http://news-at.zhihu.com/api/";
    /*获得最新新闻*/
    @GET("4/news/latest")
    Observable<StoriesLatestBean> getLatestNews();
    //http://news-at.zhihu.com/api/4/news/latest

    /*获得新闻详情*/
    @GET("4/news/{id}")
    Observable<NewsDetailBean> getNewsDetails(@Path("id") long id);
    //http://news-at.zhihu.com/api/4/news/3892357



    /*-----------------图片部分网络接口-----------------*/

    /*-----------------视频部分网络接口-----------------*/

    /*-----------------及时通讯模块网络接口-----------------*/

}
