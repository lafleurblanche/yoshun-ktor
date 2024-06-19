package net.konohana.sakuya.yoshun.db

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object KaedeDatabaseFactory {
    fun initKaedeDB(url: String, username: String, password: String) = Database.connect(
        url = url,
        driver = "org.postgresql.Driver",
        user = username,
        password = password
    )

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block()}
}
