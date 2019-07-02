import $ from "jquery";
import {URL} from "../Constant";

let instance = null;
export default class EmployeeAJAX {
    constructor() {
        if (!instance) {
            instance = this;
        }
        return instance;
    }

    getAll(id) {
        $.ajax({
            type: 'GET',
            url: URL + '/employee/' + $(id).val(),
            dataType: "json",
            success: function (data) {
                alert(data);
            }
        });
    }

}