"# vietinfo_module_demo" 
# Tích hợp module VietInfo to Adnroid project

## Thêm foder module **vietinfomodule** vào project
![alt tag](https://github.com/nmtruongloc806/vietinfo_module_demo/blob/main/images/1.png)

## Tại file settings.gradle thêm dòng code `include ':vietinfomodule'`

![alt tag](https://github.com/nmtruongloc806/vietinfo_module_demo/blob/main/images/1.png)

## Tại file build.gradle trong block allprojects -> repositories thêm code
```
String storageUrl = System.env.FLUTTER_STORAGE_BASE_URL ?: "https://storage.googleapis.com"
maven {
    url "$project.rootDir/vietinfomodule/libs"
}
maven {
    url "$storageUrl/download.flutter.io"
}
```

![alt tag](https://github.com/nmtruongloc806/vietinfo_module_demo/blob/main/images/1.png)
## Tại file app/build.gradle trong block dependencies thêm code
```
debugImplementation 'tech.vietinfo.hello_world_module:flutter_debug:1.0'
releaseImplementation 'tech.vietinfo.hello_world_module:flutter_release:1.0'
```
![alt tag](https://github.com/nmtruongloc806/vietinfo_module_demo/blob/main/images/1.png)
## Khai báo activity bên trong file AndroidManifest.xml
```
<activity android:name="tech.vietinfo.vietinfomodule.VietinfoActivity"
            android:theme="@style/Theme.AppCompat.Light.NoActionBar"/>
```
## startActivity để có thể mở module
```
Intent intent = new Intent(MainActivity.this, VietinfoActivity.class);
startActivity(intent);
```


