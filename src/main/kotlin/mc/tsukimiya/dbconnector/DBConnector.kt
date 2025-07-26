package mc.tsukimiya.dbconnector

import org.bukkit.plugin.java.JavaPlugin

class DBConnector : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()

        try {
            var url = ""
            var driver = ""
            var user = ""
            var password = ""
            when (config.getString("type")) {
                "sqlite" -> {
                    url = "jdbc:sqlite:${config.getString("sqlite.file")}"
                    driver = org.sqlite.JDBC::class.java.name
                }

                "mysql" -> {
                    url = "jdbc:mysql://${config.getString("mysql.url")}/${config.getString("mysql.name")}"
                    driver = com.mysql.jdbc.Driver::class.java.name
                    user = config.getString("mysql.user", "")!!
                    password = config.getString("mysql.password", "")!!
                }
            }
            Database.connect(url, driver, user, password)
        } catch (e: Exception) {
            logger.warning { e.stackTraceToString() }
            logger.warning { "データベースに接続できないため、プラグインを無効化します。" }
            server.pluginManager.disablePlugin(this)
        }
    }

    override fun onDisable() {
        Database.close()
    }
}
