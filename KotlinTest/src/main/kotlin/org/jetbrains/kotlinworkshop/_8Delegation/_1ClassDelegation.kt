
import com.kingz.kt.collcetions.Customer


interface Repository {
    fun getById(id: Int): Customer
    fun getAll(): List<Customer>
}

interface Logger {
    fun logAll()
}

class Controller(repository: Repository, logger: Logger) : Repository by repository, Logger by logger {


}