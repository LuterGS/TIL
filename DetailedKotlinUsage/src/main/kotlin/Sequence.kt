import kotlin.math.ceil
import kotlin.math.sqrt


class Sequence {


    class BasicSequence{
        fun makeSequence(max: Int){
            // generateSequence 는 nextFunction 이 null 을 return 할 때까지 반복해서 만든다. 만약 null 이 안나오면 infiniteSequence 가 됨.
            val result = generateSequence(2) {
                if (it == max) null
                else it + 1
            }
        }
    }
    val basicSequence = BasicSequence()


    class InfiniteSequence{
        private fun Int.isPrime() =
            this == 2 || (2..ceil(sqrt(this.toDouble())).toInt()).none { divisor -> this % divisor == 0 }

        private fun nextPrime(num: Int) =
            generateSequence(num + 1) { it + 1}
                .first { it.isPrime() }

        // infinite sequence
        fun firstNPrimes(count: Int) =
            generateSequence(2) { nextPrime(it) }
                .take(count)
                .toList()
    }
    val infiniteSequence = InfiniteSequence()


    class SequenceYield{
        val fibonacciSequence = sequence<Int> {
            var currentPoint = 0 to 1

            while(true){
                // yield 를 할때마다 sequence 에서 값을 발행함
                yield(currentPoint.first)
                currentPoint = currentPoint.second to (currentPoint.first + currentPoint.second)
            }
        }
    }
    val sequenceYield = SequenceYield()


}


fun sequence(){

    val seq = Sequence()
    val primeResult = seq.infiniteSequence.firstNPrimes(10)
    println(primeResult)
}

