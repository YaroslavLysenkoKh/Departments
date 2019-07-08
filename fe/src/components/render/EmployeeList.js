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
        let a , appDiv;

        appDiv = document.getElementById('appDiv');
        appDiv.innerHTML="";

        let div = document.createElement('div');
        let p = document.createElement('p');
        p.appendChild(a = document.createElement('a'));
        a.setAttribute('href', '#employee');
        a.setAttribute('role', 'button');
        a.innerHTML = "Add Employee";
        div.appendChild(p);

        let thead, th, tbody;

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
        employeeList.forEach((employee) => {
          tbody.appendChild(row = document.createElement("tr"));

          row.appendChild(td = document.createElement("td"));
          td.append(employee.email);

          row.appendChild(td = document.createElement("td"));
          td.append(employee.salary);

          row.appendChild(td = document.createElement("td"));
          td.append(employee.birthDate);

          row.appendChild(td = document.createElement("td"));
          let buttonEditEmployee = document.createElement('input');
          buttonEditEmployee.setAttribute('type', 'button');
          buttonEditEmployee.setAttribute('value', 'Edit')
          buttonEditEmployee.addEventListener('click', () => {
              window.location.hash = "/employee/edit?id=" + employee.id;
          });
          td.appendChild(buttonEditEmployee);

          row.appendChild(td = document.createElement("td"));
          let buttonDelete = document.createElement('input');
          buttonDelete.setAttribute('type', 'button');
          buttonDelete.setAttribute('value', 'Delete');
          buttonDelete.addEventListener('click', () => {
              window.location.hash = "/employee/delete?id=" + employee.id;
          });
          td.appendChild(buttonDelete);

        });

        let body = document.getElementsByTagName('body')[0];
        appDiv.appendChild(div);
        appDiv.appendChild(table);
        body.append(appDiv);

    }
}
