```kotlin
import kotlin.math.PI
import kotlin.math.sqrt

fun main(){

    val squareCabin = SquareCabin(6, 50.0) // 거주민이 6명인 squareCabin 인스턴스
    with(squareCabin) {
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
    }

    val roundHut = RoundHut(3, 10.0)
    with(roundHut) {
        println("\nRound Hut\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Carpet size: ${calculateMaxCarpetSize()}")
    }

    val roundTower = RoundTower(4, 15.5)
    with(roundTower) {
        println("\nRound Tower\n==========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
    }
}

abstract class Dwelling(private var residents: Int){

    abstract val buildingMaterial: String
    abstract val capacity: Int

    fun hasRoom(): Boolean {
        return residents < capacity
    }

    abstract fun floorArea(): Double // 바닥 면적 계산

    // 수용 인원이 남은 경우 거주자를 추가하는 함수
    fun getRoom() {
        if (capacity > residents) {
            residents++
            println("You got a room!")
        } else {
            println("Sorry, at capacity and no rooms left.")
        }
    }
}

// 네모난 집 서브 클래스
class SquareCabin(
    residents: Int,
    val length: Double): Dwelling(residents){

    override val buildingMaterial = "Wood"
    override val capacity = 6
    override fun floorArea(): Double{
        return length * length // 바닥 면적 반환
    }
}

// 둥근 오두막 서브 클래스
open class RoundHut(
    residents: Int,
    val radius: Double): Dwelling(residents){

    override val buildingMaterial = "Straw"
    override val capacity = 4
    override fun floorArea(): Double {
        return PI * radius * radius
    }
    
    //카펫 면적 만추기
    fun calculateMaxCarpetSize(): Double {
        val diameter = 2 * radius
        return sqrt(diameter * diameter / 2)
    }
}

// 둥근 타워 서브 클래스
class RoundTower(
    residents: Int,
    radius: Double,
    val floors: Int = 2) : RoundHut(residents, radius) {

    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors
    override fun floorArea(): Double {
        return super.floorArea()*floors
    }
}
```