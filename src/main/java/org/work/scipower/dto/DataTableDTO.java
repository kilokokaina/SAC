package org.work.scipower.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.work.scipower.model.ParseDocument;

@Data
@NoArgsConstructor
public class DataTableDTO {

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private ParseDocument[] data;
    private String error;

}
