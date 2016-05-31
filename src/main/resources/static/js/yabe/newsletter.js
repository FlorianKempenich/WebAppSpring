function incrementCookieCounter() {
    var counter = getCookieCounter();
    counter++;

    Cookies.set('counter', counter);
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

function showToolTip(emailFieldId, title) {
    $(emailFieldId).tooltip({
        title: title,
        placement: "top"
    }).tooltip('show');
    setTimeout(function () {
        $(emailFieldId).tooltip('destroy');
    }, 2000);
}

function initRegisterEmailButton(emailFieldId, thankYouId, submitButtonId) {
    $(submitButtonId).click(function (event) {

        $(submitButtonId).prop("disabled", true);
        console.log("before post");

        var data = {};
        data["email"] = $(emailFieldId).val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/newsletter/subscribe",
            data: JSON.stringify(data),
            dataType: 'json',
            timeout: 60000,
            success: function (data) {
                $(submitButtonId).prop("disabled", false);
                $(emailFieldId).fadeOut(500, function () {
                    $(thankYouId).fadeIn(500, function () {
                        setTimeout(function () {
                            $('#newsletterPopup').modal('hide');
                        }, 400);
                    });
                });

            },
            error: function (e) {
                $(submitButtonId).prop("disabled", false);
                showToolTip(emailFieldId, "Please verify your email address!");
            }
        });

    })
}
