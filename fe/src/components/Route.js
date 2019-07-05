import {RenderDepartments} from "@/components/render/DepartmentList";
import DepartmentForm from "@/components/render/DepartmentForm";
import {EmployeeList} from "@/components/render/EmployeeList";

export default class Route {


    constructor() {
        this.routeMap = new Map([
            ["/", new RenderDepartments()],
            ["/department/edit", () => new DepartmentForm().doRequest()],
            ["/employee", () => new EmployeeList()]
        ]);
        window.addEventListener("hashchange", () => this.changeRoute());
    }

    changeRoute() {
        let id = null;
        let hashLocation = window.location.hash.substring(1).split('?');
        if (hashLocation[1]) {
            id = hashLocation[1].charAt(hashLocation[1].length - 1);
        }

        this.routeMap.get(hashLocation[0])(id);
    }
}


