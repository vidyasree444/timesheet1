package org.imaginnovate.Dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "parentId", "createdBy", "createdOn", "modifiedBy", "modifiedOn", "deletedBy",
        "deletedOn" })
public class DivisionDto extends BaseAuditFieldsDTO {

    private Integer id;
    private String name;
    private Integer parentId;

    public DivisionDto() {
    }

    public DivisionDto(Integer id2, String name2, Integer parentId2, Integer createdBy, LocalDateTime createdOn,
            Integer modifiedBy, LocalDateTime modifiedOn, Integer deletedBy, LocalDateTime deletedOn) {
        super(createdBy, createdOn, modifiedBy, modifiedOn, deletedBy, deletedOn);
        this.id = id2;
        this.name = name2;
        this.parentId = parentId2;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public DivisionDto id(Integer id) {
        setId(id);
        return this;
    }

    public DivisionDto name(String name) {
        setName(name);
        return this;
    }

    public DivisionDto parentId(Integer parentId) {
        setParentId(parentId);
        return this;
    }

    @Override
    public String toString() {
        return "DivisionDto [id=" + id + ", name=" + name + ", parentId=" + parentId + "]" + "," + super.toString();
    }

}
