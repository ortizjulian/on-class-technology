package com.on_class.technology.infrastructure.documentation;

import com.on_class.technology.infrastructure.entrypoints.dto.CapabilityTechnologiesRequestDto;
import com.on_class.technology.infrastructure.entrypoints.dto.TechnologyDto;
import com.on_class.technology.infrastructure.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@RouterOperations({
        @RouterOperation(
                method = RequestMethod.POST,
                path = Constants.ROUTE_TECHNOLOGY,
                operation = @Operation(
                        summary = "Create a new technology",
                        description = "Registers a technology with a name and description.",
                        operationId = "createTechnology",
                        tags = {"Technologies"},
                        requestBody = @RequestBody(
                                description = "TechnologyDto object with name and description",
                                required = true,
                                content = @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(
                                                implementation = TechnologyDto.class,
                                                requiredProperties = {"name", "description"}
                                        )
                                )
                        ),
                        responses = {
                                @ApiResponse(
                                        responseCode = "201",
                                        description = "Technology created successfully",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = TechnologyDto.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "400",
                                        description = "Invalid request data"
                                ),
                                @ApiResponse(
                                        responseCode = "409",
                                        description = "A technology with that name already exists"
                                )
                        }
                )
        ),
        @RouterOperation(
                method = RequestMethod.POST,
                path = Constants.ROUTE_TECHNOLOGY + Constants.ROUTE_TECHNOLOGY_LINK_CAPACITIES,
                operation = @Operation(
                        summary = "Link technologies to a capability",
                        description = "Associates a list of technology IDs with a given capability.",
                        operationId = "linkCapacities",
                        tags = {"Technologies"},
                        parameters = {
                                @Parameter(
                                        name = Constants.CAPABILITY_ID_PATH_VARIABLE,
                                        description = "The ID of the capability to link technologies to",
                                        required = true,
                                        in = ParameterIn.PATH
                                )
                        },
                        requestBody = @RequestBody(
                                description = "A list of technology IDs to be linked to the capability",
                                required = true,
                                content = @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(
                                                implementation = CapabilityTechnologiesRequestDto.class,
                                                requiredProperties = {"technologyIds"}
                                        )
                                )
                        ),
                        responses = {
                                @ApiResponse(
                                        responseCode = "201",
                                        description = "Technologies linked successfully"
                                ),
                                @ApiResponse(
                                        responseCode = "400",
                                        description = "Invalid request data"
                                ),
                                @ApiResponse(
                                        responseCode = "404",
                                        description = "One or more technology IDs not found"
                                ),
                                @ApiResponse(
                                        responseCode = "500",
                                        description = "Unexpected server error"
                                )
                        }
                )
        )
})
public @interface TechnologyApiInfo {}