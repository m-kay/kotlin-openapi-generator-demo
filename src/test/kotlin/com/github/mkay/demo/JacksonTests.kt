package com.github.mkay.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.mkay.demo.model.UserInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertNotNull

@SpringBootTest
class JacksonTests {
    @Autowired
    private lateinit var objectMapper: ObjectMapper


    @Test
    fun `serialize object with property eMailAddress`() {
        assertThat(objectMapper.writeValueAsString(UserInfo("tom@tester.com"))).contains("eMailAddress")
    }

    @Test
    fun `serialize object with property eMailAddress as emailAddress`() {
        assertThat(objectMapper.writeValueAsString(UserInfo("tom@tester.com"))).contains("emailAddress")
    }

    @Test
    fun `deserialize json with property eMailAddress`() {
        val userInfo = objectMapper.readValue("{\"eMailAddress\":\"tom@tester.com\"}", UserInfo::class.java)
        assertNotNull(userInfo)
        assertThat(userInfo.eMailAddress).isEqualTo("tom@tester.com")
    }
}