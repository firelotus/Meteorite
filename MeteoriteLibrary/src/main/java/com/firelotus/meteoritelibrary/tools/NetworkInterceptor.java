package com.firelotus.meteoritelibrary.tools;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.tamic.novate.NovateResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 网络请求拦截器
 * Created by firelotus on 2017/10/15.
 */

public class NetworkInterceptor implements Interceptor{
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private Context context;

    public NetworkInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        /*Response response = chain.proceed(chain.request());
        String jstr = new String(response.body().bytes());

        Type type = new TypeToken<NovateResponse>() {}.getType();
        final NovateResponse novateResponse = new Gson().fromJson(jstr, type);
        if (!novateResponse.isResult()) {
            //返回code统一处理
        }
        return chain.proceed(chain.request());*///网络请求发送两次
        Request request = chain.request();
        /**
         * 请求URL打印
         */
        String method = request.method();
        if ("POST".equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + "&");
                }
                sb.delete(sb.length() - 1, sb.length());
                MLog.d("NetworkInterceptor Request ==>>  " + request.url().toString() + "?" + sb.toString());
                Logger.d("NetworkInterceptor Request ==>>  " + request.url().toString() + "?" + sb.toString());
            }
        }else if("GET".equals(method)){
            MLog.d("NetworkInterceptor Request ==>>  " + request.url().toString());
            Logger.d("NetworkInterceptor Request ==>>  " + request.url().toString());
        }

        Response originalResponse = chain.proceed(request);

        /**通过如下的办法曲线取到请求完成的数据
         * 当使用拦截器获取Response.body.string()后，后面的操作就直接返回Failed了，估计是因为流只能被使用一次的原因
         */
        ResponseBody responseBody = originalResponse.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        String bodyString = buffer.clone().readString(charset);
        List<String> segments = request.url().pathSegments();
        MLog.d("NetworkInterceptor Response ==>>  " + segments.get(segments.size() - 1) + " : " + bodyString);
        Logger.d("NetworkInterceptor Response ==>>  " + segments.get(segments.size() - 1));
        Logger.json(bodyString);
        Type type = new TypeToken<NovateResponse>() {
        }.getType();
        NovateResponse novateResponse = new Gson().fromJson(bodyString, type);
        if (novateResponse.getCode() != 0) {
            //返回code统一处理
        }

        return originalResponse;
    }
}
