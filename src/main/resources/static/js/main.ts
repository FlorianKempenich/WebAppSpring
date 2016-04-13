/**
 * Created by Shock on 4/13/2016.
 */


///<reference path="../typings/browser.d.ts"/>

let options:google.maps.MapOptions = {
    center: {
        lat: -34.397,
        lng: 150.644
    },
    zoom: 8
};

let map = new google.maps.Map(
    document.getElementById('map'),
    options
);
