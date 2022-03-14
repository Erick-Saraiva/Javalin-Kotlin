import java.util.concurrent.atomic.AtomicInteger

data class User(val name: String, val email: String, val id: Int) {

}




val users = hashMapOf(
    0 to User(name = "Erick", email = "erick23@gmail.com", id = 0),
    1 to User(name = "Aviaras", email = "aviaras23@gmail.com", id = 1),
    2 to User(name = "Avlis", email = "avlis23@gmail.com", id = 2),
    3 to User(name = "Silva", email = "silva23@gmail.com", id = 3)
)

class UserDao {

    val users = hashMapOf(
        0 to User(name = "Erick", email = "erick23@gmail.com", id = 0),
        1 to User(name = "Aviaras", email = "aviaras23@gmail.com", id = 1),
        2 to User(name = "Avlis", email = "avlis23@gmail.com", id = 2),
        3 to User(name = "Silva", email = "silva23@gmail.com", id = 3)
    )

    var lastId: AtomicInteger = AtomicInteger(users.size - 1)

    fun save(name: String, email: String) {
        val id = lastId.incrementAndGet()
        users.put(id, User(name = name, email = email, id = id))
    }

    fun findById(id: Int): User? {
        return users[id]
    }

    fun findByEmail(email: String): User? {
        return users.values.find {it.email == email}
    }

    fun update(id: Int, user: User) {
        users.put(id, User(name = user.name, email = user.email, id = id))
    }

    fun delete(id:Int) {
        users.remove(id)
    }



}