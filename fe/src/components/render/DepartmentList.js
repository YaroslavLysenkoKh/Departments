import $ from "jquery";
import {URL} from "@/components/Constant";

export class RenderDepartments {

    doRequest() {
        $.ajax({
            type: 'GET',
            url: URL,
            dataType: "json",
            success: this.renderTable
        });
    }

    renderTable(data) {
        let a, appDiv;
        appDiv = document.createElement("div");
        appDiv.setAttribute('id', 'appDiv');
        let div = document.createElement('div');
        let p = document.createElement('p');
        p.appendChild(a = document.createElement('a'));
        a.setAttribute('href', '#/department/edit');
        a.setAttribute('role', 'button');
        a.innerHTML = "Add Department";
        a.addEventListener('click', () => {
            window.location.hash = window.location.hash;
        });
        div.appendChild(p);

        let thead, th, tbody, row, td;

        let table = document.createElement("table");
        table.style.width = '100%';
        table.setAttribute('border', '1');
        table.appendChild(thead = document.createElement("thead"));

        thead.appendChild(th = document.createElement("th"));
        th.innerText = 'Name';
        thead.appendChild(th = document.createElement("th"));
        th.innerText = 'Action';
        th.setAttribute('colspan', '3');

        table.appendChild(tbody = document.createElement("tbody"));

        data.forEach((department) => {

            tbody.appendChild(row = document.createElement("tr"));

            row.appendChild(td = document.createElement("td"));
            td.append(department.name);

            row.appendChild(td = document.createElement("td"));
            let buttonEmployeeList = document.createElement('input');
            buttonEmployeeList.setAttribute('type', 'button');
            buttonEmployeeList.setAttribute('value', 'Employee List')
            buttonEmployeeList.addEventListener('click', () => {
                window.location.hash = window.location.hash + "/employee?id=" + department.id;
            });
            td.append(buttonEmployeeList);

            row.appendChild(td = document.createElement("td"));
            let buttonEditDepartment = document.createElement('input');
            buttonEditDepartment.setAttribute('type', 'button');
            buttonEditDepartment.setAttribute('value', 'Edit')
            buttonEditDepartment.addEventListener('click', () => {
                window.location.hash = "/department/edit?id=" + department.id;
            });
            td.appendChild(buttonEditDepartment);

            row.appendChild(td = document.createElement("td"));
            let buttonDelete = document.createElement('input');
            buttonDelete.setAttribute('type', 'button');
            buttonDelete.setAttribute('value', 'Delete');
            buttonDelete.addEventListener('click', () => {
                // this.depAjax.deleteDepartment(data[department].id);
            });
            td.appendChild(buttonDelete);

        });
        let body = document.getElementsByTagName('body')[0];
        appDiv.appendChild(div);
        appDiv.appendChild(table);
        body.append(appDiv);
    }
}
