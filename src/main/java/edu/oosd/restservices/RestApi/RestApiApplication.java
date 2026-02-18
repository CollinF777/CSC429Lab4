package edu.oosd.restservices.RestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}

class Greeting {
	final private long id;
	final private String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return this.id;
	}
	public String getContent() {
		return this.content;
	}
}

class Routes {
	private List<String> routeList;

	public Routes(RequestMappingHandlerMapping handlerMapping) {
		this.routeList = Routes.getRouteList(handlerMapping);
	}

	public List<String> getRoutes() {
		return routeList;
	}

	// Auto assemble list of routes
	public static List<String> getRouteList(RequestMappingHandlerMapping handlerMapping) {
		return handlerMapping.getHandlerMethods().keySet().stream()
				.flatMap(info -> new HashSet<>(info.getDirectPaths()).stream())
				.filter(path -> !path.startsWith("/error"))
				.toList();
	}
}