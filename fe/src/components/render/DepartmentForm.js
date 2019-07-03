import $ from "jquery";
import {URL} from "@/components/Constant";

export default class DepartmentForm {

    render(departmentId) {
        $.ajax({
            type: 'GET',
            url: URL + '/department/edit/' + departmentId,
            dataType: "json",
            success: this.renderForm
        });
    }

    renderForm(department) {
        // let department = this.depAjax.getById(id)

        let div;
        let body = document.getElementsByTagName('body')[0];

        let form = document.createElement('form');
        form.appendChild(div = document.createElement("div"));

        let inputDepId = document.createElement('input');
        inputDepId.setAttribute('type', 'hidden');
        inputDepId.setAttribute('name', 'id');
        if (dep !== null) {
            inputDepId.setAttribute('value', 'dep.id');
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
        if (dep !== null) {
            inputDepName.setAttribute('value', 'dep.name');
        }
        inputDepName.setAttribute('value', '');
        div.appendChild(inputDepName);
        form.appendChild(div);

        let buttonSubmit = document.createElement('button');
        buttonSubmit.setAttribute('type', 'submit');
        buttonSubmit.innerHTML = "Submit";
        buttonSubmit.addEventListener('click', () => {
            this.depAjax.addOrUpdate($('form').serializeJSON());
        });
        form.appendChild(buttonSubmit);

        body.appendChild(form);
    }
}
