function isSatisfied(count, form) {
    if (count > 0) {
        if (confirm('This department has ' + count + ' employees.\n' +
            'Do yo want to delete this department anyway?')) {
            form.submit();
        }
    } else {
        form.submit();
    }
}

function doEmployeeCheck() {
    $.ajax({
        url: "",
        data: ""


    })

}