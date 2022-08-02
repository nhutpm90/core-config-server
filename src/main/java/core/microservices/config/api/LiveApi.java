package core.microservices.config.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiveApi {

	@Autowired
	private Environment environment;

	@GetMapping(value = {"/", "/live-check"})
	public Map<String, String> liveCheck(@RequestParam(required = false) List<String> envs) {
		if(envs == null) {
			envs = new ArrayList<>();
		}
		envs.addAll(Arrays.asList("spring.application.name", "server.port"));
		return this.getEnvironmentConfig(new HashSet<>(envs));
	}

	public Map<String, String> getEnvironmentConfig(Set<String> envs) {
		Map<String, String> ret = envs.stream()
				.collect(Collectors.toMap(
						k -> k, 
						v -> environment.getProperty(v)));
		return ret;
	}
	
}
