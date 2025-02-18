package com.project.module.bo.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
@Getter
@Schema(description = "Test Model")
public class TestModel {

    @Schema(description = "zone Id")
    private String zoneId;
}
