function incrementCookieCounter() {
    var counter = getCookieCounter();
    counter++;

    Cookies.set('counter', counter);
}

function removeCookieCounter() {
    Cookies.remove('counter');
}

function getCookieCounter() {
    var counterCookie = Cookies.get('counter');
    var counter;
    if (counterCookie == null) {
        counter = 0;
    } else {
        counter = parseInt(counterCookie);
    }
    return counter;
}
function displayCookieCounter() {
    var cookie = getCookieCounter();
    console.log(cookie);
}

function showModal() {
    $('#newsletterPopup').modal('show');
    console.log("opening");
}

/**
 * Display the newsletter popup after the user scrolled to the bottom of 3 pages.
 * Do not display for 2 months after that.
 *
 * todo: If user enter email, never display again
 */
function initNewsletterPopup() {

    // todo: If user enter email, never display again
    // $('#newsletterPopup').on('hidden.bs.modal', function (e) {
    //     setCookie();
    // });

    $(window).scroll(function () {
        var yPos = $(window).scrollTop();
        if (yPos >= ($(document).height() - $(window).height())) {
            var counter = getCookieCounter();
            if (counter === 2) {
                showModal();
            }
            if (counter <= 2) {
                incrementCookieCounter();
            }
        }
    });
}

function initRegisterEmailButton() {
    $("#register-newsletter-sidebar").click(function (event) {

        $("#register-newsletter-sidebar").prop("disabled", true);
        console.log("before post");

        var data = {};
        data["email"] = $('#email-field-sidebar').val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/newsletter/subscribe",
            data: JSON.stringify(data),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
                $("#register-newsletter-sidebar").prop("disabled", false);
                console.log(data.email);
                console.log("post success");
                //todo tooltip in reaction to response
                // -> Success: "Thank you"
                // -> Invalid: "Error"
            },
            error: function (e) {
                $("#register-newsletter-sidebar").prop("disabled", false);
                console.log("post failed");
                console.log(e);
                //todo tooltip in reaction to response
                // -> Success: "Thank you"
                // -> Invalid: "Error"
            }
        });

    })
}