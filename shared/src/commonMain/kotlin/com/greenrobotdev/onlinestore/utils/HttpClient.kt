package com.greenrobotdev.onlinestore.utils

import com.greenrobotdev.onlinestore.data.util.APIConstants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val HttpClient = HttpClient {
  install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }

  install(Logging) { logger = Logger.SIMPLE }

  defaultRequest {
    url {
      host = APIConstants.BASE_URL
      protocol = URLProtocol.HTTPS
    }
  }
}

suspend inline fun <reified T> HttpClient.get(
  block: HttpRequestBuilder.() -> Unit = {}
): Result<T> = request {
  get { block() }
}

suspend inline fun <reified T> request(
  requester: () -> HttpResponse
): Result<T> = try {
  val httpResponse: HttpResponse = requester()
  val response: T = httpResponse.body()
  Result.success(response)
} catch (exception: ResponseException) {
  Result.failure(exception)
} catch (exception: Throwable) {
  exception.printStackTrace()
  Result.failure(exception)
}

