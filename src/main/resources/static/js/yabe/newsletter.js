/**
 * Created by Shock on 5/30/2016.
 */

function cook() {
    Cookies.set('name', true);
    // Cookies.remove('name');
    var cookie = Cookies.get('name');

    if (cookie === 'true') {
        console.log("true :D :D")
    }

    console.log(cookie);
}






function setCookie() {
    Cookies.set('test', 'hello'); //expires in days
}

function removeCookie() {
    Cookies.remove('test');
    return true;
}

function displayCookie() {
    var cookie = Cookies.get('test');
    $('#cookieResult').append(cookie);
}














function showModal() {
    $('#newsletterPopup').modal('show');
    console.log("opening");
}

function automaticPopup(mode, autoClose) {

    $('#newsletterPopup').on('hidden.bs.modal', function (e) {
        setCookie();
    });


    switch(mode) {
        case "atendpage":
            $(window).scroll(function(){
                var yPos = $(window).scrollTop();
                if (yPos >= ($(document).height() - $(window).height()) ) {
                    showModal();
                } else {
                    if (yPos + 300 < ($(document).height() - $(window).height()) ) {
                        if(autoClose == true) {
                            closeWindow();
                        }
                    }
                }

            });
            break;
        
        case "onload":
            $(window).load(function(){
                showModal();
                if(autoClose == true) {
                    scrollDetection("scrollStart", function() {
                        closeWindow();
                    });

                }
            });
            break;

        case "onidle":

            $(window).load(function(){
                scrollDetection("scrollEnd", function() {
                    showModal();
                });

                if(autoClose == true) {
                    scrollDetection("scrollStart", function() {
                        closeWindow();
                    });
                }
            });

            break;
    }

}

function scrollDetection(trigger, onDone) {
    var t, l = (new Date()).getTime();

    $(window).scroll(function(){
        var now = (new Date()).getTime();

        if(now - l > 400){
            $(this).trigger('scrollStart');
            l = now;
        }

        clearTimeout(t);
        t = setTimeout(function(){
            $(window).trigger('scrollEnd');
        }, 300);
    });
    if (trigger == "scrollStart") {
        $(window).bind('scrollStart', function(){
            $(window).unbind('scrollEnd');
            onDone();
        });
    }

    if (trigger == "scrollEnd") {
        $(window).bind('scrollEnd', function(){
            $(window).unbind('scrollStart');
            onDone();
        });
    }
}