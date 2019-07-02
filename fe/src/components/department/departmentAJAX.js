import {URL} from "@/components/Constant";
import $ from 'jquery';

let instance = null;

export default class DepartmentAJAX {

    constructor() {
        if (!instance) {
            instance = this;
        }
        return instance;
    }

    getAll() {
        $.ajax({
            type: 'GET',
            url: URL + '/',
            dataType: "json",
            success: function (data) {
                alert(data);
            }
        });
    }

    getById(id) {
        $.ajax({
            type: 'GET',
            url: URL + '/department/edit/' + id,
            dataType: "json",
            success: function (data) {
                alert(data);
            }
        });

    }

    deleteDepartment(id) {
        $.ajax({
            type: 'DELETE',
            url: URL + '/' + $(id).val(),
            success: this.getAll()
        });
    }

    editForm(id) {
        $.ajax({
            type: 'GET',
            url: URL + '/department/' + $(id).val(),
            success: renderDepFrom()
        })
    }

    addOrUpdate(department) {
        $.ajax({
            type: 'POST',
            url: URL + '/department/',
            success: ''
        })
    }
}
