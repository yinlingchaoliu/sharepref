# sharepref
简单sharepref存储工具，支持多sharepref表使用

### 功能
 * 支持参数可配置化
 * 支持多种数据类型任意object list map
 * 支持原有sp数据存储 getObj putObj
 * 支持数据迁移
 * 可扩展性强，后续可支持加密
 * 支持数据异步提交用apply并且兼容
 * 支持多sharepref文件
 * 支持项目可拆分多部分 动态配置, 基本库(sp+注解)

### 引入
1.引仓库
```
maven { url 'https://jitpack.io' }
```
2.加依赖
```
implementation 'com.github.yinlingchaoliu:sharepref:1.0.0'
```

### 用法
#### 1、增加注解配置
```
/**
 * 初始化spname
 * 一个spname 映射 一个table
 */
@SPNAME("sharepref_table") //sharepref文件名
public interface ISharedPref {

    @KEY("username") //存储的key值
    @DEFAULT("")    //默认值
    Call<String> username(); //支持数据类型 String

    @KEY("isOk")
    @DEFAULT("false")
    Call<Boolean> isOk();//支持数据类型 boolean

    @KEY("age")
    @DEFAULT("0")
    Call<Integer> age();//支持数据类型 Integer

    @KEY("waitTime")
    @DEFAULT("0")
    Call<Long> waitTime();//支持数据类型 Long

}
```
#### 2、继承SharePrefFacade，如下实现即可

```
public class SharePrefManager extends SharePrefFacade {

    private ISharedPref mISharedPrefDelegate;

    @Override
    public void create() {
        mISharedPrefDelegate = create(ISharedPref.class);
    }

    public static ISharedPref ISharedPref() {
        return getIns().mISharedPrefDelegate;
    }
    
    private SharePrefManager() {
    }

    private static class SingleHolder {
        private static final SharePrefManager sharePrefManager = new SharePrefManager();
    }

    public static SharePrefManager getIns() {
        return SingleHolder.sharePrefManager;
    }

} 
```

#### 3、application初始化

```
SharePrefManager.getIns().init(this);
```

#### 4、使用
1. 存储

```
SharePrefManager.ISharedPref().username().put(name);
```    
2. 取值

```
String name = SharePrefManager.ISharedPref().username().get();
```

#### 感谢
参考：
https://github.com/orhanobut/hawk
