package com.quangduong.SE330backend;

import com.quangduong.SE330backend.mapper.UserMapper;
import com.quangduong.SE330backend.repository.elasticsearch.UserModelRepository;
import com.quangduong.SE330backend.repository.sql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestController
public class Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserModelRepository userModelRepository;

	@GetMapping("greeting")
	public String greeting() {
		return "Hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		userRepository.findAll().forEach(u -> userModelRepository.save(userMapper.toUserModel(u)));
	}
}
