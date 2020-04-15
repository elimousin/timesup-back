package com.timesup.controller

import com.timesup.dto.AddNewName
import com.timesup.dto.AddNewNameResponse
import com.timesup.utils.NameList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimesUpControllerTests(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun `Assert timesUp add_name value, content and status code`() {
        // given
        val googleName = AddNewName("google")
        val googleResponse = AddNewNameResponse("OK")

        // when
        val entity = restTemplate.postForEntity<AddNewNameResponse>("/timesup/add_name", googleName, AddNewName::class)

        // then
        assertThat(HttpStatus.OK).isEqualTo(entity.statusCode)
        assertThat(googleResponse).isEqualTo(entity.body)
        assertThat(1).isEqualTo(NameList.nameList.size)
        assertThat(googleName.newName).isEqualTo(NameList.nameList.get(0))
    }

    @Test
    fun `Assert timesUp shuffle value, content and status code`() {
        // given
        val googleNameList = listOf(AddNewName("google"), AddNewName("est"), AddNewName("la plus belle"))
        val googleResponse = AddNewNameResponse("OK")
        googleNameList.forEach {
            restTemplate.postForEntity<AddNewNameResponse>("/timesup/add_name", it, AddNewName::class)
        }

        // when
        val entity = restTemplate.getForEntity<AddNewNameResponse>("/timesup/shuffle")

        // then
        assertThat(HttpStatus.OK).isEqualTo(entity.statusCode)
        assertThat(googleResponse).isEqualTo(entity.body)
        assertThat(NameList.nameList).isNotEqualTo(googleNameList)
    }

}