package com.dyx.rrmp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.dyx.rrmp.rx.MyService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/1 下午11:10
 * alter person：dayongxin
 * alter time：16/8/1 下午11:10
 * alter remark：
 */
public class RxActivity extends Activity {
    private static final String TAG = "RxActivity";
    @Bind(R.id.et_un)
    EditText etUn;
    @Bind(R.id.et_pw)
    EditText etPw;
    @Bind(R.id.btn_reg1)
    Button btnReg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_reg1)
    public void onClick() {
        //Create Retrofit Object
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(Constants.HOST_API).build();
        //Create MyService Object
        MyService service = retrofit.create(com.dyx.rrmp.rx.MyService.class);
        //
        service.reg(Constants.CLIENT_ID, Constants.CLIENT_SECRET, Constants.USER_EMAIL, etUn.getText().toString(), etPw.getText().toString()).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io()).doOnNext(new Action1<RegModel>() {
            @Override
            public void call(RegModel regModel) {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RegModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RegModel regModel) {
                Log.i(TAG, regModel.getId() + "");
            }
        });
    }
}
