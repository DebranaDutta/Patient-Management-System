package com.doc.payload;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ApiResponse", description = "Standard response returned when no specific data is found")
public class ApiResponse {

    @Schema(
        description = "Message describing why no data was returned",
        example = "Doctor with given ID not found"
    )
    private String message;

    @Schema(
        description = "Indicates whether the operation was successful",
        example = "false"
    )
    private boolean success;

    @Schema(
        description = "HTTP status code of the response",
        example = "404 NOT_FOUND"
    )
    private HttpStatus status;
}