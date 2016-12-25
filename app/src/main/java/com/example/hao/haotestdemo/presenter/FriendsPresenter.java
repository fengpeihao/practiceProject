package com.example.hao.haotestdemo.presenter;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hao.haotestdemo.R;
import com.example.hao.haotestdemo.contract.FriendsContract;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * Created by Hao on 2016/12/23.
 */

public class FriendsPresenter extends FriendsContract.Presenter {
    @Override
    public void addFriends() {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(mContext, R.layout.dialog_add_friends,
                null);
        final EditText editText = (EditText) view.findViewById(R.id.filter_edit);

        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = editText.getText().toString().trim();
                Intent intent = new Intent();
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);

                Uri.Builder uriBuilder = Uri.parse("rong://" + mContext.getPackageName()).buildUpon();
                uriBuilder.appendPath("push_message")
                        .appendQueryParameter("targetId", userId)
                        .appendQueryParameter("pushData", "哈哈请求添加你为好友")
                        .appendQueryParameter("pushId", userId)
                        .appendQueryParameter("extra", "");

                mContext.startActivity(intent);
//                TextMessage textMessage = TextMessage.obtain("哈哈请求添加你为好友");
//                Message message = Message.obtain(userId, Conversation.ConversationType.PRIVATE,textMessage);
//                RongIM.getInstance().sendMessage(message, null, null, new IRongCallback.ISendMessageCallback() {
//
//                    @Override
//                    public void onAttached(Message message) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Message message) {
//                        Toast.makeText(mContext,"发送成功",Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//
//                    @Override
//                    public void onError(Message message, RongIMClient.ErrorCode errorCode) {
//                        Toast.makeText(mContext,"发送失败",Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                });
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }
}
