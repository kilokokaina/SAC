package org.work.scipower.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.work.scipower.model.ParserDocument;

@Data
@NoArgsConstructor
public class DataTableDTO {

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private ParserDocument[] data;
    private String error;

}
