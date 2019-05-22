<script type="text/javascript" src="./assert/2jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="./assert/2html.js"></script><link rel="stylesheet" type="text/css" href="./assert/2style.css">

# 目錄

[TOC]

### 設定說明

---

將 config.gradle 放入與 bundle.gradle 同層 (project level)

```java
// 加入 project 依賴
apply from:"config.gradle"
buildscript{
    respositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.1'
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" } //記得加這句否則 BRAVAH Build不起來
    }
}
```



build.gradle (app level)

```java
dependencies {
	implementation rootProject.ext.dependencies[""]
}
```



---

### 隱藏鍵盤

Activity 下覆寫 dispatchTouchEvent

```java
@Override
public boolean dispatchTouchEvent(MotionEvent ev) {
    switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            View view = getCurrentFocus();
            HideKeyBoard.hide(ev, view, ViewActivity.this);
            break;
        default:
            break;
    }
    return super.dispatchTouchEvent(ev);
}
```

---

### 頁面新增

BlockEmpty 內頁面抽過去使用即可，將 import Empty 資料夾移除

可從外部呼 Call_API 方法手動操作API

---

### PopupWindow

```java
//PopupWindow 初始化
PopupWindowMgr popupWindowMgr = new PopupWindowMgr.init().setContext(getBaseContext()).setShowView(R.layout.view_upload).build();
//PopupWindow 顯示
popupWindowMgr.showAtLocation(R.layout.view_upload,Gravity.BOTTOM,0,0);
//初始化物件
btn_cancel = (Button) popupWindowMgr.getView(R.id.btn_cancel);
//關閉監聽
popupWindowMgr.setOnDismissListener(() -> {
    //關閉後動作
});
```

---

### NoScrollViewPager

```xml
<com.example.administrator.zongdemo_smxxth.Utils_Tools.NoScrollViewPager
        android:id="@+id/vp_main"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toTopOf="@+id/btn_navigation"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

```java
private NoScrollViewPager vp_main;
private List<Fragment> lst_Fragment;

lst_Fragment = new ArrayList<>();
lst_Fragment.add(<預加入的Fragment>);
lst_Fragment.add(<預加入的Fragment>);
lst_Fragment.add(<預加入的Fragment>);

MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), lst_Fragment);

vp_main.setAdapter(myAdapter);	//設置配置器
vp_main.setCurrentItem(0,true);	//滑動至指定頁面
```

---

### GlideImageLoader

在 Vlayout 內使用 Glide 需另寫 Class，以下為使用方式

```java
new GlideImageLoader().displayCircle(<Context>, holder.getView(R.id.img_head), <圖片位置>);
```

---

### GlideCircleWithBorder

Glide 4.0 的版本已經有自帶圓型修圖，目前沒用

---

### FileSaveUtil

讀取檔案路徑

```java
final String path = FileSaveUtil.with(getBaseContext()).saveBitmap(bmp);
```

---

### DebugClass

```java
DebugClass.getInstance().setDebugLog("ImagePathFileName",ImagePathFileName);
.setDebugLog() void //僅Debug模式
.setErrorLog() void	//全Error模式
```

---

### CustomeShadowView



---

### BreathInterpolator

Interpolator 插值器，此為呼吸函數，用於補間動畫的速率控制

其他預設的有

- AccelerateDecelerateInterpolator 在动画开始与介绍的地方速率改变比较慢，在中间的时候加速
- AccelerateInterpolator 在动画开始的地方速率改变比较慢，然后开始加速
- AnticipateInterpolator 开始的时候向后然后向前甩
- AnticipateOvershootInterpolator 开始的时候向后然后向前甩一定值后返回最后的值
- BounceInterpolator 动画结束的时候弹起
- CycleInterpolator 动画循环播放特定的次数，速率改变沿着正弦曲线
- DecelerateInterpolator 在动画开始的地方快然后慢
- LinearInterpolator 以常量速率改变
- OvershootInterpolator 向前甩一定值后再回到原来位置

xml 寫法：

```xml
<?xml version="1.0" encoding="utf-8"?>  
<scale xmlns:android="http://schemas.android.com/apk/res/android"  
    android:interpolator="@android:anim/accelerate_decelerate_interpolator"  
    android:fromXScale="0.0"  
    android:toXScale="1.4"  
    android:fromYScale="0.0"  
    android:toYScale="1.4"  
    android:pivotX="50%"  
    android:pivotY="50%"  
    android:duration="700"   
    android:fillAfter="true"  
/>
```

 Java 寫法：

```java
ScaleAnimation animation = new ScaleAnimation(
        1.0f, 2.0f, 1.0f, 2.0f,
        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
);
animation.setInterpolator(new BreathInterpolator());
animation.setDuration(3000);
view.startAnimation(animation);
```

---

### AppBarStatusChangeListener

使用方式

```java
appbarlayout.addOnOffsetChangedListener(new AppBarStatusChangeListener(){})
```

---

### WebsocketManager

設定 Websocket 連線位置

```java
WebsocketManager.DEF_URL = "ws://192.168.8.123:8000/ws/";
```



初始化

```java
	WebsocketManager.getInstance().create_websocket();
```



發送訊息

```java
	WebsocketManager.getInstance().sendMessage(string);
```



監聽回傳

```java
    WebsocketManager.getInstance().setWsListener(new WebsocketManager.WsInterface() {
        @Override
        public void getDataSuccess(JsonObject response) {
            view.getIMSuccess(response);
        }

        @Override
        public void getDataFail(String s) {
            view.getIMFail(s);
        }
    });
```



斷線

```java
	WebsocketManager.getInstance().disconnect();
```

---

### APIUtils

更改呼 API 位置

```java
APIUtils.URL = "<API網址>";
```

---

### APIService

Retrofit 2 的接口，寫的時候另外自己寫一支

舉例：

```java
@POST("{url}Member/member_serach/")
Single<Response<JsonObject>> Call_MemberSearch_API(@Path("url") String url,@Body JsonObject body);

```



上傳圖片：

```java
@Multipart
@POST("{url}Dynamic/dynamic_upload/")
Single<Response<JsonObject>> upload(@Path("url") String url,@Part("description") RequestBody description, @Part List<MultipartBody.Part> files, @Part("body") JsonObject jsonObject);
```



---

