package comm.util.converter.impl;

import comm.dto.DepartmentDto;
import comm.entity.Department;
import comm.util.converter.DtoConverter;
import org.apache.commons.lang3.StringUtils;

public class DepartmentConverter implements DtoConverter<DepartmentDto, Department> {
    @Override
    public Department convert(DepartmentDto model) {
        Department department = new Department();
        if (!StringUtils.isEmpty(model.getDepartmentId()))
            department.setId(Long.parseLong(model.getDepartmentId()));
        department.setName(model.getName());
        return department;
    }
}
