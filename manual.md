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

### Vlayout 用法

```java
private RecyclerView rv_chat;

/**
 * RecyclerView 設置
 */
VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(mContext);
        rv_chat.setLayoutManager(virtualLayoutManager);

final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
viewPool.setMaxRecycledViews(0, 10);
rv_chat.setRecycledViewPool(viewPool);

delegateAdapter = new DelegateAdapter(virtualLayoutManager);
rv_chat.setAdapter(delegateAdapter);
/**
 * 新增子Adapter
 */
StickyLayoutHelper sticky = new StickyLayoutHelper();
BaseDelegateAdapter stickyAdapter =
        new BaseDelegateAdapter(mContext, sticky, R.layout.vlayout_chat, 1, Config.STICKY_VIEW_TYPE) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.getView(R.id.img_diamond).setOnClickListener(v -> {
                    // 儲值API
                });

                holder.getView(R.id.tbx_diamond_count).setOnClickListener(v -> {
                    // 儲值API
                });
            }
        };
delegateAdapter.addAdapter(stickyAdapter);
```

---

### MVP用法

onCreate

```java
InitData();
```

InitData

```java
presenter = new Presenter(new Model(),this);
```

onDestroy

```java
presenter.dispose();
```

Call API

```java
presenter.setUser();
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

記得在有 AndroidManifest.xml 加入有新增的 Activity

可從外部呼 Call_API 方法手動操作API

---

### PopupWindow

在 anim 加入 publish_in.xml, publish_out.xml

publish_in.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android" >
    <translate
        android:duration="300"
        android:fromYDelta="100%p" />
</set>
```

publish_out.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android" >
    <translate
        android:duration="300"
        android:toYDelta="100%p" />
</set>
```

style.xml 加入

```xml
<resource>
    ...
    <style name="publish_setting" parent="android:Animation">
        <item name="@android:windowEnterAnimation">@anim/publish_in</item>
        <item name="@android:windowExitAnimation">@anim/publish_out</item>
    </style>
</resource>
```



```java
//PopupWindow 初始化
PopupWindowMgr popupWindowMgr = new PopupWindowMgr.init().setContext(getBaseContext()).setAnimStyle(R.anim.publish_setting).setShowView(R.layout.view_upload).build();
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

- AccelerateDecelerateInterpolator 在動畫開始與介紹的地方速率改變比較慢，在中間的時候加速
- AccelerateInterpolator 在動畫開始的地方速率改變比較慢，然後開始加速
- AnticipateInterpolator 開始的時候向後然後向前甩
- AnticipateOvershootInterpolator 開始的時候向後然後向前甩一定值後返回最後的值
- BounceInterpolator 動畫結束的時候彈起
- CycleInterpolator 動畫循環播放特定的次數，速率改變沿著正弦曲線
- DecelerateInterpolator 在動畫開始的地方快然後慢
- LinearInterpolator 以常量速率改變
- OvershootInterpolator 向前甩一定值後再回到原來的位置

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

