package aplication

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

fun main() {

    data class User(
        val name: String,
        val email: String,
        val id: Int) {
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
            1 to User(name = "Aviaras", email = "a11viaras23@gmail.com", id = 1),
            2 to User(name = "Avlis", email = "avlis23@gmail.com", id = 2),
            3 to User(name = "Silva", email = "silva23@gmail.com", id = 3)
        )

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


    val userDao = UserDao()

    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start(8090)

    app.routes {

        get("/users") { ctx ->
            ctx.json(userDao.users)
        }

        get("/users/{users-id}") { ctx ->
            ctx.json(userDao.findById(ctx.pathParam("users-id").toInt())!!)
        }

        get("/users/email/{email}") { ctx ->
            ctx.json(userDao.findByEmail(ctx.pathParam("email"))!!)
        }

        post("/users") { ctx ->
            val user = ctx.bodyAsClass<User>()
            userDao.update(
                id = ctx.pathParam("users-id").toInt(),
                user = user
            )
            ctx.status(204)
        }
        patch("/users/{user-id}") { ctx ->
            val user = ctx.bodyAsClass<User>()
            userDao.update(
                id = ctx.pathParam("user-id").toInt(),
                user = user
            )
            ctx.status(204)
        }

        delete("/users/{users-id}") { ctx ->
            userDao.delete(ctx.pathParam("users-id").toInt())
            ctx.status(204)
        }
    }

}






