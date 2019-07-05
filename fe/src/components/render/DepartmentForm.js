import $ from "jquery";
import {URL} from "@/components/Constant";
import {addOrUpdate} from "@/components/department/addOrUpdate";

export default class DepartmentForm {

    doRequest(departmentId) {
        if (departmentId) {
            $.ajax({
                type: 'GET',
                url: URL + '/department/edit/' + departmentId,
                dataType: "json",
                success: this.renderForm
            });
        }
        this.renderForm();
    }

    renderForm(department) {

        let div;

        document.getElementById('appDiv').innerHTML = "";

        let body = document.getElementsByTagName('body')[0];

        let form = document.createElement('form');
        form.appendChild(div = document.createElement("div"));

        let inputDepId = document.createElement('input');
        inputDepId.setAttribute('type', 'hidden');
        inputDepId.setAttribute('name', 'id');
        if (department) {
            inputDepId.setAttribute('value', department.id);
        }
        inputDepId.setAttribute('value', '');
        div.appendChild(inputDepId);

        let labelDepName = document.createElement('label');
        labelDepName.innerHTML = "Department Name";
        div.appendChild(labelDepName);

        let inputDepName = document.createElement('input');
        inputDepName.setAttribute('type', 'text');
        inputDepName.setAttribute('name', 'name');
        inputDepName.setAttribute('placeholder', "Enter name");
        if (department) {
            inputDepName.setAttribute('value', 'dep.name');
        }
        inputDepName.setAttribute('value', '');
        div.appendChild(inputDepName);
        form.appendChild(div);

        let smallName = document.createElement('small');
        smallName.setAttribute('id', "smallErrorName");
        smallName.innerHTML = "";

        let buttonSubmit = document.createElement('button');
        buttonSubmit.setAttribute('type', 'submit');
        buttonSubmit.innerHTML = "Submit";
        buttonSubmit.addEventListener('click', () => {
            window.location.hash = window.location.hash + "/department/addOrUpdate";
            let id = $('id').val();
            let name = $('name').val();
            addOrUpdate(id, name);
        });
        form.appendChild(buttonSubmit);

        body.appendChild(form);
    }
}
