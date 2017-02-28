# 简介
支持ios多台设备并行执行相同的case，也支持ios和android并行，或者android多台并行。使用testng控制并发，报告添加截图。

# macaca多台ios真机设备并行 iproxy设置存在bug，多个设备都会使用8900端口，无法正常判断端口是否可用

此版本需要修改macaca-ios代码，初始化ios真机driver时候可以指定iproxy端口

替换原本中的,即可使用
```
macaca-ios.js
```
## 初始化时候传入代理端口

```
        porps.put("proxyPort", Integer.parseInt(proxyport));

```
