# RxXxx-Retrofit-Mvp-Project

RxJava、RxAndroid、Retrofir、MVP架构项目。

## 单独使用Retrofit

MyService.java

```java
public interface MyService {
    @GET("oauth2/reg")
    Call<RegModel> reg(@Query("client_id") String userClientId, @Query("client_secret") String userClientSecret, @Query("email") String userEmail, @Query("account") String userAccount, @Query("password") String userPassword);
}
```
MainActivity.java

```java
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
```

## RxJava与Retrofit结合使用

MyService.java

```java
public interface MyService {
    @GET("oauth2/reg")
    Observable<RegModel> reg(
            @Query("client_id") String client_id,
            @Query("client_secret") String client_secret,
            @Query("email") String email,
            @Query("account") String account,
            @Query("password") String password
    );
}
```


RxActivity.java

 ```java
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
```