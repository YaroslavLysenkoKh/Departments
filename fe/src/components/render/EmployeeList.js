import $ from "jquery";
import {URL} from "@/components/Constant";

export class EmployeeList {

    doRequest(departmentId) {
        $.ajax({
            type: 'GET',
            url: URL + '/employee/' + departmentId,
            dataType: "json",
            success: this.renderList
        });
    }

    renderList(employeeList) {
        let a;
        let div = document.createElement('div');
        let p = document.createElement('p');
        p.appendChild(a = document.createElement('a'));
        a.setAttribute('href', '#employee');
        a.setAttribute('role', 'button');
        a.innerHTML = "Add Employee";
        div.appendChild(p);

        let thead, th, tbody;
        let body = document.getElementsByTagName('body')[0];
        let table = document.createElement("table");
        table.style.width = '100%';
        table.setAttribute('border', '1');
        table.appendChild(thead = document.createElement("thead"));

        thead.appendChild(th = document.createElement("th"));
        th.innerText = 'Email';
        thead.appendChild(th = document.createElement("th"));
        th.innerText = 'Salary';
        thead.appendChild(th = document.createElement("th"));
        th.innerText = 'Birth Date';
        thead.appendChild(th = document.createElement("th"));
        th.innerText = 'Action';
        th.setAttribute('colspan', '2');

        table.appendChild(tbody = document.createElement("tbody"));
        employeeList.forEach((value, index) => {

        });
    }
}