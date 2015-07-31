jQuery(function ($) {
    'use strict';

    $('body').on('click', "#user-submit", function () {

        var fb_id = $("#fb_id").val();
        var postData = {fb_id: fb_id};
        console.log(postData);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/index/login/check",
            dataType:'json',
            data: JSON.stringify(postData),

            success:  function (json) {
               console.log(json);
            },

            error: function () {
                console.log('error');
            }

        });
    });

});