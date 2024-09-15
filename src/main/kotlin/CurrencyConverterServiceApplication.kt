import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
internal class CurrencyConverterServiceApplication

fun main(args: Array<String>) {
    runApplication<CurrencyConverterServiceApplication>(*args)
}
