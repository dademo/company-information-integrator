/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package fr.dademo.supervision.service.controllers;

import fr.dademo.supervision.service.controllers.exceptions.DatabaseSchemaTableNotFoundException;
import fr.dademo.supervision.service.controllers.hal.DataBackendDatabaseSchemaTableRepresentationModelAssembler;
import fr.dademo.supervision.service.controllers.hal.DataBackendDatabaseSchemaTableStatisticsRepresentationCollectionModelAssembler;
import fr.dademo.supervision.service.controllers.hal.DataBackendDatabaseSchemaTableStatisticsRepresentationModelAssembler;
import fr.dademo.supervision.service.services.DatabaseSchemaTableService;
import fr.dademo.supervision.service.services.dto.DataBackendDatabaseSchemaTableDto;
import fr.dademo.supervision.service.services.dto.DataBackendDatabaseSchemaTableStatisticsDto;
import fr.dademo.supervision.service.services.dto.DataBackendDescriptionDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import javax.annotation.Nonnull;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * @author dademo
 */
@RestController
@RequestMapping(path = "/database-schema-table/{id:\\d+}", produces = {
    MediaType.APPLICATION_JSON_VALUE,
    MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE,
    MediaTypes.HAL_JSON_VALUE,
})
@Tag(name = "data-backend/database")
public class DataBackendDatabaseSchemaTableController implements ProblemHandling {

    @Autowired
    private DatabaseSchemaTableService databaseSchemaTableService;
    @Autowired
    private PagedResourcesAssembler<DataBackendDatabaseSchemaTableDto> databaseDescriptionDtoPagedResourcesAssembler;

    @PageableAsQueryParam
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found database schema table",
            content = {
                @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = DataBackendDescriptionDto.class))
            }),
        @ApiResponse(responseCode = "404", description = "Database schema table not found",
            content = {
                @Content(mediaType = MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE, schema = @Schema(implementation = DefaultProblem.class))
            })
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<DataBackendDatabaseSchemaTableDto> findDataBackendDatabaseSchemaTableById(@PathVariable("id") @Min(1) @Nonnull Long tableId) {

        return DataBackendDatabaseSchemaTableRepresentationModelAssembler.INSTANCE
            .toModel(
                databaseSchemaTableService.findDatabaseSchemaTableById(tableId)
                    .orElseThrow(() -> new DatabaseSchemaTableNotFoundException(tableId))
            );
    }

    @PageableAsQueryParam
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Database schema table statistics",
            content = {
                @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = DataBackendDescriptionDto.class))
            }),
        @ApiResponse(responseCode = "404", description = "Database schema table not found",
            content = {
                @Content(mediaType = MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE, schema = @Schema(implementation = DefaultProblem.class))
            })
    })
    @GetMapping("/statistics")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<DataBackendDatabaseSchemaTableStatisticsDto> findDataBackendDatabaseSchemaTableStatisticsById(
        @PathVariable("id") @Min(1) @Nonnull Long tableId,
        @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @Parameter(name = "from", description = "The minimum date range", required = true) Date from,
        @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @Parameter(name = "to", description = "The maximum date range", required = true) Date to) {

        return DataBackendDatabaseSchemaTableStatisticsRepresentationCollectionModelAssembler.of(tableId, from, to)
            .toCollectionModel(databaseSchemaTableService.findDatabaseSchemaTableStatisticsBetween(tableId, from, to));
    }

    @PageableAsQueryParam
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Latest database schema table statistic",
            content = {
                @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = DataBackendDescriptionDto.class))
            }),
        @ApiResponse(responseCode = "404", description = "Database schema table not found",
            content = {
                @Content(mediaType = MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE, schema = @Schema(implementation = DefaultProblem.class))
            })
    })
    @GetMapping("/latest-statistic")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<DataBackendDatabaseSchemaTableStatisticsDto> findLatestDataBackendDatabaseSchemaTableStatisticById(
        @PathVariable("id") @Min(1) @Nonnull Long tableId) {

        return databaseSchemaTableService.findLatestDatabaseSchemaTableStatistics(tableId)
            .map(
                DataBackendDatabaseSchemaTableStatisticsRepresentationModelAssembler
                    .of(tableId)::toModel
            )
            .orElseThrow(() -> new DatabaseSchemaTableNotFoundException(tableId));
    }
}
