package com.example.hao.haotestdemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hao.haotestdemo.R;
import com.example.hao.haotestdemo.bean.FriendsModel;
import com.example.hao.haotestdemo.contract.FriendsContract;
import com.example.hao.haotestdemo.presenter.FriendsPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FriendsFragment extends BaseFragment<FriendsPresenter,FriendsModel> implements FriendsContract.View {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.tv_add_friends)
    TextView tvAddFriends;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FriendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FriendsFragment newInstance(String param1, String param2) {
        FriendsFragment fragment = new FriendsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_friends;
    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.tv_add_friends)
    public void onClick() {
        mPresenter.addFriends();
    }
}
