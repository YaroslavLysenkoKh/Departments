import $ from "jquery";
import {URL} from "@/components/Constant";
import DepartmentForm from "@/components/render/DepartmentForm";

export function addOrUpdate(id, name) {
    $.ajax({
        type: 'POST',
        url: URL + '/department/add',
        data: {departmentId: id, name: name},
        dataType: "json",
        success: () => new DepartmentForm().doRequest(),
        error: e => {

        }
    });

}