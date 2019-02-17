package com.example.xsxerdiyiz.presenter;

import com.example.xsxerdiyiz.contract.ShowContract;
import com.example.xsxerdiyiz.entity.RequestParams;
import com.example.xsxerdiyiz.fragment.FragmentOne;
import com.example.xsxerdiyiz.model.ShowModel;
import com.example.xsxerdiyiz.net.RequestCallback;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class ShowPresenter extends ShowContract.ShowPresenter {
    private ShowContract.ShowView view;
    private WeakReference<ShowContract.ShowView> wr;
    private ShowModel showModel;

    public ShowPresenter(ShowContract.ShowView view) {
        this.view = view;
        wr = new WeakReference<>(view);
        showModel =new ShowModel();
    }

    @Override
    public void getShowData(HashMap<String, String> params) {
        showModel.getModel(params, new RequestCallback() {
            @Override
            public void Success(String res) {
                if (view!=null){
                    RequestParams params1 = new Gson().fromJson(res, RequestParams.class);
                    view.ShowDataSuccess(params1);
                }
            }

            @Override
            public void Frailure(String msg) {
                if (view!=null){
                    view.Frailure(msg);
                }
            }
        });
    }
    public void uBindView(){
        if (view!=null){
            wr.clear();
            wr=null;
            view=null;
        }
    }
}
