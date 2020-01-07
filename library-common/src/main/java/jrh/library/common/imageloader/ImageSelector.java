//package jrh.library.common.imageloader;
//
//public class ImageSelector {
//
//
//    public static void select(){
//
//        // 进入相册 以下是例子：用不到的api可以不写
////        PictureSelector.create(MainActivity.this)
////                .openGallery()//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
////                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
////                .maxSelectNum()// 最大图片选择数量 int
////                .minSelectNum()// 最小选择数量 int
////                .imageSpanCount(4)// 每行显示个数 int
////                .cameraFileName("")// 使用相机时保存至本地的文件名称,注意这个只在拍照时可以使用，选图时不要用
////                .isSingleDirectReturn(false)// 单选模式下是否直接返回
////                .isChangeStatusBarFontColor(isChangeStatusBarFontColor)// 是否关闭白色状态栏字体颜色
////                .setStatusBarColorPrimaryDark(statusBarColorPrimaryDark)// 状态栏背景色
////                .setUpArrowDrawable(upResId)// 设置标题栏右侧箭头图标
////                .setDownArrowDrawable(downResId)// 设置标题栏右侧箭头图标
////                .isOpenStyleCheckNumMode(isOpenStyleCheckNumMode)// 是否开启数字选择模式 类似QQ相册
////                .selectionMode()// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
////                .previewImage()// 是否可预览图片 true or false
////                .previewVideo()// 是否可预览视频 true or false
////                .enablePreviewAudio() // 是否可播放音频 true or false
////                .isCamera()// 是否显示拍照按钮 true or false
////                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
////                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
////                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
////                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
////                .enableCrop()// 是否裁剪 true or false
////                .compress()// 是否压缩 true or false
////                .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
////                .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
////                .hideBottomControls()// 是否显示uCrop工具栏，默认不显示 true or false
////                .isGif()// 是否显示gif图片 true or false
////                .compressSavePath(getPath())//压缩图片保存地址
////                .freeStyleCropEnabled()// 裁剪框是否可拖拽 true or false
////                .circleDimmedLayer()// 是否圆形裁剪 true or false
////                .showCropFrame()// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
////                .showCropGrid()// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
////                .openClickSound()// 是否开启点击声音 true or false
////                .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
////                .previewEggs()// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
////                .cropCompressQuality()// 裁剪压缩质量 默认90 int
////                .minimumCompressSize(100)// 小于100kb的图片不压缩
////                .synOrAsy(true)//同步true或异步false 压缩 默认同步
////                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
////                .rotateEnabled() // 裁剪是否可旋转图片 true or false
////                .scaleEnabled()// 裁剪是否可放大缩小图片 true or false
////                .videoQuality()// 视频录制质量 0 or 1 int
////                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
////                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
////                .recordVideoSecond()//视频秒数录制 默认60s int
////                .isDragFrame(false)// 是否可拖动裁剪框(固定)
////                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//
//
//        常见错误
//        重要：PictureSelector.create()；调用此方法时，在activity中传activity.this，在fragment中请传fragment.this,
//                影响回调到哪个地方的onActivityResult()。
//
//        问题一：
//        rxjava冲突：在app build.gradle下添加
//        packagingOptions {
//            exclude 'META-INF/rxjava.properties'
//        }
//
//        问题二：
//        java.lang.NullPointerException:
//        Attempt to invoke virtual method 'android.content.res.XmlResourceParser
//        android.content.pm.ProviderInfo.loadXmlMetaData(android.content.pm.PackageManager, java.lang.String)'
//        on a null object reference
//
// * 注意 从v2.1.3版本中，将不需要配制以下内容
//
//        application下添加如下节点:
//
// <provider
//        android:name="android.support.v4.content.FileProvider"
//        android:authorities="${applicationId}.provider"
//        android:exported="false"
//        android:grantUriPermissions="true">
//       <meta-data
//        android:name="android.support.FILE_PROVIDER_PATHS"
//        android:resource="@xml/file_paths" />
//</provider>
//
//                注意：如已添加其他sdk或项目中已使用过provider节点，
//[请参考我的博客](http://blog.csdn.net/luck_mw/article/details/54970105)的解决方案
//
//        问题三：
//        经测试在小米部分低端机中，Fragment调用PictureSelector 2.0 拍照有时内存不足会暂时回收activity,
//                导致其fragment会重新创建 建议在fragment所依赖的activity加上如下代码:
//        if (savedInstanceState == null) {
//            // 添加显示第一个fragment
//            fragment = new PhotoFragment();
//            getSupportFragmentManager().beginTransaction().add(R.id.tab_content, fragment,
//                    PictureConfig.FC_TAG).show(fragment)
//                    .commit();
//        } else {
//            fragment = (PhotoFragment) getSupportFragmentManager()
//                    .findFragmentByTag(PictureConfig.FC_TAG);
//        }
//        这里就是如果是被回收时，则不重新创建 通过tag取出fragment的实例。
//
//        问题四：
//        glide冲突
//        由于PictureSelector 2.0引入的是最新的glide 4.5.0,所以将项目中老版本的glide删除,并且将报错代码换成如下写法：
//        RequestOptions options = new RequestOptions();
//        options.placeholder(R.drawable.image);
//        Glide.with(context).load(url).apply(options).into(imageView);
//
//        问题五：
//        拍照出现损坏问题
//        如果出现拍照返回后图片加载不出来出现已损坏现象，建议提前获取一下存储权限，也就是通过RxPermissions.request
//                WRITE_EXTERNAL_STORAGE
//        READ_EXTERNAL_STORAGE
//
//        问题六：
//        如果出现如下Invoke-customs are only supported starting with Android O (--min-api 26) 错误
//        请在app目录下的build.gradle android{ }末尾添加
//        compileOptions {
//            sourceCompatibility JavaVersion.VERSION_1_8
//            targetCompatibility JavaVersion.VERSION_1_8
//        }
//
//        功能配置
//// 进入相册 以下是例子：用不到的api可以不写
//        PictureSelector.create(MainActivity.this)
//                .openGallery()//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
//                .maxSelectNum()// 最大图片选择数量 int
//                .minSelectNum()// 最小选择数量 int
//                .imageSpanCount(4)// 每行显示个数 int
//                .cameraFileName("")// 使用相机时保存至本地的文件名称,注意这个只在拍照时可以使用，选图时不要用
//                .isSingleDirectReturn(false)// 单选模式下是否直接返回
//                .isChangeStatusBarFontColor(isChangeStatusBarFontColor)// 是否关闭白色状态栏字体颜色
//                .setStatusBarColorPrimaryDark(statusBarColorPrimaryDark)// 状态栏背景色
//                .setUpArrowDrawable(upResId)// 设置标题栏右侧箭头图标
//                .setDownArrowDrawable(downResId)// 设置标题栏右侧箭头图标
//                .isOpenStyleCheckNumMode(isOpenStyleCheckNumMode)// 是否开启数字选择模式 类似QQ相册
//                .selectionMode()// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
//                .previewImage()// 是否可预览图片 true or false
//                .previewVideo()// 是否可预览视频 true or false
//                .enablePreviewAudio() // 是否可播放音频 true or false
//                .isCamera()// 是否显示拍照按钮 true or false
//                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
//                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
//                .enableCrop()// 是否裁剪 true or false
//                .compress()// 是否压缩 true or false
//                .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                .hideBottomControls()// 是否显示uCrop工具栏，默认不显示 true or false
//                .isGif()// 是否显示gif图片 true or false
//                .compressSavePath(getPath())//压缩图片保存地址
//                .freeStyleCropEnabled()// 裁剪框是否可拖拽 true or false
//                .circleDimmedLayer()// 是否圆形裁剪 true or false
//                .showCropFrame()// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
//                .showCropGrid()// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
//                .openClickSound()// 是否开启点击声音 true or false
//                .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
//                .previewEggs()// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                .cropCompressQuality()// 裁剪压缩质量 默认90 int
//                .minimumCompressSize(100)// 小于100kb的图片不压缩
//                .synOrAsy(true)//同步true或异步false 压缩 默认同步
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
//                .rotateEnabled() // 裁剪是否可旋转图片 true or false
//                .scaleEnabled()// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
//                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
//                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
//                .recordVideoSecond()//视频秒数录制 默认60s int
//                .isDragFrame(false)// 是否可拖动裁剪框(固定)
//                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//        缓存清除
//        //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
//        PictureFileUtils.deleteCacheDirFile(MainActivity.this);
//
//        主题配置
//                <!--默认样式 注意* 样式只可修改，不能删除任何一项 否则报错-->
//    <style name="picture.default.style" parent="Theme.AppCompat.Light.DarkActionBar">
//        <!-- Customize your theme here. -->
//        <!--标题栏背景色-->
//        <item name="colorPrimary">@color/bar_grey</item>
//        <!--状态栏背景色-->
//        <item name="colorPrimaryDark">@color/bar_grey</item>
//        <!--是否改变图片列表界面状态栏字体颜色为黑色-->
//        <item name="picture.statusFontColor">false</item>
//        <!--返回键图标-->
//        <item name="picture.leftBack.icon">@drawable/picture_back</item>
//        <!--标题下拉箭头-->
//        <item name="picture.arrow_down.icon">@drawable/arrow_down</item>
//        <!--标题上拉箭头-->
//        <item name="picture.arrow_up.icon">@drawable/arrow_up</item>
//        <!--标题文字颜色-->
//        <item name="picture.title.textColor">@color/white</item>
//        <!--标题栏右边文字-->
//        <item name="picture.right.textColor">@color/white</item>
//        <!--图片列表勾选样式-->
//        <item name="picture.checked.style">@drawable/checkbox_selector</item>
//        <!--开启图片列表勾选数字模式-->
//        <item name="picture.style.checkNumMode">false</item>
//        <!--选择图片样式0/9-->
//        <item name="picture.style.numComplete">false</item>
//        <!--图片列表底部背景色-->
//        <item name="picture.bottom.bg">@color/color_fa</item>
//        <!--图片列表预览文字颜色-->
//        <item name="picture.preview.textColor">@color/tab_color_true</item>
//        <!--图片列表已完成文字颜色-->
//        <item name="picture.complete.textColor">@color/tab_color_true</item>
//        <!--图片已选数量圆点背景色-->
//        <item name="picture.num.style">@drawable/num_oval</item>
//        <!--预览界面标题文字颜色-->
//        <item name="picture.ac_preview.title.textColor">@color/white</item>
//        <!--预览界面已完成文字颜色-->
//        <item name="picture.ac_preview.complete.textColor">@color/tab_color_true</item>
//        <!--预览界面标题栏背景色-->
//        <item name="picture.ac_preview.title.bg">@color/bar_grey</item>
//        <!--预览界面底部背景色-->
//        <item name="picture.ac_preview.bottom.bg">@color/bar_grey_90</item>
//        <!--预览界面返回箭头-->
//        <item name="picture.preview.leftBack.icon">@drawable/picture_back</item>
//        <!--是否改变预览界面状态栏字体颜色为黑色-->
//        <item name="picture.preview.statusFontColor">false</item>
//        <!--裁剪页面标题背景色-->
//        <item name="picture.crop.toolbar.bg">@color/bar_grey</item>
//        <!--裁剪页面状态栏颜色-->
//        <item name="picture.crop.status.color">@color/bar_grey</item>
//        <!--裁剪页面标题文字颜色-->
//        <item name="picture.crop.title.color">@color/white</item>
//        <!--相册文件夹列表选中图标-->
//        <item name="picture.folder_checked_dot">@drawable/orange_oval</item>
//    </style>
//
//                常用功能
//        启动相册并拍照
//
//        PictureSelector.create(MainActivity.this)
//                .openGallery(PictureMimeType.ofImage())
//                .forResult(PictureConfig.CHOOSE_REQUEST);
//
//        单独启动拍照或视频 根据PictureMimeType自动识别
//
//        PictureSelector.create(MainActivity.this)
//                .openCamera(PictureMimeType.ofImage())
//                .forResult(PictureConfig.CHOOSE_REQUEST);
//        预览图片
//
//// 预览图片 可自定长按保存路径
//                *注意 .themeStyle(themeId)；不可少，否则闪退...
//
//        PictureSelector.create(MainActivity.this).themeStyle(themeId).openExternalPreview(position, "/custom_file", selectList);
//        PictureSelector.create(MainActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
//
//        预览视频
//
//        PictureSelector.create(MainActivity.this).externalPictureVideo(video_path);
//
//        结果回调
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            if (resultCode == RESULT_OK) {
//                switch (requestCode) {
//                    case PictureConfig.CHOOSE_REQUEST:
//                        // 图片、视频、音频选择结果回调
//                        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
//                        // 例如 LocalMedia 里面返回三种path
//                        // 1.media.getPath(); 为原图path
//                        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
//                        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
//                        // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                        // 4.media.getAndroidQToPath();为Android Q版本特有返回的字段，此字段有值就用来做上传使用
//                        adapter.setList(selectList);
//                        adapter.notifyDataSetChanged();
//                        break;
//                }
//            }
//        }
//
//    }
//
//}
