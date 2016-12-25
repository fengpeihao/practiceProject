package com.example.hao.haotestdemo.acticity;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hao.haotestdemo.R;
import com.example.hao.haotestdemo.fragment.FriendsFragment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import butterknife.Bind;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class MainActivity extends BaseActivity {

    @Bind(R.id.rb_message)
    RadioButton rbMessage;
    @Bind(R.id.rb_friends)
    RadioButton rbFriends;
    @Bind(R.id.rb_mine)
    RadioButton rbMine;
    @Bind(R.id.radiogroup)
    RadioGroup radiogroup;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initViews() {
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_message:
                       getMessageList();
                        break;
                    case R.id.rb_friends:
                        FriendsFragment friendsFragment = new FriendsFragment();
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.frame,friendsFragment).commit();
                        break;
                }
            }
        });
    }

    private void getMessageList() {
        ConversationListFragment conversationListFragment = new ConversationListFragment();
        HashMap<String, Boolean> supportedConversation = new HashMap<>();
        supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
        Uri.Builder builder = Uri.parse("rong://" + MainActivity.this.getApplicationInfo().packageName).buildUpon().appendPath("conversationlist");
        if (supportedConversation != null && supportedConversation.size() > 0) {
            Set keys = supportedConversation.keySet();
            Iterator i$ = keys.iterator();
            while (i$.hasNext()) {
                String key = (String) i$.next();
                builder.appendQueryParameter(key, ((Boolean) supportedConversation.get(key)).booleanValue() ? "true" : "false");
            }
        }
        conversationListFragment.setUri(builder.build());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,conversationListFragment).commit();
    }

    @Override
    public void destroy() {

    }

  /*  @OnClick(R.id.click)
    public void onClick() {
        *//**
     * 启动单聊界面。
     *
     * @param context      应用上下文。
     * @param targetUserId 要与之聊天的用户 Id。
     * @param title        聊天的标题。开发者需要在聊天界面通过 intent.getData().getQueryParameter("title")
     *                     获取该值, 再手动设置为聊天界面的标题。
     *//*
        String userId = "";
        if("13052313520".equals(App.userId)){
            userId = "18639721736";
        }else{
            userId = "13052313520";
        }
        UserInfo userInfo = new UserInfo("18639721736","嘻嘻",null);
        RongIM.getInstance().setCurrentUserInfo(userInfo);
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        RongIM.getInstance().startPrivateChat(this, userId, "标题");//启动会话
        //启动会话列表
//        HashMap<String, Boolean> supportedConversation = new HashMap<>();
//        supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
//        RongIM.getInstance().startConversationList(this,supportedConversation);

//        Intent intent = new Intent();
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri.Builder builder = Uri.parse("rong://" + this.getPackageName()).buildUpon();
//
//        builder.appendPath("conversation").appendPath(Conversation.ConversationType.CHATROOM.getName())
//                .appendQueryParameter("targetId", "13052313520")
//                .appendQueryParameter("title", "标题");
//        Uri uri = builder.build();
//        intent.setData(uri);
//        startActivity(intent);
    }*/
}
