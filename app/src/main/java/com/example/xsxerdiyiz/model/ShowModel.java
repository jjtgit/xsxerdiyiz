package com.example.xsxerdiyiz.model;

import com.example.xsxerdiyiz.api.Api;
import com.example.xsxerdiyiz.contract.ShowContract;
import com.example.xsxerdiyiz.net.RequestCallback;
import com.example.xsxerdiyiz.net.RequestService;

import java.util.HashMap;

public class ShowModel implements ShowContract.ShowModel {
    @Override
    public void getModel(HashMap<String, String> params, final RequestCallback callback) {
        String keyword = params.get("keyword");
        String page = params.get("page");
        String count = params.get("count");
        RequestService.getRequestService().doGet(Api.USER_CALL + "?keyword=" + keyword + "&page=" + page + "&count=" + count, new RequestCallback() {
            @Override
            public void Success(String res) {
                if (callback!=null){
                    callback.Success(res);
                }
            }

            @Override
            public void Frailure(String msg) {
                if (callback!=null){
                    callback.Frailure(msg);
                }
            }
        });
    }
}
