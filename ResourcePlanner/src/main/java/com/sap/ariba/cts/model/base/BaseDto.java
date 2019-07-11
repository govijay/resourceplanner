package com.sap.ariba.cts.model.base;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDto {

  @NotEmpty
  @JsonProperty("id")
  String baseId;

  @JsonProperty("active")
  boolean isActive;

  public BaseDto() {
  }

  public BaseDto(String baseId, boolean isActive) {
    this.baseId = baseId;
    this.isActive = isActive;
  }

  public BaseDto(boolean isActive) {
    this.isActive = isActive;
  }

  public String getBaseId() {
    return baseId;
  }

  public void setBaseId(String baseId) {
    this.baseId = baseId;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }
}
