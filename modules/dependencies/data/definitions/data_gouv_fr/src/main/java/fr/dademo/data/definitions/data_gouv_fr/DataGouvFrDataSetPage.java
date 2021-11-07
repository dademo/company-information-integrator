package fr.dademo.data.definitions.data_gouv_fr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataGouvFrDataSetPage {

    @Nonnull
    private List<DataGouvFrDataSet> data;

    @Nonnull
    private Integer page;

    @Nonnull
    private Integer pageSize;

    @Nonnull
    private Integer total;

    @Nullable
    private String nextPage;

    @Nullable
    private String previousPage;
}
