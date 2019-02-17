package com.example.xsxerdiyiz.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.xsxerdiyiz.R;
import com.example.xsxerdiyiz.adapter.ShopAdapter;
import com.example.xsxerdiyiz.contract.ShowContract;
import com.example.xsxerdiyiz.entity.RequestParams;
import com.example.xsxerdiyiz.presenter.ShowPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentOne extends BaseFragment implements ShowContract.ShowView {
    private Unbinder unbinder;
    @BindView(R.id.xre)
    XRecyclerView rv;
    @BindView(R.id.but_name)
    Button but_name;
    @BindView(R.id.et_name)
    EditText et_name;
    private String page="1";
    private String count="5";
    private ShowPresenter showPresenter;
    private ShopAdapter shopAdapter;



    @Override
    protected int getLayout() {
        return R.layout.fragmentone;
    }

    @Override
    protected void initView(View view) {
        showPresenter=new ShowPresenter(this);
        shopAdapter=new ShopAdapter(getActivity());
        rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rv.setAdapter(shopAdapter);
    }


    @Override
    protected void initData() {

    }
    @OnClick(R.id.but_name)
    public void but(View view){
        String s = et_name.getText().toString();
        HashMap<String, String> params = new HashMap<>();
        params.put("keyword",s);
        params.put("page",page);
        params.put("count",count);
        showPresenter.getShowData(params);
    }

    @Override
    public void ShowDataSuccess(RequestParams requestParams) {
        shopAdapter.setList(requestParams.getResult());


    }

    @Override
    public void Frailure(String msg) {

    }
}
