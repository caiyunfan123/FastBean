# FastBean

### version 1.0

case class Bean(
var a:Int = 0
var b:String = null
var c:Double = 0
) 

main{
val str = "123,abc,2.2"

val bean =FastBean.build(Bean(),str.split(","))

}

备注：
1.带有空参构造器的普通class类可以不给属性默认值，否则必须如上给个默认值。
2.属性必须都是可变的var
3.支持除char外的七个基本类型和String
4.当字段不匹配时，会抛出异常，并提示出错的数据所在的位置和对应的字段名

### version 2.0

##### 新增

buildOrElse方法：传入一个默认值，当属性注入失败时会返回该默认值，可用于后续filter过滤

lengthEqulas方法：比较对象参数与所给参数的参数个数是否一致

typeEqulas方法：除了比较个数以外，还会比较每个类型是否一致

##### 更新

build方法现在会详细提示注入失败的完整数据以及失败的参数所在位置

API均大幅度改动，原1.0版本弃用。

