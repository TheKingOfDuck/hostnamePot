# About
随手写的几年前想做但没有实现的玩具。
# What
通过Web获取访客机器的hostname字段内容。大概可以应用于蜜罐方面，作为采集攻击者信息的参考点之一，毕竟遇到401谁不会顺手输个admin/admin试试呢。
# How
服务端要求浏览器走NTLM认证时浏览器携带的认证信息中即包含了机器的hostname字段。

# Run

release: [hostnamePot.jar](https://github.com/TheKingOfDuck/hostnamePot/releases/download/demo/hostnamePot.jar)

```
java -jar hostnamePot.jar --server.port=18848 #不加参数默认端口端口为80
```

![](https://github.com/TheKingOfDuck/hostnamePot/blob/main/images/hostnamePot.jpg)
