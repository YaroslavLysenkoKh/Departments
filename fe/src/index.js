import $ from "jquery";
import Route from "@/components/Route";
import {RenderDepartments} from "@/components/render/DepartmentList";

$(document).ready(new RenderDepartments().doRequest());
new Route();
