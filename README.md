# FastBean

case class Bean(
var a:Int = 0
var b:String = null
var c:Double = 0
<<<<<<< HEAD
) 

main{
val str = "123,abc,2.2"
val bean =FastBean.build(Bean())(str.split(","))
=======
) extends(或with) FastBean

main{
val str = "123,abc,2.2"
val bean = Bean().build(str.split(","))
>>>>>>> remotes/origin/master
}

备注：
1.带有空参构造器的普通class类可以不给属性默认值，否则必须如上给个默认值。
2.属性必须都是可变的var
3.支持除char外的七个基本类型和String
4.当字段不匹配时，会抛出异常，并提示出错的数据所在的位置和对应的字段名