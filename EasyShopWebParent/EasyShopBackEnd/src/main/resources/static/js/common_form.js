var MAX_FILE_SIZE = 102400; // 100KB

$(document).ready(function () {
    $("#buttonCancel").on("click", function () {
        window.location.href = moduleURL;
    });

    $("#fileImage").change(function () {
        var fileSize = this.files[0].size;
        alert("File size: " + fileSize);

        if (fileSize > MAX_FILE_SIZE) {
            this.setCustomValidity("File size must be less than 100KB!");
            this.reportValidity();
        } else {
            this.setCustomValidity("");
            showImageThumbnail(this);
        }
    });
});

function showImageThumbnail(fileInput) {
    var file = fileInput.files[0];
    var reader = new FileReader();
    reader.onload = function (e) {
        $("#thumbnail").attr("src", e.target.result);
    };

    reader.readAsDataURL(file);
}

function showModalDialog(title, message) {
    $("#modalTittle").text(title);
    $("#modalBody").text(message);
    $("#modalDialog").modal();
}

function showWarningModal(message) {
    showModalDialog("Warning", message);
}

function showErrorModal(message) {
    showModalDialog("Error", message);
}
