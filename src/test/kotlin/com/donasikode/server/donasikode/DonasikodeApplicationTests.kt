package com.donasikode.server.donasikode

import com.donasikode.server.donasikode.controllers.LoginData
import com.donasikode.server.donasikode.controllers.RegistrationData
import com.donasikode.server.donasikode.initializer.Crypton
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DonasikodeApplicationTests {

	@Test
	fun `encrypt data`() {
		val crypton = Crypton()
		val data = Gson().toJson(RegistrationData(
			"arata",
			"arata123",
			"arata@gmail.com",
			"Muhammad arata",
			"3874973492"
		))
		val encrypted = crypton.encrypt(data, "demo")
		println(encrypted)
		println()

		val decrypted = crypton.decrypt(encrypted, "demo")
		println(decrypted)

		assert(decrypted == data)
	}

	@Test
	fun `login data`() {
		val crypton = Crypton()
		val data = Gson().toJson(LoginData(
			username = "arata",
			password = "arata123"
		))
		val encrypted = crypton.encrypt(data, "demo")

		println()
		println()
		println(encrypted)
		println()
		println()
	}

}
