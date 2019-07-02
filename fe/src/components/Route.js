import {MainRender} from "@/components/render/Main";
import DepartmentForm from "@/components/render/DepartmentForm";

export default class Route {


    constructor() {
        window.addEventListener("hashchange", e => this.changeRoute(e));
        this.routeMap = new Map([
            ["/", new MainRender()],
            ["department/edit/", new DepartmentForm()]
        ]);
    }

    changeRoute(e) {
        console.log(e);
        let hashLocation = window.location.hash.substring(1).split('?');
        console.log(hashLocation[1]);
        console.log(hashLocation[0]);
        let route = this.routeMap.get(hashLocation[0]).render(id);
    }


}


