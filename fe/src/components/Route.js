import {MainRender} from "@/components/render/Main";
import DepartmentForm from "@/components/render/DepartmentForm";

export default class Route {


    constructor() {
        window.addEventListener("hashchange", e => this.changeRoute(e));
        this.routeMap = new Map([
            ["/", new MainRender()],
            ["/department/edit", new DepartmentForm()]
        ]);
    }

    changeRoute(e) {
        let hashLocation = window.location.hash.substring(1).split('?');
        let id = hashLocation[1].charAt(hashLocation[1].length - 1);
        this.routeMap.get(hashLocation[0]).render(id);
    }


}


