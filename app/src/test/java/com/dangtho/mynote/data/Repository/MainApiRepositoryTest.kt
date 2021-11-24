package com.dangtho.mynote.data.Repository

import com.dangtho.mynote.data.model.LoginResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MainApiRepositoryTest {
    @Mock
    private lateinit var mainApiRepository: MainApiRepository

    private fun createResponseLogin(): Response<LoginResponse> {
        val loginResponse = LoginResponse()
        loginResponse.token = "123kgfmskdkfnddjfd"
        return Response.success(loginResponse)
    }

    @Test
    fun testResponseLogin() {
        runBlocking {
            // give
            val response = createResponseLogin()
            // when
            `when`(
                mainApiRepository.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
            )
                .thenReturn(response)
            mainApiRepository.login("a", "").run {
                // then
                assertEquals(true, isSuccessful)
                assertEquals("123kgfmskdkfnddjfd", body()?.token)
            }
        }
    }
}