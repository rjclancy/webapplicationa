package com.ronanclancy.webapplicationA.client;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class EventClientConfig {

  @Value("${event-service-b.url}")
  private String eventServiceBUrl;

  @Bean
  public TcpClient tcpClient() {
    return TcpClient
        .create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
        .doOnConnected(connection -> {
          connection.addHandlerLast(new ReadTimeoutHandler(3000, TimeUnit.MILLISECONDS));
          connection.addHandlerLast(new WriteTimeoutHandler(3000, TimeUnit.MILLISECONDS));
        });
  }

  @Bean
  public WebClient eventServiceClient(final TcpClient tcpClient) {
    return WebClient.builder()
        .baseUrl(eventServiceBUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
        .build();
  }
}
