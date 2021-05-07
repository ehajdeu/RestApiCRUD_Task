$("#add-doctor-submit").on("click", () => {
    if ($('#doctorId').val()) {
        updateDoctor();
    } else {
        addUpdateDoctor();
    }
});

function addUpdateDoctor() {
    if (validInput() === true) {
        $.ajax({
            method: "POST",
            url: "/rest/doctors/",
            data: JSON.stringify(doctorObject()),
            contentType: "application/json",
            success: response => {
                alert(`SUCCESS: ${response}`);
                hideAddForm();
                getAllDoctors();
            },
            error: err => {
                let errorObj = err.responseJSON;
                alert(`ERROR: "${errorObj.message}" \nTIME: ${errorObj.time}`);
            }
        });
    } else {
        alert("Invalid input");
    }
}

const doctorObject = () => {
    return {
        id: $("#doctorId").val(),
        firstName: $.trim($("#firstName").val()),
        lastName: $.trim($("#lastName").val()),
        specialty: $.trim($("#specialty").val()),
        officeNumber: parseInt($("#officeNumber").val())
    };
};

const validInput = () => {
    return $.trim($("#firstName").val()) && $.trim($("#lastName").val()) && $.trim($("#specialty").val()) && parseInt($("#officeNumber").val()) >= 0;
};

function updateDoctor() {
    if (validInput() === true) {
        const id = $('#doctorId').val();
        $.ajax({
            url: `/rest/doctors/${id}`,
            method: "PUT",
            data: JSON.stringify(doctorObject()),
            contentType: "application/json",
            success: response => {
                alert(`SUCCESS: ${response}`);
                hideAddForm();
                getAllDoctors();
            },
            error: err => {
                const errorObj = err.responseJSON;
                alert(`ERROR: "${errorObj.message}" \nTIME: ${errorObj.time}`);
            }
        });
    } else {
        alert("Invalid input");
    }
}

function hideAddForm() {
    document.getElementById('add-div').style.visibility = 'hidden';
}
