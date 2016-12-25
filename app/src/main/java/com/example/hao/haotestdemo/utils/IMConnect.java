package com.example.hao.haotestdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.hao.haotestdemo.acticity.MainActivity;
import com.example.hao.haotestdemo.app.App;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Hao on 2016/12/21.
 */

public class IMConnect {
    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 Context之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param context
     * @param token
     * @return RongIM  客户端核心类的实例。
     */
    public static void connect(final Activity context, String token) {
        if (context.getApplicationInfo().packageName.equals(App.getCurProcessName(context.getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d("Debug", "onTokenIncorrect" + "onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("Debug", "--onSuccess" + userid);
                    context.startActivity(new Intent(context, MainActivity.class));
                    context.finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("Debug", "onError" + errorCode.getMessage());
                }
            });
        }
    }
}
//    /**
//     * 请求Server API，获取token
//     */
//    public static void connectIM(final Activity context, String userId, String name, String portraitUri) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.cn.ronghub.com/user/").addConverterFactory(ScalarsConverterFactory.create()).build();
//        GetToken getToken = retrofit.create(GetToken.class);
//        String timestamp = new Date().getTime() + "";
//        String nonce = (int) Math.random() * 100000 + "";
//        String signature = "";
//        try {
//            signature = getSha1("4nIgBEF7BNSZ"+nonce+timestamp);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        Call<String> call = getToken.getString(userId, name, portraitUri, "pgyu6atqpgg9u", nonce, timestamp, signature);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.d("Debug", "response"+response.toString());
//                if(response.body()!=null){
//                String token = "";
//                try {
//                    JSONObject object = new JSONObject(response.toString());
//                    token = object.optString("token");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } finally {
//                    connect(context, token);
//                }
//                }else{
//                    String msg = null;
//                    try {
//                        msg = response.errorBody().string();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Log.d("Debug", "response.errorBody()"+msg);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.d("Debug", "onFailure"+t.getMessage());
//            }
//        });
//    }
//
//    private static String getSha1(String data) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA1");
//        md.update(data.getBytes());
//        StringBuffer buf = new StringBuffer();
//        byte[] bits = md.digest();
//        for (int i = 0; i < bits.length; i++) {
//            int a = bits[i];
//            if (a < 0) a += 256;
//            if (a < 16) buf.append("0");
//            buf.append(Integer.toHexString(a));
//        }
//        return buf.toString();
//    }
//}
//
//interface GetToken {
//    @POST("getToken")
//    Call<String> getString(@Query("userId") String userId,
//                           @Query("name") String name,
//                           @Query("portraitUri") String portraitUri,
//                           @Header("App-Key") String appKey,
//                           @Header("Nonce") String nonce,
//                           @Header("Timestamp") String timestamp,
//                           @Header("Signature") String signature);
//}