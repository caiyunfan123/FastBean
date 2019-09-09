package utils

trait FastBean {
  def build(arr:Array[String]) = FastBean.build(this)(arr)

}
object FastBean{
  def build[T](obj:T)(arr:Array[String]) ={
    var index = 0
    obj.getClass.getDeclaredFields.foreach(field=>{
      field.setAccessible(true)
      try {
        field.getType.getTypeName match {
          case "java.lang.String" => field.set(obj, arr(index))
          case "int" => field.set(obj, arr(index).toInt)
          case "float" => field.set(obj, arr(index).toFloat)
          case "double" => field.set(obj, arr(index).toDouble)
          case "boolean" => field.set(obj, arr(index).toBoolean)
          case "short" => field.set(obj, arr(index).toShort)
          case "byte" => field.set(obj, arr(index).toByte)
          case _ => throw new RuntimeException("不支持的类型")
        }
      } catch{
        case a:NumberFormatException =>throw new RuntimeException(s"第${index}个参数，${arr(index)}无法正确注入${field.getName}，建议检查参数是否完全对应")
      }
      index=index+1
    })
    obj
  }
}
