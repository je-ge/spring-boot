package com.jege.spring.boot.hello.world;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:以Mock方式测试Controller
 */
@SpringBootTest
public class HelloWorldControllerTest {

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
  }

  @Test
  public void getHello() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/hello1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
	.andExpect(content().string(equalTo("Hello World")));
  }

  @Test
  public void getHello2() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/hello2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
	.andExpect(content().string(equalTo("[\"A\",\"B\",\"C\"]")));
  }

}
