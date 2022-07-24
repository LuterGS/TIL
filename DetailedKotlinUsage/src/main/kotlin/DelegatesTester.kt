import kotlin.properties.Delegates

class DelegatesTester {

    class ObservableTester{
        private var watchedValue: Int by Delegates.observable(0) {property, oldValue, newValue ->
            println("${property.name} 이 $oldValue 에서 $newValue 로 바뀌었습니다.")
        }

        private var checkedValue: Int by Delegates.vetoable(0) {property, oldValue, newValue ->
            println("${property.name} 의 값을 $oldValue 에서 $newValue 로 바꿉니다.")
            if (newValue == 3){
                println("바뀌는 값이 3입니다. 값을 바꾸지 않습니다.")
                false
            }else true
        }

        fun changeWatchedValue(value: Int){ watchedValue = value }
        fun changeCheckedValue(value: Int){ checkedValue = value }
    }
    val observableTester = ObservableTester()


    class DelegateAsMap(map: Map<String, Any?>){
        private val name: String by map
        private val age: Int by map

        fun testInitiated(){ println("name is $name, age is $age") }
    }
    fun initDelegate(map: Map<String, Any?>) = DelegateAsMap(map)
}


fun delegates(){
    val delegatesTester = DelegatesTester().also {
        it.observableTester.changeWatchedValue(3)
        it.observableTester.changeCheckedValue(2)
        it.observableTester.changeCheckedValue(3)
    }

    delegatesTester.initDelegate(mapOf("name" to "hi", "age" to 12)).let {
        // 만약 자료형에 맞지 않는 값을 넣으면 Exception 발생
        it.testInitiated()
    }
}