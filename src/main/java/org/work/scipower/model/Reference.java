package org.work.scipower.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reference {

    private ParseDocument document;
    private ParseDocument reference;

}

