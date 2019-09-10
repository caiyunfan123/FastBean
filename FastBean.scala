package utils

object FastBean{
  def typeEquals[T](obj:T,arr:Array[String]):Boolean= equalsMethod(obj,arr,isEqualsAll = true)
  def lengthEqulas[T](obj:T,arr:Array[String]):Boolean= equalsMethod(obj,arr,isEqualsAll = false)

  def build[T](obj: T,arr: Array[String]): T = {
    var index = 0
    val fields = obj.getClass.getDeclaredFields
    if (fields.length != arr.length) throw new RuntimeException(s"注入的参数数量与参数总数不符${fields.length}!=${arr.length}")
    try {
      transform(obj, arr)
    }
    catch {
      case _: NumberFormatException =>
        throw new RuntimeException(
          s"${index}/${arr.length}，${arr(index)}无法正确注入${fields(index).getName}，建议检查参数是否完全对应\n" +
            s"${arr.mkString(",")}"
        )
    }
    obj
  }
  def buildOrElse[T](obj:T,arr:Array[String],orElse:T):T={
    var index = 0
    val fields = obj.getClass.getDeclaredFields
    if (fields.length != arr.length) return orElse
    try {
      transform(obj, arr)
    }
    catch {
      case _: NumberFormatException => return orElse
    }
    obj
  }

  private def equalsMethod[T](obj: T, arr: Array[String], isEqualsAll: Boolean): Boolean = {
    val fields = obj.getClass.getDeclaredFields
    if (fields.length != arr.length) return false
    if (!isEqualsAll) return true
    try {
      transform(obj, arr)
    }
    catch {
      case _: NumberFormatException => return false
    }
    true
  }

  private def transform[T](obj: T, arr: Array[String]): Unit = {
    var index = 0
    obj.getClass.getDeclaredFields.foreach(field => {
      field.setAccessible(true)
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
      index = index + 1
    })
  }
}
