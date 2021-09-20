/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hascode.jolt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *
 * @author Tiago Santos / 1140927
 */
@JsonSerialize(JsonSerialize.Inclusion.NON_NULL)
public class OLRSchema{
    public String cliID;
    public String description;
    public String short_des;
    public String address;
    public String sysCreatedBy;
}

/**
 * Default list of fields for OLR JSON Schema
 */

enum olrSchemaKeys {
    cliID, description, short_des, address, sysCreatedBy;
}
