package com.dyx.rrmp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Bind(R.id.btn_reg)
    Button btnReg;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_reg)
    public void onClick() {
        //Create Retrofit Object
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.HOST_API).build();
        //Create MyService Object
        MyService service = retrofit.create(MyService.class);
        //Get Call Object
        Call<RegModel> call = service.reg(Constants.CLIENT_ID, Constants.CLIENT_SECRET, Constants.USER_EMAIL, etUsername.getText().toString(), etPassword.getText().toString());
        //
        call.enqueue(new Callback<RegModel>() {
            @Override
            public void onResponse(Call<RegModel> call, Response<RegModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, response.body().getId() + "");
                }
            }

            @Override
            public void onFailure(Call<RegModel> call, Throwable t) {

            }
        });
    }
}
