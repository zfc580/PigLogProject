### 悬浮日志面板 **Pig**
> 实现一个`APP`内悬浮窗口可实时打印日志,项目名称：**Pig**

#### 开发要点
1. 悬浮窗口在`APP`内任意页面切换都存在
2. 退出APP或者`Home`、`Power`键出去悬浮窗消失
3. 可便捷打印日志，如 `Pig.D(TAG,"Here is a Message.");`
4. 要求可在`APP`中配置开启与关闭
5. 点击面板支持收缩与展开，默认状态下处于收缩状态
6. 要求可任意拖动
7. 要求支持的系统版本是 `Android4.4+`,最好能做到无权限提示,无系统兼容性问题(如 **小米系列** 、**部分Android8.0** )
8. 要求无闪退、无掉帧、无内存泄漏
9. 代码需提交至`Gitlab`,每完成一个功能点，并填写`Commit`
10. 要求按照项目开发规范书写代码、按照 **Git提交规范** 提交代码

#### 参考图片
![悬浮窗](image/pig.gif)

### 1 简介
**Pig是一个log打印工具，提供一个悬浮窗来展示打印的log，悬浮窗可在屏幕内自由拖动，在项目测试时方便查看关键的log信息。**
### 2 快速集成
* **Gradle依赖**

Pig组件库通过aar形式存在美图maven仓库，关于如何配置美图私有maven仓库，请[直戳](http://techgit.meitu.com/android/MavenWiki/wikis/maven_config)。
对应在主工程下的build.gradle引入pig的库：

```groovy
dependencies {
    ...
    compile "com.meitu.library:pig:1.0.0-SNAPSHOT"
    ...
}
```
* **调用示例**
    * 在应用初始化的时候调用Pig组件的初始化接口Pig.init()，如
    ```java
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Pig.init(this);
        }
    ```
    * 在应用退出时调用Pig组件的反初始化接口Pig.unInit(),如
    ```java
        @Override
        protected void onDestroy() {
            super.onDestroy();
            Pig.unInit();
        }
    ```
    * 在应用需要打印log的时候调用打印log接口Pig.D()，每调用一次就在悬浮窗打印一行的log, **如果要在一行常驻需要加上":"** 如
    ```java
       int i = 0;
       Pig.D("码率："+i); //参数里面含有":"，不管调用几次，悬浮窗只会打印一行log，数字i会在该行LOG中实时更新；
       Pig.D("Hello World."); //参数里面不含有":"，每调用一次，参数不同的话就会另起一行；
    ```
    * 设置Pig组件log最多可打印的行数可调用Pig.setLineNum()，如
    ```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pig.init(this);
        Pig.setLineNum(10); //设置打印行数
    }
    ```
# 1.0.1 更新说明
* [优化]悬浮窗贴边逻辑以悬浮窗宽度的中心点作为边界，拖动到屏幕宽度中心点左边就贴在左边，否则右边；
* [优化]清除组件库中无用的资源文件；
# 1.0.2 更新说明
* [优化]去掉有tag参数的接口: public static void D(String tag, String msg);
* [优化]修改打印的log中含有换行符导致打印行数超过指定行数的问题；
# 1.0.3 更新说明
* [优化]打印的每一行LOG中，只要":"前面的字符串不变，就在该行更新，不会另起一行；
> 如，"拍摄时长：x"这一行log，连续调用Pig.D("拍摄时长：5")和Pig.D("拍摄时长：10")不会打印两行，而会在
同一行10会替换掉5；
# 1.0.4 更新说明
* [优化]修复一些bug；
# 1.0.5 更新说明
* [优化]修改悬浮按钮颜色；
* [优化]改变悬浮按钮初始位置；