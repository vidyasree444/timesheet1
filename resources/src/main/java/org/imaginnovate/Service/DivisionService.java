package org.imaginnovate.Service;

import java.util.List;

import org.imaginnovate.Dto.DivisionDto;
import org.imaginnovate.Entity.Division;
import org.imaginnovate.Repository.DivisionRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class DivisionService {

    @Inject
    DivisionRepo divisionsRepo;

    public Response getAllDivisions() {
        try {
            List<DivisionDto> divisions = divisionsRepo.findAllDivisions();
            if (divisions.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("There are no divisions")
                        .build();
            }
            return Response.ok(divisions).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error while fetching divisions")
                    .build();
        }
    }

    @Transactional
    public Response createDivision(DivisionDto divisionsDto) {
        try {
            if (divisionsRepo.findByName(divisionsDto.getName()) != null) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("The division already exists")
                        .build();
            }

            Division division = convertToEntity(divisionsDto);
            if (divisionsDto.getParentId() != null && divisionsDto.getParentId() != 0) {
                Division parentDivision = divisionsRepo.findById(divisionsDto.getParentId());
                if (parentDivision != null) {
                    division.setParentId(parentDivision);
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Division with ID " + divisionsDto.getParentId() + " not found")
                            .build();
                }
            }

            divisionsRepo.persist(division);
            divisionsDto.setId(division.getId());
            return Response.status(Response.Status.CREATED).entity(divisionsDto).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error while creating division")
                    .build();
        }
    }

    public Response getDivisionById(int id) {
        try {
            Division division = divisionsRepo.findById(id);
            if (division == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Division not found")
                        .build();
            }
            return Response.ok(convertToDto(division)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error while fetching division by ID")
                    .build();
        }
    }

    @Transactional
    public Response updateDivision(int id, DivisionDto divisionsDto) {
        try {
            Division division = divisionsRepo.findById(id);
            if (division == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Division not found")
                        .build();
            }

            if (divisionsDto.getName() != null && !divisionsDto.getName().isEmpty()) {
                division.setName(divisionsDto.getName());
            }

            if (divisionsDto.getParentId() != null && divisionsDto.getParentId() != 0) {
                Division parentDivision = divisionsRepo.findById(divisionsDto.getParentId());
                if (parentDivision != null) {
                    division.setParentId(parentDivision);
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Parent division with ID " + divisionsDto.getParentId() + " not found")
                            .build();
                }
            } else {
                division.setParentId(null);
            }

            divisionsRepo.persist(division);
            return Response.ok(divisionsDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error while updating division")
                    .build();
        }
    }

    @Transactional
    public Response deleteDivision(int id) {
        try {
            Division division = divisionsRepo.findById(id);
            if (division == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Division not found")
                        .build();
            } else {
                divisionsRepo.delete(division);
            }
            return Response.ok(convertToDto(division)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error while deleting division")
                    .build();
        }
    }

    private DivisionDto convertToDto(Division division) {
        return new DivisionDto(
                division.getId(),
                division.getName(),
                division.getParentId() != null ? division.getParentId().getId() : null,
                division.getCreatedBy(),
                division.getCreatedOn(),
                division.getModifiedBy(),
                division.getModifiedOn(),
                division.getDeletedBy(),
                division.getDeletedOn());
    }

    private Division convertToEntity(DivisionDto divisionsDto) {
        Division division = new Division();
        division.setId(divisionsDto.getId());
        division.setName(divisionsDto.getName());
        division.setCreatedBy(divisionsDto.getCreatedBy());
        division.setCreatedOn(divisionsDto.getCreatedOn());
        division.setModifiedBy(divisionsDto.getModifiedBy());
        division.setModifiedOn(divisionsDto.getModifiedOn());
        division.setDeletedBy(divisionsDto.getDeletedBy());
        division.setDeletedOn(divisionsDto.getDeletedOn());
        return division;
    }
}
