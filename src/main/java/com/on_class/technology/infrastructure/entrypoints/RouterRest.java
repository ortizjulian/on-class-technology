package com.on_class.technology.infrastructure.entrypoints;

import com.on_class.technology.infrastructure.entrypoints.documentation.TechnologyApiInfo;
import com.on_class.technology.infrastructure.entrypoints.handler.TechnologyHandler;
import com.on_class.technology.infrastructure.utils.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    @TechnologyApiInfo
    public RouterFunction<ServerResponse> routerFunction(TechnologyHandler technologyHandler) {
        return nest(path(Constants.ROUTE_TECHNOLOGY),
                route(POST(Constants.ROUTE_EMPTY), technologyHandler::createTechnology)
                        .andRoute(POST(Constants.ROUTE_TECHNOLOGY_LINK_CAPACITIES), technologyHandler::linkCapacities)
        );}
}
