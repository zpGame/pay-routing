# pay-routing

用户在业务端请求支付时，需要选择一种支付方式，就是支付路由返回支付通道

##设计目的

支付路由在支付系统中的承担如下职责：

1. 路由配置根据通道成本———省钱
2. 通道维护关闭路由可控———可用性。
3. 支持营销；降低运营成本。

## 路由架构图

![avatar](/images/路由设计.png)

## 路由主流程

![avatar](/images/路由主流程.png)

## 路由侧规则

> 1.渠道类型 -- 代收、代付、快捷支付等

> 2.卡类型 -- 信用卡、借记卡

> 3.账户类型 -- 对公、对私

> 4.交易限额 -- 单笔限额、日限额等

> 5.通道开通时间

> 6.费率 -- 单笔费率、阶梯费率等

## 商户测规则

to be continuing ...