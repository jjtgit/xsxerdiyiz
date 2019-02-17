package com.example.xsxerdiyiz.contract;

import com.example.xsxerdiyiz.entity.RequestParams;
import com.example.xsxerdiyiz.net.RequestCallback;

import java.util.HashMap;

public interface ShowContract {
    abstract class ShowPresenter{
        public abstract void getShowData(HashMap<String,String>params);
    }
    interface ShowModel{
        void getModel(HashMap<String,String>params, RequestCallback callback);
    }
    interface ShowView{
        void ShowDataSuccess(RequestParams requestParams);
        void Frailure(String msg);
    }
}
