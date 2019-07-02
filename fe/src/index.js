import DepartmentAJAX from "@/components/department/departmentAJAX";
import Route from "@/components/Route";

const myObj = [{id: "1", name: "department1"}, {id: "2", name: "department2 "}]

class RenderDepartment {

    constructor() {
        this.depAjax = new DepartmentAJAX();
        // this.route = new Route()
    }

    renderTable(data) {

        let a;
        let div = document.createElement('div');
        let p = document.createElement('p');
        p.appendChild(a = document.createElement('a'));
        a.setAttribute('href', '#department');
        a.setAttribute('role', 'button');
        a.innerHTML = "Add Department";
        div.appendChild(p);

        let thead, th, tbody;
        let body = document.getElementsByTagName('body')[0];
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
        for (let department in data) {

            let row = tbody.insertRow(data.indexOf(department));

            let cellName = row.insertCell(data.indexOf(department));
            cellName.innerHTML = data[department].name;

            let cellEmployeesList = row.insertCell(data.indexOf(department));
            let buttonEmployeeList = document.createElement('input');
            buttonEmployeeList.setAttribute('type', 'button');
            buttonEmployeeList.setAttribute('value', 'Employee List')
            buttonEmployeeList.addEventListener('click', () => {
                //this.depAjax.renderEmployeeList(data[department].id);
                window.location.hash = window.location.href + "/test";
            });
            cellEmployeesList.appendChild(buttonEmployeeList);

            let cellEditDepartment = row.insertCell(data.indexOf(department));
            let buttonEditEmployee = document.createElement('input');
            buttonEditEmployee.setAttribute('type', 'button');
            buttonEditEmployee.setAttribute('value', 'Edit')
            buttonEditEmployee.addEventListener('click', () => {
                // this.depAjax.editForm(data[department].id);
                window.location.hash = "/department/edit/?id=" + data[department].id;
                this.depAjax.getById(data[department].id);
            });
            cellEditDepartment.appendChild(buttonEditEmployee);

            let cellDeleteDepartment = row.insertCell(data.indexOf((department)));
            let buttonDelete = document.createElement('input');
            buttonDelete.setAttribute('type', 'button');
            buttonDelete.setAttribute('value', 'Delete');
            buttonDelete.addEventListener('click', () => {
                this.depAjax.deleteDepartment(data[department].id);

            });
            cellDeleteDepartment.appendChild(buttonDelete);

            tbody.appendChild(row);
        }
        body.appendChild(div);
        body.appendChild(table);
    }
}

let renderClass = new RenderDepartment();
renderClass.renderTable(myObj);

new Route();
