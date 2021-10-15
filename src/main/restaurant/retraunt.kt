package main.restaurant


open class Person(val id: String, val name: String, val surname: String, var age: Int) {

}

open class Customer(id: String, name: String, surname: String, age: Int, val adress: String, val phone: String) :
    Person(id, name, surname, age) {
    var orderId:Int=1
    fun makeOrder(list: MutableList<Food>,cook: Cook):Order {
        orderId++
        return Order(orderId, list,cook)
    }
    fun cancelOrder() {
println("Заказ отменён")
    }
    fun orderReceived() {
        println("Заказ успешно доставлен")

    }
}

class Cook(id: String, name: String, surname: String, age: Int, var ordersPermonth: Int) :
    Person(id, name, surname, age) {
    fun salary():Int
    {
        return ordersPermonth*10
    }
    fun takeOrder(){
        ordersPermonth++
    }
}

open class Food(val name: String, val price: Int)
class Pizza(name: String, price: Int, val size: Int) : Food(name, price)
class Drinks(name: String, price: Int, val volume: Int, val alcohol: Boolean) : Food(name, price)
class Cakes(name: String, price: Int, val weight: Int) : Food(name, price)
class Order(val id: Int, val list: MutableList<Food>,val cook:Cook){
    init {
        cook.takeOrder()
    }
    fun totalPrice():Int{
        var sum=0
        for (i in list){
            sum=sum+i.price
        }
        return sum
    }
}
fun main () {
    val cook1= Cook("1","Pety", "Nilson",23,5)
    val margarita=Pizza("Margarita",80,25)
    val neopoletan=Pizza("Neopoletan",110,25)
    val bavarian=Pizza("Bavarian",130,30)
    val coke=Drinks("Coca-cola",15,250,false)
    val maffin1=Cakes("Choklate maffin",10,80)
    val customer1=Customer("1","Ivan","Sidorov",16,"Cherkasy, Budgoshska 128","380963456789")
    val order1=customer1.makeOrder(mutableListOf(neopoletan,coke),cook1)
    println(order1.totalPrice())
    println(cook1.salary())
}
