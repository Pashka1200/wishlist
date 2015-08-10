/**
 * Created by admin on 31.07.2015.
 */
jQuery(function ($) {
    'use strict';

//      *******Menu controllers******
    $('body').on('click', ".home", function () {
        window.location.href = "/index/user";
    });
    $('body').on('click', ".friends", function () {
        window.location.href = "/index/friends";
    });
    $('body').on('click', ".tobuy", function () {
        window.location.href = "/index/tobuy";
    });
    $('body').on('click', ".options", function () {
        window.location.href = "/index/options";
    });

//      ******HELPERS******
    $(document).ready(
        function() {
            $("html").niceScroll();
        }
    );




});