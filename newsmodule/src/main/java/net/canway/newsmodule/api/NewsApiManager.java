package net.canway.newsmodule.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.canway.commonsdk.commonutils.AppUtil;
import net.canway.commonsdk.commonutils.NetWorkUtil;
import net.canway.commonsdk.config.Constants;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 张文建 king
 * @class Android高级开发实践
 * @desc  rxjava网络请求管理类
 */
public class NewsApiManager {

    private Retrofit mRetrofit;
    private static NewsApiManager instance;

    private static NewsApiManager getInstance() {
        if (instance == null) {
            instance = new NewsApiManager();
        }
        return instance;
    }

    //构造器中进行初始化操作
    private NewsApiManager() {
        OkHttpClient httpClient = getClientBuilder().build();
        Gson gson = getGsonBuilder().create();

        // create unique instance
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiService.NEWS_BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static ApiService getApiService() {
        return getInstance().mRetrofit.create(ApiService.class);
    }

    private OkHttpClient.Builder getClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // 官方提供的日志拦截器
        if (Constants.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        // 设置缓存大小
        File cacheFile = new File(AppUtil.getCacheDir() + File.pathSeparator + "httpCache");
        builder.cache(new okhttp3.Cache(cacheFile, 10 * 1024 * 1024));// 10MB

        // 缓存控制
        CacheInterceptor interceptor = new CacheInterceptor();
        builder.addNetworkInterceptor(interceptor);
        builder.addInterceptor(interceptor);

        // 超时设置
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);

        // 出现错误时重新连接
        builder.retryOnConnectionFailure(true);
        return builder;
    }

    //用来创建自定义gson实例
    private GsonBuilder getGsonBuilder() {
        return new GsonBuilder()
                .setDateFormat("yyyyMMdd")
                .disableInnerClassSerialization()
                .generateNonExecutableJson()
                .disableHtmlEscaping()
                .setPrettyPrinting();
    }

    //自定义拦截器
    private class CacheInterceptor implements okhttp3.Interceptor {
        @Override
        public Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
            // 获取请求的request
            Request request = chain.request();

            // FORCE_CACHE:仅仅使用缓存  FORCE_NETWORK :仅仅使用网络
            Request cacheRequest = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();

            Response response;
            /*网络可用的时候直接进行网络请求,网络不可以内存请求*/
            if (!NetWorkUtil.isNetWorkAvailable(AppUtil.getAppContext())) {
                response = chain.proceed(cacheRequest);
            } else {
                response = chain.proceed(request);
            }

            // cache mechanism
            if (NetWorkUtil.isNetWorkAvailable(AppUtil.getAppContext())) {
                int maxAge = 60 * 10;
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }


    }
}
