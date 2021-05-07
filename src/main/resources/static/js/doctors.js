$(document).ready(() => {
    getAllDoctors();
});

function getAllDoctors() {
    $.ajax({
        url: "/rest/doctors",
        method: "GET",
        success: response => {
            displayDoctors(response);
        },
        error: err => {
            let responseObj = err.responseJSON;
            alert(`ERROR: " ${responseObj.message} " TIME ${responseObj.time}`);
        }
    })
}
function displayDoctors(doctors) {
    if (doctors.length>0) {
        let placeholder = '';
        $.each(doctors, (index, doctor) => {
            placeholder +=
            `<tr>
                <td>${(index+1)}</td>;
                <td>${(doctor.firstName)}</td>;
                <td>${(doctor.lastName)}</td>;
                <td>${(doctor.specialty)}</td>;
                <td>${(doctor.officeNumber)}</td>;
                <td><button class='update-doctor' 
                data-doctor-id="${doctor.id}"
                data-doctor-first-name="${doctor.firstName}"
                data-doctor-last-name="${doctor.lastName}"
                data-doctor-specialty="${doctor.specialty}"
                data-doctor-office-number="${doctor.officeNumber}">Update</button></td>
                <td><button class='delete-doctor' data-doctor-id="${doctor.id}">Delete</button></td>
            </tr>`;
        });
        $("#doctors-placeholder tbody").html(placeholder);
    } else {
        $("#doctors-div").html("<p>No doctors in the system.</p>");
    }
}

$("#add").on("click", () => {
    resetAddForm();
    showAddForm();
});

$("#doctors-placeholder").on("click", ".delete-doctor", function(clickEvent) {
    if(confirm("Click okay to confirm doctor delete request")) {
        let id = $(clickEvent.target).data('doctor-id');
        $.ajax({
            url: `/rest/doctors/${id}`,
            method: "DELETE",
            success: message => {
                alert(`SUCCESS: ${message}`);
                getAllDoctors();
            },
            error: err => {
                let responseObj = err.responseJSON;
                alert(`ERROR: "${responseObj.message}" TIME: ${responseObj.time}`);
            }
        });
    }
});

$("#doctors-placeholder").on("click", ".update-doctor", getSelection(), (clickEvent) => {
    const doctorId = $(clickEvent.target).data('doctor-id');
    const firstName = $(clickEvent.target).data('doctor-first-name');
    const lastName = $(clickEvent.target).data('doctor-last-name');
    const specialty = $(clickEvent.target).data('doctor-specialty');
    let officeNumber = $(clickEvent.target).data('doctor-office-number');
    if($.trim(officeNumber)) {
        officeNumber = parseInt(officeNumber);
    }
    resetAddForm();
    $('#doctorId').val(doctorId);
    $("#firstName").val(firstName);
    $("#lastName").val(lastName);
    $("#specialty").val(specialty);
    $("#officeNumber").val(officeNumber);
    showAddForm();
});

function resetAddForm() {
    $("#doctorId").val(undefined);
    $("#firstName").val(undefined);
    $("#lastName").val(undefined);
    $("#specialty").val(undefined);
    $("#officeNumber").val(undefined);
}

function showAddForm() {
    document.getElementById('add-div').style.visibility = 'visible';
}
