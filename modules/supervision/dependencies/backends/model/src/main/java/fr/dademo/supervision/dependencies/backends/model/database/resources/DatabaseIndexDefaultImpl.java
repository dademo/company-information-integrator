/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package fr.dademo.supervision.dependencies.backends.model.database.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * @author dademo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatabaseIndexDefaultImpl implements DatabaseIndex {

    @Nonnull
    @Size(min = 1, max = 255)
    private String name;

    @Nonnull
    @Size(min = 1, max = 255)
    private String tableName;

    @Nullable
    @Min(0)
    private Long indexScansCount;

    @Nullable
    @Min(0)
    private Long returnedRowsCount;

    @Nullable
    @Min(0)
    private Long fetchedRowsCount;
}
