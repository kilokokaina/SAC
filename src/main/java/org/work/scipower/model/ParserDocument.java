package org.work.scipower.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
public class ParserDocument implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Map<String, String> documentValues;

    @Override
    public String toString() {
        var result = new StringBuilder();

        for (var entry : documentValues.entrySet()) {
            result.append(entry.getValue()).append(", ");
        }

        return result.toString();
    }

}
